package com.haoyu.aip.discussion.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionDao;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.utils.Collections3;

@Repository
public class DiscussionDao extends MybatisDao implements IDiscussionDao{

	@Override
	public int insert(Discussion entity) {
		return super.insert(entity);
	}

	@Override
	public int update(Discussion entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(Discussion entity) {
		return super.deleteByLogic(entity);
	}

	@Override
	public Discussion selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<Discussion> select(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

	@Override
	public Discussion selectByOp(Map<String, Object> param,PageBounds pageBounds) {
		List<Discussion> discussions =  selectList("selectByOp", param,pageBounds);
		if (Collections3.isNotEmpty(discussions)) {
			return discussions.get(0);
		}
		return null;
	}

	@Override
	public int updateByIds(Map<String, Object> map) {
		return update("updateByIds", map);
	}

	@Override
	public int deleteByIds(Map<String, Object> map) {
		return update("deleteByIds", map);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
