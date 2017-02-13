/**
 * 
 */
package com.haoyu.aip.qti.entity;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import uk.ac.ed.ph.jqtiplus.SimpleJqtiFacade;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.content.basic.Block;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.content.variable.FeedbackBlock;
import uk.ac.ed.ph.jqtiplus.node.content.xhtml.text.P;
import uk.ac.ed.ph.jqtiplus.node.expression.general.BaseValue;
import uk.ac.ed.ph.jqtiplus.node.expression.general.Correct;
import uk.ac.ed.ph.jqtiplus.node.expression.general.Variable;
import uk.ac.ed.ph.jqtiplus.node.expression.operator.Match;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.CorrectResponse;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Prompt;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.SimpleChoice;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.MapEntry;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.Mapping;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.ResponseCondition;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.ResponseElse;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.ResponseIf;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.ResponseProcessing;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.SetOutcomeValue;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.OutcomeDeclaration;
import uk.ac.ed.ph.jqtiplus.node.shared.FieldValue;
import uk.ac.ed.ph.jqtiplus.node.shared.declaration.DefaultValue;
import uk.ac.ed.ph.jqtiplus.node.test.VisibilityMode;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.types.ComplexReferenceIdentifier;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.IdentifierValue;


/**
 * @author lianghuahuang
 *
 */
public class MultipleChoiceQuestion extends Question {
	
	private List<InteractionOption> interactionOptions;
	
	private List<String> correctOptions;
	
	private boolean shuffle=false;
	
	private OptionScoreMapping optionScoreMapping;
	
	private String correctFeedback;
	
	private String incorrectFeedback;
	
	public MultipleChoiceQuestion(){
		super();
		this.setQuesType(QuestionType.MULTIPLE_CHOICE);
		this.interactionOptions = Lists.newArrayList();
		this.correctOptions = Lists.newArrayList();
	}
	
