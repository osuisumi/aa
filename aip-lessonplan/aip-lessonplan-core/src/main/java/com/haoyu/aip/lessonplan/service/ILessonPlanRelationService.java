package com.haoyu.aip.lessonplan.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface ILessonPlanRelationService{
	
	Response create(LessonPlanRelation obj);
	
	Response update(LessonPlanRelation obj);
	
	Response delete(String id);
	
	LessonPlanRelation get(String id);
	
	List<LessonPlanRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateBrowseNum(LessonPlanRelation obj);
	
	Response updateReplyNum(LessonPlanRelation obj);
	
	Response updateParticipateNum(LessonPlanRelation obj);

	Response updateByIdNotSelective(LessonPlanRelation lessonPlanRelation);


	
}
