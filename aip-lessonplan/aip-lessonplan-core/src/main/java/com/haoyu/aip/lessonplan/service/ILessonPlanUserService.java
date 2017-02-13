package com.haoyu.aip.lessonplan.service;

import com.haoyu.aip.lessonplan.entity.LessonPlanUser;
import com.haoyu.sip.core.service.Response;

public interface ILessonPlanUserService {

	Response create(LessonPlanUser obj);

	Response deleteLessonPlanUser(String id);

}
