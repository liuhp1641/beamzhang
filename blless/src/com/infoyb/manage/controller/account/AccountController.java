package com.cm.manage.controller.account;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.account.http.IRechargeManageHttpService;
import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.account.IAccountService;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.util.account.AccountOperate;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.account.AccountLogVO;
import com.cm.manage.vo.account.AccountVO;
import com.cm.manage.vo.account.FillVO;
import com.cm.manage.vo.account.InternalAccessVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.member.Customer;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

@Controller
@RequestMapping("/accountController")
public class AccountController extends BaseController {
    private static final Logger logger = Logger.getLogger(AccountController.class);
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IRechargeManageHttpService rechargeManageService;

    /**
     * 跳转到资金汇总
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "toAccountSum")
    public String toAccountSum(HttpServletRequest request) {
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        return "/account/accountSum";
    }

    /**
     * 资金汇总查询
     *
     * @param dg
     * @param account
     * @return
     */
    @RequestMapping(params = "accountData")
    @ResponseBody
    public EasyuiDataGridJson accountData(EasyuiDataGrid dg, AccountVO account) {
        return accountService.accountData(dg, account);
    }

    /**
     * 跳转资金明细界面
     *
     * @return
     */
    @RequestMapping(params = "toAccountDetail")
    public String toAccountDetail(HttpServletRequest request,String orderId) {
    	AccountOperate operate=new AccountOperate();
    	EasyuiTreeNode node=new EasyuiTreeNode();
    	node.setId("");
    	node.setText("全部");
    	node.setChecked(true);
    	Map<String,String> secondMap=operate.getSecondMap();
    	List<EasyuiTreeNode> secondNodeList=new ArrayList<EasyuiTreeNode>();
    	Iterator<Map.Entry<String, String>> it = secondMap.entrySet().iterator();
    	while (it.hasNext()) {
    	   Map.Entry<String, String> entry = it.next();
    	   EasyuiTreeNode secondNode=new EasyuiTreeNode();
		   secondNode.setId(entry.getKey());
		   secondNode.setText(entry.getValue());
		   secondNodeList.add(secondNode);
    	  }
    	//List<EasyuiTreeNode> secondNewList = secondNodeList.OrderBy(s=>s.id).ToList<EasyuiTreeNode>();  
    	node.setChildren(secondNodeList);
    	JSONArray array = JSONArray.fromObject(node);
    	request.setAttribute("secondNodes", array.toString());
    	
    	Map<String,String> thirdMap=operate.getThirdMap();
    	Map<String,String> fouthMap=operate.getFouthMap();
    	request.setAttribute("secondMap",secondMap);
    	request.setAttribute("thirdMap",thirdMap);
    	request.setAttribute("fouthMap",fouthMap);
    	request.setAttribute("orderId",orderId);
        return "/account/accountDetail";
    }
    @RequestMapping(params="secondTypeChange")
    @ResponseBody
    public Json secondTypeChange(HttpServletRequest request,String secondType){
    	Json j=new Json();
    	EasyuiTreeNode node=new EasyuiTreeNode();
    	node.setId("");
    	node.setText("全部");
    	node.setChecked(true);
    	AccountOperate operate=new AccountOperate();
    	if(CommonUtil.isNotEmpty(secondType)&&!"全部".equals(secondType)){
    		List<EasyuiTreeNode> secondChildList=operate.secondChildNode(secondType);
    		node.setChildren(secondChildList);
    	}
    	if("全部".equals(secondType)){
    		Map secondChildMap=operate.getSecondChildMap();
        	List<EasyuiTreeNode> secondChildList=new ArrayList<EasyuiTreeNode>();
        	Iterator<Map.Entry<String, List<EasyuiTreeNode>>> it = secondChildMap.entrySet().iterator();
        	while (it.hasNext()) {
        	   Map.Entry<String, List<EasyuiTreeNode>> entry = it.next();
        	   List<EasyuiTreeNode> childList=entry.getValue();
        	   secondChildList.addAll(childList);
        	  }
        	node.setChildren(secondChildList);
    	}
    	JSONArray array = JSONArray.fromObject(node);
    	j.setObj(array.toString());
    	return j;
    	
    }

