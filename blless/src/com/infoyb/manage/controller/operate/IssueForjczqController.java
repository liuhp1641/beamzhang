package com.cm.manage.controller.operate;

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
import com.cm.manage.service.operate.IIssueForjczqService;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.order.IssueForjczqVO;
import com.cm.order.http.IOrderManageHttpService;
import com.cm.other.http.IIndexManageHttpService;

@Controller
@RequestMapping("/issueForjczqController")
public class IssueForjczqController extends BaseController {

	private static final Logger logger = Logger
			.getLogger(IssueForjczqController.class);

	@Autowired
	private IIssueForjczqService issueForjczqService;

	@Resource(name = "orderInterface")
	private IOrderManageHttpService orderInterface;

	@Resource
	private IIndexManageHttpService indexManageInterface;

	/**
	 * 跳转到竞彩足球
	 * 
	 * @return
	 */
	@RequestMapping(params = "toIssueForjczq")
	public String toIssueForjczq() {
		return "/operate/issueForjczq";
	}

	/**
	 * 竞彩足球查询列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "issueForjczqList")
	@ResponseBody
	public EasyuiDataGridJson issueForjczqList(EasyuiDataGrid dg,
			IssueForjczqVO issue) {

		return issueForjczqService.issueForjczqList(dg, issue);
	}

	/**
	 * 竞彩足球详情
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @return
	 */
	@RequestMapping(params = "toIssueForjczqDetail")
	public String toIssueForjczqDetail(HttpServletRequest request,
			String issue, String sn) {
		Map issueInfo = issueForjczqService.issueForjczqInfo(issue, sn);
		request.setAttribute("issue", issueInfo);
		return "/operate/issueForjczqDetail";
	}

