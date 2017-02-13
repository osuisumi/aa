/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.dao.IDebateArgumentDao;
import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.dao.IDebateDao;
import com.haoyu.aip.debate.dao.IDebateRelationDao;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.event.CreateDebateEvent;
import com.haoyu.aip.debate.event.DeleteDebateEvent;
import com.haoyu.aip.debate.service.IDebateArgumentService;
import com.haoyu.aip.debate.service.IDebateService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateServiceImpl implements IDebateService {
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private IDebateDao debateDao;
	@Autowired
	private IDebateRelationDao debateRelationDao;
	@Autowired
	private IDebateArgumentDao debateArgumentDao;
	@Autowired
	private IDebateArgumentStatDao debateArgumentStatDao;
	@Autowired
	private IDebateArgumentService debateArgumentService;
	@Autowired
	private ITagRelationService tagRelationService;
	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#createDebate(com.haoyu.tip.debate.entity.Debate)
	 */
	@Override
	public Response createDebate(Debate debate) {
		if(StringUtils.isEmpty(debate.getId())){
			debate.setId(Identities.uuid2());
		}
		int count= debateDao.insertDebate(debate);
		//如果debateRelation不存在，创建debateRelation
		if(debate.getDebateRelations().isEmpty()){
			DebateRelation dr = new DebateRelation();
			dr.setDebate(debate);		
			debate.getDebateRelations().add(dr);
		}
		//插入debateRelation
		for(DebateRelation dr:debate.getDebateRelations()){
			dr.setId(DebateRelation.getId(debate.getId(),dr.getRelation()!=null?Objects.toString(dr.getRelation().getId()):""));
			dr.setDebate(debate);
			debateRelationDao.insertDebateRelation(dr);
			dr.setDebate(null);
		}
		//插入debateArgument
		if(debate.getArguments()!=null&&!debate.getArguments().isEmpty()){
			for(DebateArgument da:debate.getArguments()){
				da.setDebate(debate);
				debateArgumentService.createDebateArgument(da);
				da.setDebate(null);
			}
		}
		//插入标签信息
		if(debate.getTags()!=null&&!debate.getTags().isEmpty()){
			//添加标签信息
			tagRelationService.createTagRelation(debate.getTags(), new Relation(debate.getId(),"debate"), true);
		}
		applicationContext.publishEvent(new CreateDebateEvent(debate));
		return count>0?Response.successInstance().responseData(debate):Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#updateDebate(com.haoyu.tip.debate.entity.Debate)
	 */
	@Override
	public Response updateDebate(Debate debate) {
		if(debate==null||StringUtils.isEmpty(debate.getId())){
			return Response.failInstance().responseMsg("debate is null or id is null");
		}
		int count = debateDao.updateDebate(debate);
		//更新论点信息
		if(debate.getArguments()!=null&&!debate.getArguments().isEmpty()){
			List<DebateArgument> daSet = debate.getArguments();
			//先获取修改前的论点信息
			List<DebateArgument> daList = debateArgumentDao.selectDebateArgumentByDebate(debate);
			for(DebateArgument da:daSet){
				if(StringUtils.isEmpty(da.getId())){
					da.setId(Identities.uuid2());
				}
				if(daList.contains(da)){
					debateArgumentDao.updateDebateArgument(da);
				}else{
					Debate deb = new Debate(debate.getId());
					deb.setDebateRelations(debate.getDebateRelations());
					da.setDebate(deb);
					debateArgumentService.createDebateArgument(da);
					da.setDebate(null);
				}
				daList.remove(da);
			}
			if(daList!=null&&!daList.isEmpty()){
				debateArgumentDao.deleteDebateArgumentByLogic(daList,  debate.getUpdatedby()!=null?debate.getUpdatedby().getId():null);
				debateArgumentStatDao.deleteByLogic(daList, debate.getUpdatedby()!=null?debate.getUpdatedby().getId():null);
			}
			
		}
		if(debate.getTags()!=null&&!debate.getTags().isEmpty()){
			//添加标签信息
			tagRelationService.createTagRelation(debate.getTags(), new Relation(debate.getId(),"debate"),  true);
		}else{
			tagRelationService.deleteTagRelationByRelation(new Relation(debate.getId()));
		}
		return count>0?Response.successInstance().responseData(debate):Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#deleteDebate(Debate)
	 */
	@Override
	public Response deleteDebate(Debate debate) {
		if(debate==null||StringUtils.isEmpty(debate.getId())){
			return Response.failInstance().responseMsg("debate is null or id is null");
		}
		int count = debateDao.deleteDebateByLogic(debate);
		if (count > 0) {
			applicationContext.publishEvent(new DeleteDebateEvent(debate));
		}
		return count>0?Response.successInstance():Response.failInstance();
	} 

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#addTagToDebate(com.haoyu.tip.debate.entity.Tag, java.lang.String)
	 */
	@Override
	public Response addTagToDebate(Tag tag, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#removeTagFromDebate(com.haoyu.tip.debate.entity.Tag, java.lang.String)
	 */
	@Override
	public Response removeTagFromDebate(Tag tag, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#findDebateById(java.lang.String)
	 */
	@Override
	public Debate findDebateById(String id) {
		Debate debate =  debateDao.selectDebateById(id);
		if(debate!=null&&debate.getArguments()!=null){
			Collections.sort(debate.getArguments());
		}
		return debate;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.service.IDebateService#findDebateByTagsAndRelations(java.util.Set, java.util.Set, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Debate> findDebateByTagsAndRelations(Set<Tag> tags,
			Set<Relation> relations, PageBounds pageBounds) {
		return debateDao.selectDebateByTagAndRelation(tags, relations, pageBounds);
	}

}
