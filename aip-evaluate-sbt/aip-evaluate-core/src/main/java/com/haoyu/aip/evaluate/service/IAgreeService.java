package com.haoyu.aip.evaluate.service;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.entity.Agree;
import com.haoyu.sip.core.service.Response;

public interface IAgreeService {
	Response create(Agree agree);

	Response update(Agree agree);

	Response deleteByLogic(Agree agree);

	Agree get(String id);

	List<Agree> list(Agree agree,PageBounds pageBounds);
	
	boolean isAgreeed(String relationId,String userId);

}
