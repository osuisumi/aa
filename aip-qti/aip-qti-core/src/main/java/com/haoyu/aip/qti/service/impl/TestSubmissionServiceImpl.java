/**
 * 
 */
package com.haoyu.aip.qti.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import uk.ac.ed.ph.jqtiplus.JqtiExtensionManager;
import uk.ac.ed.ph.jqtiplus.JqtiExtensionPackage;
import uk.ac.ed.ph.jqtiplus.exception.QtiCandidateStateException;
import uk.ac.ed.ph.jqtiplus.notification.NotificationLevel;
import uk.ac.ed.ph.jqtiplus.notification.NotificationRecorder;
import uk.ac.ed.ph.jqtiplus.running.TestSessionController;
import uk.ac.ed.ph.jqtiplus.running.TestSessionControllerSettings;
import uk.ac.ed.ph.jqtiplus.state.TestPlanNodeKey;
import uk.ac.ed.ph.jqtiplus.state.TestProcessingMap;
import uk.ac.ed.ph.jqtiplus.state.TestSessionState;
import uk.ac.ed.ph.jqtiplus.state.marshalling.TestSessionStateXmlMarshaller;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.types.ResponseData;
import uk.ac.ed.ph.jqtiplus.types.StringResponseData;
import uk.ac.ed.ph.jqtiplus.xmlutils.xslt.XsltSerializationOptions;
import uk.ac.ed.ph.jqtiplus.xmlutils.xslt.XsltStylesheetManager;

