package com.haoyu.aip.text.service;

import com.haoyu.aip.text.entity.TextInfoRelation;
import com.haoyu.sip.core.service.Response;

public interface ITextInfoRelationService {

	Response createTextInfoRelation(TextInfoRelation textInfoRelation);

	Response updateTextInfoRelation(TextInfoRelation textInfoRelation);

}
