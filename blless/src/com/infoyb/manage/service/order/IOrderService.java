package com.cm.manage.service.order;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.order.Ticket;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.AutoOrderVO;
import com.cm.manage.vo.order.OrderVO;

public interface IOrderService extends IBaseService{
	/**
	  * 投注查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson orderList(EasyuiDataGrid dg, OrderVO order);
	 /**
	  * 追号方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson autoOrderList(EasyuiDataGrid dg, AutoOrderVO order);
	 
	 /**
	  * 追号订单对应方案内容
	  */
	 
	 public List<OrderVO> autoProgramsInfo(String autoOrderId);
	 
	 
	 /**
	  * 合买方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson synOrderList(EasyuiDataGrid dg, OrderVO order) ;
	 
	 /**
	  * 合买推荐
	  * @param programsOrderId
	  */
	 public boolean synRecommend(String programsOrderId,Integer backup3);
	 
	 /**
	  * 代购方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson buyOrderList(EasyuiDataGrid dg, OrderVO order);
	 
	 /**
	  * 追号方案详情
	  * @param autoOrderId
	  * @return
	  */
	 public Map autoOrderInfo(String autoOrderId);
	 
	 /**
	  * 追号订单对应方案信息
	  */
	 
	 public EasyuiDataGridJson autoProgramsList(EasyuiDataGrid dg,String autoOrderId);
	 
	 /**
	  * 合买或代购方案详情
	  * @param programsOrderId
	  * @return
	  */
	 public Map programsOrderInfo(String programsOrderId,Integer buyType);
	 
	 /**
	  * 方案彩票信息
	  * @param programsOrderId
	  * @return
	  */
	 public EasyuiDataGridJson ticketList(EasyuiDataGrid dg,String programsOrderId);
	 
	 /**
	  * 合买方案描述修改
	  * @param programsOrderId
	  * @param description
	  */
	 public void editSynDescription(String programsOrderId,String description);
	 
	 /**
	  * 彩票某期的开奖号码
	  * @param lotteryCode
	  * @param issue
	  * @return
	  */
	 public List<Map> mainIssue(String lotteryCode,String issue);
	 
	 /**
	  * 合买用户
	  * @param dg
	  * @param programsOrderId
	  * @return
	  */
	 public EasyuiDataGridJson synMemberList(EasyuiDataGrid dg,String programsOrderId);
	 
	 /**
	  * 合买方案置顶
	  * @param programsList
	  * @return
	  */
	 public boolean  setTop(List<String> programsList);
	 
}
