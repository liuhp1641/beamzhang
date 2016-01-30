package com.cm.manage.controller.operate;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.cm.manage.service.operate.IOperateService;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.util.bonusClass.BonusClassManager;
import com.cm.manage.util.bonusClass.BonusClassUtils;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.order.HandworkBonusVO;
import com.cm.manage.vo.order.MainIssueVO;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;
import com.cm.order.http.IOrderManageHttpService;
import com.cm.other.http.IIndexManageHttpService;

@Controller
@RequestMapping("/operateController")
public class OperateController extends BaseController {

    private static final Logger logger = Logger.getLogger(OperateController.class);

    @Autowired
    private ILotteryControlService lotteryControlService;

    @Autowired
    private IOperateService operateService;

    @Resource
    private IOrderManageHttpService orderInterface;
    
    @Resource
    private IIndexManageHttpService indexManageInterface;

    /**
     * 跳转到彩种管理
     *
     * @return
     */
    @RequestMapping(params = "lottery")
    public String lottery() {
        return "/operate/lottery";
    }


    /**
     * 调用接口彩种列表
     *
     * @return
     */
    @RequestMapping(params = "invoker")
    @ResponseBody
    public EasyuiDataGridJson invoker() {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        List<LotteryControl> list = lotteryControlService.getLoteryControlList();
        j.setRows(list);
        return j;
    }

