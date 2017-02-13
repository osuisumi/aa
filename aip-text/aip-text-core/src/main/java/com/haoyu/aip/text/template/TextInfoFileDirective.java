package com.haoyu.aip.text.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.text.entity.TextInfoFile;
import com.haoyu.aip.text.service.ITextInfoFileService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class TextInfoFileDirective extends AbstractTemplateDirectiveModel {

	@Resource
	private ITextInfoFileService textInfoFileService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		String id = (String) parameter.get("id");
		TextInfoFile textInfoFile = textInfoFileService.getTextInfoFile(id);
		env.setVariable("textInfoFileModel", new DefaultObjectWrapper().wrap(textInfoFile));
		body.render(env.getOut());
	}

}
