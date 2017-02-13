/*************************************************
*Test.java
*
*2013-11-20
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.haoyu.aip.qti.entity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import uk.ac.ed.ph.jqtiplus.SimpleJqtiFacade;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.content.variable.RubricBlock;
import uk.ac.ed.ph.jqtiplus.node.content.xhtml.text.P;
import uk.ac.ed.ph.jqtiplus.node.expression.operator.Sum;
import uk.ac.ed.ph.jqtiplus.node.expression.outcome.TestVariables;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.OutcomeDeclaration;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentItemRef;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentSection;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentTest;
import uk.ac.ed.ph.jqtiplus.node.test.NavigationMode;
import uk.ac.ed.ph.jqtiplus.node.test.SubmissionMode;
import uk.ac.ed.ph.jqtiplus.node.test.TestPart;
import uk.ac.ed.ph.jqtiplus.node.test.TimeLimits;
import uk.ac.ed.ph.jqtiplus.node.test.View;
import uk.ac.ed.ph.jqtiplus.node.test.outcome.processing.OutcomeProcessing;
import uk.ac.ed.ph.jqtiplus.node.test.outcome.processing.SetOutcomeValue;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;

/**
 *
 *
 *@author 梁华璜
 */
public class Test extends BaseEntity {
	
	private static final long serialVersionUID = 5552316329298644717L;
	
	private String id;
	/**
	 * 标题
	 */
	private String title;
	//描述
	private String description;

	private String state;
	//作者
	private String author;

	//是否随机
	private String shuffle;
	//关联id集合
	private List<TestDelivery> testDeliveries;
	
	private int timeLimits;
	
	private int maxAttempts;
	
	private double score;
	
	private TestPackage testPackage;
	
	public Test(){}
	
