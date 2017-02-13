package com.haoyu.aip.text.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.entity.TextInfo;
import com.haoyu.sip.core.service.Response;

public interface ITextInfoService {
	
	List<TextInfo> listTextInfo(Map<String, Object> params, PageBounds pageBounds);

	Response createTextInfo(TextInfo textInfo);

	Response updateTextInfo(TextInfo textInfo);

	Response deleteTextInfoByLogic(TextInfo textInfo);

	TextInfo getTextInfo(String id);

	TextInfo viewTextInfo(String textInfoId, String relationId);

}
