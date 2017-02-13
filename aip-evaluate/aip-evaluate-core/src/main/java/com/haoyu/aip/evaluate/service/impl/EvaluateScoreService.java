package com.haoyu.aip.evaluate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.dao.IEvaluateScoreDao;
import com.haoyu.aip.evaluate.entity.EvaluateScore;
import com.haoyu.aip.evaluate.service.IEvaluateScoreService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
@Service
public class EvaluateScoreService implements IEvaluateScoreService{
	@Resource
	private IEvaluateScoreDao evaluateScoreDao;

	@Override
	public Response create(EvaluateScore evaluateScore) {
		evaluateScore.setId(DigestUtils.md5Hex(evaluateScore.getEvaluateId()+evaluateScore.getItem())); 
		return BaseServiceUtils.create(evaluateScore, (MybatisDao)this.evaluateScoreDao);
	}

	@Override
	public Response update(EvaluateScore evaluateScore) {
		return BaseServiceUtils.update(evaluateScore, (MybatisDao)this.evaluateScoreDao);
	}


	@Override
	public Response deleteByLogic(EvaluateScore evaluateScore) {
		return BaseServiceUtils.delete(evaluateScore.getId(), (MybatisDao)this.evaluateScoreDao);
	}

	@Override
	public EvaluateScore get(String id) {
		return (EvaluateScore) BaseServiceUtils.get(id, (MybatisDao)this.evaluateScoreDao);
	}

	@Override
	public List<EvaluateScore> list(EvaluateScore evaluateScore, PageBounds pageBounds) {
		return ((MybatisDao)this.evaluateScoreDao).selectList("select",evaluateScore,pageBounds);
	}

	@Override
	public List<Integer> getItemAvgScoreByRelationId(String relationId) {
		return ((MybatisDao)this.evaluateScoreDao).selectList("getItemAvgScoreByRelationId", relationId, null);
	}

	@Override
	public Integer getAvgScoreByRelationId(String relationId) {
		return ((MybatisDao)this.evaluateScoreDao).selectOne("getAvgScoreByRelationId", relationId);
	}

	@Override
	public Response deleteByRelationId(String relationId) {
		return this.evaluateScoreDao.deleteByRelationId(relationId)>0?Response.successInstance():Response.failInstance();
	}

}
