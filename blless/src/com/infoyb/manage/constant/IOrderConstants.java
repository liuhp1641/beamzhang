package com.cm.manage.constant;

/**
 * 常量
 */
public interface IOrderConstants {

    // 平台
    public static final String PLATFORM_ANDROID = "01";//android
    public static final String PLATFORM_IOS = "02";//ios
    public static final String PLATFORM_HTML5 = "03";//h5
    public static final String PLATFORM_IPAD = "04";//ipad

    //彩种状态
    public static final int LOTTERY_STATUS_NORMAL = 0; //正常
    public static final int LOTTERY_STATUS_PAUSE = 1; //暂停
    public static final int LOTTERY_STATUS_HIDE = 2; //隐藏
    public static final int LOTTERY_STATUS_UPDATE = 3; //更新


    // 期次相关状态
    public static final int ISSUE_STATUS_WAIT = 0;// 默认期次状态/预约/追号
    public static final int ISSUE_STATUS_START = 1;// 开售，前台当前期
    public static final int ISSUE_STATUS_PAUSE = 2;// 暂停
    public static final int ISSUE_STATUS_END = 3;// 结期

    public static final int ISSUE_SALE_WAIT = 0;// 等待出票
    public static final int ISSUE_SALE_SEND = 1;// 出票

    public static final int ISSUE_PROGRAMS_HANDLE_WAIT = 0;// 方案等待处理
    public static final int ISSUE_PROGRAMS_HANDLE_END = 1;// 方案已处理

    // 追号方案状态
    public static final int AUTO_ORDER_STATUS_DOING = 0;// 进行中
    public static final int AUTO_ORDER_STATUS_COMPLETE = 1;//已完成
    public static final int AUTO_ORDER_STATUS_ADM_CANCEL = 2;//管理员撤单
    public static final int AUTO_ORDER_STATUS_BONUS_COMPLETE = 3;//中奖停追
    public static final int AUTO_ORDER_STATUS_FAILURE = 4;// 投注失败
    public static final int AUTO_ORDER_STATUS_SYS_CANCEL = 5;// 系统撤单
    public static final int AUTO_ORDER_STATUS_USER_CANCEL = 6;// 用户撤单


    // 方案状态
    public static final int PROGRAMS_STATUS_DEFAULT = -1;// 入库状态
    public static final int PROGRAMS_STATUS_WAIT = 0;// 等待成交
    public static final int PROGRAMS_STATUS_SUCCESS = 1;// 投注成功
    public static final int PROGRAMS_STATUS_SOME_FAILURE = 2;//部分成交
    public static final int PROGRAMS_STATUS_FAILURE = 3;// 投注失败
    public static final int PROGRAMS_STATUS_SYS_CANCEL = 4;// 系统取消
    public static final int PROGRAMS_STATUS_TIME_OUT = 5;// 流单
    public static final int PROGRAMS_STATUS_ADM_CANCEL = 6;// 人工取消
    public static final int PROGRAMS_STATUS_USER_CANCEL = 7;// 用户取消

    // 取消模式
    public static final String OPERATOR_FROM_SYS = "01";// 系统
    public static final String OPERATOR_FROM_USER = "02";// 用户
    public static final String OPERATOR_FROM_ADM = "03";// 管理员

    // 订单状态
    public static final int ORDER_STATUS_NORMAL = 0;// 正常
    public static final int ORDER_STATUS_SYS_CANCEL = 1;// 系统取消
    public static final int ORDER_STATUS_ADM_CANCEL = 2;// 人工取消
    public static final int ORDER_STATUS_USER_CANCEL = 3;// 用户取消


    // 方案置顶
    public static final int PROGRAMS_TOP_YES = 1;// 已置顶
    public static final int PROGRAMS_TOP_NO = 0;// 未置顶

    public static final int PROGRAMS_HIDE_YES = 1;//隐藏
    public static final int PROGRAMS_HIDE_NO = 0;//不隐藏

    public static final int PROGRAMS_VERIFY_YES = 1;//审核
    public static final int PROGRAMS_VERIFY_NO = 0;//未审核


