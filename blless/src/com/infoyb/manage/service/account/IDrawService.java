package com.cm.manage.service.account;

import com.cm.manage.model.account.Draw;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.vo.account.BatchTransferVO;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.User;

import java.util.List;
import java.util.Map;

public interface IDrawService extends IBaseService{

    public EasyuiDataGridJson drawList(EasyuiDataGrid dg, DrawVO draw);

    public Map drawCount(DrawVO draw);
    
    public Map drawDetail(DrawVO draw) throws BusiException;
    
    public Map<String,String> drawVerify(User user,DrawVO draw) throws BusiException;
    
    public Map<String,String> drawReject(User user,DrawVO draw) throws BusiException;
    
    public void batchDrawVerify(User user,String orderIdArrStr,DrawVO draw) throws BusiException;
        
    public Draw findDraw(String orderId);
    
    public List<Draw> findDrawList(List<String> argsList);
    
    public Map<String,String> batchTransfer(User user,DrawVO drawVO,String orderIdStr) throws BusiException;
    
    public void updateDrawInfo(List<AlipayOrderItem> items,DrawTransferRecord d);
    
    public void updateDrawTransferRecord(String batchNo) throws Exception;

	public void updateDrawBankInfo(AlipayOrderItem item);
	
	public EasyuiDataGridJson transferList(EasyuiDataGrid dg,BatchTransferVO transferVO);
	
	public Map<String,String> updateDraw(User user,DrawVO draw,String operation) throws BusiException;
	
	public Map<String,Object> prepareExcelData(String orderIdArr,DrawVO drawParamVO);
	
	public DrawTransferRecord findDrawTranfserRecordByBatchNo(String batchNo);
	
	public List<Map> querySubbankInfo(String province, String city,
			String bankname, String subbankName);
}
