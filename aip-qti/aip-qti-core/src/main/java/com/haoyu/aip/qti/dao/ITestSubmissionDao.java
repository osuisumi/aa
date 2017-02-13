package com.haoyu.aip.qti.dao;

import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestSubmission;

public interface ITestSubmissionDao {
	int insertTestSubmission(TestSubmission testSubmission);
	
	int deleteTestSubmissionByPhysics(TestDeliveryUser testDeliveryUser);
	
	int deleteTestSubmissionByPhysics(String id);
}
