package com.cm.manage.dao.operate.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IActiveDao;
import com.cm.manage.model.operate.TaskExchange;
import com.cm.manage.model.operate.TaskExchangeDetail;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.ExchangeVO;
@Repository("activeDao")
public class ActiveDaoImpl implements IActiveDao {

	@Autowired
	private IBaseDao<TaskExchange> taskExchangeDao;
	@Autowired
	private IBaseDao<Map> taskExchangeMapDao;
	@Autowired
	private IBaseDao<TaskExchangeDetail> taskExchangeDetailDao;
	@Autowired
	private IBaseDao<Map> taskExchangeDetailMapDao;
	@Override
	public void saveTaskExchange(TaskExchange taskExchange) {
		taskExchangeDao.save(taskExchange);
		
	}
	@Override
	public void saveTaskExchangeDetail(TaskExchangeDetail detail) {
		taskExchangeDetailDao.save(detail);
		
	}
	@Override
	public List<TaskExchangeDetail> findExchangeDetailListById(
			String taskExchangeId) {
		String hql = "from TaskExchangeDetail t where t.exchangeId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		List<TaskExchangeDetail> detailList = taskExchangeDetailDao.find(hql, param);
		return detailList;
	}
	@Override
	public TaskExchange findTaskExchangeById(String taskExchangeId) {
		String hql = "from TaskExchange t where t.exchangeId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		List<TaskExchange> taskExchangeList = taskExchangeDao.find(hql, param);
		if(taskExchangeList != null && taskExchangeList.size() > 0){
			return taskExchangeList.get(0);
		}
		return null;
	}
	@Override
	public TaskExchangeDetail findTaskExchangeDetailById(
			String taskExchangeDetailId) {
		String hql = "from TaskExchangeDetail t where t.exchangeDetailId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeDetailId);
		List<TaskExchangeDetail> taskExchangeDetailList = taskExchangeDetailDao.find(hql, param);
		if(taskExchangeDetailList != null && taskExchangeDetailList.size() > 0){
			return taskExchangeDetailList.get(0);
		}
		return null;
	}
	@Override
	public void updateTaskExchange(TaskExchange taskExchange) {
		taskExchangeDao.update(taskExchange);
		
	}
	@Override
	public void updateTaskExchangeDetail(TaskExchangeDetail detail) {
		taskExchangeDetailDao.update(detail);
		
	}
	@Override
	public EasyuiDataGridJson activeList(EasyuiDataGrid dg,
			ExchangeVO exchangeVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select T.EXCHANGE_ID as EXCHANGEID,T.EXCHANGE_NAME as EXCHANGENAME,T.EXCHANGE_NOTE as EXCHANGENOTE,T.START_TIME as STARTTIME,");
		sb.append("T.END_TIME as ENDTIME,T.RETURN_TYPE as RETURNTYPE,T.STATUS as STATUS,T.EXCHANGE_USER_CODE as EXCHANGEUSERCODE,");
		sb.append("T.RETURN_NUMBERS as RETURNNUMBERS,T.EACH_NUMBER EACHNUMBER,T.EACH_UNIT as EACHUNIT,T.CREATE_TIME as CREATETIME,");
		sb.append("T.UPDATE_TIME as UPDATETIME,T.EXCHANGE_USER_NAME as EXCHANGEUSERNAME,T.SID as SID,T.SID_NAME as SIDNAME,");
		sb.append("T.EXCHANGE_GROUP as EXCHANGEGROUP,T.IS_BINDING_MOBILE as ISBINDINGMOBILE ");
		sb.append("from TASK_EXCHANGE T where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(exchangeVO != null){
			boolean flag = exchangeVO.getFlag();
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeId())){
				sb.append(" and T.EXCHANGE_ID=?");
				param.add(exchangeVO.getExchangeId());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getStatus())){
				sb.append(" and T.STATUS=?");
				param.add(exchangeVO.getStatus());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnType())){
				sb.append(" and T.RETURN_TYPE=?");
				param.add(exchangeVO.getReturnType());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeName())){
				if (flag) {
                    sb.append(" and T.EXCHANGE_NAME like ? ");
                    param.add("%" + exchangeVO.getExchangeName() + "%");
                } else {
                    sb.append(" and T.EXCHANGE_NAME = ? ");
                    param.add(exchangeVO.getExchangeName());
                }
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeUserName())){
				if (flag) {
                    sb.append(" and T.EXCHANGE_USER_NAME like ? ");
                    param.add("%" + exchangeVO.getExchangeUserName() + "%");
                } else {
                    sb.append(" and T.EXCHANGE_USER_NAME = ? ");
                    param.add(exchangeVO.getExchangeUserName());
                }
			}
			String fromSeconds = " 00:00:00";
			 String toSeconds = " 23:59:59";
			
			if (CommonUtil.isNotEmpty(exchangeVO.getStartFromDate())) {
               sb.append(" and T.START_TIME >= ?");
               param.add(DateUtil.format(exchangeVO.getStartFromDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(exchangeVO.getStartToDate())) {
               sb.append(" and T.START_TIME <= ?");
               param.add(DateUtil.format(exchangeVO.getStartToDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }

           if (CommonUtil.isNotEmpty(exchangeVO.getEndFromDate())) {
               sb.append(" and T.END_TIME >= ?");
               param.add(DateUtil.format(exchangeVO.getEndFromDate() + fromSeconds, "yy-MM-dd HH:mm:ss"));
           }
           if (CommonUtil.isNotEmpty(exchangeVO.getEndToDate())) {
               sb.append(" and T.END_TIME <= ?");
               param.add(DateUtil.format(exchangeVO.getEndToDate() + toSeconds, "yy-MM-dd HH:mm:ss"));
           }
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(taskExchangeMapDao.countBySql(totalHql,param).longValue());
		sb.append(" order by T.CREATE_TIME desc ");
		List<Map> activeInfoList = taskExchangeMapDao.findBySql(sb.toString(), param, dg.getPage(), dg.getRows());
		List<ExchangeVO> exchangeVOList = new ArrayList<ExchangeVO>();
		if(activeInfoList != null && activeInfoList.size() > 0){
			for(Map map : activeInfoList){
				ExchangeVO exchange = new ExchangeVO();
				exchange.setExchangeId((String)map.get("EXCHANGEID"));
				exchange.setExchangeName((String)map.get("EXCHANGENAME"));
				exchange.setExchangeNote((String)map.get("EXCHANGENOTE"));
				Date startTime = (Date) map.get("STARTTIME");
				if(startTime != null){
					exchange.setStartTime(DateUtil.format(startTime));
				}else{
					exchange.setStartTime("");
				}
				Date endTime = (Date) map.get("ENDTIME");
				if(endTime != null){
					exchange.setEndTime(DateUtil.format(endTime));
				}else{
					exchange.setEndTime("");
				}
				exchange.setExchangeGroup(CommonUtil.formatDouble(map.get("EXCHANGEGROUP")).intValue());
				exchange.setIsBindingMobile(CommonUtil.formatDouble(map.get("ISBINDINGMOBILE")).intValue());
				exchange.setReturnType(CommonUtil.formatDouble(map.get("RETURNTYPE")).intValue());
				exchange.setReturnNumbers(CommonUtil.formatDouble(map.get("RETURNNUMBERS")).intValue());
				exchange.setEachNumber(CommonUtil.formatDouble(map.get("EACHNUMBER")).intValue());
				exchange.setEachUnit(CommonUtil.formatDouble(map.get("EACHUNIT")).intValue());
				exchange.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
				exchange.setExchangeUserCode((String)map.get("EXCHANGEUSERCODE"));
				exchange.setExchangeUserName((String)map.get("EXCHANGEUSERNAME"));
				exchange.setSid((String)map.get("SID"));
				exchange.setSidName((String)map.get("SIDNAME"));
				Date operateTime = (Date)map.get("CREATETIME");
				exchange.setCreateTime(DateUtil.format(operateTime));
				operateTime = (Date)map.get("UPDATETIME");
				exchange.setUpdateTime(DateUtil.format(operateTime));
				exchangeVOList.add(exchange);
			}
		}
		j.setRows(exchangeVOList);
		return j;
	}
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder("select t.EXCHANGE_ID as EXCHANGEID,t.EXCHANGE_DETAIL_ID as EXCHANGEDETAILID,t.EXCHANGE_DETAIL as EXCHANGEDETAIL,");
		sb.append("t.OUT_ACCOUNT_TYPE as OUTACCOUNTTYPE,t.OUT_AMOUNT_LOW as OUTAMOUNTLOW,t.OUT_AMOUNT_HIGH as OUTAMOUNTHIGH,t.IN_ACCOUNT_TYPE as INACCOUNTTYPE,");
		sb.append("t.IN_AMOUNT_RATE as INAMOUNTRATE,t.STATUS as ITEMSTATUS,t.TIMES as TIMES,t.CREATE_TIME as ITEMCREATETIME,t.UPDATE_TIME as ITEMUPDATETIME ");
		sb.append("from TASK_EXCHANGE_DETAIL t ");
		System.out.println(sb.toString());
	}
	@Override
	public void deleteTaskExchangeById(String taskExchangeId) {
		String sql = "delete task_exchange t where t.exchange_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		taskExchangeMapDao.executeSql(sql, param);
		
	}
	@Override
	public void deleteTaskExchangeDetail(String taskExchangeDetailId) {
		String sql = "delete task_exchange_detail t where t.exchange_detail_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeDetailId);
		taskExchangeDetailMapDao.executeSql(sql, param);
	}
	@Override
	public void deleteTaskExchangeItemById(String taskExchangeId) {
		String sql = "delete task_exchange_detail t where t.exchange_id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		taskExchangeDetailMapDao.executeSql(sql, param);
		
	}
	@Override
	public void deleteTaskExchange(TaskExchange taskExchange) {
		taskExchangeDao.delete(taskExchange);
		
	}
	@Override
	public void deleteActiveInfo(TaskExchange taskExchange) {
		taskExchangeDao.delete(taskExchange);
		deleteTaskExchangeItemById(taskExchange.getExchangeId());
	}
	@Override
	public List<TaskExchange> getTaskExchangeList(
			List<String> taskExchangeIdList) {
		if(taskExchangeIdList != null && taskExchangeIdList.size() > 0){
			StringBuilder sb = new StringBuilder("from TaskExchange t ");
			sb.append("where t.exchangeId in (");
			for(int i=0;i<taskExchangeIdList.size();i++){
				if(i == taskExchangeIdList.size() - 1){
					sb.append("?");
				}else{
					sb.append("?,");
				}
			}
			sb.append(")");
			List<TaskExchange> taskExchangeList = taskExchangeDao.find(sb.toString(), taskExchangeIdList.toArray());
			return taskExchangeList;
		}
		return null;
	}
	@Override
	public void deleteTaskExchangeList(List<TaskExchange> taskExchangeList) {
		for(TaskExchange taskExchange : taskExchangeList){
			taskExchangeDao.delete(taskExchange);
			deleteTaskExchangeItemById(taskExchange.getExchangeId());
		}
		
	}
	@Override
	public EasyuiDataGridJson activeItemList(EasyuiDataGrid dg,
			ExchangeVO exchangeVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select e.EXCHANGE_NAME as EXCHANGENAME,t.EXCHANGE_ID as EXCHANGEID,t.EXCHANGE_DETAIL_ID as EXCHANGEDETAILID,t.EXCHANGE_DETAIL as EXCHANGEDETAIL,");
		sb.append("t.OUT_ACCOUNT_TYPE as OUTACCOUNTTYPE,t.OUT_AMOUNT_LOW as OUTAMOUNTLOW,t.OUT_AMOUNT_HIGH as OUTAMOUNTHIGH,t.IN_ACCOUNT_TYPE as INACCOUNTTYPE,");
		sb.append("t.IN_AMOUNT_RATE as INAMOUNTRATE,t.STATUS as ITEMSTATUS,t.TIMES as TIMES,t.CREATE_TIME as ITEMCREATETIME,t.UPDATE_TIME as ITEMUPDATETIME ");
		sb.append("from TASK_EXCHANGE_DETAIL t,TASK_EXCHANGE e where t.EXCHANGE_ID=e.EXCHANGE_ID ");
		List<Object> param = new ArrayList<Object>();
		if(exchangeVO != null){
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeId())){
				sb.append(" and t.EXCHANGE_ID=? ");
                param.add(exchangeVO.getExchangeId());
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(taskExchangeDetailMapDao.countBySql(totalHql,param).longValue());
		sb.append(" order by T.CREATE_TIME desc ");
		List<Map> itemList = taskExchangeMapDao.findBySql(sb.toString(), param, dg.getPage(), dg.getRows());
		List<ExchangeVO> exchangeVOList = new ArrayList<ExchangeVO>();
		if(itemList != null && itemList.size() > 0){
			for(Map map : itemList){
				ExchangeVO exchange = new ExchangeVO();
				exchange.setExchangeName((String)map.get("EXCHANGENAME"));
				exchange.setExchangeId((String)map.get("EXCHANGEID"));
				exchange.setExchangeDetailId((String)map.get("EXCHANGEDETAILID"));
				exchange.setExchangeDetail((String)map.get("EXCHANGEDETAIL"));
				int outFunddingType = CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG;
				if(CommonUtil.isNotEmpty(map.get("OUTACCOUNTTYPE"))){
					outFunddingType = CommonUtil.formatDouble(map.get("OUTACCOUNTTYPE")).intValue();
				}
				exchange.setOutAccountType(outFunddingType);
				if(outFunddingType == CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG || outFunddingType == CommonConstants.ACTIVE_FUNDINGTYPE_PRESIENT_FLAG ){
					exchange.setOutAmountHighText(CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("OUTAMOUNTHIGH"))));
					exchange.setOutAmountLowText(CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("OUTAMOUNTLOW"))));
				}else{
					exchange.setOutAmountHighText(CommonUtil.formatDouble(map.get("OUTAMOUNTHIGH")).intValue() + "");
					exchange.setOutAmountLowText(CommonUtil.formatDouble(map.get("OUTAMOUNTLOW")).intValue() + "");
				}

				exchange.setInAccountType(CommonUtil.formatDouble(map.get("INACCOUNTTYPE")).intValue());
				exchange.setInAmountRate(CommonUtil.formatDouble(map.get("INAMOUNTRATE")).intValue());
				exchange.setStatus(CommonUtil.formatDouble(map.get("ITEMSTATUS")).intValue());
				exchange.setTimes(CommonUtil.formatDouble(map.get("TIMES")).intValue());
				Date operateTime = (Date)map.get("ITEMCREATETIME");
				exchange.setCreateTime(DateUtil.format(operateTime));
				operateTime = (Date)map.get("ITEMUPDATETIME");
				exchange.setUpdateTime(DateUtil.format(operateTime));
				exchangeVOList.add(exchange);
				
			}
		}
		j.setRows(exchangeVOList);
		return j;
	}
	@Override
	public List<TaskExchangeDetail> getTaskExchangeDetailList(
			List<String> taskExchangeDetailIdList) {
		if(taskExchangeDetailIdList != null && taskExchangeDetailIdList.size() > 0){
			StringBuilder sb = new StringBuilder("from TaskExchangeDetail t ");
			sb.append("where t.exchangeDetailId in (");
			for(int i=0;i<taskExchangeDetailIdList.size();i++){
				if(i == taskExchangeDetailIdList.size() - 1){
					sb.append("?");
				}else{
					sb.append("?,");
				}
			}
			sb.append(")");
			List<TaskExchangeDetail> taskExchangeDetailList = taskExchangeDetailDao.find(sb.toString(), taskExchangeDetailIdList.toArray());
			return taskExchangeDetailList;
		}
		return null;
	}
	@Override
	public void deleteTaskExchangeDetailList(
			List<TaskExchangeDetail> taskExchangeDetailList) {
		for(TaskExchangeDetail detail : taskExchangeDetailList){
			taskExchangeDetailDao.delete(detail);
		}
		
	}
	@Override
	public void updateTaskExchangeDetailList(List<TaskExchangeDetail> detailList) {
		for(TaskExchangeDetail detail : detailList){
			taskExchangeDetailDao.update(detail);
		}
		
	}
	@Override
	public void updateTaskExchangeList(List<TaskExchange> taskExchangeList) {
		for(TaskExchange taskExchange : taskExchangeList){
			taskExchangeDao.update(taskExchange);
		}
		
	}
	@Override
	public List<TaskExchangeDetail> getNotCurrentDetailList(
			String taskExchangeId, String taskExchangeDetailId) {
		String hql = "from TaskExchangeDetail t where t.exchangeId =? and t.exchangeDetailId !=? and t.status=1";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		param.add(taskExchangeDetailId);
		List<TaskExchangeDetail> detailList = taskExchangeDetailDao.find(hql, param);
		return detailList;
	}
	@Override
	public List<TaskExchangeDetail> getDetailListByStatus(String taskExchangeId,int status) {
		String hql = "from TaskExchangeDetail t where t.exchangeId=? and t.status =?";
		List<Object> param = new ArrayList<Object>();
		param.add(taskExchangeId);
		param.add(status);
		List<TaskExchangeDetail> detailList = taskExchangeDetailDao.find(hql, param);
		return detailList;
	}

}
