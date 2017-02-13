/**
 * 
 */
package com.haoyu.aip.debate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.entity.ViewComment;
import com.haoyu.aip.debate.service.IDebateRelationService;
import com.haoyu.aip.debate.service.IDebateUserViewsService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Controller
@RequestMapping("/debate_userviews")
public class DebateUserViewsController extends AbstractDebateController {
	@Autowired
	private IDebateUserViewsService debateUserViewsService;
	
	@Autowired
	private IDebateRelationService debateRelationService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createDebateUserViews(DebateUserViews debateUserViews){
		return debateUserViewsService.createDebateUserViews(debateUserViews);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateDebateUserViews(@PathVariable String id,DebateUserViews debateUserViews){
		debateUserViews.setId(id);
		return debateUserViewsService.updateDebateUserViews(debateUserViews);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getDebateUserViews(DebateUser debateUser,Model model){
		 model.addAttribute("debateRelation", debateRelationService.findDebateRelationStatByDebateUser(debateUser));
		 model.addAttribute("debateUserViews", debateUserViewsService.findDebateUserViewsByDebateUser(debateUser, this.getPageBounds(5, true)));
		 return this.getLogicalViewNamePrefix()+"view_userviews";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteDebateUserViews(@PathVariable String id,DebateUserViews debateUserViews){
		debateUserViews.setId(id);
		return debateUserViewsService.deleteDebateUserViews(debateUserViews);
	}
	
	@RequestMapping(value = "/{id}/view_comment",method=RequestMethod.GET)
	public String getViewComment(@PathVariable String id,ViewComment viewComment,Model model){
		 if(viewComment==null){
			 viewComment = new ViewComment();
		 }
		 viewComment.setRelation(new Relation(id));
		 model.addAttribute("viewComments",debateUserViewsService.findViewComment(viewComment, this.getPageBounds(5, true)) );
		 return this.getLogicalViewNamePrefix()+"view_comment";
	}
	
	
}
