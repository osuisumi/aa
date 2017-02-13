/**
 * 
 */
package com.haoyu.aip.qti.service.impl;

import java.net.URI;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haoyu.aip.qti.dao.ITestPackageDao;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.service.ITestPackageService;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TestPackageServiceImpl implements ITestPackageService {
	@Resource
	private ITestPackageDao testPackageDao;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestPackageService#findTestPackageById(java.lang.String)
	 */
	@Override
	public TestPackage findTestPackageById(String id) {
		return testPackageDao.selectTestPackageById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestPackageService#importTestPackage(org.springframework.web.multipart.MultipartFile, boolean)
	 */
	@Override
	public TestPackage importTestPackage(MultipartFile multipartFile,
			boolean validate) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestPackageService#createTestPackage()
	 */
	@Override
	public Response createTestPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestPackageService#createAssessmentFileUri(com.haoyu.aip.qti.entity.TestPackage, java.lang.String)
	 */
	@Override
	public URI createAssessmentFileUri(TestPackage testPackage, String fileHref) {
		// TODO Auto-generated method stub
		return null;
	}

}
