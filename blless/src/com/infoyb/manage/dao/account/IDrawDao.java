package com.cm.manage.dao.account;

import com.cm.manage.model.account.Draw;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.model.account.BindFillBank;
import com.cm.manage.model.member.Member;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;

import java.util.List;
import java.util.Map;


public interface IDrawDao {

    /**
     * 提现
     *
     * @param dg
     * @param draw
     * @return
     */
    public EasyuiDataGridJson drawList(EasyuiDataGrid dg, DrawVO draw);

    public Map drawCount(DrawVO draw);

	public Map drawDetail(DrawVO draw);
	
	public boolean drawVerify(DrawVO draw);
	
	public boolean drawReject(DrawVO draw);
	
	public boolean drawBatchHandle(List<String> orderIdList,DrawVO draw);
	
	public Draw getDrawByOrderId(String orderId);
	
	public List<Draw> findDrawList(List<String> argsList);
	
	public void update(Draw d);
	
	public void updateByBatchNo(String batchNo,int status,String errorMsg);
	
	public void updateDrawInfo(int status,String alipayDrawNo,String errorMsg,String orderId);
	
	public boolean drawReverify(DrawVO draw);
	
	public void updateDrawBankInfo(AlipayOrderItem item);
	
	public DrawBindBank findDrawBankByUsercode(String userCode);
	
	public void saveBankInfo(DrawBindBank bank);
	
	public void updateBankInfo(DrawBindBank bank);
	
	public boolean isVip(String orderId);
	
	public void updateDrawList(List<Draw> drawList,DrawVO drawVO);
	
	public int getVipRank(String userCode);
	
	public String getUserName(String userCode);
	
	public Member findMemberByUserCode(String userCode);
	
	public void updateDraw(Draw draw, DrawVO drawVO);
	
	public List<Map> getSubbankNameList(String province,String city,String bankname,String subbankName);
}
