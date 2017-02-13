package com.haoyu.aip.activity.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.mapper.JsonMapper;

public class ActivityResult extends BaseEntity{
	
	private static final long serialVersionUID = 910603661211182765L;

	private String id;
	
	private Activity activity;
	
	private Relation relation;
	
	private BigDecimal score;
	
	private String state;
	
	private String detail;
	
	//以下非数据库字段
	private Map<String, Object> detailMap = Maps.newHashMap();

	public Map<String, Object> getDetailMap() {
		if (!detailMap.isEmpty()) {
			return detailMap;
		}
		if (StringUtils.isNotEmpty(detail)) {
			detailMap = new JsonMapper().fromJson(detail, HashMap.class);
		}
		return detailMap;
	}

	public void setDetailMap(Map<String, Object> detailMap) {
		this.detailMap = detailMap;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public static String getId(String activityId, String relationId, String userId){
		return DigestUtils.md5Hex(activityId+relationId+userId);
	}
}
