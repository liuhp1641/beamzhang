package com.cm.manage.dao.operate.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IIssueForjczqDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.IssueForjczqVO;

@Repository
public class IssueForjczqDaoImpl implements IIssueForjczqDao {
	
	@Autowired
	private IBaseDao<Object> issueDao;

	@Override
	public EasyuiDataGridJson issueForjczqList(EasyuiDataGrid dg,
			IssueForjczqVO issue) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select ID,ISSUE,SN,WEEK,MATCH_ID,END_TIME,MATCH_NAME,");
		sb.append(" MAIN_TEAM,GUEST_TEAM,END_OPERATOR,BONUS_OPERATOR,MAIN_TEAM_HALF_SCORE,GUEST_TEAM_HALF_SCORE,");
		sb.append(" MAIN_TEAM_SCORE,GUEST_TEAM_SCORE,LET_BALL,END_FU_SHI_TIME,END_STATUS,BACKUP1,BACKUP2,BACKUP3  ");
		sb.append(" from TMS_SUB_ISSUE_FOR_JCZQ  where  PLAY_CODE='00' and  POLL_CODE='02'");
		List<Object> values = new ArrayList<Object>();
		if (issue != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = issue.isFlag();
			// 赛事编号
			if (CommonUtil.isNotEmpty(issue.getSn())) {
				if (flag) {
					sb.append(" and SN like ? ");
					values.add("%" + issue.getSn().trim() + "%");
				} else {
					sb.append(" and SN = ? ");
					values.add(issue.getSn().trim());
				}
			}

			// 主队
			if (CommonUtil.isNotEmpty(issue.getMainTeam())) {
				if (flag) {
					sb.append(" and MAIN_TEAM like ? ");
					values.add("%" + issue.getMainTeam().trim() + "%");
				} else {
					sb.append(" and MAIN_TEAM = ? ");
					values.add(issue.getMainTeam().trim());
				}
			}

		
			// 客队
			if (CommonUtil.isNotEmpty(issue.getGuestTeam())) {
				if (flag) {
					sb.append(" and GUEST_TEAM like ? ");
					values.add("%" + issue.getGuestTeam().trim() + "%");
				} else {
					sb.append(" and GUEST_TEAM = ? ");
					values.add(issue.getGuestTeam().trim());
				}
			}
			
			
			// 赛事日期
			if (CommonUtil.isNotEmpty(issue.getStartTime())) {
				sb.append(" and END_TIME >= ?");
				Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(issue.getStartTime(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); 
                values.add(cal.getTime());
			}
			if (CommonUtil.isNotEmpty(issue.getEndTime())) {
				sb.append(" and END_TIME <= ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(issue.getEndTime(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
				values.add(cal.getTime());
			}
			// 联赛名称
			
			if (CommonUtil.isNotEmpty(issue.getMatchName())) {
				if (flag) {
					sb.append(" and MATCH_NAME like ? ");
					values.add("%" + issue.getMatchName().trim() + "%");
				} else {
					sb.append(" and MATCH_NAME = ? ");
					values.add(issue.getMatchName().trim());
				}
			}
			//销售状态
			if (CommonUtil.isNotEmpty(issue.getEndOperator())) {
				sb.append(" and END_OPERATOR =? ");
				values.add(issue.getEndOperator());
			}
			//算奖状态
			if (CommonUtil.isNotEmpty(issue.getBonusOperator())) {
				sb.append(" and BONUS_OPERATOR =? ");
				values.add(issue.getBonusOperator());
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(issueDao.countBySql(totalHql, values).longValue());// 设置总记录数
		
		sb.append(" order by ISSUE,SN desc");
		List<Map> issueMapList = issueDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<IssueForjczqVO> issueList = new ArrayList<IssueForjczqVO>();
		if (issueMapList != null && issueMapList.size() > 0) {// 转换模型
			for (Map map : issueMapList) {
				IssueForjczqVO vo = new IssueForjczqVO();
				BigDecimal id=(BigDecimal)map.get("ID");
				vo.setId(id.longValue());
				vo.setIssue((String)map.get("ISSUE"));
				vo.setSn((String)map.get("SN"));
				vo.setWeek((String)map.get("WEEK"));
				vo.setMatchId((String)map.get("MATCH_ID"));
				Date endTime = (Date) map.get("END_TIME");
				vo.setEndTime(DateUtil.format(endTime));
				vo.setMatchName((String)map.get("MATCH_NAME"));
				vo.setMainTeam((String)map.get("MAIN_TEAM"));
				vo.setGuestTeam((String)map.get("GUEST_TEAM"));
				vo.setEndOperator(((BigDecimal)map.get("END_OPERATOR")).intValue());
				vo.setBonusOperator(((BigDecimal)map.get("BONUS_OPERATOR")).intValue());
				BigDecimal mainTeamHalfScore=map.get("MAIN_TEAM_HALF_SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("MAIN_TEAM_HALF_SCORE");
				vo.setMainTeamHalfScore(mainTeamHalfScore.intValue());
				BigDecimal guestTeamHalfScore=map.get("GUEST_TEAM_HALF_SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("GUEST_TEAM_HALF_SCORE");
				vo.setGuestTeamHalfScore(guestTeamHalfScore.intValue());
				BigDecimal mainTeamScore=map.get("MAIN_TEAM_SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("MAIN_TEAM_SCORE");
				vo.setMainTeamScore(mainTeamScore.intValue());
				BigDecimal guestTeamScore=map.get("GUEST_TEAM_SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("GUEST_TEAM_SCORE");
				vo.setGuestTeamScore(guestTeamScore.intValue());
				vo.setLetBall((String)map.get("LET_BALL"));
				Date endFuShiTime=(Date) map.get("END_FU_SHI_TIME");
				vo.setEndFuShiTime(DateUtil.format(endFuShiTime));
				vo.setEndStatus(((BigDecimal) map.get("END_STATUS")).intValue());
				vo.setBackup1((String)map.get("BACKUP1"));
				vo.setBackup2((String)map.get("BACKUP2"));
				vo.setBackup3((String)map.get("BACKUP3"));
				issueList.add(vo);
			}
		}
		j.setRows(issueList);// 设置返回的行
		return j;
	}
	
	 /**
	  * 竞彩足球详情
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public Map issueForjczqInfo(String issue,String sn){
		 StringBuilder sb = new StringBuilder("select ID,ISSUE,SN,WEEK,MATCH_ID,END_TIME,MATCH_NAME,PLAY_CODE,POLL_CODE,");
		 sb.append(" MAIN_TEAM,GUEST_TEAM,END_OPERATOR,BONUS_OPERATOR,MAIN_TEAM_HALF_SCORE,GUEST_TEAM_HALF_SCORE,");
		 sb.append(" MAIN_TEAM_SCORE,GUEST_TEAM_SCORE,LET_BALL,END_FU_SHI_TIME,END_STATUS,BACKUP1,BACKUP2,LABEL  ");
		 sb.append(" from TMS_SUB_ISSUE_FOR_JCZQ  where ISSUE=? and SN=? ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(issue);
		 values.add(sn);
		 List<Map> issueMapList=issueDao.findBySql(sb.toString(), values);
		 if(issueMapList!=null&&issueMapList.size()>0){
			 Map issueMap=issueMapList.get(0);
			 Date endTime = (Date) issueMap.get("END_TIME");
			 issueMap.put("END_TIME", DateUtil.format(endTime));
			 for(int i=0;i<issueMapList.size();i++){
				 String playCode=(String) issueMapList.get(i).get("PLAY_CODE");
				 String pollCode=(String) issueMapList.get(i).get("POLL_CODE");
				 String label=(String) issueMapList.get(i).get("LABEL");
				 if("00".equals(playCode)&&"02".equals(pollCode)){
					 issueMap.put("mix", label);
				 }
				 if("04".equals(playCode)&&"01".equals(pollCode)){
					 issueMap.put("score", label);
				 }
				 if("05".equals(playCode)&&"01".equals(pollCode)){
					 issueMap.put("flat", label);
				 }
				 if("01".equals(playCode)&&"01".equals(pollCode)){
					 issueMap.put("letBall", label);
				 }
				 
			 }
			 return issueMap;
		 }
		 return null;
	 }
	 
	 /**
	  * 设置开赛时间
	  * @param issue
	  * @param sn
	  * @param endTime
	  * @return
	  */
	 public boolean editEndTime(String issue,String sn,String endTime){
		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET END_TIME=? WHERE ISSUE=? and SN=?  ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(DateUtil.format(endTime,"yy-MM-dd HH:mm:ss"));
		 values.add(issue);
		 values.add(sn);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 }
	 
	 /**
	  * 设置自动更新
	  * @param issue
	  * @param sn
	  * @param backup1
	  * @return
	  */
	 public boolean editBackup1(String issue,String sn,String backup1){
		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET BACKUP1=? WHERE ISSUE=? and SN=?   ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(backup1);
		 values.add(issue);
		 values.add(sn);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 }
	 
	 /**
	  * 是否取消比赛
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean editCancel(String issue,String sn){

		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET END_OPERATOR=2 WHERE ISSUE=? and SN=?   ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(issue);
		 values.add(sn);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 
	 }
	 /**
	  * 对阵推荐
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean issueRecommend(String issue,String sn,Integer backup3){
		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET BACKUP3=? WHERE ISSUE=? and SN=?   ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(backup3);
		 values.add(issue);
		 values.add(sn);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 }
	 /**
	  * 设置隐藏对阵
	  * @param issue
	  * @param sn
	  * @param backup2
	  * @return
	  */
	 public boolean editBackup2(String issue,String sn,String backup2){

		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET BACKUP2=? WHERE ISSUE=? and SN=?   ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(backup2);
		 values.add(issue);
		 values.add(sn);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 
	 }
	 
	 /**
	  * 设置对阵标签
	  * @param issue
	  * @param sn
	  * @param label
	  * @return
	  */
	 public boolean editLabel(String issue,String sn,String playCode,String pollCode, String label){
		 StringBuffer str=new StringBuffer("UPDATE TMS_SUB_ISSUE_FOR_JCZQ SET LABEL=? WHERE ISSUE=? and SN=? and PLAY_CODE=? and POLL_CODE=?");
		 List<Object> values = new ArrayList<Object>();
		 values.add(label);
		 values.add(issue);
		 values.add(sn);
		 values.add(playCode);
		 values.add(pollCode);
		 int result=issueDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		 return false;
	 }

}
