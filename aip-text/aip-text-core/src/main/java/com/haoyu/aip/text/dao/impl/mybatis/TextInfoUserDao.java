package com.haoyu.aip.text.dao.impl.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.text.dao.ITextInfoUserDao;
import com.haoyu.aip.text.entity.TextInfoUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class TextInfoUserDao extends MybatisDao implements ITextInfoUserDao{

	@Override
	public int insert(TextInfoUser textInfoUser) {
		return super.insert(textInfoUser);
	}

	@Override
	public int update(TextInfoUser textInfoUser) {
		return super.update(textInfoUser);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

	@Override
	public TextInfoUser selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}
	
}
