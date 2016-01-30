package com.cm.manage.dao.member;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.account.BindChargeBank;
import com.cm.manage.model.account.BindFillBank;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.member.Customer;
import com.cm.user.http.bean.MemberBean;

public interface IMemberDao {

	/**
	 * 昨日新增Vip数
	 * @return
	 */
	public Long yvipCount();
	
	/**
	 * 当月新增vip数
	 * @return
	 */
	public Long mvipCount();
	
	/**
	 * 当日累计注册用户数
	 */
	public Long dregCount();
	
	/**
	 * 昨日累计注册用户数
	 */
	public Long yregCount();
	
	/**
	 * 当月累计注册用户数
	 */
	public Long mregCount();
	
	/**
	 * 上月累计注册用户数
	 */
	public Long ultregCount();
	
	/**
	 * 平台累计注册用户数
	 * @return
	 */
	public Long allRegCount();

	/**
	 * 注册渠道
	 * @param id
	 * @return
	 */
	public List<EasyuiTreeNode> channeltree();
	
	
	/**
	 * 
	 * 用户查询列表
	 * @param dg
	 * @param customer
	 * @return
	 */
	public EasyuiDataGridJson memberDatas(EasyuiDataGrid dg, Customer customer,boolean flag);
	/**
	 * 用户登录日志
	 * @param dg
	 * @param userCode
	 * @return
	 */
	public EasyuiDataGridJson memberLoginList(EasyuiDataGrid dg, String userCode);
	
	/**
	 * 根据用户编码获取用户信息
	 * @param userCode
	 * @return
	 */
	public Map getCustomerInfo(String userCode);

    /**
     * 根据手机号码获取用户信息
     * @param mobile
     * @return
     */
    public Map getCustomerInfoByMobile(String mobile);
    /**
     * 更改用户状态（解锁、锁定）
     * @param userCode
     * @param status
     */
    public int updateMemberStatus(String userCode,Integer status);

	/**
	 * 获取用户绑定充值银行信息
	 * @param userCode
	 * @return
	 */
	public BindChargeBank getRechargeBank(String userCode);
	
	/**
	 * 获取用户绑定充值银行信息
	 * @param userCode
	 * @return
	 */
	public BindFillBank getFillBank(String userCode);
	
	/**
	 * 获取用户绑定提现银行信息
	 * @param userCode
	 * @return
	 */
	public DrawBindBank getDrawBindBank(String userCode);
	
	/**
	 * 获取所有发卡行
	 * @return
	 */
	public List<Map> getBanks(String type);
	/**
	 * 用户基本信息保存
	 * @return
	 */
	public boolean updateMemberBasic(MemberBean member);
	
	/**
	 * 修改用户充值行信息
	 * 
	 * @param chargebank
	 * @return
	 */
	public boolean updateChargeBank(BindChargeBank chargebank);
	
	/**
	 * 修改用户充值银行信息
	 * @param fillbank
	 * @return
	 */
	public boolean updateFillBank(BindFillBank fillbank);
	/**
	 * 修改用户提现银行信息
	 * @param drawbank
	 * @return
	 */
	public boolean updateDrawBindBank(DrawBindBank drawbank);
	/**
	 * 资金内部存取时用户查询
	 * @param userCode
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public List<Customer> customerInfo(String userCode,String userName,String mobile);
	/**
	 * 总账新增
	 * @param vo
	 */
	public void accountSave(Customer vo); 
	
	 /**
     * 系统账户
     * @param accountName
     * @return
     */
    public List<Customer> sysAccount(String accountName);
    /**
	 * 积分商城
	 * 根据user_code查询用户名称
	 */
    public List<Map> getMemberNameByCode(String userCode);
    /**
	 * 积分商城
	 * 根据用户名查询用户code
	 */
    public List<Map> getMemberCodeByName(String userName,int isVague);
}
