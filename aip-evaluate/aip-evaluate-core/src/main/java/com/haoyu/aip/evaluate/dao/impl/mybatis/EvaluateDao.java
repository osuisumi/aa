package com.haoyu.aip.evaluate.dao.impl.mybatis;

import org.springframework.stereotype.Repository;
import com.haoyu.aip.evaluate.dao.IEvaluateDao;
import com.haoyu.sip.core.jdbc.MybatisDao;
@Repository
public class EvaluateDao extends MybatisDao implements IEvaluateDao{

	@Override
	public Integer countByEvaluateEntityId(String evaluateEntityId) {
		return this.selectOne("countByEvaluateEntityId", evaluateEntityId);
	}
	

}
