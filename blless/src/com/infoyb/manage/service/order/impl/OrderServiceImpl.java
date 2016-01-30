package com.cm.manage.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.order.IOrderDao;
import com.cm.manage.model.order.Ticket;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.order.IOrderService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.AutoOrderVO;
import com.cm.manage.vo.order.OrderVO;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {
	 @Autowired
	 private IOrderDao orderDao;
	/**
	  * 投注查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson orderList(EasyuiDataGrid dg, OrderVO order){
		 return orderDao.orderList(dg, order);
	 }
	 /**
	  * 追号方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson autoOrderList(EasyuiDataGrid dg, AutoOrderVO order){
		 return orderDao.autoOrderList(dg, order);
	 }
	 
	 /**
	  * 合买方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson synOrderList(EasyuiDataGrid dg, OrderVO order) {
		 return orderDao.synOrderList(dg, order);
	 }
	 
	 /**
	  * 合买推荐
	  * @param programsOrderId
	  */
	 public boolean synRecommend(String programsOrderId,Integer backup3){
		 return orderDao.synRecommend(programsOrderId,backup3);
	 }
	 /**
	  * 代购方案查询
	  * @param dg
	  * @param order
	  * @return
	  */
	 public EasyuiDataGridJson buyOrderList(EasyuiDataGrid dg, OrderVO order){
		 return orderDao.buyOrderList(dg, order);
	 }
	 /**
	  * 追号方案详情
	  * @param autoOrderId
	  * @return
	  */
	 public Map autoOrderInfo(String autoOrderId){
		 return orderDao.autoOrderInfo(autoOrderId);
	 }
	 
	 /**
	  * 追号订单对应方案信息
	  */
	 
	 public EasyuiDataGridJson autoProgramsList(EasyuiDataGrid dg,String autoOrderId){
		 return orderDao.autoProgramsList(dg, autoOrderId);
	 }
	 /**
	  * 追号订单对应方案内容
	  */
	 
	 public List<OrderVO> autoProgramsInfo(String autoOrderId){
		 return orderDao.autoProgramsInfo(autoOrderId);
	 }
	 
	 /**
	  * 合买或代购方案详情
	  * @param programsOrderId
	  * @return
	  */
	 public Map programsOrderInfo(String programsOrderId,Integer buyType){
		 return orderDao.programsOrderInfo(programsOrderId, buyType);
	 }
	
	 /**
	  * 方案彩票信息
	  * @param programsOrderId
	  * @return
	  */
	 public EasyuiDataGridJson ticketList(EasyuiDataGrid dg,String programsOrderId){
		 return orderDao.ticketList(dg,programsOrderId);
	 }
	 
	 /**
	  * 合买方案描述修改
	  * @param programsOrderId
	  * @param description
	  */
	 public void editSynDescription(String programsOrderId,String description){
		 orderDao.editSynDescription(programsOrderId, description);
	 }
	 /**
	  * 彩票某期的开奖号码
	  * @param lotteryCode
	  * @param issue
	  * @return
	  */
	 public List<Map> mainIssue(String lotteryCode,String issue){
		 return orderDao.mainIssue(lotteryCode, issue);
	 }
	 /**
	  * 合买用户
	  * @param dg
	  * @param programsOrderId
	  * @return
	  */
	 public EasyuiDataGridJson synMemberList(EasyuiDataGrid dg,String programsOrderId){
		 return orderDao.synMemberList(dg, programsOrderId);
	 }
	 /**
	  * 合买方案置顶
	  * @param programsList
	  * @return
	  */
	 public boolean  setTop(List<String> programsList){
		 return orderDao.setTop(programsList);
	 }
}
