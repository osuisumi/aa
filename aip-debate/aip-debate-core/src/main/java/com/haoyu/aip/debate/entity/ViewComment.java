/**
 * 
 */
package com.haoyu.aip.debate.entity;

import com.haoyu.sip.comment.entity.Comment;

/**
 * @author lianghuahuang
 *
 */
public class ViewComment extends Comment {
	
	private int argumentCount;
	
	private int argumentOrderNo;
	/**
	 * @return the argumentCount
	 */
	public int getArgumentCount() {
		return argumentCount;
	}

	/**
	 * @param argumentCount the argumentCount to set
	 */
	public void setArgumentCount(int argumentCount) {
		this.argumentCount = argumentCount;
	}

	/**
	 * @return the argumentOrderNo
	 */
	public int getArgumentOrderNo() {
		return argumentOrderNo;
	}

	/**
	 * @param argumentOrderNo the argumentOrderNo to set
	 */
	public void setArgumentOrderNo(int argumentOrderNo) {
		this.argumentOrderNo = argumentOrderNo;
	}
}
