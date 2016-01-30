package com.cm.manage.dao.operate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IUserTaskDao;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskVO;
@Repository("userTaskDao")
public class UserTaskDaoImpl implements IUserTaskDao {
	@Autowired
	private IBaseDao<Map> userTaskInfoDao;

	@Override
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg,
			UserTaskVO userTaskVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select T.USER_TASK_ID as USERTASKID,T.TASK_ID as TASKID,T.NAME as NAME,T.ACCEPT_TIME as ACCEPTTIME,");
		sb.append("T.COMPLETE_TIME as COMPLETETIME,T.STATUS as STATUS,T.START_TIME as STARTTIME,T.END_TIME as ENDTIME,");
		sb.append("R.FUNDING_TYPE as FUNDDINGTYPE,R.AMOUNT as REWARDAMOUNT,R.ACCOUNT_NAME as REWARDACCOUNTNAME,U.USER_NAME as USERNAME ");
		sb.append("from TASK_USER_COMPLETION T,TASK_RESULT_REWARD R,USER_MEMBER U where ");
		sb.append("T.TASK_ID=R.TASK_ID and T.USER_CODE=U.USER_CODE ");
		List<Object> param = new ArrayList<Object>();
		
		
		if(userTaskVO != null){
			boolean flag = userTaskVO.getFlag();
			if(CommonUtil.isNotEmpty(userTaskVO.getUserTaskId())){
				sb.append(" and T.USER_TASK_ID = ? ");
				param.add(userTaskVO.getUserTaskId());
			}
			if (CommonUtil.isNotEmpty(userTaskVO.getTaskId())) {
				sb.append(" and T.TASK_ID = ? ");
				param.add(userTaskVO.getTaskId());
			}
			if (CommonUtil.isNotEmpty(userTaskVO.getStatus())) {
				sb.append(" and T.STATUS = ?");
				param.add(userTaskVO.getStatus());
			}
			if (CommonUtil.isNotEmpty(userTaskVO.getFunddingType())) {
				sb.append(" and R.FUNDING_TYPE = ? ");
				param.add(userTaskVO.getFunddingType());
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getUserName())){
				if(flag){
					sb.append(" and U.USER_NAME like ? ");
                    param.add("%" + userTaskVO.getUserName() + "%");
				}else{
					sb.append(" and U.USER_NAME = ? ");
                    param.add(userTaskVO.getUserName());
				}
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getName())){
				if (flag) {
                    sb.append(" and T.NAME like ? ");
                    param.add("%" + userTaskVO.getName() + "%");
                } else {
                    sb.append(" and T.NAME = ? ");
                    param.add(userTaskVO.getName());
                }
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getRewardAccountName())){
				if (flag) {
                    sb.append(" and R.ACCOUNT_NAME like ? ");
                    param.add("%" + userTaskVO.getRewardAccountName() + "%");
                } else {
                    sb.append(" and R.ACCOUNT_NAME = ? ");
                    param.add(userTaskVO.getRewardAccountName());
                }
			}
			
			String fromSeconds = " 00:00:00";
			String toSeconds = " 23:59:59";
			
			if (CommonUtil.isNotEmpty(userTaskVO.getStartAcceptDate())) {
               sb.append(" and T.ACCEPT_TIME >= ?");
               param.add(DateUtil.format(userTaskVO.getStartAcceptDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getEndAcceptDate())) {
               sb.append(" and T.ACCEPT_TIME <= ?");
               param.add(DateUtil.format(userTaskVO.getEndAcceptDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getStartCompleteDate())) {
               sb.append(" and T.COMPLETE_TIME >= ?");
               param.add(DateUtil.format(userTaskVO.getStartCompleteDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getStartCompleteDate())) {
               sb.append(" and T.COMPLETE_TIME <= ?");
               param.add(DateUtil.format(userTaskVO.getStartCompleteDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(userTaskInfoDao.countBySql(totalHql,param).longValue());
		sb.append(" order by T.TASK_ORDER,T.CREATE_TIME desc ");
		List<Map> userTaskInfoList = userTaskInfoDao.findBySql(sb.toString(), param, dg.getPage(), dg.getRows());
		List<UserTaskVO> userTaskVOList = new ArrayList<UserTaskVO>();
		if(userTaskInfoList != null && userTaskInfoList.size() > 0){
			for(Map map:userTaskInfoList){
				UserTaskVO taskVO = new UserTaskVO();
				taskVO.setTaskId((String)map.get("TASKID"));
				taskVO.setName((String)map.get("NAME"));
				taskVO.setUserTaskId((String)map.get("USERTASKID"));
				taskVO.setUserName((String)map.get("USERNAME"));
				Date acceptTime = (Date)map.get("ACCEPTTIME");
				Date completeTime = (Date)map.get("COMPLETETIME");
				if(acceptTime != null){
					taskVO.setAcceptTime(DateUtil.format(acceptTime));
				}else{
					taskVO.setAcceptTime("");
				}
				if(completeTime != null){
					taskVO.setCompleteTime(DateUtil.format(completeTime));
				}else{
					taskVO.setCompleteTime("");
				}
				Date startTime = (Date)map.get("STARTTIME");
				Date endTime = (Date)map.get("ENDTIME");
				taskVO.setStartTime(DateUtil.format(startTime));
				taskVO.setEndTime(DateUtil.format(endTime));
				taskVO.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
				taskVO.setRewardAccountName((String)map.get("REWARDACCOUNTNAME"));
				taskVO.setRewardAmount(CommonUtil.formatDouble(map.get("REWARDAMOUNT")).doubleValue());
				int funddingType = CommonUtil.formatDouble(map.get("FUNDDINGTYPE")).intValue();
				taskVO.setFunddingType(funddingType);
				if(funddingType == CommonConstants.FUNDINGTYPE_PRESIENT_FLAG){//红包
					String amount = String.valueOf(CommonUtil.formatDouble(map.get("REWARDAMOUNT")));
					taskVO.setRewardAmountText(amount + "元");
				}else{
					taskVO.setRewardAmountText(String.valueOf(CommonUtil.formatDouble(map.get("REWARDAMOUNT")).intValue()));
				}
				taskVO.setFunddingTypeName(CommonBusiUtil.getFunddingTypeName(funddingType));
				userTaskVOList.add(taskVO);
			}
		}
		j.setRows(userTaskVOList);
		return j;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map countByFunddingType(UserTaskVO userTaskVO) {
		StringBuilder sb = new StringBuilder("select sum(case when t.status=1 then r.amount else 0 end) as notreward,");
		sb.append("sum(case when t.status=2 then r.amount else 0 end) as hasreward,");
		sb.append("sum(case when t.status=0 then 1 else 0 end) as doingnum,");
		sb.append("sum(case when t.status=1 then 1 else 0 end) as notrewardnum,");
		sb.append("sum(case when t.status=2 then 1 else 0 end) as hasrewardnum");
		sb.append(" from task_user_completion t ,task_result_reward r where t.task_id=r.task_id ");
		List<Object> param = new ArrayList<Object>();
		if(userTaskVO != null){
			boolean flag = userTaskVO.getFlag();
			if (CommonUtil.isNotEmpty(userTaskVO.getTaskId())) {
				sb.append(" and T.TASK_ID = ? ");
				param.add(userTaskVO.getTaskId());
			}
			if (CommonUtil.isNotEmpty(userTaskVO.getStatus())) {
				sb.append(" and T.STATUS = ?");
				param.add(userTaskVO.getStatus());
			}
			if (CommonUtil.isNotEmpty(userTaskVO.getFunddingType())) {
				sb.append(" and R.FUNDING_TYPE = ? ");
				param.add(userTaskVO.getFunddingType());
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getUserName())){
				if(flag){
					sb.append(" and U.USER_NAME like ? ");
                    param.add("%" + userTaskVO.getUserName() + "%");
				}else{
					sb.append(" and U.USER_NAME = ? ");
                    param.add(userTaskVO.getUserName());
				}
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getName())){
				if (flag) {
                    sb.append(" and T.NAME like ? ");
                    param.add("%" + userTaskVO.getName() + "%");
                } else {
                    sb.append(" and T.NAME = ? ");
                    param.add(userTaskVO.getName());
                }
			}
			if(CommonUtil.isNotEmpty(userTaskVO.getRewardAccountName())){
				if (flag) {
                    sb.append(" and R.ACCOUNT_NAME like ? ");
                    param.add("%" + userTaskVO.getRewardAccountName() + "%");
                } else {
                    sb.append(" and R.ACCOUNT_NAME = ? ");
                    param.add(userTaskVO.getRewardAccountName());
                }
			}
			String fromSeconds = " 00:00:00";
			String toSeconds = " 23:59:59";
			
			if (CommonUtil.isNotEmpty(userTaskVO.getStartAcceptDate())) {
               sb.append(" and T.ACCEPT_TIME >= ?");
               param.add(DateUtil.format(userTaskVO.getStartAcceptDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getEndAcceptDate())) {
               sb.append(" and T.ACCEPT_TIME <= ?");
               param.add(DateUtil.format(userTaskVO.getEndAcceptDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getStartCompleteDate())) {
               sb.append(" and T.COMPLETE_TIME >= ?");
               param.add(DateUtil.format(userTaskVO.getStartCompleteDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(userTaskVO.getStartCompleteDate())) {
               sb.append(" and T.COMPLETE_TIME <= ?");
               param.add(DateUtil.format(userTaskVO.getStartCompleteDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }
		}
		List<Map> resultList = userTaskInfoDao.findBySql(sb.toString(), param);
		Integer funddingType = userTaskVO.getFunddingType();
		if(resultList != null && resultList.size() > 0){
			Map map = resultList.get(0);
			if(CommonConstants.FUNDINGTYPE_PRESIENT_FLAG == funddingType){
				if(CommonUtil.formatDouble(map.get("NOTREWARD")) == 0.0){
					map.put("NOTREWARD", "0.0");
				}else{
					map.put("NOTREWARD",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("NOTREWARD"))));
				}
				if(CommonUtil.formatDouble(map.get("HASREWARD")) == 0.0){
					map.put("HASREWARD", "0.0");
				}else{
					map.put("HASREWARD",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("HASREWARD"))));
				}
			}else{
				map.put("NOTREWARD", CommonUtil.formatDouble(map.get("NOTREWARD")).intValue());
				map.put("HASREWARD", CommonUtil.formatDouble(map.get("HASREWARD")).intValue());
			}
			int doingNum = CommonUtil.formatDouble(map.get("DOINGNUM")).intValue(); 
			int notRewardNum = CommonUtil.formatDouble(map.get("NOTREWARDNUM")).intValue(); 
			int hasRewardNum = CommonUtil.formatDouble(map.get("HASREWARDNUM")).intValue(); 
			map.put("DOINGNUM", doingNum);
			map.put("NOTREWARDNUM", notRewardNum);
			map.put("HASREWARDNUM", hasRewardNum);
			return map;
		}
		return null;
	}

	public static void main(String[] args){
		StringBuilder sb = new StringBuilder("select sum(case when t.status=1 then r.amount else 0 end) as notreward,");
		sb.append("sum(case when t.status=2 then r.amount else 0 end) as hasreward,");
		sb.append("sum(case when t.status=0 then 1 else 0 end) as doingnum,");
		sb.append("sum(case when t.status=1 then 1 else 0 end) as notrewardnum,");
		sb.append("sum(case when t.status=2 then 1 else 0 end) as hasrewardnum");
		sb.append(" from task_user_completion t ,task_result_reward r where t.task_id=r.task_id ");
		System.out.println(sb.toString());
	}
}
