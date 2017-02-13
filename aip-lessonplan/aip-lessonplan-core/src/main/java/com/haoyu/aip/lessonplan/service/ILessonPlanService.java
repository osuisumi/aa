package com.haoyu.aip.lessonplan.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.entity.LessonPlan;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface ILessonPlanService {

	Response create(LessonPlan obj);
	
	Response update(LessonPlan obj);
	
	Response delete(String id);
	
	LessonPlan get(String id);
	
	List<LessonPlan> list(SearchParam searchParam, PageBounds pageBounds);

	Response createLessonPlan(LessonPlan lessonPlan);

	Response updateLessonPlan(LessonPlan lessonPlan);

	LessonPlan viewLessonPlan(String id);

	Response deleteLessonPlan(LessonPlan lessonPlan);
}