    // 方案发单状态
    public static final int PROGRAMS_SEND_WAIT = 0;// 等待发单
    public static final int PROGRAMS_SEND_DOING = 1;// 已发单

    // 中奖状态
    public static final int BONUS_STATUS_WAIT = 0;// 等待开奖
    public static final int BONUS_STATUS_YES = 1;// 中奖
    public static final int BONUS_STATUS_NO = 2;// 未中奖

    // 是否派奖
    public static final int BONUS_TO_ACCOUNT_NO = 0;// 未派奖
    public static final int BONUS_TO_ACCOUNT_YES = 1;// 已派奖
    public static final int BONUS_TO_ACCOUNT_DOING = 2;// 派奖中


    public static final int BONUS_STOP_NO = 0;// 中奖后不停止
    public static final int BONUS_STOP_YES = 1;// 中奖后停止
    public static final int BONUS_STOP_AMOUNT = 3;// 中奖到多少金额停止

    // 手工返奖状态
    public static final int HANDWORK_BONUS_WAIT = 0;// 等待
    public static final int HANDWORK_BONUS_SUCCESS = 1;// 已返
    public static final int HANDWORK_BONUS_CANCEL = 2;// 取消
    public static final int HANDWORK_BONUS_NOT = 3;// 线下派奖

    // 返奖状态
    public static final int ISSUE_BONUS_STATUS_NO = 0;// 未返奖
    public static final int ISSUE_BONUS_STATUS_YES = 1;// 已返奖

    // 购买类型
    public static final int BUY_TYPE_DAI_GOU = 1;// 代购
    public static final int BUY_TYPE_HUO_MAI = 2;// 合买
    public static final int BUY_TYPE_ZHUI_HAO = 4;// 追号

    // 订单类型
    public static final int ORDER_TYPE_PT = 0;// 代购
    public static final int ORDER_TYPE_FQHM = 1;// 发起合买认购
    public static final int ORDER_TYPE_ZDGD = 2;// 自动跟单
    public static final int ORDER_TYPE_HMRG = 3;// 合买认购
    public static final int ORDER_TYPE_BD = 4;// 保底转认购
    public static final int ORDER_TYPE_XTBD = 5;// 系统保底
    public static final int ORDER_TYPE_ZH = 8;// 追号

    // 票状态
    public static final int TICKET_STATUS_WAIT = 1;// 需要程序查询出来
    public static final int TICKET_STATUS_SENDING = 2;// 送票未回执
    public static final int TICKET_STATUS_SUCCESS = 3;// 出票成功
    public static final int TICKET_STATUS_FAILURE = 4;// 出票失败
    public static final int TICKET_STATUS_CANCEL = 5;// 取消出票
    public static final int TICKET_STATUS_TIME_OUT = 6;// 流单
    public static final int TICKET_STATUS_LIMIT = 7;// 限号失败

    // 算奖状态
    public static final int OPERATORS_AWARD_WAIT = 0;
    public static final int OPERATORS_AWARD_DOING = 1;
    public static final int OPERATORS_AWARD_COMPLETE = 2;

    // 赛果录入状态
    public static final int INPUT_AWARD_NO = 0;
    public static final int INPUT_AWARD_YES = 1;
    
    public static final int SUB_ISSUE_END_OPERATOR_DEFAULT = 0;// 默认状态 销售中
    public static final int SUB_ISSUE_END_OPERATOR_END = 1;// 结期处理
    public static final int SUB_ISSUE_END_OPERATOR_CANCEL = 2;// 中途取消
    
 // 竟彩混合串关支持玩法
    public static String JC_MIXPLAY_SUPPORT_PLAY_CODES = "[01][02][03][04][05]";
    // 竟彩混串玩法编号
    public static String JC_MIXPLAY_PLAYCODE = "10";

    public static final int SUB_ISSUE_STATUS_WAIT = 0; //未开赛
    public static final int SUB_ISSUE_STATUS_STARTING = 1; //比赛中
    public static final int SUB_ISSUE_STATUS_END = 2; //已结束
    public static final int SUB_ISSUE_STATUS_CANCEL = 3; //已取消


}
