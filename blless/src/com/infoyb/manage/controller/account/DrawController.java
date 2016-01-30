package com.cm.manage.controller.account;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.account.IDrawService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.account.BatchTransferVO;
import com.cm.manage.vo.account.DrawVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/drawController")
public class DrawController extends BaseController {
    private static final Logger logger = Logger.getLogger(DrawController.class);
    @Autowired
    private IDrawService drawService;
    
    @RequestMapping(params = "draw")
    public String draw() {
        return "/account/drawList";
    }

    /**
     * 用户资金查询
     *
     * @param dataGrid
     * @return
     */
    @RequestMapping(params = "drawList")
    @ResponseBody
    public EasyuiDataGridJson list(EasyuiDataGrid dataGrid, DrawVO drawVO) {
        return drawService.drawList(dataGrid, drawVO);
    }

    @RequestMapping(params = "drawCount")
    @ResponseBody
    public Json count(DrawVO drawVO) {
        Json j = new Json();
        j.setObj(drawService.drawCount(drawVO));
        j.setSuccess(true);
        return j;
    }
    @RequestMapping(params = "drawDetail")
    @ResponseBody
    public Json detail(DrawVO drawVO) {
    	Json j = new Json();
    	j.setSuccess(true);
    	try{
        	j.setObj(drawService.drawDetail(drawVO));
        }catch(BusiException be){
        	logger.error("查询提现详情失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("查询提现详情失败系统异常", e);
        	j.setMsg("查询提现详情失败");
        	j.setSuccess(false);
        }
        return j;
    }
    /***
     * @describe 提现申请审核
     * @param request
     * @param drawVO
     * @return
     */
    @RequestMapping(params = "drawVerify")
    @ResponseBody
    public Json verify(HttpServletRequest request,DrawVO drawVO) {
    	Json j = new Json();
    	j.setSuccess(true);
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	User user = sessionInfo.getUser();
    	try{
        	Map<String,String> logDataMap = drawService.drawVerify(user, drawVO);
        	String desc = "用户(" + logDataMap.get("userName") + ")提现" + logDataMap.get("amount") + "元申请被审核";
        	saveLog(request, "drawController.do?drawVerify", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("提现申请审核失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("提现申请审核失败系统异常", e);
        	j.setMsg("提现申请审核失败");
        	j.setSuccess(false);
        }
    	return j;
    }
    /***
     * @describe 驳回提现申请
     * @param request
     * @param drawVO
     * @return
     */
    @RequestMapping(params = "drawReject")
    @ResponseBody
    public Json reject(HttpServletRequest request,DrawVO drawVO) {
    	Json j = new Json();
    	j.setSuccess(true);
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	User user = sessionInfo.getUser();
    	try{
    		Map<String,String> logDataMap = drawService.drawReject(user, drawVO);

        	String desc = "用户(" + logDataMap.get("userName") + ")提现" + logDataMap.get("amount") + "元申请被驳回";
        	saveLog(request, "drawController.do?drawReject", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("提现申请驳回失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("提现申请驳回失败系统异常", e);
        	j.setMsg("提现申请驳回失败");
        	j.setSuccess(false);
        }
    	return j;
    }
    /***
     * @describe 批量审核
     * @param request
     * @param drawVO
     * @param orderIdArrStr
     * @return
     */
    @RequestMapping(params = "drawBatch")
    @ResponseBody
    public Json batch(HttpServletRequest request,DrawVO drawVO,String orderIdArrStr) {
    	Json j = new Json();
    	j.setSuccess(true);
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	User user = sessionInfo.getUser();
    	try{
        	drawService.batchDrawVerify(user, orderIdArrStr, drawVO);
        	String desc = "订单(" + orderIdArrStr + ")提现申请批量审核通过";
        	saveLog(request, "drawController.do?drawBatch", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("批量审核失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("批量审核失败系统异常", e);
        	j.setMsg("批量审核失败");
        	j.setSuccess(false);
        }
        return j;
    }
    
    /***
     * @describe 强制将提现申请置为成功状态或者打回重新审核
     * @param request
     * @param drawVO
     * @return
     */
    @RequestMapping(params = "drawOther")
    @ResponseBody
    public Json otherHandle(HttpServletRequest request,DrawVO drawVO,String operation) {
    	logger.debug("operation--->" + operation);
    	Json j = new Json();
    	j.setSuccess(true);
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	User user = sessionInfo.getUser();
    	try{
        	Map<String,String> logDataMap = drawService.updateDraw(user, drawVO, operation);
        	String desc = "用户(" + logDataMap.get("userName") + ")提现" + logDataMap.get("amount") + "元申请重新审核";
        	saveLog(request, "drawController.do?drawOther", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("操作失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("操作失败系统异常", e);
        	j.setMsg("操作失败");
        	j.setSuccess(false);
        }
        return j;
    }
    /***
     * @describe 批量转帐
     * @param request
     * @param drawVO
     * @param orderIdArrStr
     * @return
     */
    @RequestMapping(params = "drawBatchTransfer")
    @ResponseBody
    public Json batchTransfer(HttpServletRequest request,DrawVO drawVO,String orderIdArrStr) {
        Json j = new Json();
        j.setSuccess(true);
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	User user = sessionInfo.getUser();
    	Map<String,String> result = null;;
        try{
        	result = drawService.batchTransfer(user, drawVO, orderIdArrStr);
        	j.setObj(result);
        	String desc = "批次(" + result.get("batchNo") + ")批量转账";
        	saveLog(request, "drawController.do?drawBatchTransfer", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("批量转账失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("批量转账系统异常", e);
        	j.setMsg("批量转账失败");
        	j.setSuccess(false);
        }
        return j;
    }
    @RequestMapping(params = "updateBatchTransfer")
    @ResponseBody
    public Json updateBatchTransfer(HttpServletRequest request,String batchNo) {
        Json j = new Json();
        j.setSuccess(true);
        try{
        	drawService.updateDrawTransferRecord(batchNo);
        	String desc = "确定批次(" + batchNo + ")转账状态";
        	saveLog(request, "drawController.do?updateBatchTransfer", CommonConstants.LOG_FOR_ACCOUNT, desc);
        }catch(BusiException be){
        	logger.error("处理提现记录状态失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
        	logger.error("更新提现记录失败", e);
        	j.setMsg("处理数据失败");
        	j.setSuccess(false);
        }
        return j;
    }
    /***
     * @describe 转账列表数据
     * @param dg
     * @param transferVO
     * @return
     */
    @RequestMapping(params = "transferList")
    @ResponseBody
    public EasyuiDataGridJson transferList(EasyuiDataGrid dg,BatchTransferVO transferVO){
    	return drawService.transferList(dg, transferVO);
    }
    /***
     * @describe 下载提现Excel数据文件
     * @param orderIdArr
     * @return
     */
    @RequestMapping(params = "downloadExcelFile")
    public String downloadExcelFile(String exportOrderId,ModelMap mm,DrawVO drawVO){
    	Map<String,Object> resMap = drawService.prepareExcelData(exportOrderId,drawVO);
    	mm.addAttribute("drawMap", resMap);
    	return "downloadCommonExcel";
    }
    /***
     * @describe 支行信息查找
     * @return
     */
    @RequestMapping(params = "querysubbank")
	@ResponseBody
	public Json querySubbank(String province, String city,
			String bankname, String subbankName){
		Json j = new Json();
		j.setSuccess(true);
		try{
			List<Map> resList = drawService.querySubbankInfo(province, city, bankname, subbankName);
			j.setObj(resList);
		}catch(Exception e){
			logger.error("查找支行信息失败",e);
			j.setSuccess(false);
		}
		return j;
	}
}
