package com.haoyu.aip.evaluate.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.dao.IEvaluateDao;
import com.haoyu.aip.evaluate.entity.Evaluate;
import com.haoyu.aip.evaluate.entity.EvaluateScore;
import com.haoyu.aip.evaluate.event.CreateEvaluateEvent;
import com.haoyu.aip.evaluate.event.DeleteEvaluateEvent;
import com.haoyu.aip.evaluate.service.IEvaluateScoreService;
import com.haoyu.aip.evaluate.service.IEvaluateService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;

@Service
public class EvaluateService implements IEvaluateService{
	@Resource
	private IEvaluateDao evaluateDao;
	@Resource
	private ApplicationContext applicationcontext;
	@Resource
	private IEvaluateScoreService evaluateScoreService;
	
	@Override
	public Response create(Evaluate entity) {
		String id = DigestUtils.md5Hex(entity.getEvaluateEntityId()+ThreadContext.getUser().getId());
		entity.setId(id);
		Response response =  BaseServiceUtils.create(entity, (MybatisDao)this.evaluateDao);
		Response responseSaveScore =null;
		if(response.isSuccess()){
			if(entity.getEvaluateScores()!=null && entity.getEvaluateScores().size()>0){
				for(EvaluateScore es:entity.getEvaluateScores()){
					es.setEvaluateId(id);
					es.setRelation(new Relation(entity.getEvaluateEntityId()));
					responseSaveScore = evaluateScoreService.create(es);
				}
			}
		}
		if(response.isSuccess()){
			applicationcontext.publishEvent(new CreateEvaluateEvent(entity));
		}
		return responseSaveScore;
	}


	@Override
	public Response deleteByLogic(Evaluate evaluate) {
		Response response = BaseServiceUtils.delete(evaluate.getId(), (MybatisDao)this.evaluateDao);
		if(response.isSuccess()){
			applicationcontext.publishEvent(new DeleteEvaluateEvent(evaluate));
		}
		return response;
	}

	@Override
	public Response update(Evaluate t) {
		Response response = null;
		response = BaseServiceUtils.update(t, (MybatisDao)this.evaluateDao);
		if(response!=null && response.isSuccess()){
			if(t.getEvaluateScores()!=null &&t.getEvaluateScores().size()>0){
				for(EvaluateScore es:t.getEvaluateScores()){
					es.setId(DigestUtils.md5Hex(t.getId()+es.getItem()));
					response = evaluateScoreService.update(es);
				}
			}
		}
		return response;
	}

	@Override
	public Evaluate get(String id) {
		return (Evaluate) BaseServiceUtils.get(id, (MybatisDao)this.evaluateDao);
	}

	@Override
	public List<Evaluate> list(Evaluate evaluateCourse, PageBounds pageBounds) {
		return ((MybatisDao)this.evaluateDao).selectList("select", evaluateCourse, pageBounds);
	}

	@Override
	public Integer countByEvaluateEntityId(String evaluateEntityId) {
		return this.evaluateDao.countByEvaluateEntityId(evaluateEntityId);
	}


}
