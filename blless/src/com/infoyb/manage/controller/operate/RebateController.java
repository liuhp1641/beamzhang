package com.cm.manage.controller.operate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.operate.IRebateService;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.operate.TaskRebateUserVO;
import com.cm.manage.vo.operate.TaskRebateVO;

@Controller
@RequestMapping("/rebateController")
public class RebateController extends BaseController {

    private static final Logger logger = Logger.getLogger(RebateController.class);

    @Autowired
    private ILotteryControlService lotteryControlService;
    
    @Autowired
    private IRebateService rebateService;
   
    /**
     * 跳转到购彩返利设置
     *
     * @return
     */
    @RequestMapping(params = "toRebate")
    public String toRebate() {
    	
        return "/rebate/rebate";
    }


    /**
     * 购彩返利列表
     *
     * @return
     */
    @RequestMapping(params = "rebateList")
    @ResponseBody
    public EasyuiDataGridJson rebateList(EasyuiDataGrid dg,TaskRebateVO rebateVo) {
      
        return rebateService.rebateList(dg,rebateVo);
    }

    /**
     * 跳转新建返利方案页面
     * @return
     */
    @RequestMapping(params="toRebateActivity")
    public String toRebateActivity(HttpServletRequest request){
    	List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
    	return "/rebate/rebateActivity";
    }
    /**
     * 返利方案保存
     * 
     */
    @RequestMapping(params="saveRebateActivity")
    @ResponseBody
    public Json saveRebateActivity(HttpServletRequest request,TaskRebateVO rebate){
    	Json j=new Json();
    	try{
    		StringBuilder str=new StringBuilder("");
    		TaskRebateVO existVo=rebateService.isExistRebate(rebate.getLotteryCodes(), rebate.getVipLow(), rebate.getVipHigh(), rebate.getStartTime(), rebate.getEndTime());
    		if(CommonUtil.isNotEmpty(existVo)){
    			str.append("您创建的返利方案与<span style='color:blue'>【");
    			str.append(existVo.getRebateName());
    			str.append("】</span>的条件有重叠，请修改<span style='color:blue'>【");
    			str.append(existVo.getRebateName());
    			str.append("】</span>的返利条件");
    		}else{
    			str.append("返利活动创建成功");
    		}
    		rebateService.saveRebate(rebate);
    		saveLog(request,"/rebateController.do?saveRebateActivity", CommonConstants.LOG_FOR_REBATE, "创建返利方案："+rebate.getRebateName());
    		j.setSuccess(true);
    		j.setMsg(str.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setMsg("返利活动创建失败");
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 判断同条件且上线的返利方案是否存在
     * @param rebate
     * @return
     */
    @RequestMapping(params="rebateExist")
    @ResponseBody
    public Json rebateExist(TaskRebateVO rebate){
    	Json j=new Json();
    	try{
    		StringBuilder str=new StringBuilder("");
    		TaskRebateVO existVo=rebateService.isExistRebate(rebate.getLotteryCodes(), rebate.getVipLow(), rebate.getVipHigh(), rebate.getStartTime(), rebate.getEndTime());
    		if(CommonUtil.isNotEmpty(existVo)){
    			str.append("您操作上线返利方案与<span style='color:blue'>【");
    			str.append(existVo.getRebateName());
    			str.append("】</span>的条件有重叠，无法上线，请重新编辑该活动或下线<span style='color:blue'>【");
    			str.append(existVo.getRebateName());
    			str.append("】</span>活动方案");
    			j.setSuccess(false);
    			j.setMsg(str.toString());
    		}else{
    			j.setSuccess(true);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setMsg(e.getMessage());
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 返利方案状态修改
     * 
     * @return
     */
    @RequestMapping(params="updateActivityStatus")
    @ResponseBody
    public Json updateActivityStatus(HttpServletRequest request,String operate,String rebateId,String rebateName,Integer status){
    	Json j=new Json();
    	try{
    		boolean flag=rebateService.updateActivityStatus(rebateId, status);
    		if(flag){
    			saveLog(request,"/rebateController.do?updateActivityStatus", CommonConstants.LOG_FOR_REBATE, "("+rebateName+")返利方案"+operate);
    		}
    		j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
 
    /**
     * 返利方案开始
     * @param request
     * @param rebateId
     * @param rebateName
     * @return
     */
    @RequestMapping(params="activityStart")
    @ResponseBody
    public Json activityStart(HttpServletRequest request,String rebateId,String rebateName){
    	Json j=new Json();
    	try{
    		boolean flag=rebateService.activityStart(rebateId);
    		if(flag){
    			saveLog(request,"/rebateController.do?activityStart", CommonConstants.LOG_FOR_REBATE, "("+rebateName+")返利方案开始");
    		}
    		j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
  
    /**
     * 返利活动编辑
     * @param request
     * @param rebateId
     * @return
     */
    @RequestMapping(params="toRebateActivityEdit")
    public String toRebateActivityEdit(HttpServletRequest request,String rebateId){
    	TaskRebateVO rebate=rebateService.rebateInfo(rebateId);
    	request.setAttribute("rebate", rebate);
    	List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
		return "/rebate/rebateActivityEdit";
    	
    }
    /**
     * 返利活动详情
     * @param request
     * @param rebateId
     * @return
     */
    @RequestMapping(params="toRebateActivityDetail")
    public String toRebateActivityDetail(HttpServletRequest request,String rebateId){
    	TaskRebateVO rebate=rebateService.rebateInfo(rebateId);
    	request.setAttribute("rebate", rebate);
		return "/rebate/rebateActivityDetail";
    	
    }
    /**
     * 返利日志界面
     * @return
     */
    @RequestMapping(params="toRebateLog")
    public String toRebateLog(){
    	
    	return "/rebate/rebateLog";
    }
    /**
     * 返利日志列表
     * @param dg
     * 
     * @return
     */
    @RequestMapping(params="rebateLog")
    @ResponseBody
    public EasyuiDataGridJson rebateLog(EasyuiDataGrid dg,TaskRebateUserVO rebateUser) {
        
        return rebateService.rebateLog(dg, rebateUser);
    }
    /**
     * 返利日志汇总
     * @param rebateUser
     * @return
     */
    @RequestMapping(params="rebateCount")
    @ResponseBody
    public Json rebateCount(TaskRebateUserVO rebateUser) {
        Json j = new Json();
        j.setObj(rebateService.rebateCount(rebateUser));
        j.setSuccess(true);
        return j;
    }
    
}
