package com.haoyu.aip.text.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.text.dao.ITextInfoRelationDao;
import com.haoyu.aip.text.entity.TextInfoRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class TextInfoRelationDao extends MybatisDao implements ITextInfoRelationDao{

	@Override
	public int insert(TextInfoRelation entity) {
		return super.insert(entity);
	}

	@Override
	public int update(TextInfoRelation textInfoRelation) {
		return super.update(textInfoRelation);
	}

}
