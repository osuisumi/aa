package com.haoyu.aip.evaluate.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.entity.Evaluate;
import com.haoyu.sip.core.service.Response;

public interface IEvaluateService{
	Response create(Evaluate evaluate);

	Response update(Evaluate evaluate);

	Response deleteByLogic(Evaluate evaluate);

	Evaluate get(String id);

	List<Evaluate> list(Evaluate evaluate,PageBounds pageBounds);
	
	Integer countByEvaluateEntityId(String evaluateEntityId);

}
