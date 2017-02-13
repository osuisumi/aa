package com.haoyu.aip.lessonplan.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.entity.LessonPlanRecord;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface ILessonPlanRecordService {

	Response create(LessonPlanRecord obj);

	Response update(LessonPlanRecord obj);

	Response delete(String id);

	LessonPlanRecord get(String id);

	List<LessonPlanRecord> list(SearchParam searchParam, PageBounds pageBounds);
}
