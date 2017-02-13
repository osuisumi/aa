/**
 * 
 */
package com.haoyu.aip.qti.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class TestDeliveryUserOutcome extends BaseEntity{
	private String id;
	
	private String baseType;
	
	private String cardinality;
	
	private String outcomeIdentifier;
	
	private String stringValue;
	
	private TestDeliveryUser testDeliveryUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public String getOutcomeIdentifier() {
		return outcomeIdentifier;
	}

	public void setOutcomeIdentifier(String outcomeIdentifier) {
		this.outcomeIdentifier = outcomeIdentifier;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public TestDeliveryUser getTestDeliveryUser() {
		return testDeliveryUser;
	}

	public void setTestDeliveryUser(TestDeliveryUser testDeliveryUser) {
		this.testDeliveryUser = testDeliveryUser;
	}
}
