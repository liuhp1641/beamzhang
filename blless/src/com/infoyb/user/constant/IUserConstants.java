package com.cm.user.constant;

/**
 * 常量
 */
public interface IUserConstants {

    // 平台
    public static final String PLATFORM_ANDROID = "01";//android
    public static final String PLATFORM_IOS = "02";//ios
    public static final String PLATFORM_HTML5 = "03";//h5
    public static final String PLATFORM_IPAD = "04";//ipad


    //用户状态
    public static final int MEMBER_STATUS_LIVE = 0;//活动
    public static final int MEMBER_STATUS_LOCK = 1;//锁定
    public static final int MEMBER_STATUS_CANCEL = 2;//注销

    //用户类型
    public static final int MEMBER_TYPE_NORMAL = 0;//普通
    public static final int MEMBER_TYPE_SYSTEM = 1;//系统


    //注册方式
    public static final int MEMBER_REGISTER_NAME = 0; //用户名
    public static final int MEMBER_REGISTER_MOBILE = 1;//手机号
    public static final int MEMBER_REGISTER_UNION = 2;//联合登录

    //联合登录方式
    public static final int MEMBER_UNION_LOGIN_NOMAL = 0; //正常
    public static final int MEMBER_UNION_LOGIN_ALIPAY = 1;//支付宝
    public static final int MEMBER_UNION_LOGIN_QQ = 2;//QQ
    public static final int MEMBER_UNION_LOGIN_WX = 3;//微信

    //登录状态
    public static final int MEMBER_LOGIN_SUCCESS = 0; //成功
    public static final int MEMBER_LOGIN_FAILURE = 1;//失败

    //用户锁定原因
    public static final String MEMBER_LOCK_PASSWORD_ERR = "01"; //密码错误

    //验证码类型
    public static final int TEST_CODE_FOR_REGISTER = 0; //注册验证码
    public static final int TEST_CODE_FOR_BIND_MOBILE = 1;//绑定手机
    public static final int TEST_CODE_FOR_FORGET_PASSWORD = 2;//找回密码

    //短信发送
    public static final Integer SEND_SMS_WAIT = 0;
    public static final Integer SEND_SMS_SUCCESS = 1;
    public static final Integer SEND_SMS_FAILURE = 2;


    public static final String DICT_WORD_FILTER = "dict.word.filter";
 }
