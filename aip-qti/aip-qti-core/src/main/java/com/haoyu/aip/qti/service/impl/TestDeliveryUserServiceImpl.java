/**
 * 
 */
package com.haoyu.aip.qti.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import uk.ac.ed.ph.jqtiplus.JqtiExtensionManager;
import uk.ac.ed.ph.jqtiplus.JqtiExtensionPackage;
import uk.ac.ed.ph.jqtiplus.QtiConstants;
import uk.ac.ed.ph.jqtiplus.exception.QtiCandidateStateException;
import uk.ac.ed.ph.jqtiplus.internal.util.Assert;
import uk.ac.ed.ph.jqtiplus.node.AssessmentObjectType;
import uk.ac.ed.ph.jqtiplus.node.QtiNode;
import uk.ac.ed.ph.jqtiplus.node.result.AbstractResult;
import uk.ac.ed.ph.jqtiplus.node.result.AssessmentResult;
import uk.ac.ed.ph.jqtiplus.node.result.ItemVariable;
import uk.ac.ed.ph.jqtiplus.node.result.OutcomeVariable;
import uk.ac.ed.ph.jqtiplus.node.result.SessionStatus;
import uk.ac.ed.ph.jqtiplus.notification.NotificationLevel;
import uk.ac.ed.ph.jqtiplus.notification.NotificationRecorder;
import uk.ac.ed.ph.jqtiplus.resolution.ResolvedAssessmentTest;
import uk.ac.ed.ph.jqtiplus.running.TestPlanner;
import uk.ac.ed.ph.jqtiplus.running.TestProcessingInitializer;
import uk.ac.ed.ph.jqtiplus.running.TestSessionController;
import uk.ac.ed.ph.jqtiplus.running.TestSessionControllerSettings;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.state.AssessmentSectionSessionState;
import uk.ac.ed.ph.jqtiplus.state.ItemSessionState;
import uk.ac.ed.ph.jqtiplus.state.TestPartSessionState;
import uk.ac.ed.ph.jqtiplus.state.TestPlan;
import uk.ac.ed.ph.jqtiplus.state.TestPlanNode;
import uk.ac.ed.ph.jqtiplus.state.TestPlanNodeKey;
import uk.ac.ed.ph.jqtiplus.state.TestProcessingMap;
import uk.ac.ed.ph.jqtiplus.state.TestSessionState;



import uk.ac.ed.ph.jqtiplus.state.marshalling.TestSessionStateXmlMarshaller;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.types.ResponseData;
import uk.ac.ed.ph.jqtiplus.types.StringResponseData;
import uk.ac.ed.ph.jqtiplus.xmlutils.xslt.XsltSerializationOptions;
import uk.ac.ed.ph.jqtiplus.xmlutils.xslt.XsltStylesheetManager;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.haoyu.aip.qti.dao.ITestDao;
import com.haoyu.aip.qti.dao.ITestDeliveryUserDao;
import com.haoyu.aip.qti.dao.ITestDeliveryUserOutcomeDao;
import com.haoyu.aip.qti.dao.ITestPackageDao;
import com.haoyu.aip.qti.dao.ITestSubmissionDao;
import com.haoyu.aip.qti.entity.ResponseLegality;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestDeliveryUserOutcome;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.entity.TestSubmission;
import com.haoyu.aip.qti.event.SubmitTestDeliveryUserEvent;
import com.haoyu.aip.qti.exception.QtiLogicException;
import com.haoyu.aip.qti.exception.QtiRuntimeException;
import com.haoyu.aip.qti.service.ITestDeliveryUserService;
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
public class TestDeliveryUserServiceImpl implements ITestDeliveryUserService {
	@Resource
	private ITestDeliveryUserDao testDeliveryUserDao;

	@Resource
	private TestPackageFileService testPackageFileService;

	
	private JqtiExtensionManager jqtiExtensionManager;
	
	@Resource
	private ITestDao testDao;
	//应用访问地址 域名+contextPath
    @Value("#{commonConfig['qti.base.url']}")
    private String qtiBaseUrl;
    
    @Resource
    private FilespaceManager filespaceManager;
    
    
    private QtiSerializer qtiSerializer;
    
