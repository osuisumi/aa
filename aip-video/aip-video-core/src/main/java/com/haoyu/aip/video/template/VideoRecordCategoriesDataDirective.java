package com.haoyu.aip.video.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.tempuri.MessageResultOfListOfAppKeyValue;

import com.haoyu.aip.video.service.IVideoRecordService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class VideoRecordCategoriesDataDirective implements TemplateDirectiveModel {

	@Resource
	private IVideoRecordService videoRecordService;
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		MessageResultOfListOfAppKeyValue messageResultOfListOfAppKeyValue = videoRecordService.getCategoryAppKeyValue();
		if (messageResultOfListOfAppKeyValue.getData() != null) {
			env.setVariable("categories", new DefaultObjectWrapper().wrap(messageResultOfListOfAppKeyValue.getData()));
		}
		body.render(env.getOut());
	}

}