	public Test(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<TestDelivery> getTestDeliveries() {
		return testDeliveries;
	}

	public void setTestDeliveries(List<TestDelivery> testDeliveries) {
		this.testDeliveries = testDeliveries;
	}

	public int getTimeLimits() {
		return timeLimits;
	}

	public void setTimeLimits(int timeLimits) {
		this.timeLimits = timeLimits;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public String getShuffle(){
		return this.shuffle;
	}

	public void setShuffle(String shuffle) {
		this.shuffle = shuffle;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public TestPackage getTestPackage() {
		return testPackage;
	}

	public void setTestPackage(TestPackage testPackage) {
		this.testPackage = testPackage;
	}

	public <T> List<T> randomList(List<T> views, int max) {
	    final int size = views.size();
	    int index = RandomUtils.nextInt(1, size);
	    //
	    List<T> ret = new ArrayList<T>(max);
	    int low = index - 1, high = index;
	    while (max > 0 && (low >= 0 || high < size)) {
	        if (low >= 0 && max-- > 0) {
	            ret.add(views.get(low));
	        }
	        if (high < size && max-- > 0) {
	            ret.add(views.get(high));
	        }
	        low--;
	        high++;
	    }
	    return ret;
	}
	
	public String toXml(List<QuestionPackage> questionPackages){
		final AssessmentTest assessmentTest = new AssessmentTest();
        assessmentTest.setIdentifier(id);
        assessmentTest.setTitle(title);
        if(timeLimits>0){
        	TimeLimits tl = new TimeLimits(assessmentTest);
        	tl.setMaximum(Double.valueOf(timeLimits));
        	assessmentTest.setTimeLimits(tl);
        }
        
        /* Declare a totalScore outcome variable */
        final OutcomeDeclaration totalScore = new OutcomeDeclaration(assessmentTest);
        totalScore.setIdentifier(Identifier.assumedLegal("totalScore"));
        totalScore.setCardinality(Cardinality.SINGLE);
        totalScore.setBaseType(BaseType.FLOAT);
        assessmentTest.getOutcomeDeclarations().add(totalScore);
        
        /* Declare testPart*/
        final TestPart testPart = new TestPart(assessmentTest);
        testPart.setIdentifier(Identifier.assumedLegal("P1"));
        testPart.setNavigationMode(NavigationMode.NONLINEAR);
        testPart.setSubmissionMode(SubmissionMode.INDIVIDUAL);
        assessmentTest.getTestParts().add(testPart);
        
        /*Declare AssessmentSection*/
        AssessmentSection assessmentSection = new AssessmentSection(testPart);
        assessmentSection.setIdentifier(Identifier.assumedLegal("S1"));
        assessmentSection.setVisible(true);
        
        RubricBlock rubricBlock =  new RubricBlock(assessmentSection);
        rubricBlock.setViews(Lists.newArrayList(View.CANDIDATE));
        P p = new P(rubricBlock);
    	p.getInlines().add(new TextRun(p,this.getDescription()));
        rubricBlock.getBlocks().add(p);
        assessmentSection.getRubricBlocks().add(rubricBlock);
        testPart.getAssessmentSections().add(assessmentSection);
        
        
        /*Declare assessmentItemRef*/
        if(questionPackages!=null&&!questionPackages.isEmpty()){
        	for(int i=0;i<questionPackages.size();i++){
        		QuestionPackage qp = questionPackages.get(i);
        		AssessmentItemRef assessmentItemRef = new AssessmentItemRef(assessmentSection);
        		assessmentItemRef.setIdentifier(Identifier.assumedLegal("Q"+(i+1)));        		
        	//	final CustomUriScheme packageUriScheme = QtiContentPackageExtractor.PACKAGE_URI_SCHEME;
        		try {
					assessmentItemRef.setHref(new URI(qp.getQuestionHref()));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
        		assessmentSection.getSectionParts().add(assessmentItemRef);
        	}
        }
        
        /*Declare outcomeProcessing*/
        OutcomeProcessing outcomeProcessing = new OutcomeProcessing(assessmentTest); 
        SetOutcomeValue setOutcomeValue = new SetOutcomeValue(outcomeProcessing);
        setOutcomeValue.setIdentifier(Identifier.assumedLegal("totalScore"));
        
        Sum sum = new Sum(setOutcomeValue);
        TestVariables testVariables = new TestVariables(sum);
        testVariables.setVariableIdentifier(Identifier.assumedLegal("SCORE"));
        sum.getExpressions().add(testVariables);        
        setOutcomeValue.getExpressions().add(sum);
        outcomeProcessing.getOutcomeRules().add(setOutcomeValue);
        
        assessmentTest.setOutcomeProcessing(outcomeProcessing);
        final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
        final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
        String xml = qtiSerializer.serializeJqtiObject(assessmentTest);
		return xml;
	}
	
	public String toXml(){
		final AssessmentTest assessmentTest = new AssessmentTest();
        assessmentTest.setIdentifier(id);
        assessmentTest.setTitle(title);
        if(timeLimits>0){
        	TimeLimits tl = new TimeLimits(assessmentTest);
        	tl.setMaximum(Double.valueOf(timeLimits));
        	assessmentTest.setTimeLimits(tl);
        }
        
        /* Declare a totalScore outcome variable */
        final OutcomeDeclaration totalScore = new OutcomeDeclaration(assessmentTest);
        totalScore.setIdentifier(Identifier.assumedLegal("totalScore"));
        totalScore.setCardinality(Cardinality.SINGLE);
        totalScore.setBaseType(BaseType.FLOAT);
        assessmentTest.getOutcomeDeclarations().add(totalScore);
        
        /* Declare testPart*/
        final TestPart testPart = new TestPart(assessmentTest);
        testPart.setIdentifier(Identifier.assumedLegal("P1"));
        testPart.setNavigationMode(NavigationMode.NONLINEAR);
        testPart.setSubmissionMode(SubmissionMode.INDIVIDUAL);
        assessmentTest.getTestParts().add(testPart);
        
        /*Declare AssessmentSection*/
        AssessmentSection assessmentSection = new AssessmentSection(testPart);
        assessmentSection.setIdentifier(Identifier.assumedLegal("S1"));
        assessmentSection.setVisible(true);
        
        RubricBlock rubricBlock =  new RubricBlock(assessmentSection);
        rubricBlock.setViews(Lists.newArrayList(View.CANDIDATE));
        P p = new P(rubricBlock);
    	p.getInlines().add(new TextRun(p,this.getDescription()));
        rubricBlock.getBlocks().add(p);
        assessmentSection.getRubricBlocks().add(rubricBlock);
        testPart.getAssessmentSections().add(assessmentSection);
        
        
        /*Declare outcomeProcessing*/
        OutcomeProcessing outcomeProcessing = new OutcomeProcessing(assessmentTest); 
        SetOutcomeValue setOutcomeValue = new SetOutcomeValue(outcomeProcessing);
        setOutcomeValue.setIdentifier(Identifier.assumedLegal("totalScore"));
        
        Sum sum = new Sum(setOutcomeValue);
        TestVariables testVariables = new TestVariables(sum);
        testVariables.setVariableIdentifier(Identifier.assumedLegal("SCORE"));
        sum.getExpressions().add(testVariables);        
        setOutcomeValue.getExpressions().add(sum);
        outcomeProcessing.getOutcomeRules().add(setOutcomeValue);
        
        assessmentTest.setOutcomeProcessing(outcomeProcessing);
        final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
        final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
        String xml = qtiSerializer.serializeJqtiObject(assessmentTest);
		return xml;
	}
}
