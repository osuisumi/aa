/**
 * 
 */
package com.haoyu.aip.qti;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;
import com.haoyu.aip.qti.entity.InteractionOption;
import com.haoyu.aip.qti.entity.MultipleChoiceQuestion;
import com.haoyu.aip.qti.entity.OptionScoreMapping;
import com.haoyu.aip.qti.entity.Question;
import com.haoyu.aip.qti.entity.QuestionPackage;
import com.haoyu.aip.qti.entity.QuestionType;
import com.haoyu.aip.qti.entity.SingleChoiceQuestion;
import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TrueFalseQuestion;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testSinleChoiceQuestion();
		//testTrueFalseQuestion();
		//testMultipleChoiceQuestion();
		//testTest();
		//System.out.println(QuestionType.SINGLE_CHOICE);
		testRegex();
	}
	
	public static void testSinleChoiceQuestion(){
		SingleChoiceQuestion scq = new SingleChoiceQuestion();
		scq.setId(Identities.uuid2());
		scq.setTitle("测试单选题");
		scq.setText("中国的首都在哪里?");
		scq.setAuthor("tester");
		scq.setScore(1);
		scq.setCorrectFeedback("回答正确");
		scq.setIncorrectFeedback("回答错误");
		List<InteractionOption> ios = Lists.newArrayList();
		ios.add(new InteractionOption("ChoiceA",true,"南京"));
		ios.add(new InteractionOption("ChoiceB","北京"));
		ios.add(new InteractionOption("ChoiceC","重庆"));
		ios.add(new InteractionOption("ChoiceD","广州"));
		scq.setInteractionOptions(ios);
		scq.setCorrectOption("ChoiceB");
		scq.setShuffle(false);
		System.out.println(scq.toXml());
	}
	
	public static void testTrueFalseQuestion(){
		
		TrueFalseQuestion scq = new TrueFalseQuestion();
		scq.setId(Identities.uuid2());
		scq.setTitle("测试单选题");
		scq.setText("中国的首都在哪里?");
		scq.setAuthor("tester");
		scq.setScore(12);
		List<InteractionOption> ios = Lists.newArrayList();
		ios.add(new InteractionOption("ChoiceA",true,"南京"));
		ios.add(new InteractionOption("ChoiceB","北京"));
		ios.add(new InteractionOption("ChoiceC","重庆"));
		ios.add(new InteractionOption("ChoiceD","广州"));
		scq.setInteractionOptions(ios);
		scq.setCorrectOption("ChoiceB");
		scq.setShuffle(false);
		boolean isValid = scq.isValid();
		System.out.println(isValid);
		System.out.println(scq.getValidateErrorInfos());
		//scq.toXml();
	}
	
	public static void testMultipleChoiceQuestion(){
		MultipleChoiceQuestion scq = new MultipleChoiceQuestion();
		scq.setId(Identities.uuid2());
		scq.setTitle("测试单选题");
		scq.setText("中国四大火炉在哪里?");
		scq.setAuthor("tester");
		scq.setScore(2);
		
		OptionScoreMapping optionScoreMapping = new OptionScoreMapping();
		optionScoreMapping.setLowerBound(0);
		optionScoreMapping.setUpperBound(2);
		optionScoreMapping.setDefaultValue(0);
		optionScoreMapping.getMapEntries().put("ChoiceA", 1d);
		optionScoreMapping.getMapEntries().put("ChoiceB", -1d);
		optionScoreMapping.getMapEntries().put("ChoiceD", 1d);
		scq.setOptionScoreMapping(optionScoreMapping);
		
		List<InteractionOption> ios = Lists.newArrayList();
		ios.add(new InteractionOption("ChoiceA",true,"南京"));
		ios.add(new InteractionOption("ChoiceB","北京"));
		ios.add(new InteractionOption("ChoiceC","重庆"));
		ios.add(new InteractionOption("ChoiceD","广州"));
		scq.setInteractionOptions(ios);
		List<String> options = Lists.newArrayList("ChoiceA","ChoiceD");
		scq.setCorrectOptions(options);
		scq.setShuffle(false);
		System.out.println(scq.toXml());
	}
	
	public static void testTest(){
		Test test = new Test();
		test.setId(Identities.uuid2());
		test.setTitle("测验");
		test.setDescription("请按照要求完成下面的题目");
		test.setTimeLimits(60);
		
/*		List<QuestionPackage> qps = Lists.newArrayList();
		QuestionPackage qp = new QuestionPackage();
		qp.setQuestionHref("qti1.xml");
		qp.setSandboxPath("d:/qtiworks");
		qps.add(qp);
		qp = new QuestionPackage();
		qp.setQuestionHref("qti2.xml");
		qp.setSandboxPath("d:/qtiworks");
		qps.add(qp);*/

		System.out.println(test.toXml());
	}
	
	
	public static void testQuestions(){
		try {
			String  str=FileUtils.readFileToString(new File("d:/test.txt"),"GBK");
			//String regxSpace = "\\n[\\s]*\\n+";
		//	String regxSpace = "\\n[\\s]+\\n";
			String regxSpace = "(\\S*\\s\\n)|(\\n[\\s]+\\n)|([1-9]{1}[、]{1}.*(\\n|\\s\\n))|(.*(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]+\\n))";
			String[] array = str.split(regxSpace);
			for(String st:array){
				
				System.out.println(st);
				
				String[] arr = st.split("([1-9]{1}[、]{1}.*(\\n|\\s\\n))");
				
			//	Pattern pattern = Pattern.compile("(\\n[1-9]{1}[、]{1})|(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]+\\n)");
			//	Pattern pattern = Pattern.compile("(\\n[^1-9]{1}[^、]{1}.*)|(\\n[1-9]{1}[^、].*)");
				Pattern pattern = Pattern.compile("([1-9]{1}[、]{1}.*(\\n|\\s\\n))|(.*(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]+\\n))");
				Matcher matcher = pattern.matcher(st);
				int count=0;
				while(matcher.find()){
				//	System.out.println("==="+matcher.group());
					count++;
				}
			//	System.out.println(count);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testQues(){
		try {
			String  fileString=FileUtils.readFileToString(new File("d:/test.txt"),"GBK");
			String[] strArray = fileString.split("\\s\\n|\\n");
			String questTitleRegx ="([1-9][、]{1}.*\\s*)|(.*(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]*))"; 
			Pattern pattern = Pattern.compile(questTitleRegx);
			List<Question>	questions = Lists.newArrayList();	
			Question question = null;
			for(int i=0;i<strArray.length;i++){
				if(i==0){
					question = new Question();
					question.setTitle(strArray[0].replaceFirst("[1-9][、]{1}|((\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])", ""));
					question.setText(question.getText());
					System.out.println(question.getTitle());
				}else{
					Matcher matcher = pattern.matcher(strArray[i]);	
					if(matcher.find()){
						System.out.println(strArray[i]);
					}
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	public static void testRegex(){
		/*Pattern correctOptionPattern = Pattern.compile("^([0-9]+[、]{1}.*\\s*)|(.*(\\[单选题\\]|\\[多选题\\]|\\[是非题\\]|\\[填空题\\])([\\s]*))");
		//Matcher matcher = correctOptionPattern.matcher("正确答案：1、2、3");
		Matcher matcher = correctOptionPattern.matcher("2、您的爱好是 ");
		if(matcher.find()){
			System.out.println(matcher.group());
		}*/
		List<String> lists = Lists.newArrayList("1","3","4");
		String a = lists.get(2);
		lists.remove(2);
		lists.add(1, a);
		System.out.println(lists.toString());
	}
}
