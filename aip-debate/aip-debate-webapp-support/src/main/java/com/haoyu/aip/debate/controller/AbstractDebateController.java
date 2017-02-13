/**
 * 
 */
package com.haoyu.aip.debate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.service.IDebateArgumentStatService;
import com.haoyu.aip.debate.service.IDebateService;
import com.haoyu.aip.debate.service.IDebateUserService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@RequestMapping("/debates")
public abstract class AbstractDebateController extends AbstractBaseController{
	
	@Autowired
	protected IDebateService debateService;
	
	@Autowired
	protected IDebateUserService debateUserService;
	
	@Autowired
	protected IDebateArgumentStatService debateArgumentStatService;
	
	protected String getLogicalViewNamePrefix(){
		return "/debate/";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateDebate(DebateRelation debateRelation){
		return getLogicalViewNamePrefix()+"create_debate";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditDebate(@PathVariable String id, Model model){
		model.addAttribute("debate",debateService.findDebateById(id));
		return getLogicalViewNamePrefix()+"edit_debate";
	}

}
