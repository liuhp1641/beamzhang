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
import com.cm.manage.dao.operate.IOperateDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryClass;
import com.cm.manage.util.lottery.bean.LotteryPlay;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.HandworkBonusVO;
import com.cm.manage.vo.order.MainIssueVO;
@Repository
public class OperateDaoImpl implements IOperateDao {
	
	@Autowired
	private IBaseDao<Object> operateDao;
	/**
	 * 派奖查询
	 * @param dg
	 * @param bonus
	 * @return
	 */
	@Override
	public EasyuiDataGridJson handBonusList(EasyuiDataGrid dg,HandworkBonusVO bonus) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select h.ID,h.user_code,h.user_name ,h.LOTTERY_CODE,h.ISSUE,h.PROGRAMS_ORDER_ID,");
		sb.append(" h.ORDER_AMOUNT,h.BONUS_AMOUNT,h.FIX_BONUS_AMOUNT,h.STATUS,h.operator,h.CREATE_TIME,p.PLAY_CODE,");
		sb.append(" h.ACCEPT_TIME,p.MULTIPLE,p.BUY_TYPE ");
		sb.append(" from TMS_HANDWORK_BONUS h  left join TMS_PROGRAMS p on h.PROGRAMS_ORDER_ID=p.PROGRAMS_ORDER_ID ");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if (bonus != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = bonus.isFlag();
			// 用户名称
			if (CommonUtil.isNotEmpty(bonus.getUserName())) {
				if (flag) {
					sb.append(" and h.user_name like ? ");
					values.add("%" + bonus.getUserName().trim() + "%");
				} else {
					sb.append(" and h.user_name = ? ");
					values.add(bonus.getUserName().trim());
				}
			}

			// 方案编号
			if (CommonUtil.isNotEmpty(bonus.getProgramsOrderId())) {
				if (flag) {
					sb.append(" and h.PROGRAMS_ORDER_ID like ? ");
					values.add("%" + bonus.getProgramsOrderId().trim() + "%");
				} else {
					sb.append(" and h.PROGRAMS_ORDER_ID = ? ");
					values.add(bonus.getProgramsOrderId().trim());
				}
			}

		
			// 彩种
			if (CommonUtil.isNotEmpty(bonus.getLotteryCode())) {
				sb.append(" and h.LOTTERY_CODE = ?");
				values.add(bonus.getLotteryCode());
			}

			
			// 奖期范围
			if (CommonUtil.isNotEmpty(bonus.getIssueMin())) {
				sb.append(" and h.ISSUE >= ?");
				values.add(bonus.getIssueMin().trim());
			}
			if (CommonUtil.isNotEmpty(bonus.getIssueMax())) {
				sb.append(" and h.ISSUE <= ?");
				values.add(bonus.getIssueMax().trim());
			}

			
			// 投注时间
			if (CommonUtil.isNotEmpty(bonus.getBetStartDate())) {
				sb.append(" and h.create_time >= ?");
				Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(bonus.getBetStartDate(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); 
                values.add(cal.getTime());
				//values.add(DateUtil.format(bonus.getBetStartDate(),"yy-MM-dd"));
			}
			if (CommonUtil.isNotEmpty(bonus.getBetEndDate())) {
				sb.append(" and h.create_time <= ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(bonus.getBetEndDate(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
				//cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
			// 状态
			if (CommonUtil.isNotEmpty(bonus.getStatus())) {
				sb.append(" and h.STATUS =? ");
				values.add(bonus.getStatus());
			}
			// 派奖时间
			if (CommonUtil.isNotEmpty(bonus.getBonusStartDate())) {
				sb.append(" and h.ACCEPT_TIME >= ?");
				Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(bonus.getBonusStartDate(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); 
                values.add(cal.getTime());
				//values.add(DateUtil.format(bonus.getBonusStartDate(),"yy-MM-dd"));
			}
			if (CommonUtil.isNotEmpty(bonus.getBonusEndDate())) {
				sb.append(" and h.ACCEPT_TIME <= ?");
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.format(bonus.getBonusEndDate(),
						"yy-MM-dd"));

				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
				//cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
				values.add(cal.getTime());
			}
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(operateDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			
			if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
				sort = "h.PROGRAMS_ORDER_ID";
			}
			
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "h.CREATE_TIME";
			}
			if (dg.getSort().toString().equalsIgnoreCase("acceptTime")) {
				sort = "h.ACCEPT_TIME";
			}

			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			sb.append(" order by  h.CREATE_TIME desc");
		}
		List<Map> orderMapList = operateDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<HandworkBonusVO> orderVOList = new ArrayList<HandworkBonusVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				HandworkBonusVO orderVO = new HandworkBonusVO();
				BigDecimal id=(BigDecimal)map.get("ID");
				orderVO.setId(id.longValue());
				orderVO.setProgramsOrderId((String) map
						.get("PROGRAMS_ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				
				Date acceptTime = (Date) map.get("ACCEPT_TIME");
				orderVO.setAcceptTime(DateUtil.format(acceptTime));
				
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
				orderVO.setLotteryCode(lotteryClass.getName());
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				
				orderVO.setMultiple(((BigDecimal) map.get("MULTIPLE"))
						.intValue());
				orderVO.setOrderAmount(CommonUtil.formatDouble(map
						.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map
						.get("BONUS_AMOUNT")));
				orderVO.setStatus(((BigDecimal)map.get("STATUS")).intValue());
				orderVO.setOperator((String)map.get("OPERATOR"));
				orderVO.setBuyType(((BigDecimal) map.get("BUY_TYPE")).intValue());
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	}
	
	 /**
	  * 派奖取消
	  * @param id
	  * @return
	  */
	 public boolean handBonusCancel(String programsOrderId){
		 StringBuilder str=new StringBuilder("update TMS_HANDWORK_BONUS set STATUS=2 where PROGRAMS_ORDER_ID= ");
		 str.append(programsOrderId);
		 int i=operateDao.executeSql(str.toString());
		 if(i!= 0){
			 return true;
		 }
		 return false;
	 }

	 /**
	  * 线下派奖/派奖还原
	  * @param programsList
	  * @param status
	  * @return
	  */
	 public boolean handBonusUpdate(List<String> programsList, Integer status){
		 boolean flag=true;
		 StringBuffer str=new StringBuffer(" update TMS_HANDWORK_BONUS set STATUS=? where PROGRAMS_ORDER_ID= ? ");
		 for(int i=0;i<programsList.size();i++){
			 List<Object> values = new ArrayList<Object>();
			 values.add(status);
			 values.add(programsList.get(i));
			 int n= operateDao.executeSql(str.toString(), values);
			 if(n!=1){
				 flag=false;
			 }
		 }
		 return flag;
	 }
	 /**
	  * 奖期查询
	  * @param dg
	  * @param issue
	  * @return
	  */
	 public EasyuiDataGridJson issueList(EasyuiDataGrid dg, MainIssueVO issue){

			EasyuiDataGridJson j = new EasyuiDataGridJson();
			StringBuilder sb = new StringBuilder("select i.ID,i.LOTTERY_CODE,i.NAME,i.START_TIME,i.SEND_STATUS,");
			sb.append(" i.SIMPLEX_TIME,i.DUPLEX_TIME,i.END_TIME,i.STATUS,i.BONUS_TIME,i.BONUS_STATUS,i.BONUS_NUMBER");
			sb.append(" from TMS_MAIN_ISSUE i ");
			sb.append(" where i.LOTTERY_CODE != '200' ");
			List<Object> values = new ArrayList<Object>();
			if (issue != null) {// 添加查询条件
				// 是否模糊查询
				boolean flag = issue.isFlag();
			
				// 彩种
				if (CommonUtil.isNotEmpty(issue.getLotteryCode())) {
					sb.append(" and i.LOTTERY_CODE = ?");
					values.add(issue.getLotteryCode());
				}
				// 奖期范围
				if (CommonUtil.isNotEmpty(issue.getIssueMin())) {
					sb.append(" and i.NAME >= ?");
					values.add(issue.getIssueMin().trim());
				}
				if (CommonUtil.isNotEmpty(issue.getIssueMax())) {
					sb.append(" and i.NAME <= ?");
					values.add(issue.getIssueMax().trim());
				}
				
				//开期时间
				if (CommonUtil.isNotEmpty(issue.getStartTime())) {
					sb.append(" and i.START_TIME >= ?");
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.format(issue.getStartTime(),
							"yy-MM-dd"));

					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
					values.add(cal.getTime());
				}
				if (CommonUtil.isNotEmpty(issue.getEndTime())) {
					sb.append(" and i.START_TIME <= ?");
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.format(issue.getEndTime(),
							"yy-MM-dd"));

					cal.set(Calendar.HOUR_OF_DAY, 23);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
					//cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
					values.add(cal.getTime());
				}
				// 兑奖状态
				if (CommonUtil.isNotEmpty(issue.getBonusStatus())) {
					sb.append(" and i.BONUS_STATUS =? ");
					values.add(issue.getBonusStatus());
				}
				//销售状态
				if (CommonUtil.isNotEmpty(issue.getStatus())) {
					sb.append(" and i.STATUS =? ");
					values.add(issue.getStatus());
				}
				
			}
			String totalHql = " select count(*)  from (" + sb + ")";
			j.setTotal(operateDao.countBySql(totalHql, values).longValue());// 设置总记录数
			 if (dg.getSort() != null) {// 设置排序
		            String sort = "";
		            if (dg.getSort().toString().equalsIgnoreCase("startTime")) {
		                sort = " i.START_TIME ";
		            }
		            if (dg.getSort().toString().equalsIgnoreCase("endTime")) {
		                sort = " i.END_TIME";
		            }
		            if (dg.getSort().toString().equalsIgnoreCase("simplexTime")) {
		                sort = " i.SIMPLEX_TIME";
		            }
		            if (dg.getSort().toString().equalsIgnoreCase("duplexTime")) {
		                sort = " i.DUPLEX_TIME";
		            }
		            if (!sort.equals("")) {
		                sb.append(" order by ").append(sort).append(" ").append( dg.getOrder());
		            }
		        }
			List<Map> issueMapList = operateDao.findBySql(sb.toString(), values,
					dg.getPage(), dg.getRows());// 查询分页
			List<MainIssueVO> issueList = new ArrayList<MainIssueVO>();
			if (issueMapList != null && issueMapList.size() > 0) {// 转换模型
				LotteryList lottery = new LotteryList();
				for (Map map : issueMapList) {
					MainIssueVO vo = new MainIssueVO();
					BigDecimal id=(BigDecimal)map.get("ID");
					vo.setId(id.longValue());
					
					String lotteryCode = (String) map.get("LOTTERY_CODE");
					LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
					vo.setLotteryCode(lotteryCode);
					vo.setLotteryName(lotteryClass.getName());
					vo.setName((String)map.get("NAME"));
					Date startTime = (Date) map.get("START_TIME");
					vo.setStartTime(startTime==null?"":DateUtil.format(startTime));
					
					Date endTime = (Date) map.get("END_TIME");
					vo.setEndTime(endTime==null?"":DateUtil.format(endTime));
					
					Date duplexTime = (Date) map.get("DUPLEX_TIME");
					vo.setDuplexTime(duplexTime==null?"":DateUtil.format(duplexTime));
					
					Date simplexTime = (Date) map.get("SIMPLEX_TIME");
					vo.setSimplexTime(simplexTime==null?"":DateUtil.format(simplexTime));
					BigDecimal status=(BigDecimal) map.get("STATUS");
					vo.setStatus(status.intValue());
					 
					BigDecimal sendStatus=(BigDecimal) map.get("SEND_STATUS");
					vo.setSendStatus(sendStatus.intValue());
					vo.setBonusNumber((String)map.get("BONUS_NUMBER"));
					
					BigDecimal bonusStatus=(BigDecimal) map.get("BONUS_STATUS");
					vo.setBonusStatus(bonusStatus.intValue());
					issueList.add(vo);
				}
			}
			j.setRows(issueList);// 设置返回的行
			return j;
		
	 }
	 
	 /**
	  * 期号详情
	  * @param lotteryCode
	  * @param name
	  * @return
	  */
	 public Map issueDetail(String lotteryCode,String name){
	    StringBuilder sb = new StringBuilder("select i.ID,i.LOTTERY_CODE,i.NAME,i.START_TIME,i.SEND_STATUS,");
		sb.append(" i.SIMPLEX_TIME,i.DUPLEX_TIME,i.END_TIME,i.STATUS,i.BONUS_TIME,i.BONUS_STATUS,i.BONUS_NUMBER, ");
		sb.append(" i.BONUS_TOTAL,i.SALE_TOTAL ,i.BONUS_CLASS,i.OPEN_AWARD_TIME ,i.PRIZES_TIME,i.GLOBAL_SALE_TOTAL,i.PRIZE_POOL  ");
		sb.append(" from TMS_MAIN_ISSUE i ");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		// 彩种
		if (CommonUtil.isNotEmpty(lotteryCode)) {
			sb.append(" and i.LOTTERY_CODE = ?");
			values.add(lotteryCode);
		}
		
		// 期号
		if (CommonUtil.isNotEmpty(name)) {
			sb.append(" and i.NAME =? ");
			values.add(name);
		}
		
		List<Map> issueMapList = operateDao.findBySql(sb.toString(), values);
		if(issueMapList!=null&&issueMapList.size()>0){
			Map issueMap = issueMapList.get(0);
			LotteryList lottery = new LotteryList();
			LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
			issueMap.put("LOTTERY_NAME", lotteryClass.getName());
			return issueMap;
		}
		return null;
	 }
	 /**
	  * 奖期编辑公告保存
	  * @param lotteryCode
	  * @param name
	  * @param bonusNumber
	  * @param saleTotal
	  * @param bonusPool
	  * @param bonusClass
	  * @return
	  */
	 public boolean awardSetSave(String lotteryCode, String name,String bonusNumber,String saleTotal,String prizePool,String bonusClass){
		 StringBuilder str=new StringBuilder("update TMS_MAIN_ISSUE set BONUS_NUMBER=?, SALE_TOTAL=?,PRIZE_POOL=?,BONUS_CLASS=? where LOTTERY_CODE = ? and NAME=?");
		 List<Object> values = new ArrayList<Object>();
		 if (CommonUtil.isNotEmpty(bonusNumber)) {
				values.add(bonusNumber);
		}else{
			values.add("");
		}
		 if (CommonUtil.isNotEmpty(saleTotal)) {
				values.add(saleTotal);
		}else{
			values.add(new Double(0));
		}
		 if (CommonUtil.isNotEmpty(prizePool)) {
				values.add(prizePool);
		}else{
			values.add(new Double(0));
		}
		 if (CommonUtil.isNotEmpty(bonusClass)) {
				values.add(bonusClass);
		}else{
			values.add("-");
		}
		 values.add(lotteryCode);
		 values.add(name);
		 int result=operateDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }else{
			 return false;
		 }
		 
	 }
}
