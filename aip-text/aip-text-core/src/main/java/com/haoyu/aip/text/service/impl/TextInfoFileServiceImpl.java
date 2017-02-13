package com.haoyu.aip.text.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.text.dao.ITextInfoFileDao;
import com.haoyu.aip.text.entity.TextInfoFile;
import com.haoyu.aip.text.service.ITextInfoFileService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class TextInfoFileServiceImpl implements ITextInfoFileService{
	
	@Resource
	private ITextInfoFileDao textInfoFileDao;

	@Override
	public Response createTextInfoFile(TextInfoFile entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = textInfoFileDao.insert(entity);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public List<TextInfoFile> listTextInfoFile(Map<String, Object> parameter, PageBounds pageBounds) {
		return textInfoFileDao.select(parameter, pageBounds);
	}

	@Override
	public Response updateTextInfoFile(TextInfoFile entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = textInfoFileDao.update(entity);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response deleteTextInfoFileByIds(List<String> ids) {
		TextInfoFile textInfoFile = new TextInfoFile();
		textInfoFile.setUpdatedby(ThreadContext.getUser());
		textInfoFile.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> map = Maps.newHashMap();
		map.put("entity", textInfoFile);
		map.put("ids", ids);
		int count = textInfoFileDao.deleteByIds(map);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public TextInfoFile getTextInfoFile(String id) {
		return textInfoFileDao.selectById(id);
	}

	@Override
	public Response copyPhotos(String oldId, String newId) {
		String path = PropertiesLoader.get("file.remote.dir") + "/text_info_file/";
		File oldDesc = new File(path + "/" + oldId);
		if (oldDesc.exists() && oldDesc.isDirectory()) {
			File destDir = new File(path + "/" + newId);
			try {
				FileUtils.copyDirectory(oldDesc, destDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Response.successInstance();
		}
		return Response.failInstance();
	}

}
