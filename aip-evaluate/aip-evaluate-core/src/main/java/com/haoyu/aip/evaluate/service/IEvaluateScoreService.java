package com.haoyu.aip.evaluate.service;
import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.entity.EvaluateScore;
import com.haoyu.sip.core.service.Response;

public interface IEvaluateScoreService {
	
	Response create(EvaluateScore evaluateScore);

	Response update(EvaluateScore evaluateScore);

	Response deleteByLogic(EvaluateScore evaluateScore);
	
	Response deleteByRelationId(String relationId);

	EvaluateScore get(String id);

	List<EvaluateScore> list(EvaluateScore evaluateScore,PageBounds pageBounds);
	
	List<Integer> getItemAvgScoreByRelationId(String relationId);
	
	Integer getAvgScoreByRelationId(String relationId);
	
}
