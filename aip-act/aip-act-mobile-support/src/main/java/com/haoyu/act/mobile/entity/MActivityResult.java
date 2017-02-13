package com.haoyu.act.mobile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.Maps;

public class MActivityResult implements Serializable{
	
	private static final long serialVersionUID = 6355634593848796948L;

	private String id;
	
	private MActivity mActivity;
	
	private BigDecimal score;
	
	private String state;
	
	private Map<String, Object> detailMap = Maps.newHashMap();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MActivity getmActivity() {
		return mActivity;
	}

	public void setmActivity(MActivity mActivity) {
		this.mActivity = mActivity;
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

	public Map<String, Object> getDetailMap() {
		return detailMap;
	}

	public void setDetailMap(Map<String, Object> detailMap) {
		this.detailMap = detailMap;
	}

}
