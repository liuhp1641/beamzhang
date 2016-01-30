package com.cm.manage.controller.handleres;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.account.IDrawBatchTransferService;
import com.cm.manage.util.HttpUtil;
import com.cm.manage.util.alipay.util.AlipayNotify;
import com.cm.manage.util.base.BusiException;
@Controller
@RequestMapping("/handleAlipayController")
public class HandleAlipayReturnController extends BaseController{
	private static final Logger logger = Logger.getLogger(HandleAlipayReturnController.class);
	@Autowired
	private IDrawBatchTransferService drawBatchTransferService;
	@RequestMapping(value = "/res")
	public String handleAlipayRes(HttpServletRequest request,HttpServletResponse response){
		String tipMsg = "系统向支付宝请求复核文件成功，状态为转账中的交易需等待支付宝的通知才能确定是否成功!";
		try{
			Map<String,String> params = HttpUtil.getParams(request);
			String resultCode = request.getParameter("result_code");//支付宝返回的错误码
			String isSuccess = request.getParameter("is_success");//复核是否成功
			boolean verifyResult = AlipayNotify.verify(params);
			if(! verifyResult){
				throw new BusiException("","验证支付宝的返回数据失败");
			}			
			if(CommonConstants.ALIPAY_RESULT_SUCCESS.equals(isSuccess)){//复核成功
				String fileName = new String(request.getParameter("file_name").getBytes("ISO-8859-1"),"UTF-8");
				String batchNo = fileName.substring(0, fileName.length() - 4);
				logger.info("开始处理批次号为[" + batchNo + "]数据");
				DrawTransferRecord d = null;
				try{
					d = drawBatchTransferService.findByBatchNo(batchNo);
				}catch(Exception e){
					logger.error("根据批次号[" + batchNo + "]查询批次信息失败",e);
					throw new BusiException("","根据批次号[" + batchNo + "]查询批次信息失败");
				}
				if(d == null){
					logger.info("批次号[" + batchNo + "]记录不存在");
					throw new BusiException("","批次号[" + batchNo + "]记录不存在");
				}
				//支付宝复核成功之后此时还未完成处理无需发起查询，等待支付宝主动发起通知处理即可
//				if(3 == d.getStatus()){//处理中的允许更新状态
//					//开始调用支付宝明细查询接口,构造明细查询请求参数
//					Map<String,String> queryParams = new HashMap<String,String>();
//					queryParams.put("file_name", fileName);
//					String xml = AlipayBatchService.queryBatchDetail(queryParams);
//					logger.info("查询批次号[" + batchNo + "]明细，支付宝返回的数据为空\n" + xml);
//					if(xml == null || "".equals(xml)){
//						throw new BusiException("","查询批次号[" + batchNo + "]明细，支付宝返回的数据为空");
//					}
//					AlipayOrder order = new AlipayOrder();
//					try{
//						order.fromXml(xml);
//					}catch(Exception e){
//						logger.error("系统处理批次号[" + batchNo + "]返回的明细查询数据失败",e);
//						throw new BusiException("","系统处理批次号[" + batchNo + "]返回的明细查询数据失败");
//					}
//					if("F".equals(order.getIs_success())){//查询明细失败
//						logger.info("批次号[" + batchNo + "]明细查询失败,支付宝返回的错误码:" + order.getError());
//						//异常输出
//						throw new BusiException("","查询明细失败,批次号[" + batchNo + "],支付宝返回的错误码：" + order.getError());
//					}else if("T".equals(order.getIs_success())){//查询明细成功
//						int resultNum = Integer.parseInt(order.getPageSize());//头部声明的记录数
//						List<AlipayOrderItem> items = order.getItems();//解析xml得到的记录数
//						if(order.getTotalNumber() != resultNum){
//							logger.info("批次号[" + batchNo + "]明细查询,支付宝声明的记录数:" + resultNum + ",系统解析XML文件获取的记录数量:" + items.size());
//							//异常输出
//							throw new BusiException("","查询明细失败,批次号[" + batchNo + "],支付宝声明的交易记录数:" + resultNum + ",系统解析支付宝响应数据获取的交易记录数:" + order.getTotalNumber());
//						}
//						//开始更新提现记录
//						try{
//							if(resultNum == order.getFailNumber()){//全部失败则该批次失败
//								d.setStatus(2);//失败
//							}else if(resultNum > (order.getFailNumber() + order.getSuccessNumber())){
//								d.setStatus(3);//处理中
//							}else{
//								d.setStatus(1);//有一笔成功则成功
//							}
//							d.setSuccessAmount(order.getSuccessAmount());
//							d.setSuccessNumber(order.getSuccessNumber());
//							d.setFailAmount(order.getFailAmount());
//							d.setFailNumber(order.getFailNumber());
//							drawService.updateDrawInfo(items,d);
//						}catch(Exception e){
//							logger.error("查询支付宝明细成功，但系统更新提现明细失败,批次号[" + batchNo + "]",e);
//							throw new BusiException("","查询支付宝明细成功，但系统更新提现明细失败,批次号[" + batchNo + "]");
//						}								
//					}
//				}
		//批次全部校验失败了，支付宝不会发起主动通知，此时需要去更新记录为失败状态
			}else if(CommonConstants.ALIPAY_RESULT_FAIL.equals(isSuccess)){//复核失败
				throw new BusiException("","支付宝复核文件失败,错误码[" + resultCode + "]");
			}
		}catch(BusiException be){
			logger.error(be.getMessage(), be);
			tipMsg = be.getMessage();
		}catch(Exception e){
			logger.error(e);
			tipMsg = "系统处理失败，请联系技术人员!";
		}
		request.setAttribute("tipMsg", tipMsg);
		return "/alipay/return_url";
	}
	
	
	
}
