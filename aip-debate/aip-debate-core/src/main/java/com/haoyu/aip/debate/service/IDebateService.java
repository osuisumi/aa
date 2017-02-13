/**
 * 
 */
package com.haoyu.aip.debate.service;

import java.util.List;
import java.util.Set;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.entity.Tag;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateService {
	
	/**
	 * 创建辩论
	 * @param debate
	 */
	Response createDebate(Debate debate);
	
	/**
	 * 更新辩论
	 * @param debate
	 * @return
	 */
	Response updateDebate(Debate debate);
	
	/**
	 * 删除辩论
	 * @param debate
	 * @return
	 */
	Response deleteDebate(Debate debate);
	
	/**
	 * 给辩论添加标签
	 * @param tag
	 * @param id
	 * @return
	 */
	Response addTagToDebate(Tag tag,String id);
	
	
	/**
	 * 从辩论移除标签
	 * @param tag
	 * @param id
	 * @return
	 */
	Response removeTagFromDebate(Tag tag,String id);
	
	/**
	 * 按照id查找辩论
	 * @param id 
	 * @return
	 */
	Debate findDebateById(String id);
	
	/**
	 * 按照标签查找辩论
	 * @param tags
	 * @param relations
	 * @param pageBounds
	 * @return
	 */
	List<Debate> findDebateByTagsAndRelations(Set<Tag> tags,Set<Relation> relations,PageBounds pageBounds);
	
}
