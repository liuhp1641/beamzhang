package com.cm.manage.dao.order.impl;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.constant.IOrderConstants;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.order.IOrderDao;
import com.cm.manage.model.order.Programs;
import com.cm.manage.model.order.ReturnNumber;
import com.cm.manage.model.order.SubIssueForJingCaiZuQiu;
import com.cm.manage.model.order.Ticket;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.json.JsonBinder;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryClass;
import com.cm.manage.util.lottery.bean.LotteryPlay;
import com.cm.manage.util.lottery.bean.LotteryPoll;
import com.cm.manage.util.lottery.bean.MatchItemInfo;
import com.cm.manage.util.lottery.bean.NumberInfo;
import com.cm.manage.util.order.bean.AdapterMobileUtils;
import com.cm.manage.util.order.bean.MatchPlayBean;
import com.cm.manage.util.order.bean.MatchPlayTermBean;
import com.cm.manage.util.order.bean.ProgramsMatchBean;
import com.cm.manage.util.order.bean.SubNumberUtils;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.AutoOrderVO;
import com.cm.manage.vo.order.OrderVO;

@Repository
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private IBaseDao<Object> orderDao;
	
	@Autowired
	private IBaseDao<ReturnNumber> returnNumberDao;
	
	@Autowired
	private IBaseDao<SubIssueForJingCaiZuQiu> subIssueDao;

	/**
	 * 投注查询
	 * 
	 * @param dg
	 * @param order
	 * @return
	 */
	public EasyuiDataGridJson orderList(EasyuiDataGrid dg, OrderVO order) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
		sb.append("o.ID,o.ORDER_ID,o.ORDER_TYPE,o.CREATE_TIME,p.PROGRAMS_ORDER_ID,p.ISSUE,p.LOTTERY_CODE,p.PLAY_CODE,");
		sb.append("p.ORDER_STATUS,p.MULTIPLE,o.ORDER_AMOUNT,o.BONUS_AMOUNT,o.BONUS_STATUS,p.BONUS_TO_ACCOUNT,p.BUY_TYPE  ");
		sb.append(" from TMS_ORDER o inner join user_member u on o.user_code = u.user_code ");
		sb.append(" left join TMS_PROGRAMS p on o.PROGRAMS_ORDER_ID=p.PROGRAMS_ORDER_ID ");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if (order != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = order.isFlag();

			// 用户编号
			if (CommonUtil.isNotEmpty(order.getUserCode())) {
				if (flag) {
					sb.append(" and u.user_code like ? ");
					values.add("%" + order.getUserCode().trim() + "%");
				} else {
					sb.append(" and u.user_code = ? ");
					values.add(order.getUserCode().trim());
				}
			}

			// 用户名称
			if (CommonUtil.isNotEmpty(order.getUserName())) {
				if (flag) {
					sb.append(" and u.user_name like ? ");
					values.add("%" + order.getUserName().trim() + "%");
				} else {
					sb.append(" and u.user_name = ? ");
					values.add(order.getUserName().trim());
				}
			}

			 //手机
            if (CommonUtil.isNotEmpty(order.getMobile())) {

				if (flag) {
					sb.append(" and u.MOBILE like ? ");
					values.add("%" + order.getMobile().trim() + "%");
				} else {
					sb.append(" and u.MOBILE = ? ");
					values.add(order.getMobile().trim());
				}
            }
			// 订单编号
			if (CommonUtil.isNotEmpty(order.getOrderId())) {
				if (flag) {
					sb.append(" and o.ORDER_ID like ? ");
					values.add("%" + order.getOrderId().trim() + "%");
				} else {
					sb.append(" and o.ORDER_ID = ? ");
					values.add(order.getOrderId().trim());
				}
			}

			// 方案编号
			if (CommonUtil.isNotEmpty(order.getProgramsOrderId())) {
				if (flag) {
					sb.append(" and p.PROGRAMS_ORDER_ID like ? ");
					values.add("%" + order.getProgramsOrderId().trim() + "%");
				} else {
					sb.append(" and p.PROGRAMS_ORDER_ID = ? ");
					values.add(order.getProgramsOrderId().trim());
				}
			}
			// 订单类型
			if (CommonUtil.isNotEmpty(order.getOrderType())) {
				sb.append(" and o.ORDER_TYPE =?");
				values.add(order.getOrderType());
			}
			// 彩种
			if (CommonUtil.isNotEmpty(order.getLotteryCode())) {
				sb.append(" and p.LOTTERY_CODE = ?");
				values.add(order.getLotteryCode());
			}

			// 订单状态
			if (CommonUtil.isNotEmpty(order.getOrderStatus())) {
				Integer orderStatus = order.getOrderStatus();
				// 未出票
				if (orderStatus == 0) {
					sb.append(" and (p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_DEFAULT
							+ " or p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_WAIT + ") ");
				}
				// 已出票
				if (orderStatus == 1) {
					sb.append(" and p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SUCCESS);
					sb.append(" and p.BONUS_STATUS="+IOrderConstants.BONUS_STATUS_WAIT);
				}
				// 自动撤单
				if (orderStatus == 2) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL);
				}
				// 客服撤单
				if (orderStatus == 3) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL);
				}
				// 用户撤单
				if (orderStatus == 4) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_USER_CANCEL);
				}
				// 中奖已派
				if (orderStatus == 5) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_YES);
				}
				// 中奖未派
				if (orderStatus == 6) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_NO);
				}
				// 未中奖
				if (orderStatus == 7) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_NO);
				}
				// 流单
				if (orderStatus == 8) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_TIME_OUT);
				}
			}
			// 奖期范围
			if (CommonUtil.isNotEmpty(order.getIssueMin())) {
				sb.append(" and p.ISSUE >= ?");
				values.add(order.getIssueMin());
			}
			if (CommonUtil.isNotEmpty(order.getIssueMax())) {
				sb.append(" and p.ISSUE <= ?");
				values.add(order.getIssueMax());
			}

			// 注册渠道
			if (CommonUtil.isNotEmpty(order.getSid())) {
				sb.append(" and o.SID = ?");
				values.add(order.getSid());
			}

			// 所属于
			if (CommonUtil.isNotEmpty(order.getTrackUserCode())) {
				if (flag) {
					sb.append(" and u.TRACK_USER_CODE like ? ");
					values.add("%" + order.getTrackUserCode() + "%");
				} else {
					sb.append(" and u.TRACK_USER_CODE = ? ");
					values.add(order.getTrackUserCode());
				}

			}
			// 投注时间
			if (CommonUtil.isNotEmpty(order.getCreateStartDate())) {
				sb.append(" and o.create_time >= ?");
                values.add(DateUtil.format(order.getCreateStartDate(),"yy-MM-dd HH:mm:ss"));
			}
			if (CommonUtil.isNotEmpty(order.getCreateEndDate())) {
				sb.append(" and o.create_time <= ?");
				values.add(DateUtil.format(order.getCreateEndDate(),"yy-MM-dd HH:mm:ss"));
			}

			// 投注金额最低
			if (CommonUtil.isNotEmpty(order.getAmountMin())) {
				sb.append(" and o.ORDER_AMOUNT >= ?");
				values.add(order.getAmountMin());
			}
			if (CommonUtil.isNotEmpty(order.getAmountMax())) {
				sb.append(" and o.ORDER_AMOUNT <= ?");
				values.add(order.getAmountMax());
			}
			// 中奖金额
			if (CommonUtil.isNotEmpty(order.getBonusMin())) {
				sb.append(" and o.BONUS_AMOUNT >= ?");
				values.add(order.getBonusMin());
			}
			if (CommonUtil.isNotEmpty(order.getBonusMax())) {
				sb.append(" and o.BONUS_AMOUNT <= ?");
				values.add(order.getBonusMax());
			}

			// 隐藏状态
			if (CommonUtil.isNotEmpty(order.getIsHide())) {
				sb.append(" and p.IS_HIDE =? ");
				values.add(order.getIsHide());
			}

		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			if (dg.getSort().toString().equalsIgnoreCase("orderId")) {
				sort = "o.ORDER_ID";
			}
			if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
				sort = "p.PROGRAMS_ORDER_ID";
			}
			if (dg.getSort().toString().equalsIgnoreCase("multiple")) {
				sort = "p.MULTIPLE";
			}
			if (dg.getSort().toString().equalsIgnoreCase("orderAmount")) {
				sort = "o.ORDER_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("bonusAmount")) {
				sort = "o.BONUS_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "o.CREATE_TIME";
			}

			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			sb.append(" order by  o.CREATE_TIME desc");
		}

		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(((BigDecimal) map.get("ID")).longValue());
				orderVO.setOrderId((String) map.get("ORDER_ID"));
				orderVO.setProgramsOrderId((String) map
						.get("PROGRAMS_ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery
						.getLotteryClass(lotteryCode);
				orderVO.setLotteryCode(lotteryClass.getName());
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery
						.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVO.setOrderType(((BigDecimal) map.get("ORDER_TYPE"))
						.intValue());
				orderVO.setMultiple(((BigDecimal) map.get("MULTIPLE"))
						.intValue());
				orderVO.setOrderAmount(CommonUtil.formatDouble(map
						.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map
						.get("BONUS_AMOUNT")));
				orderVO.setOrderStatus(((BigDecimal) map.get("ORDER_STATUS"))
						.intValue());
				orderVO.setBonusStatus(((BigDecimal) map.get("BONUS_STATUS"))
						.intValue());
				orderVO.setBonusToAccount(((BigDecimal) map
						.get("BONUS_TO_ACCOUNT")).intValue());
				orderVO.setBuyType(((BigDecimal) map.get("BUY_TYPE")).intValue());
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	}

	/**
	 * 追号方案查询
	 * 
	 * @param dg
	 * @param order
	 * @return
	 */
	public EasyuiDataGridJson autoOrderList(EasyuiDataGrid dg, AutoOrderVO order) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
		sb.append("O.ID,o.AUTO_ORDER_ID,o.LOTTERY_CODE,o.PLAY_CODE,o.START_ISSUE,o.TOTAL_ISSUE,(o.SUCCESS_ISSUE+o.FAILURE_ISSUE+o.CANCEL_ISSUE)  totalAuto,o.CREATE_TIME,");
		sb.append("o.ORDER_STATUS,o.COMPLETE_AMOUNT,o.ORDER_AMOUNT,o.BONUS_AMOUNT,o.BONUS_STATUS ");
		sb.append(" from TMS_AUTO_ORDER o inner join user_member u on o.user_code = u.user_code ");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if (order != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = order.isFlag();

			// 用户编号
			if (CommonUtil.isNotEmpty(order.getUserCode())) {
				if (flag) {
					sb.append(" and u.user_code like ? ");
					values.add("%" + order.getUserCode().trim() + "%");
				} else {
					sb.append(" and u.user_code = ? ");
					values.add(order.getUserCode().trim());
				}
			}

			// 用户名称
			if (CommonUtil.isNotEmpty(order.getUserName())) {
				if (flag) {
					sb.append(" and u.user_name like ? ");
					values.add("%" + order.getUserName().trim() + "%");
				} else {
					sb.append(" and u.user_name = ? ");
					values.add(order.getUserName().trim());
				}
			}
			 //手机
            if (CommonUtil.isNotEmpty(order.getMobile())) {

				if (flag) {
					sb.append(" and u.MOBILE like ? ");
					values.add("%" + order.getMobile().trim() + "%");
				} else {
					sb.append(" and u.MOBILE = ? ");
					values.add(order.getMobile().trim());
				}
            }
			// 方案编号
			if (CommonUtil.isNotEmpty(order.getAutoOrderId())) {
				if (flag) {
					sb.append(" and o.AUTO_ORDER_ID like ? ");
					values.add("%" + order.getAutoOrderId().trim() + "%");
				} else {
					sb.append(" and o.AUTO_ORDER_ID = ? ");
					values.add(order.getAutoOrderId().trim());
				}
			}

			// 彩种
			if (CommonUtil.isNotEmpty(order.getLotteryCode())) {
				sb.append(" and o.LOTTERY_CODE = ?");
				values.add(order.getLotteryCode());
			}

			// 方案状态
			if (CommonUtil.isNotEmpty(order.getOrderStatus())) {
				Integer orderStatus = order.getOrderStatus();
				// 追号中
				if (orderStatus == 0) {
					sb.append(" and o.ORDER_STATUS="
							+ IOrderConstants.AUTO_ORDER_STATUS_DOING);
				}
				// 未中奖
				if (orderStatus == 1) {
					sb.append(" and o.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_NO);
				}
				// 客服撤单
				if (orderStatus == 2) {
					sb.append(" and o.ORDER_STATUS="
							+ IOrderConstants.AUTO_ORDER_STATUS_ADM_CANCEL);
				}
				// 中奖停追
				if (orderStatus == 3) {
					sb.append(" and o.ORDER_STATUS="
							+ IOrderConstants.AUTO_ORDER_STATUS_BONUS_COMPLETE);
				}

				// 自动撤单
				if (orderStatus == 5) {
					sb.append(" and o.ORDER_STATUS="
							+ IOrderConstants.AUTO_ORDER_STATUS_SYS_CANCEL);
				}

				// 用户撤单
				if (orderStatus == 6) {
					sb.append(" and o.ORDER_STATUS="
							+ IOrderConstants.AUTO_ORDER_STATUS_USER_CANCEL);
				}

			}
			// 包含奖期
			if (CommonUtil.isNotEmpty(order.getIssue())) {
				sb.append(" and o.START_ISSUE <= ?");
				values.add(order.getIssue());
				sb.append(" and o.END_ISSUE >= ?");
				values.add(order.getIssue());
			}

			// 注册渠道
			if (CommonUtil.isNotEmpty(order.getSid())) {
				sb.append(" and o.SID = ?");
				values.add(order.getSid());
			}

			// 所属用户
			if (CommonUtil.isNotEmpty(order.getTrackUserCode())) {
				if (flag) {
					sb.append(" and u.TRACK_USER_CODE like ? ");
					values.add("%" + order.getTrackUserCode() + "%");
				} else {
					sb.append(" and u.TRACK_USER_CODE = ? ");
					values.add(order.getTrackUserCode());
				}

			}
			// 发起时间
			if (CommonUtil.isNotEmpty(order.getCreateStartDate())) {
				sb.append(" and o.create_time >= ?");
                values.add(DateUtil.format(order.getCreateStartDate(),"yy-MM-dd HH:mm:ss"));
			}
			if (CommonUtil.isNotEmpty(order.getCreateEndDate())) {
				sb.append(" and o.create_time <= ?");
				values.add(DateUtil.format(order.getCreateEndDate(),"yy-MM-dd HH:mm:ss"));
			}

			// 方案金额最低
			if (CommonUtil.isNotEmpty(order.getAmountMin())) {
				sb.append(" and o.ORDER_AMOUNT >= ?");
				values.add(order.getAmountMin());
			}
			if (CommonUtil.isNotEmpty(order.getAmountMax())) {
				sb.append(" and o.ORDER_AMOUNT <= ?");
				values.add(order.getAmountMax());
			}
			// 成交金额

			if (CommonUtil.isNotEmpty(order.getBuyAmountMin())) {
				sb.append(" and o.COMPLETE_AMOUNT >= ?");
				values.add(order.getBuyAmountMin());
			}
			if (CommonUtil.isNotEmpty(order.getBuyAmountMax())) {
				sb.append(" and o.COMPLETE_AMOUNT <= ?");
				values.add(order.getBuyAmountMax());
			}
			// 中奖金额
			if (CommonUtil.isNotEmpty(order.getBonusMin())) {
				sb.append(" and o.BONUS_AMOUNT >= ?");
				values.add(order.getBonusMin());
			}
			if (CommonUtil.isNotEmpty(order.getBonusMax())) {
				sb.append(" and o.BONUS_AMOUNT <= ?");
				values.add(order.getBonusMax());
			}

			// 隐藏状态
			if (CommonUtil.isNotEmpty(order.getIsHide())) {
				sb.append(" and o.IS_HIDE =? ");
				values.add(order.getIsHide());
			}

		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";

			if (dg.getSort().toString().equalsIgnoreCase("autoOrderId")) {
				sort = "o.AUTO_ORDER_ID";
			}

			if (dg.getSort().toString().equalsIgnoreCase("orderAmount")) {
				sort = "o.ORDER_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("completeAmount")) {
				sort = "o.COMPLETE_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("bonusAmount")) {
				sort = "o.BONUS_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "o.CREATE_TIME";
			}

			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			sb.append("  order by  o.CREATE_TIME desc ");
		}

		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<AutoOrderVO> orderVOList = new ArrayList<AutoOrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				AutoOrderVO orderVO = new AutoOrderVO();
				orderVO.setId(((BigDecimal) map.get("ID")).longValue());
				orderVO.setAutoOrderId((String) map.get("AUTO_ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery
						.getLotteryClass(lotteryCode);
				orderVO.setLotteryCode(lotteryClass.getName());
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery
						.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVO.setStartIssue((String) map.get("START_ISSUE"));
				orderVO.setTotalIssue(((BigDecimal) map.get("TOTAL_ISSUE"))
						.intValue());
				orderVO.setTotalAuto(((BigDecimal) map.get("TOTALAUTO"))
						.intValue());
				orderVO.setOrderAmount(CommonUtil.formatDouble(map
						.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map
						.get("BONUS_AMOUNT")));
				orderVO.setCompleteAmount(CommonUtil.formatDouble(map
						.get("COMPLETE_AMOUNT")));
				orderVO.setOrderStatus(((BigDecimal) map.get("ORDER_STATUS"))
						.intValue());
				orderVO.setBonusStatus(((BigDecimal) map.get("BONUS_STATUS"))
						.intValue());
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	}

	/**
	 * 合买方案查询
	 * 
	 * @param dg
	 * @param order
	 * @return
	 */
	public EasyuiDataGridJson synOrderList(EasyuiDataGrid dg, OrderVO order) {

		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
		sb.append("p.ID,p.PROGRAMS_ORDER_ID,p.ISSUE,p.LOTTERY_CODE,p.PLAY_CODE,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.BUY_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) buyProgress,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.LAST_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) lastProgress,p.CREATE_TIME,");
		sb.append("p.ORDER_STATUS,p.ORDER_AMOUNT,p.BONUS_AMOUNT,p.BONUS_STATUS,p.BONUS_TO_ACCOUNT,p.IS_VERIFY,p.BACKUP3,p.DESCRIPTION ");
		sb.append(" from TMS_PROGRAMS p inner join user_member u on p.user_code = u.user_code ");
		sb.append(" where p.BUY_TYPE = 2 ");
		List<Object> values = new ArrayList<Object>();
		if (order != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = order.isFlag();

			// 用户编号
			if (CommonUtil.isNotEmpty(order.getUserCode())) {
				if (flag) {
					sb.append(" and u.user_code like ? ");
					values.add("%" + order.getUserCode().trim() + "%");
				} else {
					sb.append(" and u.user_code = ? ");
					values.add(order.getUserCode().trim());
				}
			}

			// 用户名称
			if (CommonUtil.isNotEmpty(order.getUserName())) {
				if (flag) {
					sb.append(" and u.user_name like ? ");
					values.add("%" + order.getUserName().trim() + "%");
				} else {
					sb.append(" and u.user_name = ? ");
					values.add(order.getUserName().trim());
				}
			}
			
			 //手机
            if (CommonUtil.isNotEmpty(order.getMobile())) {

				if (flag) {
					sb.append(" and u.MOBILE like ? ");
					values.add("%" + order.getMobile().trim() + "%");
				} else {
					sb.append(" and u.MOBILE = ? ");
					values.add(order.getMobile().trim());
				}
            }

			// 方案编号
			if (CommonUtil.isNotEmpty(order.getProgramsOrderId())) {
				if (flag) {
					sb.append(" and p.PROGRAMS_ORDER_ID like ? ");
					values.add("%" + order.getProgramsOrderId().trim() + "%");
				} else {
					sb.append(" and p.PROGRAMS_ORDER_ID = ? ");
					values.add(order.getProgramsOrderId().trim());
				}
			}
			// 彩种
			if (CommonUtil.isNotEmpty(order.getLotteryCode())) {
				sb.append(" and p.LOTTERY_CODE = ?");
				values.add(order.getLotteryCode());
			}
			// 进度
			if (CommonUtil.isNotEmpty(order.getBuyProgress())) {
				sb.append(" and round(decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.BUY_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)),4) = ?");
				Double progress=order.getBuyProgress();
				values.add(new BigDecimal(progress).divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			}

			// 订单状态
			if (CommonUtil.isNotEmpty(order.getOrderStatus())) {
				Integer orderStatus = order.getOrderStatus();
				// 未出票
				if (orderStatus == 0) {
					sb.append(" and (p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_DEFAULT
							+ " or p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_WAIT + ") ");
				}
				// 已出票
				if (orderStatus == 1) {
					sb.append(" and p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SUCCESS);
					sb.append(" and  p.BONUS_STATUS=");
					sb.append(IOrderConstants.BONUS_STATUS_WAIT);
				}
				// 自动撤单
				if (orderStatus == 2) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL);
				}
				// 客服撤单
				if (orderStatus == 3) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL);
				}
				// 用户撤单
				if (orderStatus == 4) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_USER_CANCEL);
				}
				// 流单
				if (orderStatus == 5) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_TIME_OUT);
				}

				// 中奖已派
				if (orderStatus == 6) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_YES);
				}
				// 中奖未派
				if (orderStatus == 7) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_NO);
				}
				// 未中奖
				if (orderStatus == 8) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_NO);
				}
			}
			// 奖期范围
			if (CommonUtil.isNotEmpty(order.getIssueMin())) {
				sb.append(" and p.ISSUE >= ?");
				values.add(order.getIssueMin());
			}
			if (CommonUtil.isNotEmpty(order.getIssueMax())) {
				sb.append(" and p.ISSUE <= ?");
				values.add(order.getIssueMax());
			}

			// 注册渠道
			if (CommonUtil.isNotEmpty(order.getSid())) {
				sb.append(" and o.SID = ?");
				values.add(order.getSid());
			}

			// 所属于
			if (CommonUtil.isNotEmpty(order.getTrackUserCode())) {
				if (flag) {
					sb.append(" and u.TRACK_USER_CODE like ? ");
					values.add("%" + order.getTrackUserCode() + "%");
				} else {
					sb.append(" and u.TRACK_USER_CODE = ? ");
					values.add(order.getTrackUserCode());
				}

			}
			// 发起时间
			if (CommonUtil.isNotEmpty(order.getCreateStartDate())) {
				sb.append(" and p.create_time >= ?");
				values.add(DateUtil.format(order.getCreateStartDate(),"yy-MM-dd HH:mm:ss"));
			}
			if (CommonUtil.isNotEmpty(order.getCreateEndDate())) {
				sb.append(" and p.create_time <= ?");
				values.add(DateUtil.format(order.getCreateEndDate(),"yy-MM-dd HH:mm:ss"));
			}

			// 方案金额最低
			if (CommonUtil.isNotEmpty(order.getAmountMin())) {
				sb.append(" and p.ORDER_AMOUNT >= ?");
				values.add(order.getAmountMin());
			}
			if (CommonUtil.isNotEmpty(order.getAmountMax())) {
				sb.append(" and p.ORDER_AMOUNT <= ?");
				values.add(order.getAmountMax());
			}
			// 中奖金额
			if (CommonUtil.isNotEmpty(order.getBonusMin())) {
				sb.append(" and p.BONUS_AMOUNT >= ?");
				values.add(order.getBonusMin());
			}
			if (CommonUtil.isNotEmpty(order.getBonusMax())) {
				sb.append(" and p.BONUS_AMOUNT <= ?");
				values.add(order.getBonusMax());
			}

			// 隐藏状态
			if (CommonUtil.isNotEmpty(order.getIsHide())) {
				sb.append(" and p.IS_HIDE =? ");
				values.add(order.getIsHide());
			}
			// 描述状态
			if (CommonUtil.isNotEmpty(order.getIsVerify())) {

				sb.append(" and p.IS_VERIFY =? ");
				values.add(order.getIsVerify());
			}
			if (CommonUtil.isNotEmpty(order.getBackup3())) {

				sb.append(" and p.BACKUP3 =? ");
				values.add(order.getBackup3());
			}

		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";

			if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
				sort = "p.PROGRAMS_ORDER_ID";
			}

			if (dg.getSort().toString().equalsIgnoreCase("orderAmount")) {
				sort = "p.ORDER_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("bonusAmount")) {
				sort = "p.BONUS_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "p.CREATE_TIME";
			}

			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			sb.append("  order by  p.CREATE_TIME desc ");
		}

		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(((BigDecimal) map.get("ID")).longValue());
				orderVO.setProgramsOrderId((String) map
						.get("PROGRAMS_ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
				orderVO.setLotteryCode(lotteryClass.getName());
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVO.setOrderAmount(CommonUtil.formatDouble(map.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
				orderVO.setOrderStatus(((BigDecimal) map.get("ORDER_STATUS")).intValue());
				orderVO.setBonusStatus(((BigDecimal) map.get("BONUS_STATUS")).intValue());
				orderVO.setBonusToAccount(((BigDecimal) map.get("BONUS_TO_ACCOUNT")).intValue());
				BigDecimal buyProgress =(BigDecimal) map.get("BUYPROGRESS");
		        Double buyPro = buyProgress.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				orderVO.setBuyProgress(buyPro);
				BigDecimal lastProgress =(BigDecimal) map.get("LASTPROGRESS");
		        Double lastPro = lastProgress.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				orderVO.setLastProgress(lastPro);
				orderVO.setIsVerify(((BigDecimal) map.get("IS_VERIFY"))
						.intValue());
				orderVO.setBackup3((String)map.get("BACKUP3"));
				orderVO.setDescription((String)map.get("DESCRIPTION"));
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	}
	/**
	  * 合买推荐
	  * @param programsOrderId
	  */
	 public boolean synRecommend(String programsOrderId,Integer backup3){
		 StringBuilder str=new StringBuilder("update TMS_PROGRAMS set BACKUP3=? where PROGRAMS_ORDER_ID=?");
		 List<Object> values = new ArrayList<Object>();
		 values.add(backup3);
		 values.add(programsOrderId);
		 int result=orderDao.executeSql(str.toString(),values);
		 if(result>0){
			 return true;
		 }
		return false;
	 }
	/**
	 * 代购方案查询
	 * 
	 * @param dg
	 * @param order
	 * @return
	 */
	public EasyuiDataGridJson buyOrderList(EasyuiDataGrid dg, OrderVO order) {

		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
		sb.append("p.ID,p.PROGRAMS_ORDER_ID,p.ISSUE,p.LOTTERY_CODE,p.PLAY_CODE,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.BUY_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) buyProgress,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.LAST_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) lastProgress,p.CREATE_TIME,");
		sb.append("p.ORDER_STATUS,p.ORDER_AMOUNT,p.BONUS_AMOUNT,p.BONUS_STATUS,p.BONUS_TO_ACCOUNT,p.MULTIPLE ");
		sb.append(" from TMS_PROGRAMS p inner join user_member u on p.user_code = u.user_code ");
		sb.append(" where p.BUY_TYPE = 1 ");
		List<Object> values = new ArrayList<Object>();
		if (order != null) {// 添加查询条件
			// 是否模糊查询
			boolean flag = order.isFlag();

			// 用户编号
			if (CommonUtil.isNotEmpty(order.getUserCode())) {
				if (flag) {
					sb.append(" and u.user_code like ? ");
					values.add("%" + order.getUserCode().trim() + "%");
				} else {
					sb.append(" and u.user_code = ? ");
					values.add(order.getUserCode().trim());
				}
			}

			// 用户名称
			if (CommonUtil.isNotEmpty(order.getUserName())) {
				if (flag) {
					sb.append(" and u.user_name like ? ");
					values.add("%" + order.getUserName().trim() + "%");
				} else {
					sb.append(" and u.user_name = ? ");
					values.add(order.getUserName().trim());
				}
			}
			
			 //手机
            if (CommonUtil.isNotEmpty(order.getMobile())) {

				if (flag) {
					sb.append(" and u.MOBILE like ? ");
					values.add("%" + order.getMobile().trim() + "%");
				} else {
					sb.append(" and u.MOBILE = ? ");
					values.add(order.getMobile().trim());
				}
            }

			// 方案编号
			if (CommonUtil.isNotEmpty(order.getProgramsOrderId())) {
				if (flag) {
					sb.append(" and p.PROGRAMS_ORDER_ID like ? ");
					values.add("%" + order.getProgramsOrderId().trim() + "%");
				} else {
					sb.append(" and p.PROGRAMS_ORDER_ID = ? ");
					values.add(order.getProgramsOrderId().trim());
				}
			}
			// 彩种
			if (CommonUtil.isNotEmpty(order.getLotteryCode())) {
				sb.append(" and p.LOTTERY_CODE = ?");
				values.add(order.getLotteryCode());
			}
			// 进度
			if (CommonUtil.isNotEmpty(order.getBuyProgress())) {
				sb.append(" and decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.BUY_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) = ?");
				values.add(order.getBuyProgress());
			}

			// 订单状态
			if (CommonUtil.isNotEmpty(order.getOrderStatus())) {
				Integer orderStatus = order.getOrderStatus();
				// 未出票
				if (orderStatus == 0) {
					sb.append(" and (p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_DEFAULT
							+ " or p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_WAIT + ") ");
				}
				// 已出票
				if (orderStatus == 1) {
					sb.append(" and p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SUCCESS);
					sb.append(" and  p.BONUS_STATUS=");
					sb.append(IOrderConstants.BONUS_STATUS_WAIT);
				}
				// 自动撤单
				if (orderStatus == 2) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL);
				}
				// 客服撤单
				if (orderStatus == 3) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL);
				}
				// 用户撤单
				if (orderStatus == 4) {
					sb.append(" and  p.ORDER_STATUS="
							+ IOrderConstants.PROGRAMS_STATUS_USER_CANCEL);
				}

				// 中奖已派
				if (orderStatus == 5) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_YES);
				}
				// 中奖未派
				if (orderStatus == 6) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_YES
							+ " and p.BONUS_TO_ACCOUNT="
							+ IOrderConstants.BONUS_TO_ACCOUNT_NO);
				}
				// 未中奖
				if (orderStatus == 7) {
					sb.append(" and  p.BONUS_STATUS="
							+ IOrderConstants.BONUS_STATUS_NO);
				}
			}
			// 奖期范围
			if (CommonUtil.isNotEmpty(order.getIssueMin())) {
				sb.append(" and p.ISSUE >= ?");
				values.add(order.getIssueMin());
			}
			if (CommonUtil.isNotEmpty(order.getIssueMax())) {
				sb.append(" and p.ISSUE <= ?");
				values.add(order.getIssueMax());
			}

			// 注册渠道
			if (CommonUtil.isNotEmpty(order.getSid())) {
				sb.append(" and o.SID = ?");
				values.add(order.getSid());
			}

			// 所属于
			if (CommonUtil.isNotEmpty(order.getTrackUserCode())) {
				if (flag) {
					sb.append(" and u.TRACK_USER_CODE like ? ");
					values.add("%" + order.getTrackUserCode() + "%");
				} else {
					sb.append(" and u.TRACK_USER_CODE = ? ");
					values.add(order.getTrackUserCode());
				}

			}
			// 发起时间
			if (CommonUtil.isNotEmpty(order.getCreateStartDate())) {
				sb.append(" and p.create_time >= ?");
				values.add(DateUtil.format(order.getCreateStartDate(),"yy-MM-dd HH:mm:ss"));
			}
			if (CommonUtil.isNotEmpty(order.getCreateEndDate())) {
				sb.append(" and p.create_time <= ?");
				values.add(DateUtil.format(order.getCreateEndDate(),"yy-MM-dd HH:mm:ss"));
			}

			// 方案金额最低
			if (CommonUtil.isNotEmpty(order.getAmountMin())) {
				sb.append(" and p.ORDER_AMOUNT >= ?");
				values.add(order.getAmountMin());
			}
			if (CommonUtil.isNotEmpty(order.getAmountMax())) {
				sb.append(" and p.ORDER_AMOUNT <= ?");
				values.add(order.getAmountMax());
			}
			// 中奖金额
			if (CommonUtil.isNotEmpty(order.getBonusMin())) {
				sb.append(" and p.BONUS_AMOUNT >= ?");
				values.add(order.getBonusMin());
			}
			if (CommonUtil.isNotEmpty(order.getBonusMax())) {
				sb.append(" and p.BONUS_AMOUNT <= ?");
				values.add(order.getBonusMax());
			}

			// 隐藏状态
			if (CommonUtil.isNotEmpty(order.getIsHide())) {
				sb.append(" and p.IS_HIDE =? ");
				values.add(order.getIsHide());
			}
			// 出票编号
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";

			if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
				sort = "p.PROGRAMS_ORDER_ID";
			}

			if (dg.getSort().toString().equalsIgnoreCase("multiple")) {
				sort = "p.MULTIPLE";
			}
			if (dg.getSort().toString().equalsIgnoreCase("orderAmount")) {
				sort = "p.ORDER_AMOUNT";
			}

			if (dg.getSort().toString().equalsIgnoreCase("bonusAmount")) {
				sort = "p.BONUS_AMOUNT";
			}
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "p.CREATE_TIME";
			}

			if (!sort.equals("")) {
				sb.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			sb.append("  order by  p.CREATE_TIME desc ");
		}

		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setId(((BigDecimal) map.get("ID")).longValue());
				orderVO.setProgramsOrderId((String) map
						.get("PROGRAMS_ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
				orderVO.setLotteryCode(lotteryClass.getName());
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVO.setOrderAmount(CommonUtil.formatDouble(map.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
				orderVO.setOrderStatus(((BigDecimal) map.get("ORDER_STATUS")).intValue());
				orderVO.setBonusStatus(((BigDecimal) map.get("BONUS_STATUS")).intValue());
				orderVO.setBonusToAccount(((BigDecimal) map.get("BONUS_TO_ACCOUNT")).intValue());
				// orderVO.setProgress(CommonUtil.formatDouble(map.get("PROGRESS")));
				orderVO.setMultiple(((BigDecimal) map.get("MULTIPLE")).intValue());
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	}

	/**
	 * 追号方案详情
	 * 
	 * @param autoOrderId
	 * @return
	 */
	public Map autoOrderInfo(String autoOrderId) {
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,o.SOFT_VER,");
		sb.append("o.AUTO_ORDER_ID,o.LOTTERY_CODE,o.PLAY_CODE,o.START_ISSUE,o.TOTAL_ISSUE,(o.SUCCESS_ISSUE+o.FAILURE_ISSUE+o.CANCEL_ISSUE)  totalAuto,o.CREATE_TIME,");
		sb.append("o.ORDER_STATUS,o.COMPLETE_AMOUNT,o.ORDER_AMOUNT,o.BONUS_AMOUNT,o.BONUS_STATUS ");
		sb.append(" from TMS_AUTO_ORDER o inner join user_member u on o.user_code = u.user_code ");
		sb.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		// 方案编号
		if (CommonUtil.isNotEmpty(autoOrderId)) {
			sb.append(" and o.AUTO_ORDER_ID = ? ");
			values.add(autoOrderId);
		}
		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values);
		LotteryList lottery = new LotteryList();
		if (orderMapList != null && orderMapList.size() > 0) {
			Map orderMap = orderMapList.get(0);
			String lotteryCode = (String) orderMap.get("LOTTERY_CODE");
			LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
			orderMap.put("LOTTERY_CODE", lotteryClass.getName());
			String playCode = (String) orderMap.get("PLAY_CODE");
			LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
			orderMap.put("PLAY_CODE", play.getName());
			return orderMap;
		}
		return null;
	}
	
	 /**
	  * 追号方案下的订单方案信息
	  */
	 
	 public EasyuiDataGridJson autoProgramsList(EasyuiDataGrid dg,String autoOrderId){
		 EasyuiDataGridJson j = new EasyuiDataGridJson();
		 StringBuilder sb = new StringBuilder();
		 sb.append("select p.PROGRAMS_ORDER_ID,p.ISSUE,p.LOTTERY_CODE,p.PLAY_CODE,p.NUMBER_INFO,p.CREATE_TIME,p.ORDER_ID,p.AUTO_ORDER_ID,");
		 sb.append("p.ITEM,p.MULTIPLE,p.ORDER_STATUS,p.ORDER_AMOUNT,p.BONUS_AMOUNT,p.BONUS_STATUS,p.BONUS_TO_ACCOUNT ,i.BONUS_NUMBER");
		 sb.append(" from TMS_PROGRAMS p left join TMS_MAIN_ISSUE i on p.LOTTERY_CODE=i.LOTTERY_CODE  and p.ISSUE=i.NAME ");
		 sb.append(" where 1 = 1 ");
		 List<Object> values = new ArrayList<Object>();
		 // 方案编号
		 if (CommonUtil.isNotEmpty(autoOrderId)) {
			sb.append(" and p.AUTO_ORDER_ID = ? ");
			values.add(autoOrderId);
		 }
		 sb.append("  order by  p.ISSUE asc ");
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values,dg.getPage(), dg.getRows());// 查询分页
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : orderMapList) {
				OrderVO orderVO = new OrderVO();
				orderVO.setProgramsOrderId((String) map.get("PROGRAMS_ORDER_ID"));
				orderVO.setOrderId((String) map.get("ORDER_ID"));
				Date createTime = (Date) map.get("CREATE_TIME");
				orderVO.setCreateTime(DateUtil.format(createTime));
				orderVO.setUserCode((String) map.get("USER_CODE"));
				orderVO.setUserName((String) map.get("USER_NAME"));
				orderVO.setIssue((String) map.get("ISSUE"));
				orderVO.setOrderAmount(CommonUtil.formatDouble(map.get("ORDER_AMOUNT")));
				orderVO.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
				orderVO.setOrderStatus(((BigDecimal) map.get("ORDER_STATUS")).intValue());
				orderVO.setBonusStatus(((BigDecimal) map.get("BONUS_STATUS")).intValue());
				orderVO.setBonusToAccount(((BigDecimal) map.get("BONUS_TO_ACCOUNT")).intValue());
				orderVO.setMultiple(((BigDecimal) map.get("MULTIPLE")).intValue());
				orderVO.setBonusNumber( (String)map.get("BONUS_NUMBER"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVOList.add(orderVO);
			}
		}
		j.setRows(orderVOList);// 设置返回的行
		return j;
	 }
	 
	 /**
	  * 追号订单对应方案内容
	  */
	 
	 public List<OrderVO> autoProgramsInfo(String autoOrderId){
		 StringBuilder sb = new StringBuilder(" select p.PROGRAMS_ORDER_ID,p.LOTTERY_CODE,p.PLAY_CODE,p.NUMBER_INFO");
		 sb.append(" from TMS_PROGRAMS p ");
		 sb.append(" where 1 = 1 ");
		 List<Object> values = new ArrayList<Object>();
		 // 方案编号
		 if (CommonUtil.isNotEmpty(autoOrderId)) {
			sb.append(" and p.AUTO_ORDER_ID = ? ");
			values.add(autoOrderId);
		 }
		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values);// 查询分页
		List<OrderVO> orderVOList = new ArrayList<OrderVO>();
		if (orderMapList != null && orderMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			Map map=orderMapList.get(0);
			String lotteryCode = (String) map.get("LOTTERY_CODE");
			LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
			String playCode = (String) map.get("PLAY_CODE");
			LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
			Clob numberClob=(Clob) map.get("NUMBER_INFO");
	        String jsonStr= CommonUtil.oracleClob2Str(numberClob);
	        StringBuffer numberInfo=new StringBuffer();
            JSONArray jsonArr = JSONArray.fromObject(jsonStr);
            List<Map> numberList=new ArrayList<Map>();
            for (int i = 0; i < jsonArr.size(); i++) { 
            	OrderVO orderVO = new OrderVO();
				String number=jsonArr.getJSONObject(i).getString("number");
				playCode=jsonArr.getJSONObject(i).getString("playCode");
				play = lottery.getLotteryPlay(lotteryCode, playCode);
				orderVO.setPlayCode(play.getName());
				orderVO.setNumberInfo(number);
				orderVOList.add(orderVO);
            }
			}
		return orderVOList;
	 
	 }

	/**
	  * 合买或代购方案详情
	  * @param programsOrderId
	  * @return
	  */
	 public Map programsOrderInfo(String programsOrderId,Integer buyType) {
		StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
		sb.append("p.PROGRAMS_ORDER_ID,p.AUTO_ORDER_ID,p.ISSUE,p.LOTTERY_CODE,p.PLAY_CODE,p.CREATE_TIME,p.SEND_TIME,p.RETURN_TIME,p.BONUS_TIME,");
		sb.append("p.TOTAL_WERE,p.AVG_AMOUNT,p.COMMISSION,p.POLL_CODE,p.DESCRIPTION,p.BUY_WERE,p.MIN_WERE,p.BUY_AMOUNT,");
		sb.append("p.NUMBER_INFO,p.PRIVACY,p.ITEM,p.MULTIPLE,p.LAST_WERE,p.LAST_AMOUNT,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.BUY_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) buyProgress,decode(nvl(p.ORDER_AMOUNT,0),0,0,nvl(p.LAST_AMOUNT,0)/nvl(p.ORDER_AMOUNT,0)) lastProgress,");
		sb.append("p.ORDER_STATUS,p.ORDER_AMOUNT,p.BONUS_AMOUNT,p.BONUS_STATUS,p.BONUS_TO_ACCOUNT,p.PLATFORM,p.IS_VERIFY,p.PASS_MODEL,p.BACKUP3 ");
		sb.append(" from TMS_PROGRAMS p inner join user_member u on p.user_code = u.user_code ");
		sb.append(" where 1 = 1 ");
		List<Object> values = new ArrayList<Object>();
		// 方案编号
		if (CommonUtil.isNotEmpty(programsOrderId)) {
			sb.append(" and p.PROGRAMS_ORDER_ID = ? ");
			values.add(programsOrderId);
		}
		if (CommonUtil.isNotEmpty(buyType)) {
			sb.append(" and p.BUY_TYPE  = ? ");
			values.add(buyType);
		}
		List<Map> orderMapList = orderDao.findBySql(sb.toString(), values);
		if (orderMapList != null && orderMapList.size() > 0) {
			Map orderMap = orderMapList.get(0);
			LotteryList lottery = new LotteryList();
			String lotteryCode = (String) orderMap.get("LOTTERY_CODE");
			LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
			String lotteryName=lotteryClass.getName();
			orderMap.put("LOTTERY_CODE", lotteryCode);
			orderMap.put("LOTTERY_NAME",lotteryName);
			String playCode = (String) orderMap.get("PLAY_CODE");
			LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
			orderMap.put("PLAY_CODE", play.getName());
			String pollCode = (String) orderMap.get("POLL_CODE");
			LotteryPoll lotteryPoll = lottery.getLotteryPoll(lotteryCode,playCode, pollCode);
			if(lotteryPoll!=null){
				orderMap.put("POLL_CODE", lotteryPoll.getName());
			}
			BigDecimal buyProgress =(BigDecimal) orderMap.get("BUYPROGRESS");
	        Double buyPro = buyProgress.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        orderMap.put("BUYPROGRESS", buyPro);
			BigDecimal lastProgress =(BigDecimal) orderMap.get("LASTPROGRESS");
	        Double lastPro = lastProgress.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        orderMap.put("LASTPROGRESS", lastPro);
	        String passModel=(String) orderMap.get("PASS_MODEL");
	        BigDecimal orderStatus= (BigDecimal) orderMap.get("ORDER_STATUS");
            Clob numberClob=(Clob) orderMap.get("NUMBER_INFO");
            if("竞彩足球".equals(lotteryName)){
            	Programs programs=new Programs();
            	programs.setProgramsOrderId(programsOrderId);
            	programs.setLotteryCode(lotteryCode);
            	programs.setPlayCode(playCode);
            	programs.setPassModel(passModel);
            	programs.setNumberInfo(CommonUtil.clobToString(numberClob));
            	programs.setOrderStatus(orderStatus.intValue());
            	List<ProgramsMatchBean> matchBeanList=getMatchBeanList(programs);
            	List<Map> numberList=new ArrayList<Map>();
            	if(matchBeanList!=null&&matchBeanList.size()>0){
            		for(ProgramsMatchBean matchBean : matchBeanList){
            			String matchNo=matchBean.getMatchNo();
            			String mainTeam=matchBean.getMainTeam();
            			String guestTeam= matchBean.getGuestTeam();
            			Integer mainTeamScore=matchBean.getMainTeamScore();
            			Integer guestTeamScore=matchBean.getGuestTeamScore();
            			List<MatchPlayBean> playList=matchBean.getPlayList();
            			for(MatchPlayBean playBean : playList){
            				Map numberMap=new HashMap();
            				numberMap.put("matchNo", matchNo);
            				numberMap.put("guestTeam", guestTeam);
            				String playName=playBean.getPlayName();
            				String letBall=playBean.getLetBall();
            				String result=playBean.getResult();
            				numberMap.put("playName", playName);
            				String mainTeamLetBall="";
            				if(CommonUtil.isNotEmpty(letBall)){
            					mainTeamLetBall= mainTeam.concat("(").concat(letBall).concat(")");
            				}else{
            					mainTeamLetBall=mainTeam;
            				}
            				numberMap.put("mainTeam",mainTeamLetBall);
            				if(CommonUtil.isNotEmpty(mainTeamScore)&&CommonUtil.isNotEmpty(guestTeamScore)){
            					numberMap.put("score", mainTeamScore+"-"+guestTeamScore);
            					
            				}else{
            					numberMap.put("score", "");
            				}
            				numberMap.put("result", result);
            				List<MatchPlayTermBean> termList=playBean.getTermList();
            				ArrayList<String> termStr=new ArrayList<String>();
            				for(MatchPlayTermBean termBean : termList ){
            					String termName=termBean.getTermName();
            					Double sp=termBean.getSp();
            					if(CommonUtil.isNotEmpty(sp)&&sp.doubleValue()!=0.0){
            						termName=termName.concat("(").concat(sp.toString()).concat(")");
            					}
            					termStr.add(termName);
            				}
            				numberMap.put("term",termStr.toArray());
            				numberList.add(numberMap);
            			}
            		}
            	}
            	JSONArray array = JSONArray.fromObject(numberList);
            	orderMap.put("NUMBER_INFO", array.toString());
            }else{
            	List<Map> numberList=new ArrayList<Map>();
            	String jsonStr= CommonUtil.oracleClob2Str(numberClob);
            	StringBuffer numberInfo=new StringBuffer();
            	JSONArray jsonArr = JSONArray.fromObject(jsonStr);
            	for (int i = 0; i < jsonArr.size(); i++) { 
            		Map numberMap=new HashMap();
            		String number=jsonArr.getJSONObject(i).getString("number");
            		playCode=jsonArr.getJSONObject(i).getString("playCode");
            		play = lottery.getLotteryPlay(lotteryCode, playCode);
            		numberMap.put("number", number);
            		numberMap.put("playName", play.getName());
            		numberList.add(numberMap);
            	}
            	orderMap.put("NUMBER_INFO", numberList);
            }
			return orderMap;
		}
		return null;
	}
	private List<ProgramsMatchBean> getMatchBeanList(Programs programs) {
		    String lotteryCode = programs.getLotteryCode();
	        String playCode = programs.getPlayCode();

	        List<NumberInfo> numberInfoList = null;
	        try {
	            numberInfoList = JsonBinder.buildNonDefaultBinder().getMapper().readValue(programs.getNumberInfo(), new TypeReference<List<NumberInfo>>() {
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        Map<String, Object> matchMap = SubNumberUtils.matchList(numberInfoList);
	        if (!CommonUtil.isNotEmpty(matchMap)) {
	            return new ArrayList<ProgramsMatchBean>();
	        }

	        List<MatchItemInfo> matchItemInfoList = (List<MatchItemInfo>) matchMap.get("matchItemInfoList");

	        //方案是否出票成功
	        boolean sendSuccess = false;
	        if (programs.getOrderStatus().equals(IOrderConstants.PROGRAMS_STATUS_SUCCESS)) {
	            sendSuccess = true;
	        }
	        String issue = "";
	        String sn = "";

	        Map<String, Object> returnNumberMap = null;
	        if (sendSuccess) {
	            List<ReturnNumber> returnNumberList = getReturnNumberListByProgramsOrderId(programs.getProgramsOrderId());
	            if (lotteryCode.equals("200")) {
	                returnNumberMap = SubNumberUtils.formatFootballReturnNumber(returnNumberList);
	            } 
	        }
	        List<ProgramsMatchBean> programsMatchBeanList = new ArrayList<ProgramsMatchBean>();
	        Map<String, ProgramsMatchBean> finalMatchMap = new HashMap<String, ProgramsMatchBean>();
	        if (lotteryCode.equals("200")) {
	            SubIssueForJingCaiZuQiu subIssueForJingCaiZuQiu = null;

	            for (MatchItemInfo matchItemInfo : matchItemInfoList) {
	                if (CommonUtil.isNotEmpty(matchItemInfo)) {
	                    issue = matchItemInfo.getMatchId().substring(0, 8);
	                    sn = matchItemInfo.getMatchId().substring(8);
	                    playCode = matchItemInfo.getSubPlayCode();

	                    subIssueForJingCaiZuQiu = getSubIssueForJingCaiZuQiuBySnDate(sn, issue, "00", "02");
	                    if (CommonUtil.isNotEmpty(subIssueForJingCaiZuQiu)) {

	                        Integer mainTeamScore = subIssueForJingCaiZuQiu.getMainTeamScore();
	                        Integer guestTeamScore = subIssueForJingCaiZuQiu.getGuestTeamScore();

	                        ProgramsMatchBean programsMatchBean = null;
	                        List<MatchPlayBean> matchPlayBeanList = null;
	                        String matchId = subIssueForJingCaiZuQiu.getMatchId();
	                        if (finalMatchMap.containsKey(matchId)) {
	                            programsMatchBean = finalMatchMap.get(matchId);
	                            matchPlayBeanList = programsMatchBean.getPlayList();
	                        } else {
	                            programsMatchBean = new ProgramsMatchBean();
	                            programsMatchBean.setMachId(matchItemInfo.getMatchId());
	                            programsMatchBean.setWeek(subIssueForJingCaiZuQiu.getWeek());
	                            programsMatchBean.setSn(sn);
	                            programsMatchBean.setMatchNo(subIssueForJingCaiZuQiu.getWeek() + sn);
	                            programsMatchBean.setMainTeam(subIssueForJingCaiZuQiu.getMainTeam());
	                            programsMatchBean.setGuestTeam(subIssueForJingCaiZuQiu.getGuestTeam());
	                            programsMatchBean.setOpenTime(subIssueForJingCaiZuQiu.getEndTime());
	                            if (subIssueForJingCaiZuQiu.getEndOperator().equals(IOrderConstants.SUB_ISSUE_END_OPERATOR_CANCEL)) {
	                                programsMatchBean.setMatchCancel(1);
	                            } else {
	                                programsMatchBean.setMatchCancel(0);
	                            }
//	                            programsMatchBean.setStatus(subIssueForJingCaiZuQiu.getEndStatus());
//	                            programsMatchBean.setStatusDesc(AdapterMobileUtils.getMatchStatusDesc(subIssueForJingCaiZuQiu.getEndStatus()));

	                            programsMatchBean.setStatus(4);
	                            programsMatchBean.setStatusDesc("");

	                            programsMatchBean.setMainTeamHalfScore(subIssueForJingCaiZuQiu.getMainTeamHalfScore());
	                            programsMatchBean.setGuestTeamHalfScore(subIssueForJingCaiZuQiu.getGuestTeamHalfScore());
	                            programsMatchBean.setMainTeamScore(subIssueForJingCaiZuQiu.getMainTeamScore());
	                            programsMatchBean.setGuestTeamScore(subIssueForJingCaiZuQiu.getGuestTeamScore());
	                            matchPlayBeanList = new ArrayList<MatchPlayBean>();
	                            programsMatchBeanList.add(programsMatchBean);
	                        }

	                        String letBall = "";
	                        //赛果
	                        String result = "";
	                        if (sendSuccess && CommonUtil.isNotEmpty(returnNumberMap)) {
	                            //让分胜平负
	                            if (playCode.equals("01")) {
	                                letBall = CommonUtil.formatStr(subIssueForJingCaiZuQiu.getLetBall(), "");
	                                result = AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, letBall);
	                            }
	                            //总进球数
	                            if (playCode.equals("02")) {
	                                result = AdapterMobileUtils.zjqsScore(mainTeamScore, guestTeamScore);
	                            }
	                            //半场胜平负
	                            if (playCode.equals("03")) {
	                                result = AdapterMobileUtils.spfScore(subIssueForJingCaiZuQiu.getMainTeamHalfScore(), subIssueForJingCaiZuQiu.getGuestTeamHalfScore(), "0") + AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, "0");
	                            }
	                            //比分
	                            if (playCode.equals("04")) {
	                                result = AdapterMobileUtils.bfScore(mainTeamScore, guestTeamScore);
	                            }
	                            //胜平负
	                            if (playCode.equals("05")) {
	                                result = AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, "0");
	                            }
	                        } else {
	                            //让分胜平负
	                            if (playCode.equals("01")) {
	                                letBall = CommonUtil.formatStr(subIssueForJingCaiZuQiu.getLetBall(), "");
	                                result = AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, letBall);
	                            }
	                            //总进球数
	                            if (playCode.equals("02")) {
	                                result = AdapterMobileUtils.zjqsScore(mainTeamScore, guestTeamScore);
	                            }
	                            //半场胜平负
	                            if (playCode.equals("03")) {
	                                result = AdapterMobileUtils.spfScore(subIssueForJingCaiZuQiu.getMainTeamHalfScore(), subIssueForJingCaiZuQiu.getGuestTeamHalfScore(), "0") + AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, "0");
	                            }
	                            //比分
	                            if (playCode.equals("04")) {
	                                result = AdapterMobileUtils.bfScore(mainTeamScore, guestTeamScore);
	                            }
	                            //胜平负
	                            if (playCode.equals("05")) {
	                                result = AdapterMobileUtils.spfScore(mainTeamScore, guestTeamScore, "0");
	                            }
	                        }
	                        MatchPlayBean matchPlayBean = new MatchPlayBean();
	                        matchPlayBean.setPlayCode(playCode);
	                        LotteryPlay lotteryPlay = LotteryList.getLotteryPlay(lotteryCode, playCode);
	                        matchPlayBean.setPlayName(lotteryPlay.getName());
	                        matchPlayBean.setPlayCode(playCode);
	                        matchPlayBean.setLetBall(letBall);
	                        matchPlayBean.setResult(result);

	                        String number = matchItemInfo.getValue();
	                        String[] numberArray = number.split(",");

	                        List<MatchPlayTermBean> matchPlayTermBeanList = new ArrayList<MatchPlayTermBean>();
	                        for (String str : numberArray) {
	                            String subChinaNumber = SubNumberUtils.getNumberChinaValue(lotteryCode + playCode + str);
	                            Double sp = 0d;
	                            if (sendSuccess) {
	                                //出票成功，取出票口返回的sp值
	                                if (subIssueForJingCaiZuQiu.getEndOperator().equals(IOrderConstants.SUB_ISSUE_END_OPERATOR_CANCEL)) {
	                                    sp = 1.00;
	                                } else {
	                                    sp = CommonUtil.formatDouble(returnNumberMap.get(issue + sn + playCode + str) + "", 1.00);
	                                }
	                            }
	                            MatchPlayTermBean matchPlayTermBean = new MatchPlayTermBean();
	                            if (result.equals(subChinaNumber)) {
	                                matchPlayTermBean.setChecked(1);
	                            } else {
	                                matchPlayTermBean.setChecked(0);
	                            }
	                            matchPlayTermBean.setSp(sp);
	                            matchPlayTermBean.setTermName(subChinaNumber);
	                            matchPlayTermBean.setTerm(str);
	                            matchPlayTermBeanList.add(matchPlayTermBean);
	                            Collections.sort(matchPlayTermBeanList);
	                        }
	                        matchPlayBean.setTermList(matchPlayTermBeanList);
	                        matchPlayBeanList.add(matchPlayBean);
	                        Collections.sort(matchPlayBeanList);
	                        programsMatchBean.setPlayList(matchPlayBeanList);

	                        finalMatchMap.put(matchId, programsMatchBean);
	                    }
	                }
	            }
	        } 
	        Collections.sort(programsMatchBeanList);
	        return programsMatchBeanList;
	    }
	public List<ReturnNumber> getReturnNumberListByProgramsOrderId(String programsOrderId){
		String sql = " From ReturnNumber where programsOrderId = ? ";
		List<Object> values = new ArrayList<Object>();
		values.add(programsOrderId);
		return returnNumberDao.find(sql, values);
	}
	
	public SubIssueForJingCaiZuQiu getSubIssueForJingCaiZuQiuBySnDate(String sn,String issue,String playCode,String pollCode){
		String sql = "From SubIssueForJingCaiZuQiu where sn=? and issue=? and playCode=? and pollCode=?";
		List<Object> values = new ArrayList<Object>();
		values.add(sn);
		values.add(issue);
		values.add(playCode);
		values.add(pollCode);
		List<SubIssueForJingCaiZuQiu> list=subIssueDao.find(sql, values);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	    
	/**
	 * 根据方案Id获取票信息
	 * 
	 * @param programsOrderId
	 * @return
	 */
	public EasyuiDataGridJson ticketList(EasyuiDataGrid dg,String programsOrderId) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder sb = new StringBuilder(
				"select t.TICKET_ID,t.LOTTERY_CODE,t.PLAY_CODE,t.NUMBER_INFO,t.ITEM,t.MULTIPLE,t.TICKET_STATUS,t.BONUS_AMOUNT,");
		sb.append("t.AMOUNT from TMS_TICKET t where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		// 方案编号
		if (CommonUtil.isNotEmpty(programsOrderId)) {
			sb.append(" and t.PROGRAMS_ORDER_ID = ? ");
			values.add(programsOrderId);
		}
		String totalHql = " select count(*)  from (" + sb + ")";
		j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
		List<Map> ticketMapList = orderDao.findBySql(sb.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<Ticket> ticketList = new ArrayList<Ticket>();
		if (ticketMapList != null && ticketMapList.size() > 0) {// 转换模型
			LotteryList lottery = new LotteryList();
			for (Map map : ticketMapList) {
				Ticket t=new Ticket();
				t.setTicketId((String)map.get("TICKET_ID"));
				String lotteryCode = (String) map.get("LOTTERY_CODE");
				String playCode = (String) map.get("PLAY_CODE");
				LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
				t.setPlayCode(play.getName());
				t.setNumberInfo((String)map.get("NUMBER_INFO"));
				t.setItem(((BigDecimal) map.get("ITEM")).intValue());
				t.setMultiple(((BigDecimal) map.get("MULTIPLE")).intValue());
				t.setTicketStatus(((BigDecimal) map.get("TICKET_STATUS")).intValue());
				t.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")));
				ticketList.add(t);
			}
			}
		j.setRows(ticketList);// 设置返回的行
		return j;
	}
	
	 /**
	  * 彩票某期的开奖号码
	  * @param lotteryCode
	  * @param issue
	  * @return
	  */
	 public List<Map> mainIssue(String lotteryCode,String issue){
		StringBuilder sb = new StringBuilder("select LOTTERY_CODE,NAME ,START_TIME,END_TIME,");
		sb.append("STATUS,SEND_STATUS,BONUS_TIME,BONUS_STATUS,BONUS_NUMBER,BONUS_TOTAL,SALE_TOTAL,GLOBAL_SALE_TOTAL ");
		sb.append(" from TMS_MAIN_ISSUE  ");
		sb.append(" where 1 = 1 ");
		List<Object> values = new ArrayList<Object>();
		// 彩票编号
		if (CommonUtil.isNotEmpty(lotteryCode)) {
			sb.append(" and LOTTERY_CODE = ? ");
			values.add(lotteryCode);
		}
		if (CommonUtil.isNotEmpty(issue)) {
			sb.append(" and NAME  = ? ");
			values.add(issue);
		}
		List<Map> issueMapList = orderDao.findBySql(sb.toString(), values);
		return issueMapList;
	 }
	 
	 /**
	  * 合买方案描述修改
	  * @param programsOrderId
	  * @param description
	  */
	 public void editSynDescription(String programsOrderId,String description){
		 String sql="update TMS_PROGRAMS p set p.DESCRIPTION='"+description+"',p.IS_VERIFY=1 where p.PROGRAMS_ORDER_ID='"+programsOrderId+"'";
		 orderDao.executeSql(sql);
	 }
	 
	 /**
	  * 合买用户
	  * @param dg
	  * @param programsOrderId
	  * @return
	  */
	 public EasyuiDataGridJson synMemberList(EasyuiDataGrid dg,String programsOrderId){

			EasyuiDataGridJson j = new EasyuiDataGridJson();
			StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,");
			sb.append(" o.ORDER_ID,o.ORDER_TYPE,o.CREATE_TIME,o.BUY_WERE,");
			sb.append(" o.ORDER_AMOUNT,o.BONUS_AMOUNT,o.FIX_BONUS_AMOUNT ");
			sb.append(" from TMS_ORDER o inner join user_member u on o.user_code = u.user_code ");
			sb.append(" where 1=1 ");
			List<Object> values = new ArrayList<Object>();
			// 方案编号
			if (CommonUtil.isNotEmpty(programsOrderId)) {
				sb.append(" and o.PROGRAMS_ORDER_ID = ? ");
				values.add(programsOrderId.trim());
			}
			String totalHql = " select count(*)  from (" + sb + ")";
			j.setTotal(orderDao.countBySql(totalHql, values).longValue());// 设置总记录数
			List<Map> memberMapList = orderDao.findBySql(sb.toString(), values,
					dg.getPage(), dg.getRows());// 查询分页
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			if (memberMapList != null && memberMapList.size() > 0) {// 转换模型
				for (Map map : memberMapList) {
					OrderVO vo=new OrderVO();
					vo.setUserName((String)map.get("USER_NAME"));
					BigDecimal buyWere=(BigDecimal)map.get("BUY_WERE");
					vo.setBuyWere(buyWere.intValue());
					vo.setOrderAmount(CommonUtil.formatDouble(map.get("ORDER_AMOUNT")));
					Date createTime = (Date) map.get("CREATE_TIME");
					vo.setCreateTime(DateUtil.format(createTime));
					vo.setFixBonusAmount(CommonUtil.formatDouble(map.get("FIX_BONUS_AMOUNT")));
					orderList.add(vo);
				}
				}
			j.setRows(orderList);// 设置返回的行
			return j;
		
	 }
	 /**
	  * 合买方案置顶
	  * @param programsList
	  * @return
	  */
	 public boolean  setTop(List<String> programsList){
		 StringBuilder str=new StringBuilder("update TMS_PROGRAMS set IS_TOP= 1 where PROGRAMS_ORDER_ID= ? ");
		 int n=0;
		 for(int i=0;i<programsList.size();i++){
			 List<Object> values = new ArrayList<Object>();
			 String programsOrderId=programsList.get(i);
			 values.add(programsOrderId);
			 int result=orderDao.executeSql(str.toString(), values);
			 if(result>0){
				 n++;
			 }
		 }
		 if(n==programsList.size()){
			 return true;
		 }
		 return false;
	 }
}
