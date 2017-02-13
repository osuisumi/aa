/**
 * 
 */
package com.haoyu.aip.debate.entity;



import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

/**
 * @author Administrator
 *
 */
public class DebateRelation extends BaseEntity {
	
	private String id;
	
	private Relation relation;
	
	private Debate debate;
	
	private TimePeriod timePeriod = new TimePeriod()  ;
	
	/**
	 * 观点数
	 */
	private int viewsNum;
	
	/**
	 * 评论数
	 */
	private int commentsNum;
		
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
	 * 最后发表观点的时间
	 */
	private long lastPublishViewsTime;
	
	public DebateRelation(){}

	public DebateRelation(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Debate getDebate() {
		return debate;
	}

	public void setDebate(Debate debate) {
		this.debate = debate;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int getViewsNum() {
		return viewsNum;
	}

	public void setViewsNum(int viewsNum) {
		this.viewsNum = viewsNum;
	}

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
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

	
	
	public long getLastPublishViewsTime() {
		return lastPublishViewsTime;
	}

	public void setLastPublishViewsTime(long lastPublishViewsTime) {
		this.lastPublishViewsTime = lastPublishViewsTime;
	}

	public static String getId(String debateId,String relationId){
		return DigestUtils.md5Hex(debateId+relationId);
	}
	
	
	//TODO 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DebateRelation other = (DebateRelation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
