package com.haoyu.aip.text.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.text.entity.TextInfoFile;

public interface ITextInfoFileDao {

	int insert(TextInfoFile entity);

	List<TextInfoFile> select(Map<String, Object> parameter, PageBounds pageBounds);

	int update(TextInfoFile entity);

	int deleteByIds(Map<String, Object> map);

	TextInfoFile selectById(String id);

}
