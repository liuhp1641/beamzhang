package com.cm.manage.service.account.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.account.IAccountDao;
import com.cm.manage.service.account.IAccountService;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.vo.account.AccountLogVO;
import com.cm.manage.vo.account.AccountVO;
import com.cm.manage.vo.account.FillVO;
import com.cm.manage.vo.account.InternalAccessVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl implements
		IAccountService {
	@Autowired
    private IAccountDao accountDao;
	
	@Override
	public EasyuiDataGridJson accountData(EasyuiDataGrid dg, AccountVO account) {
		return accountDao.accountData(dg, account);
	}
	
	/**
	 * 资金明细查询
	 * @param dg
	 * @param account
	 * @return
	 */
    public EasyuiDataGridJson detailData(EasyuiDataGrid dg, AccountLogVO account){
    	return accountDao.detailData(dg, account);
    }

    /**
     * 账户明细汇总
     * @param account
     * @return
     */
    public Map detailCount(AccountLogVO account){
    	return accountDao.detailCount(account);
    }
    
    /**
     * 充值管理查询
     * @param dg
     * @param fill
     * @return
     */
    public EasyuiDataGridJson rechargeData(EasyuiDataGrid dg, FillVO fill){
    	return accountDao.rechargeData(dg, fill);
    }
    /**
     * 充值管理汇总
     * @param fill
     * @return
     */
    public Map rechargeCount(FillVO fill){
    	return accountDao.rechargeCount(fill);
    }
    /**
     * 充值详情
     * @param id
     * @return
     */
    public Map rechargeDetail(String orderId){
    	return accountDao.rechargeDetail(orderId);
    }
    /**
     * 充值备注
     * @param orderId
     * @param memo
     */
    public int rechargeMemoSave(String orderId,String memo){
    	return accountDao.rechargeMemoSave(orderId, memo);
    }
    
    /**
     * 充值单强制失败
     * @param orderList
     * @return
     */
    public boolean  rechargeFail(List<String> orderList){
    	return accountDao.rechargeFail(orderList);
    }
    /**
     * 内部存取操作 
     * @param internalAccess
     */
    public Map<String,Object>  accountAdjust(InternalAccessVO vo){
    	return accountDao.accountAdjust(vo);
    }
    /**
     * 资金调整
     * @param map
     * @return
     */
    public boolean amountAdjust(Map<String,Object> map){
    	return accountDao.amountAdjust(map);
    }
    /**
     * 内部存取查询
     * @param dg
     * @param account
     * @return
     */
    
    public EasyuiDataGridJson internalData(EasyuiDataGrid dg, InternalAccessVO account){
    	return accountDao.internalData(dg, account);
    }
    /**
     * 内部存取汇总
     * @param account
     * @return
     */
    public Map internalCount(InternalAccessVO account){
    	return accountDao.internalCount(account);
    }
}
