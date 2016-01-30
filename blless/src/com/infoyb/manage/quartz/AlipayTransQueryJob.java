package com.cm.manage.quartz;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.account.IDrawBatchTransferService;
import com.cm.manage.service.account.IDrawService;
import com.cm.manage.util.SpringContextUtils;
import com.cm.manage.util.alipay.AlipayBatchService;
import com.cm.manage.util.alipay.AlipayOrder;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.util.base.DateUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 金福
 * @describe 支付宝定时交易查询
 */
public class AlipayTransQueryJob extends QuartzJobBean {

    private Logger logger = Logger.getLogger(AlipayTransQueryJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date date = new Date();
        logger.info("支付宝交易定时查询---AlipayTransQueryJob----" + DateUtil.format(date));
        try {
            IDrawService drawService = (IDrawService) SpringContextUtils.getBean("drawService");
            IDrawBatchTransferService drawBatchTransferService = (IDrawBatchTransferService) SpringContextUtils.getBean("drawBatchTransferService");
            if (drawService != null && drawBatchTransferService != null) {
                //查询状态为处理中的批次信息
                List<DrawTransferRecord> recordList = drawBatchTransferService.getTransferByStatus(CommonConstants.Batch_TRANSFER_DOING);
                if (recordList != null && recordList.size() > 0) {
                    int recordListSize = recordList.size();
                    AlipayOrder order = null;
                    logger.info("当前正在处理中的批次记录数量:" + recordListSize);
                    for (DrawTransferRecord record : recordList) {
                        String batchNo = record.getBatchNo();
                        String fileName = batchNo + CommonConstants.TRANSFER_FILE_CSV_SUFFIX;
                        try {
                            //复核成功之后开始调用支付宝明细查询接口,构造明细查询请求参数
                            Map<String, String> queryParams = new HashMap<String, String>();
                            queryParams.put("file_name", fileName);
                            String xml = AlipayBatchService.queryBatchDetail(queryParams);
                            logger.info("查询批次[" + batchNo + "]明细，支付宝返回的数据:\n" + xml);
                            order = new AlipayOrder();
                            order.fromXml(xml);
                            logger.info("解析批次[" + batchNo + "]明细数据成功");
                            if (CommonConstants.ALIPAY_RESULT_FAIL.equals(order.getIs_success())) {//查询明细失败
                                logger.info("查询批次[" + batchNo + "]明细失败,支付宝返回的错误码:" + order.getError());
                            } else if (CommonConstants.ALIPAY_RESULT_SUCCESS.equals(order.getIs_success())) {//查询明细成功
                                int resultNum = Integer.parseInt(order.getPageSize());//头部声明的记录数
                                List<AlipayOrderItem> items = order.getItems();//解析xml得到的记录数
                                if (order.getTotalNumber() != resultNum) {
                                    logger.error("数据错误,批次[" + batchNo + "]支付宝声明的记录数:" + resultNum + ",系统解析XML文件获取的记录数量:" + items.size());
                                }

                                if (resultNum == order.getFailNumber()) {//全部失败则该批次失败
                                    record.setStatus(CommonConstants.Batch_TRANSFER_FAIL);//失败
                                } else if (resultNum > (order.getFailNumber() + order.getSuccessNumber())) {
                                    record.setStatus(CommonConstants.Batch_TRANSFER_DOING);//处理中
                                } else {
                                    record.setStatus(CommonConstants.Batch_TRANSFER_SUCCESS);//有一笔成功则成功
                                }
                                record.setSuccessAmount(order.getSuccessAmount());
                                record.setSuccessNumber(order.getSuccessNumber());
                                record.setFailAmount(order.getFailAmount());
                                record.setFailNumber(order.getFailNumber());
                                String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
                                record.setAcceptTime(DateUtil.format(dateStr));
                                drawService.updateDrawInfo(items, record);
                            }
                        } catch (Exception e) {
                            logger.error("批次[" + batchNo + "]处理失败");
                            logger.error(e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
