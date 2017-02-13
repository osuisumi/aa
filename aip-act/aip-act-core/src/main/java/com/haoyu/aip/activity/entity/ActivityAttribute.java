package com.haoyu.aip.activity.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class ActivityAttribute extends BaseEntity{
	
	private static final long serialVersionUID = -59955448053246882L;

	private String id;
	
	private Activity activity;
	
	private String attrName;
	
	private String attrValue;
	
	private String attrType;

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

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	
	public static String getId(String activityId, String attrName){
		return DigestUtils.md5Hex(activityId+attrName);
	}

}
