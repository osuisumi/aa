/**
 * 
 */
package com.haoyu.aip.qti.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.qti.dao.ITestDeliveryDao;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class TestDeliveryDao extends MybatisDao implements ITestDeliveryDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.dao.ITestDeliveryDao#insertTestDelivery(com.haoyu.aip.qti.entity.TestDelivery)
	 */
	@Override
	public int insertTestDelivery(TestDelivery testDelivery) {
		testDelivery.setDefaultValue();
		return super.insert(testDelivery);
	}
	
	public TestDelivery selectTestDelivery(String testId,String relationId){
		TestDelivery testDelivery = new TestDelivery();
		testDelivery.setTest(new Test(testId));
		testDelivery.setRelationId(relationId);
		return super.selectOne("selectByTestDelivery", testDelivery);
	}

}
