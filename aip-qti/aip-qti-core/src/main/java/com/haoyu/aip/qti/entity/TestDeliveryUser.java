/*************************************************
*TestUser.java
*
*2013-11-29
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.haoyu.aip.qti.entity;

import java.util.List;
import java.util.Map;




import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;



/**
 *
 *
 *@author 梁华璜
 */
public class TestDeliveryUser extends BaseEntity {

	private String id;
	
	private TestDelivery testDelivery;
	
	private String completionStatus;
	
	private long finishTime;
	
	private double sumScore;
	
	// 参与次数
	private int attempts;
	
	private User deliveryUser;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public TestDelivery getTestDelivery() {
		return testDelivery;
	}

	public void setTestDelivery(TestDelivery testDelivery) {
		this.testDelivery = testDelivery;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public double getSumScore() {
		return sumScore;
	}

	public void setSumScore(double sumScore) {
		this.sumScore = sumScore;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public User getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(User deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

}
