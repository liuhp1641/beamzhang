package com.cm.manage.service.member.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.member.IMemberDao;
import com.cm.manage.model.account.BindChargeBank;
import com.cm.manage.model.account.BindFillBank;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.member.Customer;
import com.cm.user.http.bean.MemberBean;

@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl implements IMemberService {
    @Autowired
    private IMemberDao memberDao;

    @Transactional(readOnly = true)
    public Long yvipCount() {
        return memberDao.yvipCount();
    }


    @Transactional(readOnly = true)
    public Long mvipCount() {
        return memberDao.mvipCount();
    }


    @Transactional(readOnly = true)
    public Long dregCount() {
        return memberDao.dregCount();
    }


    @Transactional(readOnly = true)
    public Long yregCount() {
        return memberDao.yregCount();
    }

    @Transactional(readOnly = true)
    public Long mregCount() {
        return memberDao.mregCount();
    }


    @Transactional(readOnly = true)
    public Long ultregCount() {
        return memberDao.ultregCount();
    }
    /**
	 * 平台累计注册用户数
	 * @return
	 */
	public Long allRegCount(){
		return memberDao.allRegCount();
	}

    @Transactional(readOnly = true)
    public List<EasyuiTreeNode> channeltree() {
        return memberDao.channeltree();

    }

    @Transactional(readOnly = true)
    public EasyuiDataGridJson memberDatas(EasyuiDataGrid dg, Customer customer, boolean flag) {

        return memberDao.memberDatas(dg, customer, flag);

    }

    /**
	 * 用户登录日志
	 * @param dg
	 * @param userCode
	 * @return
	 */
	public EasyuiDataGridJson memberLoginList(EasyuiDataGrid dg, String userCode){
		return memberDao.memberLoginList(dg, userCode);
	}
    @Override
    public Map getCustomerInfo(String userCode) {
        return memberDao.getCustomerInfo(userCode);
    }

    @Override
    public Map getCustomerInfoByMobile(String mobile) {
        return memberDao.getCustomerInfoByMobile(mobile);
    }


    /**
     * 更改用户状态（解锁、锁定）
     * @param userCode
     * @param status
     */
    public int updateMemberStatus(String userCode,Integer status){
    	return memberDao.updateMemberStatus(userCode, status);
    }
    
    public BindChargeBank getRechargeBank(String userCode) {
        return memberDao.getRechargeBank(userCode);
    }

	/**
	 * 获取用户绑定充值银行信息
	 */
    public BindFillBank getFillBank(String userCode) {
        return memberDao.getFillBank(userCode);
    }
    
    /**
	 * 获取用户绑定提现银行信息
	 * @param userCode
	 * @return
	 */
	public DrawBindBank getDrawBindBank(String userCode){
		return memberDao.getDrawBindBank(userCode);
	}

    /**
     * 获取所有发卡行
     *
     * @return
     */
    public List<Map> getBanks(String type) {
        return memberDao.getBanks(type);
    }
    
    /**
	 * 用户基本信息保存
	 * @return
	 */
	public boolean updateMemberBasic(MemberBean member){
		return memberDao.updateMemberBasic(member);
	}

    /**
     * 修改用户充值行信息
     *
     * @param chargebank
     * @return
     */
    public boolean updateChargeBank(BindChargeBank chargebank) {
        return memberDao.updateChargeBank(chargebank);
    }

    /**
     * 修改用户提现银行信息
     *
     * @param fillbank
     * @return
     */
    public boolean updateFillBank(BindFillBank fillbank) {
        return memberDao.updateFillBank(fillbank);
    }
    
    /**
	 * 修改用户提现银行信息
	 * @param drawbank
	 * @return
	 */
	public boolean updateDrawBindBank(DrawBindBank drawbank){
		return memberDao.updateDrawBindBank(drawbank);
	}
    /**
	 * 资金内部存取时用户查询
	 * @param userCode
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public List<Customer> customerInfo(String userCode,String userName,String mobile){
		return memberDao.customerInfo(userCode, userName, mobile);
	};
	
	/**
	 * 总账新增
	 * @param vo
	 */
	public void accountSave(Customer vo){
		memberDao.accountSave(vo);
	}
	/**
     * 系统账户
     * @param accountName
     * @return
     */
    public List<Customer> sysAccount(String accountName){
    	return memberDao.sysAccount(accountName);
    }

    /**
	 * 积分商城
	 * 根据user_code查询用户名称
	 */
	@Override
	public List<Map> getMemberNameByCode(String userCode) {
		return memberDao.getMemberNameByCode(userCode);
	}
	/**
	 * 积分商城
	 * 根据user_name查询用户code
	 */
	@Override
	public List<Map> getMemberCodeByName(String userCode,int isVague) {
		return memberDao.getMemberCodeByName(userCode,isVague);
	}
}
