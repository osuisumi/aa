package com.haoyu.aip.activity.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class ActivityRelation extends BaseEntity{

	private static final long serialVersionUID = -2948389496422851798L;
	
	private Relation relation;
	
	private Activity activity;

	private TimePeriod timePeriod = new TimePeriod();
		
	/**
	 * 参与数
	 */
	private int participateNum;
	
	/**
	 * 浏览数
	 */
	private int browseNum;
	
	/**
	 * 关注数
	 */
	private int followNum;
	
	/**
	 * 收藏数
	 */
	private int collectNum;
	
	/**
	 * 回复数
	 */
	private int replyNum;
	
	/**
	 * 最后发表观点的时间
	 */
	private long lastReplyTime;
	
	private String isTop;

    private String isEssence;
    
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

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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
	
	public long getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(long lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public static String getId(String activityId, String relationId){
		return DigestUtils.md5Hex(activityId+relationId);
	}

}