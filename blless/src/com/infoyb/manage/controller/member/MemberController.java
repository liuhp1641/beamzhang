package com.cm.manage.controller.member;

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
import com.cm.manage.model.account.BindChargeBank;
import com.cm.manage.model.account.BindFillBank;
import com.cm.manage.model.account.DrawBindBank;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.util.base.Encrypt;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.member.Customer;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;
import com.cm.user.exception.IUserErrCode;
import com.cm.user.exception.UserException;
import com.cm.user.http.IMemberManageHttpService;
import com.cm.user.http.bean.MemberBean;

/**
 * 账户控制器
 *
 * @author chw
 */
@Controller
@RequestMapping("/memberController")
public class MemberController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberController.class);

    @Autowired
    private IMemberService memberService;

    @Resource
    private IMemberManageHttpService memberInterface;

    /**
     * 跳转到用户管理首页
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "main")
    public String main(HttpServletRequest request) {
        Long yvipCount = memberService.yvipCount();//昨日新增VIP
        Long mvipCount = memberService.mvipCount();//当月新增VIP
        Long dregCount = memberService.dregCount();//当日累计注册用户数
        Long yregCount = memberService.yregCount();//昨日累计注册用户数
        Long mregCount = memberService.mregCount();//当月累计注册用户数
        Long ultregCount = memberService.ultregCount();//上月累计注册用户数
        
        Long allRegCount=memberService.allRegCount();//平台累计用户数
        request.setAttribute("yvipCount", yvipCount);
        request.setAttribute("mvipCount", mvipCount);
        request.setAttribute("dregCount", dregCount);
        request.setAttribute("allRegCount", allRegCount);
//        Long rise = dregCount - yregCount;
//        String risePercent = CommonUtil.getPercent(rise, yregCount);
//        request.setAttribute("regRise", risePercent);
        request.setAttribute("mregCount", mregCount);
        request.setAttribute("ultregCount", ultregCount);
//        rise = mregCount - ultregCount;
//        risePercent = CommonUtil.getPercent(rise, ultregCount);
//        request.setAttribute("ultregRise", risePercent);
        List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        return "/member/main";
    }

    /**
     * 用户管理查询
     *
     * @param dg
     * @param customer
     * @return
     */
    @RequestMapping(params = "members")
    @ResponseBody
    public EasyuiDataGridJson members(EasyuiDataGrid dg, Customer customer, boolean flag) {
        return memberService.memberDatas(dg, customer, flag);
    }

    /**
     * 用户详情
     *
     * @return
     */
    @RequestMapping(params = "detail")
    public String detail(HttpServletRequest request, String userCode) {
        //用户基础信息
        Map customer = memberService.getCustomerInfo(userCode);
        request.setAttribute("customer", customer);
        //充值信息
        BindFillBank fillbank = memberService.getFillBank(userCode);
        request.setAttribute("fillbank", fillbank);
        //提现信息
        DrawBindBank drawbank = memberService.getDrawBindBank(userCode);
        request.setAttribute("drawbank", drawbank);

        return "/member/detail";
    }

    /**
     * 用户详情
     *
     * @return
     */
    @RequestMapping(params = "detailByMobile")
    public String detailByMobile(HttpServletRequest request, String mobile) {
        //用户基础信息
        Map customer = memberService.getCustomerInfoByMobile(mobile);
        String userCode = (String)customer.get("USERCODE");
        request.setAttribute("customer", customer);
        //充值信息
        BindFillBank fillbank = memberService.getFillBank(userCode);
        request.setAttribute("fillbank", fillbank);
        //提现信息
        DrawBindBank drawbank = memberService.getDrawBindBank(userCode);
        request.setAttribute("drawbank", drawbank);

        return "/member/detail";
    }
