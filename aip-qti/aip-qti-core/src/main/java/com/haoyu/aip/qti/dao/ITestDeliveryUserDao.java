/**
 * 
 */
package com.haoyu.aip.qti.dao;

import java.util.List;

import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.sip.core.entity.User;

/**
 * @author lianghuahuang
 *
 */
public interface ITestDeliveryUserDao {
	
	TestDeliveryUser selectTestDeliveryUserById(String id);
	
	List<TestDeliveryUser> selectTestDeliveryUsers(TestDeliveryUser testDeliveryUser);
	
	int insertTestDeliveryUser(TestDeliveryUser testDeliveryUser);
	
	int updateTestDeliveryUser(TestDeliveryUser testDeliveryUser);
	
}
