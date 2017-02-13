/**
 * 
 */
package com.haoyu.aip.debate.listener;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.service.IDebateUserViewsService;
import com.haoyu.aip.debate.utils.RelationTypeConstants;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.entity.CommentStat;
import com.haoyu.sip.comment.event.CreateCommentEvent;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;

/**
 * @author lianghuahuang
 *
 */
@Component
public class CreateCommentEventListener implements
		ApplicationListener<CreateCommentEvent> {
	@Resource
	private ICommentService commentService;
	@Resource
	private IDebateUserViewsService debateUserViewsService;
	@Override
	public void onApplicationEvent(CreateCommentEvent event) {
		Comment comment = (Comment)event.getSource();
		//如果关注的是辩论则更新辩论对象的关注数
		if(comment!=null){
			Relation relation = comment.getRelation();
			if(relation!=null&&StringUtils.isNotEmpty(relation.getType())&&relation.getType().equals(RelationTypeConstants.DEBATE_USER_VIEWS)){
				CommentStat  cs= commentService.getCommentStatByRelation(relation);
				debateUserViewsService.updateDebateUserViewsCommentsNum(relation.getId(),cs.getCommentNum());
			}
		}	
		
	}

}
