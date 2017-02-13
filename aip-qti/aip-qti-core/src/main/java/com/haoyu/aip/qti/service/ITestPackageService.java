package com.haoyu.aip.qti.service;

import java.net.URI;

import org.springframework.web.multipart.MultipartFile;

import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.sip.core.service.Response;

public interface ITestPackageService {
	
	TestPackage findTestPackageById(String id);
	
	TestPackage importTestPackage(final MultipartFile multipartFile, final boolean validate);
	
	Response createTestPackage();
	
	URI createAssessmentFileUri(final TestPackage testPackage, final String fileHref);
}
