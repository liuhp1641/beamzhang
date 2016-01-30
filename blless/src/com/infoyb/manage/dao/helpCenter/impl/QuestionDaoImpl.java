package com.cm.manage.dao.helpCenter.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.helpCenter.IQuestionDao;
import com.cm.manage.model.helpCenter.Aor;
import com.cm.manage.model.helpCenter.Picture;
import com.cm.manage.model.helpCenter.Question;
import com.cm.manage.model.helpCenter.QuestionType;
import com.cm.manage.model.member.Member;
import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.AorVo;
import com.cm.manage.vo.helpCenter.HelperContentVo;
import com.cm.manage.vo.helpCenter.QuestionAor;
import com.cm.manage.vo.helpCenter.QuestionSum;
import com.cm.manage.vo.helpCenter.QuestionVo;

@Repository("questDaos")
public class QuestionDaoImpl implements IQuestionDao {

	@Autowired
	private IBaseDao<Question> questionDao;

	@Autowired
	private IBaseDao<QuestionType> questionTypeDao;

	@Autowired
	private IBaseDao<Aor> aorDao;

	@Autowired
	private IBaseDao<Member> memberDao;

	@Autowired
	private IBaseDao<Picture> pictureDao;

	@Override
	public EasyuiDataGridJson questionList(EasyuiDataGrid dg,
			QuestionVo questionVo) throws Exception {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder(
				"select q.id, q.createdate,q.content,q.state,q.remark,q.sourcetype,q.Equipment_Type,q.Answer_Num,q.question_Type_Id,q.LastDate,q.updateDate,q.weight,q.Page_view,q.Hidden_flag,u.id as userId,u.user_name,u.user_code from hc_question q join user_member u on q.user_code=u.user_code");
		List<Object> values = new ArrayList<Object>();
		if (questionVo != null) {// 添加查询条件
			// 用户名称
			if (CommonUtil.isNotEmpty(questionVo.getUserName())) {
				sb.append(" and u.user_name like ? ");
				values.add("%" + questionVo.getUserName() + "%");
			}
			// 问题Id
			if (CommonUtil.isNotEmpty(questionVo.getQid())
					&& questionVo.getQid() != 0) {
				sb.append(" and q.id like ? ");
				values.add("%" + questionVo.getQid() + "%");

			}
			// 问题类型
			if (CommonUtil.isNotEmpty(questionVo.getQuestionTypeId())
					&& questionVo.getQuestionTypeId() != 0) {
				sb.append(" and q.question_Type_Id = ? ");
				values.add(questionVo.getQuestionTypeId());
			}
			
			// 问题类型
			if (questionVo.getState()!=4) {
				sb.append(" and q.state = ? ");
				values.add(questionVo.getState());
			}
			
			// 问题内容
			if (CommonUtil.isNotEmpty(questionVo.getContent())) {
				sb.append(" and q.content like ? ");
				values.add("%" + questionVo.getContent() + "%");
			}
			// 留言时间
			if (CommonUtil.isNotEmpty(questionVo.getCreateDateStart())) {
				sb.append(" and q.createdate >= ?");
				values.add(DateUtil.format(questionVo.getCreateDateStart(),
						"yy-MM-dd"));
			}
			// 创建时间
			if (CommonUtil.isNotEmpty(questionVo.getCreateDateEnd())) {
				sb.append(" and q.createdate < ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(questionVo.getCreateDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
			// 最后回复时间
			if (CommonUtil.isNotEmpty(questionVo.getLastDateStart())) {
				sb.append(" and q.LastDate >= ?");
				values.add(DateUtil.format(questionVo.getLastDateStart(),
						"yy-MM-dd"));
			}
			if (CommonUtil.isNotEmpty(questionVo.getLastDateEnd())) {
				sb.append(" and q.LastDate < ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(questionVo.getLastDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
			// 问题状态
			if (questionVo != null && questionVo.getProcessingState() != null) {
				if (questionVo.getProcessingState() == 2) {
					sb.append(" and q.state = 0 ");
				} else if (questionVo.getProcessingState() == 3) {
					sb.append(" and q.state in (1,2,3) ");
				}
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(questionDao.countBySql(totalHql, values).longValue());// 设置总记录数

		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			if (dg.getSort().toString().equalsIgnoreCase("qid")) {
				sort = "q.id";
			}

			if (dg.getSort().toString().equalsIgnoreCase("replyNum")) {
				sort = "q.Answer_Num";
			}
			if (dg.getSort().toString().equalsIgnoreCase("pageView")) {
				sort = "q.Page_view";
			}
			if (dg.getSort().toString().equalsIgnoreCase("lastDate")) {
				sort = "q.LastDate";
			}
			if (dg.getSort().toString().equalsIgnoreCase("createDate")) {
				sort = "q.createDate";
			}
			if (dg.getSort().toString().equalsIgnoreCase("weight")) {
				sort = "q.weight";
			}
			if (dg.getSort().toString().equalsIgnoreCase("replyNum")) {
				sort = "q.UNEXAMINENUM";
			}
			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}

		List<Map> questionMapList = questionDao.findBySql(sb.toString(),
				values, dg.getPage(), dg.getRows());// 查询分页
		List<QuestionVo> questionVoList = new ArrayList<QuestionVo>();
		if (questionMapList != null && questionMapList.size() > 0) {// 转换模型
			for (Map map : questionMapList) {
				QuestionVo qVo = new QuestionVo();
				long qid = ((BigDecimal) map.get("ID")).longValue();
				qVo.setQid(((BigDecimal) map.get("ID")).longValue());
				qVo.setUserName((String) map.get("USER_NAME"));
				String content = (String) map.get("CONTENT");
				if (content.length() > 20) {
					content = content.substring(0, 19);
					content += "...";
				}
				qVo.setContent(content);
				qVo.setUserCode((String) map.get("USER_CODE"));
				qVo.setPageView(((BigDecimal) map.get("PAGE_VIEW")).intValue());
				qVo.setCreateDate(DateUtil.format((Date) map.get("CREATEDATE")));
				if (map.get("LASTDATE") != null)
					qVo.setLastDate(DateUtil.format((Date) map.get("LASTDATE")));
				qVo.setQuestionTypeId(((BigDecimal) map.get("QUESTION_TYPE_ID"))
						.intValue());
				qVo.setWeight(((BigDecimal) map.get("WEIGHT")).intValue());
				qVo.setReplyNum(getQuestionReplyNum(qid));
				qVo.setState(((BigDecimal) map.get("STATE")).intValue());
				questionVoList.add(qVo);
			}
		}
		j.setRows(questionVoList);// 设置返回的行
		return j;
	}

	public String getQuestionReplyNum(long questionId) throws Exception {
		String sql = "select count(*) from hc_answer_or_requestion a where a.question_id = ? and a.state=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(questionId);
		values.add(0);
		// 公开数
		Long openNum = aorDao.countBySql(sql, values).longValue();

		values.clear();
		values.add(questionId);
		values.add(1);
		// 待审核
		Long paNum = aorDao.countBySql(sql, values).longValue();
		String replyNum = null;
		if (openNum > 0) {
			replyNum = "" + paNum
					+ "(<span style=\"font-family:华文中宋; color:red; \">"
					+ openNum + "</span>)";
		} else {
			replyNum = "" + paNum + "(" + openNum + ")";
		}
		return replyNum;

	}

	@Override
	public List<QuestionType> questionTypeList() throws Exception {
		String sql = "from QuestionType order by serialNumber desc ";
		return questionTypeDao.find(sql, new Object[] {});
	}

	@Override
	public Object getNum(QuestionVo questionVo) throws Exception {
		StringBuilder sb = new StringBuilder(
				"select q.id, q.createdate,q.content,q.state,q.remark,q.sourcetype,q.Equipment_Type,q.Answer_Num,q.question_Type_Id,q.LastDate,q.updateDate,q.weight,q.Page_view,q.Hidden_flag,u.id as userId,u.user_name,u.user_code from hc_question q join user_member u on q.user_code=u.user_code");
		String sb2Sum = " select count(*)  from (" + sb;
		String allNumSql = null;
		String untreatedNumSql = null;
		String treatedNumSql = null;
		if (questionVo != null && questionVo.getQuestionTypeId() != null
				&& questionVo.getQuestionTypeId() != 0) {
			allNumSql = sb2Sum + " and q.question_Type_Id="
					+ questionVo.getQuestionTypeId() + ")";
			untreatedNumSql = sb2Sum
					+ " and q.state=0 and  q.question_Type_Id="
					+ questionVo.getQuestionTypeId() + ")";
			treatedNumSql = sb2Sum = sb2Sum
					+ " and q.state in (1,2,3)  and  q.question_Type_Id="
					+ questionVo.getQuestionTypeId() + ")";
		} else {
			allNumSql = sb2Sum + ")";
			untreatedNumSql = sb2Sum + " and q.state=0" + ")";
			treatedNumSql = sb2Sum = sb2Sum + " and q.state in (1,2,3)" + ")";
		}
		QuestionSum questionSum = new QuestionSum();
		questionSum.setAllNum(questionDao.countBySql(allNumSql).longValue());
		questionSum.setUntreatedNum(questionDao.countBySql(untreatedNumSql)
				.longValue());
		questionSum.setTreatedNum(questionDao.countBySql(treatedNumSql)
				.longValue());
		return questionSum;
	}

	@Override
	public QuestionAor getQuestionById(long id) throws Exception {
		QuestionAor questionAor = null;
		if (id != 0) {
			questionAor = new QuestionAor();
			Question question = questionDao.get(Question.class, id);
			String hqlq = " from Member where userCode='"
					+ question.getUserCode() + "'";
			question.setUserName(memberDao.get(hqlq).getUserName());
			questionAor.setId(question.getId());
			questionAor.setUserName(question.getUserName());
			questionAor.setUserCode(question.getUserCode());
			questionAor.setQuestionTypeId(question.getQuestionTypeId());
			questionAor.setWeight(question.getWeight());
			questionAor.setEquipmentType(question.getEquipmentType());
			questionAor.setEquipmentSystem(question.getEquipmentSystem());
			questionAor
					.setCreateDate(DateUtil.format(question.getCreateDate()));
			questionAor.setContent(question.getContent());
			questionAor.setHiddenFlag(question.getHiddenFlag());
			JSONArray json = JSONArray.fromObject(getAorVoListByQid(id));
			questionAor.setAorVoList(json.toString());
		}
		return questionAor;
	}

	public List<AorVo> getAorVoListByQid(long id) throws Exception {
		String sqla4u = "select a.id,m.user_name,m.user_code,a.createdate,a.content,a.question_id,a.parent_id,a.state,a.remark,a.hidden_flag from hc_answer_or_requestion a join user_member m on a.user_code =m.user_code and a.Question_Id=?";
		String sqla4s = "select a.id,s.id userId,s.name,a.createdate,a.content, a.question_id,a.parent_id,a.state, a.remark,a.hidden_flag from hc_answer_or_requestion a join SYS_USER s on a.sys_user_id = s.id and a.Question_Id=?";
		List<AorVo> allAorVoList = new ArrayList<AorVo>();
		List<Object> obja = new ArrayList<Object>();
		obja.add(id);
		List<Map> aorList4u = aorDao.findBySql(sqla4u, obja);
		List<Map> aorList4s = aorDao.findBySql(sqla4s, obja);
		List<AorVo> aorVoList4u = null;
		if (aorList4u != null && aorList4u.size() != 0) {
			aorVoList4u = new ArrayList<AorVo>();
			for (Map aor : aorList4u) {
				AorVo aorVo = new AorVo();
				aorVo.setId(((BigDecimal) aor.get("ID")).longValue());
				aorVo.setUserCode((String) aor.get("USER_CODE"));
				aorVo.setUserName((String) aor.get("USER_NAME"));
				aorVo.setHiddenFlag(((BigDecimal) aor.get("HIDDEN_FLAG"))
						.intValue());
				aorVo.setState(((BigDecimal) aor.get("STATE")).intValue());
				aorVo.setContent((String) aor.get("CONTENT"));
				aorVo.setCreateDate(DateUtil.format((Date) aor
						.get("CREATEDATE")));
				String sqlpa = "select a.id,s.id userId,s.name,a.createdate,a.content, a.question_id,a.parent_id,a.state, a.remark,a.hidden_flag from hc_answer_or_requestion a join SYS_USER s on a.sys_user_id = s.id and a.Parent_id=?";
				List<Object> objpa = new ArrayList<Object>();
				objpa.add(aorVo.getId());
				List<Map> aorp = aorDao.findBySql(sqlpa, objpa);
				if (aorp != null && aorp.size() != 0) {
					AorVo reAorVo = new AorVo();
					reAorVo.setId(((BigDecimal) aorp.get(0).get("ID"))
							.longValue());
					reAorVo.setUserName((String) aorp.get(0).get("NAME"));
					reAorVo.setHiddenFlag(((BigDecimal) aorp.get(0).get(
							"HIDDEN_FLAG")).intValue());
					reAorVo.setState(((BigDecimal) aorp.get(0).get("STATE"))
							.intValue());
					reAorVo.setContent((String) aorp.get(0).get("CONTENT"));
					reAorVo.setCreateDate(DateUtil.format((Date) aorp.get(0)
							.get("CREATEDATE")));
					aorVo.setReAorVo(reAorVo);
				}
				aorVoList4u.add(aorVo);
			}
			allAorVoList.addAll(aorVoList4u);
		}
		List<AorVo> aorVoList4s = null;
		if (aorList4s != null && aorList4s.size() != 0) {
			aorVoList4s = new ArrayList<AorVo>();
			for (Map aor : aorList4s) {
				AorVo aorVo = new AorVo();
				aorVo.setId(((BigDecimal) aor.get("ID")).longValue());
				aorVo.setUserName((String) aor.get("NAME"));
				aorVo.setHiddenFlag(((BigDecimal) aor.get("HIDDEN_FLAG"))
						.intValue());
				aorVo.setState(((BigDecimal) aor.get("STATE")).intValue());
				aorVo.setContent((String) aor.get("CONTENT"));
				aorVo.setCreateDate(DateUtil.format((Date) aor
						.get("CREATEDATE")));
				aorVoList4s.add(aorVo);
			}
			allAorVoList.addAll(aorVoList4s);
		}
		Comparator<AorVo> comparator = new Comparator<AorVo>() {
			public int compare(AorVo a1, AorVo a2) {
				// 先排年龄
				if (a1.getCreateDate() != null
						&& !a1.getCreateDate().equals(a2.getCreateDate())) {
					return a1.getCreateDate().compareTo(a2.getCreateDate());
				}
				return 0;
			}
		};
		Collections.sort(allAorVoList, comparator);
		return allAorVoList;
	}

	@Override
	public void updateQAorHiddenFlag(long qid, long aid, int hiddenFlag,
			int qaType) throws Exception {
		Question question = questionDao.get(Question.class, qid);
		List<AorVo> aorVos = getAorVoListByQid(qid);
		boolean flag = false;
		if (qaType == 1) {
			question.setHiddenFlag(hiddenFlag);
			if (hiddenFlag == 1) {
				if (aorVos != null && aorVos.size() != 0) {
					for (AorVo aorVo : aorVos) {
						if (aorVo.getHiddenFlag() == 2) {
							flag = true;
						}
					}
					if (flag) {
						question.setState(3);
					} else {
						question.setState(1);
					}
				} else {
					question.setState(1);
				}
			} else if (hiddenFlag == 2) {
				question.setState(2);
			}
			questionDao.update(question);
		} else if (qaType == 2) {
			int qState = question.getState();
			Aor aor = aorDao.get(Aor.class, aid);
			if (qState == 1 && hiddenFlag == 2) {
				question.setState(3);
			}
			if (qState == 3 && hiddenFlag == 1) {

				for (AorVo aorVo : aorVos) {
					if (aorVo.getHiddenFlag() == 2) {
						flag = true;
					}
				}
				if (flag) {
					question.setState(1);
				}
			}
			if (aor.getState() == 0) {
				question.setUnExamineNum(question.getUnExamineNum() - 1);
				question.setExamineNum(question.getExamineNum() + 1);
			}
			List<Object> param = new ArrayList<Object>();
			param.add(aid);
			long pnum = aorDao
					.countBySql(
							"select count(*) from hc_answer_or_requestion t where t.parent_id=? ",
							param).longValue();
			if (hiddenFlag == 1 && hiddenFlag != aor.getHiddenFlag()) {
				question.setAnswerNum(question.getAnswerNum() + 1);
				if (pnum > 0 && aor.getHiddenFlag() != 0) {
					question.setAnswerNum(question.getAnswerNum() + 1);
				}
			} else if (hiddenFlag == 2 && hiddenFlag != aor.getHiddenFlag()
					&& aor.getHiddenFlag() != 0) {
				question.setAnswerNum(question.getAnswerNum() - 1);
				if (pnum > 0) {
					question.setAnswerNum(question.getAnswerNum() - 1);
				}
			}
			questionDao.update(question);
			aor.setState(1);
			aor.setHiddenFlag(hiddenFlag);
			aorDao.update(aor);
		}
	}

	@Override
	public void updateWeight(long id, int weight) throws Exception {
		Question question = questionDao.get(Question.class, id);
		question.setWeight(weight);
		questionDao.update(question);
	}

	@Override
	public void updateQuestionType(long qid, long qtid) throws Exception {
		Question question = questionDao.get(Question.class, qid);
		question.setQuestionTypeId(qtid);
		questionDao.update(question);
	}

	@Override
	public void saveAor(long qid, String content, String userId)
			throws Exception {
		Aor aor = new Aor();
		aor.setQuestionId(qid);
		aor.setState(-1);
		aor.setSysUserId(userId);
		aor.setContent(content);
		aor.setCreateDate(new Date());
		aorDao.save(aor);
		Question question = questionDao.get(Question.class, qid);
		question.setLastDate(new Date());
		question.setExamineNum(question.getExamineNum() + 1);
		question.setAnswerNum(question.getAnswerNum() + 1);
		questionDao.update(question);

	}

	@Override
	public void updateAor(long qid, long id, String content, String userId)
			throws Exception {
		Aor aor = aorDao.get(Aor.class, id);
		aor.setSysUserId(userId);
		aor.setContent(content);
		aor.setCreateDate(new Date());
		aorDao.update(aor);
		Question question = questionDao.get(Question.class, qid);
		question.setLastDate(new Date());
		questionDao.update(question);
	}

	@Override
	public void saveRAor(long qid, long pId, String content, String userId)
			throws Exception {
		Aor aor = new Aor();
		aor.setParentId(pId);
		aor.setState(-1);
		aor.setSysUserId(userId);
		aor.setContent(content);
		aor.setCreateDate(new Date());
		aorDao.save(aor);
		Question question = questionDao.get(Question.class, qid);
		question.setLastDate(new Date());
		question.setExamineNum(question.getExamineNum() + 1);
		question.setAnswerNum(question.getAnswerNum() + 1);
		questionDao.update(question);

	}

	@Override
	public QuestionType addType(QuestionType questionType) throws Exception {
		long id = (Long) questionTypeDao.save(questionType);
		questionType.setId(id);
		return questionType;

	}

	@Override
	public void delType(QuestionType questionType) throws Exception {
		questionTypeDao.delete(questionType);

	}

	@Override
	public void editType(QuestionType questionType) throws Exception {
		questionTypeDao.update(questionType);

	}

	@Override
	public List<Picture> pictureListByQid(long id) throws Exception {
		String sql = "select * from hc_picture where question_id =?";
		List<Object> objQid = new ArrayList<Object>();
		objQid.add(id);
		List<Map> pictureObj = pictureDao.findBySql(sql, objQid);
		;
		List<Picture> pictureList = new ArrayList<Picture>();
		if (pictureObj != null && pictureObj.size() != 0) {
			for (Map map : pictureObj) {
				Picture picture = new Picture();
				picture.setId(((BigDecimal) map.get("ID")).longValue());
				picture.setQuestionId(((BigDecimal) map.get("QUESTION_ID"))
						.longValue());
				picture.setPicturePath(ConfigUtils.getValue("MESSAGE_IMG_URL")
						+ (String) map.get("PICTURE_PATH"));
				pictureList.add(picture);
			}
		}
		return pictureList;
	}

	@Override
	public EasyuiDataGridJson helperContentList(EasyuiDataGrid dg,
			HelperContentVo helperContentVo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select e.tid,e.sys_user_id,e.name,e.createdate,e.aorContent,e.question_id,e.parent_id,e.questionContent,e.qid  from(select t.id as tid,t.sys_user_id,u.name,t.createdate,t.content     as aorContent, t.question_id, t.parent_id, q.content     as questionContent, q.id  as qid from hc_answer_or_requestion t join sys_user u on t.sys_user_id = u.id and t.state = -1 and t.parent_id = 0 join hc_question q on t.question_id = q.id union select t.id          as tid, t.sys_user_id, u.name, t.createdate, t.content     as aorContent, t.question_id,  t.parent_id, q.content     as questionContent, q.id  as qid  from hc_answer_or_requestion t  join sys_user u   on t.sys_user_id = u.id   and t.state = -1   and t.question_id = 0  left join hc_question q    on t.question_id = q.id) e where 1=1  ");
		List<Object> values = new ArrayList<Object>();
		if (helperContentVo != null) {// 添加查询条件
			// 用户名称
			if (CommonUtil.isNotEmpty(helperContentVo.getHelperId())) {
				sb.append(" and e.sys_user_id= ?");
				values.add(helperContentVo.getHelperId());
			}
			
			// 留言时间
			if (CommonUtil.isNotEmpty(helperContentVo.getCreateDateStart())) {
				sb.append(" and e.createdate >= ?");
				values.add(DateUtil.format(helperContentVo.getCreateDateStart(),
						"yy-MM-dd"));
			}
		
			if (CommonUtil.isNotEmpty(helperContentVo.getCreateDateEnd())) {
				sb.append(" and e.createdate < ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(helperContentVo.getCreateDateEnd(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
				cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
			
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(aorDao.countBySql(totalHql, values).longValue());// 设置总记录数

		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			

			if (dg.getSort().toString().equalsIgnoreCase("createDate")) {
				sort = "e.createdate";
			}
			
			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}

		List<Map> aorMapList = aorDao.findBySql(sb.toString(),
				values, dg.getPage(), dg.getRows());// 查询分页
		List<HelperContentVo> helperContentVoList = new ArrayList<HelperContentVo>();
		if (aorMapList != null && aorMapList.size() > 0) {// 转换模型
			for (Map map : aorMapList) {
				HelperContentVo vo = new HelperContentVo();
				vo.setHelperId((String) map.get("SYS_USER_ID"));
				vo.setHelperName((String) map.get("NAME"));
				vo.setCreateDate(DateUtil.format((Date) map.get("CREATEDATE")));
				
				
				
				String aorContent = (String) map.get("AORCONTENT");
				if (aorContent!=null&&aorContent.length() > 20) {
					aorContent = aorContent.substring(0, 19);
					aorContent += "...";
				}
				String questionContent=null;
				if(((BigDecimal) map.get("QUESTION_ID")).intValue()==0){
					vo.setReAorContent("<span style=\"font-family:华文中宋; color:red; \">"+aorContent+"</span>");
					Aor aor = aorDao.get(Aor.class, ((BigDecimal) map.get("PARENT_ID")).longValue());
					String faorContent = aor.getContent();
					if (faorContent!=null&&faorContent.length() > 20) {
						faorContent = faorContent.substring(0, 19);
						faorContent += "...";
					}
					vo.setAorContent(faorContent);
					Question question = questionDao.get(Question.class, aor.getQuestionId());
					vo.setQid(question.getId());
					questionContent= question.getContent();
				}else if(((BigDecimal) map.get("PARENT_ID")).intValue()==0){
					vo.setAorContent("<span style=\"font-family:华文中宋; color:red; \">"+aorContent+"</span>");
					vo.setQid(((BigDecimal) map.get("QID")).longValue());
					questionContent= (String) map.get("QUESTIONCONTENT");
				}
				
				if (questionContent!=null&&questionContent.length() > 20) {
					questionContent = questionContent.substring(0, 19);
					questionContent += "...";
				}
				vo.setQuestionContent(questionContent);
				helperContentVoList.add(vo);
			}
		}
		j.setRows(helperContentVoList);// 设置返回的行
		return j;
	}

	@Override
	public void deleteQuestion(long id) {
		List<Aor> aorList=aorDao.find("from Aor where questionId= ?", id);
		if(aorList!=null&&aorList.size()!=0){
			for (Aor aor : aorList) {
				List<Aor> reAorList =aorDao.find("from Aor where parentId=?", aor.getId());
				if(reAorList!=null&&reAorList.size()!=0){
					for (Aor aor2 : reAorList) {
						aorDao.delete(aor2);
					}
				}
				aorDao.delete(aor);
			}
		}
		questionDao.delete(questionDao.get(Question.class, id));
	}

}
