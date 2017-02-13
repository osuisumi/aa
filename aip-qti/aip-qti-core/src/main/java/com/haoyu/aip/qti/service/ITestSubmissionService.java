/**
 * 
 */
package com.haoyu.aip.qti.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.sip.core.service.Response;

import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.types.StringResponseData;

/**
 * @author lianghuahuang
 *
 */
public interface ITestSubmissionService {
	
	Response handleResponses(String testDeliveryUserId,
            final Map<String, StringResponseData> stringResponseMap);
	
	
	
}
