package com.haoyu.aip.video.service.impl;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.AppKeyValue;
import org.tempuri.AppVideo;
import org.tempuri.MessageResultOfAppVideo;
import org.tempuri.MessageResultOfListOfAppKeyValue;
import org.tempuri.MessageResultOfListOfAppVideo;
import org.tempuri.MessageResultOfVideoResourcePath;
import org.tempuri.VideoResourcePath;
import org.tempuri.WebServiceSoapProxy;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.video.service.IVideoRecordService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Service
public class VideoRecordServiceImpl implements IVideoRecordService{
	
	@Autowired
	private WebServiceSoapProxy videoProxy;
	
	@Override
	public MessageResultOfListOfAppVideo getMessageResultOfListOfAppVideo(Map<String, Object> param, PageBounds pageBounds){
    	try {
    		List<AppKeyValue> appKeyValues = Lists.newArrayList();
    		for (String key : param.keySet()) {
    			AppKeyValue appKeyValue = new AppKeyValue();
    			appKeyValue.setKey(key);
    			appKeyValue.setValue((String)param.get(key));
    			appKeyValues.add(appKeyValue);
			}
    		AppKeyValue[] array = appKeyValues.toArray(new AppKeyValue[]{});
			return videoProxy.listVideo(array, pageBounds.getPage(), pageBounds.getLimit(), 0);
		} catch (RemoteException e) {
			MessageResultOfListOfAppVideo messageResultOfListOfAppVideo = new MessageResultOfListOfAppVideo();
			messageResultOfListOfAppVideo.setMessage("录播系统异常, 调用超时");
			messageResultOfListOfAppVideo.setData(new AppVideo[]{});
			return messageResultOfListOfAppVideo;
		}
	}
	
	@Override
	public MessageResultOfListOfAppKeyValue getCategoryAppKeyValue(){
		try {
			return videoProxy.listCategory();
		} catch (RemoteException e) {
			MessageResultOfListOfAppKeyValue messageResultOfListOfAppKeyValue = new MessageResultOfListOfAppKeyValue();
			messageResultOfListOfAppKeyValue.setMessage("录播系统异常, 调用超时");
			messageResultOfListOfAppKeyValue.setData(new AppKeyValue[]{});
			return messageResultOfListOfAppKeyValue;
		}
	}

	@Override
	public String getVideoUrls(String videoGuid) {
		try {
			MessageResultOfVideoResourcePath messageResultOfVideoResourcePath = videoProxy.videoResourcePath(videoGuid);
			VideoResourcePath videoResourcePath = messageResultOfVideoResourcePath.getData();
			MessageResultOfAppVideo messageResultOfAppVideo = videoProxy.video(videoGuid);
			String HD = videoResourcePath.getHD();
			String NR = videoResourcePath.getNR();
			Map<String, Object> param = Maps.newHashMap();
			param.put("name", messageResultOfAppVideo.getData().getName());
			param.put("fileName", messageResultOfAppVideo.getData().getFileName());
			String domain = PropertiesLoader.get("video.record.download.domain");
			if (StringUtils.isNotEmpty(HD)) {
				param.put("HD", StringUtils.substringAfterLast(HD, domain));
			}
			if (StringUtils.isNotEmpty(NR)) {
				param.put("NR", StringUtils.substringAfterLast(NR, domain));
			}
			return new JsonMapper().toJson(param);
		} catch (RemoteException e) {
			return null;
		}
	}

	@Override
	public MessageResultOfListOfAppKeyValue getGradeAppKeyValue() {
		try {
			return videoProxy.listGrade();
		} catch (RemoteException e) {
			MessageResultOfListOfAppKeyValue messageResultOfListOfAppKeyValue = new MessageResultOfListOfAppKeyValue();
			messageResultOfListOfAppKeyValue.setMessage("录播系统异常, 调用超时");
			messageResultOfListOfAppKeyValue.setData(new AppKeyValue[]{});
			return messageResultOfListOfAppKeyValue;
		}
	}
}
