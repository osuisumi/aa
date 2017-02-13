/**
 * 
 */
package com.haoyu.aip.qti.entity;

import org.apache.commons.lang3.StringUtils;

import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;

/**
 * @author lianghuahuang
 *
 */
public class TrueFalseQuestion extends SingleChoiceQuestion {
	
	
	public TrueFalseQuestion(){
		super();
		this.setQuesType(QuestionType.TRUE_FALSE);
	}
	
	public TrueFalseQuestion(AssessmentItem assessmentItem){
		super(assessmentItem);
		this.setQuesType(QuestionType.TRUE_FALSE);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.entity.Question#isValid()
	 */
	@Override
	public boolean validate() {
		if(this.getInteractionOptions().isEmpty()){
			this.getValidateErrorInfos().add("未设置答题选项");
			return false;
		}
		if(this.getInteractionOptions().size()!=2){
			this.getValidateErrorInfos().add("答题选项有且仅能设置两个");
			return false;
		}
		if(StringUtils.isEmpty(this.getCorrectOption())){
			this.getValidateErrorInfos().add("未设置正确答案");
			return false;
		}
		if(!this.getInteractionOptions().contains(new InteractionOption(this.getCorrectOption()))){
			this.getValidateErrorInfos().add("正确答案不在选项中");
			return false;
		}
		return true;
	}

}
