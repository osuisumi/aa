package com.haoyu.aip.courseware.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.courseware.dao.ICoursewareDao;
import com.haoyu.aip.courseware.entity.Courseware;
import com.haoyu.aip.courseware.entity.CoursewareRelation;
import com.haoyu.aip.courseware.event.CreateCoursewareEvent;
import com.haoyu.aip.courseware.event.DeleteCoursewareEvent;
import com.haoyu.aip.courseware.event.UpdateCoursewareEvent;
import com.haoyu.aip.courseware.service.ICoursewareRelationService;
import com.haoyu.aip.courseware.service.ICoursewareService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class CoursewareService implements ICoursewareService{
	@Resource
	private ICoursewareDao listenCourseDao;
	@Resource
	private ICoursewareRelationService listenCourseRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private IFileService fileService;

	@Override
	public Response create(Courseware listenCourse) {
		String id = StringUtils.isEmpty(listenCourse.getId())?Identities.uuid2():listenCourse.getId();
		listenCourse.setId(id);
		if(listenCourse.getVideo()!=null){
			fileService.createFile(listenCourse.getVideo(), listenCourse.getId(), "coursewareVideo");
		}
		Response responseSaveLC  = BaseServiceUtils.create(listenCourse, (MybatisDao) this.listenCourseDao);
		Response responseSaveLCR = null;
		if(responseSaveLC.isSuccess()){
			for(CoursewareRelation lr:listenCourse.getCoursewareRelations()){
				lr.setCoursewareId(id);
				lr.setId(DigestUtils.md5Hex(id+lr.getRelation().getId()));
				responseSaveLCR = listenCourseRelationService.create(lr);
			}
		}
		
		if(responseSaveLCR!=null && responseSaveLCR.isSuccess()){
			applicationContext.publishEvent(new CreateCoursewareEvent(listenCourse));
		}
		return responseSaveLCR;
	}

	@Override
	public Response update(Courseware listenCourse) {
		//如果重新上传了视频，删除之前的视频
		if(listenCourse.getVideo()!=null){
			Courseware old = this.get(listenCourse.getId());
			if (StringUtils.isNotEmpty(old.getVideoJson())) {
				FileInfo fileInfo = old.getVideo();
				fileInfo.setUrl("");
				fileService.deleteFileFromServer(fileInfo);
				fileService.createFile(listenCourse.getVideo(), listenCourse.getId(), "coursewareVideo");
			}
		}
		Response response =  BaseServiceUtils.update(listenCourse, (MybatisDao)this.listenCourseDao);
		if(response.isSuccess()){
			if (Collections3.isNotEmpty(listenCourse.getCoursewareRelations())){
				for(CoursewareRelation cr:listenCourse.getCoursewareRelations()){
					if(StringUtils.isNotEmpty(cr.getId())){
						listenCourseRelationService.update(cr);
					}
				}
			}
			applicationContext.publishEvent(new UpdateCoursewareEvent(listenCourse));
		}
		return response;
	}

	@Override
	public Response deleteByLogic(Courseware courseware) {
		courseware = get(courseware.getId());
		int count = ((MybatisDao)this.listenCourseDao).update("deleteByLogic", courseware);
		if(count>0){
			applicationContext.publishEvent(new DeleteCoursewareEvent(courseware));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Courseware get(String id) {
		return (Courseware) BaseServiceUtils.get(id,(MybatisDao)this.listenCourseDao);
	}

	@Override
	public List<Courseware> list(Courseware listenCourse,PageBounds pageBounds) {
		return ((MybatisDao) this.listenCourseDao).selectList("select", listenCourse, pageBounds);
	}
	
}
