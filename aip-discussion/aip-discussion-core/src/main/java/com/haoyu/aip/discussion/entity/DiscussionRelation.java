package com.haoyu.aip.discussion.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class DiscussionRelation extends BaseEntity{
	
	private static final long serialVersionUID = 4098778437438309538L;
	
	private Relation relation;
	
	private Discussion discussion;
	
	private TimePeriod timePeriod = new TimePeriod();
		
	private int participateNum;
	
	private int browseNum;
	
	private int followNum;
	
	private int collectNum;
	
	private int replyNum;
	
	private String isTop = "N";
	
	private String isEssence = "N";
	
	private DiscussionPost lastPost;
	
	private int supportNum;
	
	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(String isEssence) {
		this.isEssence = isEssence;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public DiscussionPost getLastPost() {
		return lastPost;
	}

	public void setLastPost(DiscussionPost lastPost) {
		this.lastPost = lastPost;
	}
	
	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public static String getId(String discussionId,String relationId){
		return DigestUtils.md5Hex(discussionId+relationId);
	}

}
