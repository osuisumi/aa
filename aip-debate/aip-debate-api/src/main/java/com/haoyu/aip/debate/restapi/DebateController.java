/**
 * 
 */
package com.haoyu.aip.debate.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.service.IDebateService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@RestController
@RequestMapping("/debates")
public class DebateController {
	
	@Autowired
	private IDebateService debateService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Response createDebate(Debate debate){
		return debateService.createDebate(debate);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Response updateDebate(Debate debate){
		return debateService.updateDebate(debate);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Debate getDebate(@PathVariable String id){
		return debateService.findDebateById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Debate> getDebate(PageBounds pageBounds){
		return debateService.findDebateByTagsAndRelations(null, null, pageBounds);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public Response deleteDebate(@PathVariable String id){
		Debate debate = new Debate(id);
		debate.setUpdatedby(new User("test"));
		return debateService.deleteDebate(debate);
	}

}
