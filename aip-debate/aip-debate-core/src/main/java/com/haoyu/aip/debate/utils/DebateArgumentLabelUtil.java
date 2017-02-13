/**
 * 
 */
package com.haoyu.aip.debate.utils;

/**
 * @author lianghuahuang
 *
 */
public class DebateArgumentLabelUtil {
	
	private static final String[] minArguments={"正","反"};
	
	private static final String[] maxArguments={"甲","乙","丙","丁"};
	
	public static String getArgumentLabel(int argumentCount,int orderNo){
		if(argumentCount<1||argumentCount>4||orderNo<0||orderNo>3){
			return null;
		}else if(argumentCount<=2){
			return minArguments[orderNo];
		}else{
			return maxArguments[orderNo];
		}
	}
}
