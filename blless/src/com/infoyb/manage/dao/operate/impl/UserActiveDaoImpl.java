package com.cm.manage.dao.operate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IUserActiveDao;
import com.cm.manage.model.operate.TaskUserExchange;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskExchangeVO;
@Repository("userActiveDao")
public class UserActiveDaoImpl implements IUserActiveDao{
	@Autowired
	private IBaseDao<Map> userActiveMapDao;
	@Autowired 
	private IBaseDao<TaskUserExchange> taskUserExchangeDao;

	@Override
	public EasyuiDataGridJson userActiveList(EasyuiDataGrid dg,
			UserTaskExchangeVO exchangeVO) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select T.USER_EXCHANGE_ID as USEREXCHANGEID,T.EXCHANGE_ID as EXCHANGEID,T.EXCHANGE_DETAIL_ID as EXCHANGEDETAILID,");
		sb.append("T.CREATE_TIME as CREATETIME,T.IN_AMOUNT as INAMOUNT,T.IN_ACCOUNT_TYPE as INACCOUNTTYPE,T.RETURN_NUMBERS as RETURNNUMBERS,");
		sb.append("E.EXCHANGE_NAME as EXCHANGENAME,E.EXCHANGE_USER_NAME as EXCHANGEUSERNAME,E.RETURN_TYPE as RETURNTYPE,U.USER_NAME as USERNAME");
		sb.append(" from TASK_USER_EXCHANGE T,TASK_EXCHANGE E,USER_MEMBER U where T.EXCHANGE_ID=E.EXCHANGE_ID and T.USER_CODE=U.USER_CODE");
		List<Object> param = new ArrayList<Object>();
		if(exchangeVO != null){
			boolean flag = exchangeVO.getFlag();
			if(CommonUtil.isNotEmpty(exchangeVO.getFunddingType())){
				sb.append(" and T.IN_ACCOUNT_TYPE = ? ");
				param.add(exchangeVO.getFunddingType());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeId())){
				sb.append(" and T.EXCHANGE_ID = ? ");
				param.add(exchangeVO.getExchangeId());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getUserExchangeId())){
				sb.append(" and T.USER_EXCHANGE_ID = ? ");
				param.add(exchangeVO.getUserExchangeId());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnType())){
				sb.append(" and E.RETURN_TYPE = ? ");
				param.add(exchangeVO.getReturnType());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeName())){
				if(flag){
					sb.append(" and E.EXCHANGE_NAME like ? ");
					param.add("%" + exchangeVO.getExchangeName() + "%");
				}else{
					sb.append(" and E.EXCHANGE_NAME = ? ");
					param.add(exchangeVO.getExchangeName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getUserName())){
				if(flag){
					sb.append(" and U.USER_NAME like ? ");
					param.add("%" + exchangeVO.getUserName() + "%");
				}else{
					sb.append(" and U.USER_NAME = ? ");
					param.add(exchangeVO.getUserName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeUserName())){
				if(flag){
					sb.append(" and E.EXCHANGE_USER_NAME like ? ");
					param.add("%" + exchangeVO.getExchangeUserName() + "%");
				}else{
					sb.append(" and E.EXCHANGE_USER_NAME = ? ");
					param.add(exchangeVO.getExchangeUserName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnAmountLow())){
				sb.append(" and T.IN_AMOUNT >= ? ");
				param.add(exchangeVO.getReturnAmountLow());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnAmountHigh())){
				sb.append(" and T.IN_AMOUNT <= ? ");
				param.add(exchangeVO.getReturnAmountHigh());
			}
			String fromSeconds = " 00:00:00";
			String toSeconds = " 23:59:59";
			if(CommonUtil.isNotEmpty(exchangeVO.getCreateFromDate())){
				sb.append(" and T.CREATE_TIME >= ? ");
				param.add(DateUtil.format(exchangeVO.getCreateFromDate() + fromSeconds,"yy-MM-dd HH:mm:ss"));
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getCreateToDate())){
				sb.append(" and T.CREATE_TIME <= ? ");
				param.add(DateUtil.format(exchangeVO.getCreateToDate() + toSeconds,"yy-MM-dd HH:mm:ss"));
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(userActiveMapDao.countBySql(totalHql, param).longValue());
		sb.append(" order by T.CREATE_TIME desc ");
		List<Map> userActiveInfoList = userActiveMapDao.findBySql(sb.toString(), param, dg.getPage(), dg.getRows());
		List<UserTaskExchangeVO> exchangeVOList = new ArrayList<UserTaskExchangeVO>();
		if(userActiveInfoList != null && userActiveInfoList.size() > 0){
			for(Map map : userActiveInfoList){
				UserTaskExchangeVO exchange = new UserTaskExchangeVO();
				exchange.setExchangeName((String)map.get("EXCHANGENAME"));
				exchange.setExchangeId((String)map.get("EXCHANGEID"));
				exchange.setUserExchangeId((String)map.get("USEREXCHANGEID"));
				exchange.setExchangeDetailId((String)map.get("EXCHANGEDETAILID"));
				Date createTime = (Date)map.get("CREATETIME");
				exchange.setCreateTime(DateUtil.format(createTime));
				int inAccountType = CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG;
				if(CommonUtil.isNotEmpty(map.get("INACCOUNTTYPE"))){
					inAccountType = CommonUtil.formatDouble(map.get("INACCOUNTTYPE")).intValue();
				}
				exchange.setFunddingType(inAccountType);
				if(inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG || 
						inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_PRESIENT_FLAG){
					exchange.setInAmountText(CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("INAMOUNT"))));
				}else{
					exchange.setInAmountText(CommonUtil.formatDouble(map.get("INAMOUNT")).intValue() + "");
				}
				exchange.setReturnType(CommonUtil.formatDouble(map.get("RETURNTYPE")).intValue());
				exchange.setReturnNumbers(CommonUtil.formatDouble(map.get("RETURNNUMBERS")).intValue());
				exchange.setExchangeUserName((String)map.get("EXCHANGEUSERNAME"));
				exchange.setUserName((String)map.get("USERNAME"));
				exchangeVOList.add(exchange);
			}
		}
		j.setRows(exchangeVOList);
		return j;
	}
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder("select T.USER_EXCHANGE_ID as USEREXCHANGEID,T.EXCHANGE_ID as EXCHANGEID,T.EXCHANGE_DETAIL_ID as EXCHANGEDETAILID,");
		sb.append("T.RETURN_ACCOUNT_TYPE as RETURNACCOUNTTYPE,T.RETURN_AMOUNT as RETURNAMOUNT,T.RETURN_DATE as RETURNDATE,T.STATUS as STATUS,");
		sb.append("T.EXCHANGE_USER_CODE as EXCHANGEUSERCODE,T.UPDATE_TIME as UPDATETIME,U.IN_ACCOUNT_TYPE as INACCOUNTTYPE ");
		sb.append("from TASK_EX_USER_STAGING T,TASK_USER_EXCHANGE U where T.USER_EXCHANGE_ID=U.USER_EXCHANGE_ID ");
		System.out.println(sb.toString());
	}
	@Override
	public EasyuiDataGridJson userActiveDetailList(EasyuiDataGrid dg,String userExchangeId) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select T.USER_EXCHANGE_ID as USEREXCHANGEID,T.EXCHANGE_ID as EXCHANGEID,T.EXCHANGE_DETAIL_ID as EXCHANGEDETAILID,");
		sb.append("T.RETURN_ACCOUNT_TYPE as RETURNACCOUNTTYPE,T.RETURN_AMOUNT as RETURNAMOUNT,T.RETURN_DATE as RETURNDATE,T.STATUS as STATUS,");
		sb.append("T.EXCHANGE_USER_CODE as EXCHANGEUSERCODE,T.UPDATE_TIME as UPDATETIME,U.IN_ACCOUNT_TYPE as INACCOUNTTYPE ");
		sb.append("from TASK_EX_USER_STAGING T,TASK_USER_EXCHANGE U where T.USER_EXCHANGE_ID=U.USER_EXCHANGE_ID ");
		sb.append(" and T.USER_EXCHANGE_ID=?");
		List<Object> param = new ArrayList<Object>();
		param.add(userExchangeId);
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(userActiveMapDao.countBySql(totalHql, param).longValue());
		sb.append(" order by T.UPDATE_TIME desc ");
		List<Map> userActiveDetailList = userActiveMapDao.findBySql(sb.toString(), param, dg.getPage(), dg.getRows());
		List<UserTaskExchangeVO> exchangeVOList = new ArrayList<UserTaskExchangeVO>();
		if(userActiveDetailList != null && userActiveDetailList.size() > 0){
			for(Map map : userActiveDetailList){
				UserTaskExchangeVO exchange = new UserTaskExchangeVO();
				exchange.setUserExchangeId((String)map.get("USEREXCHANGEID"));
				int inAccountType = CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG;
				if(CommonUtil.isNotEmpty(map.get("INACCOUNTTYPE"))){
					inAccountType = CommonUtil.formatDouble(map.get("INACCOUNTTYPE")).intValue();
				}
				exchange.setFunddingType(inAccountType);
				if(inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG || 
						inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_PRESIENT_FLAG){
					exchange.setReturnAmountText(CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("RETURNAMOUNT"))));
				}else{
					exchange.setReturnAmountText(CommonUtil.formatDouble(map.get("RETURNAMOUNT")).intValue() + "");
				}
				Date returnDate = (Date)map.get("RETURNDATE");
				exchange.setReturnDate(DateUtil.format(returnDate));
				Date updateTime = (Date)map.get("UPDATETIME");
				exchange.setUpdateTime(DateUtil.format(updateTime));
				exchange.setStatus(CommonUtil.formatDouble(map.get("STATUS")).intValue());
				exchangeVOList.add(exchange);
			}
		}
		j.setRows(exchangeVOList);
		return j;
	}
	/***
	 * @describe 统计返赠明细数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> count(String userExchangeId) {
		TaskUserExchange taskUserExchange = findTaskUserExchangeById(userExchangeId);
		int inAccountType = taskUserExchange.getInAccountType();
		StringBuilder sb = new StringBuilder("select sum(case when t.status=0 then 1 else 0 end) as notrewardnum,");
		sb.append("sum(case when t.status=1 then 1 else 0 end) as hasrewardnum,");
		sb.append("sum(case when t.status=0 then t.return_amount else 0 end) as notrewardamount,");
		sb.append("sum(case when t.status=1 then t.return_amount else 0 end) as hasrewardamount ");
		sb.append("from task_ex_user_staging t where t.user_exchange_id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(userExchangeId);
		List<Map> resultList = userActiveMapDao.findBySql(sb.toString(), param);
		if(resultList != null && resultList.size() > 0){
			Map map = resultList.get(0);
			if(inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG || 
					inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_PRESIENT_FLAG){
				map.put("NOTREWARDAMOUNT",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("NOTREWARDAMOUNT"))));
				map.put("HASREWARDAMOUNT",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("HASREWARDAMOUNT"))));
			}else{
				map.put("NOTREWARDAMOUNT",CommonUtil.formatDouble(map.get("NOTREWARDAMOUNT")).intValue());
				map.put("HASREWARDAMOUNT",CommonUtil.formatDouble(map.get("HASREWARDAMOUNT")).intValue());
			}
			int notRewardNum = CommonUtil.formatDouble(map.get("NOTREWARDNUM")).intValue(); 
			int hasRewardNum = CommonUtil.formatDouble(map.get("HASREWARDNUM")).intValue(); 
			map.put("NOTREWARDNUM", notRewardNum);
			map.put("HASREWARDNUM", hasRewardNum);
			map.put("INACCOUNTTYPE", inAccountType);
			return map;
		}
		return null;
	}
	@Override
	public TaskUserExchange findTaskUserExchangeById(String userExchangeId) {
		String hql = "from TaskUserExchange t where t.userExchangeId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(userExchangeId);
		List<TaskUserExchange> taskUserExchangeList = taskUserExchangeDao.find(hql, param);
		if(taskUserExchangeList != null && taskUserExchangeList.size() > 0){
			return taskUserExchangeList.get(0);
		}
		return null;
	}
	/***
	 * @describe 统计活动日志数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> count(UserTaskExchangeVO exchangeVO) {
		StringBuilder sb = new StringBuilder("select sum(case when E.RETURN_TYPE=1 then T.IN_AMOUNT else 0 end) as STAGGINGAMOUNT,");
		sb.append("sum(case when E.RETURN_TYPE=0 then T.IN_AMOUNT else 0 end) as NOSTAGGINGAMOUNT,");
		sb.append("sum(case when E.RETURN_TYPE=1 then 1 else 0 end) as STAGGINGNUM,");
		sb.append("sum(case when E.RETURN_TYPE=0 then 1 else 0 end) as NOSTAGGINGNUM");
		sb.append(" from TASK_USER_EXCHANGE T,TASK_EXCHANGE E,USER_MEMBER U where T.EXCHANGE_ID=E.EXCHANGE_ID and T.USER_CODE=U.USER_CODE");
		List<Object> param = new ArrayList<Object>();
		if(exchangeVO != null){
			boolean flag = exchangeVO.getFlag();
			if(CommonUtil.isNotEmpty(exchangeVO.getFunddingType())){
				sb.append(" and T.IN_ACCOUNT_TYPE = ? ");
				param.add(exchangeVO.getFunddingType());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeId())){
				sb.append(" and T.EXCHANGE_ID = ? ");
				param.add(exchangeVO.getExchangeId());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getUserExchangeId())){
				sb.append(" and T.USER_EXCHANGE_ID = ? ");
				param.add(exchangeVO.getUserExchangeId());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnType())){
				sb.append(" and E.RETURN_TYPE = ? ");
				param.add(exchangeVO.getReturnType());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeName())){
				if(flag){
					sb.append(" and E.EXCHANGE_NAME like ? ");
					param.add("%" + exchangeVO.getExchangeName() + "%");
				}else{
					sb.append(" and E.EXCHANGE_NAME = ? ");
					param.add(exchangeVO.getExchangeName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getUserName())){
				if(flag){
					sb.append(" and U.USER_NAME like ? ");
					param.add("%" + exchangeVO.getUserName() + "%");
				}else{
					sb.append(" and U.USER_NAME = ? ");
					param.add(exchangeVO.getUserName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getExchangeUserName())){
				if(flag){
					sb.append(" and E.EXCHANGE_USER_NAME like ? ");
					param.add("%" + exchangeVO.getExchangeUserName() + "%");
				}else{
					sb.append(" and E.EXCHANGE_USER_NAME = ? ");
					param.add(exchangeVO.getExchangeUserName());
				}
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnAmountLow())){
				sb.append(" and T.IN_AMOUNT >= ? ");
				param.add(exchangeVO.getReturnAmountLow());
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getReturnAmountHigh())){
				sb.append(" and T.IN_AMOUNT <= ? ");
				param.add(exchangeVO.getReturnAmountHigh());
			}
			String fromSeconds = " 00:00:00";
			String toSeconds = " 23:59:59";
			if(CommonUtil.isNotEmpty(exchangeVO.getCreateFromDate())){
				sb.append(" and T.CREATE_TIME >= ? ");
				param.add(DateUtil.format(exchangeVO.getCreateFromDate() + fromSeconds,"yy-MM-dd HH:mm:ss"));
			}
			if(CommonUtil.isNotEmpty(exchangeVO.getCreateToDate())){
				sb.append(" and T.CREATE_TIME <= ? ");
				param.add(DateUtil.format(exchangeVO.getCreateToDate() + toSeconds,"yy-MM-dd HH:mm:ss"));
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		sb.append(" order by T.CREATE_TIME desc ");
		List<Map> userActiveInfoList = userActiveMapDao.findBySql(sb.toString(), param);
		if(userActiveInfoList != null && userActiveInfoList.size() > 0){
			Map map = userActiveInfoList.get(0);
			int inAccountType = exchangeVO.getFunddingType();

			if(inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_CASH_FLAG || 
					inAccountType == CommonConstants.ACTIVE_FUNDINGTYPE_PRESIENT_FLAG){
				map.put("STAGGINGAMOUNT",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("STAGGINGAMOUNT"))));
				map.put("NOSTAGGINGAMOUNT",CommonBusiUtil.formatAmount(CommonUtil.formatDouble(map.get("NOSTAGGINGAMOUNT"))));
			}else{
				map.put("STAGGINGAMOUNT",CommonUtil.formatDouble(map.get("STAGGINGAMOUNT")).intValue());
				map.put("NOSTAGGINGAMOUNT",CommonUtil.formatDouble(map.get("NOSTAGGINGAMOUNT")).intValue());
			}
			int staggingNum = CommonUtil.formatDouble(map.get("STAGGINGNUM")).intValue(); 
			int noStaggingNum = CommonUtil.formatDouble(map.get("NOSTAGGINGNUM")).intValue(); 
			map.put("STAGGINGNUM", staggingNum);
			map.put("NOSTAGGINGNUM", noStaggingNum);
			map.put("INACCOUNTTYPE", inAccountType);
			return map;
		}
		return null;
	}

}
