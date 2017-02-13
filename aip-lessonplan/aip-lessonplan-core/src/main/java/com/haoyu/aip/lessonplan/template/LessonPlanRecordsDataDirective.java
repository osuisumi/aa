package com.haoyu.aip.lessonplan.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.aip.lessonplan.entity.LessonPlanRecord;
import com.haoyu.aip.lessonplan.service.ILessonPlanRecordService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.web.SearchParam;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class LessonPlanRecordsDataDirective extends AbstractTemplateDirectiveModel {
	@Resource
	private ILessonPlanRecordService lessonPlanRecordService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		SearchParam searchParam = new SearchParam();
		searchParam.setParamMap(getSelectParam(params));
		List<LessonPlanRecord> lessonPlanRecords = lessonPlanRecordService.list(searchParam, pageBounds);

		env.setVariable("lessonPlanRecords", new DefaultObjectWrapper().wrap(lessonPlanRecords));

		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList) lessonPlanRecords;
			env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}

		body.render(env.getOut());

	}

}
