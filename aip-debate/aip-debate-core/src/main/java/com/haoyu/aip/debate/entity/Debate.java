package com.haoyu.aip.debate.entity;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.tag.entity.Tag;

/**
 * 辩论主题信息
 * @author lianghuahuang
 *
 */
public class Debate  extends BaseEntity{
	
	private static final long serialVersionUID = -570744647523903843L;
	

	private String id;
	
	/**
	 * 论题
	 */
    private String title;
    
    /**
     * 状态：存草稿、发布
     */
    private String state = "draft";
    
    /**
     * 标签
     */
    private List<Tag> tags = Lists.newArrayList();
    
    /**
     * 论点
     */
    private List<DebateArgument> arguments = Lists.newArrayList();
    
    /**
     * 论题补充说明
     */
    private String supplementExplanation;
   
    
    /**
     * 关联
     */
    private List<DebateRelation> debateRelations  =Lists.newArrayList();
    
    public Debate() {
	}
    
	public Debate(String id) {
		this.id = id;
	}

	public List<DebateRelation> getDebateRelations() {
		return debateRelations;
	}

	public void setDebateRelations(List<DebateRelation> debateRelations) {
		this.debateRelations = debateRelations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<DebateArgument> getArguments() {	
		return arguments;
	}

	public void setArguments(List<DebateArgument> arguments) {
		this.arguments = arguments;
	}

	public String getSupplementExplanation() {
		return supplementExplanation;
	}

	public void setSupplementExplanation(String supplementExplanation) {
		this.supplementExplanation = supplementExplanation;
	}

	
	
    
}