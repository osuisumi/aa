package com.haoyu.aip.survey.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.utils.Identities;

public class SurveyQuestion extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String title;

	private String content;

	private String prepose;

	private List<ChoiceGroup> choiceGroups;

	private List<QuestionPrepose> preposeGroups;

	private String interaction;

	private String type;

	private BigDecimal sortNo;

	private Survey survey;

	private boolean isRequireAnswer;

	private TextEntryInteraction textEntryInteraction;

	public SurveyQuestion() {
	}

	public SurveyQuestion(String id) {
		this.id = id;
	}

	public SurveyQuestion(Survey survey) {
		this.survey = survey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	//获取前置条件json字符串
	public String getPrepose() {
		if (StringUtils.isNoneEmpty(this.prepose)) {
			return this.prepose;
		} else if (CollectionUtils.isNotEmpty(this.preposeGroups)) {
			this.prepose = new JsonMapper().toJson(this.preposeGroups);
			return this.prepose;
		} else {
			return this.prepose;
		}
	}

	public void setPrepose(String prepose) {
		this.prepose = prepose;
	}

	public boolean isRequireAnswer() {
		return isRequireAnswer;
	}

	public void setRequireAnswer(boolean isRequireAnswer) {
		this.isRequireAnswer = isRequireAnswer;
	}
	
	public TextEntryInteraction getTextEntryInteraction() {
		if(this.textEntryInteraction!=null){
			return this.textEntryInteraction;
		}else if(QuesType.TEXTENTRY.equals(this.type)&&StringUtils.isNotEmpty(this.interaction)){
			JsonMapper mapper = new JsonMapper();
			this.textEntryInteraction = mapper.fromJson(this.interaction, TextEntryInteraction.class);
			return this.textEntryInteraction;
		}
		return this.textEntryInteraction;
	}

	public void setTextEntryInteraction(TextEntryInteraction textEntryInteraction) {
		this.textEntryInteraction = textEntryInteraction;
	}

	public List<QuestionPrepose> getPreposeGroups() {
		if (CollectionUtils.isNotEmpty(this.preposeGroups)) {
			return this.preposeGroups;
		} else if (StringUtils.isNotEmpty(this.prepose)) {
			JsonMapper mapper = new JsonMapper();
			this.preposeGroups = mapper.fromJson(this.prepose, mapper.contructCollectionType(ArrayList.class, QuestionPrepose.class));
			return this.preposeGroups;
		} else {
			return this.preposeGroups;
		}
	}

	public void setPreposeGroups(List<QuestionPrepose> preposeGroups) {
		this.preposeGroups = preposeGroups;
	}

	public String getInteraction() {
		if(StringUtils.isNotEmpty(interaction)){
			return interaction;
		}else if((QuesType.SINGLECHOICE.equals(this.type)||QuesType.MULTIPLECHOICE.equals(this.type)||QuesType.TRUEORFALSE.equals(this.type))&&CollectionUtils.isNotEmpty(this.choiceGroups)){
			for (ChoiceGroup choiceGroup : this.choiceGroups) {
				if (StringUtils.isEmpty(choiceGroup.getId())) {
					choiceGroup.setId(Identities.uuid2());
				}
				if (CollectionUtils.isNotEmpty(choiceGroup.getChoices())) {
					for (Choice c : choiceGroup.getChoices()) {
						if (StringUtils.isEmpty(c.getId())) {
							c.setId(Identities.uuid2());
						}
					}
				}
			}
			this.interaction = new JsonMapper().toJson(this.choiceGroups);
			return interaction;
		}else if(QuesType.TEXTENTRY.equals(this.type)&&this.textEntryInteraction!=null){
			this.interaction = new JsonMapper().toJson(this.textEntryInteraction);
			return interaction;
		}else{
			return interaction;
		}
		
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public List<ChoiceGroup> getChoiceGroups() {
		if (CollectionUtils.isNotEmpty(this.choiceGroups)) {
			return this.choiceGroups;
		} else if (StringUtils.isNotEmpty(this.interaction)) {
			JsonMapper mapper = new JsonMapper();
			this.choiceGroups = mapper.fromJson(this.interaction, mapper.contructCollectionType(ArrayList.class, ChoiceGroup.class));
		}
		return this.choiceGroups;

	}

	public void setChoiceGroups(List<ChoiceGroup> choiceGroups) {
		this.choiceGroups = choiceGroups;
	}

	public class QuesType {

		public final static String SINGLECHOICE = "singleChoice";

		public final static String MULTIPLECHOICE = "multipleChoice";

		public final static String TRUEORFALSE = "trueOrFalse";

		public final static String TEXTENTRY = "textEntry";
	}

	/*public void setInteractionByChoiceGroups() {
		if (CollectionUtils.isNotEmpty(this.choiceGroups)) {
			for (ChoiceGroup choiceGroup : this.choiceGroups) {
				if (StringUtils.isEmpty(choiceGroup.getId())) {
					choiceGroup.setId(Identities.uuid2());
				}
				if (CollectionUtils.isNotEmpty(choiceGroup.getChoices())) {
					for (Choice c : choiceGroup.getChoices()) {
						if (StringUtils.isEmpty(c.getId())) {
							c.setId(Identities.uuid2());
						}
					}
				}
			}
			this.interaction = new JsonMapper().toJson(this.choiceGroups);
		}
	}*/

	@Override
	public String toString() {
		return this.title + "-------------" + this.createTime;
	}
	
	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		this.setSortNo(BigDecimal.valueOf(9999));
	}

}
