package com.cm.manage.service.helpCenter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.helpCenter.IQuestionDao;
import com.cm.manage.model.helpCenter.Picture;
import com.cm.manage.model.helpCenter.Question;
import com.cm.manage.model.helpCenter.QuestionType;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.helpCenter.IQuestionService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.AorVo;
import com.cm.manage.vo.helpCenter.HelperContentVo;
import com.cm.manage.vo.helpCenter.QuestionAor;
import com.cm.manage.vo.helpCenter.QuestionVo;

@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl implements IQuestionService {
	@Autowired
	private  IQuestionDao questionDao;



	@Override
	public List<QuestionType> questionTypeList()throws Exception {
		return questionDao.questionTypeList();
	}

	@Override
	public EasyuiDataGridJson questionList(EasyuiDataGrid dg,
			QuestionVo questionVo) throws Exception{
		return questionDao.questionList(dg,questionVo);
	}

	@Override
	public Object getNum(QuestionVo questionVo) throws Exception{
		return questionDao.getNum(questionVo);
	}

	@Override
	public QuestionAor getQuestionAorById(long id)throws Exception {
		return 	questionDao.getQuestionById(id);
	}

	@Override
	public void updateQAorHiddenFlag(long qid,long aid, int hiddenFlag, int qaType) throws Exception{
		questionDao.updateQAorHiddenFlag(qid,aid,hiddenFlag,qaType);
	}

	@Override
	public void updateWeight(long id, int weight) throws Exception{
		questionDao.updateWeight(id,weight);
		
	}

	@Override
	public void saveAor(long qid, String content, String userId) throws Exception{
		questionDao.saveAor(qid,content,userId);
		
	}

	@Override
	public void updateAor(long qid,long id, String content, String userId) throws Exception{
		questionDao.updateAor(qid,id,content,userId);
		
	}

	@Override
	public void saveRAor(long qid,long pId, String content, String userId) throws Exception{
		questionDao.saveRAor(qid,pId,content,userId);
		
	}

	@Override
	public List<AorVo> getAorVoListByQid(long qid) throws Exception {
		return questionDao.getAorVoListByQid(qid);
	}

	@Override
	public void updateQuestionType(long qid, long qtid) throws Exception {
		questionDao.updateQuestionType(qid,qtid);
		
	}

	@Override
	public QuestionType addType(QuestionType questionType) throws Exception {
		return questionDao.addType(questionType);
		
	}

	@Override
	public void delType(QuestionType questionType) throws Exception {
		questionDao.delType(questionType);
		
	}

	@Override
	public void editType(QuestionType questionType) throws Exception {
		questionDao.editType(questionType);
		
	}

	@Override
	public List<Picture> pictureListByQid(long id) throws Exception {
		return questionDao.pictureListByQid(id);
	}

	@Override
	public EasyuiDataGridJson helperContentList(EasyuiDataGrid dg,
			HelperContentVo helperContentVo) {
		return questionDao.helperContentList(dg,helperContentVo);
	}

	@Override
	public void deleteQuestion(long id) {
		questionDao.deleteQuestion(id);
	}

	

}
