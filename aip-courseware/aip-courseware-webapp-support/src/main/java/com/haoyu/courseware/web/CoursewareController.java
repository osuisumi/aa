package com.haoyu.courseware.web;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.courseware.entity.Courseware;
import com.haoyu.aip.courseware.service.ICoursewareService;
import com.haoyu.aip.evaluate.entity.Evaluate;
import com.haoyu.aip.evaluate.service.IEvaluateService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.file.service.IFileService;

@Controller
@RequestMapping(value = "courseware")
public class CoursewareController extends AbstractBaseController {
	@Resource
	private ICoursewareService coursewareService;
	@Resource
	private IFileService fileService;
	@Resource
	private IEvaluateService evaluateService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Courseware courseware) {
		return courseware.getType() + "/edit_" + courseware.getType();
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(Courseware courseware, Model model) {
		model.addAttribute("courseware", coursewareService.get(courseware.getId()));
		return courseware.getType() + "/edit_" + courseware.getType();
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Response save(Courseware courseware, String startTime, String endTime, String teachTimeForm) {
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				courseware.getCoursewareRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")));
			}
			if (StringUtils.isNotEmpty(endTime)) {
				courseware.getCoursewareRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")));
			}
			if (StringUtils.isNotEmpty(teachTimeForm)) {
				courseware.setTeachTime(DateUtils.parseDate(teachTimeForm, PropertiesLoader.get("activity.timePeriod.pattern")));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (courseware.getTeacher() == null || StringUtils.isBlank(courseware.getTeacher().getId())) {
			courseware.setTeacher(ThreadContext.getUser());
		}
		if (StringUtils.isEmpty(courseware.getId())) {
			return this.coursewareService.create(courseware);
		} else {
			return this.coursewareService.update(courseware);
		}

	}

	@RequestMapping(value = "{id}/view", method = RequestMethod.GET)
	public String view(Courseware courseware, Model model, HttpServletRequest request) {
		courseware = coursewareService.get(courseware.getId());
		String role = "normal";
		if (courseware.getTeacher() != null && courseware.getTeacher().getId().equals(ThreadContext.getUser().getId())) {
			role = "teacher";
		} else if (courseware.getCreator() != null && courseware.getCreator().getId().equals(ThreadContext.getUser().getId())) {
			role = "creator";
		}
		boolean isEvaluate = false;
		Evaluate evaluate = evaluateService.get(DigestUtils.md5Hex(courseware.getId() + ThreadContext.getUser().getId()));
		if (evaluate != null) {
			isEvaluate = true;
		}
		model.addAttribute("isEvaluate", isEvaluate);
		model.addAttribute("fileInfos", fileService.listFileInfoByRelationId(courseware.getId()));
		model.addAttribute("courseware", courseware);
		model.addAttribute("role", role);
		model.addAllAttributes(request.getParameterMap());
		return courseware.getType()+"/view_"+courseware.getType();

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response delete(Courseware courseware) {
		return coursewareService.deleteByLogic(courseware);
	}

	// 上传附件
	@RequestMapping(value = "uploadFile")
	public @ResponseBody Response uploadFile(Courseware courseware, String relationId) {
		return fileService.createFileList(courseware.getFileInfos(), relationId, "listenCourse_doc");
	}

}
