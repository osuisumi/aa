/**
 * 
 */
package com.haoyu.aip.qti.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.qti.dao.IQuestionDao;
import com.haoyu.aip.qti.dao.IQuestionPackageDao;
import com.haoyu.aip.qti.entity.Question;
import com.haoyu.aip.qti.entity.QuestionPackage;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.service.IQuestionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class QuestionServiceImpl implements IQuestionService {
	@Resource
	private IQuestionDao questionDao;
	@Resource
	private IQuestionPackageDao questionPackageDao;
	@Value("#{commonConfig['qti.file.system.base']}")
	private String fileSystemBase;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#findQuestionById(java.lang.String)
	 */
	@Override
	public Question findQuestionById(String id) {
		return questionDao.selectQuestioById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#findQuestions(com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public List<Question> findQuestions(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#findQuestions(com.haoyu.aip.qti.entity.Question, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Question> findQuestions(Question question, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#createQuestion(com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public Response createQuestion(Question question) {
		if(question==null){
			return Response.failInstance().responseMsg("question object is null");
		}
		if(StringUtils.isEmpty(question.getId())){
			question.setId(Identities.uuid2());
		}
		QuestionPackage questionPackage = new QuestionPackage();
		questionPackage.setId(Identities.uuid2());
		questionPackage.setSandboxPath(this.getSandboxPath(question.getId()));
		questionPackage.setQuestionHref(questionPackage.getId()+".xml");
		questionPackage.setFileName(questionPackage.getId()+".xml");
		int count = questionPackageDao.insertQuestionPackage(questionPackage);
		if(count>0){
			try {
				FileUtils.writeStringToFile(new File(questionPackage.getSandboxPath()+"/"+questionPackage.getQuestionHref()), question.toXml());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		question.setQuestionPackage(questionPackage);
		count = questionDao.insertQuestion(question);
		return count>0?Response.successInstance().responseData(question):Response.failInstance().responseMsg("create question fail!");
	}
	

	@Override
	public Response createTestQuestion(TestPackage testPackage,
			Question question) {
		if(question==null){
			return Response.failInstance().responseMsg("question object is null");
		}
		if(StringUtils.isEmpty(question.getId())){
			question.setId(Identities.uuid2());
		}
		QuestionPackage questionPackage = new QuestionPackage();
		questionPackage.setId(Identities.uuid2());
		questionPackage.setSandboxPath(testPackage.getSandboxPath());
		questionPackage.setQuestionHref("item/"+questionPackage.getId()+".xml");
		questionPackage.setFileName(questionPackage.getId()+".xml");
		int count = questionPackageDao.insertQuestionPackage(questionPackage);
		if(count>0){
			try {
				FileUtils.writeStringToFile(new File(questionPackage.getSandboxPath()+"/"+questionPackage.getQuestionHref()), question.toXml(),Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		question.setQuestionPackage(questionPackage);
		count = questionDao.insertQuestion(question);
		return count>0?Response.successInstance().responseData(question):Response.failInstance().responseMsg("create question fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#updateQuestion(com.haoyu.aip.qti.entity.Question)
	 */
	@Override
	public Response updateQuestion(Question question) {
		if(question==null||StringUtils.isEmpty(question.getId())){
			Response.failInstance().responseMsg("update question fail!question is null!");
		}
		Question questionResult = questionDao.selectQuestioById(question.getId());
		QuestionPackage questionPackage = questionResult.getQuestionPackage();
		int count =questionDao.updateQuestion(question);
		if(count>0){
			try {
				FileUtils.writeStringToFile(new File(questionPackage.getSandboxPath()+"/"+questionPackage.getQuestionHref()), question.toXml(),Charset.forName("UTF-8"));
				questionPackageDao.updateQuestionPackage(questionPackage);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return count>0?Response.successInstance().responseData(question):Response.failInstance().responseMsg("update question fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.qti.service.IQuestionService#deleteQuestion(java.lang.String)
	 */
	@Override
	public Response deleteQuestion(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getSandboxPath(String questionId){
		StringBuffer sandboxPath = new StringBuffer(fileSystemBase);
		sandboxPath.append("/").append(ThreadContext.getUser().getId());
		sandboxPath.append("/").append(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd"));
		sandboxPath.append("/").append("question").append("/").append(questionId);
		return sandboxPath.toString();
	}


}
