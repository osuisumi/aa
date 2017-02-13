/**
 * 
 */
package com.haoyu.aip.qti.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.ac.ed.ph.jqtiplus.SimpleJqtiFacade;
import uk.ac.ed.ph.jqtiplus.node.RootNode;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.content.variable.RubricBlock;
import uk.ac.ed.ph.jqtiplus.node.content.xhtml.text.P;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.outcome.declaration.OutcomeDeclaration;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentItemRef;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentSection;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentTest;
import uk.ac.ed.ph.jqtiplus.node.test.ItemSessionControl;
import uk.ac.ed.ph.jqtiplus.node.test.SectionPart;
import uk.ac.ed.ph.jqtiplus.node.test.TestPart;
import uk.ac.ed.ph.jqtiplus.node.test.TimeLimits;
import uk.ac.ed.ph.jqtiplus.node.test.View;
import uk.ac.ed.ph.jqtiplus.resolution.ResolvedAssessmentItem;
import uk.ac.ed.ph.jqtiplus.resolution.ResolvedAssessmentTest;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.SingleValue;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.FileResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ResourceLocator;

import com.google.common.collect.Lists;
import com.haoyu.aip.qti.dao.ITestDao;
import com.haoyu.aip.qti.dao.ITestDeliveryDao;
import com.haoyu.aip.qti.dao.ITestPackageDao;
import com.haoyu.aip.qti.entity.InteractionOption;
import com.haoyu.aip.qti.entity.MultipleChoiceQuestion;
import com.haoyu.aip.qti.entity.Question;
import com.haoyu.aip.qti.entity.QuestionFormKey;
import com.haoyu.aip.qti.entity.QuestionPackage;
import com.haoyu.aip.qti.entity.QuestionType;
import com.haoyu.aip.qti.entity.SingleChoiceQuestion;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.entity.TrueFalseQuestion;
import com.haoyu.aip.qti.service.IQuestionService;
import com.haoyu.aip.qti.service.ITestService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class TestServiceImpl implements ITestService {
	@Resource
	private ITestDao testDao;
	@Resource
	private ITestPackageDao testPackageDao;
	@Resource
	private IQuestionService questionService;
	
	@Resource
	private ITestDeliveryDao testDeliveryDao;
	@Value("#{commonConfig['qti.file.system.base']}")
	private String fileSystemBase;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestService#createTest(com.haoyu.aip.qti.entity.Test)
	 */
	@Override
	public Response createTest(Test test) {
		if(test==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		if(StringUtils.isEmpty(test.getId())){
			test.setId(Identities.uuid2());
		}
		TestPackage testPackage = new TestPackage();
		testPackage.setId(Identities.uuid2());
		testPackage.setSandboxPath(this.getSandboxPath(test.getId()));
		testPackage.setTestHref("assessmentTest.xml");
		testPackage.setFileName("assessmentTest.xml");
		int count = testPackageDao.insertTestPackage(testPackage);
		if(count>0){
			try {
				FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), test.toXml(),"UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		test.setTestPackage(testPackage);
		count = testDao.insertTest(test);
		if(count>0 && test.getTestDeliveries()!=null&&!test.getTestDeliveries().isEmpty()){
			for(TestDelivery testDelivery:test.getTestDeliveries()){
				if(testDelivery.getId()==null||StringUtils.isEmpty(testDelivery.getId())){
					testDelivery.setId(Identities.uuid2());
				}
				testDelivery.setTest(test);
				count=testDeliveryDao.insertTestDelivery(testDelivery);
			}
		}
		return count>0?Response.successInstance().responseData(test):Response.failInstance().responseMsg("create test fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestService#addTestQuestion(com.haoyu.aip.qti.entity.Test, com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public Response addTestQuestion(Test test, Question question,QuestionFormKey questionFormKey) {
		if(question==null){
			return Response.failInstance().responseMsg("question object is null");
		}
		if(test==null||test.getId()==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		Test testResult = testDao.selectTestById(test.getId());
		double score = 0;
		//TestPackage testPackage = testPackageDao.selectTestPackageById(test.getTestPackage().getId());
		TestPackage testPackage = testResult.getTestPackage();
		if(testPackage==null)
			 return Response.failInstance().responseData("testPackage is null");
		//先插入question
		Response response = questionService.createTestQuestion(testPackage,question);
		if(response.isSuccess()){
			 final ResourceLocator inputResourceLocator = new FileResourceLocator();
		     final StringBuffer path = new StringBuffer("");
		     path.append(testPackage.getSandboxPath()).append("/").append(testPackage.getTestHref());
		     File file = new File(path.toString());
		     final URI inputUri = file.toURI();
			 final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		     final ResolvedAssessmentTest rat = simpleJqtiFacade.loadAndResolveAssessmentTest(inputResourceLocator, inputUri);
		     AssessmentTest assessmentTest = rat.getRootNodeLookup().extractIfSuccessful();
		     List<AssessmentSection> assessmentSections = assessmentTest.getTestPart(Identifier.assumedLegal(questionFormKey.getTestPartId())).getAssessmentSections();
		     if(assessmentSections!=null&&!assessmentSections.isEmpty()){
		    	 for(AssessmentSection assessmentSection:assessmentSections){
		    		 if(assessmentSection.getIdentifier().equals(Identifier.assumedLegal(questionFormKey.getAssessmentSectionId()))){
		    			 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
		    			 AssessmentItemRef assessmentItemRef = new AssessmentItemRef(assessmentSection);
		         		 assessmentItemRef.setIdentifier(Identifier.assumedLegal("Q"+(questionFormKey.getIndex()+1)));  
		         		 assessmentItemRef.setHref(URI.create(question.getQuestionPackage().getQuestionHref()));
		         		 //设置题目提交次数为不限制
		         		 ItemSessionControl itemSessionControl =  new ItemSessionControl(assessmentItemRef);
		         		 itemSessionControl.setMaxAttempts(0);
		         		 assessmentItemRef.setItemSessionControl(itemSessionControl);
		    			 sectionParts.add(questionFormKey.getIndex(), assessmentItemRef);
		    			 for(int i=(questionFormKey.getIndex()+1);i<sectionParts.size();i++){
		    				 SectionPart sp = sectionParts.get(i);
		    				 sp.setIdentifier(Identifier.assumedLegal("Q"+(i+1))); 
		    			 } 
		    		 }
		    		 //统计测验总分
		    		 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
		    		 if(sectionParts!=null&&!sectionParts.isEmpty()){
		    			 for(SectionPart sectionPart:sectionParts){
		    				 if(sectionPart instanceof AssessmentItemRef){
		    					 score = getItemScore(score, testPackage,
										simpleJqtiFacade, assessmentTest,
										sectionPart);
		    				 }
		    			 }
		    		 }
		    		 
		    	 }
		     }
		    final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
		    String xml = qtiSerializer.serializeJqtiObject(assessmentTest);		   
		    try {
				FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), xml,Charset.forName("UTF-8"));
				testResult.setScore(score);
				testDao.updateTest(testResult);
				return Response.successInstance().responseData(question);
		    } catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return Response.failInstance().responseMsg("create test question fail!");
	}
	
	@Override
	public Response importTestQuestions(Test test, String questionsText,
			QuestionFormKey questionFormKey) {
		if(test==null||test.getId()==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		Test testResult = testDao.selectTestById(test.getId());
		double score = 0;
		//TestPackage testPackage = testPackageDao.selectTestPackageById(test.getTestPackage().getId());
		TestPackage testPackage = testResult.getTestPackage();
		if(testPackage==null)
			 return Response.failInstance().responseData("testPackage is null");
		List<Question> questions = parseQuestions(questionsText);
		StringBuffer invalidQuestionMsg = new StringBuffer();
		if(questions!=null&&!questions.isEmpty()){
			 List<Question> validQuestions = Lists.newArrayList();
			 for(int i=0;i<questions.size();i++){
				 Question question = questions.get(i);
				 if(question.getValidateErrorInfos()!=null||question.isValid()){
					 if(question.getScore()==0){
						 question.setScore(1d);
					 }
					 Response response = questionService.createTestQuestion(testPackage,question);
					 if(response.isSuccess()){
						 validQuestions.add(question);
					 }
				 }else{
					 invalidQuestionMsg.append("第"+(i+1)+"题"+question.getValidateErrorInfos().toString()+"<br/>");
				 }
			 }
			 if(!validQuestions.isEmpty()){
					 final ResourceLocator inputResourceLocator = new FileResourceLocator();
				     final StringBuffer path = new StringBuffer("");
				     path.append(testPackage.getSandboxPath()).append("/").append(testPackage.getTestHref());
				     File file = new File(path.toString());
				     final URI inputUri = file.toURI();
					 final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
				     final ResolvedAssessmentTest rat = simpleJqtiFacade.loadAndResolveAssessmentTest(inputResourceLocator, inputUri);
				     AssessmentTest assessmentTest = rat.getRootNodeLookup().extractIfSuccessful();
				     List<AssessmentSection> assessmentSections = assessmentTest.getTestPart(Identifier.assumedLegal(questionFormKey.getTestPartId())).getAssessmentSections();
				     if(assessmentSections!=null&&!assessmentSections.isEmpty()){
				    	 for(AssessmentSection assessmentSection:assessmentSections){
				    		 if(assessmentSection.getIdentifier().equals(Identifier.assumedLegal(questionFormKey.getAssessmentSectionId()))){
				    			 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
				    			 for(int j=0;j<validQuestions.size();j++){
					    			 AssessmentItemRef assessmentItemRef = new AssessmentItemRef(assessmentSection);
					         		 assessmentItemRef.setIdentifier(Identifier.assumedLegal("Q"+(questionFormKey.getIndex()+j+1)));  
					         		 assessmentItemRef.setHref(URI.create(validQuestions.get(j).getQuestionPackage().getQuestionHref()));
					         		 //设置题目提交次数为不限制
					         		 ItemSessionControl itemSessionControl =  new ItemSessionControl(assessmentItemRef);
					         		 itemSessionControl.setMaxAttempts(0);
					         		 assessmentItemRef.setItemSessionControl(itemSessionControl);
					    			 sectionParts.add(questionFormKey.getIndex()+j, assessmentItemRef);
				    			 }
				    			 for(int i=(questionFormKey.getIndex()+1);i<sectionParts.size();i++){
				    				 SectionPart sp = sectionParts.get(i);
				    				 sp.setIdentifier(Identifier.assumedLegal("Q"+(i+1))); 
				    			 } 
				    		 }
				    		 //统计测验总分
				    		 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
				    		 if(sectionParts!=null&&!sectionParts.isEmpty()){
				    			 for(SectionPart sectionPart:sectionParts){
				    				 if(sectionPart instanceof AssessmentItemRef){
				    					 score = getItemScore(score, testPackage,
												simpleJqtiFacade, assessmentTest,
												sectionPart);
				    				 }
				    			 }
				    		 }
				    		 
				    	 }
				     }
				    final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
				    String xml = qtiSerializer.serializeJqtiObject(assessmentTest);		   
				    try {
						FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), xml,Charset.forName("UTF-8"));
						testResult.setScore(score);
						testDao.updateTest(testResult);
						return Response.successInstance().responseData(validQuestions).responseMsg(invalidQuestionMsg.toString());
				    } catch (IOException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
			 }
		}
		return Response.failInstance().responseMsg(invalidQuestionMsg.toString());
	}

	/**
	 * @param score
	 * @param testPackage
	 * @param simpleJqtiFacade
	 * @param assessmentTest
	 * @param sectionPart
	 * @return
	 */
	private double getItemScore(double score, TestPackage testPackage,
			final SimpleJqtiFacade simpleJqtiFacade,
			AssessmentTest assessmentTest, SectionPart sectionPart) {
		 AssessmentItemRef assessmentItemRef = (AssessmentItemRef)sectionPart;	
		 final URI itemHref = assessmentItemRef.getHref();
		 if (itemHref!=null) {
			 final ResourceLocator inputResourceLocator = new FileResourceLocator();
		     final StringBuffer path = new StringBuffer("");
		     path.append(testPackage.getSandboxPath()).append("/").append(itemHref);
		     File file = new File(path.toString());
		     final URI inputUri = file.toURI();
		     final ResolvedAssessmentItem rat = simpleJqtiFacade.loadAndResolveAssessmentItem(inputResourceLocator, inputUri);
/*		     
			 URI itemSystemId = resolveUri(assessmentTest, itemHref);
			 File sandboxDirectory = new File(testPackage.getSandboxPath());
		     CustomUriScheme packageUriScheme = QtiContentPackageExtractor.PACKAGE_URI_SCHEME;
		     FileSandboxResourceLocator fileSandboxResourceLocator = new FileSandboxResourceLocator(packageUriScheme, sandboxDirectory);
			 ResolvedAssessmentItem ra =simpleJqtiFacade.loadAndResolveAssessmentItem(fileSandboxResourceLocator, itemSystemId);*/
			 AssessmentItem assessmentItem = rat.getRootNodeLookup().extractIfSuccessful();
			 OutcomeDeclaration scoreOutcomeDeclaration = assessmentItem.getOutcomeDeclaration(Identifier.assumedLegal("SCORE"));
			 if(scoreOutcomeDeclaration!=null){
					SingleValue singleValue = scoreOutcomeDeclaration.getDefaultValue().getFieldValues().get(0).getSingleValue();
					score+=((FloatValue)singleValue).doubleValue();
			}
		 }
		return score;
	}
	

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestService#addTestQuestions(com.haoyu.aip.qti.entity.Test, java.util.List)
	 */
	@Override
	public Response addTestQuestions(Test test,
			List<QuestionPackage> questionPackages) {
		if(test==null||test.getId()==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.ITestService#removeTestQuestion(com.haoyu.aip.qti.entity.Test, com.haoyu.aip.qti.entity.QuestionPackage)
	 */
	@Override
	public Response removeTestQuestion(Test test,QuestionFormKey questionFormKey) {
		if(test==null||test.getId()==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		Test testResult = testDao.selectTestById(test.getId());
		double score = 0;
		TestPackage testPackage = testResult.getTestPackage();
		if(testPackage==null)
			 return Response.failInstance().responseData("testPackage is null");
		
		 final ResourceLocator inputResourceLocator = new FileResourceLocator();
		 final StringBuffer path = new StringBuffer("");
	     path.append(testPackage.getSandboxPath()).append("/").append(testPackage.getTestHref());
	     File file = new File(path.toString());
	     final URI inputUri = file.toURI();
		 final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
	     final ResolvedAssessmentTest rat = simpleJqtiFacade.loadAndResolveAssessmentTest(inputResourceLocator, inputUri);
	     AssessmentTest assessmentTest = rat.getRootNodeLookup().extractIfSuccessful();
	     List<AssessmentSection> assessmentSections = assessmentTest.getTestPart(Identifier.assumedLegal(questionFormKey.getTestPartId())).getAssessmentSections();
	     if(assessmentSections!=null&&!assessmentSections.isEmpty()){
	    	 for(AssessmentSection assessmentSection:assessmentSections){
	    		 if(assessmentSection.getIdentifier().equals(Identifier.assumedLegal(questionFormKey.getAssessmentSectionId()))){
	    			 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
	         		 sectionParts.remove(questionFormKey.getIndex());
	    			 for(int i=(questionFormKey.getIndex());i<sectionParts.size();i++){
	    				 SectionPart sp = sectionParts.get(i);
	    				 sp.setIdentifier(Identifier.assumedLegal("Q"+(i+1)));
	    			 }	    			
	    		 }
	    		 //统计测验总分
	    		 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
	    		 if(sectionParts!=null&&!sectionParts.isEmpty()){
	    			 for(SectionPart sectionPart:sectionParts){
	    				 if(sectionPart instanceof AssessmentItemRef){
	    					 score = getItemScore(score, testPackage,
									simpleJqtiFacade, assessmentTest,
									sectionPart);
	    				 }
	    			 }
	    		 }
	    	 }
	     }
	    final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
	    String xml = qtiSerializer.serializeJqtiObject(assessmentTest);		   
	    try {
			FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), xml,Charset.forName("UTF-8"));
			testResult.setScore(score);
			testDao.updateTest(testResult);
			return Response.successInstance().responseMsg("删除成功!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private String getSandboxPath(String testId){
		StringBuffer sandboxPath = new StringBuffer(fileSystemBase);
		sandboxPath.append("/").append(ThreadContext.getUser().getId());
		sandboxPath.append("/").append(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd"));
		sandboxPath.append("/").append("test").append("/").append(testId);
		return sandboxPath.toString();
	}

	@Override
	public Test findTestById(String id) {
		return testDao.selectTestById(id);
	}

	@Override
	public Response updateTest(Test test) {
		if(test==null||test.getId()==null){
			return Response.failInstance().responseMsg("test object is null");
		}
		int count = testDao.updateTest(test);
		if(count>0){
			Test testResult = testDao.selectTestById(test.getId());
			TestPackage testPackage = testResult.getTestPackage();
			if(testPackage==null)
				 return Response.failInstance().responseData("testPackage is null");
			 final ResourceLocator inputResourceLocator = new FileResourceLocator();
		     final StringBuffer path = new StringBuffer("");
		     path.append(testPackage.getSandboxPath()).append("/").append(testPackage.getTestHref());
		     File file = new File(path.toString());
		     final URI inputUri = file.toURI();
			 final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		     final ResolvedAssessmentTest rat = simpleJqtiFacade.loadAndResolveAssessmentTest(inputResourceLocator, inputUri);
		     AssessmentTest assessmentTest = rat.getRootNodeLookup().extractIfSuccessful();
		     
		     assessmentTest.setTitle(test.getTitle());
		     
		     TimeLimits tl = new TimeLimits(assessmentTest);
	     	 tl.setMaximum(Double.valueOf(test.getTimeLimits()));
	     	 assessmentTest.setTimeLimits(tl);
	     	 
	     	 List<AssessmentSection> assessmentSections = assessmentTest.getTestParts().get(0).getAssessmentSections();
	     	 
	     	 RubricBlock rubricBlock =  new RubricBlock(assessmentSections.get(0));
	         rubricBlock.setViews(Lists.newArrayList(View.CANDIDATE));
	         P p = new P(rubricBlock);
	     	 p.getInlines().add(new TextRun(p,test.getDescription()));
	         rubricBlock.getBlocks().add(p);
	         assessmentSections.get(0).getRubricBlocks().remove(0);
	         assessmentSections.get(0).getRubricBlocks().add(rubricBlock);
		     
		    final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
		    String xml = qtiSerializer.serializeJqtiObject(assessmentTest);		   
		    try {
				FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), xml,Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		    return Response.successInstance().responseData(test);
		}
		return Response.failInstance();
	}
	
	@Override
	public List<Question> findQuestionsByTestId(String testId) {
		Test test = this.findTestById(testId);
		TestPackage testPackage = test.getTestPackage();
		List<Question> questions = Lists.newArrayList();
		ResourceLocator inputResourceLocator = new FileResourceLocator();
		StringBuffer path = new StringBuffer("");
		path.append(testPackage.getSandboxPath()).append("/")
				.append(testPackage.getTestHref());
		File file = new File(path.toString());
		URI inputUri = file.toURI();
		SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		ResolvedAssessmentTest rat = simpleJqtiFacade
				.loadAndResolveAssessmentTest(inputResourceLocator,
						inputUri);
		AssessmentTest assessmentTest = rat.getRootNodeLookup()
				.extractIfSuccessful();
		List<TestPart> testParts = assessmentTest.getTestParts();
		for(TestPart testPart:testParts){
		    List<AssessmentSection> assessmentSections = testPart.getAssessmentSections();
		    if(assessmentSections!=null&&!assessmentSections.isEmpty()){
		    	 for(AssessmentSection assessmentSection:assessmentSections){
		    			 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
		    			 if(sectionParts!=null&&!sectionParts.isEmpty()){
			    			 for(SectionPart sectionPart:sectionParts){
			    				 if(sectionPart instanceof AssessmentItemRef){
			    					 AssessmentItemRef assessmentItemRef = (AssessmentItemRef)sectionPart;					    					 
			    					 final URI itemHref = assessmentItemRef.getHref();
			    		             if (itemHref!=null) {
								         inputResourceLocator = new FileResourceLocator();
										 path = new StringBuffer("");
										 path.append(testPackage.getSandboxPath()).append("/")
													.append(assessmentItemRef.getHref());
										 file = new File(path.toString());
										 inputUri = file.toURI();
								         
										 ResolvedAssessmentItem ra =simpleJqtiFacade.loadAndResolveAssessmentItem(inputResourceLocator, inputUri);
										 AssessmentItem assessmentItem = ra.getRootNodeLookup().extractIfSuccessful();
										 QuestionType questionType  =  Question.getQuesType(assessmentItem);
										 switch(questionType){
													case SINGLE_CHOICE:
														questions.add(new SingleChoiceQuestion(assessmentItem));
														break;
													case TRUE_FALSE:
														questions.add(new TrueFalseQuestion(assessmentItem));
														break;
													case MULTIPLE_CHOICE:
														questions.add(new MultipleChoiceQuestion(assessmentItem));
														break;
													default:
														break;
											}
											
					    				 }
			    		             }
			    			 }
		    			 }
		    	 }
		     }
		}
		return questions;
	}

	
	
	private List<Question> parseQuestions(String questionsText){
		String[] strArray = questionsText.split("\\s\\n|\\n");
		Pattern questionTitlePattern = Pattern.compile("^([0-9]+[、]{1}.*\\s*)|(.*(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]*))");
		Pattern correctOptionPattern = Pattern.compile("^正确答案：[0-9]+");
		Pattern scorePattern = Pattern.compile("^分值：[0-9]+(.[0-9]{1,3})?");
		List<Question>	questions = Lists.newArrayList();	
		Question question = null;		
		for(int i=0;i<strArray.length;i++){
			if(StringUtils.isEmpty(strArray[i]))
				continue;
			QuestionType questionType = null;
			if(i==0){
				questionType = Question.getQuesType(strArray[0]);	
				question = newQuestionInstance(questionType,strArray[0]);
				question.setTitle(strArray[0].replaceFirst("[1-9][、]{1}|(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])", ""));
				question.setText(question.getTitle());
				questions.add(question);
			}else{
				Matcher matcher = questionTitlePattern.matcher(strArray[i]);	
				if(matcher.find()){
					questionType = Question.getQuesType(strArray[i]);		
					question = newQuestionInstance(questionType,strArray[i]);
					question.setTitle(strArray[i].replaceFirst("[1-9][、]{1}|(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])", ""));
					question.setText(question.getTitle());
					questions.add(question);
				}else{
					matcher = correctOptionPattern.matcher(strArray[i]);
					if(matcher.find()){
						setCorrectOptions(question,strArray[i].replace("正确答案：", "").trim());
					}else{
						matcher = scorePattern.matcher(strArray[i]);
						if(matcher.find()){
							question.setScore(Double.valueOf(matcher.group().replace("分值：","").trim()));
						}else if(Pattern.compile("^分值：-[0-9]+(.[0-9]{1,3})?").matcher(strArray[i]).find()){
							question.setValidateErrorInfos(Lists.newArrayList("分值不能为负数!"));;
						}else if(StringUtils.isNotEmpty(strArray[i].trim())){
							setInteractionOption(question,strArray[i]);
						}
					}
				}
			}	
		}
		return questions;
	}
	
	private Question newQuestionInstance(QuestionType questionType,String questionTitle){
		Question question = null;
		switch(questionType){
			case SINGLE_CHOICE:
				question = new SingleChoiceQuestion();
				break;
			case TRUE_FALSE:
				question =  new TrueFalseQuestion();
				break;
			case MULTIPLE_CHOICE:
				question =  new MultipleChoiceQuestion();
				break;
			default:
				break;
		 }
		if(question!=null){
			question.setId(Identities.uuid2());
			question.setTitle(questionTitle.replaceFirst("[1-9][、]{1}|(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])", ""));
			question.setText(question.getText());
		}
		return question;
	}
	
	private void setInteractionOption(Question question,String text){
		if(question!=null){
			switch(question.getQuesType()){
				case SINGLE_CHOICE:{
					List<InteractionOption> interactionOptions = ((SingleChoiceQuestion)question).getInteractionOptions();
					interactionOptions.add(new InteractionOption("Choice"+interactionOptions.size(),text.trim()));
					break;
				}
				case TRUE_FALSE:{
					List<InteractionOption> interactionOptions = ((TrueFalseQuestion)question).getInteractionOptions();
					interactionOptions.add(new InteractionOption("Choice"+interactionOptions.size(),text.trim()));
					break;
				}
				case MULTIPLE_CHOICE:{
					List<InteractionOption> interactionOptions = ((MultipleChoiceQuestion)question).getInteractionOptions();
					interactionOptions.add(new InteractionOption("Choice"+interactionOptions.size(),text.trim()));
					break;
				}
				default:
					break;
			}
		}
	}
	
	private void setCorrectOptions(Question question,String correctText){
		if(question!=null){
			switch(question.getQuesType()){
				case SINGLE_CHOICE:{
					Pattern correctOptionPattern = Pattern.compile("[1-"+((SingleChoiceQuestion)question).getInteractionOptions().size()+"]{1}");
					Matcher matcher = correctOptionPattern.matcher(correctText);	
					if(matcher.find()){
						((SingleChoiceQuestion)question).setCorrectOption("Choice"+(Integer.valueOf(matcher.group())-1));
					}
					break;
				}
				case TRUE_FALSE:{
					Pattern correctOptionPattern = Pattern.compile("[1-"+((TrueFalseQuestion)question).getInteractionOptions().size()+"]{1}");
					Matcher matcher = correctOptionPattern.matcher(correctText);	
					if(matcher.find()){
						((TrueFalseQuestion)question).setCorrectOption("Choice"+(Integer.valueOf(matcher.group())-1));
					}
					break;
				}
				case MULTIPLE_CHOICE:{								
					List<String> correctOptions = ((MultipleChoiceQuestion)question).getCorrectOptions();
					Pattern correctOptionPattern = Pattern.compile("[1-"+((MultipleChoiceQuestion)question).getInteractionOptions().size()+"]+、?");
					Matcher matcher = correctOptionPattern.matcher(correctText);	
					while(matcher.find()){
						if(!correctOptions.contains(matcher.group().replace("、", ""))){
							correctOptions.add("Choice"+(Integer.valueOf(matcher.group().replace("、", ""))-1));
						}
					}
					break;
				}
				default:
					break;
			}
		}
		
	}

	@Override
	public Response updateTestQuestionSequence(Test test,
			QuestionFormKey targetQfk, QuestionFormKey sourceQfk) {
		Test testResult = testDao.selectTestById(test.getId());
		TestPackage testPackage = testResult.getTestPackage();
		if(testPackage==null)
			 return Response.failInstance().responseData("testPackage is null");
		final ResourceLocator inputResourceLocator = new FileResourceLocator();
		final StringBuffer path = new StringBuffer("");
		path.append(testPackage.getSandboxPath()).append("/").append(testPackage.getTestHref());
		File file = new File(path.toString());
		final URI inputUri = file.toURI();
		final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		final ResolvedAssessmentTest rat = simpleJqtiFacade.loadAndResolveAssessmentTest(inputResourceLocator, inputUri);
		AssessmentTest assessmentTest = rat.getRootNodeLookup().extractIfSuccessful();
		
		if(targetQfk.sameAssessmentSection(sourceQfk)){
			 List<AssessmentSection> assessmentSections = assessmentTest.getTestPart(Identifier.assumedLegal(targetQfk.getTestPartId())).getAssessmentSections();
		     if(assessmentSections!=null&&!assessmentSections.isEmpty()){
		    	 for(AssessmentSection assessmentSection:assessmentSections){
		    		 if(assessmentSection.getIdentifier().equals(Identifier.assumedLegal(targetQfk.getAssessmentSectionId()))){
		    			 List<SectionPart> sectionParts= assessmentSection.getSectionParts();
		    			 SectionPart sourceSp  =sectionParts.get(sourceQfk.getIndex());
		    			 sectionParts.remove(sourceQfk.getIndex());
		    			 
		    			 sourceSp.setIdentifier(Identifier.assumedLegal("Q"+(targetQfk.getIndex()+1)));
		    			 sectionParts.add(targetQfk.getIndex(),sourceSp);
		    			 for(int i=(targetQfk.getIndex()+1);i<sectionParts.size();i++){
		    				 SectionPart sp = sectionParts.get(i);
		    				 sp.setIdentifier(Identifier.assumedLegal("Q"+(i+1))); 
		    			 } 
		    		 }
		    		 
		    	 }
		     }
		}else{
			//TODO 需要增加不同testPart与不同AssessmentSection的处理
		}
		final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
	    String xml = qtiSerializer.serializeJqtiObject(assessmentTest);		   
	    try {
			FileUtils.writeStringToFile(new File(testPackage.getSandboxPath()+"/"+testPackage.getTestHref()), xml,Charset.forName("UTF-8"));
			return Response.successInstance().responseData(testPackage);
	    } catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
