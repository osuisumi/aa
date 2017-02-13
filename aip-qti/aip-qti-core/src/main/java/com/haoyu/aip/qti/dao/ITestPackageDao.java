/**
 * 
 */
package com.haoyu.aip.qti.dao;

import com.haoyu.aip.qti.entity.TestPackage;

/**
 * @author lianghuahuang
 *
 */
public interface ITestPackageDao {
	TestPackage selectTestPackageById(String id);
	
	int insertTestPackage(TestPackage testPackage);
	
	
	int updateTestPackage(TestPackage testPackage);
}
