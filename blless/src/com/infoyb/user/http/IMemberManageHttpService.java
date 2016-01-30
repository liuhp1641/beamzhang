package com.cm.user.http;


import com.cm.user.http.bean.MemberBean;

public interface IMemberManageHttpService {

    /**
     * 添加渠道
     *
     * @return
     */
    public int saveCooperation();

    /**
     * 修改密码
     *
     * @param userCode 用户编码
     * @param password 密码
     * @return
     */
    public boolean editPassword(String userCode, String password);

    /**
     * 清空手机号
     *
     * @param userCode 用户编码
     * @return
     */
    public boolean clearMobile(String userCode);

    /**
     * 编辑用户信息
     *
     * @param userCode 用户编码
     * @param cardCode 身份证号码
     * @param realName 真实姓名
     * @return
     */
    public boolean editReal(String userCode, String cardCode, String realName);

    /**
     * 解锁用户
     *
     * @param userCode 用户编码
     * @return
     */
    public boolean unLock(String userCode);

    /**
     * 编辑用户
     * @param memberBean
     * @return
     */
    public boolean editMember(MemberBean memberBean);
}
