/**
 * 
 */
package com.haoyu.aip.debate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateUserService;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/debate_users")
public class DebateUserController extends AbstractDebateController{
	@Autowired
	private IDebateUserService debateUserService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createDebateUser(DebateUser debateUser){
		return debateUserService.createDebateUser(debateUser);
	}
	
}
