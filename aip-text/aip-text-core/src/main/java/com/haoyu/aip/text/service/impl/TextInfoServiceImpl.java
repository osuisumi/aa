package com.haoyu.aip.text.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.text.dao.ITextInfoDao;
import com.haoyu.aip.text.entity.TextInfo;
import com.haoyu.aip.text.entity.TextInfoFile;
import com.haoyu.aip.text.entity.TextInfoRelation;
import com.haoyu.aip.text.service.ITextInfoFileService;
import com.haoyu.aip.text.service.ITextInfoRelationService;
import com.haoyu.aip.text.service.ITextInfoService;
import com.haoyu.aip.text.service.ITextInfoUserService;
import com.haoyu.aip.text.utils.TextInfoType;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileInfoService;
import com.haoyu.sip.file.service.IFileRelationService;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.pdf.exception.NotSupportWpsException;
import com.haoyu.sip.pdf.utils.ConverterUtils;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class TextInfoServiceImpl implements ITextInfoService {

	@Resource
	private ITextInfoDao textInfoDao;
	@Resource
	private ITextInfoRelationService textInfoRelationService;
	@Resource
	private ITextInfoUserService textInfoUserService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IFileService fileService;
	@Resource
	private IFileInfoService fileInfoService;
	@Resource
	private ITextInfoFileService textInfoFileService;
	@Resource
	private IFileRelationService fileRelationService;

	@Override
	public Response createTextInfo(TextInfo entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		if (TextInfoType.FILE.equals(entity.getType()) && Collections3.isNotEmpty(entity.getFileInfos())) {
			Map<String, Object> param = Maps.newHashMap();
			Response response = fileService.createFileList(entity.getFileInfos(), entity.getId(), "textInfo");
			if (response.isSuccess()) {
				FileInfo fileInfo = entity.getFileInfos().get(0);
				param.put("orig_id", fileInfo.getId());
				param.put("orig_url", fileInfo.getUrl());

				response = convertFileToPdf(fileInfo);
				if (!response.isSuccess()) {
					return response;
				}
				String path = (String) response.getResponseData();
				param.put("pdf_url", path);
				entity.setContent(new JsonMapper().toJson(param));
			}
		} else if (TextInfoType.PHOTO.equals(entity.getType()) && Collections3.isNotEmpty(entity.getTextInfoFiles())) {
			createTextInfoFiles(entity);
		}
		entity.setDefaultValue();
		int count = textInfoDao.insert(entity);
		if (count > 0) {
			if (Collections3.isNotEmpty(entity.getTextInfoRelations())) {
				for (TextInfoRelation textInfoRelation : entity.getTextInfoRelations()) {
					if (textInfoRelation.getRelation() != null && StringUtils.isNotEmpty(textInfoRelation.getRelation().getId())) {
						if (textInfoRelation.getTextInfo() == null || StringUtils.isEmpty(textInfoRelation.getTextInfo().getId())) {
							textInfoRelation.setTextInfo(entity);
						}
						String id = TextInfoRelation.getId(textInfoRelation.getTextInfo().getId(), textInfoRelation.getRelation().getId());
						textInfoRelation.setId(id);
						textInfoRelationService.createTextInfoRelation(textInfoRelation);
						textInfoRelation.setTextInfo(null);
					}
				}
			}
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	private void createTextInfoFiles(TextInfo entity) {
		for (TextInfoFile textInfoFile : entity.getTextInfoFiles()) {
			textInfoFile.setId(Identities.uuid2());
			Response response = fileService.createFileList(Lists.newArrayList(textInfoFile.getFileInfo()), textInfoFile.getId(), "textInfoFile");
			if (response.isSuccess()) {
				textInfoFile.setTextInfo(entity);
				textInfoFile.setUrl(textInfoFile.getFileInfo().getUrl());
				unzip(textInfoFile);
				textInfoFileService.createTextInfoFile(textInfoFile);
			}
		}
	}
	
	private void updateTextInfoFiles(TextInfo entity) {
		for (TextInfoFile textInfoFile : entity.getTextInfoFiles()) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("relationId", textInfoFile.getId());
			List<FileInfo> oldFiles = fileService.listFileInfoByRelationId(textInfoFile.getId());
			Response response = fileService.updateFileList(Lists.newArrayList(textInfoFile.getFileInfo()), oldFiles, textInfoFile.getId(), "textInfoFile");
			if (response.isSuccess()) {
				textInfoFile.setUrl(textInfoFile.getFileInfo().getUrl());
				if (Collections3.isNotEmpty(oldFiles) && !oldFiles.get(0).getId().equals(textInfoFile.getFileInfo().getId())) {
					unzip(textInfoFile);
				}
				textInfoFileService.updateTextInfoFile(textInfoFile);
			}
		}
	}

	private void unzip(TextInfoFile textInfoFile) {
		File desc = new File(PropertiesLoader.get("file.remote.dir") + "/text_info_file/");
		File zipFile = new File(PropertiesLoader.get("file.remote.dir")+"/"+textInfoFile.getUrl());
		try {
			ZipFile zip = new ZipFile(zipFile);
			textInfoFile.setFileNum(BigDecimal.valueOf(zip.size() - 1));
			int index = 0;
			for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				String fileName = StringUtils.substringAfterLast(zipEntryName, "/");
				InputStream in = zip.getInputStream(entry);
				String outPath = "";
				File file = null;
				if (index == 0) {
					outPath = (desc + "/" + textInfoFile.getId()).replaceAll("\\*", "/");
					file = new File(outPath);
					FileUtils.deleteDirectory(file);
					// 判断路径是否存在,不存在则创建文件路径
					if (!file.exists()) {
						file.mkdirs();
					}
					index++;
					continue;
				}else{
					outPath = (desc + "/" + textInfoFile.getId() + "/" + fileName).replaceAll("\\*", "/");
					file = new File(outPath);
					index++;
				}
				// 输出文件路径信息
				OutputStream out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Response updateTextInfo(TextInfo entity) {
		if (TextInfoType.FILE.equals(entity.getType()) && Collections3.isNotEmpty(entity.getFileInfos())) {
			Map<String, Object> param = Maps.newHashMap();
			FileInfo fileInfo = entity.getFileInfos().get(0);
			if (StringUtils.isNotEmpty(fileInfo.getUrl())) {
				List<FileInfo> oldList = fileService.listFileInfoByRelationId(entity.getId());
				//Response response = fileService.updateFileList(entity.getFileInfos(), oldFileInfos, entity.getId(), "textInfo");
				List<FileInfo> newList = entity.getFileInfos();
				if (Collections3.isNotEmpty(newList) || Collections3.isNotEmpty(oldList)) {
					List<FileInfo> addList = Collections3.subtract(newList, oldList);
					List<FileInfo> deleteList = Collections3.subtract(oldList, newList);
					if (Collections3.isNotEmpty(deleteList)) {
						for (FileInfo f: deleteList) {
							fileRelationService.deleteFileRelation(f.getId(), entity.getId());
						}
					}
					fileService.createFileList(addList, entity.getId(), "textInfo");
					if (Collections3.isNotEmpty(addList)) {
						param.put("orig_id", fileInfo.getId());
						param.put("orig_url", fileInfo.getUrl());
					 	Response response = convertFileToPdf(fileInfo);
						if (!response.isSuccess()) {
							return response;
						}
						String path = (String) response.getResponseData();
						param.put("pdf_url", path);
						entity.setContent(new JsonMapper().toJson(param));
					}
				}
			}
		}else if (TextInfoType.PHOTO.equals(entity.getType()) && Collections3.isNotEmpty(entity.getTextInfoFiles())) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("textInfoId", entity.getId());
			List<TextInfoFile> oldList = textInfoFileService.listTextInfoFile(param, null);
			if (Collections3.isNotEmpty(entity.getTextInfoFiles()) || Collections3.isNotEmpty(oldList)) {
				List<TextInfoFile> addList = Collections3.subtract(entity.getTextInfoFiles(), oldList);
				List<TextInfoFile> updateList = Collections3.intersection(entity.getTextInfoFiles(), oldList);
				List<TextInfoFile> deleteList = Collections3.subtract(oldList, entity.getTextInfoFiles());
				
				entity.setTextInfoFiles(addList);
				this.createTextInfoFiles(entity);
				entity.setTextInfoFiles(updateList);
				this.updateTextInfoFiles(entity);
				if (Collections3.isNotEmpty(deleteList)) {
					List<String> ids = Collections3.extractToList(deleteList, "id");
					textInfoFileService.deleteTextInfoFileByIds(ids);
				}
			}
		}
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = textInfoDao.update(entity);
		if (count > 0) {
			if (Collections3.isNotEmpty(entity.getTextInfoRelations())) {
				for (TextInfoRelation textInfoRelation : entity.getTextInfoRelations()) {
					if (StringUtils.isNotEmpty(textInfoRelation.getId())) {
						textInfoRelationService.updateTextInfoRelation(textInfoRelation);
					}
				}
			}
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@SuppressWarnings("finally")
	private Response convertFileToPdf(FileInfo fileInfo) {
		Response response = null;
		try {
			String suffix = StringUtils.substringAfterLast(fileInfo.getUrl(), ".");
			if (suffix.equals("pdf")) {
				response = Response.successInstance().responseData(fileInfo.getUrl());
			} else {
				String origUrl = PropertiesLoader.get("file.remote.dir") + fileInfo.getUrl();
				String targUrl = PropertiesLoader.get("file.remote.dir") + StringUtils.replace(fileInfo.getUrl(), "." + suffix, ".pdf");
				if (suffix.equals("doc") || suffix.equals("docx")) {
					response = ConverterUtils.convertFromDoc(origUrl, targUrl);
				} else if (suffix.equals("ppt") || suffix.equals("pptx")) {
					response = ConverterUtils.convertFromPpt(origUrl, targUrl);
				}
				if (response.isSuccess()) {
					response.setResponseData(StringUtils.replace(fileInfo.getUrl(), "." + suffix, ".pdf"));
				}
			}
			return response;
		} catch (NotSupportWpsException e) {
			response = Response.failInstance().responseMsg("file made by wps is not supported");
			throw new RuntimeException();
		} finally {
			return response;
		}
	}

	@Override
	public Response deleteTextInfoByLogic(TextInfo entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = textInfoDao.deleteByLogic(entity);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public TextInfo getTextInfo(String id) {
		return textInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public List<TextInfo> listTextInfo(Map<String, Object> params, PageBounds pageBounds) {
		return textInfoDao.select(params, pageBounds);
	}

	@Override
	public TextInfo viewTextInfo(String textInfoId, String relationId) {
		TextInfo textInfo = this.getTextInfo(textInfoId);
		if (textInfo != null) {
			String textInfoRelationId = TextInfoRelation.getId(textInfoId, relationId);
			TextInfoRelation textInfoRelation = new TextInfoRelation();
			textInfoRelation.setId(textInfoRelationId);
			textInfoRelation.setBrowseNum(1);
			textInfoRelationService.updateTextInfoRelation(textInfoRelation);

			// String textInfoUserId = TextInfoUser.getId(textInfoRelationId, ThreadContext.getUser().getId());
			// TextInfoUser textInfoUser = new TextInfoUser();
			// textInfoUser.setId(textInfoUserId);
			// textInfoUser.setViewNum(1);
			// textInfoUserService.update(textInfoUser);
			//
			// applicationContext.publishEvent(new ViewTextInfoEvent(textInfoUser));
		}
		return textInfo;
	}

}
