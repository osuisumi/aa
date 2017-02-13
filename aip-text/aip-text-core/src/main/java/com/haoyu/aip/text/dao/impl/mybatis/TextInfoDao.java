package com.haoyu.aip.text.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.dao.ITextInfoDao;
import com.haoyu.aip.text.entity.TextInfo;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class TextInfoDao extends MybatisDao implements ITextInfoDao{

	@Override
	public int insert(TextInfo entity) {
		return super.insert(entity);
	}

	@Override
	public int update(TextInfo entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(TextInfo entity) {
		return update("deleteByLogic", entity);
	}

	@Override
	public TextInfo selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<TextInfo> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}
}