    @Resource
    private ITestDeliveryUserOutcomeDao testDeliveryUserOutcomeDao;
    

	@Resource
	private ApplicationContext applicationContext;
	
	@Resource
	private ITestSubmissionDao testSubmissionDao;
    
    public TestDeliveryUserServiceImpl(){
    	 final List<JqtiExtensionPackage<?>> extensionPackages = new ArrayList<JqtiExtensionPackage<?>>();
    	this.jqtiExtensionManager=new JqtiExtensionManager(extensionPackages);
    	this.qtiSerializer = new QtiSerializer(jqtiExtensionManager);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.haoyu.aip.qti.service.ITestDeliveryUserService#
	 * enterOrReenterTestDeliveryUser(com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public AssessmentResult enterOrReenterTestDeliveryUser(TestDeliveryUser tdu) {
		// final TestDelivery testDelivery = testDelivery
		
		List<TestDeliveryUser> testDeliveryUsers = testDeliveryUserDao.selectTestDeliveryUsers(tdu);
		if (testDeliveryUsers != null&&!testDeliveryUsers.isEmpty()) {
			TestDeliveryUser testDeliveryUser =testDeliveryUsers.get(0);
			tdu.setId(testDeliveryUser.getId());
			tdu.setTestDelivery(testDeliveryUser.getTestDelivery());
			tdu.setCompletionStatus(testDeliveryUser.getCompletionStatus());
			tdu.setFinishTime(testDeliveryUser.getFinishTime());
			tdu.setSumScore(testDeliveryUser.getSumScore());
			tdu.setAttempts(testDeliveryUser.getAttempts());
			
			final File sessionFile = getSessionStateFile(testDeliveryUser);
			if(sessionFile.exists()){
				
				final NotificationRecorder notificationRecorder = new NotificationRecorder(
						NotificationLevel.INFO);
				final TestSessionState testSessionState = loadTestSessionState(testDeliveryUser);
				
	
				final TestSessionController testSessionController = createTestSessionController(
						testDeliveryUser, testSessionState, notificationRecorder);
	
				AssessmentResult ar = computeAndRecordTestAssessmentResult(testDeliveryUser,testSessionController);
	
				/* Handle immediate end of test session */
				if (testSessionState.isEnded()) {
					 testDeliveryUser.setFinishTime(System.currentTimeMillis());
				     testDeliveryUserDao.updateTestDeliveryUser(testDeliveryUser);
				}
	
				return ar;
			}else{
				Test test = testDao.selectTestById(testDeliveryUser.getTestDelivery().getTest().getId());
				final TestProcessingMap testProcessingMap = getTestProcessingMap(test.getTestPackage());
				if (testProcessingMap == null) {
					return null;
				}
				return createSession(testDeliveryUser, testProcessingMap);
				
			}
			
		}else{
			
			TestDeliveryUser testDeliveryUser = new TestDeliveryUser();
			testDeliveryUser.setId(Identities.uuid2());
			testDeliveryUser.setCompletionStatus(QtiConstants.COMPLETION_STATUS_UNKNOWN);
			testDeliveryUser.setDeliveryUser(tdu.getDeliveryUser());
			testDeliveryUser.setTestDelivery(tdu.getTestDelivery());
			int count = testDeliveryUserDao.insertTestDeliveryUser(testDeliveryUser);
			if(count>0){
			
				TestDelivery testDelivery = testDeliveryUser.getTestDelivery();
				Test test = testDao.selectTestById(testDelivery.getTest().getId());
				
				testDelivery.setTest(test);
				testDeliveryUser.setTestDelivery(testDelivery);

				tdu.setId(testDeliveryUser.getId());
				tdu.setTestDelivery(testDelivery);
				tdu.setCompletionStatus(testDeliveryUser.getCompletionStatus());
				tdu.setFinishTime(testDeliveryUser.getFinishTime());
				tdu.setSumScore(testDeliveryUser.getSumScore());
				tdu.setAttempts(testDeliveryUser.getAttempts());
				
				
				final TestProcessingMap testProcessingMap = getTestProcessingMap(test.getTestPackage());
				if (testProcessingMap == null) {
					return null;
				}
		
				return createSession(testDeliveryUser, testProcessingMap);
			}
		}
		return null;
		
	}

	/**
	 * @param testDeliveryUser
	 * @param testProcessingMap
	 * @return
	 */
	private AssessmentResult createSession(TestDeliveryUser testDeliveryUser,
			final TestProcessingMap testProcessingMap) {
		/* Generate a test plan for this session */
		final TestPlanner testPlanner = new TestPlanner(testProcessingMap);
		/*
		 * if (notificationRecorder!=null) {
		 * testPlanner.addNotificationListener(notificationRecorder); }
		 */
		final TestPlan testPlan = testPlanner.generateTestPlan();

		/* Create fresh state for session */
		final TestSessionState testSessionState = new TestSessionState(testPlan);

		/* Create config for TestSessionController */
		// final DeliverySettings testDeliverySettings =
		// assessmentDataService.getEffectiveDeliverySettings(candidate,
		// delivery);
		final TestSessionControllerSettings testSessionControllerSettings = new TestSessionControllerSettings();
		/* Create controller and wire up notification recorder */
		final TestSessionController testSessionController = new TestSessionController(
				jqtiExtensionManager, testSessionControllerSettings,
				testProcessingMap, testSessionState);

		final Date timestamp = new Date();
		testSessionController.initialize(timestamp);
		final int testPartCount = testSessionController.enterTest(timestamp);
		testSessionController.enterNextAvailableTestPart(timestamp);
		
		storeTestSessionState(testDeliveryUser,testSessionState);
		AssessmentResult ar = computeAndRecordTestAssessmentResult(testDeliveryUser,testSessionController);
		return ar;
	}
	
	public TestSessionController createTestSessionController(
			final TestDeliveryUser testDeliveryUser,
			final TestSessionState testSessionState,
			final NotificationRecorder notificationRecorder) {
		final TestDelivery delivery = testDeliveryUser.getTestDelivery();
		Test test = testDao.selectTestById(delivery.getTest().getId());
		/* Try to resolve the underlying JQTI+ object */
		final TestPackage testPackage = test.getTestPackage();
		final TestProcessingMap testProcessingMap = getTestProcessingMap(testPackage);
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

	
    public AssessmentResult computeAndRecordTestAssessmentResult(final TestDeliveryUser testDeliveryUser, final TestSessionController testSessionController) {
        final AssessmentResult assessmentResult = computeTestAssessmentResult(testDeliveryUser, testSessionController);
        recordTestAssessmentResult(testDeliveryUser, assessmentResult);
        return assessmentResult;
    }

    public void recordTestAssessmentResult(final TestDeliveryUser testDeliveryUser, final AssessmentResult assessmentResult) {
        /* First record full result XML to filesystem */
        storeAssessmentResultFile(testDeliveryUser, assessmentResult);

        /* Then record test outcome variables to DB */
        recordOutcomeVariables(testDeliveryUser, assessmentResult.getTestResult());
    }

	public AssessmentResult computeTestAssessmentResult(
			final TestDeliveryUser testDeliveryUser,
			final TestSessionController testSessionController) {
		final URI sessionIdentifierSourceId = URI
				.create(qtiBaseUrl);
		final String sessionIdentifier = "testsession/"
				+ testDeliveryUser.getId();
		return testSessionController.computeAssessmentResult(
				new Date(),
				sessionIdentifier, sessionIdentifierSourceId);
	}
	
	private void storeAssessmentResultFile(final TestDeliveryUser testDeliveryUser, final QtiNode resultNode) {
        final File resultFile = getAssessmentResultFile(testDeliveryUser);
        FileOutputStream resultStream = null;
        try {
            resultStream = new FileOutputStream(resultFile);
            qtiSerializer.serializeJqtiObject(resultNode, resultStream);
        }
        catch (final Exception e) {
            throw QtiRuntimeException.unexpectedException(e);
        }
        finally {
            ServiceUtilities.ensureClose(resultStream);
        }
    }
	
    private File getAssessmentResultFile(final TestDeliveryUser testDeliveryUser) {
        final File sessionFolder = filespaceManager.obtainTestDeliveryUserStateStore(testDeliveryUser);
        return new File(sessionFolder, "assessmentResult.xml");
    }
    
    private void recordOutcomeVariables(final TestDeliveryUser testDeliveryUser, final AbstractResult resultNode) {
    	testDeliveryUserOutcomeDao.deleteTestDleverUserOutcome(testDeliveryUser);
        for (final ItemVariable itemVariable : resultNode.getItemVariables()) {
            if (itemVariable instanceof OutcomeVariable
                    || QtiConstants.VARIABLE_DURATION_IDENTIFIER.equals(itemVariable.getIdentifier())) {
                final TestDeliveryUserOutcome outcome = new TestDeliveryUserOutcome();
                outcome.setId(Identities.uuid2());
                outcome.setTestDeliveryUser(testDeliveryUser);
                outcome.setOutcomeIdentifier(itemVariable.getIdentifier().toString());
                outcome.setBaseType(itemVariable.getBaseType().toQtiString());
                outcome.setCardinality(itemVariable.getCardinality().toQtiString());
                outcome.setStringValue(itemVariable.getComputedValue().toQtiString());
                if(itemVariable.getIdentifier().equals(Identifier.assumedLegal("totalScore"))){
                	testDeliveryUser.setSumScore(Double.valueOf(outcome.getStringValue()));
                }
                testDeliveryUserOutcomeDao.insertTestDeliveryUserOutcome(outcome);
            }
        }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.aip.qti.service.ITestDeliveryUserService#finishTest(com.haoyu
	 * .aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public Response finishTest(TestDeliveryUser tdu) {

	        /* Get current JQTI state and create JQTI controller */
	        final NotificationRecorder notificationRecorder = new NotificationRecorder(NotificationLevel.INFO);
	        TestDeliveryUser testDeliveryUser = findTestDeliveryUserById(tdu.getId());
	        User currectUser = ThreadContext.getUser();
			//判断提交选项的用户是否为testDeliveryUser所属用户
			if(!currectUser.equals(testDeliveryUser.getDeliveryUser())){
				return Response.failInstance().responseMsg("该测验不允许当前用户提交，请重新登录后再操作");
			}
			if(testDeliveryUser.getCompletionStatus().equals(QtiConstants.COMPLETION_STATUS_COMPLETED)&&testDeliveryUser.getAttempts()>=testDeliveryUser.getTestDelivery().getTest().getMaxAttempts()&&testDeliveryUser.getTestDelivery().getTest().getMaxAttempts()>0){
				return Response.failInstance().responseMsg("该测验已完成，不允许重新提交！");
			}
	    	final TestSessionState testSessionState = loadTestSessionState(testDeliveryUser);
	    	
	    //	testSessionState.reset();
	    	
	        final TestSessionController testSessionController = createTestSessionController(testDeliveryUser,testSessionState, notificationRecorder);
	        

	        /* Update state */
	       // testSessionState.setDurationIntervalStartTime(new Date());
	      //  testSessionController.res
	      //  testSessionController
	        testSessionController.endCurrentTestPart(new Date());

	        /* Record current result */
	        final AssessmentResult assessmentResult = computeAndRecordTestAssessmentResult(testDeliveryUser, testSessionController);

	        /* If there are now no more available testParts, finish the session now */
	        if (testSessionController.findNextEnterableTestPart()==null) {
	        	testDeliveryUser.setCompletionStatus(QtiConstants.COMPLETION_STATUS_COMPLETED);
	        	testDeliveryUser.setFinishTime(System.currentTimeMillis());
	            int count = testDeliveryUserDao.updateTestDeliveryUser(testDeliveryUser);
	            if(count>0){
	            	applicationContext.publishEvent(new SubmitTestDeliveryUserEvent(testDeliveryUser));
	            }
	            
	        }

	        /* Record and log event */
	        storeTestSessionState(testDeliveryUser,testSessionState);

	        return Response.successInstance().responseData(testDeliveryUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.aip.qti.service.ITestDeliveryUserService#findTestDeliveryUsers
	 * (com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public List<TestDeliveryUser> findTestDeliveryUsers(
			TestDeliveryUser testDeliveryUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.aip.qti.service.ITestDeliveryUserService#findTestDeliveryUsers
	 * (com.haoyu.aip.qti.entity.TestDeliveryUser,
	 * com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<TestDeliveryUser> findTestDeliveryUsers(
			TestDeliveryUser testDeliveryUser, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	public TestProcessingMap getTestProcessingMap(final TestPackage testPackage) {
		final ResolvedAssessmentTest resolvedAssessmentTest = testPackageFileService
				.loadAndResolveAssessmentObject(testPackage);
		TestProcessingMap result = new TestProcessingInitializer(
				resolvedAssessmentTest, true).initialize();
		return result;
	}


	public TestSessionState loadTestSessionState(
			final TestDeliveryUser testDeliveryUser) {
		final Document document = loadStateDocument(testDeliveryUser);
		return TestSessionStateXmlMarshaller.unmarshal(document
				.getDocumentElement());
	}

	public Document loadStateDocument(final TestDeliveryUser testDeliveryUser) {
		final File sessionFile = ensureSessionStateFile(testDeliveryUser);
		final DocumentBuilder documentBuilder = XmlUtilities
				.createNsAwareDocumentBuilder();
		try {
			return documentBuilder.parse(sessionFile);
		} catch (final Exception e) {
			throw new QtiLogicException(
					"Could not parse serailized state XML. This is an internal error as we currently don't expose this data to clients", 
					e);
		}
	}

	public File ensureSessionStateFile(final TestDeliveryUser testDeliveryUser) {
		final File sessionStateFile = getSessionStateFile(testDeliveryUser);
		if (!sessionStateFile.exists()) {
			throw new QtiLogicException("Expectation failed: State file "
					+ sessionStateFile + " does not exist");
		}
		return sessionStateFile;
	}

	private File getSessionStateFile(final TestDeliveryUser testDeliveryUser) {
		final String stateFileBaseName = "testSessionState";
		final File sessionFolder = filespaceManager
				.obtainTestDeliveryUserStateStore(testDeliveryUser);
		final String stateFileName = stateFileBaseName + ".xml";
		return new File(sessionFolder, stateFileName);
	}
	
	 public void storeTestSessionState(TestDeliveryUser testDeliveryUser, final TestSessionState testSessionState) {
	        final Document stateDocument = TestSessionStateXmlMarshaller.marshal(testSessionState);
	        storeStateDocument(testDeliveryUser, stateDocument);
	  }
	 
	 private void storeStateDocument(TestDeliveryUser testDeliveryUser, final Document stateXml) {
	        final File sessionFile = getSessionStateFile(testDeliveryUser);
	        final XsltSerializationOptions xsltSerializationOptions = new XsltSerializationOptions();
	        xsltSerializationOptions.setIndenting(true);
	        xsltSerializationOptions.setIncludingXMLDeclaration(false);
	        final Transformer serializer = XsltStylesheetManager.createSerializer(xsltSerializationOptions);
	        FileOutputStream resultStream = null;
	        try {
	            resultStream = new FileOutputStream(sessionFile);
	            serializer.transform(new DOMSource(stateXml), new StreamResult(resultStream));
	        }
	        catch (final TransformerException e) {
	            throw new QtiRuntimeException("Unexpected Exception serializing state DOM", e);
	        }
	        catch (final FileNotFoundException e) {
	            throw QtiRuntimeException.unexpectedException(e);
	        }
	        finally {
	            ServiceUtilities.ensureClose(resultStream);
	        }
	    }

	@Override
	public TestDeliveryUser findTestDeliveryUserById(String id) {
		return testDeliveryUserDao.selectTestDeliveryUserById(id);
	}

	@Override
	public Response finishTest(TestDeliveryUser tdu,
			Map<String, StringResponseData> stringResponseMap) {
		/* Get current JQTI state and create JQTI controller */
        final NotificationRecorder notificationRecorder = new NotificationRecorder(NotificationLevel.INFO);
        TestDeliveryUser testDeliveryUser = findTestDeliveryUserById(tdu.getId());
        User currectUser = ThreadContext.getUser();
		//判断提交选项的用户是否为testDeliveryUser所属用户
		if(!currectUser.equals(testDeliveryUser.getDeliveryUser())){
			return Response.failInstance().responseMsg("该测验不允许当前用户提交，请重新登录后再操作");
		}
		if(testDeliveryUser.getCompletionStatus().equals(QtiConstants.COMPLETION_STATUS_COMPLETED)){
			if(testDeliveryUser.getAttempts()>=testDeliveryUser.getTestDelivery().getTest().getMaxAttempts()&&testDeliveryUser.getTestDelivery().getTest().getMaxAttempts()>0){
				return Response.failInstance().responseMsg("该测验已超过最大允许提交次数，无法重新提交！");
			}
		}
    	final TestSessionState testSessionState = loadTestSessionState(testDeliveryUser);
    	
    	final TestSessionController testSessionController = createTestSessionController(testDeliveryUser,testSessionState, notificationRecorder);
    	//提交答案
        Response response = handleResponses(testDeliveryUser,stringResponseMap,testSessionState,testSessionController);
        if(!response.isSuccess()){
        	return response;
        }
   
        
        Map<TestPlanNodeKey,TestPartSessionState> tssMap = testSessionState.getTestPartSessionStates();
        for(TestPartSessionState iss:tssMap.values()){
        	iss.setDurationIntervalStartTime(new Date());
    	}
        testSessionController.endCurrentTestPart(new Date());

        /* Record current result */
        computeAndRecordTestAssessmentResult(testDeliveryUser, testSessionController);

        /* If there are now no more available testParts, finish the session now */
        if (testSessionController.findNextEnterableTestPart()==null) {
        	testDeliveryUser.setCompletionStatus(QtiConstants.COMPLETION_STATUS_COMPLETED);
        	testDeliveryUser.setFinishTime(System.currentTimeMillis());
            int count = testDeliveryUserDao.updateTestDeliveryUser(testDeliveryUser);
            if(count>0){
            	applicationContext.publishEvent(new SubmitTestDeliveryUserEvent(testDeliveryUser));
            }
            
        }

        /* Record and log event */
        storeTestSessionState(testDeliveryUser,testSessionState);

        return Response.successInstance().responseData(testDeliveryUser);
	}
	
	
	private Response handleResponses(TestDeliveryUser testDeliveryUser,
			Map<String, StringResponseData> stringResponseMap,final TestSessionState testSessionState ,TestSessionController testSessionController) {		
		Map<TestPlanNodeKey, AssessmentSectionSessionState> tssMap = testSessionState.getAssessmentSectionSessionStates();
		for (AssessmentSectionSessionState ass :tssMap.values()){
			ass.setDurationIntervalStartTime(new Date());
			ass.setEndTime(null);
		}
		
		final TestPlanNodeKey currentTestPartKey = testSessionState.getCurrentTestPartKey();
		final TestPlanNode testPlanNode = testSessionState.getTestPlan().getNode(currentTestPartKey);
		final TestPartSessionState testPartSessionState = testSessionState.getTestPartSessionStates().get(testPlanNode.getKey());

		testPartSessionState.setDurationIntervalStartTime(new Date());
		testPartSessionState.setEndTime(null);
		
		Map<TestPlanNodeKey,ItemSessionState> issMap = testSessionState.getItemSessionStates();
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
			Map<Identifier, ResponseData> responseDataMap = new HashMap<Identifier, ResponseData>();
			responseDataMap.put(Identifier.parseString(key.substring(key.indexOf("_")+1)), responseData);
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
				ItemSessionState iss = issMap.get(TestPlanNodeKey
						.fromString(candidateItemResponse.getItemKey()));
				iss.setSessionStatus(SessionStatus.INITIAL);
				testSessionController.selectItemNonlinear(new Date(),TestPlanNodeKey
						.fromString(candidateItemResponse.getItemKey()));
				iss.setSuspendTime(null);
				//结束时间置空，否则处理时会提示为完成状态无法更新
	    		iss.setEndTime(null);

				testSessionController.handleResponsesToCurrentItem(new Date(),
						responseDataMap);
				
			} catch (final QtiCandidateStateException e) {
				e.printStackTrace();
				return Response.failInstance().responseMsg(e.getMessage());
			} catch (final RuntimeException e) {
				e.printStackTrace();
				return Response.failInstance().responseMsg(e.getMessage());
			}
			
			
		}

		final Document stateDocument = TestSessionStateXmlMarshaller
				.marshal(testSessionState);

		final File sessionFile = ensureSessionStateFile(testDeliveryUser);
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
		return Response.successInstance();
	}


}