    /**
     * 资金明细查询
     *
     * @param dg
     * @param account
     * @return
     */
    @RequestMapping(params = "detailData")
    @ResponseBody
    public EasyuiDataGridJson detailData(EasyuiDataGrid dg, AccountLogVO account) {
        return accountService.detailData(dg, account);
    }
    /**
     * 资金明细汇总
     * @param account
     * @return
     */
    @RequestMapping(params="detailCount")
    @ResponseBody
    public Json detailCount(AccountLogVO account) {
        Json j = new Json();
        j.setObj(accountService.detailCount(account));
        j.setSuccess(true);
        return j;
    }
    @RequestMapping(params = "rechargeManage")
    public String rechargeManage(HttpServletRequest request,String orderId) {
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        request.setAttribute("orderId", CommonUtil.formatStr(orderId, ""));
        return "/account/rechargeManage";
    }

    /**
     * 充值管理查询
     *
     * @param dg
     * @param fill
     * @return
     */
    @RequestMapping(params = "rechargeData")
    @ResponseBody
    public EasyuiDataGridJson rechargeData(EasyuiDataGrid dg, FillVO fill) {
        return accountService.rechargeData(dg, fill);
    }

    /**
     * 充值管理汇总
     * @param fill
     * @return
     */
    @RequestMapping(params="rechargeCount")
    @ResponseBody
    public Json rechargeCount(FillVO fill) {
        Json j = new Json();
        j.setObj(accountService.rechargeCount(fill));
        j.setSuccess(true);
        return j;
    }
    @RequestMapping(params = "toRechargeDetail")
    public String toRechargeDetail(HttpServletRequest request, String orderId) {
        Map recharge = accountService.rechargeDetail(orderId);
        request.setAttribute("recharge", recharge);
        return "/account/rechargeDetail";
    }

    @RequestMapping(params = "toRechargeMemoDetail")
    public String toRechargeMemoDetail(HttpServletRequest request, String orderId) {
        Map recharge = accountService.rechargeDetail(orderId);
        request.setAttribute("recharge", recharge);
        return "/account/rechargeMemoDetail";
    }

    /**
     * 充值备注保存
     *
     * @param orderId
     * @param memo
     * @return
     */
    @RequestMapping(params = "rechargeMemoSave")
    @ResponseBody
    public Json rechargeMemoSave(HttpServletRequest request, String orderId, String userName, String memo) {
        Json j = new Json();
        int i = accountService.rechargeMemoSave(orderId, memo);
        if (i > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("修改用户(").append(userName).append(")的充值订单(").append(orderId).append(")的备注为：").append(memo);
            saveLog(request, "/accountController.do?rechargeMemoSave", CommonConstants.LOG_FOR_ACCOUNT, sb.toString());
            j.setSuccess(true);
        } else {
            j.setSuccess(false);
        }
        return j;
    }

    /**
     * 充值单强制失败
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(params="rechargeFail")
    @ResponseBody
    public Json rechargeFail(HttpServletRequest request, String ids){
        Json j = new Json();
        List<String> orderList = new ArrayList<String>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
        	orderList.add(idStr[i]);
        }
        boolean flag = accountService.rechargeFail(orderList);
        if (flag) {
        	 for (int i = 0; i < idStr.length; i++) {
             	saveLog(request, "/accountController.do?rechargeFail", CommonConstants.LOG_FOR_CANCEL_ORDER, "(" + idStr[i] + ")充值单强制处理失败");
             }
            j.setSuccess(true);
        }else{
        	j.setSuccess(false);
        }
        return j;
    }
    /**
     * 补单
     *
     * @param orderId
     * @return
     */
    @RequestMapping(params = "repairOrder")
    @ResponseBody
    public Json repairOrder(HttpServletRequest request, String orderId, String userName, Double amount) {
        Json j = new Json();
        int i = rechargeManageService.repairOrder(orderId, amount);
        if (i > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("用户(").append(userName).append(")的充值订单(").append(orderId).append(")补单");
            saveLog(request, "/accountController.do?repairOrder", CommonConstants.LOG_FOR_ACCOUNT, sb.toString());
            j.setSuccess(true);
        } else {
            j.setSuccess(false);
        }
        return j;
    }

