package com.cm.manage.constant;


public class CommonConstants {
    /**
     * 业务状态类
     */
    //提现记录状态
    public static final int DRAW_WAIT_REVIEW = 0;//待审核
    public static final int DRAW_HAS_REVIEW = 1;//已审核
    public static final int DRAW_FAIL = 2;//失败
    public static final int DRAW_TRANSFERING = 3;//处理中
    public static final int DRAW_SUCCESS = 4;
    public static final int DRAW_HAS_REJECT = 5;//被驳回
    //批次转账处理状态
    public static final int Batch_TRANSFER_SUCCESS = 1;//成功
    public static final int Batch_TRANSFER_FAIL = 2;//失败
    public static final int Batch_TRANSFER_DOING = 3;//处理中
    //任务状态类
    public static final int TASK_STATUS_NEW = 0;//待发布
    public static final int TASK_STATUS_START = 1;//已上线
    public static final int TASK_STATUS_STOP = 2;//已暂停
    public static final int TASK_STATUS_END = 3;//已结束
    //活动状态
    public static final int ACTIVE_STATUS_NEW = 0;//待发布
    public static final int ACTIVE_STATUS_START = 1;//已上线
    public static final int ACTIVE_STATUS_STOP = 2;//已暂停
    public static final int ACTIVE_STATUS_END = 3;//已结束
    //活动方案状态
    public static final int ACTIVE_ITEM_STATUS_NEW = 0;//待发布
    public static final int ACTIVE_ITEM_STATUS_START = 1;//已上线
    public static final int ACTIVE_ITEM_STATUS_STOP = 2;//已暂停
    public static final int ACTIVE_ITEM_STATUS_END = 3;//已结束
    //活动返奖状态
    public static final int ACTIVE_RETURN_STATUS_NO = 0;//未返
    public static final int ACTIVE_RETURN_STATUS_YES = 0;//已返奖
    
