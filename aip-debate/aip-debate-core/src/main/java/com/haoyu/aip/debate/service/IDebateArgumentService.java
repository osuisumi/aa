/**
 * 
 */
package com.haoyu.aip.debate.service;

import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateArgumentService {
	
	Response createDebateArgument(DebateArgument debateArgument);
	
	Response updateDebateArgument(DebateArgument debateArgument);
	
	Response deleteDebateArgument(DebateArgument debateArgument);
}
