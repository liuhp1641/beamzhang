package com.cm.manage.controller.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.service.order.IOrderService;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.order.AutoOrderVO;
import com.cm.manage.vo.order.OrderVO;
import com.cm.order.http.IOrderManageHttpService;
import com.cm.other.http.IIndexManageHttpService;

/**
 * 订单控制器
 *
 * @author
 */
@Controller
@RequestMapping("/orderController")
public class OrderController extends BaseController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ILotteryControlService lotteryControlService;

    @Autowired
    private IMemberService memberService;

    @Resource
    private IOrderManageHttpService orderInterface;
    
    @Resource
    private IIndexManageHttpService indexManageInterface;

    //跳转到投注查询页
    @RequestMapping(params = "toOrderList")
    public String toOrderList(HttpServletRequest request, String userCode,String orderId) {
        //注册渠道
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        request.setAttribute("userCode", CommonUtil.formatStr(userCode, ""));
        request.setAttribute("orderId", CommonUtil.formatStr(orderId, ""));
        return "/order/orderList";
    }

    //投注查询表格
    @RequestMapping(params = "orderList")
    @ResponseBody
    public EasyuiDataGridJson orderList(EasyuiDataGrid dg, OrderVO order) {
        return orderService.orderList(dg, order);
    }

    //跳转到追号方案
    @RequestMapping(params = "toAutoOrderList")
    public String toAutoOrderList(HttpServletRequest request) {
        //注册渠道
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);

        return "/order/autoOrderList";
    }

    //追号方案查询
    @RequestMapping(params = "autoOrderList")
    @ResponseBody
    public EasyuiDataGridJson autoOrderList(EasyuiDataGrid dg, AutoOrderVO order) {
        return orderService.autoOrderList(dg, order);
    }

    //追号方案详情
    @RequestMapping(params = "toAutoDetail")
    public String toAutoDetail(HttpServletRequest request, String autoOrderId) {
        Map order = orderService.autoOrderInfo(autoOrderId);
        request.setAttribute("order", order);
        return "/order/autoDetail";
    }

    //追号方案下的订单方案
    @RequestMapping(params = "autoProgramsList")
    @ResponseBody
    public EasyuiDataGridJson autoProgramsList(EasyuiDataGrid dg, String autoOrderId) {
        return orderService.autoProgramsList(dg, autoOrderId);
    }

    //追号方案下的方案内容
    @RequestMapping(params = "autoProgramsInfo")
    @ResponseBody
    public List<OrderVO> autoProgramsInfo(String autoOrderId) {
        return orderService.autoProgramsInfo(autoOrderId);
    }

    //追号订单详情
    @RequestMapping(params = "toAutoOrderDetail")
    public String toAutoOrderDetail(HttpServletRequest request, String programsOrderId) {
        Map order = orderService.programsOrderInfo(programsOrderId, 4);
        request.setAttribute("order", order);
        return "/order/autoOrderDetail";
    }

    //跳转合买方案页面
    @RequestMapping(params = "toSynOrderList")
    public String toSynOrderList(HttpServletRequest request) {
        //注册渠道
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);

        return "/order/synOrderList";
    }

    //合买方案查询
    @RequestMapping(params = "synOrderList")
    @ResponseBody
    public EasyuiDataGridJson synOrderList(EasyuiDataGrid dg, OrderVO order) {
        return orderService.synOrderList(dg, order);
    }

    //合买方案详情
    @RequestMapping(params = "toSynOrderDetail")
    public String toSynOrderDetail(HttpServletRequest request, String programsOrderId) {
        Map order = orderService.programsOrderInfo(programsOrderId, 2);
        request.setAttribute("order", order);
        return "/order/synOrderDetail";
    }

    //合买用户
    @RequestMapping(params = "synMemberList")
    @ResponseBody
    public EasyuiDataGridJson synMemberList(EasyuiDataGrid dg, String programsOrderId) {
        return orderService.synMemberList(dg, programsOrderId);
    }
    /**
     * 合买推荐
     * @param programsOrderId
     * @return
     */
    @RequestMapping(params="synRecommend")
    @ResponseBody
    public Json synRecommend(HttpServletRequest request,String programsOrderId){
    	Json j=new Json();
    	Map synInfo=orderService.programsOrderInfo(programsOrderId, 2);
    	if(CommonUtil.isNotEmpty(synInfo)){
    		if(CommonUtil.isNotEmpty(synInfo.get("DESCRIPTION"))){
    			boolean flag=indexManageInterface.topBuy(programsOrderId, (String)synInfo.get("LOTTERY_CODE"),  (String)synInfo.get("ISSUE"),(String) synInfo.get("DESCRIPTION"));
    			if(flag){
    				flag=orderService.synRecommend(programsOrderId,1);
    				if(flag){
    					saveLog(request, "/orderController.do?synRecommend", CommonConstants.LOG_FOR_CANCEL_ORDER,  "推荐合买方案(" + programsOrderId + ")");
    					j.setSuccess(true);
    					j.setMsg("推荐成功");
    				}
    			}else{
    				j.setSuccess(false);
					j.setMsg("推荐失败");
    			}
    			
    		}else{
    			j.setSuccess(false);
				j.setMsg("方案描述为空的记录不可推荐！");
    		}
    	}
    	return j;
    }
    /**
     * 合买取消推荐
     * @param request
     * @param programsOrderId
     * @return
     */
    @RequestMapping(params="synCancelRecommend")
    @ResponseBody
    public Json synCancelRecommend(HttpServletRequest request,String programsOrderId){
    	Json j=new Json();
    	Map synInfo=orderService.programsOrderInfo(programsOrderId, 2);
    	if(CommonUtil.isNotEmpty(synInfo)){
    		boolean flag=indexManageInterface.cancelTopBuy(programsOrderId);
    		if(flag){
    			 flag=orderService.synRecommend(programsOrderId,0);
    			 if(flag){
    				 saveLog(request, "/orderController.do?synCancelRecommend", CommonConstants.LOG_FOR_CANCEL_ORDER,  "取消推荐合买方案(" + programsOrderId + ")");
    			 }
    	         j.setSuccess(flag);
    		}
    	}
    	return j;
    }

    //方案彩票信息
    @RequestMapping(params = "ticketList")
    @ResponseBody
    public EasyuiDataGridJson ticketList(EasyuiDataGrid dg,String programsOrderId) {
        return orderService.ticketList(dg,programsOrderId);
    }

    //跳转代购页面
    @RequestMapping(params = "toBuyOrderList")
    public String toBuyOrderList(HttpServletRequest request) {
        //注册渠道
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);

        return "/order/buyOrderList";
    }

    //代购查询
    @RequestMapping(params = "buyOrderList")
    @ResponseBody
    public EasyuiDataGridJson buyOrderList(EasyuiDataGrid dg, OrderVO order) {
        return orderService.buyOrderList(dg, order);
    }

    //代购方案详情
    @RequestMapping(params = "toBuyOrderDetail")
    public String toBuyOrderDetail(HttpServletRequest request, String programsOrderId) {
        Map order = orderService.programsOrderInfo(programsOrderId, 1);
        request.setAttribute("order", order);
        return "/order/buyOrderDetail";
    }

    //方案撤单
    @RequestMapping(params = "orderCancel")
    @ResponseBody
    public Json orderCancel(HttpServletRequest request, String ids, Integer buyType) {
        Json j = new Json();
        List<String> programsList = new ArrayList<String>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            programsList.add(idStr[i]);
        }
        int n = orderInterface.orderCancel(programsList, buyType);
        if (n != 1) {
            j.setSuccess(false);
        } else {
            String buyStr = "";
            if (buyType == 1) {
                buyStr = "代购方案";
            }
            if (buyType == 2) {
                buyStr = "合买方案";
            }
            if (buyType == 4) {
                buyStr = "追号方案";
            }
            saveLog(request, "/orderController.do?orderCancel", CommonConstants.LOG_FOR_CANCEL_ORDER, buyStr + "(" + ids + ")撤单");
            j.setSuccess(true);
        }
        return j;
    }

    /**
     * 方案置顶
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "setTop")
    @ResponseBody
    public Json setTop(HttpServletRequest request, String ids, Integer buyType) {
        Json j = new Json();
        List<String> programsList = new ArrayList<String>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            programsList.add(idStr[i]);
        }
        boolean flag = orderService.setTop(programsList);
        String buyStr = "";
        if (buyType == 1) {
            buyStr = "代购方案";
        }
        if (buyType == 2) {
            buyStr = "合买方案";
        }
        if (buyType == 4) {
            buyStr = "追号方案";
        }
        saveLog(request, "/orderController.do?setTop", CommonConstants.LOG_FOR_WEIGHT, "方案(" + buyStr + ")批量置顶");
        j.setSuccess(flag);
        return j;
    }

    //方案描述修改
    @RequestMapping(params = "editSynDescription")
    @ResponseBody
    public Json editSynDescription(HttpServletRequest request, String programsOrderId, String description) {
        Json j = new Json();
        orderService.editSynDescription(programsOrderId, description);
        saveLog(request, "/orderController.do?editSynDescription", CommonConstants.LOG_FOR_ORDER, "方案(" + programsOrderId + ")描述修改为" + description);
        j.setSuccess(true);
        return j;
    }

    //彩票某期的开奖号码
    @RequestMapping(params = "lotteryIssue")
    @ResponseBody
    public Json lotteryIssue(String lotteryCode, String issue) {
        Json j = new Json();
        List<Map> mainIssue = orderService.mainIssue(lotteryCode, issue);
        j.setObj(mainIssue);
        return j;
    }
}
