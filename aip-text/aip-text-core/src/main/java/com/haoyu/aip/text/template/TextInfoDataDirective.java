package com.haoyu.aip.text.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.text.entity.TextInfo;
import com.haoyu.aip.text.service.ITextInfoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class TextInfoDataDirective implements TemplateDirectiveModel {

	@Resource
	private ITextInfoService textInfoService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id")){
			String id = params.get("id").toString();
			TextInfo textInfo = textInfoService.getTextInfo(id);
			if (textInfo != null) {
				env.setVariable("textInfo", new DefaultObjectWrapper().wrap(textInfo));
			}else{
				env.setVariable("textInfo", new DefaultObjectWrapper().wrap(new TextInfo()));
			}
		}
		body.render(env.getOut());
	}

}
