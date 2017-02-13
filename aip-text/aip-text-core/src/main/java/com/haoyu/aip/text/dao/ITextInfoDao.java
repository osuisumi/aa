package com.haoyu.aip.text.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.entity.TextInfo;

public interface ITextInfoDao {

	int insert(TextInfo entity);

	int update(TextInfo entity);

	int deleteByLogic(TextInfo entity);

	TextInfo selectByPrimaryKey(String id);

	List<TextInfo> select(Map<String, Object> params, PageBounds pageBounds);

}
