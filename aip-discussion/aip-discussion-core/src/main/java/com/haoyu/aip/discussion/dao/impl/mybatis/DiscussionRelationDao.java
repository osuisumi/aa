package com.haoyu.aip.discussion.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionRelationDao;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class DiscussionRelationDao extends MybatisDao implements IDiscussionRelationDao{
	
	@Override
	public int insert(DiscussionRelation entity) {
		return super.insert(entity);
	}

	@Override
	public int update(DiscussionRelation entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(DiscussionRelation entity) {
		return super.deleteByLogic(entity);
	}

	@Override
	public DiscussionRelation selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionRelation> select(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

	@Override
	public int updateLastPost(DiscussionRelation discussionRelation) {
		return this.update("updateLastPost",discussionRelation);
	}

	@Override
	public int updateByIdNotSelective(DiscussionRelation discussionRelation) {
		return this.update("updateByIdNotSelective", discussionRelation);
	}

}
