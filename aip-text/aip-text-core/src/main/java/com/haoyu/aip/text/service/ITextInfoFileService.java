package com.haoyu.aip.text.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.entity.TextInfoFile;
import com.haoyu.sip.core.service.Response;

public interface ITextInfoFileService {

	Response createTextInfoFile(TextInfoFile textInfoFile);

	List<TextInfoFile> listTextInfoFile(Map<String, Object> parameter, PageBounds pageBounds);

	Response updateTextInfoFile(TextInfoFile textInfoFile);

	Response deleteTextInfoFileByIds(List<String> ids);

	TextInfoFile getTextInfoFile(String id);

	Response copyPhotos(String oldId, String newId);

}