    /**
     * 彩种保存修改
     *
     * @return
     */
    @RequestMapping(params = "editLottery")
    @ResponseBody
    public Json editLottery(HttpServletRequest request, String lotterys) {
        Json j = new Json();
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(lotterys);
        int success = 0;
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            LotteryControl lottery = (LotteryControl) JSONObject.toBean(jsonObj, LotteryControl.class);
            String mask = lottery.getMark();
            if (mask == null || mask.equals("无")) {
                lottery.setMark("");
            }
            int flag = lotteryControlService.updateLotteryControl(lottery.getLotteryCode(), lottery.getMark(), lottery.getTips(), lottery.getDelayTime());
            if (flag != 0) {
                saveLog(request, "operateController.do?editLottery", CommonConstants.LOG_FOR_LOTTERY, "彩种" + lottery.getLotteryName() + "被修改，标签为" + lottery.getMark() + ",营销语为" + lottery.getTips());
                success++;
            }
        }
        if (success == jsonArr.size()) {
            j.setSuccess(true);
        }
        return j;
    }

    @RequestMapping(params = "controlSendStatus")
    @ResponseBody
    public Json controlSendStatus(HttpServletRequest request, String lotteryCode, String lotteryName, Integer status) {
        int success = lotteryControlService.updateSendStatus(lotteryCode, status);
        Json j = new Json();
        if (success != 0) {
            String statusStr = "";
            if (status == 1) {
                statusStr = "恢复出票";
            }
            if (status == 0) {
                statusStr = "暂停出票";
            }
            saveLog(request, "/operateController.do?controlSendStatus", CommonConstants.LOG_FOR_LOTTERY, "彩种" + lotteryName + "修改为" + statusStr);
            j.setSuccess(true);
        }
        return j;
    }

    @RequestMapping(params = "controlStatus")
    @ResponseBody
    public Json controlStatus(HttpServletRequest request, String lotteryCode, String lotteryName, Integer status, String memo) {
        int success = lotteryControlService.updateStatus(lotteryCode, status, memo);
        Json j = new Json();
        if (success != 0) {
            String statusStr = "";
            if (status == 1) {
                statusStr = "暂停销售";
            }
            if (status == 2) {
                statusStr = "隐藏";
            }
            if (status == 0) {
                statusStr = "恢复销售";
            }
            saveLog(request, "/operateController.do?controlStatus", CommonConstants.LOG_FOR_LOTTERY, "彩种" + lotteryName + "修改为" + statusStr);
            j.setSuccess(true);
        }
        return j;
    }
    /**
     * 彩种推荐
     * @param request
     * @param lotteryCode
     * @param lotteryName
     * @param bonusAmount
     * @return
     */
    @RequestMapping(params = "lotteryRecommend")
    @ResponseBody
    public Json lotteryRecommend(HttpServletRequest request, String lotteryCode, String lotteryName,Double bonusAmount) {
        Json j = new Json();
        if(bonusAmount.compareTo(new Double(500000000))<0){
        	j.setSuccess(false);
        	j.setMsg("奖池金额小于5亿不可推荐");
        	return j;
        }
        boolean flag=indexManageInterface.lotBonus(lotteryCode, bonusAmount);
        if(flag){
        	saveLog(request, "/operateController.do?lotteryRecommend", CommonConstants.LOG_FOR_LOTTERY, "推荐彩种(" + lotteryName+")");
        	j.setSuccess(true);
        	j.setMsg("推荐成功");
        }else{
        	j.setSuccess(false);
        	j.setMsg("推荐失败");
        }
        return j;
    }
    /**
     * 彩种取消推荐
     * @param request
     * @param lotteryCode
     * @param lotteryName
     * @return
     */
    @RequestMapping(params="lotteryCancelRecommend")
    @ResponseBody
    public Json lotteryCancelRecommend(HttpServletRequest request, String lotteryCode, String lotteryName) {
        Json j = new Json();
        boolean flag=indexManageInterface.cancelLotBonus(lotteryCode);
        if(flag){
        	saveLog(request, "/operateController.do?lotteryCancelRecommend", CommonConstants.LOG_FOR_LOTTERY, "取消推荐彩种(" + lotteryName+")");
        	j.setSuccess(true);
        }
        return j;
    }

    /**
     * 跳转到派奖管理
     *
     * @return
     */
    @RequestMapping(params = "toHandBonus")
    public String toHandBonus(HttpServletRequest request) {
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        return "/operate/handBonusList";
    }

    /**
     * 派奖查询
     *
     * @param dg
     * @param bonus
     * @return
     */
    @RequestMapping(params = "handBonusList")
    @ResponseBody
    public EasyuiDataGridJson handBonusList(EasyuiDataGrid dg, HandworkBonusVO bonus) {
        return operateService.handBonusList(dg, bonus);
    }

    /**
     * 派奖
     *
     * @param session
     * @param programsOrderId
     * @return
     */
    @RequestMapping(params = "handBonus")
    @ResponseBody
    public Json handBonus(HttpServletRequest request, HttpSession session, String programsOrderId) {
        Json j = new Json();
        try{
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
            User u = sessionInfo.getUser();
            orderInterface.handBonus(programsOrderId, u.getName());
            saveLog(request, "/operateController.do?handBonus", CommonConstants.LOG_FOR_SEND_WIN, programsOrderId + "方案派奖");
            j.setSuccess(true);
            j.setMsg("派奖成功!");
        } else {
            j.setMsg("您没有登录或登录超时，请重新登录后重试！");
        }
        }catch(Exception e){
        	e.printStackTrace();
        	j.setSuccess(false);
        	j.setMsg("派奖失败!");
        }
        return j;
    }

    /**
     * 派奖取消
     *
     * @param session
     * @param programsOrderId
     * @return
     */
    @RequestMapping(params = "handBonusCancel")
    @ResponseBody
    public Json handBonusCancel(HttpServletRequest request, String programsOrderId) {
        Json j = new Json();
        boolean flag = operateService.handBonusCancel(programsOrderId);
        saveLog(request, "/operateController.do?handBonusCancel", CommonConstants.LOG_FOR_SEND_WIN, programsOrderId + "方案派奖取消");
        j.setSuccess(flag);
        return j;
    }

    /**
     * 线下派奖/派奖还原
     *
     * @param request
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping(params = "handBonusUpdate")
    @ResponseBody
    public Json handBonusUpdate(HttpServletRequest request, String ids, Integer status) {
        Json j = new Json();
        List<String> programsList = new ArrayList<String>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            programsList.add(idStr[i]);
        }
        boolean flag = operateService.handBonusUpdate(programsList, status);
        if (flag) {
            String statusStr = "";
            if (status == 3) {
                statusStr = "线下派奖";
            }
            saveLog(request, "/operateController.do?handBonusUpdate", CommonConstants.LOG_FOR_SEND_WIN, programsList.size() + "条被批量" + statusStr);
            j.setSuccess(true);
        }
        return j;
    }

    /**
     * 跳转到奖期管理
     *
     * @return
     */
    @RequestMapping(params = "toIssue")
    public String toIssue(HttpServletRequest request) {
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        return "/operate/issueList";
    }

    /**
     * 奖期查询
     *
     * @param dg
     * @param issue
     * @return
     */
    @RequestMapping(params = "issueList")
    @ResponseBody
    public EasyuiDataGridJson issueList(EasyuiDataGrid dg, MainIssueVO issue) {
        return operateService.issueList(dg, issue);
    }

    /**
     * 期号详情
     *
     * @param request
     * @param lotteryCode
     * @param name
     * @return
     */
    @RequestMapping(params = "toIssueDetail")
    public String toIssueDetail(HttpServletRequest request, String lotteryCode, String name) {
        Map issue = operateService.issueDetail(lotteryCode, name);
        request.setAttribute("issue", issue);
        String  bonusClassStr=(String)issue.get("BONUS_CLASS");
        if("-".equals(bonusClassStr)){
        	 request.setAttribute("bonusClass", "-");
        }else{
        	BonusClassManager classManager=new BonusClassManager(bonusClassStr);
        	request.setAttribute("bonusClass", classManager.getLevelList());
        }
        return "/operate/issueDetail";
    }

    /**
     * 编辑公告
     *
     * @param request
     * @param lotteryCode
     * @param lotteryName
     * @param name
     * @return
     */
    @RequestMapping(params = "toIssueMemo")
    public String toIssueMemo(HttpServletRequest request, String lotteryCode, String lotteryName, String name) {
        Map issue = operateService.issueDetail(lotteryCode, name);
        request.setAttribute("issue", issue);
        try{
        lotteryName = URLDecoder.decode(lotteryName, "UTF-8");
        BonusClassManager bonusClass= BonusClassUtils.getDefaultBonusClassManagerByLotteryCode(lotteryCode);
        String  bonusClassStr=(String)issue.get("BONUS_CLASS");
        if("-".equals(bonusClassStr)||bonusClassStr==null||"".equals(bonusClassStr) ){
        	 request.setAttribute("bonusClass", bonusClass.getLevelList());
        }else{
        	BonusClassManager classManager=new BonusClassManager(bonusClassStr);
        	request.setAttribute("bonusClass", classManager.getLevelList());
        }
        if(lotteryName.indexOf("双色球") != -1||lotteryName.indexOf("七星彩") != -1||lotteryName.indexOf("大乐透") != -1||lotteryName.indexOf("七乐彩") != -1||lotteryName.indexOf("排列五") != -1||lotteryName.indexOf("排列三") != -1){
        	return "/awardSet/awardSetDetail";
        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return "/awardSet/awardSetDetail";
    }
   /**
    * 奖期编辑公告保存
    * @param lotteryCode
    * @param name
    * @param bonusNumber
    * @param saleTotal
    * @param bonusPool
    * @param bonusClass
    * @return
    */
   @RequestMapping(params="awardSetSave")
   @ResponseBody
   public Json awardSetSave(HttpServletRequest request,String lotteryCode, String name,String bonusNumber,String saleTotal,String prizePool,String bonusClass){
	   Json j=new Json();
	   boolean flag=operateService.awardSetSave(lotteryCode, name, bonusNumber, saleTotal, prizePool, bonusClass);
	   if(flag){
		   saveLog(request, "/operateController.do?awardSetSave", CommonConstants.LOG_FOR_ISSUE,  name + "期奖期编辑公告保存");
	   }
	   j.setSuccess(flag);
	   return j;
   }

    @RequestMapping(params = "toBatchSaveIssue")
    public String toBatchSaveIssue(HttpServletRequest request) {
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        return "/operate/batchIssueSave";
    }

    /**
     * 期次生成
     *
     * @param lotteryCode
     * @param dateStr
     * @param day
     * @return
     */
    @RequestMapping(params = "batchSaveIssue")
    @ResponseBody
    public Json batchSaveIssue(HttpServletRequest request, String lotteryCode, String lotteryName, String dateStr, Integer day) {
        Json j = new Json();
        int i = orderInterface.doBatchSaveIssue(lotteryCode, dateStr, day);
        if (i > 0) {
            saveLog(request, "/operateController.do?batchSaveIssue", CommonConstants.LOG_FOR_EDIT_ISSUE, "批量录入" + lotteryName + ",开始时间" + dateStr + ",天数" + day);
            j.setSuccess(true);
        } else {
            j.setSuccess(false);
        }
        return j;
    }

}
