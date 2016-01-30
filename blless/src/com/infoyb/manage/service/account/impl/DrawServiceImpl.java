package com.cm.manage.service.account.impl;

import com.cm.account.http.IAccountHttpService;
import com.cm.account.http.bean.IncomingOperationBean;
import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.account.DrawController;
import com.cm.manage.dao.account.IDrawDao;
import com.cm.manage.dao.account.IDrawTransferRecordDao;
import com.cm.manage.jms.producer.DrawMessageProducer;
import com.cm.manage.model.account.Draw;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.model.member.Member;
import com.cm.manage.service.account.IDrawService;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.util.alipay.AlipayBatchService;
import com.cm.manage.util.alipay.AlipayOrder;
import com.cm.manage.util.alipay.AlipayOrderItem;
import com.cm.manage.util.alipay.config.AlipayConfig;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CSVUtil;
import com.cm.manage.util.base.CommonBusiUtil;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.account.BatchTransferVO;
import com.cm.manage.vo.account.DrawTransferVO;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service("drawService")
public class DrawServiceImpl extends BaseServiceImpl implements IDrawService {
    private static final Logger logger = Logger.getLogger(DrawController.class);
    @Autowired
    private IDrawDao drawDao;
    @Autowired
    private IDrawTransferRecordDao drawTransferDao;

    @Resource(name = "accountInterface")
    private IAccountHttpService accountInterface;
    @Autowired
    private DrawMessageProducer drawMessageProducer;

    @Override
    public EasyuiDataGridJson drawList(EasyuiDataGrid dg, DrawVO draw) {
        return drawDao.drawList(dg, draw);
    }

    @Override
    public Map drawCount(DrawVO draw) {
        return drawDao.drawCount(draw);
    }

