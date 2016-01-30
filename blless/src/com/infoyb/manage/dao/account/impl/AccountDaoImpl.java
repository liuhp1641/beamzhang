package com.cm.manage.dao.account.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.account.http.IAccountHttpService;
import com.cm.account.http.bean.DepositOrTellerBean;
import com.cm.account.http.bean.TransferBean;
import com.cm.manage.dao.account.IAccountDao;
import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.member.IMemberDao;
import com.cm.manage.util.account.AccountOperate;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.account.AccountLogVO;
import com.cm.manage.vo.account.AccountVO;
import com.cm.manage.vo.account.FillVO;
import com.cm.manage.vo.account.InternalAccessVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.Customer;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private IBaseDao<Object> accountDao;

    @Autowired
    private IMemberDao memberDao;

    @Resource
    private IAccountHttpService accountInterface;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 资金汇总查询
     *
     * @param dg
     * @param account
     * @return
     */
    public EasyuiDataGridJson accountData(EasyuiDataGrid dg, AccountVO account) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder sb = new StringBuilder("select u.USER_CODE,u.USER_NAME ,");
        sb.append(" a.BONUS_AMOUNT,a.BONUS_TOTAL,a.BONUS_DRAW_TOTAL,a.BONUS_IN_TOTAL,a.BONUS_OUT_TOTAL,a.BONUS_PAY_TOTAL,");
        sb.append(" a.RECHARGE_AMOUNT,a.RECHARGE_TOTAL,a.RECHARGE_DRAW_TOTAL,a.RECHARGE_IN_TOTAL,a.RECHARGE_OUT_TOTAL,a.RECHARGE_PAY_TOTAL,");
        sb.append(" a.PRESENT_AMOUNT,a.PRESENT_TOTAL,a.PRESENT_DRAW_TOTAL,a.PRESENT_IN_TOTAL,a.PRESENT_OUT_TOTAL,a.PRESENT_PAY_TOTAL,");
        sb.append(" a.SCORE,a.SCORE_TOTAL,a.SCORE_DRAW_TOTAL,a.SCORE_IN_TOTAL,a.SCORE_OUT_TOTAL,a.SCORE_PAY_TOTAL,");
        sb.append(" a.GOLD,a.GOLD_TOTAL,a.GOLD_DRAW_TOTAL,a.GOLD_IN_TOTAL,a.GOLD_OUT_TOTAL,a.GOLD_PAY_TOTAL ");
        sb.append(" from ACCOUNT a left join USER_MEMBER u on u.USER_CODE=a.USER_CODE ");
        sb.append(" left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE where 1=1 ");

        List<Object> values = new ArrayList<Object>();
        if (account != null) {// 添加查询条件
            boolean flag = account.isFlag();
            // 用户编号
            if (CommonUtil.isNotEmpty(account.getUserCode())) {
                if (flag) {
                    sb.append(" and u.user_code like ? ");
                    values.add("%" + account.getUserCode().trim() + "%");
                } else {
                    sb.append(" and u.user_code = ? ");
                    values.add(account.getUserCode().trim());
                }
            }

            // 用户名称
            if (CommonUtil.isNotEmpty(account.getUserName())) {
                if (flag) {
                    sb.append(" and u.user_name like ? ");
                    values.add("%" + account.getUserName().trim() + "%");
                } else {
                    sb.append(" and u.user_name = ? ");
                    values.add(account.getUserName().trim());
                }
            }
            //姓名
            if (CommonUtil.isNotEmpty(account.getRealName())) {
                if (flag) {
                    sb.append(" and u.REAL_NAME like ? ");
                    values.add("%" + account.getRealName().trim() + "%");
                } else {
                    sb.append(" and u.REAL_NAME = ? ");
                    values.add(account.getRealName().trim());
                }
            }

            //身份证
            if (CommonUtil.isNotEmpty(account.getCardCode())) {
                if (flag) {
                    sb.append(" and u.CARD_CODE like ? ");
                    values.add("%" + account.getCardCode().trim() + "%");
                } else {

                    sb.append(" and u.CARD_CODE = ? ");
                    values.add(account.getCardCode().trim());
                }
            }
            //渠道
            if (CommonUtil.isNotEmpty(account.getSid())) {
                sb.append(" and u.SID = ? ");
                values.add(account.getSid());
            }

            //所属于
            if (CommonUtil.isNotEmpty(account.getTrackUserCode())) {
                if (flag) {
                    sb.append(" and u.TRACK_USER_CODE like ? ");
                    values.add("%" + account.getTrackUserCode() + "%");
                } else {
                    sb.append(" and u.TRACK_USER_CODE = ? ");
                    values.add(account.getTrackUserCode());
                }
            }
            //注册方式
            if (CommonUtil.isNotEmpty(account.getRegisterFrom())) {

                sb.append(" and u.REGISTER_FROM = ? ");
                values.add(account.getRegisterFrom());
            }
            //设备串号
            if (CommonUtil.isNotEmpty(account.getEqu())) {

                if (CommonUtil.isNotEmpty(account.getMachId())) {
                    //登录串号
                    if (account.getEqu() == 0) {
                        if (flag) {
                            sb.append(" and l.MACH_ID like like ? ");
                            values.add("%" + account.getMachId() + "%");
                        } else {
                            sb.append(" and l.MACH_ID = ? ");
                            values.add(account.getMachId());
                        }

                    } else if (account.getEqu() == 1) {
                        //注册串号
                        if (flag) {
                            sb.append(" and u.MACH_ID like like ? ");
                            values.add("%" + account.getMachId() + "%");
                        } else {
                            sb.append(" and u.MACH_ID = ? ");
                            values.add(account.getMachId());
                        }

                    }
                }
            }
            //注册开始时间
            if (CommonUtil.isNotEmpty(account.getRegstart())) {
                sb.append(" and u.CREATE_TIME >= ?");
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(account.getRegstart(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
                values.add(cal.getTime());
            }
            //注册结束时间
            if (CommonUtil.isNotEmpty(account.getRegend())) {
                sb.append(" and u.CREATE_TIME <= ?");
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(account.getRegend(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
                //cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
                values.add(cal.getTime());
            }

            //登录开始时间
            if (CommonUtil.isNotEmpty(account.getLoginstart())) {
                sb.append(" and l.CREATE_TIME >= ?");
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(account.getLoginstart(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); // 设置时分秒都为0
                values.add(cal.getTime());
                //values.add(DateUtil.format(account.getLoginstart(), "yy-MM-dd"));
            }
            //登录结束时间
            if (CommonUtil.isNotEmpty(account.getLoginend())) {
                sb.append(" and l.CREATE_TIME <= ?");
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(account.getLoginend(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
                //cal.add(Calendar.DAY_OF_YEAR, 1); // 小时加1
                values.add(cal.getTime());
            }


            //离开天数最小
            if (CommonUtil.isNotEmpty(account.getLeaveMin())) {

                sb.append(" and TRUNC(sysdate)-TRUNC(l.CREATE_TIME) >= ?");
                values.add(account.getLeaveMin().intValue());
            }
            //离开天数最大
            if (CommonUtil.isNotEmpty(account.getLeaveMax())) {
                sb.append(" and TRUNC(sysdate)-TRUNC(l.CREATE_TIME) < ?");
                values.add(account.getLeaveMax().intValue());
            }

            //账号状态
            if (CommonUtil.isNotEmpty(account.getStatus())) {
                sb.append(" and u.STATUS = ? ");
                values.add(account.getStatus());
            }
            //资金类型
            if (CommonUtil.isNotEmpty(account.getMoneyType())) {
                Integer moneyType = account.getMoneyType();
                if (CommonUtil.isNotEmpty(account.getSumType())) {
                    Integer sumType = account.getSumType();
                    if (CommonUtil.isNotEmpty(account.getAmountMin())) {
                        //充值金
                        if (moneyType == 0) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.RECHARGE_TOTAL >= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.RECHARGE_DRAW_TOTAL >= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.RECHARGE_IN_TOTAL >= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.RECHARGE_OUT_TOTAL >= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.RECHARGE_PAY_TOTAL >= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.RECHARGE_AMOUNT >= ?");
                            }
                        }
                        //奖金
                        if (moneyType == 1) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.BONUS_TOTAL >= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.BONUS_DRAW_TOTAL >= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.BONUS_IN_TOTAL >= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.BONUS_OUT_TOTAL >= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.BONUS_PAY_TOTAL >= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.BONUS_AMOUNT >= ?");
                            }
                        }
                        //红包
                        if (moneyType == 2) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.PRESENT_TOTAL >= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.PRESENT_DRAW_TOTAL >= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.PRESENT_IN_TOTAL >= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.PRESENT_OUT_TOTAL >= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.PRESENT_PAY_TOTAL >= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.PRESENT_AMOUNT >= ?");
                            }
                        }
                        //积分
                        if (moneyType == 3) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.SCORE_TOTAL >= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.SCORE_DRAW_TOTAL >= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.SCORE_IN_TOTAL >= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.SCORE_OUT_TOTAL >= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.SCORE_PAY_TOTAL >= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.SCORE >= ?");
                            }
                        }
                        //金币
                        if (moneyType == 4) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.GOLD_TOTAL >= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.GOLD_DRAW_TOTAL >= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.GOLD_IN_TOTAL >= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.GOLD_OUT_TOTAL >= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.GOLD_PAY_TOTAL >= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.GOLD >= ?");
                            }
                        }
                        values.add(account.getAmountMin());
                    }

                    if (CommonUtil.isNotEmpty(account.getAmountMax())) {
                        //充值金
                        if (moneyType == 0) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.RECHARGE_TOTAL <= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.RECHARGE_DRAW_TOTAL <= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.RECHARGE_IN_TOTAL <= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.RECHARGE_OUT_TOTAL <= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.RECHARGE_PAY_TOTAL <= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.RECHARGE_AMOUNT <= ?");
                            }
                        }
                        //奖金
                        if (moneyType == 1) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.BONUS_TOTAL <= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.BONUS_DRAW_TOTAL <= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.BONUS_IN_TOTAL <= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.BONUS_OUT_TOTAL <= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.BONUS_PAY_TOTAL <= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.BONUS_AMOUNT <= ?");
                            }
                        }
                        //红包
                        if (moneyType == 2) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.PRESENT_TOTAL <= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.PRESENT_DRAW_TOTAL <= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.PRESENT_IN_TOTAL <= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.PRESENT_OUT_TOTAL <= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.PRESENT_PAY_TOTAL <= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.PRESENT_AMOUNT <= ?");
                            }
                        }
                        //积分
                        if (moneyType == 3) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.SCORE_TOTAL <= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.SCORE_DRAW_TOTAL <= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.SCORE_IN_TOTAL <= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.SCORE_OUT_TOTAL <= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.SCORE_PAY_TOTAL <= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.SCORE <= ?");
                            }
                        }
                        //金币
                        if (moneyType == 4) {
                            //存入
                            if (sumType == 0) {
                                sb.append(" and a.GOLD_TOTAL <= ?");
                            }
                            //取出
                            if (sumType == 1) {
                                sb.append(" and a.GOLD_DRAW_TOTAL <= ?");
                            }
                            //转入
                            if (sumType == 2) {
                                sb.append(" and a.GOLD_IN_TOTAL <= ?");
                            }
                            //转出
                            if (sumType == 3) {
                                sb.append(" and a.GOLD_OUT_TOTAL <= ?");
                            }
                            //消费
                            if (sumType == 4) {
                                sb.append(" and a.GOLD_PAY_TOTAL <= ?");
                            }
                            //余额
                            if (sumType == 5) {
                                sb.append(" and a.GOLD <= ?");
                            }
                        }
                        values.add(account.getAmountMax());
                    }
                }
            }

        }
        String totalHql = " select count(*)  from (" + sb.toString() + ")";
        j.setTotal(accountDao.countBySql(totalHql, values).longValue());// 设置总记录数
        sb.append(" order by a.RECHARGE_TOTAL desc");
        List<Map> accountMaps = accountDao.findBySql(sb.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<AccountVO> accounts = new ArrayList<AccountVO>();
        if (accountMaps != null && accountMaps.size() > 0) {// 转换模型
            for (Map map : accountMaps) {
                AccountVO a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("存入");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_TOTAL")));//充值金存入
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_TOTAL")));//奖金存入
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_TOTAL")));//红包存入
                a.setScore(CommonUtil.formatDouble(map.get("SCORE_TOTAL")));//积分存入
                a.setGold(CommonUtil.formatDouble(map.get("GOLD_TOTAL")));//金币存入
                accounts.add(a);


                a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("取出");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_DRAW_TOTAL")));//充值金取出
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_DRAW_TOTAL")));//奖金取出
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_DRAW_TOTAL")));//红包取出
                a.setScore(CommonUtil.formatDouble(map.get("SCORE_DRAW_TOTAL")));//积分取出
                a.setGold(CommonUtil.formatDouble(map.get("GOLD_DRAW_TOTAL")));//金币取出
                accounts.add(a);

                a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("转入");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_IN_TOTAL")));//充值金转入
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_IN_TOTAL")));//奖金转入
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_IN_TOTAL")));//红包转入
                a.setScore(CommonUtil.formatDouble(map.get("SCORE_IN_TOTAL")));//积分转入
                a.setGold(CommonUtil.formatDouble(map.get("GOLD_IN_TOTAL")));//金币转入
                accounts.add(a);

                a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("转出");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_OUT_TOTAL")));//充值金转出
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_OUT_TOTAL")));//奖金转出
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_OUT_TOTAL")));//红包转出
                a.setScore(CommonUtil.formatDouble(map.get("SCORE_OUT_TOTAL")));//积分转出
                a.setGold(CommonUtil.formatDouble(map.get("GOLD_OUT_TOTAL")));//金币转出
                accounts.add(a);

                a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("消费");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_PAY_TOTAL")));//充值金消费
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_PAY_TOTAL")));//奖金消费
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_PAY_TOTAL")));//红包消费
                a.setScore(CommonUtil.formatDouble(map.get("SCORE_PAY_TOTAL")));//积分消费
                a.setGold(CommonUtil.formatDouble(map.get("GOLD_PAY_TOTAL")));//金币消费
                accounts.add(a);

                a = new AccountVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                a.setAccountType("余额");
                a.setRechargeAmount(CommonUtil.formatDouble(map.get("RECHARGE_AMOUNT")));//充值金余额
                a.setBonusAmount(CommonUtil.formatDouble(map.get("BONUS_AMOUNT")));//奖金余额
                a.setPresentAmount(CommonUtil.formatDouble(map.get("PRESENT_AMOUNT")));//红包余额
                a.setScore(CommonUtil.formatDouble(map.get("SCORE")));//积分余额
                a.setGold(CommonUtil.formatDouble(map.get("GOLD")));//金币余额
                accounts.add(a);
            }
        }
        j.setRows(accounts);// 设置返回的行
        return j;
    }

    /**
     * 资金明细查询
     *
     * @param dg
     * @param account
     * @return
     */
    public EasyuiDataGridJson detailData(EasyuiDataGrid dg, AccountLogVO account) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();

        String sql = " select u.USER_NAME,u.USER_CODE,"
                + " a.BONUS_AMOUNT,a.RECHARGE_AMOUNT,a.PRESENT_AMOUNT,a.SCORE,a.COMMISSION,a.GOLD,"
                + " a.BONUS_AMOUNT_OLD,a.RECHARGE_AMOUNT_OLD,a.PRESENT_AMOUNT_OLD,a.SCORE_OLD,a.COMMISSION_OLD,a.GOLD_OLD,"
                + " a.BONUS_AMOUNT_NEW,a.RECHARGE_AMOUNT_NEW,a.PRESENT_AMOUNT_NEW,a.SCORE_NEW,a.COMMISSION_NEW,a.GOLD_NEW," 
                + " a.ORDER_ID,a.EVENT_TYPE ,a.TYPE,a.EVENT_CODE,a.CREATE_TIME,a.MEMO,a.DESCRIPTION  "
                + " from ACCOUNT_LOG a left join  USER_MEMBER u on  a.USER_CODE=u.USER_CODE "
                + " where 1=1";
        List<Object> values = new ArrayList<Object>();
        if (account != null) {// 添加查询条件
            boolean flag = account.isFlag();
            //用户ID
            if (CommonUtil.isNotEmpty(account.getUserCode())) {
                if (flag) {
                    sql += " and u.USER_CODE like '%%" + account.getUserCode().trim() + "%%' ";

                } else {

                    sql += " and u.USER_CODE = ? ";
                    values.add(account.getUserCode().trim());
                }
            }
            //用户名
            if (CommonUtil.isNotEmpty(account.getUserName())) {
                if (flag) {
                    sql += " and u.USER_NAME like '%%" + account.getUserName().trim() + "%%' ";
                } else {

                    sql += " and u.USER_NAME = ?";
                    values.add(account.getUserName().trim());
                }
            }

            //交易开始时间
            if (CommonUtil.isNotEmpty(account.getTradeStart())) {
                sql += " and a.CREATE_TIME >= ?";
                values.add(DateUtil.format(account.getTradeStart(),"yy-MM-dd HH:mm:ss"));
            }
            //交易结束时间
            if (CommonUtil.isNotEmpty(account.getTradeEnd())) {
                sql += " and a.CREATE_TIME <= ?";
                values.add(DateUtil.format(account.getTradeEnd(),"yy-MM-dd HH:mm:ss"));
            }

            //订单编号
            if (CommonUtil.isNotEmpty(account.getOrderId())) {
                if (flag) {
                    sql += " and a.ORDER_ID like '%%" + account.getOrderId().trim() + "%%' ";
                } else {
                    sql += " and a.ORDER_ID = ?";
                    values.add(account.getOrderId().trim());
                }
            }
            //账户类型
            if (CommonUtil.isNotEmpty(account.getAccountType())) {
                //充值金
                if (account.getAccountType().equals("0")) {
                    sql += " and a.RECHARGE_AMOUNT <> 0 ";
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        sql += " and a.RECHARGE_AMOUNT >= ? ";
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        sql += " and a.RECHARGE_AMOUNT <= ? ";
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //奖金
                if (account.getAccountType().equals("1")) {
                    sql += " and a.BONUS_AMOUNT <> 0 ";
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        sql += " and a.BONUS_AMOUNT >= ? ";
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        sql += " and a.BONUS_AMOUNT <= ? ";
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //红包
                if (account.getAccountType().equals("2")) {
                    sql += " and a.PRESENT_AMOUNT <> 0 ";
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        sql += " and a.PRESENT_AMOUNT >= ? ";
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        sql += " and a.PRESENT_AMOUNT <= ? ";
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //积分
                if (account.getAccountType().equals("3")) {
                    sql += " and a.SCORE <> 0 ";
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        sql += " and a.SCORE >= ? ";
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        sql += " and a.SCORE <= ? ";
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //金币
                if (account.getAccountType().equals("4")) {
                    sql += " and a.GOLD <> 0 ";
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        sql += " and a.GOLD >= ? ";
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        sql += " and a.GOLD <= ? ";
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
            } 
            //资金流向
            if (CommonUtil.isNotEmpty(account.getSecondType())&&!"全部".equals(account.getSecondType())) {
            	String eventType = account.getSecondType().substring(0, 1);
                String secondType=account.getSecondType().substring(1,3);
                sql += " and a.event_type = ? and a.SECOND_TYPE=? ";
                values.add(eventType);
                values.add(secondType);
            }

            if (CommonUtil.isNotEmpty(account.getEventCode())) {
            	String eventCode=account.getEventCode();
            	if(eventCode.length()==5){
            		String eventType = eventCode.substring(0, 1);
                    String secondType=eventCode.substring(1,3);
                    String type=eventCode.substring(3,5);
                    sql += " and a.event_type = ? and a.SECOND_TYPE=? and a.TYPE=? ";
                    values.add(eventType);
                    values.add(secondType);
                    values.add(type);
            	}else{
            		
            		sql += " and a.MEMO = ?  ";
            		values.add(account.getEventCode());
            	}
            }
        }
        String totalHql = " select count(*)  from (" + sql + ")";
        j.setTotal(accountDao.countBySql(totalHql, values).longValue());// 设置总记录数

        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = "a.CREATE_TIME";
            }
            if (!sort.equals("")) {
                sql += " order by " + sort + " " + dg.getOrder();
            }
        }
        List<Map> accountMaps = accountDao.findBySql(sql, values, dg.getPage(), dg.getRows());// 查询分页
        List<AccountLogVO> accounts = new ArrayList<AccountLogVO>();
        if (accountMaps != null && accountMaps.size() > 0) {// 转换模型
        	AccountOperate operate=new AccountOperate();
            for (Map map : accountMaps) {
                AccountLogVO a = new AccountLogVO();
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));

                BigDecimal bonusAmount = map.get("BONUS_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("BONUS_AMOUNT");
                a.setBonusAmount(bonusAmount.doubleValue());
                BigDecimal bonusAmountOld = map.get("BONUS_AMOUNT_OLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("BONUS_AMOUNT_OLD");
                a.setBonusAmountOld(bonusAmountOld.doubleValue());
                BigDecimal bonusAmountNew = map.get("BONUS_AMOUNT_NEW") == null ? new BigDecimal(0) : (BigDecimal) map.get("BONUS_AMOUNT_NEW");
                a.setBonusAmountNew(bonusAmountNew.doubleValue());

                BigDecimal rechargeAmount = map.get("RECHARGE_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("RECHARGE_AMOUNT");
                a.setRechargeAmount(rechargeAmount.doubleValue());
                BigDecimal rechargeAmountOld = map.get("RECHARGE_AMOUNT_OLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("RECHARGE_AMOUNT_OLD");
                a.setRechargeAmountOld(rechargeAmountOld.doubleValue());
                BigDecimal rechargeAmountNew = map.get("RECHARGE_AMOUNT_NEW") == null ? new BigDecimal(0) : (BigDecimal) map.get("RECHARGE_AMOUNT_NEW");
                a.setRechargeAmountNew(rechargeAmountNew.doubleValue());

                BigDecimal presentAmount = map.get("PRESENT_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("PRESENT_AMOUNT");
                a.setPresentAmount(presentAmount.doubleValue());
                BigDecimal presentAmountOld = map.get("PRESENT_AMOUNT_OLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("PRESENT_AMOUNT_OLD");
                a.setPresentAmountOld(presentAmountOld.doubleValue());
                BigDecimal presentAmountNew = map.get("PRESENT_AMOUNT_NEW") == null ? new BigDecimal(0) : (BigDecimal) map.get("PRESENT_AMOUNT_NEW");
                a.setPresentAmountNew(presentAmountNew.doubleValue());

                BigDecimal score = map.get("SCORE") == null ? new BigDecimal(0) : (BigDecimal) map.get("SCORE");
                a.setScore(score.doubleValue());
                BigDecimal scoreOld = map.get("SCORE_OLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("SCORE_OLD");
                a.setScoreOld(scoreOld.doubleValue());
                BigDecimal scoreNew = map.get("SCORE_NEW") == null ? new BigDecimal(0) : (BigDecimal) map.get("SCORE_NEW");
                a.setScoreNew(scoreNew.doubleValue());
                
                BigDecimal gold = map.get("GOLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("GOLD");
                a.setGold(gold.doubleValue());
                BigDecimal goldOld = map.get("GOLD_OLD") == null ? new BigDecimal(0) : (BigDecimal) map.get("GOLD_OLD");
                a.setGoldOld(goldOld.doubleValue());
                BigDecimal goldNew = map.get("GOLD_NEW") == null ? new BigDecimal(0) : (BigDecimal) map.get("GOLD_NEW");
                a.setGoldNew(goldNew.doubleValue());
                
                a.setOrderId((String) map.get("ORDER_ID"));
                BigDecimal eventType = map.get("EVENT_TYPE") == null ? new BigDecimal(0) : (BigDecimal) map.get("EVENT_TYPE");
                a.setEventType(eventType.intValue());
                a.setType((String) map.get("TYPE"));
                Date createTime = (Date) map.get("CREATE_TIME");
                a.setCreateTime(DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss"));
                String eventCode=(String) map.get("EVENT_CODE");
                a.setEventCode(eventCode);
                String memo=(String) map.get("MEMO");
                a.setMemo(memo);
                if(CommonUtil.isNotEmpty(memo)){
                	String thirdName=operate.getThirdName(memo.substring(0, 5));
                	String fouthName=operate.getFouthName(memo);
                	if(CommonUtil.isNotEmpty(thirdName)&&CommonUtil.isNotEmpty(fouthName)){
                		a.setEventName(thirdName.concat("-").concat(fouthName));
                	}
                }
                accounts.add(a);
            }
        }
        j.setRows(accounts);// 设置返回的行
        return j;
    }
    /**
     * 账户明细汇总
     * @param account
     * @return
     */
    public Map detailCount(AccountLogVO account){
    	StringBuilder str=new StringBuilder("select sum(a.recharge_amount) recharge_amount, sum(a.bonus_amount) bonus_amount,");
        str.append(" sum(a.present_amount) present_amount,sum(a.score) score,sum(a.gold) gold from account_log a  inner join user_member u  on a.user_code = u.user_code ");
        str.append(" where 1=1");
        List<Object> values = new ArrayList<Object>();
        if (account != null) {// 添加查询条件
            boolean flag = account.isFlag();
         // 用户编号
 			if (CommonUtil.isNotEmpty(account.getUserCode())) {
 				if (flag) {
 					str.append(" and u.user_code like ? ");
 					values.add("%" + account.getUserCode().trim() + "%");
 				} else {
 					str.append(" and u.user_code = ? ");
 					values.add(account.getUserCode().trim());
 				}
 			}
            //用户名
            if (CommonUtil.isNotEmpty(account.getUserName())) {
                if (flag) {
                    str.append(" and u.USER_NAME like ? ");
                    values.add("%" + account.getUserName().trim() + "%");
                } else {

                    str.append(" and u.USER_NAME = ?");
                    values.add(account.getUserName().trim());
                }
            }

            //交易开始时间
            if (CommonUtil.isNotEmpty(account.getTradeStart())) {
                str.append(" and a.CREATE_TIME >= ?");
                values.add(DateUtil.format(account.getTradeStart(),"yy-MM-dd HH:mm:ss"));
            }
            //交易结束时间
            if (CommonUtil.isNotEmpty(account.getTradeEnd())) {
                str.append( " and a.CREATE_TIME <= ?");
                values.add(DateUtil.format(account.getTradeEnd(),"yy-MM-dd HH:mm:ss"));
            }

            //订单编号
            if (CommonUtil.isNotEmpty(account.getOrderId())) {
                if (flag) {
                    str.append( " and a.ORDER_ID like ? ");
                    values.add("%" + account.getOrderId().trim() + "%");
                } else {
                    str.append(" and a.ORDER_ID = ?");
                    values.add(account.getOrderId().trim());
                }
            }
            //账户类型
            if (CommonUtil.isNotEmpty(account.getAccountType())) {
                //充值金
                if (account.getAccountType().equals("0")) {
                    str.append(" and a.RECHARGE_AMOUNT <> 0 ");
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        str.append(" and a.RECHARGE_AMOUNT >= ? ");
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        str.append(" and a.RECHARGE_AMOUNT <= ? ");
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //奖金
                if (account.getAccountType().equals("1")) {
                    str.append(" and a.BONUS_AMOUNT <> 0 ");
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        str.append(" and a.BONUS_AMOUNT >= ? ");
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        str.append(" and a.BONUS_AMOUNT <= ? ");
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //红包
                if (account.getAccountType().equals("2")) {
                    str.append(" and a.PRESENT_AMOUNT <> 0 ");
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        str.append(" and a.PRESENT_AMOUNT >= ? ");
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        str.append(" and a.PRESENT_AMOUNT <= ? ");
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //积分
                if (account.getAccountType().equals("3")) {
                    str.append(" and a.SCORE <> 0 ");
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        str.append(" and a.SCORE >= ? ");
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        str.append(" and a.SCORE <= ? ");
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
                //金币
                if (account.getAccountType().equals("4")) {
                    str.append(" and a.GOLD <> 0 ");
                    //交易金额最低
                    if (CommonUtil.isNotEmpty(account.getTradeMin())) {

                        str.append(" and a.GOLD >= ? ");
                        values.add(account.getTradeMin().doubleValue());
                    }
                    //交易金额最高
                    if (CommonUtil.isNotEmpty(account.getTradeMax())) {
                        str.append(" and a.GOLD <= ? ");
                        values.add(account.getTradeMax().doubleValue());
                    }
                }
            } 
            //资金流向
            if (CommonUtil.isNotEmpty(account.getSecondType())&&!"全部".equals(account.getSecondType())) {
            	String eventType = account.getSecondType().substring(0, 1);
                String secondType=account.getSecondType().substring(1,3);
                str.append(" and a.event_type = ? and a.SECOND_TYPE=? ");
                values.add(eventType);
                values.add(secondType);
            }

            if (CommonUtil.isNotEmpty(account.getEventCode())) {
            	String eventCode=account.getEventCode();
            	if(eventCode.length()==5){
            		String eventType = eventCode.substring(0, 1);
                    String secondType=eventCode.substring(1,3);
                    String type=eventCode.substring(3,5);
                    str.append(" and a.event_type = ? and a.SECOND_TYPE=? and a.TYPE=? ");
                    values.add(eventType);
                    values.add(secondType);
                    values.add(type);
            	}else{
            		str.append(" and a.MEMO = ?  ");
            		values.add(account.getEventCode());
            	}
            }
        }
        //str.append(" group by a.user_code  ");
        List<Map> mapList = accountDao.findBySql(str.toString(), values);
        if (CommonUtil.isNotEmpty(mapList)) {
            return mapList.get(0);
        }
        return null;
    }
    /**
     * 充值管理查询
     *
     * @param dg
     * @param fill
     * @return
     */
    public EasyuiDataGridJson rechargeData(EasyuiDataGrid dg, FillVO fill) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        String sql = "select u.USER_NAME userName,u.USER_CODE userCode,"
                + " a.OUT_ORDER_ID ,a.AMOUNT amount,a.ORDER_ID  orderId,a.CREATE_TIME createTime ,a.ACCEPT_TIME acceptTime,a.FILL_RESOURCES fillResources,"
                + " a.STATUS  status,a.REAL_AMOUNT   "
                + " from ACCOUNT_FILL a left join  USER_MEMBER u on  a.USER_CODE=u.USER_CODE "
                + " where 1=1";
        List<Object> values = new ArrayList<Object>();
        if (fill != null) {// 添加查询条件
            boolean flag = fill.isFlag();
            //用户ID
            if (CommonUtil.isNotEmpty(fill.getUserCode())) {
                if (flag) {
                    sql += " and u.USER_CODE like '%%" + fill.getUserCode().trim() + "%%' ";

                } else {

                    sql += " and u.USER_CODE = ? ";
                    values.add(fill.getUserCode().trim());
                }
            }
            //用户名
            if (CommonUtil.isNotEmpty(fill.getUserName())) {
                if (flag) {
                    sql += " and u.USER_NAME like '%%" + fill.getUserName().trim() + "%%' ";
                } else {

                    sql += " and u.USER_NAME = ?";
                    values.add(fill.getUserName().trim());
                }
            }

            //发起开始时间
            if (CommonUtil.isNotEmpty(fill.getCreateStart())) {
                sql += " and a.CREATE_TIME >= ?";
                values.add(DateUtil.format(fill.getCreateStart(),"yy-MM-dd HH:mm:ss"));
            }
            //发起结束时间
            if (CommonUtil.isNotEmpty(fill.getCreateEnd())) {
                sql += " and a.CREATE_TIME <= ?";
                
                values.add(DateUtil.format(fill.getCreateEnd(),"yy-MM-dd HH:mm:ss"));
            }

            //受理开始时间
            if (CommonUtil.isNotEmpty(fill.getAcceptStart())) {
                sql += " and a.ACCEPT_TIME >= ?";
                values.add(DateUtil.format(fill.getAcceptStart(),"yy-MM-dd HH:mm:ss"));
            }
            //受理结束时间
            if (CommonUtil.isNotEmpty(fill.getAcceptEnd())) {
                sql += " and a.ACCEPT_TIME <= ?";
                values.add(DateUtil.format(fill.getAcceptEnd(),"yy-MM-dd HH:mm:ss"));
            }

            //对方编号
            if (CommonUtil.isNotEmpty(fill.getOutOrderId())) {
                if (flag) {
                    sql += " and a.OUT_ORDER_ID like '%%" + fill.getOutOrderId().trim() + "%%' ";
                } else {

                    sql += " and a.OUT_ORDER_ID = ?";
                    values.add(fill.getOutOrderId().trim());
                }
            }
            //订单编号
            if (CommonUtil.isNotEmpty(fill.getOrderId())) {
                if (flag) {
                    sql += " and a.ORDER_ID like '%%" + fill.getOrderId().trim() + "%%' ";
                } else {

                    sql += " and a.ORDER_ID = ?";
                    values.add(fill.getOrderId().trim());
                }
            }
            //交易渠道
            if (CommonUtil.isNotEmpty(fill.getSid())) {
                sql += " and a.FILL_RESOURCES = ?";
                values.add(fill.getSid().trim());
            }
            //交易状态
            if (CommonUtil.isNotEmpty(fill.getStatus())) {
                sql += " and a.STATUS = ?";
                values.add(fill.getStatus());
            }
            //注册渠道
            if (CommonUtil.isNotEmpty(fill.getRegSid())) {
                sql += " and u.SID = ?";
                values.add(fill.getRegSid().trim());
            }
            //所属于
            if (CommonUtil.isNotEmpty(fill.getTrackUserCode())) {
                if (flag) {
                    sql += " and u.TRACK_USER_CODE like '%%" + fill.getTrackUserCode().trim() + "%%' ";
                } else {

                    sql += " and u.TRACK_USER_CODE = ?";
                    values.add(fill.getTrackUserCode().trim());
                }
            }
            //金额最低
            if (CommonUtil.isNotEmpty(fill.getAmountMin())) {

                sql += " and a.AMOUNT >= " + fill.getAmountMin().doubleValue();
            }
            //金额最高
            if (CommonUtil.isNotEmpty(fill.getAmountMax())) {
                sql += " and a.AMOUNT <= " + fill.getAmountMax().doubleValue();
            }

        }
        String totalHql = " select count(*)  from (" + sql + ")";
        j.setTotal(accountDao.countBySql(totalHql, values).longValue());// 设置总记录数

        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
                sort = "a.CREATE_TIME";
            }
            if (dg.getSort().toString().equalsIgnoreCase("acceptTime")) {
                sort = "a.ACCEPT_TIME";
            }
            if (dg.getSort().toString().equalsIgnoreCase("amount")) {
                sort = "a.AMOUNT";
            }
            if (dg.getSort().toString().equalsIgnoreCase("realAmount")) {
                sort = "a.REAL_AMOUNT";
            }
            if (!sort.equals("")) {
                sql += " order by " + sort + " " + dg.getOrder();
            }
        }
        List<Map> accountMaps = accountDao.findBySql(sql, values, dg.getPage(), dg.getRows());// 查询分页
        List<FillVO> accounts = new ArrayList<FillVO>();
        if (accountMaps != null && accountMaps.size() > 0) {// 转换模型
            for (Map map : accountMaps) {
                FillVO a = new FillVO();

                a.setUserCode((String) map.get("USERCODE"));
                a.setUserName((String) map.get("USERNAME"));
                BigDecimal amount = map.get("AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("AMOUNT");
                a.setAmount(amount.doubleValue());
                BigDecimal realAmount = map.get("REAL_AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("REAL_AMOUNT");
                a.setRealAmount(realAmount.doubleValue());
                a.setOutOrderId((String) map.get("OUT_ORDER_ID"));
                a.setOrderId((String) map.get("ORDERID"));
                Date createTime = (Date) map.get("CREATETIME");
                a.setCreateTime(DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss"));
                Date acceptTime = (Date) map.get("ACCEPTTIME");
                a.setAcceptTime(DateUtil.format(acceptTime, "yyyy-MM-dd HH:mm:ss"));
                a.setFillResources((String) map.get("FILLRESOURCES"));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                a.setStatus(status.intValue());
                accounts.add(a);
            }
        }
        j.setRows(accounts);// 设置返回的行
        return j;
    }
    /**
     * 充值管理汇总
     * @param fill
     * @return
     */
    public Map rechargeCount(FillVO fill){
    	  StringBuilder sb = new StringBuilder("select sum(CASE WHEN a.status = 0 THEN a.amount ELSE 0 END) as doingAmount,");
          sb.append(" sum(CASE WHEN a.status = 1 THEN a.amount ELSE 0 END) as succAmount,");
          sb.append(" sum(CASE WHEN a.status = 2 THEN a.amount ELSE 0 END) as failAmount ");
          sb.append(" from ACCOUNT_FILL a left join  USER_MEMBER u on  a.USER_CODE=u.USER_CODE where 1=1 ");
          List<Object> values = new ArrayList<Object>();
          if (fill != null) {// 添加查询条件
              boolean flag = fill.isFlag();
              // 用户编号
   			if (CommonUtil.isNotEmpty(fill.getUserCode())) {
   				if (flag) {
   					sb.append(" and u.user_code like ? ");
   					values.add("%" + fill.getUserCode().trim() + "%");
   				} else {
   					sb.append(" and u.user_code = ? ");
   					values.add(fill.getUserCode().trim());
   				}
   			}
              //用户名
              if (CommonUtil.isNotEmpty(fill.getUserName())) {
                  if (flag) {
                      sb.append(" and u.USER_NAME like ? ");
                      values.add("%" + fill.getUserName().trim() + "%");
                  } else {

                      sb.append(" and u.USER_NAME = ?");
                      values.add(fill.getUserName().trim());
                  }
              }
              //发起开始时间
              if (CommonUtil.isNotEmpty(fill.getCreateStart())) {
                  sb.append(" and a.CREATE_TIME >= ?");
                  values.add(DateUtil.format(fill.getCreateStart(),"yy-MM-dd HH:mm:ss"));
              }
              //发起结束时间
              if (CommonUtil.isNotEmpty(fill.getCreateEnd())) {
                  sb.append(" and a.CREATE_TIME <= ?");
                  
                  values.add(DateUtil.format(fill.getCreateEnd(),"yy-MM-dd HH:mm:ss"));
              }

              //受理开始时间
              if (CommonUtil.isNotEmpty(fill.getAcceptStart())) {
                  sb.append(" and a.ACCEPT_TIME >= ?");
                  values.add(DateUtil.format(fill.getAcceptStart(),"yy-MM-dd HH:mm:ss"));
              }
              //受理结束时间
              if (CommonUtil.isNotEmpty(fill.getAcceptEnd())) {
                  sb.append(" and a.ACCEPT_TIME <= ?");
                  values.add(DateUtil.format(fill.getAcceptEnd(),"yy-MM-dd HH:mm:ss"));
              }

              //对方编号
              if (CommonUtil.isNotEmpty(fill.getOutOrderId())) {
                  if (flag) {
                      sb.append(" and a.OUT_ORDER_ID like ? ");
                      values.add("%" + fill.getOutOrderId().trim() + "%");
                  } else {

                      sb.append(" and a.OUT_ORDER_ID = ?");
                      values.add(fill.getOutOrderId().trim());
                  }
              }
              //订单编号
              if (CommonUtil.isNotEmpty(fill.getOrderId())) {
                  if (flag) {
                      sb.append(" and a.ORDER_ID like ? ");
                      values.add("%" + fill.getOrderId().trim() + "%");
                  } else {

                      sb.append(" and a.ORDER_ID = ?");
                      values.add(fill.getOrderId().trim());
                  }
              }
              //交易渠道
              if (CommonUtil.isNotEmpty(fill.getSid())) {
                  sb.append(" and a.FILL_RESOURCES = ?");
                  values.add(fill.getSid().trim());
              }
              //交易状态
              if (CommonUtil.isNotEmpty(fill.getStatus())) {
                  sb.append(" and a.STATUS = ?");
                  values.add(fill.getStatus());
              }
              //注册渠道
              if (CommonUtil.isNotEmpty(fill.getRegSid())) {
                  sb.append(" and u.SID = ?");
                  values.add(fill.getRegSid().trim());
              }
              //所属于
              if (CommonUtil.isNotEmpty(fill.getTrackUserCode())) {
                  if (flag) {
                      sb.append(" and u.TRACK_USER_CODE like ? ");
                      values.add("%" + fill.getTrackUserCode().trim() + "%");
                  } else {

                      sb.append(" and u.TRACK_USER_CODE = ?");
                      values.add(fill.getTrackUserCode().trim());
                  }
              }
              //金额最低
              if (CommonUtil.isNotEmpty(fill.getAmountMin())) {

                  sb.append(" and a.AMOUNT >= ? " );
                  values.add(fill.getAmountMin().doubleValue());
              }
              //金额最高
              if (CommonUtil.isNotEmpty(fill.getAmountMax())) {
                  sb.append(" and a.AMOUNT <=? ") ;
                  values.add(fill.getAmountMax().doubleValue());
              }

          }
          List<Map> mapList = accountDao.findBySql(sb.toString(), values);
          if (CommonUtil.isNotEmpty(mapList)) {
              return mapList.get(0);
          }
          return null;
    }

    /**
     * 充值详情
     *
     * @param id
     * @return
     */
    public Map rechargeDetail(String orderId) {
        StringBuilder sb = new StringBuilder("select u.user_code,u.user_name ,u.REAL_NAME, u.MOBILE,u.CARD_CODE ,a.REAL_AMOUNT,");
        sb.append(" a.OUT_ORDER_ID,a.AMOUNT,a.ORDER_ID ,a.CREATE_TIME  ,a.ACCEPT_TIME ,a.FILL_RESOURCES ,a.STATUS,a.MEMO ");
        sb.append(" from ACCOUNT_FILL a left join  USER_MEMBER u on  a.USER_CODE=u.USER_CODE");
        sb.append(" where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        if (CommonUtil.isNotEmpty(orderId)) {
            sb.append(" and a.ORDER_ID = ? ");
            values.add(orderId);
        }
        List<Map> mapList = accountDao.findBySql(sb.toString(), values);
        if (mapList != null && mapList.size() > 0) {
            Map recharge= mapList.get(0);
            BigDecimal amount=(BigDecimal) recharge.get("AMOUNT");
            BigDecimal realAmount=(BigDecimal) recharge.get("REAL_AMOUNT");
            BigDecimal status=(BigDecimal) recharge.get("STATUS");
            BigDecimal fee=new BigDecimal(0);
            if(status.intValue()==1){
            	fee=amount.subtract(realAmount);
            }
            recharge.put("FEE", fee);
            return recharge;
        }
        return null;
    }
    
    /**
     * 充值备注
     * @param orderId
     * @param memo
     */
    public int rechargeMemoSave(String orderId,String memo){
    	StringBuilder str=new StringBuilder("update ACCOUNT_FILL set MEMO=? where ORDER_ID=?");
    	 List<Object> values = new ArrayList<Object>();
    	 values.add(memo);
    	 values.add(orderId);
    	 return accountDao.executeSql(str.toString(), values);
    }

    
    /**
     * 充值单强制失败
     * @param orderList
     * @return
     */
    public boolean  rechargeFail(List<String> orderList){

		 StringBuilder str=new StringBuilder("update ACCOUNT_FILL set STATUS= 2 where ORDER_ID= ? ");
		 int n=0;
		 for(int i=0;i<orderList.size();i++){
			 List<Object> values = new ArrayList<Object>();
			 String orderId=orderList.get(i);
			 values.add(orderId);
			 int result=accountDao.executeSql(str.toString(), values);
			 if(result>0){
				 n++;
			 }
		 }
		 if(n==orderList.size()){
			 return true;
		 }
		 return false;
	 
    }
    /**
     * 内部存取操作
     *
     * @param internalAccess
     */
    public Map<String, Object> accountAdjust(InternalAccessVO vo) {
        Integer accountType = vo.getAccountType();
        
        Map<String,String> eventCodeMap=new HashMap<String, String>();
        
        eventCodeMap.put("0010000", "1010000");//普通转入——普通转出
        eventCodeMap.put("1010000", "0010000");//普通转出——普通转入
        
        eventCodeMap.put("0010001", "1010001");//线下充值——大额转账
        eventCodeMap.put("1010001", "0010001");//大额转账——线下充值
        
        eventCodeMap.put("0010100", "1010100");//赔偿转入——赔偿转出
        eventCodeMap.put("1010100", "0010100");//赔偿转出——赔偿转入
        
        eventCodeMap.put("0010200", "1010200");//活动转入——活动转出
        eventCodeMap.put("1010200", "0010200");//活动转出——活动转入
        
        eventCodeMap.put("0010300", "1010300");//任务转入——任务转出
        eventCodeMap.put("1010300", "0010300");//任务转出——任务转入
        
        eventCodeMap.put("0010400", "1010400");//竞猜奖励——竞猜返奖
        eventCodeMap.put("1010400", "0010400");//竞猜返奖——竞猜奖励
        
        String sysUserCode = vo.getAccount();
        List<Customer> sysAccountList = memberDao.customerInfo(sysUserCode, null, null);
        Customer sysVo = null;
        if (sysAccountList != null && sysAccountList.size() > 0) {
            sysVo = sysAccountList.get(0);
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //充值金
        if (accountType == 0) {
            returnMap = rechargeAccess(sysVo, vo,eventCodeMap);
        }
        //奖金
        if (accountType == 1) {
            returnMap = bonusAccess(sysVo, vo, eventCodeMap);
        }
        //红包
        if (accountType == 2) {
            returnMap = presentAccess(sysVo, vo, eventCodeMap);
        }

        //积分
        if (accountType == 3) {
            returnMap = scoreAccess(sysVo, vo, eventCodeMap);
        }
        //金币
        if (accountType == 4) {
            returnMap = goldAccess(sysVo, vo, eventCodeMap);
        }
        return returnMap;

    }

    //充值金转入转出
    public Map<String, Object> rechargeAccess(Customer sysVo, InternalAccessVO vo,Map<String,String> eventCodeMap) {
    	
        String eventCode=vo.getEventCode();
        String secondType = vo.getSecondType();
        
        Map<String, Object> idMap = new HashMap<String, Object>();
        Date date = new Date();
        String serialNo = formatter.format(date) + CommonUtil.random(6);
        String relateSerialNo = formatter.format(date) + CommonUtil.random(6);
        idMap.put("serialNo", serialNo);
        idMap.put("relateSerialNo", relateSerialNo);
        idMap.put("eventCode", eventCode);
        idMap.put("otherEventCode", eventCodeMap.get(eventCode));
        idMap.put("secondType", secondType);
        //转出(由普通账户转出到系统账户)
        if (secondType.equals("101")) {
        	
            //普通账户转出
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            
            values.add(eventCode);
            
            values.add(-vo.getAmount());
            values.add(vo.getRechargeAmount());
            values.add(vo.getRechargeAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            
            //系统账户转入
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            
            values.add(eventCodeMap.get(eventCode));
            
            values.add(vo.getAmount());
            values.add(sysVo.getRechargeAmount());
            values.add(sysVo.getRechargeAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //转入(由系统账户转入到普通账户)
        if (secondType.equals("001")) {
        	
            //普通账户转入

            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            
            values.add(eventCode);
            
            values.add(vo.getAmount());
            values.add(vo.getRechargeAmount());
            values.add(vo.getRechargeAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            
            //系统账户转出

            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(eventCodeMap.get(eventCode));
            values.add(-vo.getAmount());
            values.add(sysVo.getRechargeAmount());
            values.add(sysVo.getRechargeAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //存入
        if (secondType.equals("000")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,2,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getRechargeAmount());
            values.add(vo.getRechargeAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //取出
        if (secondType.equals("100")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,OUT_ACCOUNT,EVENT_CODE,");
            sb.append("RECHARGE_AMOUNT,RECHARGE_AMOUNT_OLD,RECHARGE_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,3,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getRechargeAmount());
            values.add(vo.getRechargeAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        return idMap;

    }

    //奖金转入转出
    public Map<String, Object> bonusAccess(Customer sysVo, InternalAccessVO vo,Map<String,String> eventCodeMap) {
    	
    	String eventCode=vo.getEventCode();
        String secondType = vo.getSecondType();
        Map<String, Object> idMap = new HashMap<String, Object>();
        Date date = new Date();
        String serialNo = formatter.format(date) + CommonUtil.random(6);
        String relateSerialNo = formatter.format(date) + CommonUtil.random(6);
        idMap.put("serialNo", serialNo);
        idMap.put("relateSerialNo", relateSerialNo);
        idMap.put("eventCode", eventCode);
        String otherEventCode=eventCodeMap.get(eventCode);
        idMap.put("otherEventCode", otherEventCode);
        idMap.put("secondType", secondType);
        //转出(由普通账户转出到系统账户)
        if (secondType.equals("101")) {
            //普通账户转出
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getBonusAmount());
            values.add(vo.getBonusAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转入
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(otherEventCode);
            values.add(vo.getAmount());
            values.add(sysVo.getBonusAmount());
            values.add(sysVo.getBonusAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //转入(由系统账户转入到普通账户)
        if (secondType.equals("001")) {
            //普通账户转入
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getBonusAmount());
            values.add(vo.getBonusAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转出
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(otherEventCode);
            values.add(-vo.getAmount());
            values.add(sysVo.getBonusAmount());
            values.add(sysVo.getBonusAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //存入
        if (secondType.equals("000")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,2,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(sysVo.getBonusAmount());
            values.add(sysVo.getBonusAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //取出
        if (secondType.equals("100")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,OUT_ACCOUNT,EVENT_CODE,");
            sb.append("BONUS_AMOUNT,BONUS_AMOUNT_OLD,BONUS_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,3,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(sysVo.getBonusAmount());
            values.add(sysVo.getBonusAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        return idMap;
    }

    //红包转入转出
    public Map<String, Object> presentAccess(Customer sysVo, InternalAccessVO vo, Map<String,String> eventCodeMap) {
    	String eventCode=vo.getEventCode();
        String secondType = vo.getSecondType();
        Map<String, Object> idMap = new HashMap<String, Object>();
        Date date = new Date();
        String serialNo = formatter.format(date) + CommonUtil.random(6);
        String relateSerialNo = formatter.format(date) + CommonUtil.random(6);
        idMap.put("serialNo", serialNo);
        idMap.put("relateSerialNo", relateSerialNo);
        idMap.put("eventCode", eventCode);
        String otherEventCode=eventCodeMap.get(eventCode);
        idMap.put("otherEventCode", otherEventCode);
        idMap.put("secondType", secondType);
        //转出(由普通账户转出到系统账户)
        if (secondType.equals("101")) {
            //普通账户转出
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getPresentAmount());
            values.add(vo.getPresentAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转入
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(otherEventCode);
            values.add(vo.getAmount());
            values.add(sysVo.getPresentAmount());
            values.add(sysVo.getPresentAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //转入(由系统账户转入到普通账户)
        if (secondType.equals("001")) {
            //普通账户转入
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getPresentAmount());
            values.add(vo.getPresentAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转出
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(otherEventCode);
            values.add(-vo.getAmount());
            values.add(sysVo.getPresentAmount());
            values.add(sysVo.getPresentAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //存入
        if (secondType.equals("000")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,2,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getPresentAmount());
            values.add(vo.getPresentAmount() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //取出
        if (secondType.equals("100")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,OUT_ACCOUNT,EVENT_CODE,");
            sb.append("PRESENT_AMOUNT,PRESENT_AMOUNT_OLD,PRESENT_AMOUNT_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,3,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getPresentAmount());
            values.add(vo.getPresentAmount() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        return idMap;
    }

    //积分
    public Map<String, Object> scoreAccess(Customer sysVo, InternalAccessVO vo, Map<String,String> eventCodeMap) {
    	 String eventCode=vo.getEventCode();
         String secondType = vo.getSecondType();
        Map<String, Object> idMap = new HashMap<String, Object>();
        Date date = new Date();
        String serialNo = formatter.format(date) + CommonUtil.random(6);
        String relateSerialNo = formatter.format(date) + CommonUtil.random(6);
        idMap.put("serialNo", serialNo);
        idMap.put("relateSerialNo", relateSerialNo);
        idMap.put("eventCode", eventCode);
        String otherEventCode=eventCodeMap.get(eventCode);
        idMap.put("otherEventCode", otherEventCode);
        idMap.put("secondType", secondType);
        //转出(由普通账户转出到系统账户)
        if (secondType.equals("101")) {
            //普通账户转出
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append("SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getScore());
            values.add(vo.getScore() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转入
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(otherEventCode);
            values.add(vo.getAmount());
            values.add(sysVo.getScore());
            values.add(sysVo.getScore() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //转入(由系统账户转入到普通账户)
        if (secondType.equals("001")) {
            //普通账户转入
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getScore());
            values.add(vo.getScore() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转出
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(otherEventCode);
            values.add(-vo.getAmount());
            values.add(sysVo.getScore());
            values.add(sysVo.getScore() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //存入
        if (secondType.equals("000")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,2,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getScore());
            values.add(vo.getScore() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //取出
        if (secondType.equals("100")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,OUT_ACCOUNT,EVENT_CODE,");
            sb.append(" SCORE,SCORE_OLD,SCORE_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,3,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getScore());
            values.add(vo.getScore() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        return idMap;
    }

    //金币
    public Map<String, Object> goldAccess(Customer sysVo, InternalAccessVO vo,  Map<String,String> eventCodeMap) {
    	 String eventCode=vo.getEventCode();
         String secondType = vo.getSecondType();
        Map<String, Object> idMap = new HashMap<String, Object>();
        Date date = new Date();
        String serialNo = formatter.format(date) + CommonUtil.random(6);
        String relateSerialNo = formatter.format(date) + CommonUtil.random(6);
        idMap.put("serialNo", serialNo);
        idMap.put("relateSerialNo", relateSerialNo);
        idMap.put("eventCode", eventCode);
        String otherEventCode=eventCodeMap.get(eventCode);
        idMap.put("otherEventCode", otherEventCode);
        idMap.put("secondType", secondType);
        //转出(由普通账户转出到系统账户)
        if (secondType.equals("101")) {
            //普通账户转出
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getGold());
            values.add(vo.getGold() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转入
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(otherEventCode);
            values.add(vo.getAmount());
            values.add(sysVo.getGold());
            values.add(sysVo.getGold() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //转入(由系统账户转入到普通账户)
        if (secondType.equals("001")) {
            //普通账户转入
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,0,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(relateSerialNo);
            values.add(vo.getUserCode());
            values.add(vo.getAccount());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getGold());
            values.add(vo.getGold() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
            //系统账户转出
            sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,RELATE_SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,?,?,sysdate,1,0,?,?)");
            values = new ArrayList<Object>();
            values.add(relateSerialNo);
            values.add(serialNo);
            values.add(vo.getAccount());
            values.add(vo.getAccount());
            values.add(otherEventCode);
            values.add(vo.getUserCode());
            values.add(-vo.getAmount());
            values.add(sysVo.getGold());
            values.add(sysVo.getGold() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //存入
        if (secondType.equals("000")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,INTO_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,2,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(vo.getAmount());
            values.add(vo.getGold());
            values.add(vo.getGold() + vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        //取出
        if (secondType.equals("100")) {
            StringBuilder sb = new StringBuilder("insert into ACCOUNT_INTERNAL_ACCESS(ID,SERIAL_NO,USER_CODE,OUT_ACCOUNT,EVENT_CODE,");
            sb.append(" GOLD,GOLD_OLD,GOLD_NEW,CREATE_TIME,EVENT_TYPE,STATUS,MEMO,OPERATOR)");
            sb.append(" values(SEQ_ACCOUNT_INTERNAL_ACCESS.NEXTVAL,?,?,?,?,?,?,?,sysdate,3,0,?,?)");
            List<Object> values = new ArrayList<Object>();
            values.add(serialNo);
            values.add(vo.getUserCode());
            values.add(vo.getUserCode());
            values.add(eventCode);
            values.add(-vo.getAmount());
            values.add(vo.getGold());
            values.add(vo.getGold() - vo.getAmount());
            values.add(vo.getMemo());
            values.add(vo.getOperator());
            accountDao.executeSql(sb.toString(), values);
        }
        return idMap;
    }

    /**
     * 资金调整
     *
     * @param map
     * @return
     */
    public boolean amountAdjust(Map<String, Object> map) {
        String serialNo = (String) map.get("serialNo");
        String relateSerialNo = (String) map.get("relateSerialNo");
        String eventCode=(String) map.get("eventCode");
        String otherEventCode=(String) map.get("otherEventCode");
        String secondType=(String) map.get("secondType");
        StringBuilder str = new StringBuilder("select SERIAL_NO,USER_CODE,OUT_ACCOUNT,INTO_ACCOUNT,(CASE WHEN RECHARGE_AMOUNT <> 0  THEN 1  WHEN BONUS_AMOUNT <> 0 THEN 2 WHEN PRESENT_AMOUNT <> 0 THEN 3 WHEN SCORE <> 0 THEN 4 WHEN GOLD <> 0 THEN 5 END) type,");
        str.append("(nvl(RECHARGE_AMOUNT,0)+nvl(BONUS_AMOUNT,0)+nvl(PRESENT_AMOUNT,0)+nvl(SCORE,0))+nvl(GOLD,0) amount from ACCOUNT_INTERNAL_ACCESS where 1=1");
        str.append(" and SERIAL_NO = ?");
        List<Object> values = new ArrayList<Object>();
        if (CommonUtil.isNotEmpty(serialNo)) {
            values.add(serialNo);
            List<Map> adjustList = accountDao.findBySql(str.toString(), values);
            if (adjustList != null && adjustList.size() > 0) {
                Map adjustOrder = adjustList.get(0);
                boolean flag = adjustOperate(serialNo, eventCode,otherEventCode,secondType,adjustOrder);
                if (flag) {
                    updateStatus(serialNo,1);
                    if (secondType.equals("001")||secondType.equals("101")) {
                        if (CommonUtil.isNotEmpty(relateSerialNo)) {
                            updateStatus(relateSerialNo,1);
                        }
                    }
                }else{
                	 updateStatus(serialNo,2);
                     if (secondType.equals("001")||secondType.equals("101")) {
                         if (CommonUtil.isNotEmpty(relateSerialNo)) {
                             updateStatus(relateSerialNo,2);
                         }
                     }
                }
                return flag;
            }
        }
        return false;
    }

    public boolean adjustOperate(String serialNo,String eventCode,String otherEventCode,String secondType, Map adjustOrder) {
        boolean flag = false;
        //转入
        if (secondType.equals("001")) {
            TransferBean bean = new TransferBean();
            BigDecimal amount = (BigDecimal) adjustOrder.get("AMOUNT");
            bean.setAmount(amount.abs().doubleValue());
            bean.setInUserCode((String) adjustOrder.get("INTO_ACCOUNT"));
            bean.setOrderId((String) adjustOrder.get("SERIAL_NO"));
            bean.setOutUserCode((String) adjustOrder.get("OUT_ACCOUNT"));
            BigDecimal type = (BigDecimal) adjustOrder.get("TYPE");
            bean.setType(type.intValue());
            bean.setOutEventCode(otherEventCode);
            bean.setInEventCode(eventCode);
            bean.setDesc("-");
            flag = accountInterface.transferOperation(bean);
        }
        //转出
        if (secondType.equals("101")) {
            TransferBean bean = new TransferBean();
            BigDecimal amount = (BigDecimal) adjustOrder.get("AMOUNT");
            bean.setAmount(amount.abs().doubleValue());
            bean.setInUserCode((String) adjustOrder.get("INTO_ACCOUNT"));
            bean.setOrderId((String) adjustOrder.get("SERIAL_NO"));
            bean.setOutUserCode((String) adjustOrder.get("OUT_ACCOUNT"));
            BigDecimal type = (BigDecimal) adjustOrder.get("TYPE");
            bean.setType(type.intValue());
            bean.setOutEventCode(eventCode);
            bean.setInEventCode(otherEventCode);
            bean.setDesc("-");
            flag = accountInterface.transferOperation(bean);
        }
        //存入
        if (secondType.equals("000")) {
            DepositOrTellerBean bean = new DepositOrTellerBean();
            BigDecimal amount = (BigDecimal) adjustOrder.get("AMOUNT");
            bean.setAmount(amount.abs().doubleValue());
            bean.setUserCode((String) adjustOrder.get("USER_CODE"));
            bean.setOrderId((String) adjustOrder.get("SERIAL_NO"));
            BigDecimal type = (BigDecimal) adjustOrder.get("TYPE");
            bean.setAmountType(type.intValue());
            bean.setType(1);
            bean.setEventCode(eventCode);
            bean.setMemo(eventCode);
            flag = accountInterface.dpositOrTellerOperation(bean);
        }
        //取出
        if (secondType.equals("100")) {
            DepositOrTellerBean bean = new DepositOrTellerBean();
            BigDecimal amount = (BigDecimal) adjustOrder.get("AMOUNT");
            bean.setAmount(amount.abs().doubleValue());
            bean.setUserCode((String) adjustOrder.get("USER_CODE"));
            bean.setOrderId((String) adjustOrder.get("SERIAL_NO"));
            BigDecimal type = (BigDecimal) adjustOrder.get("TYPE");
            bean.setAmountType(type.intValue());
            bean.setType(2);
            bean.setEventCode(eventCode);
            bean.setMemo(eventCode);
            flag = accountInterface.dpositOrTellerOperation(bean);
        }
        return flag;
    }

    public void updateStatus(String serialNo,Integer status) {
        StringBuilder str = new StringBuilder("update ACCOUNT_INTERNAL_ACCESS set STATUS=?,ACCEPT_TIME=sysdate where SERIAL_NO= ?");
        List<Object> values = new ArrayList<Object>();
        values.add(status);
        values.add(serialNo);
        accountDao.executeSql(str.toString(), values);
    }
    
    /*public void deleteInternalAccess(String serialNo){
    	StringBuilder str = new StringBuilder("delete from  ACCOUNT_INTERNAL_ACCESS where SERIAL_NO= ?");
        List<Object> values = new ArrayList<Object>();
        values.add(serialNo);
        accountDao.executeSql(str.toString(), values);
    }*/

    /**
     * 内部存取数据查询
     */
    public EasyuiDataGridJson internalData(EasyuiDataGrid dg, InternalAccessVO account) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();

        String sql = "select u.USER_NAME,u.USER_CODE,"
                + " a.SERIAL_NO,a.RELATE_SERIAL_NO,(nvl(a.BONUS_AMOUNT,0) + nvl(a.RECHARGE_AMOUNT,0) + nvl(a.PRESENT_AMOUNT,0) +nvl(a.COMMISSION,0) + nvl(a.SCORE,0) + nvl(a.GOLD,0)) amount, "
                + " (CASE WHEN a.RECHARGE_AMOUNT <> 0  THEN '充值金'  WHEN a.BONUS_AMOUNT<>0 THEN '奖金' WHEN a.PRESENT_AMOUNT <>0 THEN '红包' WHEN a.COMMISSION <>0 THEN '佣金' WHEN a.SCORE <>0 THEN '积分' WHEN a.GOLD <>0 THEN '金币' END) account,"
                + " a.EVENT_CODE ,a.CREATE_TIME,a.ACCEPT_TIME,a.OPERATOR,a.MEMO ,a.STATUS  "
                + " from ACCOUNT_INTERNAL_ACCESS a left join  USER_MEMBER u on  a.USER_CODE=u.USER_CODE "
                + " where 1=1";
        List<Object> values = new ArrayList<Object>();
        if (account != null) {// 添加查询条件
            boolean flag = account.isFlag();
            //用户ID
            if (CommonUtil.isNotEmpty(account.getUserCode())) {
                if (flag) {
                    sql += " and u.USER_CODE like '%%" + account.getUserCode().trim() + "%%' ";

                } else {

                    sql += " and u.USER_CODE = ? ";
                    values.add(account.getUserCode().trim());
                }
            }
            //用户名
            if (CommonUtil.isNotEmpty(account.getUserName())) {
                if (flag) {
                    sql += " and u.USER_NAME like '%%" + account.getUserName().trim() + "%%' ";
                } else {

                    sql += " and u.USER_NAME = ?";
                    values.add(account.getUserName().trim());
                }
            }

            //编号
            if (CommonUtil.isNotEmpty(account.getSerialNo())) {
                if (flag) {
                    sql += " and a.SERIAL_NO like '%%" + account.getSerialNo() + "%%' ";
                } else {

                    sql += " and a.SERIAL_NO = ?";
                    values.add(account.getSerialNo());
                }
            }
            //订单编号
            if (CommonUtil.isNotEmpty(account.getRelateSerialNo())) {
                if (flag) {
                    sql += " and a.RELATE_SERIAL_NO like '%%" + account.getRelateSerialNo().trim() + "%%' ";
                } else {

                    sql += " and a.RELATE_SERIAL_NO = ?";
                    values.add(account.getRelateSerialNo().trim());
                }
            }
            //账户类型
            if (CommonUtil.isNotEmpty(account.getAccountType())) {
                //充值金
                if (account.getAccountType() == 0) {
                    sql += " and a.RECHARGE_AMOUNT <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.RECHARGE_AMOUNT >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.RECHARGE_AMOUNT <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //奖金
                if (account.getAccountType() == 1) {
                    sql += " and a.BONUS_AMOUNT <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.BONUS_AMOUNT >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.BONUS_AMOUNT <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //红包
                if (account.getAccountType() == 2) {
                    sql += " and a.PRESENT_AMOUNT <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.PRESENT_AMOUNT >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.PRESENT_AMOUNT <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //积分
                if (account.getAccountType() == 3) {
                    sql += " and a.SCORE <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.SCORE >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.SCORE <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //金币
                if (account.getAccountType() == 4) {
                    sql += " and a.GOLD <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.GOLD >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.GOLD <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //佣金
                if (account.getAccountType() == 5) {
                    sql += " and a.COMMISSION <> 0 ";
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        sql += " and a.COMMISSION >= ? ";
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        sql += " and a.COMMISSION <= ? ";
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
            }
            if(CommonUtil.isNotEmpty(account.getSecondType())){
            	//存入
            	if(account.getSecondType().equals("000")){
            		 sql += " and a.EVENT_TYPE = 2  ";
            	}
            	//转入
            	if(account.getSecondType().equals("001")){
           		 sql += " and a.EVENT_TYPE = 0  ";
           	    }
            	if(account.getSecondType().equals("100")){
              		 sql += " and a.EVENT_TYPE = 3  ";
              	    }
            	if(account.getSecondType().equals("101")){
             		 sql += " and a.EVENT_TYPE = 1  ";
             	    }
            }
            if (CommonUtil.isNotEmpty(account.getEventCode())) {
                sql += " and a.EVENT_CODE = ?  ";
                values.add(account.getEventCode());
            }
          /*  //调整金额最低
            if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                sql += " and (a.RECHARGE_AMOUNT >= " + account.getAdjustMin().doubleValue() + " or a.BONUS_AMOUNT>= " + account.getAdjustMin().doubleValue() + " or a.PRESENT_AMOUNT >=" + account.getAdjustMin().doubleValue() + " )";
            }
            //调整金额最高
            if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                sql += " and (a.RECHARGE_AMOUNT <= " + account.getAdjustMax().doubleValue() + " or a.BONUS_AMOUNT<= " + account.getAdjustMax().doubleValue() + " or a.PRESENT_AMOUNT <=" + account.getAdjustMax().doubleValue() + " )";
            }*/
            //交易状态
            if (CommonUtil.isNotEmpty(account.getStatus())) {
                sql += " and a.STATUS = ?";
                values.add(account.getStatus());
            }
            //备注说明
            if (CommonUtil.isNotEmpty(account.getMemo())) {
                if (flag) {
                    sql += " and a.MEMO like '%%" + account.getMemo().trim() + "%%' ";
                } else {

                    sql += " and a.MEMO = ?";
                    values.add(account.getMemo().trim());
                }
            }
            //操作员
            if (CommonUtil.isNotEmpty(account.getOperator())) {
                if (flag) {
                    sql += " and a.OPERATOR like '%%" + account.getOperator().trim() + "%%' ";
                } else {

                    sql += " and a.OPERATOR = ?";
                    values.add(account.getOperator().trim());
                }
            }
            //受理开始时间
            if (CommonUtil.isNotEmpty(account.getAcceptStart())) {
                sql += " and a.ACCEPT_TIME >= ?";
                values.add(DateUtil.format(account.getAcceptStart(),"yy-MM-dd HH:mm:ss"));
            }
            //受理结束时间
            if (CommonUtil.isNotEmpty(account.getAcceptEnd())) {
                sql += " and a.ACCEPT_TIME <= ?";
                values.add(DateUtil.format(account.getAcceptEnd(),"yy-MM-dd HH:mm:ss"));
            }
        }
        String totalHql = " select count(*)  from (" + sql + ")";
        j.setTotal(accountDao.countBySql(totalHql, values).longValue());// 设置总记录数

        if (dg.getSort() != null) {// 设置排序
            String sort = "";
            if (dg.getSort().toString().equalsIgnoreCase("acceptTime")) {
                sort = "a.ACCEPT_TIME";
            }
            if (dg.getSort().toString().equalsIgnoreCase("amount")) {
                sort = "amount";
            }

            if (!sort.equals("")) {
                sql += " order by " + sort + " " + dg.getOrder();
            }
        }
        List<Map> accountMaps = accountDao.findBySql(sql, values, dg.getPage(), dg.getRows());// 查询分页
        List<InternalAccessVO> accounts = new ArrayList<InternalAccessVO>();
        if (accountMaps != null && accountMaps.size() > 0) {// 转换模型
            for (Map map : accountMaps) {
                InternalAccessVO a = new InternalAccessVO();
                a.setSerialNo((String) map.get("SERIAL_NO"));
                a.setRelateSerialNo((String) map.get("RELATE_SERIAL_NO"));
                a.setUserCode((String) map.get("USER_CODE"));
                a.setUserName((String) map.get("USER_NAME"));
                BigDecimal amount = map.get("AMOUNT") == null ? new BigDecimal(0) : (BigDecimal) map.get("AMOUNT");
                a.setAmount(amount.doubleValue());
                a.setAccount((String) map.get("ACCOUNT"));
                a.setEventCode((String)map.get("EVENT_CODE"));
                Date acceptTime = (Date) map.get("ACCEPT_TIME");
                a.setAcceptTime(DateUtil.format(acceptTime));
                a.setOperator((String) map.get("OPERATOR"));
                a.setMemo((String) map.get("MEMO"));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                a.setStatus(status.intValue());
                accounts.add(a);
            }
        }
        j.setRows(accounts);// 设置返回的行
        return j;
    }
    /**
     * 内部存取汇总
     * @param account
     * @return
     */
    public Map internalCount(InternalAccessVO account){
    	StringBuilder str=new StringBuilder("select sum(a.recharge_amount) recharge_amount, sum(a.bonus_amount) bonus_amount,");
        str.append(" sum(a.present_amount) present_amount,sum(a.score) score,sum(a.gold) gold from ACCOUNT_INTERNAL_ACCESS a  left join user_member u  on a.user_code = u.user_code ");
        str.append(" where 1=1");
        List<Object> values = new ArrayList<Object>();
        if (account != null) {// 添加查询条件
            boolean flag = account.isFlag();
            // 用户编号
 			if (CommonUtil.isNotEmpty(account.getUserCode())) {
 				if (flag) {
 					str.append(" and u.user_code like ? ");
 					values.add("%" + account.getUserCode().trim() + "%");
 				} else {
 					str.append(" and u.user_code = ? ");
 					values.add(account.getUserCode().trim());
 				}
 			}
            //用户名
            if (CommonUtil.isNotEmpty(account.getUserName())) {
                if (flag) {
                    str.append(" and u.USER_NAME like ? ");
                    values.add("%" + account.getUserName().trim() + "%");
                } else {

                    str.append(" and u.USER_NAME = ?");
                    values.add(account.getUserName().trim());
                }
            }

            //编号
            if (CommonUtil.isNotEmpty(account.getSerialNo())) {
                if (flag) {
                    str.append(" and a.SERIAL_NO like ? ");
                    values.add("%" + account.getSerialNo().trim() + "%");
                } else {

                    str.append(" and a.SERIAL_NO = ?");
                    values.add(account.getSerialNo());
                }
            }
            //订单编号
            if (CommonUtil.isNotEmpty(account.getRelateSerialNo())) {
                if (flag) {
                    str.append(" and a.RELATE_SERIAL_NO like ? ");
                    values.add("%" + account.getRelateSerialNo().trim() + "%");
                } else {

                    str.append(" and a.RELATE_SERIAL_NO = ?");
                    values.add(account.getRelateSerialNo().trim());
                }
            }
            //账户类型
            if (CommonUtil.isNotEmpty(account.getAccountType())) {
                //充值金
                if (account.getAccountType() == 0) {
                    str.append(" and a.RECHARGE_AMOUNT <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.RECHARGE_AMOUNT >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.RECHARGE_AMOUNT <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //奖金
                if (account.getAccountType() == 1) {
                    str.append(" and a.BONUS_AMOUNT <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.BONUS_AMOUNT >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.BONUS_AMOUNT <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //红包
                if (account.getAccountType() == 2) {
                    str.append(" and a.PRESENT_AMOUNT <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.PRESENT_AMOUNT >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.PRESENT_AMOUNT <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //积分
                if (account.getAccountType() == 3) {
                    str.append(" and a.SCORE <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.SCORE >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.SCORE <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //金币
                if (account.getAccountType() == 4) {
                    str.append(" and a.GOLD <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.GOLD >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.GOLD <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
                //佣金
                if (account.getAccountType() == 5) {
                    str.append(" and a.COMMISSION <> 0 ");
                    if (CommonUtil.isNotEmpty(account.getAdjustMin())) {

                        str.append(" and a.COMMISSION >= ? ");
                        values.add(account.getAdjustMin().doubleValue());
                    }
                    
                    if (CommonUtil.isNotEmpty(account.getAdjustMax())) {
                        str.append(" and a.COMMISSION <= ? ");
                        values.add(account.getAdjustMax().doubleValue());
                    }
                }
            }
            if(CommonUtil.isNotEmpty(account.getSecondType())){
            	//存入
            	if(account.getSecondType().equals("000")){
            		 str.append(" and a.EVENT_TYPE = 2  ");
            	}
            	//转入
            	if(account.getSecondType().equals("001")){
           		 str.append(" and a.EVENT_TYPE = 0  ");
           	    }
            	if(account.getSecondType().equals("100")){
              		 str.append(" and a.EVENT_TYPE = 3  ");
              	    }
            	if(account.getSecondType().equals("101")){
             		 str.append(" and a.EVENT_TYPE = 1  ");
             	    }
            }
            if (CommonUtil.isNotEmpty(account.getEventCode())) {
                str.append(" and a.EVENT_CODE = ?  ");
                values.add(account.getEventCode());
            }
            //交易状态
            if (CommonUtil.isNotEmpty(account.getStatus())) {
                str.append(" and a.STATUS = ?");
                values.add(account.getStatus());
            }

            //备注说明
            if (CommonUtil.isNotEmpty(account.getMemo())) {
                if (flag) {
                    str.append(" and a.MEMO like ? ");
                    values.add("%" + account.getMemo().trim() + "%");
                } else {

                    str.append(" and a.MEMO = ?");
                    values.add(account.getMemo().trim());
                }
            }
            //操作员
            if (CommonUtil.isNotEmpty(account.getOperator())) {
                if (flag) {
                    str.append(" and a.OPERATOR like ? ");
                    values.add("%" + account.getOperator().trim() + "%");
                } else {

                    str.append(" and a.OPERATOR = ?");
                    values.add(account.getOperator().trim());
                }
            }
            //受理开始时间
            if (CommonUtil.isNotEmpty(account.getAcceptStart())) {
                str.append(" and a.ACCEPT_TIME >= ?");
                values.add(DateUtil.format(account.getAcceptStart(),"yy-MM-dd HH:mm:ss"));
            }
            //受理结束时间
            if (CommonUtil.isNotEmpty(account.getAcceptEnd())) {
                str.append(" and a.ACCEPT_TIME <= ?");
                values.add(DateUtil.format(account.getAcceptEnd(),"yy-MM-dd HH:mm:ss"));
            }
        }
        List<Map> mapList = accountDao.findBySql(str.toString(), values);
        if (CommonUtil.isNotEmpty(mapList)) {
            return mapList.get(0);
        }
        return null;
    }
    
}
