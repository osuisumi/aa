/**
 * 
 */
package com.haoyu.aip.qti.entity;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author lianghuahuang
 *
 */
public class OptionScoreMapping {
	
	private double lowerBound=0;
	
	private double upperBound=0;
	
	private double defaultValue=0;
	
	private Map<String,Double> mapEntries = Maps.newHashMap();
	
	public boolean isEmpty(){
		return mapEntries.isEmpty();
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public double getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(double defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Map<String, Double> getMapEntries() {
		return mapEntries;
	}

	public void setMapEntries(Map<String, Double> mapEntries) {
		this.mapEntries = mapEntries;
	}
	
	
}
