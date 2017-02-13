package com.haoyu.aip.lessonplan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.dao.ILessonPlanDao;
import com.haoyu.aip.lessonplan.entity.LessonPlan;
import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.aip.lessonplan.event.CreateLessonPlanEvent;
import com.haoyu.aip.lessonplan.event.DeleteLessonPlanEvent;
import com.haoyu.aip.lessonplan.event.UpdateLessonPlanEvent;
import com.haoyu.aip.lessonplan.service.ILessonPlanRelationService;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;

@Service
public class LessonPlanServiceImpl implements ILessonPlanService{

	@Resource
	private ILessonPlanDao lessonPlanDao;
	@Resource
	private ILessonPlanRelationService lessonPlanRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private ITagRelationService tagRelationService;
	
	@Override
	public Response create(LessonPlan obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)lessonPlanDao);
	}

	@Override
	public Response update(LessonPlan obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)lessonPlanDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)lessonPlanDao);
	}

	@Override
	public LessonPlan get(String id) {
		return (LessonPlan) BaseServiceUtils.get(id, (MybatisDao)lessonPlanDao);
	}

	@Override
	public List<LessonPlan> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)lessonPlanDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response createLessonPlan(LessonPlan lessonPlan) {
		Response response = this.create(lessonPlan);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(lessonPlan.getLessonPlanRelations())) {
				for (LessonPlanRelation lessonPlanRelation : lessonPlan.getLessonPlanRelations()) {
					if (lessonPlanRelation.getRelation() != null && StringUtils.isNotEmpty(lessonPlanRelation.getRelation().getId())) {
						if (lessonPlanRelation.getLessonPlan() == null || StringUtils.isEmpty(lessonPlanRelation.getLessonPlan().getId())) {
							lessonPlanRelation.setLessonPlan(lessonPlan);
						}
						String id = LessonPlanRelation.getId(lessonPlanRelation.getLessonPlan().getId(), lessonPlanRelation.getRelation().getId());
						lessonPlanRelation.setId(id);
						lessonPlanRelationService.create(lessonPlanRelation);
					}
					lessonPlanRelation.setLessonPlan(null);
				}
			}
			tagRelationService.createTagRelation(lessonPlan.getTags(), new Relation(lessonPlan.getId(),"lesson_plan"), true);
			applicationContext.publishEvent(new CreateLessonPlanEvent(lessonPlan));
			response.setResponseData(lessonPlan);
		}
		return response;
	}

	@Override
	public Response updateLessonPlan(LessonPlan lessonPlan) {
		Response response = this.update(lessonPlan);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(lessonPlan.getLessonPlanRelations())) {
				for (LessonPlanRelation lessonPlanRelation : lessonPlan.getLessonPlanRelations()) {
					if (StringUtils.isNotEmpty(lessonPlanRelation.getId())) {
						lessonPlanRelationService.updateByIdNotSelective(lessonPlanRelation);
					}
				}
			}
			tagRelationService.createTagRelation(lessonPlan.getTags(), new Relation(lessonPlan.getId(),"lesson_plan"), true);
			applicationContext.publishEvent(new UpdateLessonPlanEvent(lessonPlan));
		}
		return response;
	}

	@Override
	public LessonPlan viewLessonPlan(String id) {
		LessonPlan lessonPlan = this.get(id);
		if (lessonPlan != null) {
			if (Collections3.isNotEmpty(lessonPlan.getLessonPlanRelations())) {
				for (LessonPlanRelation lessonPlanRelation : lessonPlan.getLessonPlanRelations()) {
					LessonPlanRelation lpr = new LessonPlanRelation();
					lpr.setId(lessonPlanRelation.getId());
					lessonPlanRelationService.updateBrowseNum(lpr);
				}
			}
		}
		return lessonPlan;
	}

	@Override
	public Response deleteLessonPlan(LessonPlan lessonPlan) {
		Response response = this.delete(lessonPlan.getId());
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteLessonPlanEvent(lessonPlan));
		}
		return response;
	}
}
