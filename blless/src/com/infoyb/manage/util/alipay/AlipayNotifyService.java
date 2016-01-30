package com.cm.manage.util.alipay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.account.IDrawBatchTransferService;
import com.cm.manage.service.account.IDrawService;
import com.cm.manage.util.HttpUtil;
import com.cm.manage.util.SpringContextUtils;
import com.cm.manage.util.alipay.util.AlipayNotify;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;

/***
 * @describe 处理支付宝的异步通知
 * @author sunjf
 *
 */
public class AlipayNotifyService{
	private static final Logger logger = Logger.getLogger(AlipayNotifyService.class);
	private static IDrawBatchTransferService drawBatchTransferService = (IDrawBatchTransferService) SpringContextUtils.getBean("drawBatchTransferService");
    private static IDrawService drawService = (IDrawService) SpringContextUtils.getBean("drawService"); 
	public String notity(HttpServletRequest request){
		String tipMsg = "";
		try{
			Map<String,String> params = HttpUtil.getParams(request);
			String fileName = new String(request.getParameter("file_name").getBytes("ISO-8859-1"),"UTF-8");
			logger.info("收到支付宝的一个异步通知信息,批次文件-->" + fileName);
			String isSuccess = CommonUtil.trim(request.getParameter("flag"));//通知
			boolean verifyResult = AlipayNotify.verify(params);
			if(! verifyResult){
				throw new BusiException("","验证支付宝的返回数据失败");
			}
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
				logger.info("批次[" + batchNo + "]记录不存在");
				throw new BusiException("","批次号[" + batchNo + "]记录不存在");
			}
			if(CommonConstants.Batch_TRANSFER_DOING == d.getStatus()){
				if(CommonConstants.ALIPAY_BPTB_RESULT_FILE.equals(isSuccess)){//文件处理结果通知
					//复核成功之后开始调用支付宝明细查询接口,构造明细查询请求参数
					Map<String,String> queryParams = new HashMap<String,String>();
					queryParams.put("file_name", fileName);
					String xml = AlipayBatchService.queryBatchDetail(queryParams);
					logger.info("查询批次[" + batchNo + "]明细，支付宝返回的数据:\n" + xml);
					if(xml == null || "".equals(xml)){
						throw new BusiException("","查询批次[" + batchNo + "]明细，支付宝返回的数据为空");
					}
					AlipayOrder order = new AlipayOrder();
					try{
						order.fromXml(xml);
					}catch(Exception e){
						logger.error("系统处理批次[" + batchNo + "]返回的明细查询数据失败",e);
						throw new BusiException("","系统处理批次[" + batchNo + "]返回的明细查询数据失败");
					}
					if(CommonConstants.ALIPAY_RESULT_FAIL.equals(order.getIs_success())){//查询明细失败
						logger.info("批次[" + batchNo + "]明细查询失败,支付宝返回的错误码:" + order.getError());
						//异常输出
						throw new BusiException("","查询批次[" + batchNo + "]明细失败,支付宝返回的错误码：" + order.getError());
					}else if(CommonConstants.ALIPAY_RESULT_SUCCESS.equals(order.getIs_success())){//查询明细成功
						int resultNum = Integer.parseInt(order.getPageSize());//头部声明的记录数
						List<AlipayOrderItem> items = order.getItems();//解析xml得到的记录数
						if(order.getTotalNumber() != resultNum){
							logger.info("批次[" + batchNo + "]明细查询,支付宝声明的记录数:" + resultNum + ",系统解析XML文件获取的记录数量:" + items.size());
							//异常输出
							throw new BusiException("","查询批次[" + batchNo + "]明细失败,支付宝声明的交易记录数:" + resultNum + ",系统解析支付宝响应数据获取的交易记录数:" + order.getTotalNumber());
						}
						//开始更新提现记录
						try{
							if(resultNum == order.getFailNumber()){//全部失败则该批次失败
								d.setStatus(CommonConstants.Batch_TRANSFER_FAIL);//失败
							}else if(resultNum > (order.getFailNumber() + order.getSuccessNumber())){
								d.setStatus(CommonConstants.Batch_TRANSFER_DOING);//处理中
							}else{
								d.setStatus(CommonConstants.Batch_TRANSFER_SUCCESS);//有一笔成功则成功
							}
							d.setSuccessAmount(order.getSuccessAmount());
							d.setSuccessNumber(order.getSuccessNumber());
							d.setFailAmount(order.getFailAmount());
							d.setFailNumber(order.getFailNumber());
							Date date = new Date();
							String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
							d.setAcceptTime(DateUtil.format(dateStr));
							drawService.updateDrawInfo(items,d);
						}catch(Exception e){
							logger.error("查询支付宝明细成功，但系统更新提现明细失败,批次号[" + batchNo + "]",e);
							throw new BusiException("","查询支付宝明细成功，但系统更新提现明细失败,批次号[" + batchNo + "]");
						}								
					}
				}else if(CommonConstants.ALIPAY_BPTB_UNFREEZE_FILE.equals(isSuccess)){//文件余额不足通知
					try{
						d.setStatus(CommonConstants.Batch_TRANSFER_FAIL);//失败
						d.setErrorCode("批次处理失败，支付宝账户余额不足");
						String errorMsg = "批次余额不足，导致交易失败";
						drawBatchTransferService.updateTransInfo(d,CommonConstants.DRAW_FAIL,errorMsg);//更新提现记录状态为2失败
					}catch(Exception e){
						logger.error("支付宝复核文件失败，系统更新批次记录状态失败,批次号[" + batchNo + "]",e);
						throw new BusiException("","支付宝复核文件失败，系统更新批次记录状态失败,批次号[" + batchNo + "]");
					}
				}
			}
		}catch(BusiException be){
			logger.error(be.getMessage(), be);
			tipMsg = be.getMessage();
		}catch(Exception e){
			logger.error(e);
			tipMsg = "系统处理失败，请联系技术人员!";
		}
		return tipMsg;
		
	}
	
}
