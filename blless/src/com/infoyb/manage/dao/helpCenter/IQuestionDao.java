package com.cm.manage.dao.helpCenter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cm.manage.model.helpCenter.Picture;
import com.cm.manage.model.helpCenter.QuestionType;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.AorVo;
import com.cm.manage.vo.helpCenter.HelperContentVo;
import com.cm.manage.vo.helpCenter.QuestionAor;
import com.cm.manage.vo.helpCenter.QuestionVo;

public interface IQuestionDao {

	EasyuiDataGridJson questionList(EasyuiDataGrid dg, QuestionVo questionVo)throws Exception;

	List<QuestionType> questionTypeList()throws Exception;

	Object getNum(QuestionVo questionVo)throws Exception;

	QuestionAor getQuestionById(long id)throws Exception;

	void updateQAorHiddenFlag(long qid,long aid, int hiddenFlag, int qaType)throws Exception;

	void updateWeight(long id, int weight)throws Exception;

	void saveAor(long qid, String content, String userId)throws Exception;

	void updateAor(long qid,long id, String content, String userId)throws Exception;

	void saveRAor(long qid,long pId, String content, String userId)throws Exception;

	List<AorVo> getAorVoListByQid(long qid)throws Exception;

	void updateQuestionType(long qid, long qtid)throws Exception;

	QuestionType addType(QuestionType questionType)throws Exception;

	void delType(QuestionType questionType)throws Exception;

	void editType(QuestionType questionType)throws Exception;

	List<Picture> pictureListByQid(long id)throws Exception;

	EasyuiDataGridJson helperContentList(EasyuiDataGrid dg,
			HelperContentVo helperContentVo);

	void deleteQuestion(long id);

}
