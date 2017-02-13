/**
 * 
 */
package com.haoyu.aip.debate.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateRelationService;
import com.haoyu.aip.debate.service.IDebateService;
import com.haoyu.aip.debate.utils.RelationTypeConstants;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.service.IFollowService;

/**
 * @author lianghuahuang
 *
 */

@Controller
@RequestMapping(value={"/debates"})
public class DebateController extends AbstractDebateController {
	@Autowired
	private IDebateRelationService debateRelationService;
	
	@Autowired
	private IFollowService followService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createDebate(Debate debate){
//		debate.getDebateRelations().get(0).getTimePeriod().setStartTime(startTime);
//		debate.getDebateRelations().get(0).getTimePeriod().setEndTime(endTime);
		return debateService.createDebate(debate);
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Debate> getDebates(){
		return debateService.findDebateByTagsAndRelations(null, null, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response updateDebate(@PathVariable String id,Debate debate){
		debate.setId(id);
		return debateService.updateDebate(debate);
	}
	
	@RequestMapping(value = "/{debateRelationId}/view_debate",method={RequestMethod.GET})
	public String viewDebate(@PathVariable String debateRelationId,Model model){
		DebateUser du = new DebateUser();
		du.setDebateRelation(new DebateRelation(debateRelationId));
		du.setCreator(ThreadContext.getUser());
		model.addAttribute("debateUser", debateUserService.findDebateUser(du));
		model.addAttribute("follow", followService.findFollowById(Follow.getId(ThreadContext.getUser().getId(), debateRelationId, RelationTypeConstants.DEBATE_RELATION)));
		model.addAttribute("debateArgumentStats", debateArgumentStatService.findDebateArgumentStatByDebateRelation(du.getDebateRelation()));
		//更新浏览数
		Runnable r = ()->{
			debateRelationService.updateBrowseNum(debateRelationId);
		};
		new Thread(r).start();
		model.addAllAttributes(request.getParameterMap());
		return this.getLogicalViewNamePrefix()+"view_debate";
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteDebate(Debate debate){
		return debateService.deleteDebate(debate);
	}
}
