package com.cm.manage.dao.operate.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IScoreQutzDao;
import com.cm.manage.model.score.ScoreQutz;
import com.cm.manage.model.score.ScoreQutzOption;
import com.cm.manage.model.score.ScoreQutzType;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.score.ScoreQutzUserVO;
import com.cm.manage.vo.score.ScoreQutzVO;

@Repository
public class ScoreQutzDaoImpl implements IScoreQutzDao {

    @Autowired
    private IBaseDao<Object> scoreQutzDao;
    
    @Autowired
    private IBaseDao<ScoreQutz> scoreQutzForInfoDao;

    @Autowired
    private IBaseDao<ScoreQutzOption> qutzOptionDao;


    /**
     * 奖池列表
     *
     * @return
     */
    public List<Map> scoreQutzType() {
        StringBuffer str = new StringBuffer("select qt.ID,qt.QUTZ_TYPE_ID ,qt.QUTZ_TYPE_NAME,qt.BONUS_TYPE,qt.BONUS_USER_CODE,qt.ISSUE_CODE,u.USER_NAME from SCORE_QUTZ_TYPE qt");
        str.append(" left join USER_MEMBER u on qt.BONUS_USER_CODE=u.USER_CODE ORDER BY  QUTZ_TYPE_ID ");
        return scoreQutzDao.findBySql(str.toString());

    }

    /**
     * 最大奖池编号
     *
     * @return
     */
    public Integer maxQutzTypeId() {
        StringBuffer str = new StringBuffer("select max(TO_NUMBER(nvl(QUTZ_TYPE_ID,0))) maxId from SCORE_QUTZ_TYPE");
        List<Map> list = scoreQutzDao.findBySql(str.toString());
        if (list != null && list.size() > 0) {
            Map map = list.get(0);
            BigDecimal maxId = (BigDecimal) map.get("MAXID");
            if (CommonUtil.isNotEmpty(maxId)) {
                return maxId.intValue();
            }
        }
        return null;
    }

    /**
     * 奖池保存
     *
     * @param vo
     * @return
     */
    public void saveQutzType(ScoreQutzType vo) {
        Integer id = vo.getId();
        if (id == null) {
            vo.setCreateTime(new Date());
            scoreQutzDao.save(vo);
        } else {
            StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ_TYPE SET QUTZ_TYPE_NAME=?,BONUS_TYPE=?,BONUS_USER_CODE=?,UPDATE_TIME=sysdate,ISSUE_CODE=? WHERE QUTZ_TYPE_ID=?");
            List<Object> values = new ArrayList<Object>();
            values.add(vo.getQutzTypeName());
            values.add(vo.getBonusType());
            values.add(vo.getBonusUserCode());
            values.add(vo.getIssueCode());
            values.add(vo.getQutzTypeId());
            scoreQutzDao.executeSql(str.toString(), values);
        }
    }

