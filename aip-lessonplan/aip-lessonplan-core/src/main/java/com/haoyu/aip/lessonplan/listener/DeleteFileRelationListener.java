package com.haoyu.aip.lessonplan.listener;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.aip.lessonplan.entity.LessonPlanUser;
import com.haoyu.aip.lessonplan.event.DeleteLessonPlanUserEvent;
import com.haoyu.aip.lessonplan.service.ILessonPlanRelationService;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.aip.lessonplan.service.ILessonPlanUserService;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.event.DeleteFileRelationEvent;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.file.utils.FileRelationType;

@Component
public class DeleteFileRelationListener implements ApplicationListener<DeleteFileRelationEvent>{
	
	@Resource
	private ILessonPlanService lessonPlanService;
	@Resource
	private ILessonPlanRelationService lessonPlanRelationService;
	@Resource
	private ILessonPlanUserService lessonPlanUserService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IFileService fileService;

	@Override
	public void onApplicationEvent(DeleteFileRelationEvent event) {
		FileRelation fileRelation = (FileRelation) event.getSource();
		if (FileRelationType.LESSON_PLAN_RELATION.toString().equals(fileRelation.getType())) {
			LessonPlanRelation lessonPlanRelation = lessonPlanRelationService.get(fileRelation.getRelation().getId());
			
			Map<String, Object> param = Maps.newHashMap();
			param.put("creator", ThreadContext.getUser().getId());
			param.put("relationId", lessonPlanRelation.getId());
			int count = fileService.getFileInfoCount(param);
			if (count == 0) {
				LessonPlanUser lessonPlanUser = new LessonPlanUser();
				lessonPlanUser.setLessonPlanRelation(lessonPlanRelation);
				String lessonPlanUserId = LessonPlanUser.getId(lessonPlanRelation.getId(), ThreadContext.getUser().getId());
				lessonPlanUser.setId(lessonPlanUserId);
				lessonPlanUserService.deleteLessonPlanUser(lessonPlanUserId);
				applicationContext.publishEvent(new DeleteLessonPlanUserEvent(lessonPlanUser));
			}
			LessonPlanRelation lr = new LessonPlanRelation();
			lr.setId(lessonPlanRelation.getId());
			lessonPlanRelation.setFileNum(1);
			lessonPlanRelation.setParticipateNum(1);
			lessonPlanRelationService.update(lessonPlanRelation);
		}
	}
}
