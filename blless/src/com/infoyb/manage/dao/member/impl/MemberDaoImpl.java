package com.cm.manage.dao.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.member.IMemberDao;
import com.cm.manage.model.account.Account;
import com.cm.manage.model.account.BankList;
import com.cm.manage.model.account.BindChargeBank;
import com.cm.manage.model.account.BindFillBank;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.model.member.Cooperation;
import com.cm.manage.model.member.Member;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.member.Customer;
import com.cm.manage.vo.member.MemberLoginVO;
import com.cm.user.http.bean.MemberBean;

@Repository("memberDao")
public class MemberDaoImpl implements IMemberDao {

    @Autowired
    private IBaseDao<Member> memberDao;
    @Autowired
    private IBaseDao<Map<String, Object>> mapDao;
    @Autowired
    private IBaseDao<Account> accountDao;
    @Autowired
    private IBaseDao<Cooperation> channelDao;
    @Autowired
    private IBaseDao<BindChargeBank> rechargeDao;
    @Autowired
    private IBaseDao<DrawBindBank> drawBankDao;

    @Autowired
    private IBaseDao<BindFillBank> fillbankDao;

    @Autowired
    private IBaseDao<BankList> bankDao;

    public Long yvipCount() {
        String totalHql = "select count(*) from Member where createTime >= trunc(SYSDATE-1) and createTime < trunc(SYSDATE ) and vip is not null and memberType=0 ";
        Long vipCount = memberDao.count(totalHql);
        return vipCount;
    }


    public Long mvipCount() {
        String totalHql = "select count(*) from Member where createTime >=TRUNC(SYSDATE, 'MM') and createTime <= last_day(SYSDATE) and vip is not null and memberType=0 ";
        Long vipCount = memberDao.count(totalHql);
        return vipCount;
    }


    public Long dregCount() {
        String totalHql = "select count(*) from Member where  trunc(sysdate)=trunc(createTime) and memberType=0 ";
        Long regCount = memberDao.count(totalHql);
        return regCount;
    }


    public Long yregCount() {
        String totalHql = "select count(*) from Member where createTime >= trunc(SYSDATE-1) and createTime < trunc(SYSDATE ) and memberType=0 ";
        Long regCount = memberDao.count(totalHql);
        return regCount;
    }

    public Long mregCount() {
        String totalHql = "select count(*) from Member where createTime >=TRUNC(SYSDATE, 'MM') and createTime <= last_day(SYSDATE) and memberType=0 ";
        Long regCount = memberDao.count(totalHql);
        return regCount;
    }


    public Long ultregCount() {
        String totalHql = "select count(*) from Member where createTime BETWEEN Trunc(add_months(SYSDATE,-1),'mm') AND last_day(add_months(sysdate,-1)) and memberType=0  ";
        Long regCount = memberDao.count(totalHql);
        return regCount;
    }

    /**
	 * 平台累计注册用户数
	 * @return
	 */
	public Long allRegCount(){
		 String totalHql = "select count(*) from Member where memberType=0 ";
         Long allRegCount = memberDao.count(totalHql);
         return allRegCount;
	}

    public List<EasyuiTreeNode> channeltree() {

        String hql = " from Cooperation ";
        List<Cooperation> channels = channelDao.find(hql);
        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        for (Cooperation channel : channels) {
            tree.add(tree(channel, false));
        }
        return tree;

    }