    /**
     * 数据标志类
     */
    //任务完成条件表中信息校验验证项标志
    public static final int COMPLETE_FLAG_NO = 0;//无校验项
    public static final int COMPLETE_FLAG_PHONE = 1;//手机号
    public static final int COMPLETE_FLAG_NAME = 2;//姓名
    public static final int COMPLETE_FLAG_IDCARD = 3;//身份证
    public static final int COMPLETE_FLAG_VIP = 4;//VIP
    //前置任务ID不限制标志
    public static final String PREFIX_TASK_NOT_EXSIT = "00000";
    //彩种不限制标志
    public static final String LOTTERY_NO_LIMIT_FLAG = "00000";
    //任务中渠道无限制标志
    public static final String CHANNEL_NO_LIMIT_FLAG = "00000";
    //订单购买方式
    public static final int ORDER_BYETYPE_ALL = 0;//全部
    public static final int ORDER_BYETYPE_PURCHASE = 1;//代买
    public static final int ORDER_BYETYPE_TOGETHER = 2;//合买
    public static final int ORDER_BYETYPE_CHASE = 3;//追号
    //前置任务组合类型
    public static final int PREFIX_TASK_EXCLUSION_FLAG = 1;//互斥
    public static final int PREFIX_TASK_INCLUSION_FLAG = 0;//组合
    //是否是首次不中奖标志
    public static final int FIRST_NO_LOTTERY_YES_FLAG = 1;//首次不中奖
    public static final int FIRST_NO_LOTTERY_NO_FLAG = 0;//非首次不中奖
    //任务条件类型
    public static final int TASK_CONDITION_NOT_EXSIT = 0;//无
    public static final int TASK_CONDITION_CONSUME_FLAG = 1;//消费
    public static final int TASK_CONDITION_RECHARGE_FLAG = 2;//充值
    public static final int TASK_CONDITION_LOTTERY_FLAG = 3;//中奖
    public static final int TASK_CONDITION_PERFECTINFO_FLAG = 4;//完善用户信息
    public static final int TASK_CONDITION_NO_LOTTERY_FLAG = 5;//不中奖
    //任务类型
    public static final int TASK_TYPE_CONDITION_FLAG = 0;//条件型
    public static final int TASK_TYPE_ASSOCATION_FLAG = 1;//关联型
    public static final int TASK_TYPE_PROMOTION_FLAG = 2;//促销型
    //任务进度条是否显示
    public static final int TASK_PROGRESS_HIDE_FLAG = 0;//不显示
    public static final int TASK_PROGRESS_SHOW_FLAG = 1;//显示
    //任务显示条件
    public static final int TASK_SHOW_AND_DO = 0;//可见可做
    public static final int TASK_SHOW_NOT_DO = 1;//可见不可做
    public static final int TASK_NOT_SHOW_NOT_DO = 2;//不可见不可做
    //任务默认排序
    public static final int TASK_ORDER_DEFAULT_FLAG = 1;//任务默认排序第一
    //任务默认领取次数
    public static final int TASK_RECEIVETIMES_DEFAULT_FLAG = 1;//任务默认领取次数1次
    //任务默认无金额限制
    public static final double TASK_AMOUNT_NOT_LIMIT_FLAG = 0.0;
    //任务默认无次数限制
    public static final int TASK_TIMES_NOT_LIMIT_FLAG = 0;
    //任务的前置条件是否存在
    public static final int TASK_HAS_NO_PREFIX_FLAG = 0;//任务前置条件无效时，即当显示条件为可见可做时
    public static final int TASK_HAS_PREFIX_FLAG = 1;//任务前置条件有效,即当显示条件为可见不可做/不可见不可做
    //任务奖励资金类型
    public static final int FUNDINGTYPE_PRESIENT_FLAG = 0;//红包
    public static final int FUNDINGTYPE_SCORE_FLAG = 1;//积分
    public static final int FUNDINGTYPE_GOLD_FLAG = 2;//金币
    //任务奖励资金名称
    public static final String FUNDINGTYPE_PRESIENT_NAME = "红包";
    public static final String FUNDINGTYPE_SCORE_NAME = "积分";
    public static final String FUNDINGTYPE_GOLD_NAME = "金币";
    //任务奖励类型
    public static final int TASK_REWARD_FUNDING_FLAG = 0;//资金奖励
    public static final int TASK_REWARD_HONOR_FLAG = 1;//荣誉奖励
    public static final int TASK_REWARD_VIP_FLAG = 2;//VIP奖励
    public static final int TASK_REWARD_PROPS_FLAG = 3;//道具奖励
    //VIP等级标志
    public static final int USER_VIP_RANK_ZERO = 0;//普通用户
    public static final int USER_VIP_RANK_FIRST = 1;//vip 1级
    public static final int USER_VIP_RANK_SECOND = 2;
    public static final int USER_VIP_RANK_THREE = 3;
    public static final int USER_VIP_RANK_FOUR = 4;
    public static final int USER_VIP_RANK_FIVE = 5;
    public static final int USER_VIP_RANK_SIX = 6;
    public static final int USER_VIP_RANK_SEVEN = 7;
    public static final int USER_VIP_RANK_EIGHT = 8;
    public static final int USER_VIP_RANK_NINE = 9;
    //提现中对公对私标志
    public static final String DRAW_TO_PUBLIC_FLAG = "1";//对公
    public static final String DRAW_TO_PERSONAL_FLAG = "2";//对私
    //提现账户绑定标志
    public static final int DRAW_ACCOUNT_HAS_BINDDING = 1;//已绑定
    public static final int DRAW_ACCOUNT_NO_BINDDING = 0;//未绑定
    //任务操作类型
    public static final String TASK_OPERATE_NEW = "new";//新建任务
    public static final String TASK_OPERATE_STOP = "stop";//暂停任务
    public static final String TASK_OPERATE_RECOVER = "recover";//恢复任务
    public static final String TASK_OPERATE_END = "end";//结束任务
    //提现操作类型
    public static final String DRAW_OPERATE_REVERIFY = "reverify";//重新审核
    //转账文件csv后缀
    public static final String TRANSFER_FILE_CSV_SUFFIX = ".csv";
    public static final String PAYMENT_RESULTFILE_ZIP_SUFFIX = ".zip";
    //支付宝接口返回结果F失败T成功
    public static final String ALIPAY_RESULT_SUCCESS = "T";//成功
    public static final String ALIPAY_RESULT_FAIL = "F";//失败
    //支付宝交易处理状态
    public static final String ALIPAY_TRNS_SUCCESS = "SUCCESS";//成功
    public static final String ALIPAY_TRNS_DISUSE = "DISUSE";//废除
    public static final String ALIPAY_TRNS_FAIL = "FAIL";//失败
    public static final String ALIPAY_TRNS_INIT = "INIT";//未处理
    public static final String ALIPAY_TRNS_DEALED = "DEALED";//处理中
    //支付宝异步通知类型
    public static final String ALIPAY_BPTB_RESULT_FILE = "bptb_result_file";//文件处理结果通知
    public static final String ALIPAY_BPTB_UNFREEZE_FILE = "bptb_unfreeze_file";//批次文件余额不足通知
    //支付宝批量代发类型
    public static final String BUSSINESS_TYPE_T0 = "T0";//当天到账
    public static final String BUSSINESS_TYPE_T1 = "T1";//隔天到账
    
