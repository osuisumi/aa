package com.haoyu.aip.text.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.dao.ITextInfoFileDao;
import com.haoyu.aip.text.entity.TextInfoFile;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class TextInfoFileDao extends MybatisDao implements ITextInfoFileDao{

	@Override
	public int insert(TextInfoFile entity) {
		return super.insert(entity);
	}

	@Override
	public List<TextInfoFile> select(Map<String, Object> parameter, PageBounds pageBounds) {
		return selectList("select", parameter, pageBounds);
	}

	@Override
	public int update(TextInfoFile entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByIds(Map<String, Object> map) {
		return update("deleteByIds", map);
	}

	@Override
	public TextInfoFile selectById(String id) {
		return super.selectByPrimaryKey(id);
	}

}