    private EasyuiTreeNode tree(Cooperation channel, boolean recursive) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(channel.getSid());
        node.setText(channel.getName());
        String hql = "from Cooperation  where pid ='" + channel.getSid() + "' ";
        List<Cooperation> channels = channelDao.find(hql);
        if (channels != null && channels.size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
                List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
                for (Cooperation c : channels) {
                    EasyuiTreeNode t = tree(c, true);
                    children.add(t);
                }
                node.setChildren(children);
            }
        }
        return node;
    }

    public EasyuiDataGridJson memberDatas(EasyuiDataGrid dg, Customer customer, boolean flag) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();

        String sql = "select u.USER_NAME,u.USER_CODE,u.GRADE,u.REAL_NAME , u.MOBILE ,u.CARD_CODE,u.STATUS,"
                + " a.RECHARGE_PAY_TOTAL,a.BONUS_PAY_TOTAL,a.PRESENT_PAY_TOTAL,a.COMMISSION_PAY_TOTAL,a.SCORE_PAY_TOTAL,a.GOLD_PAY_TOTAL,"
                + " a.PRESENT_AMOUNT ,a.BONUS_AMOUNT,a.RECHARGE_AMOUNT,a.COMMISSION,a.SCORE,a.GOLD,"
                + " a.BONUS_AMOUNT_FREEZE,a.RECHARGE_AMOUNT_FREEZE,a.PRESENT_AMOUNT_FREEZE,a.COMMISSION_FREEZE,a.SCORE_FREEZE,a.GOLD_FREEZE,"
                + " u.CREATE_TIME CREATETIME,(TRUNC(sysdate)-TRUNC(l.CREATE_TIME)) leaveDay, "
                + " l.CREATE_TIME LOGINTIME,c.NAME sname,u.SOFT_VER "
                + " from USER_MEMBER u left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE "
                + " left join ACCOUNT a on u.USER_CODE=a.USER_CODE "
                + " left join USER_COOPERATION c on u.SID=c.SID "
                + " where 1=1";
        List<Object> values = new ArrayList<Object>();
        if (customer != null) {// 添加查询条件
        	//用户类型
        	if(CommonUtil.isNotEmpty(customer.getMemberType())){
        		 sql += " and u.MEMBER_TYPE = ? ";
                 values.add(customer.getMemberType());
        	}
            //用户ID
            if (customer.getUserCode() != null && !customer.getUserCode().trim().equals("")) {
                if (flag) {
                    sql += " and u.USER_CODE like '%%" + customer.getUserCode().trim() + "%%' ";

                } else {

                    sql += " and u.USER_CODE = ? ";
                    values.add(customer.getUserCode().trim());
                }
            }
            //用户名
            if (customer.getUserName() != null && !customer.getUserName().trim().equals("")) {
                if (flag) {
                    sql += " and u.USER_NAME like '%%" + customer.getUserName().trim() + "%%' ";
                } else {

                    sql += " and u.USER_NAME = ?";
                    values.add(customer.getUserName().trim());
                }
            }
            //手机
            if (customer.getMobile() != null && !customer.getMobile().trim().equals("")) {
                if (flag) {
                    sql += " and u.MOBILE like '%%" + customer.getMobile().trim() + "%%' ";
                } else {

                    sql += " and u.MOBILE = ?";
                    values.add(customer.getMobile().trim());
                }
            }
            
           // 注册开始时间
 			if (CommonUtil.isNotEmpty(customer.getRegstart())) {
 				sql += " and  u.CREATE_TIME >= ?";
 				 Calendar cal = Calendar.getInstance();
                 cal.setTime(DateUtil.format(customer.getRegstart(), "yy-MM-dd"));
                 cal.set(Calendar.HOUR_OF_DAY, 0);
                 cal.set(Calendar.MINUTE, 0);
                 cal.set(Calendar.SECOND, 0); 
                 values.add(cal.getTime());
 				//values.add(DateUtil.format(customer.getRegstart(),"yy-MM-dd"));
 			}
 			if (CommonUtil.isNotEmpty(customer.getRegend())) {
 				sql += " and u.CREATE_TIME <= ? ";
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(customer.getRegend(),"yy-MM-dd"));

 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				//cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
 				values.add(cal.getTime());
 			}
 			
            if (CommonUtil.isNotEmpty(customer.getLoginstart())) {
 				sql += " and  l.CREATE_TIME >= ?";
 				Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(customer.getLoginstart(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); 
                values.add(cal.getTime());
 				//values.add(DateUtil.format(customer.getLoginstart(),"yy-MM-dd"));
 			}
 			if (CommonUtil.isNotEmpty(customer.getLoginend())) {
 				sql += " and l.CREATE_TIME <= ? ";
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(customer.getLoginend(),"yy-MM-dd"));

 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				//cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
 				values.add(cal.getTime());
 			}
            //姓名
            if (customer.getRealName() != null && !customer.getRealName().trim().equals("")) {
                if (flag) {
                    sql += " and u.REAL_NAME like '%%" + customer.getRealName().trim() + "%%' ";
                } else {

                    sql += " and u.REAL_NAME = ?";
                    values.add(customer.getRealName().trim());
                }
            }
            //身份证
            if (customer.getCardCode() != null && !customer.getCardCode().trim().equals("")) {
                if (flag) {
                    sql += " and u.CARD_CODE like '%%" + customer.getCardCode().trim() + "%%' ";
                } else {

                    sql += " and u.CARD_CODE = ?";
                    values.add(customer.getCardCode().trim());
                }
            }

            //渠道
            if (customer.getSid() != null && !customer.getSid().trim().equals("")) {
                sql += " and u.SID = ?";
                values.add(customer.getSid().trim());
            }
            //所属于
            if (customer.getTrackUserCode() != null && !customer.getTrackUserCode().trim().equals("")) {
                if (flag) {
                    sql += " and u.TRACK_USER_CODE like '%%" + customer.getTrackUserCode().trim() + "%%' ";
                } else {

                    sql += " and u.TRACK_USER_CODE = ?";
                    values.add(customer.getTrackUserCode().trim());
                }
            }
            //消费类型与余额都有值的情况下有效
            if(CommonUtil.isNotEmpty(customer.getConsumeType())&&(CommonUtil.isNotEmpty(customer.getBalanceMin())||CommonUtil.isNotEmpty(customer.getBalanceMax()))){
            	String consumeType=customer.getConsumeType();
            	//充值金
            	if(consumeType.equals("0")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.RECHARGE_AMOUNT,0)+nvl(a.RECHARGE_AMOUNT_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.RECHARGE_AMOUNT,0)+nvl(a.RECHARGE_AMOUNT_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            	//奖金
            	if(consumeType.equals("1")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.BONUS_AMOUNT,0)+nvl(a.BONUS_AMOUNT_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.BONUS_AMOUNT,0)+nvl(a.BONUS_AMOUNT_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            	
            	//红包
            	if(consumeType.equals("2")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.PRESENT_AMOUNT,0)+nvl(a.PRESENT_AMOUNT_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.PRESENT_AMOUNT,0)+nvl(a.PRESENT_AMOUNT_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            	//积分
            	if(consumeType.equals("3")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.SCORE,0)+nvl(a.SCORE_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.SCORE,0)+nvl(a.SCORE_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            	//金币
            	if(consumeType.equals("4")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.GOLD,0)+nvl(a.GOLD_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.GOLD,0)+nvl(a.GOLD_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            	//佣金
            	if(consumeType.equals("5")){
            		if(CommonUtil.isNotEmpty(customer.getBalanceMin())){
            			sql+=" and (nvl(a.COMMISSION,0)+nvl(a.COMMISSION_FREEZE,0)) >= ? ";
            			values.add(customer.getBalanceMin());
            		}
            		if(CommonUtil.isNotEmpty(customer.getBalanceMax())){
            			sql+=" and (nvl(a.COMMISSION,0)+nvl(a.COMMISSION_FREEZE,0)) <= ? ";
            			values.add(customer.getBalanceMax());
            		}
            	}
            }
            //离开天数最小
            if (customer.getLeaveMin() != null) {

                sql += " and  TRUNC(sysdate)-TRUNC(l.CREATE_TIME) >= " + customer.getLeaveMin().intValue();
            }
            //离开天数最大
            if (customer.getLeaveMax() != null) {
                sql += " and TRUNC(sysdate)-TRUNC(l.CREATE_TIME) <= " + customer.getLeaveMax().intValue();
            }

            //注册方式
            if (customer.getRegisterFrom() != null) {

                sql += " and u.REGISTER_FROM = ?";
                values.add(customer.getRegisterFrom());
            }
            //账号状态
            if (customer.getStatus() != null) {
                sql += " and u.STATUS = ?";
                values.add(customer.getStatus());
            }
            //软件版本
            if (customer.getSysVer() != null && !customer.getSysVer().trim().equals("")) {
                sql += " and u.SYS_VER = ?";
                values.add(customer.getSysVer());
            }
            //登录串号
            if (customer.getLoginMachId() != null && !customer.getLoginMachId().trim().equals("")) {
                if (flag) {
                    sql += " and l.MACH_ID like '%%" + customer.getLoginMachId().trim() + "%%' ";
                } else {

                    sql += " and l.MACH_ID = ?";
                    values.add(customer.getLoginMachId().trim());
                }
            }
            //注册串号
            if (customer.getMachId() != null && !customer.getMachId().trim().equals("")) {
                if (flag) {
                    sql += " and u.MACH_ID like '%%" + customer.getMachId().trim() + "%%' ";
                } else {

                    sql += " and u.MACH_ID = ?";
                    values.add(customer.getMachId().trim());
                }
            }
            //开发平台ID
            if (customer.getUnUserID() != null && !customer.getUnUserID().trim().equals("")) {
                if (flag) {
                    sql += " and u.UN_USER_ID like '%%" + customer.getUnUserID().trim() + "%%' ";
                } else {

                    sql += " and u.UN_USER_ID = ?";
                    values.add(customer.getUnUserID().trim());
                }
            }
            //开放平台
            if (customer.getUnionApp() != null) {
                sql += " and u.UNION_APP = ?";
                values.add(customer.getUnionApp());
            }
        }
        String totalHql = " select count(*)  from (" + sql + ")";
        j.setTotal(memberDao.countBySql(totalHql, values).longValue());// 设置总记录数

        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("grade")) {
                sort = "u.GRADE";
            }
            if (dg.getSort().toString().equalsIgnoreCase("betTotal")) {
            	sort="(nvl(a.RECHARGE_PAY_TOTAL,0)+nvl(a.BONUS_PAY_TOTAL,0)+nvl(a.PRESENT_PAY_TOTAL,0)+nvl(a.COMMISSION_PAY_TOTAL,0)+nvl(a.SCORE_PAY_TOTAL,0)+nvl(a.GOLD_PAY_TOTAL,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("cashTotal")) {
            	sort="(nvl(a.BONUS_AMOUNT,0)+nvl(a.BONUS_AMOUNT_FREEZE,0)+nvl(a.RECHARGE_AMOUNT,0)+nvl(a.RECHARGE_AMOUNT_FREEZE,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("rechargeAmount")) {
                sort = " (nvl(a.RECHARGE_AMOUNT,0)+nvl(a.RECHARGE_AMOUNT_FREEZE,0))";
            }
            
            if (dg.getSort().toString().equalsIgnoreCase("bonusAmount")) {
                sort = "(nvl(a.BONUS_AMOUNT,0)+nvl(a.BONUS_AMOUNT_FREEZE,0))";
            }
            
            if (dg.getSort().toString().equalsIgnoreCase("score")) {
                sort = "(nvl(a.SCORE,0)+nvl(a.SCORE_FREEZE,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("gold")) {
                sort = "(nvl(a.GOLD,0)+nvl(a.GOLD_FREEZE,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("commission")) {
                sort = "(nvl(a.COMMISSION,0)+nvl(a.COMMISSION_FREEZE,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("presentAmount")) {
                sort = "(nvl(a.PRESENT_AMOUNT,0)+nvl(a.PRESENT_AMOUNT_FREEZE,0))";
            }
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = " u.CREATE_TIME";
            }
            if (dg.getSort().toString().equalsIgnoreCase("loginTime")) {
                sort = " l.CREATE_TIME";
            }
            if (!sort.equals("")) {
                sql += " order by " + sort + " " + dg.getOrder();
            }
        }
        List<Map> customers = mapDao.findBySql(sql, values, dg.getPage(), dg.getRows());// 查询分页
        List<Customer> users = new ArrayList<Customer>();
        if (customers != null && customers.size() > 0) {// 转换模型
            for (Map map : customers) {
            	
                Customer u = new Customer();
                
                u.setUserCode((String) map.get("USER_CODE"));
                
                BigDecimal leaveDay =  map.get("LEAVEDAY")==null?new BigDecimal(0):(BigDecimal)map.get("LEAVEDAY");
                u.setUserName((String) map.get("USER_NAME") + "(" + leaveDay.intValue() + ")");
                
                BigDecimal grade =  map.get("GRADE")==null?new BigDecimal(0):(BigDecimal)map.get("GRADE");
                u.setGrade(grade.intValue());
                
                u.setRealName((String) map.get("REAL_NAME"));
                
                u.setMobile((String) map.get("MOBILE"));
                
                u.setCardCode((String)map.get("CARD_CODE"));
                
                BigDecimal presentAmount =  map.get("PRESENT_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_AMOUNT");
                BigDecimal presentAmountFreeze =  map.get("PRESENT_AMOUNT_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_AMOUNT_FREEZE");
                BigDecimal presentPayTotal =  map.get("PRESENT_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_PAY_TOTAL");
                u.setPresentAmount(presentAmount.add(presentAmountFreeze).doubleValue());
                
                BigDecimal bonusAmount =  map.get("BONUS_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT");
                BigDecimal bonusAmountFreeze =  map.get("BONUS_AMOUNT_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT_FREEZE");
                BigDecimal bonusPayTotal =  map.get("BONUS_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_PAY_TOTAL");
                u.setBonusAmount(bonusAmount.add(bonusAmountFreeze).doubleValue());
                
                BigDecimal rechargeAmount =  map.get("RECHARGE_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_AMOUNT");
                BigDecimal rechargeAmountFreeze =  map.get("RECHARGE_AMOUNT_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_AMOUNT_FREEZE");
                BigDecimal rechargePayTotal =  map.get("RECHARGE_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_PAY_TOTAL");
                u.setRechargeAmount(rechargeAmount.add(rechargeAmountFreeze).doubleValue());
                
                BigDecimal commission =  map.get("COMMISSION")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION");
                BigDecimal commissionFreeze =  map.get("COMMISSION_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION_FREEZE");
                BigDecimal commissionPayTotal =  map.get("COMMISSION_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION_PAY_TOTAL");
                u.setCommission(commission.add(commissionFreeze).doubleValue());
                
                BigDecimal score =  map.get("SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE");
                BigDecimal scoreFreeze =  map.get("SCORE_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE_FREEZE");
                BigDecimal scorePayTotal =  map.get("SCORE_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE_PAY_TOTAL");
                u.setScore(score.add(scoreFreeze).doubleValue());
                
                BigDecimal gold =  map.get("GOLD")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD");
                BigDecimal goldFreeze =  map.get("GOLD_FREEZE")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD_FREEZE");
                BigDecimal goldPayTotal =  map.get("GOLD_PAY_TOTAL")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD_PAY_TOTAL");
                u.setGold(gold.add(goldFreeze).doubleValue());
                
                BigDecimal status =  map.get("STATUS")==null?new BigDecimal(0):(BigDecimal)map.get("STATUS");
                u.setStatus(status.intValue());
                
                Date createTime = (Date) map.get("CREATETIME");
                u.setCreateTime(DateUtil.format(createTime));
                
                Date loginTime = (Date) map.get("LOGINTIME");
                u.setLoginTime(DateUtil.format(loginTime));
                
                u.setSid((String) map.get("SNAME"));
                u.setSoftVer((String) map.get("SOFTVER"));
                u.setBetTotal(presentPayTotal.add(goldPayTotal).add(scorePayTotal).add(commissionPayTotal).add(rechargePayTotal).add(bonusPayTotal).doubleValue());
                u.setCashTotal(rechargeAmount.add(rechargeAmountFreeze).add(bonusAmount).add(bonusAmountFreeze).doubleValue());
                users.add(u);
            }
        }
        j.setRows(users);// 设置返回的行
        return j;
    }
    
    /**
	 * 用户登录日志
	 * @param dg
	 * @param userCode
	 * @return
	 */
	public EasyuiDataGridJson memberLoginList(EasyuiDataGrid dg, String userCode){
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuffer str=new StringBuffer(" select l.USER_CODE,l.MACH_ID ,L.MACH_NAME,l.CREATE_TIME, ");
		str.append(" l.LOGIN_FROM,l.PLATFORM,l.SOFT_VER,l.SYS_VER,l.ip,c.NAME from USER_MEMBER_LOGIN_HISTORY l ");
		str.append(" left join USER_COOPERATION c on l.SID=c.SID where l.USER_CODE=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(userCode);
		String totalHql = " select count(*)  from (" + str.toString() + ")";
	    j.setTotal(memberDao.countBySql(totalHql, values).longValue());// 设置总记录数

        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = "l.CREATE_TIME ";
            }
            
            if (!sort.equals("")) {
                str.append(" order by ").append( sort ).append( " ").append( dg.getOrder());
            }
        }
        List<Map> memberMapList = mapDao.findBySql(str.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<MemberLoginVO> memberLogs = new ArrayList<MemberLoginVO>();
        if (memberMapList != null && memberMapList.size() > 0) {// 转换模型
            for (Map map : memberMapList) {
            	MemberLoginVO u=new MemberLoginVO();
            	u.setUserCode((String)map.get("USER_CODE"));
            	u.setMachId((String)map.get("MACH_ID"));
            	u.setMachName((String)map.get("MACH_NAME"));
            	Date createTime = (Date) map.get("CREATE_TIME");
                u.setCreateTime(DateUtil.format(createTime));
                BigDecimal loginFrom=(BigDecimal) map.get("LOGIN_FROM");
                u.setLoginFrom(loginFrom.intValue());
                u.setPlatform((String)map.get("PLATFORM"));
                u.setSoftVer((String)map.get("SOFT_VER"));
                u.setSysVer((String)map.get("SYS_VER"));
                u.setIp((String)map.get("IP"));
                u.setSid((String)map.get("NAME"));
                memberLogs.add(u);
            }
        }
        j.setRows(memberLogs);// 设置返回的行
        return j;
	}
    /**
	 * 资金内部存取时用户查询
	 * @param userCode
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public List<Customer> customerInfo(String userCode,String userName,String mobile){

        String sql = "select u.USER_NAME,u.USER_CODE,u.REAL_NAME, u.MOBILE,u.CARD_CODE,u.MEMBER_TYPE,"
                + " a.PRESENT_AMOUNT,a.BONUS_AMOUNT,a.RECHARGE_AMOUNT,a.COMMISSION,a.SCORE,a.GOLD,u.STATUS,"
                + " u.CREATE_TIME "
                + " from USER_MEMBER u "
                + " left join ACCOUNT a on u.USER_CODE=a.USER_CODE "
                + " where 1=1";
           List<Object> values = new ArrayList<Object>();
            //用户ID
            if (CommonUtil.isNotEmpty(userCode)) {
                    sql += " and u.USER_CODE = ? ";
                    values.add(userCode.trim());
                }
            //用户名
            if (CommonUtil.isNotEmpty(userName)) {
                    sql += " and u.USER_NAME = ?";
                    values.add(userName.trim());
                }
            //手机
            if (CommonUtil.isNotEmpty(mobile)) {
                    sql += " and u.MOBILE = ?";
                    values.add(mobile.trim());
            }
        List<Map> customers = mapDao.findBySql(sql, values);// 查询分页
        List<Customer> users = new ArrayList<Customer>();
        if (customers != null && customers.size() > 0) {// 转换模型
            for (Map map : customers) {
                Customer u = new Customer();
                u.setUserCode((String) map.get("USER_CODE"));
                
                u.setUserName((String) map.get("USER_NAME"));
                
                u.setRealName((String) map.get("REAL_NAME"));
                
                u.setMobile((String) map.get("MOBILE"));
                
                u.setCardCode((String)map.get("CARD_CODE"));
                
                BigDecimal presentTotal =  map.get("PRESENT_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_AMOUNT");
                u.setPresentAmount(presentTotal.doubleValue());
                
                BigDecimal bonusAmount =  map.get("BONUS_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT");
                u.setBonusAmount(bonusAmount.doubleValue());
                
                BigDecimal rechargeAmount =  map.get("RECHARGE_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_AMOUNT");
                u.setRechargeAmount(rechargeAmount.doubleValue());
                
                BigDecimal commission =  map.get("COMMISSION")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION");
                u.setCommission(commission.doubleValue());
                
                BigDecimal score =  map.get("SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE");
                u.setScore(score.doubleValue());
                
                BigDecimal gold =  map.get("GOLD")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD");
                u.setGold(gold.doubleValue());
                
                BigDecimal status =  map.get("STATUS")==null?new BigDecimal(0):(BigDecimal)map.get("STATUS");
                u.setStatus(status.intValue());
                
                BigDecimal memberType =  map.get("MEMBER_TYPE")==null?new BigDecimal(0):(BigDecimal)map.get("MEMBER_TYPE");
                u.setMemberType(memberType.intValue());
                
                Date createTime = (Date) map.get("CREATE_TIME");
                
                u.setCreateTime(DateUtil.format(createTime));
                users.add(u);
            }
        }
        return users;
    
	}

    @Override
    public Map getCustomerInfo(String userCode) {
        String sql = " select t.USER_NAME userName,t.USER_CODE userCode,t.GRADE grade,t.STATUS status,t.VIP vip,"
                + " t.CREATE_TIME createTime,t.REGISTER_FROM registerFrom ,c.NAME sname,"
                + " t.PLATFORM platform,t.SOFT_VER softVer,t.SYS_VER sysVer,t.IP ip,t.TRACK_USER_CODE trackUserCode,"
                + " l.CREATE_TIME loginTime,l.LOGIN_FROM loginFrom,l.PLATFORM loginPlatform,l.SOFT_VER loginSoftVer,l.SYS_VER loginSysVer,l.ip loginIp, "
                + " c1.name lname,t.REAL_NAME realName, t.MOBILE mobile,t.CARD_CODE cardCode, "
                + " t.SEX sex,t.LUNAR_BIRTHDAY lunarBirthday,t.SOLAR_BIRTHDAY solarBirthday,t.CONSTELLATION constellation,"
                + " t.QQ qq,t.WE_CHAT weChat,t.EMAIL email,t.SINA_BLOG sinaBlog, "
                + " t.MACH_ID machId,t.MACH_NAME machName,l.MACH_ID loginMachId,l.MACH_NAME loginMachName "
                + " from USER_MEMBER t left join  USER_MEMBER_LOGIN l on  t.USER_CODE=l.USER_CODE "
                + " left join USER_COOPERATION c on t.SID=c.SID "
                + " left join USER_COOPERATION c1 on l.SID=c1.SID "
                + " where t.USER_CODE='" + userCode + "'";
        List<Map> customers = mapDao.findBySql(sql);
        Map map = new HashMap();
        if (customers != null && customers.size() > 0) {
            map = customers.get(0);
        }
        return map;
    }

    @Override
    public Map getCustomerInfoByMobile(String mobile) {
        String sql = " select t.USER_NAME userName,t.USER_CODE userCode,t.GRADE grade,t.STATUS status,t.VIP vip,"
                + " to_char(t.CREATE_TIME,'yyyy-mm-dd') createTime,t.REGISTER_FROM registerFrom ,c.NAME sname,"
                + " t.PLATFORM platform,t.SOFT_VER softVer,t.SYS_VER sysVer,t.IP ip,t.TRACK_USER_CODE trackUserCode,"
                + " to_char(l.CREATE_TIME,'yyyy-mm-dd') loginTime,l.LOGIN_FROM loginFrom,l.PLATFORM loginPlatform,l.SOFT_VER loginSoftVer,l.SYS_VER loginSysVer,l.ip loginIp, "
                + " t.REAL_NAME realName, t.MOBILE mobile,t.CARD_CODE cardCode, "
                + " t.SEX sex,t.LUNAR_BIRTHDAY lunarBirthday,t.SOLAR_BIRTHDAY solarBirthday,t.CONSTELLATION constellation,"
                + " t.QQ qq,t.WE_CHAT weChat,t.EMAIL email,t.SINA_BLOG sinaBlog, "
                + " t.MACH_ID machId,t.MACH_NAME machName,l.MACH_ID loginMachId,l.MACH_NAME loginMachName "
                + " from USER_MEMBER t left join  USER_MEMBER_LOGIN l on  t.USER_CODE=l.USER_CODE "
                + " left join USER_COOPERATION c on t.SID=c.SID "
                + " where t.MOBILE='" + mobile + "'";
        List<Map> customers = mapDao.findBySql(sql);
        Map map = new HashMap();
        if (customers != null && customers.size() > 0) {
            map = customers.get(0);
        }
        return map;
    }
    /**
     * 更改用户状态（解锁、锁定）
     * @param userCode
     * @param status
     */
    public int updateMemberStatus(String userCode,Integer status){
    	StringBuilder str=new StringBuilder("update USER_MEMBER set STATUS= ? where USER_CODE=?");
    	List<Object> values = new ArrayList<Object>();
    	//状态
    	if (CommonUtil.isNotEmpty(status)) {
    		values.add(status);
    	}
        //用户ID
        if (CommonUtil.isNotEmpty(userCode)) {
                values.add(userCode.trim());
            }
        return memberDao.executeSql(str.toString(), values);
    }

    @Override
    public BindChargeBank getRechargeBank(String userCode) {
        String hql = " from BindChargeBank where userCode='" + userCode + "'";
        BindChargeBank bank = rechargeDao.get(hql);
        return bank;
    }

    @Override
    public BindFillBank getFillBank(String userCode) {
        String hql = " from BindFillBank where userCode='" + userCode + "'";
        BindFillBank bank = fillbankDao.get(hql);
        return bank;
    }
    
    /**
	 * 获取用户绑定提现银行信息
	 * @param userCode
	 * @return
	 */
	public DrawBindBank getDrawBindBank(String userCode){
		String hql="from DrawBindBank where userCode=?";
		List<Object> values = new ArrayList<Object>();
		values.add(userCode);
		return drawBankDao.get(hql, values);
		
	}


    @Override
    public List<Map> getBanks(String type) {
    	List<Map> list =new ArrayList<Map>();
       if(CommonUtil.isNotEmpty(type)){
    	   List<Object> values = new ArrayList<Object>();
    	   StringBuffer str=new StringBuffer("select a.CODE,a.NAME from ACCOUNT_DICT_DETAIL a inner join ACCOUNT_DICT_TYPE t on a.TYPE_ID=t.ID ");
    	   str.append(" where t.NAME like ? ");
		   values.add("%" + type + "%");
		   list= mapDao.findBySql(str.toString(), values);
       }
        return list;
    }
    /**
	 * 用户基本信息保存
	 * @return
	 */
	public boolean updateMemberBasic(MemberBean member){
		StringBuffer str=new StringBuffer("update USER_MEMBER set REAL_NAME=?,CARD_CODE=?,SEX=?,QQ=?,WE_CHAT=?,EMAIL=?,SINA_BLOG=? where USER_CODE=?");
		 List<Object> values = new ArrayList<Object>();
		 values.add(member.getRealName());
		 values.add(member.getCardCode());
		 values.add(member.getSex());
		 values.add(member.getQq());
		 values.add(member.getWeChat());
		 values.add(member.getEmail());
		 values.add(member.getSinaBlog());
		 values.add(member.getUserCode());
		 int result = memberDao.executeSql(str.toString(), values);
		 if (result > 0) {
             return true;
         } else {
             return false;
         }
	}

    @Override
    public boolean updateChargeBank(BindChargeBank chargebank) {
        BindChargeBank bank = getRechargeBank(chargebank.getUserCode());
        if (bank == null) {
            rechargeDao.save(chargebank);
            return true;
        } else {
            String sql = "update ACCOUNT_BIND_CHARGE_BANK set BANK_CODE= '" + chargebank.getBankCode() + "',PROVINCE='" + chargebank.getProvince() + "',CITY='" + chargebank.getCity() + "',SUB_BANK='" + chargebank.getSubBank() + "',BANK_NAME='" + chargebank.getBankName() + "'"
                    + " where USER_CODE='" + chargebank.getUserCode() + "'";
            int result = rechargeDao.executeSql(sql);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 修改用户提现银行信息
     *
     * @param fillbank
     * @return
     */
    public boolean updateFillBank(BindFillBank fillbank) {
        BindFillBank bank = getFillBank(fillbank.getUserCode());
        if (bank == null) {
            fillbankDao.save(fillbank);
            return true;
        } else {
            String sql = "update ACCOUNT_BIND_FILL_BANK set BANK_CODE= '" + fillbank.getBankCode() + "',PROVINCE='" + fillbank.getProvince() + "',CITY='" + fillbank.getCity() + "',SUB_BANK='" + fillbank.getSubBank() + "',BANK_NAME='" + fillbank.getBankName() + "'"
                    + " where USER_CODE='" + fillbank.getUserCode() + "'";
            int result = fillbankDao.executeSql(sql);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
	 * 修改用户提现银行信息
	 * @param drawbank
	 * @return
	 */
	public boolean updateDrawBindBank(DrawBindBank drawbank){
		DrawBindBank bank = getDrawBindBank(drawbank.getUserCode());
        if (bank == null) {
        	drawBankDao.save(drawbank);
            return true;
        } else {
            String sql = "update ACCOUNT_DRAW_BIND_BANK set BANK_CODE= ?,PROVINCE=?,CITY=?,SUB_BANK=?,BANK_NAME=?  where USER_CODE=? ";
            List<Object> values = new ArrayList<Object>();
            values.add(drawbank.getBankCode());
            values.add(drawbank.getProvince());
            values.add(drawbank.getCity());
            values.add(drawbank.getSubBank());
            values.add(drawbank.getBankName());
            values.add(drawbank.getUserCode());
            int result = drawBankDao.executeSql(sql,values);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
	}
    /**
	 * 总账新增
	 * @param vo
	 */
	public void accountSave(Customer vo){
		StringBuilder str=new StringBuilder("insert into USER_MEMBER(ID,USER_NAME,USER_CODE,STATUS,TRACK_USER_CODE,SID,MEMBER_TYPE,CREATE_TIME)");
		str.append(" values(SEQ_USER_MEMBER.NEXTVAL,?,?,0,?,?,?,sysdate)");
		List<Object> values = new ArrayList<Object>();
		values.add(vo.getUserName());
		values.add(vo.getUserCode());		
		values.add(vo.getTrackUserCode());
		values.add(vo.getSid());
		values.add(vo.getMemberType());
		memberDao.executeSql(str.toString(), values);
	}
	
	 /**
     * 系统账户
     * @param accountName
     * @return
     */
    public List<Customer> sysAccount(String accountName){
    	StringBuilder sb = new StringBuilder("select u.USER_NAME,u.USER_CODE,");
    	sb.append(" a.PRESENT_AMOUNT,a.BONUS_AMOUNT,a.RECHARGE_AMOUNT,a.COMMISSION,a.SCORE,a.GOLD,u.STATUS,u.CREATE_TIME");
    	sb.append(" from USER_MEMBER u left join ACCOUNT a on u.USER_CODE=a.USER_CODE where u.MEMBER_TYPE=1 ");
    	List<Object> values = new ArrayList<Object>();
		if (CommonUtil.isNotEmpty(accountName.trim())) {
			sb.append(" and u.USER_NAME like ? ");
			values.add("%" + accountName.trim() + "%");
		}
		List<Map> mapList = memberDao.findBySql(sb.toString(), values);
		List<Customer> users = new ArrayList<Customer>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
	            for (Map map : mapList) {
	            	
	                Customer u = new Customer();
	                
	                u.setUserCode((String) map.get("USER_CODE"));
	                
	                u.setUserName((String) map.get("USER_NAME"));
	                
	                BigDecimal presentTotal =  map.get("PRESENT_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("PRESENT_AMOUNT");
	                u.setPresentAmount(presentTotal.doubleValue());
	                
	                BigDecimal bonusAmount =  map.get("BONUS_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("BONUS_AMOUNT");
	                u.setBonusAmount(bonusAmount.doubleValue());
	                
	                BigDecimal rechargeAmount =  map.get("RECHARGE_AMOUNT")==null?new BigDecimal(0):(BigDecimal)map.get("RECHARGE_AMOUNT");
	                u.setRechargeAmount(rechargeAmount.doubleValue());
	                
	                BigDecimal commission =  map.get("COMMISSION")==null?new BigDecimal(0):(BigDecimal)map.get("COMMISSION");
	                u.setCommission(commission.doubleValue());
	                
	                BigDecimal score =  map.get("SCORE")==null?new BigDecimal(0):(BigDecimal)map.get("SCORE");
	                u.setScore(score.doubleValue());
	                
	                BigDecimal gold =  map.get("GOLD")==null?new BigDecimal(0):(BigDecimal)map.get("GOLD");
	                u.setGold(gold.doubleValue());
	                
	                BigDecimal status =  map.get("STATUS")==null?new BigDecimal(0):(BigDecimal)map.get("STATUS");
	                u.setStatus(status.intValue());
	                
	                Date createTime = (Date) map.get("CREATE_TIME");
	                u.setCreateTime(DateUtil.format(createTime));
	                users.add(u);
	            }
	        }
		return users;
    }

    /**
	 * 积分商城
	 * 根据user_code查询用户名称
	 */
	@Override
	public List<Map> getMemberNameByCode(String userCode) {
		List<Map> list = new ArrayList<Map>();
		StringBuffer stringBuff = null;
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.isNotEmpty(userCode)){
	    	   stringBuff = new StringBuffer("select USER_CODE,USER_NAME from USER_MEMBER");
	    	   stringBuff.append(" where USER_CODE like ? ");
	    	   param.add("%" + userCode + "%");
	    }else {
	    	stringBuff = new StringBuffer("select USER_CODE,USER_NAME from USER_MEMBER");
	    }
		list= mapDao.findBySql(stringBuff.toString(), param);
	    return list;
	}
	/**
	 * 积分商城
	 * 根据user_name查询用户code
	 */
	@Override
	public List<Map> getMemberCodeByName(String userName, int isVague) {
		List<Map> list = new ArrayList<Map>();
		StringBuffer stringBuff = null;
		List<Object> param = new ArrayList<Object>();
		if (CommonUtil.isNotEmpty(userName)) {
			stringBuff = new StringBuffer("select USER_CODE,USER_NAME from USER_MEMBER");
			//是否模糊查询  0为否1为是
			if(1==isVague) {
				stringBuff.append(" where USER_NAME like ? ");
				param.add("%" + userName + "%");
			}else {
				stringBuff.append(" where USER_NAME = ? ");
				param.add(userName);
			}
		} else {
			stringBuff = new StringBuffer(
					"select USER_CODE, USER_NAME from USER_MEMBER");
		}
		list = mapDao.findBySql(stringBuff.toString(), param);
		return list;
	}
}
