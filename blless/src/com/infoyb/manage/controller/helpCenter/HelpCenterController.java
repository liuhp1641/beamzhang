package com.cm.manage.controller.helpCenter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.controller.cms.CmsController;
import com.cm.manage.model.helpCenter.Picture;
import com.cm.manage.model.helpCenter.QuestionType;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.service.helpCenter.IQuestionService;
import com.cm.manage.service.system.IUserService;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.helpCenter.AorVo;
import com.cm.manage.vo.helpCenter.HelperContentVo;
import com.cm.manage.vo.helpCenter.QuestionAor;
import com.cm.manage.vo.helpCenter.QuestionVo;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;

@Controller
@RequestMapping("/helpCenterController")
public class HelpCenterController extends BaseController {
	private static final Logger logger = Logger.getLogger(CmsController.class);

	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	private IUserService userService;

	/**
	 * 跳转到查询问题页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toQuestionList")
	public String toQuestionList(HttpServletRequest request) {
		// 问题类型
		List<QuestionType> questionTypeList = null;
		try {
			questionTypeList = questionService.questionTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject(questionTypeList);
		String qtlJson = json.toString();
		request.setAttribute("questionTypeList", questionTypeList);
		request.setAttribute("qtlJson", qtlJson);
		return "/helpCenter/questionList";
	}

	/**
	 * 跳转到问题类型管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toQuestionType")
	public String toQuestionType(HttpServletRequest request) {
		// 问题类型
		List<QuestionType> questionTypeList = null;
		try {
			questionTypeList = questionService.questionTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("questionTypeList", questionTypeList);
		return "/helpCenter/questionType";
	}

	/**
	 * 得到问题list
	 * 
	 * @param dg
	 * @param questionVo
	 * @return
	 */
	@RequestMapping(params = "questionList")
	@ResponseBody
	public EasyuiDataGridJson getQuestionList(EasyuiDataGrid dg,
			QuestionVo questionVo) {
		EasyuiDataGridJson edgj = null;
		try {
			edgj = questionService.questionList(dg, questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}

	/**
	 * 获得处理问题 总数，处理 未处理数量
	 * 
	 * @param questionVo
	 * @return
	 */
	@RequestMapping(params = "getNum")
	@ResponseBody
	public Json getNum(QuestionVo questionVo) {
		Json j = new Json();
		try {
			j.setObj(questionService.getNum(questionVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 跳转问题 详情页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "toQuestionAorById")
	public String toQuestionAorById(HttpServletRequest request, long id) {
		QuestionAor questionAor = null;
		try {
			questionAor = questionService.getQuestionAorById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<QuestionType> questionTypeList = null;
		try {
			questionTypeList = questionService.questionTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("questionTypeList", questionTypeList);
		List<Picture> pictureList = null;
		try {
			pictureList = questionService.pictureListByQid(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("questionAor", questionAor);
		request.setAttribute("questionTypeList", questionTypeList);
		request.setAttribute("pictureList", pictureList);
		return "/helpCenter/questionDetail";
	}

	/**
	 * 更改 是否隐藏
	 * 
	 * @param qid
	 *            问题ID
	 * @param aid
	 *            回复Id
	 * @param hiddenFlag
	 *            是否隐藏标示
	 * @param qaType
	 *            问题/回复 标示
	 * @return
	 */
	@RequestMapping(params = "updateHiddenFlag")
	@ResponseBody
	public Json updateHiddenFlag(HttpServletRequest request, long qid,
			long aid, int hiddenFlag, int qaType) {
		Json j = new Json();
		try {
			questionService.updateQAorHiddenFlag(qid, aid, hiddenFlag, qaType);
			j.setObj(true);
			j.setMsg("修改公开隐藏成功");
			StringBuilder desc = new StringBuilder("修改");
			if (qaType == 1) {
				desc.append("主问题: " + qid);
				if (hiddenFlag == 1) {
					desc.append("为公开");
				} else if (hiddenFlag == 2) {
					desc.append("为隐藏");
				}
			} else if (qaType == 2) {
				desc.append("追问: " + aid);
				if (hiddenFlag == 1) {
					desc.append("为公开");
				} else if (hiddenFlag == 2) {
					desc.append("为隐藏");
				}
			}
			saveLog(request, "/helpCenterController.do?updateHiddenFlag",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setObj(false);
			j.setMsg("修改公开隐藏失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 更改 权重
	 * 
	 * @param id
	 * @param weight
	 * @return
	 */
	@RequestMapping(params = "updateWeight")
	@ResponseBody
	public Json updateWeight(HttpServletRequest request, long id, int weight) {
		Json j = new Json();
		try {
			questionService.updateWeight(id, weight);
			j.setMsg("修改权重成功");
			StringBuilder desc = new StringBuilder("修改");
			desc.append("主问题: ");
			desc.append(id);
			desc.append("权重为: ");
			desc.append(weight);
			saveLog(request, "/helpCenterController.do?updateWeight",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setMsg("修改权重失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	
	/**
	 * 更改 权重
	 * 
	 * @param id
	 * @param weight
	 * @return
	 */
	@RequestMapping(params = "deleteQuestion")
	@ResponseBody
	public Json deleteQuestion(HttpServletRequest request, long id) {
		Json j = new Json();
		try {
			questionService.deleteQuestion(id);
			j.setMsg("删除问题成功");
			StringBuilder desc = new StringBuilder("删除");
			desc.append("主问题: ");
			desc.append(id);
			saveLog(request, "/helpCenterController.do?deleteQuestion",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setMsg("删除问题失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	
	/**
	 * 更改问题类型
	 * 
	 * @param qid
	 * @param qtid
	 * @return
	 */
	@RequestMapping(params = "updateQuestionType")
	@ResponseBody
	public Json updateQuestionType(HttpServletRequest request, long qid,
			int qtid) {
		Json j = new Json();
		try {
			questionService.updateQuestionType(qid, qtid);
			j.setObj(true);
			j.setMsg("修改问题类型成功");
			StringBuilder desc = new StringBuilder("修改");
			desc.append("主问题: ");
			desc.append(qid);
			desc.append("的问题类型为: ");
			desc.append(qtid);
			saveLog(request, "/helpCenterController.do?updateQuestionType",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setObj(false);
			j.setMsg("修改问题类型失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 问题 主题回复
	 * 
	 * @param request
	 * @param qid
	 * @param content
	 * @return
	 */
	@RequestMapping(params = "newAor")
	@ResponseBody
	public Json newAor(HttpServletRequest request, long qid, String content) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		User user = sessionInfo.getUser();
		Json j = new Json();
		try {
			questionService.saveAor(qid, content, user.getId());
			List<AorVo> aorVosList = questionService.getAorVoListByQid(qid);
			j.setObj(aorVosList);
			j.setMsg("编辑成功");
			StringBuilder desc = new StringBuilder("回复");
			desc.append("主问题: ");
			desc.append(qid);
			desc.append("的内容为: ");
			desc.append(content);
			saveLog(request, "/helpCenterController.do?newAor",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setMsg("编辑失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 修改问题回复 update操作
	 * 
	 * @param request
	 * @param qid
	 * @param id
	 * @param content
	 * @return
	 */
	@RequestMapping(params = "updateReply")
	@ResponseBody
	public Json updateReply(HttpServletRequest request, long qid, long id,
			String content) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		User user = sessionInfo.getUser();
		Json j = new Json();
		try {
			questionService.updateAor(qid, id, content, user.getId());
			List<AorVo> aorVosList = questionService.getAorVoListByQid(qid);
			j.setObj(aorVosList);
			j.setMsg("编辑成功");

			StringBuilder desc = new StringBuilder("修改");
			desc.append("主问题: ");
			desc.append(qid);
			desc.append(" 回复: ");
			desc.append(id);
			desc.append(" 的内容为: ");
			desc.append(content);
			saveLog(request, "/helpCenterController.do?updateReply",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setMsg("编辑失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 追问回复 save操作
	 * 
	 * @param request
	 * @param qid
	 * @param pId
	 * @param content
	 * @return
	 */
	@RequestMapping(params = "editorReply")
	@ResponseBody
	public Json editorReply(HttpServletRequest request, long qid, long pId,
			String content) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		User user = sessionInfo.getUser();
		Json j = new Json();
		try {
			questionService.saveRAor(qid, pId, content, user.getId());
			List<AorVo> aorVosList = questionService.getAorVoListByQid(qid);
			j.setObj(aorVosList);
			j.setMsg("编辑成功");

			StringBuilder desc = new StringBuilder("编辑");
			desc.append("主问题: ");
			desc.append(qid);
			desc.append(" 回复: ");
			desc.append(pId);
			desc.append(" 的内容为: ");
			desc.append(content);
			saveLog(request, "/helpCenterController.do?editorReply",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			j.setMsg("编辑失败");
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 得到问题类型list
	 * 
	 * @return
	 */
	@RequestMapping(params = "questionTypeList")
	@ResponseBody
	public List<QuestionType> questionTypeList() {

		// 问题类型
		List<QuestionType> questionTypeList = null;
		try {
			questionTypeList = questionService.questionTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionTypeList;
	}

	/**
	 * 添加类型
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "addType")
	@ResponseBody
	public Json addType(HttpServletRequest request, QuestionType questionType) {
		Json j = new Json();
		try {
			questionType = questionService.addType(questionType);
			j.setObj(questionType);
			StringBuilder desc = new StringBuilder("添加");
			desc.append("问题类型: ");
			desc.append(questionType.getQtName());
			desc.append(" 问题序号为: ");
			desc.append(questionType.getSerialNumber());
			saveLog(request, "/helpCenterController.do?addType",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除类型
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "delType")
	@ResponseBody
	public Json delType(HttpServletRequest request, QuestionType questionType) {
		Json j = new Json();
		try {
			questionService.delType(questionType);
			StringBuilder desc = new StringBuilder("删除");
			desc.append("问题类型: ");
			desc.append(questionType.getId());
			saveLog(request, "/helpCenterController.do?delType",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑类型
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "editType")
	@ResponseBody
	public Json editType(HttpServletRequest request, QuestionType questionType) {
		Json j = new Json();
		try {
			questionService.editType(questionType);
			StringBuilder desc = new StringBuilder("编辑");
			desc.append("问题类型: ");
			desc.append(questionType.getQtName());
			desc.append(" 问题Id为: ");
			desc.append(questionType.getId());
			desc.append(" 问题序号为: ");
			desc.append(questionType.getSerialNumber());
			saveLog(request, "/helpCenterController.do?editType",
					CommonConstants.LOG_FOR_MESSAGE, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}
	
	/**
	 * 跳转到查询问题页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toHelperContentList")
	public String toHelperContentList(HttpServletRequest request) {
		// 问题类型
		List<Syuser> syuserList = null;
		try {
			syuserList = userService.getUserList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("syuserList", syuserList);
		return "/helpCenter/helperContentList";
	}
	
	
	
	/**
	 * 得到客服回复详情list
	 * 
	 * @param dg
	 * @param questionVo
	 * @return
	 */
	@RequestMapping(params = "helperContentList")
	@ResponseBody
	public EasyuiDataGridJson getHelperContentList(EasyuiDataGrid dg,
			HelperContentVo helperContentVo) {
		EasyuiDataGridJson edgj = null;
		try {
			edgj = questionService.helperContentList(dg, helperContentVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}
}
