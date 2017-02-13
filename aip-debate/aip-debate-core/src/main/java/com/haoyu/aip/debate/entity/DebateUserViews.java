/**
 * 
 */
package com.haoyu.aip.debate.entity;



import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author Administrator
 *
 */
public class DebateUserViews extends BaseEntity {
	
	private String id;
	
	private DebateUser debateUser;
	
	/**
	 * 观点内容
	 */
	private String viewsContent; 
	
	/**
	 * 是否最佳观点
	 */
	private String isBestViews;
	
	
	/**
	 * 点赞支持数
	 */
	private int supportNum;
	
	/**
	 * 评论数
	 */
	private int commentsNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DebateUser getDebateUser() {
		return debateUser;
	}

	public void setDebateUser(DebateUser debateUser) {
		this.debateUser = debateUser;
	}

	public String getViewsContent() {
		return viewsContent;
	}

	public void setViewsContent(String viewsContent) {
		this.viewsContent = viewsContent;
	}

	public String getIsBestViews() {
		return isBestViews;
	}

	public void setIsBestViews(String isBestViews) {
		this.isBestViews = isBestViews;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}


}
