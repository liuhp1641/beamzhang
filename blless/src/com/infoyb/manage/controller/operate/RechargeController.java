package com.cm.manage.controller.operate;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.account.RechargeManage;
import com.cm.manage.service.operate.IRechargeService;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.Json;

@Controller
@RequestMapping("/rechargeController")
public class RechargeController extends BaseController {

    private static final Logger logger = Logger.getLogger(RechargeController.class);
    
    @Autowired
    private IRechargeService rechargeService;
    /**
     * 跳转到充值管理页面
     * @return
     */
    @RequestMapping(params="toRecharge")
    public String toRecharge(){
    	return "/operate/recharge";
    }
    
    @RequestMapping(params="rechargeList")
    @ResponseBody
    public List<RechargeManage> rechargeList(){
    	return rechargeService.rechargeList();
    }
    
    /**
     * 支付方式新增
     * @param channelCode
     * @param channelName
     * @return
     */
    @RequestMapping(params="channelSave")
    @ResponseBody
    public Json channelSave(HttpServletRequest request,RechargeManage manage){
    	Json j=new Json();
    	rechargeService.channelSave(manage);
    	saveLog(request,"/rechargeController.do?channelSave", CommonConstants.LOG_FOR_PAY_COOP, "创建支付渠道"+manage.getChannelName());
    	j.setSuccess(true);
    	return j;
    }
    /**
     * 支付方式保存修改
     * @param channels
     * @return
     */
    @RequestMapping(params="editChannel")
    @ResponseBody
    public Json editChannel(HttpServletRequest request,String channels) {
        Json j = new Json();
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(channels);
        int success = 0;
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            RechargeManage recharge = (RechargeManage) JSONObject.toBean(jsonObj, RechargeManage.class);
            String label=recharge.getLabel();
            if(label==null||label.equals("无")){
            	recharge.setLabel("");
            }
            rechargeService.channelSave(recharge);
            StringBuffer str=new StringBuffer("修改支付渠道(");
            str.append(recharge.getChannelName());
            str.append(")。");
            if(CommonUtil.isNotEmpty(recharge.getLabel())){
            	str.append("标签为").append(recharge.getLabel());
            }
            if(CommonUtil.isNotEmpty(recharge.getAdNote())){
            	str.append("广告词为").append(recharge.getAdNote());
            }
            if(CommonUtil.isNotEmpty(recharge.getRemark())){
            	str.append("备注为").append(recharge.getRemark());
            }
            saveLog(request, "/rechargeController.do?editChannel", CommonConstants.LOG_FOR_PAY_COOP, str.toString());
            success++;
        }
        if (success == jsonArr.size()) {
            j.setSuccess(true);
        }
        return j;
    }

    

}
