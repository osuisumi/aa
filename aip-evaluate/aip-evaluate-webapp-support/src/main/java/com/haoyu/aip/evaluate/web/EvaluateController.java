package com.haoyu.aip.evaluate.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.courseware.service.ICoursewareService;
import com.haoyu.aip.evaluate.entity.Agree;
import com.haoyu.aip.evaluate.entity.Evaluate;
import com.haoyu.aip.evaluate.entity.EvaluateScore;
import com.haoyu.aip.evaluate.service.IAgreeService;
import com.haoyu.aip.evaluate.service.IEvaluateScoreService;
import com.haoyu.aip.evaluate.service.IEvaluateService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping(value = "**/evaluate")
public class EvaluateController extends AbstractBaseController{
	@Resource
	private IEvaluateService evaluateService;
	@Resource
	private IAgreeService agreeService;
	@Resource
	private ICoursewareService coursewareService;
	@Resource
	private IEvaluateScoreService evaluateScoreService;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Evaluate evaluate, Model model) {
		model.addAttribute("listenCourse", coursewareService.get(evaluate.getEvaluateEntityId()));
		Evaluate e = evaluateService.get(DigestUtils.md5Hex(evaluate.getEvaluateEntityId()+ThreadContext.getUser().getId()));
		if(e!=null){
			model.addAttribute("evaluate", e);
		}else{
			List<EvaluateScore> evaluateScores = new ArrayList<EvaluateScore>();
			for(int i=0;i<10;i++){
				evaluateScores.add(new EvaluateScore(i+"",0));
			}
			e = new Evaluate();
			e.setEvaluateScores(evaluateScores);
			model.addAttribute("evaluate", e);
		}
		model.addAllAttributes(request.getParameterMap());
		return "evaluate/edit_evaluate";
	}

	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Response createEvaluate(Evaluate evaluate) {
		if(StringUtils.isEmpty(evaluate.getId())){
			return this.evaluateService.create(evaluate);
		}
		return this.evaluateService.update(evaluate);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String listEvaluate(Evaluate evaluate,String pageable,String role,Model model){
		model.addAttribute("evaluates",this.evaluateService.list(evaluate, getPageBounds(10, true)));
		model.addAttribute("evaluateCount",this.evaluateService.countByEvaluateEntityId(evaluate.getEvaluateEntityId()));
		model.addAttribute("role",role);
		model.addAttribute("relationId",evaluate.getEvaluateEntityId());
		if(StringUtils.isNotEmpty(pageable)&& "false".equals(pageable))
		{
			return "evaluate/list_evaluate";
			
		}else{
			return "evaluate/list_more_evaluate";
		}
	}
	
	
	@RequestMapping(value = "{id}/view",method=RequestMethod.GET)
	public String view(Evaluate evaluate, Model model) {
		model.addAttribute("evaluates", this.evaluateService.list(evaluate, getPageBounds(6, true)));
		model.addAttribute("avgScoresByItem",evaluateScoreService.getItemAvgScoreByRelationId(evaluate.getEvaluateEntityId()));
		model.addAllAttributes(request.getParameterMap());
		return "evaluate/view_evaluate";
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	public @ResponseBody Response delete(Evaluate evaluate){
		return this.evaluateService.deleteByLogic(evaluate);
	}
	
	@RequestMapping(value="{id}/result",method=RequestMethod.GET)
	public String viewResult(@PathVariable String id,Model model){
		model.addAttribute("avgScoresByItem", evaluateScoreService.getItemAvgScoreByRelationId(id));
		model.addAttribute("courseware", coursewareService.get(id));
		model.addAllAttributes(request.getParameterMap());
		return "evaluate/view_evaluateResult";
	}
	
	@RequestMapping(value="{relation.id}/result/detail")
	public @ResponseBody List<EvaluateScore> viewDetailEvaluateResult(EvaluateScore evaluateScore){
		return evaluateScoreService.list(evaluateScore, null);
	}
	
	@RequestMapping(value="response/sumAvgScore")
	public @ResponseBody Integer getSumAvgScore(String relationId){
		Integer sumAvgScore = this.evaluateScoreService.getAvgScoreByRelationId(relationId);
		if(sumAvgScore == null){
			sumAvgScore = 0;
		}
		return sumAvgScore;
	}
	
	@RequestMapping(value="response/countByRelationId")
	public @ResponseBody Integer countByRelationId(Evaluate evaluate){
		return (this.evaluateService.list(evaluate, null)).size();
	}
	
	@RequestMapping(value = "acceptOrNot")
	public @ResponseBody Response agreeOrSign(Evaluate evaluate) {
		return this.evaluateService.update(evaluate);
	}
	
	@RequestMapping(value = "agree")
	public @ResponseBody Response createAgree(Agree agree) {
		if (this.agreeService.isAgreeed(agree.getRelation().getId(), ThreadContext.getUser().getId())) {
			return Response.failInstance();
		} else {
			this.agreeService.create(agree);
			return Response.successInstance();
		}

	}
	@RequestMapping(value="{id}/getById",method=RequestMethod.GET)
	public @ResponseBody Evaluate getEvaluateById(Evaluate evaluate){
		return this.evaluateService.get(evaluate.getId());
	} 

}
