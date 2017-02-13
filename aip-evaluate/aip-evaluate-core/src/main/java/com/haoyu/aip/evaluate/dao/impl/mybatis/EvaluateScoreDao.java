package com.haoyu.aip.evaluate.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.evaluate.dao.IEvaluateScoreDao;
import com.haoyu.sip.core.jdbc.MybatisDao;
@Repository
public class EvaluateScoreDao extends MybatisDao implements IEvaluateScoreDao{

	@Override
	public int deleteByRelationId(String relationId) {
		return this.delete("deleteByRelationId", relationId);
	}

}
