package com.haoyu.aip.video.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.video.entity.Video;
import com.haoyu.aip.video.service.IVideoService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class VideoDataDirective implements TemplateDirectiveModel {

	@Resource
	private IVideoService videoService;
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id")){
			String id = params.get("id").toString();
			Video video = videoService.getVideo(id);
			if (video != null) {
				env.setVariable("video", new DefaultObjectWrapper().wrap(video));
			}else{
				env.setVariable("video", new DefaultObjectWrapper().wrap(new Video()));
			}
		}
		body.render(env.getOut());
	}

}