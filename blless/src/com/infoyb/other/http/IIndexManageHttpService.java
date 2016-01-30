package com.cm.other.http;


import com.cm.other.http.bean.*;

import java.util.List;

public interface IIndexManageHttpService {

    /**
     * 栏目列表
     *
     * @return
     */
    public List<ColumnBean> listColumn();

    /**
     * 编辑栏目
     *
     * @param columnBean 栏目对象
     * @return
     */
    public boolean editColumn(ColumnBean columnBean);

    /**
     * 编辑栏目状态
     *
     * @param columnCode 栏目code
     * @param status     状态
     * @return
     */
    public boolean editColumnStatus(String columnCode, Integer status);


    /**
     * 条目列表
     *
     * @return
     */
    public List<ItemDetailBean> listItem();


    /**
     * 条目详情
     * @param itemCode
     * @return
     */
    public ItemDetailBean detailItem(String itemCode);

    /**
     * 编辑条目
     *
     * @param itemBean 条目对象
     * @return
     */
    public boolean editItem(ItemBean itemBean);

    /**
     * 编辑条目状态
     *
     * @param itemCode 条目code
     * @param status   状态
     * @return
     */
    public boolean editItemStatus(String itemCode, Integer status);

    /**
     * 模板列表
     *
     * @return
     */
    public List<TemplateBean> listTemplate(String itemCode);

    /**
     * 删除模板
     *
     * @param id
     * @return
     */
    public void removeTemplate(Long id);


    /**
     * 编辑模板
     *
     * @param templateBean
     * @return
     */
    public boolean editTemplate(TemplateBean templateBean);

    /**
     * 乐透大奖
     *
     * @param lotteryCode 彩种编码
     * @param bonusAmount 奖池
     * @return
     */
    public boolean lotBonus(String lotteryCode, Double bonusAmount);
    
    /**
     * 取消乐透大奖
     * @param lotteryCode 彩种编码
     * @return
     */
    public boolean cancelLotBonus(String lotteryCode);


    /**
     * 合买达人
     *
     * @param programsOrderId 方案号
     * @param lotteryCode     彩种编码
     * @param issue           彩期
     * @param desc            合买描述
     * @return
     */
    public boolean topBuy(String programsOrderId, String lotteryCode, String issue, String desc);

    /**
     * 焦点赛事
     *
     * @param matchNo   赛事编号
     * @param mainTeam  主队
     * @param guestTeam 客队
     * @return
     */
    public boolean topMatch(String matchNo, String mainTeam, String guestTeam);

    /**
     * 取消赛事推荐
     *
     * @param matchNo 赛事编号
     * @return
     */
    public boolean cancelTopMatch(String matchNo);

    /**
     * 可用的内容列表
     *
     * @param itemCode 条目编码
     * @return
     */
    public List<ContentBean> getContentList(String itemCode);

    /**
     * 天天加奖
     *
     * @param lotteryCode 彩种编码
     * @param content     内容
     * @return
     */
    public boolean addBonus(String lotteryCode, String content);

    /**
     * 取消天天加奖
     *
     * @param id
     * @return
     */
    public boolean cancelAddBonus(Long id);

    /**
     * 幸运号码
     * @return
     */
    public boolean luckNumber(String lotteryCode);

    /**
     * 取消幸运号码
     * @return
     */
    public boolean cancelLuckNumber(Long id);
    
    /**
     * 取消合买
     * @param programsOrderId
     * @return
     */
    public boolean cancelTopBuy(String programsOrderId);

}
