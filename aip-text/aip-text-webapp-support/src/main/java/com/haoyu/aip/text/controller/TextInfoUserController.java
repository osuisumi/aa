package com.haoyu.aip.text.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.text.entity.TextInfoUser;
import com.haoyu.aip.text.service.ITextInfoUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("**/textInfo/user")
public class TextInfoUserController extends AbstractBaseController{

	@Resource
	private ITextInfoUserService textInfoUserService;
	
	@RequestMapping(value="updateAttempt", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAttempt(TextInfoUser textInfoUser){
		return textInfoUserService.updateAttempt(textInfoUser);
	}
}