    /**
     * 奖池删除
     *
     * @param qutzTypeId
     * @return
     */
    public boolean delQutzType(String qutzTypeId) {
        StringBuffer str = new StringBuffer("delete from SCORE_QUTZ_TYPE where QUTZ_TYPE_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(qutzTypeId);
        int result = scoreQutzDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据奖池Id获取奖池信息
     *
     * @param qutzTypeId
     * @return
     */
    public Map qutzTypeInfo(String qutzTypeId) {
        StringBuffer str = new StringBuffer("select ID,QUTZ_TYPE_ID ,QUTZ_TYPE_NAME,BONUS_TYPE,BONUS_USER_CODE,ISSUE_CODE from SCORE_QUTZ_TYPE ");
        str.append(" where QUTZ_TYPE_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(qutzTypeId);
        List<Map> list = scoreQutzDao.findBySql(str.toString(), values);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;

    }

    /**
     * 积分竞猜查询列表
     */
    @Override
    public EasyuiDataGridJson scoreQutzList(EasyuiDataGrid dg, ScoreQutzVO score) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuffer str = new StringBuffer(" select q.QUTZ_ID,q.QUTZ_NAME,q.QUTZ_ISSUE,q.QUTZ_TYPE_ID,t.QUTZ_TYPE_NAME QUTZ_TYPE,q.BONUS_TYPE,q.STATUS,q.BONUS_STATUS,");
        str.append(" q.START_TIME,q.END_TIME,q.QUTZ_TOPIC,q.QUTZ_AMOUNT,q.ATTEND_NUMBERS,q.ATTEND_AMOUNT,q.LAST_BONUS_AMOUNT,q.IS_HIDE,q.IS_RECOMMEND,q.IS_SHARE from SCORE_QUTZ q ");
        str.append(" left join SCORE_QUTZ_TYPE t on q.QUTZ_TYPE_ID=t.QUTZ_TYPE_ID where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        if (score != null) {
            // 是否模糊查询
            boolean flag = score.isFlag();
            // 活动期次
            if (CommonUtil.isNotEmpty(score.getQutzIssue())) {
                if (flag) {
                    str.append(" and q.QUTZ_ISSUE like ? ");
                    values.add("%" + score.getQutzIssue().trim() + "%");
                } else {
                    str.append(" and q.QUTZ_ISSUE = ? ");
                    values.add(score.getQutzIssue().trim());
                }
            }
            //竞猜类型
            if (CommonUtil.isNotEmpty(score.getQutzTypeId())) {
                str.append(" and q.QUTZ_TYPE_ID = ? ");
                values.add(score.getQutzTypeId());
            }
            //活动状态
            if (CommonUtil.isNotEmpty(score.getStatus())) {
                str.append(" and q.STATUS = ? ");
                values.add(score.getStatus());
            }
            //派奖状态
            if (CommonUtil.isNotEmpty(score.getBonusStatus())) {
                str.append(" and q.BONUS_STATUS = ? ");
                values.add(score.getBonusStatus());
            }
            //开始时间start
            if (CommonUtil.isNotEmpty(score.getStartTimeStart())) {
                str.append(" and q.START_TIME >= ? ");
                values.add(DateUtil.format(score.getStartTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //开始时间end
            if (CommonUtil.isNotEmpty(score.getStartTimeEnd())) {
                str.append(" and q.START_TIME <= ? ");
                values.add(DateUtil.format(score.getStartTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //结束时间start
            if (CommonUtil.isNotEmpty(score.getEndTimeStart())) {
                str.append(" and q.END_TIME >= ? ");
                values.add(DateUtil.format(score.getEndTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //结束时间end
            if (CommonUtil.isNotEmpty(score.getEndTimeEnd())) {
                str.append(" and q.END_TIME <= ? ");
                values.add(DateUtil.format(score.getEndTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(scoreQutzDao.countBySql(totalHql, values).longValue());// 设置总记录数

        str.append(" order by  q.STATUS,q.BONUS_STATUS ");
        List<Map> mapList = scoreQutzDao.findBySql(str.toString(), values,
                dg.getPage(), dg.getRows());// 查询分页
        List<ScoreQutzVO> scoreQutzList = new ArrayList<ScoreQutzVO>();
        if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
                ScoreQutzVO vo = new ScoreQutzVO();
                vo.setQutzId((String) map.get("QUTZ_ID"));
                vo.setQutzName((String) map.get("QUTZ_NAME"));
                vo.setQutzIssue((String) map.get("QUTZ_ISSUE"));
                vo.setQutzTopic((String) map.get("QUTZ_TOPIC"));
                vo.setQutzType((String) map.get("QUTZ_TYPE"));
                BigDecimal bonusType = (BigDecimal) map.get("BONUS_TYPE");
                vo.setBonusType(bonusType.intValue());
                vo.setQutzAmount(CommonUtil.round(map.get("QUTZ_AMOUNT"), 2));
                vo.setAttendAmount(CommonUtil.round(map.get("ATTEND_AMOUNT"), 2));
                BigDecimal attendNumbers = (BigDecimal) map.get("ATTEND_NUMBERS");
                vo.setAttendNumbers(attendNumbers == null ? 0 : attendNumbers.intValue());
                vo.setLastBonusAmount(CommonUtil.round(map.get("LAST_BONUS_AMOUNT"), 2));
                Date startTime = (Date) map.get("START_TIME");
                vo.setStartTime(DateUtil.format(startTime));
                Date endTime = (Date) map.get("END_TIME");
                vo.setEndTime(DateUtil.format(endTime));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                vo.setStatus(status.intValue());
                BigDecimal bonusStatus = (BigDecimal) map.get("BONUS_STATUS");
                vo.setBonusStatus(bonusStatus.intValue());
                BigDecimal isHide = (BigDecimal) map.get("IS_HIDE");
                vo.setIsHide(isHide==null?0:isHide.intValue());
                BigDecimal isRecommend = (BigDecimal) map.get("IS_RECOMMEND");
                vo.setIsRecommend(isRecommend==null?0:isRecommend.intValue());
                
                BigDecimal isShare = (BigDecimal) map.get("IS_SHARE");
                vo.setIsShare(isShare==null?0:isShare.intValue());
                scoreQutzList.add(vo);
            }
        }
        j.setRows(scoreQutzList);// 设置返回的行
        return j;
    }

    /**
     * 根据竞猜类型上期活动信息
     *
     * @param qutzTypeId
     * @return
     */
    public Map lastScoreQutz(String qutzTypeId, Integer bonusType) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer str = new StringBuffer("select max(QUTZ_ISSUE) maxIssue from SCORE_QUTZ where QUTZ_TYPE_ID=? group by QUTZ_TYPE_ID ");
        List<Object> values = new ArrayList<Object>();
        values.add(qutzTypeId);
        List<Map> list = scoreQutzDao.findBySql(str.toString(), values);
        if (list != null && list.size() > 0) {
            Map map = list.get(0);
            String maxIssue = (String) map.get("MAXISSUE");
            resultMap.put("maxIssue", maxIssue);
        } else {
            resultMap.put("maxIssue", null);
        }
        if (bonusType == 0) {
            str = new StringBuffer(" select max(QUTZ_ISSUE) maxIssue from SCORE_QUTZ where QUTZ_TYPE_ID=? and  BONUS_STATUS=2 group by QUTZ_TYPE_ID ");

            list = scoreQutzDao.findBySql(str.toString(), values);
            String lastMaxIssue = null;
            if (list != null && list.size() > 0) {
                Map map = list.get(0);
                lastMaxIssue = (String) map.get("MAXISSUE");
                resultMap.put("lastMaxIssue", lastMaxIssue);
            } else {
                resultMap.put("lastMaxIssue", null);
            }
            str = new StringBuffer(" select LAST_BONUS_AMOUNT from SCORE_QUTZ where QUTZ_TYPE_ID=? ");
            if (CommonUtil.isNotEmpty(lastMaxIssue)) {
                str.append(" and QUTZ_ISSUE=?");
                values.add(lastMaxIssue);
            }
            list = scoreQutzDao.findBySql(str.toString(), values);
            if (list != null && list.size() > 0) {
                Map map = list.get(0);
                resultMap.put("lastBonusAmount", CommonUtil.round(map.get("LAST_BONUS_AMOUNT"), 2));
            } else {
                resultMap.put("lastBonusAmount", null);
            }
        } else {
            resultMap.put("lastMaxIssue", null);
        }
        return resultMap;
    }

    /**
     * 竞猜答案
     *
     * @param qutzId
     * @return
     */
    public List<ScoreQutzOption> scoreQutzOption(String qutzId) {
        String hql = "from ScoreQutzOption where qutzId=? order by optionOrder ";
        List<ScoreQutzOption> qutzOptionlList = qutzOptionDao.find(hql, new Object[]{qutzId});
        if (CommonUtil.isNotEmpty(qutzOptionlList)) {
            return qutzOptionlList;
        }
        return null;
    }

    /**
     * 竞猜活动保存
     *
     * @param scoreQutz
     * @param options
     */
    public void saveQutzActivity(ScoreQutzVO scoreQutz, String options) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String qutzId = scoreQutz.getQutzId();
        if (CommonUtil.isNotEmpty(qutzId)) {
            StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ SET QUTZ_NAME=?,QUTZ_TOPIC=?,QUTZ_ISSUE=?,QUTZ_TYPE_ID=?,BONUS_TYPE=?,START_TIME=?,END_TIME=?,");
            str.append(" VIP_LOW=?,VIP_HIGH=?,SID=?,QUTZ_AMOUNT=? ,IMG_URL=? ,IS_SHARE=?,UPDATE_TIME=sysdate where QUTZ_ID=?");
            List<Object> values = new ArrayList<Object>();
            values.add(scoreQutz.getQutzName());
            values.add(scoreQutz.getQutzTopic());
            values.add(scoreQutz.getQutzIssue());
            values.add(scoreQutz.getQutzTypeId());
            values.add(scoreQutz.getBonusType());
            values.add(DateUtil.format(scoreQutz.getStartTime(), "yy-MM-dd HH:mm:ss"));
            values.add(DateUtil.format(scoreQutz.getEndTime(), "yy-MM-dd HH:mm:ss"));
            values.add(scoreQutz.getVipLow() == null ? 0 : scoreQutz.getVipLow());
            values.add(scoreQutz.getVipHigh() == null ? 99 : scoreQutz.getVipHigh());
            values.add((scoreQutz.getSid() == null || scoreQutz.getSid() == "") ? "00000" : scoreQutz.getSid());
            values.add(scoreQutz.getQutzAmount() == null ? 0 : scoreQutz.getQutzAmount());
            values.add(scoreQutz.getImgUrl());
            values.add(scoreQutz.getIsShare());
            values.add(qutzId);
            scoreQutzDao.executeSql(str.toString(), values);

        } else {
            qutzId = formatter.format(new Date()) + CommonUtil.random(6);
            StringBuffer str = new StringBuffer("insert into SCORE_QUTZ(	ID,QUTZ_ID,QUTZ_NAME,QUTZ_TOPIC,QUTZ_ISSUE,QUTZ_TYPE_ID,BONUS_TYPE,START_TIME,END_TIME,");
            str.append(" VIP_LOW,VIP_HIGH,SID,QUTZ_AMOUNT,IMG_URL,IS_SHARE,STATUS,BONUS_STATUS,CREATE_TIME,UPDATE_TIME,IS_HIDE,IS_RECOMMEND) values (SEQ_SCORE_QUTZ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,0,sysdate,sysdate,0,0)");
            List<Object> values = new ArrayList<Object>();
            values.add(qutzId);
            values.add(scoreQutz.getQutzName());
            values.add(scoreQutz.getQutzTopic());
            values.add(scoreQutz.getQutzIssue());
            values.add(scoreQutz.getQutzTypeId());
            values.add(scoreQutz.getBonusType());
            values.add(DateUtil.format(scoreQutz.getStartTime(), "yy-MM-dd HH:mm:ss"));
            values.add(DateUtil.format(scoreQutz.getEndTime(), "yy-MM-dd HH:mm:ss"));
            values.add(scoreQutz.getVipLow() == null ? 0 : scoreQutz.getVipLow());
            values.add(scoreQutz.getVipHigh() == null ? 99 : scoreQutz.getVipHigh());
            values.add((scoreQutz.getSid() == null || scoreQutz.getSid() == "") ? "00000" : scoreQutz.getSid());
            values.add(scoreQutz.getQutzAmount() == null ? 0 : scoreQutz.getQutzAmount());
            values.add(scoreQutz.getImgUrl());
            values.add(scoreQutz.getIsShare());
            scoreQutzDao.executeSql(str.toString(), values);
        }
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(options);
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            ScoreQutzOption qutzOption = (ScoreQutzOption) JSONObject.toBean(jsonObj, ScoreQutzOption.class);
            String optionId = qutzOption.getOptionId();
            if (CommonUtil.isNotEmpty(optionId)) {
                StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ_OPTION SET QUTZ_ID=?,OPTION_NOTE=? ,UPDATE_TIME=sysdate where OPTION_ID=?");
                List<Object> values = new ArrayList<Object>();
                values.add(qutzId);
                values.add(qutzOption.getOptionNote());
                values.add(optionId);
                scoreQutzDao.executeSql(str.toString(), values);
            } else {
                optionId = formatter.format(new Date()) + CommonUtil.random(6);
                StringBuffer str = new StringBuffer("insert into SCORE_QUTZ_OPTION(ID, OPTION_ID,QUTZ_ID,OPTION_NOTE,OPTION_ORDER,CREATE_TIME,UPDATE_TIME)values(SEQ_SCORE_QUTZ_OPTION.NEXTVAL,?,?,?,?,sysdate,sysdate)");
                List<Object> values = new ArrayList<Object>();
                values.add(optionId);
                values.add(qutzId);
                values.add(qutzOption.getOptionNote());
                values.add(i + 1);
                scoreQutzDao.executeSql(str.toString(), values);
            }

        }
    }

    /**
     * 竞猜活动状态修改
     *
     * @param qutzId
     * @param status
     */
    public boolean updateQutzActivityStatus(String qutzId, Integer status, Integer bonusStatus) {
        StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ set STATUS=?,BONUS_STATUS=? where QUTZ_ID=? ");
        List<Object> values = new ArrayList<Object>();
        values.add(status);
        values.add(bonusStatus);
        values.add(qutzId);
        int result = scoreQutzDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 竞猜活动详情
     *
     * @param qutzId
     * @return
     */
    public ScoreQutzVO qutzActivityInfo(String qutzId, String qutzIssue) {
        StringBuffer str = new StringBuffer(" select q.QUTZ_ID,q.QUTZ_NAME,q.QUTZ_ISSUE,q.QUTZ_TYPE_ID,t.QUTZ_TYPE_NAME QUTZ_TYPE,q.BONUS_TYPE,q.STATUS,q.BONUS_STATUS,q.IMG_URL,q.IS_SHARE,");
        str.append(" q.START_TIME,q.END_TIME,q.QUTZ_TOPIC,q.QUTZ_AMOUNT,q.ATTEND_NUMBERS,q.ATTEND_AMOUNT,q.LAST_BONUS_AMOUNT,q.QUTZ_ANSWER_ID,q.QUTZ_ANSWER_NOTE,q.VIP_LOW,q.VIP_HIGH,c.NAME sname from SCORE_QUTZ q ");
        str.append(" left join SCORE_QUTZ_TYPE t on q.QUTZ_TYPE_ID=t.QUTZ_TYPE_ID  ");
        str.append(" left join USER_COOPERATION c on q.SID=c.SID where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        if (CommonUtil.isNotEmpty(qutzId)) {
            str.append(" and q.QUTZ_ID=? ");
            values.add(qutzId);
        }
        if (CommonUtil.isNotEmpty(qutzIssue)) {
            str.append(" and q.QUTZ_ISSUE=? ");
            values.add(qutzIssue);
        }
        List<Map> mapList = scoreQutzDao.findBySql(str.toString(), values);
        if (mapList != null && mapList.size() > 0) {
            Map map = mapList.get(0);
            ScoreQutzVO vo = new ScoreQutzVO();
            vo.setQutzId((String) map.get("QUTZ_ID"));
            vo.setQutzName((String) map.get("QUTZ_NAME"));
            vo.setQutzIssue((String) map.get("QUTZ_ISSUE"));
            vo.setQutzTopic((String) map.get("QUTZ_TOPIC"));
            vo.setQutzType((String) map.get("QUTZ_TYPE"));
            vo.setQutzTypeId((String) map.get("QUTZ_TYPE_ID"));
            BigDecimal bonusType = (BigDecimal) map.get("BONUS_TYPE");
            vo.setBonusType(bonusType.intValue());
            vo.setQutzAmount(CommonUtil.round(map.get("QUTZ_AMOUNT"), 2));
            vo.setAttendAmount(CommonUtil.round(map.get("ATTEND_AMOUNT"), 2));
            BigDecimal attendNumbers = (BigDecimal) map.get("ATTEND_NUMBERS");
            vo.setAttendNumbers(attendNumbers == null ? 0 : attendNumbers.intValue());
            vo.setLastBonusAmount(CommonUtil.round(map.get("LAST_BONUS_AMOUNT"), 2));
            Date startTime = (Date) map.get("START_TIME");
            vo.setStartTime(DateUtil.format(startTime, "yy-MM-dd HH:mm:ss"));
            Date endTime = (Date) map.get("END_TIME");
            vo.setEndTime(DateUtil.format(endTime, "yy-MM-dd HH:mm:ss"));
            BigDecimal status = (BigDecimal) map.get("STATUS");
            vo.setStatus(status.intValue());
            BigDecimal bonusStatus = (BigDecimal) map.get("BONUS_STATUS");
            vo.setBonusStatus(bonusStatus.intValue());
            vo.setQutzAnswerId((String) map.get("QUTZ_ANSWER_ID"));
            vo.setQutzAnswerNote((String) map.get("QUTZ_ANSWER_NOTE"));
            BigDecimal vipLow = (BigDecimal) map.get("VIP_LOW");
            vo.setVipLow(vipLow.intValue());
            BigDecimal vipHigh = (BigDecimal) map.get("VIP_HIGH");
            vo.setVipHigh(vipHigh.intValue());
            vo.setSid((String) map.get("SNAME"));
            vo.setImgUrl((String)map.get("IMG_URL"));
            BigDecimal isShare=(BigDecimal) map.get("IS_SHARE");
            vo.setIsShare(isShare==null?0:isShare.intValue());
            return vo;
        }
        return null;
    }

    /**
     * 竞猜答案保存
     *
     * @param scoreQutz
     * @return
     */
    public boolean saveQutzAnswer(ScoreQutzVO scoreQutz) {
        StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ SET QUTZ_ANSWER_ID=?,QUTZ_ANSWER_NOTE=? where QUTZ_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(scoreQutz.getQutzAnswerId());
        values.add(scoreQutz.getQutzAnswerNote());
        values.add(scoreQutz.getQutzId());
        int result = scoreQutzDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 竞猜活动删除
     *
     * @param qutzId
     * @return
     */
    public boolean delQutzActivity(String qutzId) {
        StringBuffer str = new StringBuffer("DELETE FROM SCORE_QUTZ WHERE QUTZ_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(qutzId);
        int result = scoreQutzDao.executeSql(str.toString(), values);
        if (result > 0) {
            str = new StringBuffer("DELETE FROM SCORE_QUTZ_OPTION WHERE QUTZ_ID=?");
            scoreQutzDao.executeSql(str.toString(), values);
            return true;
        }
        return false;

    }
    /**
	  * 竞猜活动隐藏
	  * @param qutzId
	  * @return
	  */
	 public boolean qutzActivityHide(String qutzId,Integer status){
		 StringBuffer str=new StringBuffer(" UPDATE SCORE_QUTZ SET IS_HIDE=? WHERE QUTZ_ID=? ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(status);
	     values.add(qutzId);
	     int result = scoreQutzDao.executeSql(str.toString(), values);
	     if (result > 0) {
	        return true;
	    }
	    return false;
	 }
	 
	 /**
	  * 竞猜活动分享设置
	  * @param qutzId
	  * @param status
	  * @return
	  */
	 public boolean qutzActivityShare(String qutzId,Integer status){

		 StringBuffer str=new StringBuffer(" UPDATE SCORE_QUTZ SET IS_SHARE=? WHERE QUTZ_ID=? ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(status);
	     values.add(qutzId);
	     int result = scoreQutzDao.executeSql(str.toString(), values);
	     if (result > 0) {
	        return true;
	    }
	    return false;
	 
	 }
	 
	 /**
	  * 竞猜活动推荐
	  * @param qutzId
	  * @return
	  */
	 public boolean activityRecommend(String qutzId,Integer isRecommend){
		 StringBuffer str=new StringBuffer(" UPDATE SCORE_QUTZ SET IS_RECOMMEND=? WHERE QUTZ_ID=? ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(isRecommend);
	     values.add(qutzId);
	     int result = scoreQutzDao.executeSql(str.toString(), values);
	     if (result > 0) {
	        return true;
	    }
	    return false;
	 }
    /**
     * 竞猜答案删除
     *
     * @param optionId
     * @return
     */
    public boolean delScoreQutzAnswer(String optionId) {
        StringBuffer str = new StringBuffer("DELETE FROM SCORE_QUTZ_OPTION WHERE OPTION_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(optionId);
        int result = scoreQutzDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 积分竞猜活动日志
     *
     * @param dg
     * @param vo
     * @return
     */
    public EasyuiDataGridJson scoreQutzLog(EasyuiDataGrid dg, ScoreQutzUserVO vo) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuffer str = new StringBuffer(" select qu.QUTZ_USER_ID,qu.QUTZ_ID,qu.OPTION_ID,qu.QUTZ_ISSUE,u.USER_NAME,qu.USER_CODE,qu.AMOUNT,");
        str.append(" qu.BONUS_AMOUNT,qu.STATUS,qu.CREATE_TIME,qu.UPDATE_TIME,qu.BONUS_TIME,qt.QUTZ_TYPE_NAME,q1.OPTION_ORDER trueOrder,sq.QUTZ_ANSWER_NOTE trueNote, q2.OPTION_ORDER ,q2.OPTION_NOTE  ");
        str.append(" from SCORE_QUTZ_USER qu left join SCORE_QUTZ sq on qu.QUTZ_ID=sq.QUTZ_ID ");
        str.append(" left join SCORE_QUTZ_TYPE qt on sq.QUTZ_TYPE_ID =qt.QUTZ_TYPE_ID ");
        str.append(" left join SCORE_QUTZ_OPTION q1 on sq.QUTZ_ANSWER_ID=q1.OPTION_ID ");
        str.append(" left join SCORE_QUTZ_OPTION q2 on qu.OPTION_ID=q2.OPTION_ID ");
        str.append(" left join USER_MEMBER u on qu.USER_CODE=u.USER_CODE where 1=1 ");

        List<Object> values = new ArrayList<Object>();
        if (vo != null) {
            // 是否模糊查询
            boolean flag = vo.isFlag();
            // 流水编号
            if (CommonUtil.isNotEmpty(vo.getQutzUserId())) {
                if (flag) {
                    str.append(" and qu.QUTZ_USER_ID like ? ");
                    values.add("%" + vo.getQutzUserId().trim() + "%");
                } else {
                    str.append(" and qu.QUTZ_USER_ID = ? ");
                    values.add(vo.getQutzUserId().trim());
                }
            }
            // 用户名称
            if (CommonUtil.isNotEmpty(vo.getUserName())) {
                if (flag) {
                    str.append(" and u.user_name like ? ");
                    values.add("%" + vo.getUserName().trim() + "%");
                } else {
                    str.append(" and u.user_name = ? ");
                    values.add(vo.getUserName().trim());
                }
            }
            // 参与期次
            if (CommonUtil.isNotEmpty(vo.getQutzIssue())) {
                if (flag) {
                    str.append(" and qu.QUTZ_ISSUE like ? ");
                    values.add("%" + vo.getQutzIssue().trim() + "%");
                } else {
                    str.append(" and qu.QUTZ_ISSUE = ? ");
                    values.add(vo.getQutzIssue().trim());
                }
            }
            //竞猜类型
            if (CommonUtil.isNotEmpty(vo.getQutzTypeId())) {
                str.append(" and qt.QUTZ_TYPE_ID = ? ");
                values.add(vo.getQutzTypeId());
            }
            //参与状态
            if (CommonUtil.isNotEmpty(vo.getStatus())) {
                str.append(" and qu.STATUS = ? ");
                values.add(vo.getStatus());
            }
            //参与时间start
            if (CommonUtil.isNotEmpty(vo.getCreateTimeStart())) {
                str.append(" and qu.CREATE_TIME >= ? ");
                values.add(DateUtil.format(vo.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //参与时间end
            if (CommonUtil.isNotEmpty(vo.getCreateTimeEnd())) {
                str.append(" and qu.CREATE_TIME <= ? ");
                values.add(DateUtil.format(vo.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //处理时间start
            if (CommonUtil.isNotEmpty(vo.getUpdateTimeStart())) {
                str.append(" and qu.UPDATE_TIME >= ? ");
                values.add(DateUtil.format(vo.getUpdateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //处理时间end
            if (CommonUtil.isNotEmpty(vo.getUpdateTimeEnd())) {
                str.append(" and qu.UPDATE_TIME <= ? ");
                values.add(DateUtil.format(vo.getUpdateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(scoreQutzDao.countBySql(totalHql, values).longValue());// 设置总记录数

        str.append(" order by  qu.BONUS_TIME desc");
        List<Map> mapList = scoreQutzDao.findBySql(str.toString(), values,
                dg.getPage(), dg.getRows());// 查询分页
        List<ScoreQutzUserVO> qutzUserLogs = new ArrayList<ScoreQutzUserVO>();
        if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
                ScoreQutzUserVO qutzvo = new ScoreQutzUserVO();
                qutzvo.setQutzUserId((String) map.get("QUTZ_USER_ID"));
                qutzvo.setUserCode((String) map.get("USER_CODE"));
                qutzvo.setUserName((String) map.get("USER_NAME"));
                Date createTime = (Date) map.get("CREATE_TIME");
                qutzvo.setCreateTime(DateUtil.format(createTime));
                qutzvo.setQutzType((String) map.get("QUTZ_TYPE_NAME"));
                qutzvo.setQutzIssue((String) map.get("QUTZ_ISSUE"));
                Date bonusTime = (Date) map.get("BONUS_TIME");
                qutzvo.setBonusTime(DateUtil.format(bonusTime));
                qutzvo.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")));
                qutzvo.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
                BigDecimal trueOrder = (BigDecimal) map.get("TRUEORDER");
                if (trueOrder != null) {
                    qutzvo.setTrueOrder(trueOrder.intValue());
                }
                qutzvo.setTrueNote((String) map.get("TRUENOTE"));
                qutzvo.setOptionNote((String) map.get("OPTION_NOTE"));
                BigDecimal optionOrder = (BigDecimal) map.get("OPTION_ORDER");
                if (optionOrder != null) {
                    qutzvo.setOptionOrder(optionOrder.intValue());

                }
                BigDecimal status = (BigDecimal) map.get("STATUS");
                qutzvo.setStatus(status.intValue());
                qutzUserLogs.add(qutzvo);
            }
        }
        j.setRows(qutzUserLogs);// 设置返回的行
        return j;
    }

    /**
     * 积分竞猜时间更新
     *
     * @param qutzId
     * @param endTime
     * @return
     */
    public void updateActivityTime(String qutzId, String endTime) {
        StringBuffer str = new StringBuffer("UPDATE SCORE_QUTZ set END_TIME=? where QUTZ_ID=?");
        List<Object> values = new ArrayList<Object>();
        values.add(DateUtil.format(endTime, "yyyy-MM-dd HH:mm:ss"));
        values.add(qutzId);
        scoreQutzDao.executeSql(str.toString(), values);

    }
    
    /**
	  * 积分竞猜中奖用户
	  * @param qutzId
	  * @return
	  */
	 public EasyuiDataGridJson qutzBonusUser(EasyuiDataGrid dg,String qutzId){
		    EasyuiDataGridJson j = new EasyuiDataGridJson();
		    StringBuffer str = new StringBuffer(" select qu.QUTZ_USER_ID,qu.QUTZ_ID,qu.OPTION_ID,qu.QUTZ_ISSUE,u.USER_NAME,qu.USER_CODE,qu.AMOUNT,");
	        str.append(" qu.BONUS_AMOUNT,qu.STATUS,qu.CREATE_TIME,qu.UPDATE_TIME,qu.BONUS_TIME,q2.OPTION_ORDER ,q2.OPTION_NOTE  ");
	        str.append(" from SCORE_QUTZ_USER qu  left join SCORE_QUTZ_OPTION q2 on qu.OPTION_ID=q2.OPTION_ID ");
	        str.append(" left join USER_MEMBER u on qu.USER_CODE=u.USER_CODE where qu.STATUS=2 and qu.QUTZ_ID= ? ");
	        List<Object> values = new ArrayList<Object>();
	        values.add(qutzId);
	        String totalHql = " select count(*)  from (" + str.toString() + ")";
	        j.setTotal(scoreQutzDao.countBySql(totalHql, values).longValue());// 设置总记录数

	        str.append(" order by  qu.BONUS_TIME desc");
	        List<Map> mapList = scoreQutzDao.findBySql(str.toString(), values,
	                dg.getPage(), dg.getRows());// 查询分页
	        List<ScoreQutzUserVO> qutzUsers= new ArrayList<ScoreQutzUserVO>();
	        if (mapList != null && mapList.size() > 0) {// 转换模型
	            for (Map map : mapList) {
	                ScoreQutzUserVO qutzvo = new ScoreQutzUserVO();
	                qutzvo.setQutzUserId((String) map.get("QUTZ_USER_ID"));
	                qutzvo.setUserCode((String) map.get("USER_CODE"));
	                qutzvo.setUserName((String) map.get("USER_NAME"));
	                Date createTime = (Date) map.get("CREATE_TIME");
	                qutzvo.setCreateTime(DateUtil.format(createTime));
	                qutzvo.setQutzType((String) map.get("QUTZ_TYPE_NAME"));
	                qutzvo.setQutzIssue((String) map.get("QUTZ_ISSUE"));
	                Date bonusTime = (Date) map.get("BONUS_TIME");
	                qutzvo.setBonusTime(DateUtil.format(bonusTime));
	                qutzvo.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")));
	                qutzvo.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
	                qutzvo.setOptionNote((String) map.get("OPTION_NOTE"));
	                BigDecimal optionOrder = (BigDecimal) map.get("OPTION_ORDER");
	                if (optionOrder != null) {
	                    qutzvo.setOptionOrder(optionOrder.intValue());

	                }
	                BigDecimal status = (BigDecimal) map.get("STATUS");
	                qutzvo.setStatus(status.intValue());
	                qutzUsers.add(qutzvo);
	            }
	        }
	        j.setRows(qutzUsers);// 设置返回的行
	        return j;
	 }

	@Override
	public List<ScoreQutz> selectAllScoreQutz() {
		return scoreQutzForInfoDao.find("from ScoreQutz where status=1");
	}
}
