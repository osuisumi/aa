/**
 * 
 */
package com.haoyu.aip.qti.service;

import java.io.File;
import java.util.List;










import java.util.Map;

import org.w3c.dom.Document;

import uk.ac.ed.ph.jqtiplus.node.result.AssessmentResult;
import uk.ac.ed.ph.jqtiplus.running.TestSessionController;
import uk.ac.ed.ph.jqtiplus.state.TestProcessingMap;
import uk.ac.ed.ph.jqtiplus.state.TestSessionState;
import uk.ac.ed.ph.jqtiplus.types.StringResponseData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface ITestDeliveryUserService {
	
	TestDeliveryUser findTestDeliveryUserById(String id);
	
	AssessmentResult  enterOrReenterTestDeliveryUser(TestDeliveryUser testDeliveryUser);
	
	Response  finishTest(TestDeliveryUser testDeliveryUser);
	
	Response  finishTest(TestDeliveryUser testDeliveryUser,final Map<String, StringResponseData> stringResponseMap);
	
	List<TestDeliveryUser> findTestDeliveryUsers(TestDeliveryUser testDeliveryUser);
	
	List<TestDeliveryUser> findTestDeliveryUsers(TestDeliveryUser testDeliveryUser,PageBounds pageBounds);
	
	AssessmentResult computeAndRecordTestAssessmentResult(
			final TestDeliveryUser testDeliveryUser,
			final TestSessionController testSessionController);
	
	
	TestSessionState loadTestSessionState(
			final TestDeliveryUser testDeliveryUser);
	
	Document loadStateDocument(final TestDeliveryUser testDeliveryUser);
	
	File ensureSessionStateFile(final TestDeliveryUser testDeliveryUser);
	
	TestProcessingMap getTestProcessingMap(final TestPackage testPackage); 
}
