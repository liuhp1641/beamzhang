package com.cm.order.http;

import com.cm.order.http.bean.TicketCenterBean;

import java.util.Date;
import java.util.List;

public interface IOrderManageHttpService {

    /**
     * 出票口余额
     *
     * @return
     */
    public List<TicketCenterBean> ticketCenterBalance();

    /**
     * 撤单
     *
     * @param programsList 方案号列表
     * @return
     */
    public int orderCancel(List<String> programsList, Integer buyType);

    /**
     * 手工派奖
     * @param programsOrderId 方案编号
     * @param operator 管理员
     * @return
     */
    public int handBonus(String programsOrderId, String operator);

    /**
     * 批量添加期次
     * @param lotteryCode 彩种编号
     * @param dateStr 开始日期 yyyy-MM-dd
     * @param day   天数
     * @return
     */
    public int doBatchSaveIssue(String lotteryCode,String dateStr,Integer day);


    /**
     * @param ticketList
     * @return
     */
    public int sendTicketByAdm(List<String> ticketList);

    /**
     * 修改竞彩足球赛事时间
     * @param issue 期次
     * @param sn sn
     * @param endTime 赛事时间
     * @return
     */
    public boolean editFootballMatchTime(String issue, String sn, Date endTime);


    /**
     * 取消竞彩足球赛事
     *
     * @param issue
     * @param sn
     * @return
     */
    public boolean cancelFootballMatch(String issue, String sn);

    /**
     * 隐藏竞彩足球赛事
     *
     * @param issue
     * @param sn
     * @return
     */
    public boolean hiddenFootballMatch(String issue, String sn, String backup2);
}
