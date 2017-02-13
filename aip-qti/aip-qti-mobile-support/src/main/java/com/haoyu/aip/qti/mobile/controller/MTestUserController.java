package com.haoyu.aip.qti.mobile.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.service.ITestDeliveryUserService;
import com.haoyu.aip.qti.service.ITestSubmissionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

import uk.ac.ed.ph.jqtiplus.types.StringResponseData;

@Controller
@RequestMapping("**/m/test/user")
public class MTestUserController extends AbstractBaseController {

	@Resource
	private ITestDeliveryUserService testDeliveryUserService;

	@Resource
	private ITestSubmissionService testSubmissionService;

	@RequestMapping(value = "/{id}/finishTest", method = RequestMethod.PUT)
	@ResponseBody
	public Response finishTest(@PathVariable String id, HttpServletRequest request) {
		TestDeliveryUser testDeliveryUser = new TestDeliveryUser();
		testDeliveryUser.setId(id);
		Response response = testDeliveryUserService.finishTest(testDeliveryUser, extractStringResponseData(request));
		return response.responseData(null);
	}

	protected Map<String, StringResponseData> extractStringResponseData(final HttpServletRequest request) {
		final Map<String, StringResponseData> responseMap = new HashMap<String, StringResponseData>();
		@SuppressWarnings("unchecked")
		final Set<String> parameterNames = request.getParameterMap().keySet();
		for (final String name : parameterNames) {
			if (name.startsWith("qti_item_")) {
				final String responseIdentifierString = name.substring("qti_item_".length());
				final String[] responseValues = request.getParameterValues("qti_response_" + responseIdentifierString);
				final StringResponseData stringResponseData = new StringResponseData(responseValues);
				responseMap.put(responseIdentifierString, stringResponseData);
			}
		}
		return responseMap;
	}

}