    /**
     * 提现转账
     *
     * @return
     */
    @RequestMapping(params = "drawTransfer")
    public String drawTransfer() {
        return "/account/drawTransfer";
    }

    /**
     * 内部存取
     *
     * @return
     */
    @RequestMapping(params = "access")
    public String access() {
        return "/account/access";
    }

    /**
     * 内部存取查询
     *
     * @param dg
     * @param account
     * @return
     */
    @RequestMapping(params = "internalData")
    @ResponseBody
    public EasyuiDataGridJson internalData(EasyuiDataGrid dg, InternalAccessVO account) {
        return accountService.internalData(dg, account);
    }
    
    /**
     * 内部存取汇总
     * @param account
     * @return
     */
    @RequestMapping(params="internalCount")
    @ResponseBody
    public Json internalCount(InternalAccessVO account){
    	Json j=new Json();
    	j.setObj(accountService.internalCount(account));
    	j.setSuccess(true);
    	return j;
    }

    /**
     * 内部存取调整界面
     *
     * @return
     */
    @RequestMapping(params = "toAdjust")
    public String toAdjust(HttpServletRequest request, String userCode, String userName, String mobile) {
        try {
        	if(CommonUtil.isNotEmpty(userName)){
        		
        		userName = URLDecoder.decode(userName, "UTF-8");
        	}
            List<Customer> memberList = memberService.customerInfo(userCode, userName, mobile);
            JSONArray memberJson = JSONArray.fromObject(memberList);
            request.setAttribute("memberJson", memberJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/account/adjust";
    }

    /**
     * 存取操作
     */
    @RequestMapping(params = "accountAdjust")
    @ResponseBody
    public Json accountAdjust(HttpServletRequest request, HttpSession session, InternalAccessVO vo) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
            User u = sessionInfo.getUser();
            vo.setOperator(u.getName());
            Map<String, Object> returnMap = accountService.accountAdjust(vo);
            boolean flag = accountService.amountAdjust(returnMap);
            Integer accountType = vo.getAccountType();
            String secondType=vo.getSecondType();
            String accountName = vo.getAccountName();
            StringBuffer accountStr = new StringBuffer("");
            //充值金
            if (accountType == 0) {
                accountStr.append("充值金");
            }
            //奖金
            if (accountType == 1) {
                accountStr.append("奖金");
            }
            //红包
            if (accountType == 2) {
                accountStr.append("红包");
            }
            //积分
            if (accountType == 3) {
                accountStr.append("积分");
            }
            //金币
            if (accountType == 4) {
                accountStr.append("金币");
            }
            StringBuilder sb = new StringBuilder();
            if (secondType.equals("101")) {
                sb.append("用户").append(vo.getUserName()).append(accountStr).append("账户转出").append(vo.getAmount()).append("元到").append(accountName);
            }
            if (secondType.equals("001")) {
                sb.append(accountName).append(accountStr).append("账户转出").append(vo.getAmount()).append("元到用户").append(vo.getUserName());
            }
            if (secondType.equals("000")) {
                sb.append(vo.getUserName()).append("存入").append(accountStr).append(vo.getAmount()).append("元");
            }
            if (secondType.equals("100")) {
                sb.append(vo.getUserName()).append("取出").append(accountStr).append(vo.getAmount()).append("元");
            }

            saveLog(request, "/accountController.do?accountAdjust", CommonConstants.LOG_FOR_ACCOUNT, sb.toString());
            j.setSuccess(flag);
        } else {
            j.setSuccess(false);
            j.setMsg("您没有登录或登录超时，请重新登录后重试！");
        }
        return j;
    }
}
