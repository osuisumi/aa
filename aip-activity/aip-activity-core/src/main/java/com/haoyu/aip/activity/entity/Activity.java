package com.haoyu.aip.activity.entity;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;

public class Activity extends BaseEntity{

	private static final long serialVersionUID = 254910140628115513L;
	
	private List<ActivityRelation> activityRelations = Lists.newArrayList();
	
	private String title;
    
    private String entityId;

    private String activityType;
    
    private String property;

    private BigDecimal score;

    private String state;

    private String configuration;
    
    private ActivityResult activityResult;
    
	public ActivityResult getActivityResult() {
		return activityResult;
	}

	public void setActivityResult(ActivityResult activityResult) {
		this.activityResult = activityResult;
	}

	public List<ActivityRelation> getActivityRelations() {
		return activityRelations;
	}

	public void setActivityRelations(List<ActivityRelation> activityRelations) {
		this.activityRelations = activityRelations;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }

	public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration == null ? null : configuration.trim();
    }

}