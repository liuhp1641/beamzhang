package com.cm.manage.controller.share;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.share.IShareUserService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.share.JoinUserRecordVO;
import com.cm.manage.vo.share.ShareUserInfoVO;
import com.cm.manage.vo.share.ShareUserRecordExtraVO;
@Controller
@RequestMapping("/shareUserController")
public class ShareUserController extends BaseController {
	private static final Logger logger = Logger.getLogger(ShareUserController.class);
	
	@Autowired
	private IShareUserService shareUserService;
	/**
	 * 跳转到分享奖励日志
	 * @return
	 */
	@RequestMapping(params="toShareUserList")
	public String toShareUserList(){
		return "/share/shareUserList";
	}
	/**
	 * 分享奖励日志列表
	 * @param dg
	 * @param shareUser
	 * @return
	 */
	@RequestMapping(params="shareUserList")
	@ResponseBody
	public EasyuiDataGridJson shareUserList(EasyuiDataGrid dg,ShareUserInfoVO shareUser) {
		return shareUserService.shareUserList(dg, shareUser);
	}
	/**
	 * 参与人奖励汇总
	 * @param shareUser
	 * @return
	 */
	@RequestMapping(params="shareUserCount")
	@ResponseBody
	 public Json shareUserCount(ShareUserInfoVO shareUser) {
        Json j = new Json();
        j.setObj(shareUserService.shareUserCount(shareUser));
        j.setSuccess(true);
        return j;
	 }
	/**
	 * 跳转分享人基本奖励
	 * @return
	 */
	@RequestMapping(params="toShareUserBase")
	public String toShareUserBase(HttpServletRequest request,String shareUserId){
		request.setAttribute("shareUserId", shareUserId);
		return "/share/shareBase";
	}
	/**
	 * 基本奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	@RequestMapping(params="shareUserRecordBase")
	@ResponseBody
	public EasyuiDataGridJson shareUserRecordBase(EasyuiDataGrid dg,String shareUserId){
		return shareUserService.shareUserRecordBase(dg, shareUserId);
	}
	/**
	 * 额外奖励
	 * @return
	 */
	@RequestMapping(params="toShareUserExtra")
	public String toShareUserExtra(HttpServletRequest request,String shareUserId){
		request.setAttribute("shareUserId", shareUserId);
		return "/share/shareExtra";
	}
	/**
	 * 额外奖励
	 * @param shareUserId
	 * @return
	 */
	@RequestMapping(params="shareUserRecordExtra")
	@ResponseBody
	public List<ShareUserRecordExtraVO> shareUserRecordExtra(String shareUserId){
		return shareUserService.shareUserRecordExtra(shareUserId);
	}
	/**
	 * 跳转参与奖励页面 
	 * @return
	 */
	@RequestMapping(params="toJoinUserList")
	public String toJoinUserList(){
		return "/share/joinUserList";
	}
	/**
	 * 参与人奖励列表
	 * @param dg
	 * @param joinUser
	 * @return
	 */
	@RequestMapping(params="joinUserList")
	@ResponseBody
	public EasyuiDataGridJson joinUserList(EasyuiDataGrid dg,JoinUserRecordVO joinUser) {
		return shareUserService.joinUserList(dg, joinUser);
	}
}