/**
 * 跳转登录日志
 * @param request
 * @param userCode
 * @return
 */
    @RequestMapping(params="toMemberLogin")
    public String toMemberLogin(HttpServletRequest request,String userCode){
    	request.setAttribute("userCode", userCode);
    	return "/member/memberLogin";
    }
    /**
	 * 用户登录日志
	 * @param dg
	 * @param userCode
	 * @return
	 */
    @RequestMapping(params="memberLoginList")
    @ResponseBody
	public EasyuiDataGridJson memberLoginList(EasyuiDataGrid dg, String userCode){
		return memberService.memberLoginList(dg, userCode);
	}
    @RequestMapping(params = "edit")
    public String detail_edit(HttpServletRequest request, String userCode) {
        Map customer = memberService.getCustomerInfo(userCode);
        request.setAttribute("customer", customer);
        //充值信息
        BindFillBank fillbank = memberService.getFillBank(userCode);
        request.setAttribute("fillbank", fillbank);
        //提现信息
        DrawBindBank drawbank = memberService.getDrawBindBank(userCode);
        request.setAttribute("drawbank", drawbank);
        
        //充值发卡行信息
        List<Map> rechargeBanks = memberService.getBanks("充值银行列表");
        request.setAttribute("rechargeBanks", rechargeBanks);
        
        //提现发卡行信息
        List<Map> withdrawBanks = memberService.getBanks("提现银行列表");
        request.setAttribute("withdrawBanks", withdrawBanks);
        
        return "/member/detail_edit";
    }

    @RequestMapping(params = "pwdopen")
    public String detail_pwd(HttpServletRequest request, String userCode, String userName, String mobile) {
        if (userName == null || userName.equals("null")) {
            userName = "";
        }
        if (mobile == null || mobile.equals("null")) {
            mobile = "";
        }
        request.setAttribute("userCode", userCode);
        request.setAttribute("userName", userName);
        request.setAttribute("mobile", mobile);
        return "/member/detail_pwd";
    }

    /**
     * 用户基本信息保存
     * @param request
     * @param member
     * @return
     */
    @RequestMapping(params = "basicSave")
    @ResponseBody
    public Json basicSave(HttpServletRequest request, MemberBean member) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
        User user = sessionInfo.getUser();
        String password = user.getPassword();
        String textpassword = member.getPassword();

        if (!password.equals(Encrypt.e(textpassword))) {
            j.setSuccess(false);
            j.setMsg("密码输入不正确");
            return j;
        }
        try {
            boolean flag = memberService.updateMemberBasic(member);
            if (flag) {
            	saveLog(request, "/memberController.do?basicSave", CommonConstants.LOG_FOR_MEMBER, "修改用户("+member.getUserName()+")基本信息");
                j.setSuccess(true);
                j.setMsg("保存成功");
            }
        } catch (UserException e) {
            IUserErrCode code = new IUserErrCode();
            String msg = code.codeToMsg(e.getMessage());
            j.setMsg(msg);
        }
        }else{
        	j.setMsg("您没有登录或登录超时，请重新登录后重试！");
        }
        return j;
    }

    /**
     * 用户充值银行保存
     * @param request
     * @param textpassword
     * @param chargebank
     * @return
     */
    @RequestMapping(params = "chargeSave")
    @ResponseBody
    public Json chargeSave(HttpServletRequest request, String textpassword, BindChargeBank chargebank) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
        	 User user = sessionInfo.getUser();
             String password = user.getPassword();
             if (!password.equals(Encrypt.e(textpassword))) {
                 j.setSuccess(false);
                 j.setMsg("密码输入不正确");
                 return j;
             }
             try {
                 boolean flag = memberService.updateChargeBank(chargebank);
                 if (flag) {
                	 saveLog(request, "/memberController.do?chargeSave", CommonConstants.LOG_FOR_MEMBER, "修改用户("+chargebank.getUserName()+")充值银行信息");
                     j.setSuccess(true);
                     j.setMsg("保存成功");
                 }
             } catch (Exception e) {
                 j.setSuccess(false);
                 j.setMsg("保存失败");
             }
		} else {
			j.setMsg("您没有登录或登录超时，请重新登录后重试！");
		}
       
        return j;
    }

    /**
     * 用户充值银行保存
     * @param request
     * @param textpassword
     * @param fillbank
     * @return
     */
    @RequestMapping(params = "fillBankSave")
    @ResponseBody
    public Json fillBankSave(HttpServletRequest request, String textpassword, BindFillBank fillbank) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
        	 User user = sessionInfo.getUser();
             String password = user.getPassword();
             if (!password.equals(Encrypt.e(textpassword))) {
                 j.setSuccess(false);
                 j.setMsg("密码输入不正确");
                 return j;
             }
             try {
                 boolean flag = memberService.updateFillBank(fillbank);
                 if (flag) {
                	 saveLog(request, "/memberController.do?fillBankSave", CommonConstants.LOG_FOR_MEMBER, "修改用户("+fillbank.getUserName()+")提现银行信息");
                     j.setSuccess(true);
                     j.setMsg("保存成功");
                 }
             } catch (Exception e) {
                 j.setSuccess(false);
                 j.setMsg("保存失败");
             }
		} else {
			j.setMsg("您没有登录或登录超时，请重新登录后重试！");
		}
       
        return j;
    }

    /**
     * 用户提现银行修改
     * @param request
     * @param textpassword
     * @param userName
     * @param drawbank
     * @return
     */
    @RequestMapping(params="drawBankSave")
    @ResponseBody
    public Json drawBankSave(HttpServletRequest request, String textpassword, String userName,DrawBindBank drawbank) {
        Json j = new Json();
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        if (sessionInfo != null && sessionInfo.getUser() != null) {
        	 User user = sessionInfo.getUser();
             String password = user.getPassword();
             if (!password.equals(Encrypt.e(textpassword))) {
                 j.setSuccess(false);
                 j.setMsg("密码输入不正确");
                 return j;
             }
             try {
                 boolean flag = memberService.updateDrawBindBank(drawbank);
                 if (flag) {
                	 saveLog(request, "/memberController.do?drawBankSave", CommonConstants.LOG_FOR_MEMBER, "修改用户("+userName+")提现银行信息");
                     j.setSuccess(true);
                     j.setMsg("保存成功");
                 }
             } catch (Exception e) {
                 j.setSuccess(false);
                 j.setMsg("保存失败");
             }
		} else {
			j.setMsg("您没有登录或登录超时，请重新登录后重试！");
		}
       
        return j;
    }
    @RequestMapping(params = "clearMobile")
    @ResponseBody
    public Json clearMobile(HttpServletRequest request,String userCode,String userName) {
        Json j = new Json();
        try {
            boolean flag = memberInterface.clearMobile(userCode);
            if (flag) {
            	saveLog(request, "/memberController.do?clearMobile", CommonConstants.LOG_FOR_MEMBER,userName+"手机号清空");
                j.setSuccess(true);
                j.setMsg("清除成功");
            }
        } catch (UserException e) {
            IUserErrCode code = new IUserErrCode();
            String msg = code.codeToMsg(e.getMessage());
            j.setMsg(msg);
        }
        return j;

    }

    /**
     * 更改用户状态（解锁、锁定、注销）
     * @param userCode
     * @param status
     * @return
     */
    @RequestMapping(params = "updateMemberStatus")
    @ResponseBody
    public Json updateMemberStatus(HttpServletRequest request,String userCode,String userName,Integer status) {
        Json j = new Json();
        try {
            int i = memberService.updateMemberStatus(userCode, status);
            if (i>0) {
            	String statusStr="";
            	if(status==1){
            		statusStr="锁定";
            	}
            	if(status==0){
            		statusStr="解锁";
            	}
            	saveLog(request, "/memberController.do?updateMemberStatus", CommonConstants.LOG_FOR_MEMBER, userName+statusStr);
                j.setSuccess(true);
                j.setMsg("修改成功!");
            }
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;

    }
    @RequestMapping(params = "focus")
    public String focus() {
        return "/member/focus";
    }

    @RequestMapping(params = "level")
    public String level() {
        return "/member/level";
    }
    @RequestMapping(params = "blacklist")
    public String blacklist() {
        return "/member/blacklist";
    }

    /**
	 * 资金内部存取时用户查询
	 * @param userCode
	 * @param userName
	 * @param mobile
	 * @return
	 */
    @RequestMapping(params="customerInfo")
    @ResponseBody
	public List<Customer> customerInfo(String userCode,String userName,String mobile){
		return memberService.customerInfo(userCode, userName, mobile);
	}
    /**
	 * 获取系统账户
	 */
	@RequestMapping(params="sysAccount")
	@ResponseBody
	public List<Customer> sysAccount(String accountName){
		return memberService.sysAccount(accountName);
		
	}
    /**
     * 总账管理
     */
    @RequestMapping(params="toGlAccount")
    public String toGlAccount(HttpServletRequest request){
    	List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
    	return "/account/glaccount";
    }
    /**
     * 总账新增
     * @param vo
     * @return
     */
    @RequestMapping(params="accountAdd")
    @ResponseBody
    public Json accountAdd(Customer vo){
    	Json j=new Json();
    	memberService.accountSave(vo);
    	j.setSuccess(true);
    	return j;
    }
    /*根据总账账户查询用户名称
     * 
     */
    @RequestMapping(params="queryUserName")
    @ResponseBody
    public List<Map> queryUserName(HttpServletRequest request) {
    	return memberService.getMemberNameByCode(request.getParameter("accountCode"));
    }
}
