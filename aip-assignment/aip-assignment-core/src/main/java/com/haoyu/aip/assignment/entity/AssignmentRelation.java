package com.haoyu.aip.assignment.entity;

import java.text.ParseException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.core.utils.PropertiesLoader;

public class AssignmentRelation extends BaseEntity{
	
	private static final long serialVersionUID = 6764397434333930132L;

	private String id;
	
	private Relation relation;
	
	private Assignment assignment;
	
	private TimePeriod responseTime;
	
	private TimePeriod markTime;
	
	private int responseNum;
	
	private int markNum;	
	
	//以下非数据库字段
	private String responseStartTime;
	
	private String responseEndTime;
	
	private String markStartTime;
	
	private String markEndTime;

	public String getResponseStartTime() {
		return responseStartTime;
	}

	public void setResponseStartTime(String responseStartTime) {
		if (responseStartTime != null) {
			if (getResponseTime() == null) {
				setResponseTime(new TimePeriod());
			}
			if (StringUtils.isNotEmpty(responseStartTime)) {
				try {
					getResponseTime().setStartTime(DateUtils.parseDate(responseStartTime, PropertiesLoader.get("assignment.response.timePeriod.pattern")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				getResponseTime().setStartTime(null);
			}
		}
		this.responseStartTime = responseStartTime;
	}

	public String getResponseEndTime() {
		return responseEndTime;
	}

	public void setResponseEndTime(String responseEndTime) {
		if (responseEndTime != null) {
			if (getResponseTime() == null) {
				setResponseTime(new TimePeriod());
			}
			if (StringUtils.isNotEmpty(responseEndTime)) {
				try {
					getResponseTime().setEndTime(DateUtils.parseDate(responseEndTime, PropertiesLoader.get("assignment.response.timePeriod.pattern")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				getResponseTime().setEndTime(null);
			}
		}
		this.responseEndTime = responseEndTime;
	}

	public String getMarkStartTime() {
		return markStartTime;
	}

	public void setMarkStartTime(String markStartTime) {
		if (markStartTime != null) {
			if (getMarkTime() == null) {
				setMarkTime(new TimePeriod());
			}
			if (StringUtils.isNotEmpty(markStartTime)) {
				try {
					getMarkTime().setStartTime(DateUtils.parseDate(markStartTime, PropertiesLoader.get("assignment.mark.timePeriod.pattern")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				getMarkTime().setStartTime(null);
			}
		}
		this.markStartTime = markStartTime;
	}

	public String getMarkEndTime() {
		return markEndTime;
	}

	public void setMarkEndTime(String markEndTime) {
		if (markEndTime != null) {
			if (getMarkTime() == null) {
				setMarkTime(new TimePeriod());
			}
			if (StringUtils.isNotEmpty(markEndTime)) {
				try {
					getMarkTime().setEndTime(DateUtils.parseDate(markEndTime, PropertiesLoader.get("assignment.mark.timePeriod.pattern")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				getMarkTime().setEndTime(null);
			}
		}
		this.markEndTime = markEndTime;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public TimePeriod getMarkTime() {
		return markTime;
	}

	public void setMarkTime(TimePeriod markTime) {
		this.markTime = markTime;
	}

	public int getMarkNum() {
		return markNum;
	}

	public void setMarkNum(int markNum) {
		this.markNum = markNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TimePeriod getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(TimePeriod responseTime) {
		this.responseTime = responseTime;
	}

	public int getResponseNum() {
		return responseNum;
	}

	public void setResponseNum(int responseNum) {
		this.responseNum = responseNum;
	}

	public static String getId(String assignmentId,String relationId){
		return DigestUtils.md5Hex(assignmentId+relationId);
	}

}
