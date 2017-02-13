package com.haoyu.aip.lessonplan.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.aip.lessonplan.entity.LessonPlanUser;
import com.haoyu.aip.lessonplan.event.CreateLessonPlanUserEvent;
import com.haoyu.aip.lessonplan.service.ILessonPlanRelationService;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.aip.lessonplan.service.ILessonPlanUserService;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.event.UploadFileEvent;
import com.haoyu.sip.file.utils.FileRelationType;

@Component
public class UploadFileListener implements ApplicationListener<UploadFileEvent>{
	
	@Resource
	private ILessonPlanService lessonPlanService;
	@Resource
	private ILessonPlanRelationService lessonPlanRelationService;
	@Resource
	private ILessonPlanUserService lessonPlanUserService;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(UploadFileEvent event) {
		FileRelation fileRelation = (FileRelation) event.getSource();
		if (FileRelationType.LESSON_PLAN_RELATION.toString().equals(fileRelation.getType())) {
			LessonPlanRelation lessonPlanRelation = lessonPlanRelationService.get(fileRelation.getRelation().getId());
			LessonPlanUser lessonPlanUser = new LessonPlanUser();
			lessonPlanUser.setLessonPlanRelation(lessonPlanRelation);
			String lessonPlanUserId = LessonPlanUser.getId(lessonPlanRelation.getId(), ThreadContext.getUser().getId());
			lessonPlanUser.setId(lessonPlanUserId);
			try {
				lessonPlanUserService.create(lessonPlanUser);
				applicationContext.publishEvent(new CreateLessonPlanUserEvent(lessonPlanUser));
			} catch (DuplicateKeyException e) {
				
			}
			LessonPlanRelation lr = new LessonPlanRelation();
			lr.setId(lessonPlanRelation.getId());
			lessonPlanRelation.setFileNum(1);
			lessonPlanRelation.setParticipateNum(1);
			lessonPlanRelationService.update(lessonPlanRelation);
		}
	}

}
