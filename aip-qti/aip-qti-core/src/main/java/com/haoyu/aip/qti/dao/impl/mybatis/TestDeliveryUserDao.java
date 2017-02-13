/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.aip.qti.dao.ITestDeliveryUserDao;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestDeliveryUserDao extends MybatisDao implements
		ITestDeliveryUserDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryUserDao#selectTestDeliveryUsers(com.haoyu.sip.core.entity.User, java.lang.String)
	 */
	@Override
	public List<TestDeliveryUser> selectTestDeliveryUsers(TestDeliveryUser testDeliveryUser) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("deliveryUserId",testDeliveryUser.getDeliveryUser().getId());
		parameter.put("testDeliveryId", testDeliveryUser.getTestDelivery().getId());
		if(testDeliveryUser.getCompletionStatus()!=null){
			parameter.put("completionStatus", testDeliveryUser.getCompletionStatus());
		}
		return super.selectList("selectByParameter", parameter);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryUserDao#insertTestDeliveryUser(com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public int insertTestDeliveryUser(TestDeliveryUser testDeliveryUser) {
		testDeliveryUser.setDefaultValue();
		return super.insert(testDeliveryUser);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryUserDao#updateTestDeliveryUser(com.haoyu.aip.qti.entity.TestDeliveryUser)
	 */
	@Override
	public int updateTestDeliveryUser(TestDeliveryUser testDeliveryUser) {
		testDeliveryUser.setUpdateValue();
		return super.update(testDeliveryUser);
	}

	@Override
	public TestDeliveryUser selectTestDeliveryUserById(String id) {
		return super.selectByPrimaryKey(id);
	}

}
