package com.haoyu.aip.video.service;

import java.util.Map;

import org.tempuri.MessageResultOfListOfAppKeyValue;
import org.tempuri.MessageResultOfListOfAppVideo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface IVideoRecordService {
	
	MessageResultOfListOfAppVideo getMessageResultOfListOfAppVideo(Map<String, Object> param, PageBounds pageBounds);

	MessageResultOfListOfAppKeyValue getCategoryAppKeyValue();

	String getVideoUrls(String videoGuid);

	MessageResultOfListOfAppKeyValue getGradeAppKeyValue();

}