    //活动是否分期 0不分期 1分期
    public static final int ACTIVE_RETURNTYPE_NO = 0;
    public static final int ACTIVE_RETURNTYPE_YES = 1;
    //活动资金类型
    public static final int ACTIVE_FUNDINGTYPE_CASH_FLAG = 0;//现金
    public static final int ACTIVE_FUNDINGTYPE_PRESIENT_FLAG = 1;//红包
    public static final int ACTIVE_FUNDINGTYPE_SCORE_FLAG = 2;//积分
    public static final int ACTIVE_FUNDINGTYPE_GOLD_FLAG = 3;//金币    
    
    /**
     * 校验信息类
     */
    //是否需要校验标志
    public static final int VALID_YES_FLAG = 1;//需要校验
    public static final int VALID_NO_FLAG = 0;//无需校验


    //操作日志类型
    public static final int LOG_FOR_LOGIN = 0;  //登录退出
    public static final int LOG_FOR_MEMBER = 1;  //用户管理
    public static final int LOG_FOR_ACCOUNT = 2;  //资金管理
    public static final int LOG_FOR_LOTTERY = 3;  //彩种管理
    public static final int LOG_FOR_PAY_COOP = 4;  //支付渠道
    public static final int LOG_FOR_TASK = 5;  //任务管理
    public static final int LOG_FOR_EDIT_ISSUE = 6;  //录入期次
    public static final int LOG_FOR_CANCEL_ORDER = 7;  //撤单
    public static final int LOG_FOR_SEND_TICKET = 8;  //传票处理
    public static final int LOG_FOR_SMS = 9;  //短信管理
    public static final int LOG_FOR_MESSAGE = 10;  //客服管理
    public static final int LOG_FOR_INFO = 11;  //咨询管理
    public static final int LOG_FOR_SECURITY = 12;  //系统管理(管理员 角色 资源 menu 权限分配)
    public static final int LOG_FOR_WEIGHT = 13;  //权重分配
    public static final int LOG_FOR_SEND_WIN = 14;  //派奖管理
    public static final int LOG_FOR_ORDER = 15;  //订单管理
    public static final int LOG_FOR_ACTIVE = 16;  //活动管理
    public static final int LOG_FOR_SCORE_QUTZ = 17;  //积分竞猜管理
    public static final int LOG_FOR_SOFTWARE_VERSION = 18;  //软件版本管理
    
    public static final int LOG_FOR_SUBISSUE_JCZQ = 19;  //竞彩足球
    
    public static final int LOG_FOR_MATCH_FOR_MAPPING = 20;  //赛事
    public static final int LOG_FOR_SCORE_MALL = 21;//积分商城
    
    public static final int LOG_FOR_REBATE = 22;//购彩返利
    public static final int LOG_FOR_ISSUE = 23;//奖期管理
    public static final int LOG_FOR_INDEX_MANAGE = 24;//首页管理
    
    public static final int LOG_FOR_SHARE = 25;//分享管理
    
    public static final int LOG_FOR_GAMEROOM = 26;

}