	/**
	 * 设置开赛时间
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @param endTime
	 * @return
	 */
	@RequestMapping(params = "editEndTime")
	@ResponseBody
	public Json editEndTime(HttpServletRequest request, String issue,
			String sn, String endTime) {
		Json j = new Json();
		boolean flag = orderInterface.editFootballMatchTime(issue, sn,
				DateUtil.format(endTime, "yyyy-MM-dd HH:mm:ss"));
		if (flag) {
			StringBuilder desc = new StringBuilder("设置");
			desc.append("赛事日期 : ");
			desc.append(issue);
			desc.append("编号 : ");
			desc.append(sn);
			desc.append("开赛时间为: ");
			desc.append(endTime);
			saveLog(request, "/issueForjczqController.do?editEndTime",
					CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
		}
		j.setSuccess(flag);
		return j;
	}

	/**
	 * 设置自动更新
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @param backup1
	 * @return
	 */
	@RequestMapping(params = "editBackup1")
	@ResponseBody
	public Json editBackup1(HttpServletRequest request, String issue,
			String sn, String backup1) {
		Json j = new Json();
		boolean flag = issueForjczqService.editBackup1(issue, sn, backup1);
		if (flag) {
			StringBuilder desc = new StringBuilder("设置");
			desc.append("赛事日期 : ");
			desc.append(issue);
			desc.append("编号 : ");
			desc.append(sn);
			desc.append("为: ");
			if ("".equals(backup1) || "0".equals(backup1)) {
				desc.append("自动更新 ");
			}
			if ("1".equals(backup1)) {
				desc.append("不自动更新 ");
			}
			saveLog(request, "/issueForjczqController.do?editBackup1",
					CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
		}
		j.setSuccess(flag);
		return j;
	}

	/**
	 * 设置隐藏对阵
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @param backup2
	 * @return
	 */
	@RequestMapping(params = "editBackup2")
	@ResponseBody
	public Json editBackup2(HttpServletRequest request, String issue,
			String sn, String backup2) {
		Json j = new Json();
		boolean flag = orderInterface.hiddenFootballMatch(issue, sn, backup2);
		if (flag) {
			StringBuilder desc = new StringBuilder("设置");
			desc.append("赛事日期 : ");
			desc.append(issue);
			desc.append("编号 : ");
			desc.append(sn);
			desc.append("为: ");
			if ("".equals(backup2) || "0".equals(backup2)) {
				desc.append("不隐藏");
			}
			if ("1".equals(backup2)) {
				desc.append("隐藏 ");
			}
			saveLog(request, "/issueForjczqController.do?editBackup2",
					CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
		}
		j.setSuccess(flag);
		return j;
	}

	/**
	 * 对阵推荐
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "issueRecommend")
	@ResponseBody
	public Json issueRecommend(HttpServletRequest request, String issue,
			String sn, String mainTeam, String guestTeam) {
		Json j = new Json();
		boolean flag = indexManageInterface.topMatch(issue + sn, mainTeam,
				guestTeam);
		if (flag) {
			 flag = issueForjczqService.issueRecommend(issue, sn,1);
			if (flag) {
				StringBuilder desc = new StringBuilder("推荐");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				saveLog(request, "/issueForjczqController.do?issueRecommend",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
				j.setSuccess(true);
			}
		}
		return j;
	}

	/**
	 * 对阵取消推荐
	 * @param request
	 * @param issue
	 * @param sn
	 * @return
	 */
	@RequestMapping(params="issueCancelRecommend")
	@ResponseBody
	public Json issueCancelRecommend(HttpServletRequest request, String issue,String sn) {
		Json j = new Json();
		boolean flag = indexManageInterface.cancelTopMatch(issue + sn);
		if (flag) {
			 flag = issueForjczqService.issueRecommend(issue, sn,0);
			if (flag) {
				StringBuilder desc = new StringBuilder("取消推荐");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				saveLog(request, "/issueForjczqController.do?issueCancelRecommend",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
				j.setSuccess(true);
			}
		}
		return j;
	}
	
	/**
	 * 是否取消比赛
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @return
	 */
	@RequestMapping(params = "editCancel")
	@ResponseBody
	public Json editCancel(HttpServletRequest request, String issue, String sn) {
		Json j = new Json();
		boolean flag = orderInterface.cancelFootballMatch(issue, sn);
		if (flag) {

			StringBuilder desc = new StringBuilder("设置");
			desc.append("赛事日期 : ");
			desc.append(issue);
			desc.append("编号 : ");
			desc.append(sn);
			desc.append("取消比赛");
			saveLog(request, "/issueForjczqController.do?editCancel",
					CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
		}
		j.setSuccess(flag);
		return j;
	}

	/**
	 * 设置对阵标签
	 * 
	 * @param request
	 * @param issue
	 * @param sn
	 * @return
	 */
	@RequestMapping(params = "editLabel")
	@ResponseBody
	public Json editLabel(HttpServletRequest request, String issue, String sn,
			String mix, String score, String flat, String letBall) {
		Json j = new Json();
		try {
			// 混合过关
			if ("无".equals(mix)) {
				mix = "";
			}
			boolean flag = issueForjczqService.editLabel(issue, sn, "00", "02",
					mix);
			if (flag) {
				StringBuilder desc = new StringBuilder("设置");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				desc.append("混合过关标签为：");
				if ("".equals(mix)) {
					desc.append("无");
				} else {
					desc.append(mix);
				}
				saveLog(request, "/issueForjczqController.do?editLabel",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
			}
			// 比分
			if ("无".equals(score)) {
				score = "";
			}
			flag = issueForjczqService.editLabel(issue, sn, "04", "01", score);
			if (flag) {
				StringBuilder desc = new StringBuilder("设置");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				desc.append("单关比分标签为：");
				if ("".equals(score)) {
					desc.append("无");
				} else {
					desc.append(score);
				}
				saveLog(request, "/issueForjczqController.do?editLabel",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
			}
			// 胜平负
			if ("无".equals(flat)) {
				flat = "";
			}
			flag = issueForjczqService.editLabel(issue, sn, "05", "01", flat);
			if (flag) {
				StringBuilder desc = new StringBuilder("设置");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				desc.append("单关胜平负标签为：");
				if ("".equals(flat)) {
					desc.append("无");
				} else {
					desc.append(flat);
				}
				saveLog(request, "/issueForjczqController.do?editLabel",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
			}
			// 让球
			if ("无".equals(letBall)) {
				letBall = "";
			}
			flag = issueForjczqService
					.editLabel(issue, sn, "01", "01", letBall);
			if (flag) {
				StringBuilder desc = new StringBuilder("设置");
				desc.append("赛事日期 : ");
				desc.append(issue);
				desc.append("编号 : ");
				desc.append(sn);
				desc.append("单关让球胜平负标签为：");
				if ("".equals(letBall)) {
					desc.append("无");
				} else {
					desc.append(letBall);
				}
				saveLog(request, "/issueForjczqController.do?editLabel",
						CommonConstants.LOG_FOR_SUBISSUE_JCZQ, desc.toString());
			}
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}
}