import com.haoyu.aip.qti.dao.ITestDao;
import com.haoyu.aip.qti.dao.ITestSubmissionDao;
import com.haoyu.aip.qti.entity.ResponseLegality;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.entity.TestSubmission;
import com.haoyu.aip.qti.exception.QtiLogicException;
import com.haoyu.aip.qti.exception.QtiRuntimeException;
import com.haoyu.aip.qti.service.ITestDeliveryUserService;
import com.haoyu.aip.qti.service.ITestSubmissionService;
import com.haoyu.aip.qti.utils.XmlUtilities;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TestSubmissionServiceImpl implements ITestSubmissionService {
	@Resource
	private FilespaceManager filespaceManager;

	@Resource
	private TestPackageFileService testPackageFileService;

	//@Resource
	private JqtiExtensionManager jqtiExtensionManager;

	@Resource
	private ITestDeliveryUserService testDeliveryUserService;

	@Resource
	private ITestSubmissionDao testSubmissionDao;
	
	@Resource
	private ITestDao testDao;
	
	public TestSubmissionServiceImpl(){
		final List<JqtiExtensionPackage<?>> extensionPackages = new ArrayList<JqtiExtensionPackage<?>>();
	    this.jqtiExtensionManager=new JqtiExtensionManager(extensionPackages);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.aip.qti.service.ITestSubmissionService#handleResponses(com.
	 * haoyu.aip.qti.entity.TestDeliveryUser, java.util.Map, java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public Response handleResponses(String testDeliveryUserId,
			Map<String, StringResponseData> stringResponseMap) {		
		TestDeliveryUser testDeliveryUser = testDeliveryUserService.findTestDeliveryUserById(testDeliveryUserId);
		User currectUser = ThreadContext.getUser();
		//判断提交选项的用户是否为testDeliveryUser所属用户
		if(!currectUser.equals(testDeliveryUser.getDeliveryUser())){
			return Response.failInstance().responseMsg("该测验不允许当前用户提交，请重新登录后再操作");
		}
		/* Get current JQTI state and create JQTI controller */
		final NotificationRecorder notificationRecorder = new NotificationRecorder(
				NotificationLevel.INFO);
		final TestSessionState testSessionState = testDeliveryUserService.loadTestSessionState(testDeliveryUser);

		final TestSessionController testSessionController = createTestSessionController(
				testDeliveryUser, testSessionState, notificationRecorder);

		/* FIXME: Next wodge of code has some cut & paste! */

		/*
		 * Build response map in required format for JQTI+. NB: The following
		 * doesn't test for duplicate keys in the two maps. I'm not sure it's
		 * worth the effort.
		 */
		final Map<Identifier, ResponseData> responseDataMap = new HashMap<Identifier, ResponseData>();
		if (stringResponseMap != null) {
			for (final Entry<String, StringResponseData> stringResponseEntry : stringResponseMap
					.entrySet()) {
				final String key = stringResponseEntry.getKey();
				final StringResponseData stringResponseData = stringResponseEntry.getValue();
				responseDataMap.put(Identifier.parseString(key.substring(key.indexOf("_")+1)), stringResponseData);
			}
		}

		/*
		 * Build Map of responses in appropriate entity form. NB: Not ready for
		 * persisting yet.
		 */
		final Map<Identifier, TestSubmission> testSubmissionResponseMap = new HashMap<Identifier, TestSubmission>();
		for (final Entry<String, StringResponseData> responseEntry : stringResponseMap
				.entrySet()) {
			String key = responseEntry.getKey();
			final Identifier responseIdentifier = Identifier.parseString(key
					.substring(key.indexOf("_")));
			final ResponseData responseData = responseEntry.getValue();
			final TestSubmission candidateItemResponse = new TestSubmission();
			candidateItemResponse.setId(Identities.uuid2());
			candidateItemResponse.setResponseDataType(responseData.getType());
			candidateItemResponse.setResponseLegality(ResponseLegality.VALID); 
			candidateItemResponse
					.setItemKey(key.substring(0, key.indexOf("_")));
			candidateItemResponse.setTestDeliveryUser(testDeliveryUser);
			JsonMapper jsonMapper = new JsonMapper();
			switch (responseData.getType()) {
			case STRING:
				candidateItemResponse.setResponseData(jsonMapper
						.toJson(((StringResponseData) responseData)
								.getResponseData()));
				break;
			/*
			 * case FILE:
			 * candidateItemResponse.setFileSubmission(fileSubmissionMap
			 * .get(responseIdentifier)); break;
			 */
			default:
				throw new QtiLogicException("Unexpected switch case: "
						+ responseData.getType());
			}
			testSubmissionResponseMap.put(responseIdentifier,
					candidateItemResponse);			
			try {
				testSessionController.selectItemNonlinear(new Date(),TestPlanNodeKey
						.fromString(candidateItemResponse.getItemKey()));
				testSessionController.handleResponsesToCurrentItem(new Date(),
						responseDataMap);
			} catch (final QtiCandidateStateException e) {
				return Response.failInstance().responseMsg(e.getMessage());
			} catch (final RuntimeException e) {
				return null;
				// return handleExplosion(e, testDeliveryUser);
			}
		}

		final Document stateDocument = TestSessionStateXmlMarshaller
				.marshal(testSessionState);

		final File sessionFile = testDeliveryUserService.ensureSessionStateFile(testDeliveryUser);
		final XsltSerializationOptions xsltSerializationOptions = new XsltSerializationOptions();
		xsltSerializationOptions.setIndenting(true);
		xsltSerializationOptions.setIncludingXMLDeclaration(false);
		final Transformer serializer = XsltStylesheetManager
				.createSerializer(xsltSerializationOptions);
		FileOutputStream resultStream = null;
		try {
			resultStream = new FileOutputStream(sessionFile);
			serializer.transform(new DOMSource(stateDocument),
					new StreamResult(resultStream));
		} catch (final TransformerException e) {
			throw new QtiRuntimeException(
					"Unexpected Exception serializing state DOM", e);
		} catch (final FileNotFoundException e) {
			throw QtiRuntimeException.unexpectedException(e);
		} finally {
			ServiceUtilities.ensureClose(resultStream);
		}

		/* Persist CandidateResponse entities */
		for (final TestSubmission testSubmission : testSubmissionResponseMap
				.values()) {
			testSubmissionDao.insertTestSubmission(testSubmission);
		}

		/* Record current result state */
		testDeliveryUserService.computeAndRecordTestAssessmentResult(
				testDeliveryUser, testSessionController);
		return Response.successInstance().responseData(testDeliveryUser);
	}

	public TestSessionController createTestSessionController(
			final TestDeliveryUser testDeliveryUser,
			final TestSessionState testSessionState,
			final NotificationRecorder notificationRecorder) {
		final TestPackage testPackage = testDeliveryUser.getTestDelivery().getTest().getTestPackage();
		final TestProcessingMap testProcessingMap = testDeliveryUserService.getTestProcessingMap(testPackage);
		if (testProcessingMap == null) {
			return null;
		}

		/* Create controller and wire up notification recorder (if passed) */
		final TestSessionController result = new TestSessionController(
				jqtiExtensionManager, new TestSessionControllerSettings(),
				testProcessingMap, testSessionState);
		if (notificationRecorder != null) {
			result.addNotificationListener(notificationRecorder);
		}

		return result;
	}

	

}
