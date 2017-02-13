package com.haoyu.aip.activity.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;

public class ActivityResult extends BaseEntity{

	private static final long serialVersionUID = 8481382760578031367L;
	
	private ActivityRelation activityRelation;

    private String state;

    private BigDecimal score;

    public ActivityRelation getActivityRelation() {
		return activityRelation;
	}

	public void setActivityRelation(ActivityRelation activityRelation) {
		this.activityRelation = activityRelation;
	}

	public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public static String getId(String activityRelationId, String creator){
    	return DigestUtils.md5Hex(activityRelationId+creator);
    }
}