    @Override
    public Map drawDetail(DrawVO draw) throws BusiException {
        String orderId = draw.getOrderId();
        if (orderId == null || "".equals(orderId)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        Map data = drawDao.drawDetail(draw);
        if (data == null) {
            throw new BusiException("3004", "查询提现详情失败");
        }
        return data;
    }

    //审核
    @Override
    public Map<String, String> drawVerify(User user, DrawVO drawVO) throws BusiException {
        String orderId = drawVO.getOrderId();
        if (orderId == null || "".equals(orderId)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        Draw d = drawDao.getDrawByOrderId(drawVO.getOrderId());
        if (d == null) {
            throw new BusiException("3005", "提现申请不存在");
        }
        if (CommonConstants.DRAW_WAIT_REVIEW != d.getStatus()) {//只有状态为0即待审核状态的提现才可以进行审核操作
            throw new BusiException("3006", "该提现申请不可进行审核操作");
        }
        d.setStatus(drawVO.getStatus());
        if (user != null) {
            d.setPeopleAccepted(user.getName());
        }
        Date date = new Date();
        String acceptTime = DateUtil.format(date);
        d.setAcceptTime(DateUtil.format(acceptTime, "yyyy-MM-dd HH:mm:ss"));
        String memo = drawVO.getMemo();
        if (memo != null && memo.length() > 255) {
            memo = memo.substring(0, 255);
        }
        d.setMemo(memo);
        d.setRealFee(drawVO.getRealFee());
        String bankinfo = d.getDrawInfo();
        String[] bankArr = bankinfo.split("#");
        StringBuilder sb = new StringBuilder();
        sb.append(bankArr[0]);
        sb.append("#");
        sb.append(bankArr[1]);
        sb.append("#");
        sb.append(bankArr[2]);
        sb.append("#");
        sb.append(drawVO.getDrawInfo());
        sb.append("#");
        sb.append(bankArr[4]);
        sb.append("#");
        sb.append(bankArr[5]);
        sb.append("#");
        sb.append(bankArr[6]);
        d.setDrawInfo(sb.toString());
        drawDao.update(d);
        //准备日志数据
        String userName = drawDao.getUserName(d.getUserCode());
        Map<String, String> logDataMap = new HashMap<String, String>();
        logDataMap.put("userName", userName);
        logDataMap.put("amount", String.valueOf(d.getAmount()));
        return logDataMap;
    }

    //驳回
    @Override
    public Map<String, String> drawReject(User user, DrawVO drawVO) throws BusiException {
        String orderId = drawVO.getOrderId();
        if (orderId == null || "".equals(orderId)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        Draw d = drawDao.getDrawByOrderId(drawVO.getOrderId());
        if (d == null) {
            throw new BusiException("3005", "提现申请不存在");
        }
        if (CommonConstants.DRAW_WAIT_REVIEW != d.getStatus() && CommonConstants.DRAW_FAIL != d.getStatus()) {//只有状态为0即待审核状态2失败的提现才可以进行驳回操作
            throw new BusiException("3008", "该提现申请不可进行驳回操作");
        }
        //失败的提现查找所属的批次是否全部处理完毕
//        if (CommonConstants.DRAW_FAIL == d.getStatus()) {
//            DrawTransferRecord record = drawTransferDao.findByBatchNo(d.getTransferBatchId());
//            if (record != null && CommonConstants.Batch_TRANSFER_DOING == record.getStatus()) {
//                throw new BusiException("", "该提现批次还没有处理完毕不能进行驳回");
//            }
//        }
        //组装账户资金回退接口数据
        IncomingOperationBean operateionBean = new IncomingOperationBean();
        operateionBean.setAmount(d.getAmount());
        operateionBean.setOrderId(d.getOrderId());
        operateionBean.setEventCode(IncomingOperationBean.R00500);
        operateionBean.setMemo(IncomingOperationBean.R00500);
        operateionBean.setUserCode(d.getUserCode());
        List<IncomingOperationBean> beanList = new ArrayList<IncomingOperationBean>();
        beanList.add(operateionBean);
        boolean drawBack = false;
        try {
            logger.info("提现驳回时上送数据：userCode[" + d.getUserCode() + "],orderId[" + d.getOrderId() + "]," +
                    "amount[" + d.getAmount() + "],eventCode[" + IncomingOperationBean.R00500 + "]");
            drawBack = accountInterface.incomingOperation(beanList);
            logger.info("驳回时后台接口返回数据：" + drawBack);
        } catch (Exception e) {
            logger.error("提现驳回时后台接口异常", e);
            throw new BusiException("3009", "调用接口失败");
        }

        //退款失败
        if (!drawBack) {
            throw new BusiException("3010", "驳回失败");
        }
        //退款成功时更新数据库记录状态
        if (user != null) {
            drawVO.setPeopleAccepted(user.getName());
        }
        Date date = new Date();
        String acceptTime = DateUtil.format(date);
        drawVO.setAcceptTime(acceptTime);
        String memo = drawVO.getMemo();
        if (memo != null && memo.length() > 255) {
            memo = memo.substring(0, 255);
        }
        drawVO.setMemo(memo);
        drawDao.drawReject(drawVO);
        //准备日志数据
        String userName = drawDao.getUserName(d.getUserCode());
        Map<String, String> logDataMap = new HashMap<String, String>();
        logDataMap.put("userName", userName);
        logDataMap.put("amount", String.valueOf(d.getAmount()));

        try {
            Draw draw = drawDao.getDrawByOrderId(orderId);
            //提现成功
            Map<String, Object> drawMap = new HashMap<String, Object>();
            drawMap.put("userCode", draw.getUserCode());
            drawMap.put("orderId", draw.getOrderId());
            drawMap.put("amount", draw.getAmount());
            drawMap.put("drawTime", DateUtil.format(draw.getCreateTime()));
            drawMap.put("bankCode", "");
            drawMap.put("status",0);
            drawMessageProducer.sendMessage("draw", drawMap);
        } catch (Exception e) {
            logger.error("提现失败外发消息(" + orderId + "):" + e.getMessage());
        }

        return logDataMap;
    }

    //***批量审核操作
    @Override
    public void batchDrawVerify(User user, String orderIdArrStr, DrawVO drawVO) throws BusiException {
        if (orderIdArrStr == null || "".equals(orderIdArrStr)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        String[] orderIdArray = orderIdArrStr.split("\\|");
        if (orderIdArray == null || orderIdArray.length == 0) {
            throw new BusiException("3001", "流水编号丢失");
        }
        if (orderIdArray.length > 100) {
            throw new BusiException("3002", "批处理数据过多，最多同时操作100条记录");
        }
        List<String> argsList = Arrays.asList(orderIdArray);
        List<Draw> drawList = drawDao.findDrawList(argsList);
        if (drawList != null && drawList.size() > 0) {
        	if (user != null) {
                drawVO.setPeopleAccepted(user.getName());
            }
            Date date = new Date();
            String acceptTime = DateUtil.format(date);
            drawVO.setAcceptTime(acceptTime);
            String memo = drawVO.getMemo();
            if (memo != null && memo.length() > 255) {
                memo = memo.substring(0, 255);
            }
            drawVO.setMemo(memo);
            Iterator it = drawList.iterator();
            while (it.hasNext()) {
                Draw draw = (Draw) it.next();
                if (draw.getStatus() != CommonConstants.DRAW_WAIT_REVIEW) {
                    it.remove();
                    continue;
                }
                int vipRank = drawDao.getVipRank(draw.getUserCode());
                if(vipRank > 0){
                	drawVO.setRealFee(0.0);
                }else{
                	drawVO.setRealFee(draw.getRealFee());
                }
                drawDao.updateDraw(draw, drawVO);
            }
        } else {
            throw new BusiException("", "查找提现记录失败");
        }
    }

    /**
     * *
     * 重审等操作
     */
    @Override
    public Map<String, String> updateDraw(User user, DrawVO drawVO, String operation) throws BusiException {
        String orderId = drawVO.getOrderId();
        if (orderId == null || "".equals(orderId)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        Draw d = drawDao.getDrawByOrderId(orderId);
        if (d == null) {
            throw new BusiException("3005", "提现申请不存在");
        }
        //重新审核只能操作状态为1即为已审核2失败的提现记录
        if (CommonConstants.DRAW_OPERATE_REVERIFY.equals(operation)) {
            if (CommonConstants.DRAW_HAS_REVIEW != d.getStatus() && CommonConstants.DRAW_FAIL != d.getStatus()) {
                throw new BusiException("3013", "该提现申请不可进行重审操作");
            }
            //失败的提现查找所属的批次是否全部处理完毕
            if (CommonConstants.DRAW_FAIL == d.getStatus()) {
                DrawTransferRecord record = drawTransferDao.findByBatchNo(d.getTransferBatchId());
                if (record != null && CommonConstants.Batch_TRANSFER_DOING == record.getStatus()) {
                    throw new BusiException("", "该提现批次还没有处理完毕不能进行重审");
                }
            }
        } else {
            throw new BusiException("3012", "操作参数传递错误");
        }
        int vipRank = drawDao.getVipRank(d.getUserCode());
        if(vipRank > 0){
        	d.setRealFee(0.0);
        }else{
        	d.setRealFee(d.getFee());
        }
        if (user != null) {
            drawVO.setPeopleAccepted(user.getName());
        }
        Date date = new Date();
        String acceptTime = DateUtil.format(date);
        d.setAcceptTime(DateUtil.format(acceptTime, "yyyy-MM-dd HH:mm:ss"));
        String memo = drawVO.getMemo();
        if (memo != null && memo.length() > 255) {
            memo = memo.substring(0, 255);
        }
        d.setMemo(memo);
        if (user != null) {
            d.setPeopleAccepted(user.getName());
        }
        d.setStatus(drawVO.getStatus());
        d.setErrorMsg("");
        drawDao.update(d);
        //准备日志数据
        String userName = drawDao.getUserName(d.getUserCode());
        Map<String, String> logDataMap = new HashMap<String, String>();
        logDataMap.put("userName", userName);
        logDataMap.put("amount", String.valueOf(d.getAmount()));
        return logDataMap;
    }

    @Override
    public Draw findDraw(String orderId) {
        return drawDao.getDrawByOrderId(orderId);
    }

    @Override
    public List<Draw> findDrawList(List<String> argsList) {
        return drawDao.findDrawList(argsList);
    }

    @Override
    public Map<String, String> batchTransfer(User user, DrawVO drawVO, String orderIdStr) throws BusiException {
        //流水编号丢失
        if (orderIdStr == null || "".equals(orderIdStr)) {
            throw new BusiException("3001", "流水编号丢失");
        }
        //流水编号丢失
        String[] orderIdArray = orderIdStr.split("\\|");
        if (orderIdArray == null || orderIdArray.length == 0) {
            throw new BusiException("3001", "流水编号丢失");
        }
        //批处理数据过多，最多同时操作50条记录
        if (orderIdArray.length > 100) {
            throw new BusiException("3002", "批处理数据过多，最多同时操作100条记录");
        }
        List<String> argsList = Arrays.asList(orderIdArray);
        List<Draw> drawList = drawDao.findDrawList(argsList);
        if (drawList == null || drawList.size() == 0) {
            throw new BusiException("3019", "查询记录失败");
        }
        List<DrawTransferVO> transferList = new ArrayList<DrawTransferVO>();
        DrawTransferVO transferVO = null;
        Double totalAmount = 0.0;//总金额
        int totalNumber = 0;//总笔数
        String batchNo = CSVUtil.getBatchNo();
        logger.info("本次提现转账生成的批次号【" + batchNo + "】");
        //过滤不满足条件的提现转账记录
        Iterator it = drawList.iterator();
        while (it.hasNext()) {
            Draw d = (Draw) it.next();
            if (d == null || d.getStatus() == null || CommonConstants.DRAW_HAS_REVIEW != d.getStatus()) {
                it.remove();
                continue;
            }
            String drawInfo = d.getDrawInfo();
            //提现信息不完整，不做提现操作
            if (!CommonUtil.isNotEmpty(d.getDrawInfo())) {
                it.remove();
                continue;
            }
            String[] infoArray = drawInfo.split("#");
            if (infoArray.length < 7) {
                it.remove();
                continue;
            }
            //提现户名、卡号、开户银行名称必须有
            if (!CommonUtil.isNotEmpty(infoArray[5]) || !CommonUtil.isNotEmpty(infoArray[4]) || !CommonUtil.isNotEmpty(infoArray[2])) {
                it.remove();
                continue;
            }
            //提现金额必须有
            Double realFee = d.getRealFee();
            Double amount = d.getAmount();
            if (amount == null || amount <= 0.0) {
                it.remove();
                continue;
            }
            Double realAmount = CommonUtil.sub(amount, realFee);
            //实际提现金额不能小于等于0
            if (realAmount <= 0.0) {
                it.remove();
                continue;
            }
            totalAmount = CommonUtil.add(totalAmount, realAmount);
            totalNumber = totalNumber + 1;
            transferVO = new DrawTransferVO();
            transferVO.setBatchNo(batchNo);
            transferVO.setOrderId(d.getOrderId());
            transferVO.setRealName(infoArray[5]);
            transferVO.setBankCardNo(infoArray[4]);
            transferVO.setBankName(infoArray[2]);
            transferVO.setProvince(infoArray[0]);
            transferVO.setCity(infoArray[1]);
            if (infoArray[3].equals("null")) {
                transferVO.setSubBankName(" ");
            } else {
                transferVO.setSubBankName(CSVUtil.trim(infoArray[3]));
            }
            transferVO.setAmount(realAmount);
            transferVO.setCustomerType(CommonConstants.DRAW_TO_PERSONAL_FLAG);//对私
            transferVO.setNotes("");//备注
            transferList.add(transferVO);
        }
        //判断过滤之后是否还有需要处理的数据
        if (drawList == null || drawList.size() == 0) {
            throw new BusiException("3020", "没有可处理的有效数据");
        }
        ////抬头数据：日期	总金额	总笔数	支付宝账号(Email)
        List<String> headData = new ArrayList<String>();
        Date date = new Date();
        headData.add(DateUtil.format(date, "yyyyMMdd"));
        headData.add(CommonUtil.formatDouble(totalAmount.doubleValue(), "#.00"));
        headData.add(totalNumber + "");
        headData.add(CSVUtil.getPropertyValue("ALIPAY_SELLER"));

        String outputPath = CSVUtil.getPropertyValue("batchfilepath");
        String realFile = outputPath + "/" + batchNo + CommonConstants.TRANSFER_FILE_CSV_SUFFIX;
        try {
            CSVUtil.createCSVFile(batchNo, outputPath, headData, transferList);
        } catch (Exception e) {
            logger.error("生成转账文件失败", e);
            throw new BusiException("3017", "生成转账文件失败");
        }

        DrawTransferRecord record = null;
        try {
            //将该批次信息保存到数据库中,同时更新提现记录的批次号和状态信息，两者同时成功或者同时失败
            record = new DrawTransferRecord();
            record.setBatchNo(batchNo);
            String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
            record.setCreateTime(DateUtil.format(dateStr));
            record.setAcceptTime(DateUtil.format(dateStr));
            record.setPeopleAccepted(user.getName());
            record.setTotalNumber(totalNumber);
            record.setTotalAmount(totalAmount);
            record.setSuccessAmount(0.0);
            record.setSuccessNumber(0);
            record.setFailAmount(0.0);
            record.setFailNumber(0);
            record.setTransferFilename(realFile);
            record.setStatus(CommonConstants.Batch_TRANSFER_DOING);//处理中
            drawTransferDao.save(record);

            for (int i = 0; i < drawList.size(); i++) {
                Draw d = drawList.get(i);
                d.setTransferBatchId(batchNo);//批次号
                d.setStatus(CommonConstants.DRAW_TRANSFERING);//转账中
                String memo = drawVO.getMemo();
                if (memo != null && memo.length() > 255) {
                    memo = memo.substring(0, 255);
                }
                d.setMemo(memo);
                drawDao.update(d);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new BusiException("3016", "保存转账记录失败");
        }

        //开始发送文件到支付宝
        String successFlag = "文件上传成功!";
        String res_1 = "";//第一步返回的处理结果
        String res_2 = "";//第二步返回的处理结果
        try {
            res_1 = AlipayBatchService.batchFileToAlipayReq(realFile);
            logger.info("批次号[ " + batchNo + "],文件上送支付宝返回的数据[" + res_1 + "]");
        } catch (Exception e) {
            logger.error("批次号[ " + batchNo + "],文件上送支付宝请求失败", e);
        }
        //文件上传成功之后，发送复核文件请求到支付宝
        if (res_1 != null && successFlag.equals(res_1)) {
            try {
                res_2 = AlipayBatchService.reViewFileToAlipayReq(batchNo + CommonConstants.TRANSFER_FILE_CSV_SUFFIX);
                logger.info("批次号[ " + batchNo + "],请求复核文件支付宝返回的数据[" + res_2 + "]");
            } catch (Exception e) {
                logger.error("批次号[ " + batchNo + "],请求复核文件失败", e);
            }
        }
        //文件上传失败或者复核过程中操作失败，
        if (!successFlag.equals(res_1) || res_2 == null || res_2.equals("")) {
            String batchErrorMsg = "批次文件处理失败";
            String drawErrorMsg = "交易请求失败";
            if (res_1 != null && res_1.length() > 7) {
                batchErrorMsg = res_1.substring(7, res_1.length());
                drawErrorMsg = res_1.substring(7, res_1.length());
            }
            try {
                record.setStatus(CommonConstants.Batch_TRANSFER_FAIL);//失败
                record.setErrorCode(batchErrorMsg);
                record.setFailAmount(record.getTotalAmount());
                record.setFailNumber(record.getTotalNumber());
                record.setSuccessNumber(0);
                record.setSuccessAmount(0.0);
                drawTransferDao.update(record);
                for (int i = 0; i < drawList.size(); i++) {
                    Draw d = drawList.get(i);
                    d.setStatus(CommonConstants.DRAW_FAIL);//失败
                    d.setErrorMsg(drawErrorMsg);
                    drawDao.update(d);
                }
            } catch (Exception e) {
                logger.error("更新提现记录状态或者转账记录状态失败");
                logger.error(e);
                throw new BusiException("", "转账失败");
            }
            res_2 = "";
        }
        Map<String, String> res = new HashMap<String, String>();
        res.put("batchNo", batchNo);
        res.put("alipayUrl", res_2);
        return res;
    }

    @Override
    public void updateDrawBankInfo(AlipayOrderItem item) {
        try {
            Draw d = drawDao.getDrawByOrderId(item.getUserSerialNo());
            DrawBindBank bank = drawDao.findDrawBankByUsercode(d.getUserCode());
            String nowStr = DateUtil.format(new Date());
            Date today = DateUtil.format(nowStr, "yyyy-MM-dd HH:mm:ss");
            if (bank == null) {
                bank = new DrawBindBank();
                bank.setCreateTime(today);
                bank.setUserCode(d.getUserCode());
                bank.setBankName(item.getBankName());
                bank.setProvince(item.getBankProvince());
                bank.setCity(item.getBankCity());
                bank.setSubBank(item.getSubBankName());
                bank.setBankCode(item.getBankCardNo());
                bank.setEasyFlag(CommonConstants.DRAW_ACCOUNT_HAS_BINDDING);//绑定
                drawDao.saveBankInfo(bank);
            } else if (CommonConstants.DRAW_ACCOUNT_HAS_BINDDING != bank.getEasyFlag()) {//未绑定
                bank.setUpdateTime(today);
                bank.setProvince(item.getBankProvince());
                bank.setCity(item.getBankCity());
                bank.setSubBank(item.getSubBankName());
                bank.setBankName(item.getBankName());
                bank.setBankCode(item.getBankCardNo());
                bank.setEasyFlag(CommonConstants.DRAW_ACCOUNT_HAS_BINDDING);//绑定
                drawDao.updateBankInfo(bank);
            }
        } catch (Exception e) {
            logger.error("更新提现银行信息表失败");
            logger.error(e);
        }
    }

    @Override
    public void updateDrawInfo(List<AlipayOrderItem> items, DrawTransferRecord d) {
        if (items != null && items.size() > 0) {
            for (AlipayOrderItem item : items) {
                int status = AlipayOrder.converStatus(item.getStatus());
                Draw draw = drawDao.getDrawByOrderId(item.getUserSerialNo());
                if (draw != null && draw.getStatus() == CommonConstants.DRAW_TRANSFERING && CommonConstants.DRAW_TRANSFERING != status) {
                    draw.setStatus(status);
                    draw.setAlipayDrawNo(item.getInstructionId());
                    draw.setErrorMsg(item.getDealMemo());
                    draw.setAcceptTime(new Date());
                    drawDao.update(draw);
                    if (status == CommonConstants.DRAW_SUCCESS) {//提现成功的更新提现银行信息表
                        updateDrawBankInfo(item);
                        try {
                            //提现成功
                            String bankCode = item.getBankCardNo();
                            Map<String, Object> drawMap = new HashMap<String, Object>();
                            drawMap.put("userCode", draw.getUserCode());
                            drawMap.put("orderId", draw.getOrderId());
                            drawMap.put("amount", draw.getAmount());
                            drawMap.put("drawTime", DateUtil.format(draw.getCreateTime()));
                            drawMap.put("bankCode", bankCode.substring(bankCode.length() - 4, bankCode.length()));
                            drawMap.put("status",1);
                            drawMessageProducer.sendMessage("draw", drawMap);
                        } catch (Exception e) {
                            logger.error("提现成功外发消息(" + draw.getOrderId() + "):" + e.getMessage());
                        }
                    }
                }
            }
        }
        drawTransferDao.update(d);
    }

    @Override
    public void updateDrawTransferRecord(String batchNo) throws Exception {
        if (batchNo == null && "".equals(batchNo)) {
            throw new BusiException("", "批次信息丢失");
        }
        logger.info("开始处理批次号为[" + batchNo + "]数据");
        DrawTransferRecord d = null;
        try {
            d = drawTransferDao.findByBatchNo(batchNo);
        } catch (Exception e) {
            logger.error("根据批次号[" + batchNo + "]查询批次信息失败", e);
            throw new BusiException("", "根据批次号[" + batchNo + "]查询批次信息失败");
        }
        if (d == null) {
            logger.info("批次号[" + batchNo + "]记录不存在");
            throw new BusiException("", "批次号[" + batchNo + "]记录不存在");
        }
        if (CommonConstants.Batch_TRANSFER_DOING == d.getStatus()) {
            //开始调用支付宝明细查询接口,构造明细查询请求参数
            String fileName = batchNo + CommonConstants.TRANSFER_FILE_CSV_SUFFIX;
            Map<String, String> queryParams = new HashMap<String, String>();
            queryParams.put("file_name", fileName);
            String xml = AlipayBatchService.queryBatchDetail(queryParams);
            logger.info("支付宝返回的批次[" + batchNo + "]明细数据：\n" + xml);
            if (xml == null || "".equals(xml)) {
                throw new BusiException("", "查询批次号[" + batchNo + "]明细，支付宝返回的数据为空");
            }
            AlipayOrder order = new AlipayOrder();
            try {
                order.fromXml(xml);
            } catch (Exception e) {
                logger.error("系统处理批次号[" + batchNo + "]返回的明细查询数据失败", e);
                throw new BusiException("", "系统处理批次[" + batchNo + "]的明细数据失败");
            }
            if (CommonConstants.ALIPAY_RESULT_FAIL.equals(order.getIs_success())) {//查询明细失败
                logger.info("批次[" + batchNo + "]明细查询失败,支付宝返回的错误码:" + order.getError());
                //异常输出
                throw new BusiException("", "查询批次[" + batchNo + "]明细失败,支付宝返回的错误码：" + order.getError());
            } else if (CommonConstants.ALIPAY_RESULT_SUCCESS.equals(order.getIs_success())) {//查询明细成功
                int resultNum = Integer.parseInt(order.getPageSize());//头部声明的记录数
                List<AlipayOrderItem> items = order.getItems();//解析xml得到的记录数
                if (order.getTotalNumber() != resultNum) {
                    logger.info("批次号[" + batchNo + "]明细查询,支付宝声明的记录数:" + resultNum + ",系统解析XML文件获取的记录数量:" + items.size());
                    //异常输出
                    throw new BusiException("", "支付宝返回的交易数据不正确");
                }
                try {
                    if (resultNum == order.getFailNumber()) {//全部失败则该批次失败
                        d.setStatus(CommonConstants.Batch_TRANSFER_FAIL);//失败
                    } else if (resultNum > (order.getFailNumber() + order.getSuccessNumber())) {
                        d.setStatus(CommonConstants.Batch_TRANSFER_DOING);//处理中
                    } else {
                        d.setStatus(CommonConstants.Batch_TRANSFER_SUCCESS);//有一笔成功则成功
                    }
                    d.setSuccessAmount(order.getSuccessAmount());
                    d.setSuccessNumber(order.getSuccessNumber());
                    d.setFailAmount(order.getFailAmount());
                    d.setFailNumber(order.getFailNumber());
                    Date date = new Date();
                    String dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
                    d.setAcceptTime(DateUtil.format(dateStr));
                    updateDrawInfo(items, d);
                } catch (Exception e) {
                    logger.error(e);
                    throw new BusiException("", "更新提现记录状态失败");
                }
            }
        }

    }

    @Override
    public EasyuiDataGridJson transferList(EasyuiDataGrid dg, BatchTransferVO transferVO) {
        return drawTransferDao.transferList(dg, transferVO);
    }

    /**
     * @throws BusiException
     * @describe 准备导出提现数据
     */
    @Override
    public Map<String, Object> prepareExcelData(String orderIdStr, DrawVO drawParamVO) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            //流水编号丢失
            if (orderIdStr == null || "".equals(orderIdStr)) {
                throw new BusiException("3001", "流水编号丢失");
            }
            //流水编号丢失
            String[] orderIdArray = orderIdStr.split("\\|");
            if (orderIdArray == null || orderIdArray.length == 0) {
                throw new BusiException("3001", "流水编号丢失");
            }
            //批处理数据过多，最多同时操作100条记录
            if (orderIdArray.length > 100) {
                throw new BusiException("3002", "批处理数据过多，最多同时操作100条记录");
            }
            List<String> argsList = Arrays.asList(orderIdArray);
            List<Draw> drawList = drawDao.findDrawList(argsList);
            if (drawList == null || drawList.size() == 0) {
                throw new BusiException("3019", "查询提现记录失败");
            }
            List<DrawVO> drawVOList = new ArrayList<DrawVO>();
            Double totalAmount = 0.0;
            int totalNum = 0;
            DrawVO drawVO = null;
            Member m = null;
            for (Draw draw : drawList) {
                if (draw != null) {
                    totalNum = totalNum + 1;
                    drawVO = new DrawVO();
                    drawVO.setOrderId(draw.getOrderId());
                    m = drawDao.findMemberByUserCode(draw.getUserCode());
                    if (m != null) {
                        drawVO.setUserName(m.getUserName());
                        drawVO.setVipName(CommonBusiUtil.getVipRankName(m.getVip()));
                        drawVO.setRealName(m.getRealName());
                    }
                    if (draw.getCreateTime() != null) {
                        drawVO.setCreateTime(DateUtil.format(draw.getCreateTime()));
                    } else {
                        drawVO.setCreateTime(" ");
                    }
                    if (draw.getAcceptTime() != null) {
                        drawVO.setAcceptTime(DateUtil.format(draw.getAcceptTime()));
                    } else {
                        drawVO.setAcceptTime(" ");
                    }
                    if (draw.getDrawLimit() != null) {
                        drawVO.setDrawBeforeLimit(draw.getDrawLimit());
                    } else {
                        drawVO.setDrawBeforeLimit(0.0);
                    }
                    Double amount = draw.getAmount();
                    if (amount == null) {
                        amount = 0.0;
                    }
                    drawVO.setAmount(amount);
                    if (draw.getFee() != null) {
                        drawVO.setFee(draw.getFee());
                    } else {
                        drawVO.setFee(0.0);
                    }
                    Double realFee = draw.getRealFee();
                    if (realFee == null) {
                        realFee = 0.0;
                    }
                    drawVO.setRealFee(realFee);
                    Double realAmount = CommonBusiUtil.sub(amount, realFee);
                    totalAmount = CommonBusiUtil.add(totalAmount, realAmount);
                    drawVO.setRealAmount(realAmount);
                    drawVO.setStatusName(CommonBusiUtil.getDrawStatusName(draw.getStatus()));
                    if (draw.getErrorMsg() != null) {
                        drawVO.setErrorMsg(draw.getErrorMsg());
                    } else {
                        drawVO.setErrorMsg(" ");
                    }
                    if (draw.getPeopleAccepted() != null) {
                        drawVO.setPeopleAccepted(draw.getPeopleAccepted());
                    } else {
                        drawVO.setPeopleAccepted(" ");
                    }
                    if (draw.getMemo() != null) {
                        drawVO.setMemo(draw.getMemo());
                    } else {
                        drawVO.setMemo(" ");
                    }
                    drawVOList.add(drawVO);
                }
            }
            resMap.put("drawList", drawVOList);
            resMap.put("totalAmount", totalAmount);
            if (drawParamVO.getCreateStartDate() != null) {
                resMap.put("createStartTime", drawParamVO.getCreateStartDate());
            } else {
                resMap.put("createStartTime", " ");
            }
            if (drawParamVO.getCreateEndDate() != null) {
                resMap.put("createEndTime", drawParamVO.getCreateEndDate());
            } else {
                resMap.put("createEndTime", " ");
            }
            if (drawParamVO.getAcceptStartDate() != null) {
                resMap.put("acceptStartTime", drawParamVO.getAcceptStartDate());
            } else {
                resMap.put("acceptStartTime", " ");
            }
            if (drawParamVO.getAcceptEndDate() != null) {
                resMap.put("acceptEndTime", drawParamVO.getAcceptEndDate());
            } else {
                resMap.put("acceptEndTime", " ");
            }
            resMap.put("totalNum", totalNum);
            resMap.put("accountNo", AlipayConfig.account);
        } catch (Exception e) {
            logger.error("下载提现数据失败");
            logger.error(e);
        }
        return resMap;
    }

    @Override
    public DrawTransferRecord findDrawTranfserRecordByBatchNo(String batchNo) {
        return drawTransferDao.findByBatchNo(batchNo);
    }

	@Override
	public List<Map> querySubbankInfo(String province, String city,
			String bankname, String subbankName) {
		return drawDao.getSubbankNameList(province, city, bankname, subbankName);
	}
}
