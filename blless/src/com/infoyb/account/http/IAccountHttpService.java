package com.cm.account.http;

import java.util.List;

import com.cm.account.http.bean.DepositOrTellerBean;
import com.cm.account.http.bean.IncomingOperationBean;
import com.cm.account.http.bean.TransferBean;

public interface IAccountHttpService {
	/**
	 * 收入操作
	 * 
	 * @param beanList
	 * @return 成功失败
	 */
	public boolean incomingOperation(List<IncomingOperationBean> beanList);
	/**
	 * 转账操作
	 * 
	 * @param transferBean
	 * @return
	 */
	public boolean transferOperation(TransferBean transferBean);
	
	/**
	 * 存入取出操作
	 * 
	 * @param depositBean
	 * @return
	 */
	public boolean dpositOrTellerOperation(DepositOrTellerBean depositBean);


}