	public MultipleChoiceQuestion(AssessmentItem assessmentItem){
		super(assessmentItem);
		this.setQuesType(QuestionType.MULTIPLE_CHOICE);
		interactionOptions = Lists.newArrayList();
		this.correctOptions = Lists.newArrayList();
		ResponseDeclaration responseDeclaration= assessmentItem.getResponseDeclaration(Identifier.assumedLegal("RESPONSE"));
		List<FieldValue> fieldValues = responseDeclaration.getCorrectResponse().getFieldValues();
		for(FieldValue fieldValue:fieldValues){
			correctOptions.add(fieldValue.getSingleValue().toQtiString());
		}
		
/*		Mapping mapping = responseDeclaration.getMapping();
		optionScoreMapping = new OptionScoreMapping();
		optionScoreMapping.setDefaultValue(mapping.getDefaultValue());
		optionScoreMapping.setLowerBound(mapping.getLowerBound());
		optionScoreMapping.setUpperBound(mapping.getUpperBound());
		Map<String, Double> mapEntries = Maps.newHashMap();
		List<MapEntry> mes = mapping.getMapEntries();
		for(MapEntry me:mes){
			mapEntries.put(me.getMapKey().toString(), Double.valueOf(me.getMappedValue()));
		}
		optionScoreMapping.setMapEntries(mapEntries);*/

		List<Block> blocks = assessmentItem.getItemBody().getBlocks();
		for(Block block:blocks){
			if(block instanceof ChoiceInteraction){
				ChoiceInteraction choiceInteraction = (ChoiceInteraction)block;
				TextRun tr = (TextRun)choiceInteraction.getPrompt().getInlineStatics().get(0);
				this.setText(tr.getTextContent());
				this.setShuffle(choiceInteraction.getShuffle());
				List<SimpleChoice> simpleChoices =  choiceInteraction.getSimpleChoices();
				for(SimpleChoice simpleChoice:simpleChoices){
					tr = (TextRun)simpleChoice.getFlowStatics().get(0);
					InteractionOption interactionOption = new InteractionOption(simpleChoice.getIdentifier().toString(),simpleChoice.getFixed(),tr.getTextContent());
					interactionOptions.add(interactionOption);
				}
			}
			if(block instanceof FeedbackBlock){
				FeedbackBlock feedbackBlock = (FeedbackBlock)block;		
				if(feedbackBlock.getIdentifier().toString().equals("CORRECT")){
					P p = (P)feedbackBlock.getBlocks().get(0);
					TextRun tr = (TextRun)p.getInlines().get(0);
					this.setCorrectFeedback(tr.getTextContent());
				}else if(feedbackBlock.getIdentifier().toString().equals("INCORRECT")){
					P p = (P)feedbackBlock.getBlocks().get(0);
					TextRun tr = (TextRun)p.getInlines().get(0);
					this.setIncorrectFeedback(tr.getTextContent());
				}
				
			}
			
		}
	}
	

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.entity.Question#validate()
	 */
	@Override
	protected boolean validate() {
		if(interactionOptions.isEmpty()){
			this.getValidateErrorInfos().add("未设置答题选项");
			return false;
		}
		if(interactionOptions.size()<=1){
			this.getValidateErrorInfos().add("答题选项至少需要设置两个以上");
			return false;
		}
		if(correctOptions==null||correctOptions.isEmpty()){
			this.getValidateErrorInfos().add("未设置正确答案");
			return false;
		}
		for(String correctOption:correctOptions){
			if(!interactionOptions.contains(new InteractionOption(correctOption))){
				this.getValidateErrorInfos().add("正确答案不在选项中");
				return false;
			}
		}
		if(optionScoreMapping!=null&&!optionScoreMapping.isEmpty()){
			Map<String,Double> mapEntries = optionScoreMapping.getMapEntries();
			for(InteractionOption io:interactionOptions){
				if(!mapEntries.containsKey(io.getId())){
					this.getValidateErrorInfos().add("评分选项不在答案选项中");
					return false;
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.entity.Question#toXml()
	 */
	@Override
	public String toXml() {
		 /* Create empty AssessmentItem and add necessary properties to make it valid */
        final AssessmentItem assessmentItem = new AssessmentItem();
        assessmentItem.setIdentifier(this.getId());
        assessmentItem.setTitle(this.getTitle());
        assessmentItem.setAdaptive(false);
        assessmentItem.setTimeDependent(false);
        /* Declare a Response */
        final ResponseDeclaration responseDeclaration = new ResponseDeclaration(assessmentItem);
        responseDeclaration.setIdentifier(Identifier.assumedLegal("RESPONSE"));
        responseDeclaration.setBaseType(BaseType.IDENTIFIER);//指定答案类型
        responseDeclaration.setCardinality(Cardinality.MULTIPLE);//多选       
        CorrectResponse correctResponse = new CorrectResponse(responseDeclaration);
        for(String correctOption:correctOptions){
			IdentifierValue value = new IdentifierValue(correctOption);
			correctResponse.getFieldValues().add(new FieldValue(correctResponse, value));
        }
		responseDeclaration.setCorrectResponse(correctResponse);
		assessmentItem.getResponseDeclarations().add(responseDeclaration);
		
		/* Declare mapping*/
/*		Mapping mapping = new Mapping(responseDeclaration);
		if(optionScoreMapping!=null&&!optionScoreMapping.isEmpty()){
			mapping.setDefaultValue(Double.valueOf(optionScoreMapping.getDefaultValue()));
			mapping.setLowerBound(Double.valueOf(optionScoreMapping.getLowerBound()));
			mapping.setUpperBound(Double.valueOf(optionScoreMapping.getUpperBound()));
			Map<String,Double> mapEntries = optionScoreMapping.getMapEntries();
			Iterator<Entry<String,Double>> iterator = mapEntries.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,Double> entry = iterator.next();
				MapEntry mapEntry = new MapEntry(mapping);
				mapEntry.setMapKey( new IdentifierValue(entry.getKey()));
				mapEntry.setMappedValue(Double.valueOf(entry.getValue()));
				mapping.getMapEntries().add(mapEntry);
			}
			
		}else{
			mapping.setDefaultValue(Double.valueOf(-this.getScore()));
			mapping.setLowerBound(Double.valueOf(0));
			mapping.setUpperBound(Double.valueOf(this.getScore()));
			double avgScore = BigDecimal.valueOf(this.getScore()).divide(BigDecimal.valueOf(correctOptions.size())).setScale(1).doubleValue();
			for(String option:correctOptions){
				MapEntry mapEntry = new MapEntry(mapping);
				mapEntry.setMapKey( new IdentifierValue(option));
				mapEntry.setMappedValue(Double.valueOf(avgScore));
				mapping.getMapEntries().add(mapEntry);
			}
			
		}
		responseDeclaration.setMapping(mapping);*/
		
		/* Declare Itembody*/
		ItemBody itemBody = new ItemBody(assessmentItem);
		assessmentItem.setItemBody(itemBody);
		ChoiceInteraction choiceInteraction = new ChoiceInteraction(itemBody);
		choiceInteraction.setResponseIdentifier(Identifier.assumedLegal("RESPONSE"));
		choiceInteraction.setShuffle(this.shuffle);
		choiceInteraction.setMaxChoices(0);
		
		final Prompt prompt = new Prompt(choiceInteraction);
        choiceInteraction.setPrompt(prompt);
        prompt.getInlineStatics().add(new TextRun(prompt,this.getText()));
        for(InteractionOption io:interactionOptions){
        	final SimpleChoice simpleChoice = new SimpleChoice(choiceInteraction);
            simpleChoice.setIdentifier(Identifier.assumedLegal(io.getId()));
            simpleChoice.getFlowStatics().add(new TextRun(simpleChoice,io.getText()));
            if(io.isFixed()){
            	simpleChoice.setFixed(io.isFixed());
            }
            choiceInteraction.getSimpleChoices().add(simpleChoice);
        }
        itemBody.getBlocks().add(choiceInteraction);
		 
        
        /* Declare a SCORE outcome variable */
        final OutcomeDeclaration score = new OutcomeDeclaration(assessmentItem);
        score.setIdentifier(Identifier.assumedLegal("SCORE"));
        score.setCardinality(Cardinality.SINGLE);
        score.setBaseType(BaseType.FLOAT);
        final DefaultValue defaultValue = new DefaultValue(score);
        defaultValue.getFieldValues().add(new FieldValue(defaultValue, new FloatValue(this.getScore())));
        score.setDefaultValue(defaultValue);
        assessmentItem.getOutcomeDeclarations().add(score);
        
        final ResponseProcessing responseProcessing = new ResponseProcessing(assessmentItem);
        if(this.getScore()==1f&&StringUtils.isEmpty(correctFeedback)&&StringUtils.isEmpty(incorrectFeedback)){
        	responseProcessing.setTemplate(URI.create("http://www.imsglobal.org/question/qti_v2p1/rptemplates/match_correct"));
        }else{
        	ResponseCondition responseCondition = new ResponseCondition(responseProcessing);
        	
        	ResponseIf responseIf = new ResponseIf(responseCondition);
        	
        	Match match  = new Match(responseIf);
        	Variable variable = new Variable(match);
        	variable.setIdentifier(ComplexReferenceIdentifier.assumedLegal("RESPONSE"));
        	match.getExpressions().add(variable);
        	Correct correct = new Correct(match);
        	correct.setIdentifier(ComplexReferenceIdentifier.assumedLegal("RESPONSE"));
        	match.getExpressions().add(correct);
        	
        	responseIf.getExpressions().add(match);
        	
        	SetOutcomeValue setOutcomeValue = new SetOutcomeValue(responseIf);
        	setOutcomeValue.setIdentifier(Identifier.assumedLegal("SCORE"));
        	BaseValue baseValue = new BaseValue(setOutcomeValue);
        	baseValue.setBaseTypeAttrValue(BaseType.FLOAT);
        	baseValue.setSingleValue(new FloatValue(this.getScore()));
        	setOutcomeValue.setExpression(baseValue);        	
        	responseIf.getResponseRules().add(setOutcomeValue);
        	
        	if(StringUtils.isNotEmpty(correctFeedback)){
	        	setOutcomeValue = new SetOutcomeValue(responseProcessing);
	        	setOutcomeValue.setIdentifier(Identifier.assumedLegal("FEEDBACK"));
	        	baseValue = new BaseValue(setOutcomeValue);
	        	baseValue.setBaseTypeAttrValue(BaseType.IDENTIFIER);
	        	baseValue.setSingleValue(new IdentifierValue("CORRECT"));
	        	setOutcomeValue.setExpression(baseValue);
	        	responseIf.getResponseRules().add(setOutcomeValue);
	        	
	        	//feedback
	        	FeedbackBlock feedbackBlock = new FeedbackBlock(itemBody);
	        	feedbackBlock.setOutcomeIdentifier(Identifier.assumedLegal("FEEDBACK"));
	        	feedbackBlock.setIdentifier(Identifier.assumedLegal("CORRECT"));
	        	feedbackBlock.setVisibilityMode(VisibilityMode.SHOW_IF_MATCH);
	        	P p = new P(feedbackBlock);
	        	p.getInlines().add(new TextRun(p,correctFeedback));
	        	feedbackBlock.getBlocks().add(p);
	        	itemBody.getBlocks().add(feedbackBlock);
        	}
        	responseCondition.setResponseIf(responseIf);
        	
        	ResponseElse responseElse = new ResponseElse(responseCondition);
        	setOutcomeValue = new SetOutcomeValue(responseIf);
        	setOutcomeValue.setIdentifier(Identifier.assumedLegal("SCORE"));
        	baseValue = new BaseValue(setOutcomeValue);
        	baseValue.setBaseTypeAttrValue(BaseType.FLOAT);
        	baseValue.setSingleValue(new FloatValue(0.0));
        	setOutcomeValue.setExpression(baseValue);        	
        	responseElse.getResponseRules().add(setOutcomeValue);  
        	
        	if(StringUtils.isNotEmpty(incorrectFeedback)){
	        	setOutcomeValue = new SetOutcomeValue(responseProcessing);
	        	setOutcomeValue.setIdentifier(Identifier.assumedLegal("FEEDBACK"));
	        	baseValue = new BaseValue(setOutcomeValue);
	        	baseValue.setBaseTypeAttrValue(BaseType.IDENTIFIER);
	        	baseValue.setSingleValue(new IdentifierValue("INCORRECT"));
	        	setOutcomeValue.setExpression(baseValue);
	        	responseElse.getResponseRules().add(setOutcomeValue);
	        	//feedback
	        	FeedbackBlock feedbackBlock = new FeedbackBlock(itemBody);
	        	feedbackBlock.setOutcomeIdentifier(Identifier.assumedLegal("FEEDBACK"));
	        	feedbackBlock.setIdentifier(Identifier.assumedLegal("INCORRECT"));
	        	feedbackBlock.setVisibilityMode(VisibilityMode.SHOW_IF_MATCH);
	        	P p = new P(feedbackBlock);
	        	p.getInlines().add(new TextRun(p,incorrectFeedback));
	        	feedbackBlock.getBlocks().add(p);
	        	itemBody.getBlocks().add(feedbackBlock);
        	}
        	
        	responseCondition.setResponseElse(responseElse);
        	
        	responseProcessing.getResponseRules().add(responseCondition);
        	
        	
        	
        	//responseProcessing.getResponseRules().add(setOutcomeValue);
        }  
        assessmentItem.setResponseProcessing(responseProcessing);
   
        
        final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
        final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
        String xml = qtiSerializer.serializeJqtiObject(assessmentItem);
		return xml;
	}

	public List<InteractionOption> getInteractionOptions() {
		return interactionOptions;
	}

	public void setInteractionOptions(List<InteractionOption> interactionOptions) {
		this.interactionOptions = interactionOptions;
	}

	public List<String> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(List<String> correctOptions) {
		this.correctOptions = correctOptions;
	}

	public boolean isShuffle() {
		return shuffle;
	}

	public void setShuffle(boolean shuffle) {
		this.shuffle = shuffle;
	}

	public OptionScoreMapping getOptionScoreMapping() {
		return optionScoreMapping;
	}

	public void setOptionScoreMapping(OptionScoreMapping optionScoreMapping) {
		this.optionScoreMapping = optionScoreMapping;
	}

	public String getCorrectFeedback() {
		return correctFeedback;
	}

	public void setCorrectFeedback(String correctFeedback) {
		this.correctFeedback = correctFeedback;
	}

	public String getIncorrectFeedback() {
		return incorrectFeedback;
	}

	public void setIncorrectFeedback(String incorrectFeedback) {
		this.incorrectFeedback = incorrectFeedback;
	}

}
