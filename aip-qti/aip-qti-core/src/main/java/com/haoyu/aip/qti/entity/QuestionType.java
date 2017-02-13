/**
 * 
 */
package com.haoyu.aip.qti.entity;

/**
 * 题目类型
 * @author lianghuahuang
 *
 */
public enum QuestionType {
	/**
	 * 单选
	 */
	SINGLE_CHOICE("单选题"),
	
	/**
	 * 多选
	 */
	MULTIPLE_CHOICE("多选题"),
	
	/**
	 * 是非
	 */
	TRUE_FALSE("是非题"),
	
	/**
	 * 文件上传
	 */
	UPLOAD("附件题"),
	
	/**
	 * 文本域
	 */
	EXTENDED_TEXT("文本题"),
	
	/**
	 * 填空
	 */
	TEXTENTRY("填空题");
	
	private final String name;
	
	QuestionType(String name){
		this.name = name;
	}
	
	public static QuestionType getQuesType(String name){
		if(name!=null){
			if(name.equals(SINGLE_CHOICE.name)){
				return SINGLE_CHOICE;
			}else if(name.equals(MULTIPLE_CHOICE.name)){
				return MULTIPLE_CHOICE;
			}else if(name.equals(TRUE_FALSE.name)){
				return TRUE_FALSE;
			}else if(name.equals(UPLOAD.name)){
				return UPLOAD;
			}else if(name.equals(EXTENDED_TEXT.name)){
				return EXTENDED_TEXT;
			}else if(name.equals(TEXTENTRY.name)){
				return TEXTENTRY;
			}
		}
		return null;
	}
	
}
