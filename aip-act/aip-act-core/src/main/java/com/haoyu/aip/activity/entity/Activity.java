package com.haoyu.aip.activity.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.utils.Identities;

public class Activity extends BaseEntity{
	
	private static final long serialVersionUID = 6438945764903812574L;

	private String id;
	
	private Relation relation;
	
	private String title;
	
	private TimePeriod timePeriod;
	
	private String entityId;
	
	private BigDecimal score;

    private String state;
    
    private String type;
    
    private BigDecimal sortNo;
    
	//以下为非数据库字段
	private Map<String, ActivityAttribute> attributeMap = Maps.newHashMap();
	
	private List<Tag> tags = Lists.newArrayList();
	
	private String startTime;
	
	private String endTime;
	
	public static String generateId(){
		return "a_"+Identities.uuid2();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Map<String, ActivityAttribute> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, ActivityAttribute> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TimePeriod getTimePeriod() {
		if (timePeriod == null) {
			if (startTime != null || endTime != null) {
				String pattern = "yyyy-MM-dd HH:mm:ss";
				timePeriod = new TimePeriod();
				try {
					if (StringUtils.isNotEmpty(startTime)) {
						timePeriod.setStartTime(DateUtils.parseDate(startTime, pattern));
					}
					if (StringUtils.isNotEmpty(endTime)) {
						timePeriod.setEndTime(DateUtils.parseDate(endTime, pattern));
					}
				} catch (ParseException e) {
					try {
						if (StringUtils.isNotEmpty(startTime)) {
							String[] array = startTime.split(" ");
							if (array.length == 1) {
								startTime += " 00:00:00";
							}
							timePeriod.setStartTime(DateUtils.parseDate(startTime, pattern));
						}
						if (StringUtils.isNotEmpty(endTime)) {
							String[] array = endTime.split(" ");
							if (array.length == 1) {
								endTime += " 23:59:59";
							}
							timePeriod.setEndTime(DateUtils.parseDate(endTime, pattern));
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		this.setSortNo(BigDecimal.valueOf(9999));
	}
	
}
