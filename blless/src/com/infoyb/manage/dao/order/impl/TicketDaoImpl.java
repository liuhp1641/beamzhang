package com.cm.manage.dao.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.order.ITicketDao;
import com.cm.manage.model.order.TicketCenter;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryClass;
import com.cm.manage.util.lottery.bean.LotteryPlay;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.TicketCenterVO;
import com.cm.manage.vo.order.TicketVO;
import com.cm.order.http.bean.TicketCenterBean;

@Repository
public class TicketDaoImpl implements ITicketDao {

    @Autowired
    private IBaseDao<Object> ticketDao;

    /**
     * 出票查询
     *
     * @param dg
     * @param ticket
     * @return
     */
    @Override
    public EasyuiDataGridJson ticketList(EasyuiDataGrid dg, TicketVO ticket) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder str = new StringBuilder("select t.TICKET_ID,t.PROGRAMS_ORDER_ID,u.USER_CODE,u.USER_NAME,t.LOTTERY_CODE,t.POST_CODE,");
        str.append(" t.ISSUE,t.PLAY_CODE,t.ITEM,t.MULTIPLE,t.AMOUNT,t.BONUS_AMOUNT,t.TICKET_STATUS,t.CREATE_TIME,t.ACCEPT_TIME,p.BUY_TYPE,p.send_time ");
        str.append(" from TMS_TICKET t left join user_member u on t.user_code = u.user_code left join TMS_PROGRAMS p on t.PROGRAMS_ORDER_ID=p.PROGRAMS_ORDER_ID where 1=1");
        List<Object> values = new ArrayList<Object>();
        if (ticket != null) {// 添加查询条件
            // 是否模糊查询
            boolean flag = ticket.isFlag();

            // 用户编号
            if (CommonUtil.isNotEmpty(ticket.getUserCode())) {
                if (flag) {
                    str.append(" and u.user_code like ? ");
                    values.add("%" + ticket.getUserCode().trim() + "%");
                } else {
                    str.append(" and u.user_code = ? ");
                    values.add(ticket.getUserCode().trim());
                }
            }

            // 用户名称
            if (CommonUtil.isNotEmpty(ticket.getUserName())) {
                if (flag) {
                    str.append(" and u.user_name like ? ");
                    values.add("%" + ticket.getUserName().trim() + "%");
                } else {
                    str.append(" and u.user_name = ? ");
                    values.add(ticket.getUserName().trim());
                }
            }
            //手机
            if (CommonUtil.isNotEmpty(ticket.getMobile())) {

				if (flag) {
					str.append(" and u.MOBILE like ? ");
					values.add("%" + ticket.getMobile().trim() + "%");
				} else {
					str.append(" and u.MOBILE = ? ");
					values.add(ticket.getMobile().trim());
				}
            }
            // 方案编号
            if (CommonUtil.isNotEmpty(ticket.getProgramsOrderId())) {
                if (flag) {
                    str.append(" and t.PROGRAMS_ORDER_ID like ? ");
                    values.add("%" + ticket.getProgramsOrderId().trim() + "%");
                } else {
                    str.append(" and t.PROGRAMS_ORDER_ID = ? ");
                    values.add(ticket.getProgramsOrderId().trim());
                }
            }
            //出票编号
            if (CommonUtil.isNotEmpty(ticket.getTicketId())) {
                if (flag) {
                    str.append(" and t.TICKET_ID like ? ");
                    values.add("%" + ticket.getTicketId().trim() + "%");
                } else {
                    str.append(" and t.TICKET_ID = ? ");
                    values.add(ticket.getTicketId().trim());
                }
            }

            // 彩种
            if (CommonUtil.isNotEmpty(ticket.getLotteryCode())) {
                str.append(" and t.LOTTERY_CODE = ?");
                values.add(ticket.getLotteryCode());
            }

            // 奖期范围
            if (CommonUtil.isNotEmpty(ticket.getIssueMin())) {
                str.append(" and t.ISSUE >= ?");
                values.add(ticket.getIssueMin());
            }
            if (CommonUtil.isNotEmpty(ticket.getIssueMax())) {
                str.append(" and t.ISSUE <= ?");
                values.add(ticket.getIssueMax());
            }
            //出票状态
            if (CommonUtil.isNotEmpty(ticket.getTicketStatus())) {
                str.append(" and t.TICKET_STATUS = ?");
                values.add(ticket.getTicketStatus());
            }
            //出票口
            if (CommonUtil.isNotEmpty(ticket.getPostCode())) {
                str.append(" and t.POST_CODE = ?");
                values.add(ticket.getPostCode());
            }
            // 创建时间
            if (CommonUtil.isNotEmpty(ticket.getCreateStartTime())) {
                str.append(" and t.create_time >= ?");
                values.add(DateUtil.format(ticket.getCreateStartTime(),"yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(ticket.getCrateEndTime())) {
                str.append(" and t.create_time <= ?");
                values.add(DateUtil.format(ticket.getCrateEndTime(),"yy-MM-dd HH:mm:ss"));
            }
            //处理时间
            if (CommonUtil.isNotEmpty(ticket.getAcceptStartTime())) {
                str.append(" and t.ACCEPT_TIME >= ?");
                values.add(DateUtil.format(ticket.getAcceptStartTime(),"yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(ticket.getAcceptEndTime())) {
                str.append(" and t.ACCEPT_TIME <= ?");
                values.add(DateUtil.format(ticket.getAcceptEndTime(),"yy-MM-dd HH:mm:ss"));
            }

        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(ticketDao.countBySql(totalHql, values).longValue());// 设置总记录数
        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("ticketId")) {
                sort = "t.TICKET_ID";
            }
            if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
                sort = "t.PROGRAMS_ORDER_ID";
            }
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = "t.CREATE_TIME";
            }

            if (dg.getSort().toString().equalsIgnoreCase("acceptTime")) {
                sort = "t.ACCEPT_TIME";
            }

            if (!sort.equals("")) {
                str.append(" order by " + sort + " " + dg.getOrder());
            }
        }

        List<Map> ticketMapList = ticketDao.findBySql(str.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<TicketVO> ticketList = new ArrayList<TicketVO>();
        if (ticketMapList != null && ticketMapList.size() > 0) {// 转换模型
            LotteryList lottery = new LotteryList();
            for (Map map : ticketMapList) {
                TicketVO vo = new TicketVO();
                vo.setTicketId((String) map.get("TICKET_ID"));
                vo.setProgramsOrderId((String) map.get("PROGRAMS_ORDER_ID"));
                vo.setUserCode((String) map.get("USER_CODE"));
                vo.setUserName((String) map.get("USER_NAME"));
                vo.setIssue((String) map.get("ISSUE"));
                String lotteryCode = (String) map.get("LOTTERY_CODE");
                LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
                vo.setLotteryCode(lotteryClass.getName());
                String playCode = (String) map.get("PLAY_CODE");
                LotteryPlay play = lottery.getLotteryPlay(lotteryCode, playCode);
                vo.setPlayCode(play.getName());
                vo.setItem(((BigDecimal) map.get("ITEM")).intValue());
                vo.setMultiple(((BigDecimal) map.get("MULTIPLE")).intValue());
                vo.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));
                vo.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")));
                vo.setTicketStatus(((BigDecimal) map.get("TICKET_STATUS")).intValue());
                Date createTime = (Date) map.get("CREATE_TIME");
                vo.setCreateTime(DateUtil.format(createTime));
                Date acceptTime = (Date) map.get("ACCEPT_TIME");
                vo.setAcceptTime(DateUtil.format(acceptTime));
                vo.setPostCode((String) map.get("POST_CODE"));
                vo.setBuyType(((BigDecimal) map.get("BUY_TYPE")).intValue());
                Date sendTime = (Date) map.get("SEND_TIME");
                vo.setSendTime(DateUtil.format(sendTime));
                ticketList.add(vo);
            }
        }
        j.setRows(ticketList);// 设置返回的行
        return j;
    }

    /**
     * 传票查询
     *
     * @param dg
     * @param ticket
     * @return
     */
    public EasyuiDataGridJson unticketList(EasyuiDataGrid dg, TicketVO ticket) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder str = new StringBuilder("select t.TICKET_ID,t.PROGRAMS_ORDER_ID,t.LOTTERY_CODE,t.POST_CODE,i.STATUS,");
        str.append(" t.ISSUE,t.PLAY_CODE,t.ITEM,t.MULTIPLE,t.AMOUNT,t.BONUS_AMOUNT,t.TICKET_STATUS,t.CREATE_TIME,t.ACCEPT_TIME,t.err_code,t.err_msg,tp.send_time,tp.buy_type ");
        str.append(" from TMS_TICKET t left join TMS_MAIN_ISSUE i on t.LOTTERY_CODE=i.LOTTERY_CODE and  t.ISSUE =i.NAME left join tms_programs tp on t.programs_order_id = tp.programs_order_id where t.TICKET_STATUS in (1,2,4,5) and i.status in (1,3) ");
        List<Object> values = new ArrayList<Object>();
        if (ticket != null) {// 添加查询条件
            // 是否模糊查询
            boolean flag = ticket.isFlag();
            // 方案编号
            if (CommonUtil.isNotEmpty(ticket.getProgramsOrderId())) {
                if (flag) {
                    str.append(" and t.PROGRAMS_ORDER_ID like ? ");
                    values.add("%" + ticket.getProgramsOrderId().trim() + "%");
                } else {
                    str.append(" and t.PROGRAMS_ORDER_ID = ? ");
                    values.add(ticket.getProgramsOrderId().trim());
                }
            }
            //出票编号
            if (CommonUtil.isNotEmpty(ticket.getTicketId())) {
                if (flag) {
                    str.append(" and t.TICKET_ID like ? ");
                    values.add("%" + ticket.getTicketId().trim() + "%");
                } else {
                    str.append(" and t.TICKET_ID = ? ");
                    values.add(ticket.getTicketId().trim());
                }
            }

            // 彩种
            if (CommonUtil.isNotEmpty(ticket.getLotteryCode())) {
                str.append(" and t.LOTTERY_CODE = ?");
                values.add(ticket.getLotteryCode());
            }

            //出票口
            if (CommonUtil.isNotEmpty(ticket.getPostCode())) {
                str.append(" and t.POST_CODE = ?");
                values.add(ticket.getPostCode());
            }
            // 创建时间
            if (CommonUtil.isNotEmpty(ticket.getCreateStartTime())) {
                str.append(" and t.create_time >= ?");
                values.add(DateUtil.format(ticket.getCreateStartTime(),"yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(ticket.getCrateEndTime())) {
                str.append(" and t.create_time <= ?");
                values.add(DateUtil.format(ticket.getCrateEndTime(),"yy-MM-dd HH:mm:ss"));
            }
            //处理时间
            if (CommonUtil.isNotEmpty(ticket.getAcceptStartTime())) {
                str.append(" and t.ACCEPT_TIME >= ?");
                values.add(DateUtil.format(ticket.getAcceptStartTime(),"yy-MM-dd HH:mm:ss"));
            }
            if (CommonUtil.isNotEmpty(ticket.getAcceptEndTime())) {
                str.append(" and t.ACCEPT_TIME <= ?");
                values.add(DateUtil.format(ticket.getAcceptEndTime(),"yy-MM-dd HH:mm:ss"));
            }

        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(ticketDao.countBySql(totalHql, values).longValue());// 设置总记录数
        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("ticketId")) {
                sort = "t.TICKET_ID";
            }
            if (dg.getSort().toString().equalsIgnoreCase("programsOrderId")) {
                sort = "t.PROGRAMS_ORDER_ID";
            }
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = "t.CREATE_TIME";
            }

            if (dg.getSort().toString().equalsIgnoreCase("acceptTime")) {
                sort = "t.ACCEPT_TIME";
            }

            if (!sort.equals("")) {
                str.append(" order by " + sort + " " + dg.getOrder());
            }
        }

        List<Map> ticketMapList = ticketDao.findBySql(str.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<TicketVO> ticketList = new ArrayList<TicketVO>();
        if (ticketMapList != null && ticketMapList.size() > 0) {// 转换模型
            LotteryList lottery = new LotteryList();
            for (Map map : ticketMapList) {
                TicketVO vo = new TicketVO();
                vo.setTicketId((String) map.get("TICKET_ID"));
                vo.setProgramsOrderId((String) map.get("PROGRAMS_ORDER_ID"));
                vo.setIssue((String) map.get("ISSUE"));
                String lotteryCode = (String) map.get("LOTTERY_CODE");
                LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
                vo.setLotteryCode(lotteryClass.getName());
                vo.setAmount(CommonUtil.formatDouble(map.get("AMOUNT")));
                vo.setTicketStatus(((BigDecimal) map.get("TICKET_STATUS")).intValue());
                BigDecimal saleStatus = map.get("STATUS") == null ? new BigDecimal(0) : (BigDecimal) map.get("STATUS");
                vo.setSaleStatus(saleStatus.intValue());
                Date createTime = (Date) map.get("CREATE_TIME");
                vo.setCreateTime(DateUtil.format(createTime));
                Date acceptTime = (Date) map.get("ACCEPT_TIME");
                vo.setAcceptTime(DateUtil.format(acceptTime));
                vo.setPostCode((String) map.get("POST_CODE"));

                vo.setErrCode((String) map.get("ERR_CODE"));
                vo.setErrMsg((String) map.get("ERR_MSG"));
                vo.setBuyType(((BigDecimal) map.get("BUY_TYPE")).intValue());
                Date sendTime = (Date) map.get("SEND_TIME");
                vo.setSendTime(DateUtil.format(sendTime));
                ticketList.add(vo);
            }
        }
        j.setRows(ticketList);// 设置返回的行
        return j;


    }

    /**
     * 出票账户
     *
     * @param ticketBean
     * @return
     */
    public List<TicketCenterVO> ticketCenterList(List<TicketCenterBean> ticketBean) {
        List<TicketCenterVO> ticketCenterList = new ArrayList<TicketCenterVO>();
        StringBuilder str = new StringBuilder("select ID,POST_CODE,POST_NAME,BALANCE from TMS_TICKET_CENTER ");
        List<Map> ticketWarList = ticketDao.findBySql(str.toString());
        Map<String, Object> centerMap = new HashMap<String, Object>();
        for (int i = 0; i < ticketBean.size(); i++) {
            TicketCenterVO center = new TicketCenterVO();
            TicketCenterBean bean = ticketBean.get(i);
            center.setPostCode(bean.getPostCode());
            center.setPostName(bean.getPostName());
            center.setBalance(bean.getBalance());
            centerMap.put(bean.getPostCode(), center);
        }
        if (ticketWarList != null && ticketWarList.size() > 0) {
            for (int i = 0; i < ticketWarList.size(); i++) {
                Map warMap = ticketWarList.get(i);
                BigDecimal id = (BigDecimal) warMap.get("ID");
                String postCode = (String) warMap.get("POST_CODE");
                BigDecimal maxBalance = warMap.get("BALANCE") == null ? new BigDecimal(0) : (BigDecimal) warMap.get("BALANCE");
                TicketCenterVO vo = (TicketCenterVO) centerMap.get(postCode);
                if (vo != null) {
                    vo.setMaxBalance(maxBalance.doubleValue());
                    vo.setId(id.longValue());
                    centerMap.put(postCode, vo);
                }
            }
        }
        Iterator it = centerMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            ticketCenterList.add((TicketCenterVO) centerMap.get(key));
        }
        return ticketCenterList;
    }

    /**
     * 余额警告阀值设置
     *
     * @param vo
     * @return
     */
    public void ticketCenterWar(TicketCenterVO vo) {
        TicketCenter center = new TicketCenter();
        center.setPostCode(vo.getPostCode());
        center.setPostName(vo.getPostName());
        if (vo.getId() != null) {
            center.setId(vo.getId());
        }
        center.setBalance(vo.getMaxBalance());
        ticketDao.saveOrUpdate(center);
    }

    /**
     * 票号详情
     *
     * @param ticketId
     * @return
     */
    public Map ticketInfo(String ticketId) {

        StringBuilder str = new StringBuilder("select t.TICKET_ID,t.PROGRAMS_ORDER_ID,t.LOTTERY_CODE,t.POST_CODE,t.NUMBER_INFO,");
        str.append(" t.ISSUE,t.PLAY_CODE,t.ITEM,t.MULTIPLE,t.AMOUNT,t.BONUS_AMOUNT,t.TICKET_STATUS,t.CREATE_TIME,t.ACCEPT_TIME");
        str.append(" from TMS_TICKET t  where 1=1");
        List<Object> values = new ArrayList<Object>();
        // 票编号
        if (CommonUtil.isNotEmpty(ticketId)) {
            str.append(" and t.TICKET_ID = ? ");
            values.add(ticketId);
        }
        List<Map> ticketMapList = ticketDao.findBySql(str.toString(), values);
        if (ticketMapList != null && ticketMapList.size() > 0) {
            Map ticketMap = ticketMapList.get(0);
            LotteryList lottery = new LotteryList();
            String lotteryCode = (String) ticketMap.get("LOTTERY_CODE");
            LotteryClass lotteryClass = lottery.getLotteryClass(lotteryCode);
            ticketMap.put("LOTTERY_CODE", lotteryClass.getName());
            return ticketMap;
        }
        return null;
    }

    @Override
    public List<Map> getTicketCenterWarning() {
        StringBuilder str = new StringBuilder("select POST_CODE,BALANCE from TMS_TICKET_CENTER ");
        List<Map> ticketWarList = ticketDao.findBySql(str.toString());
        return ticketWarList;
    }
}
