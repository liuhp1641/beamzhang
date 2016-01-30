package com.cm.user.exception;

import java.util.HashMap;
import java.util.Map;



public class IUserErrCode  {

    /**
     * 用户不存在
     */
    public static final String E1000 = "1000";

    /**
     * 投注码为空
     */
    public static final String E1001 = "1001";

    /**
     * 用户名为空
     */
    public static final String E1002 = "1002";

    /**
     * 用户名格式错误
     */
    public static final String E1003 = "1003";

    /**
     * 用户名已存在
     */
    public static final String E1004 = "1004";

    /**
     * 密码为空
     */
    public static final String E1005 = "1005";

    /**
     * 密码格式错误
     */
    public static final String E1006 = "1006";

    /**
     * 密码错误
     */
    public static final String E1007 = "1007";

    /**
     * 原密码错误
     */
    public static final String E1008 = "1008";

    /**
     * 原密码为空
     */
    public static final String E1009 = "1009";

    /**
     * 身份证号为空
     */
    public static final String E1010 = "1010";

    /**
     * 身份证号格式错误
     */
    public static final String E1011 = "1011";

    /**
     * 真实姓名为空
     */
    public static final String E1012 = "1012";

    /**
     * 真实姓名格式错误
     */
    public static final String E1013 = "1013";

    /**
     * 手机号为空
     */
    public static final String E1014 = "1014";

    /**
     * 手机号格式错误
     */
    public static final String E1015 = "1015";

    /**
     * 手机号已存在
     */
    public static final String E1016 = "1016";

    /**
     * 用户已被锁定
     */
    public static final String E1023 = "1023";


    /**
     * 联合登录 第三方用户ID为空
     */
    public static final String E1024 = "1024";

    /**
     * 联合登录 第三方类型为空
     */
    public static final String E1025 = "1025";

    /**
     * 年龄小于18岁
     */
    public static final String E1026 = "1026";

    /**
     * 用户名已修改
     */
    public static final String E1027 = "1027";

    /**
     * 用户名已绑定
     */
    public static final String E1028 = "1028";

    /**
     * 验证码为空
     */
    public static final String E1030 = "1030";
    /**
     * 验证码已失效
     */
    public static final String E1031 = "1031";
    /**
     * 验证码错误
     */
    public static final String E1032 = "1032";

    /**
     * 设备串号为空
     */
    public static final String E1033 = "1033";


    /**
     * 用户包含敏感词
     */
    public static final String E1034 = "1034";
    
    /**
     * 成功
     */
    public static final String E0000 = "0000";
    /**
     * MD5验证失败
     */
    public static final String E0001 = "0001";
    /**
     * 参数错误
     */
    public static final String E0002 = "0002";

    /*
     * 通信数据类型为空
     */
    public static final String E0003 = "0003";
    /*
     * 通信数据类型不正确
     */
    public static final String E0004 = "0004";

    /*
     * 参数不是有效的json格式
     */
    public static final String E0005 = "0005";

    /*
    * 功能参数func无效
    */
    public static final String E0006 = "0006";

    /*
     * 渠道商不存在
     */
    public static final String E0007 = "0007";


    /**
     * 系统处理失败
     */
    public static final String E0999 = "0999";

    private static final Map<String,String> map=new HashMap<String,String>();

    static {
        init();
    }

    public static void init() {

        // 用户相关
    	
		map.put(E0000, "用户不存在");
	    map.put(E0001, "MD5验证失败");
	    map.put(E0002, "参数错误");
	      
	    map.put(E0003, "通信数据类型为空");
        map.put(E0004, "通信数据类型不正确");
        map.put(E0005, "参数不是有效的json格式");
          
        map.put(E0006, "功能参数func无效");
        map.put(E0007, "渠道商不存在");
        
        map.put(E1000, "用户不存在");
        map.put(E1001, "投注码为空");
        map.put(E1002, "用户名为空");

        map.put(E1003, "用户名格式错误");
        map.put(E1004, "用户名已存在");
        map.put(E1005, "密码为空");

        map.put(E1006, "密码格式错误");
        map.put(E1007, "密码错误");
        map.put(E1008, "原密码错误");
        map.put(E1009, "原密码为空");

        map.put(E1010, "身份证号为空");
        map.put(E1011, "身份证号格式错误");

        map.put(E1012, "真实姓名为空");
        map.put(E1013, "真实姓名格式错误");

        map.put(E1014, "手机号为空");
        map.put(E1015, "手机号格式错误");
        map.put(E1016, "手机号已存在");

        map.put(E1023, "用户已被锁定");
        map.put(E1024, "第三方用户ID为空");
        map.put(E1025, "第三方类型为空");

        map.put(E1026, "年龄小于18岁");
        map.put(E1027, "用户名已修改");
        map.put(E1028, "用户名已绑定");

        map.put(E1030, "验证码为空");
        map.put(E1031, "验证码已失效");
        map.put(E1032, "验证码错误");
        map.put(E1033, "设备串号为空");

        map.put(E1034, "用户名包含敏感词");

    }

    public static String codeToMsg(String code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        throw new UserException(E0999);
    }
}
