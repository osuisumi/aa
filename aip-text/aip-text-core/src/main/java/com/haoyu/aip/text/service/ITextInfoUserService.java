package com.haoyu.aip.text.service;

import com.haoyu.aip.text.entity.TextInfoUser;
import com.haoyu.sip.core.service.Response;

public interface ITextInfoUserService {

	Response create(TextInfoUser textInfoUser);
	
	Response update(TextInfoUser textInfoUser);
	
	Response deleteByLogic(TextInfoUser textInfoUser);

	TextInfoUser get(String id);

	TextInfoUser createTextInfoUserIfNotExists(String textInfoRelationId);

	Response updateAttempt(TextInfoUser textInfoUser);
}
