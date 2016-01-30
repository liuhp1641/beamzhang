package com.cm.manage.controller.operate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.model.order.LotteryUpdateLog;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.service.operate.ILotteryUpdateService;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

@Controller
@RequestMapping("/lotteryUpdateController")
public class LotteryUpdateController extends BaseController {

    private static final Logger logger = Logger.getLogger(LotteryUpdateController.class);
    
    @Autowired
    private ILotteryUpdateService lotteryUpdateService;
    
    @Autowired
    private IMemberService memberService;
    
    @Autowired
    private ILotteryControlService lotteryControlService;
    /**
     * 跳转到更新控制页面
     * @return
     */
    @RequestMapping(params="toUpdateControl")
    public String toUpdateControl(HttpServletRequest request){
    	List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        
        List<Map> channelList=new ArrayList<Map>();
	    for(int i=0;i<channel.size();i++){
	    	Map<String,Object> channelMap=new HashMap<String,Object>();
	    	EasyuiTreeNode node=channel.get(i);
	    	channelMap.put("id", node.getId());
	    	channelMap.put("text", node.getText());
	    	channelList.add(channelMap);
	    }
	    JSONArray channelJson = JSONArray.fromObject(channelList);
	    request.setAttribute("channelJson", channelJson);
        //彩种
	    List<LotteryControl> lottery=lotteryControlService.getLoteryControlList();
	    request.setAttribute("lottery", lottery);
	    List<Map> lotteryList=new ArrayList<Map>();
	    for(int i=0;i<lottery.size();i++){
	    	Map<String,Object> lotteryMap=new HashMap<String,Object>();
	    	LotteryControl control=lottery.get(i);
	    	lotteryMap.put("lotteryCode", control.getLotteryCode());
	    	lotteryMap.put("lotteryName", control.getLotteryName());
	    	lotteryList.add(lotteryMap);
	    }
	    JSONArray lotteryJson = JSONArray.fromObject(lotteryList);
	    request.setAttribute("lotteryJson", lotteryJson);
    	return "/operate/updateControl";
    }
    /**
     * 更新控制记录
     * @return
     */
    @RequestMapping(params="lotteryUpdateList")
    @ResponseBody
    public List<LotteryUpdateLog> lotteryUpdateList(){
    	return lotteryUpdateService.lotteryUpdateList();
    }
    
    /**
     * 彩种更新新增
     * 
     * @return
     */
    @RequestMapping(params="lotteryUpdateSave")
    @ResponseBody
    public Json lotteryUpdateSave(HttpServletRequest request,LotteryUpdateLog log){
    	Json j=new Json();
    	SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
    	if (sessionInfo != null && sessionInfo.getUser() != null) {
        User user = sessionInfo.getUser();
        log.setOperator(user.getName());
        log.setCreateTime(new Date());
    	lotteryUpdateService.lotteryUpdateSave(log);
    	StringBuffer str=new StringBuffer("彩种(");
    	str.append(log.getLotteryName()).append(")新增一条更新。");
    	if(CommonUtil.isNotEmpty(log.getSid())){
    		str.append("渠道：").append(log.getChannelName());
    	}
    	if(CommonUtil.isNotEmpty(log.getPlatform())){
    		str.append("平台：").append(log.getPlatform());
    	}
    	str.append("版本：").append(log.getVersion());
    	str.append("更新说明：").append(log.getMemo());
    	saveLog(request, "/lotteryUpdateController.do?lotteryUpdateSave", CommonConstants.LOG_FOR_LOTTERY,str.toString());
    	j.setSuccess(true);
    	}else{
    		j.setMsg("您没有登录或登录超时，请重新登录后重试！");
    	}
    	return j;
    }
    /**
     * 彩种更新保存修改
     * @param channels
     * @return
     */
    @RequestMapping(params="editLotteryUpdate")
    @ResponseBody
    public Json editLotteryUpdate(HttpServletRequest request,String updatelogs) {
        Json j = new Json();
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(updatelogs);
        int success = 0;
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            LotteryUpdateLog log = (LotteryUpdateLog) JSONObject.toBean(jsonObj, LotteryUpdateLog.class);
            lotteryUpdateService.lotteryUpdateSave(log);
            success++;
            StringBuffer str=new StringBuffer("彩种(");
        	str.append(log.getLotteryName()).append(")更新为：");
        	if(CommonUtil.isNotEmpty(log.getSid())){
        		str.append("渠道：").append(log.getChannelName());
        	}
        	if(CommonUtil.isNotEmpty(log.getPlatform())){
        		str.append("平台：").append(log.getPlatform());
        	}
        	str.append("版本：").append(log.getVersion());
        	str.append("更新说明：").append(log.getMemo());
            saveLog(request, "/lotteryUpdateController.do?editLotteryUpdate", CommonConstants.LOG_FOR_LOTTERY,str.toString());
        }
        if (success == jsonArr.size()) {
            j.setSuccess(true);
        }
        return j;
    }

    /**
     * 彩种更新删除
     * @param updatelogs
     * @return
     */
    @RequestMapping(params="lotteryUpdateDel")
    @ResponseBody
    public Json lotteryUpdateDel(HttpServletRequest request,String updatelogs) {
        Json j = new Json();
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(updatelogs);
        int success = 0;
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            LotteryUpdateLog log = (LotteryUpdateLog) JSONObject.toBean(jsonObj, LotteryUpdateLog.class);
            lotteryUpdateService.lotteryUpdateDel(log);
            saveLog(request, "/lotteryUpdateController.do?lotteryUpdateDel", CommonConstants.LOG_FOR_LOTTERY,"彩种("+log.getLotteryName()+")一条更新被删除");
            success++;
        }
        if (success == jsonArr.size()) {
            j.setSuccess(true);
        }
        return j;
    }
}
