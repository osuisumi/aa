package com.haoyu.aip.text.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class TextInfoRelation extends BaseEntity{
	
	private static final long serialVersionUID = 9095459465735125111L;

	private String id;
	
	private TextInfo textInfo;
	
	private Relation relation;
	
	private int browseNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TextInfo getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(TextInfo textInfo) {
		this.textInfo = textInfo;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}
	
	public static String getId(String textInfoId, String relationId){
		return DigestUtils.md5Hex(textInfoId+relationId);
	}
	
}
