package com.haoyu.aip.text.dao;

import java.util.Map;

import com.haoyu.aip.text.entity.TextInfoUser;

public interface ITextInfoUserDao {

	int insert(TextInfoUser textInfoUser);

	int update(TextInfoUser textInfoUser);

	int deleteByLogic(Map<String, Object> param);

	TextInfoUser selectByPrimaryKey(String id);
}
