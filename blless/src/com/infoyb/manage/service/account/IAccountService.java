package com.cm.manage.service.account;

import java.util.List;
import java.util.Map;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.account.AccountLogVO;
import com.cm.manage.vo.account.AccountVO;
import com.cm.manage.vo.account.FillVO;
import com.cm.manage.vo.account.InternalAccessVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;

public interface IAccountService extends IBaseService{
	
	/**
	 * 用户资金查询
	 * @param dg
	 * @param account
	 * @return
	 */
	 public EasyuiDataGridJson accountData(EasyuiDataGrid dg, AccountVO account) ;
	 
	 /**
	 * 账户明细查询
	 * @param dg
	 * @param account
	 * @return
	 */
    public EasyuiDataGridJson detailData(EasyuiDataGrid dg, AccountLogVO account);
    /**
     * 账户明细汇总
     * @param account
     * @return
     */
    public Map detailCount(AccountLogVO account);
    
    /**
     * 充值管理查询
     * @param dg
     * @param fill
     * @return
     */
    public EasyuiDataGridJson rechargeData(EasyuiDataGrid dg, FillVO fill);
    /**
     * 充值管理汇总
     * @param fill
     * @return
     */
    public Map rechargeCount(FillVO fill);
    
    /**
     * 充值详情
     * @param id
     * @return
     */
    public Map rechargeDetail(String orderId);
    
    /**
     * 充值备注
     * @param orderId
     * @param memo
     */
    public int rechargeMemoSave(String orderId,String memo);
    
    /**
     * 充值单强制失败
     * @param orderList
     * @return
     */
    public boolean  rechargeFail(List<String> orderList);
    
   
    /**
     * 内部存取操作 
     * @param internalAccess
     */
    public Map<String,Object>  accountAdjust(InternalAccessVO vo);
    
    /**
     * 资金调整
     * @param map
     * @return
     */
    public boolean amountAdjust(Map<String,Object> map);
    
    /**
     * 内部存取查询
     * @param dg
     * @param account
     * @return
     */
    
    public EasyuiDataGridJson internalData(EasyuiDataGrid dg, InternalAccessVO account); 
    
    /**
     * 内部存取汇总
     * @param account
     * @return
     */
    public Map internalCount(InternalAccessVO account);

}
