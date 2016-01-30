package com.cm.manage.controller.information;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.information.Cooperation4Info;
import com.cm.manage.model.information.ThemeCooperation;
import com.cm.manage.model.information.Url;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.information.IInformationService;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.information.HtmlContentVo;
import com.cm.manage.vo.information.AppParaVo;
import com.cm.manage.vo.information.NewsDetail;
import com.cm.manage.vo.information.NewsVo;
import com.cm.manage.vo.information.ThemeVo;

@Controller
@RequestMapping("/informationController")
public class InformationController extends BaseController {
	@Autowired
	private IInformationService informationService;
	@Autowired
	private ILotteryControlService lotteryControlService;

	/**
	 * 跳转新闻查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toNewsList")
	public String toNewsList(HttpServletRequest request) {
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/news/newsList";
	}

	/**
	 * 跳转新闻修改与新增页面
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toNewsEdit")
	public String toNewsEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		NewsVo newsVo = new NewsVo();
		List<NewsDetail> details = null;
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		if (themeVo != null) {
			newsVo.setCheckFlag(checkFlag);
			HtmlContentVo htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
			NewsDetail newsDetail = new NewsDetail();
			newsDetail.setThemeVo(themeVo);
			newsDetail.setHtmlContentVo(htmlContentVo);
			newsVo.setMajor(newsDetail);
			List<ThemeVo> list = informationService
					.selectThemeVoByParent(themeCode);
			if (list != null && list.size() != 0) {
				details = new ArrayList<NewsDetail>();
				for (ThemeVo themeVo2 : list) {
					NewsDetail newsDetail2 = new NewsDetail();
					HtmlContentVo htmlContentVo2 = informationService
							.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
					newsDetail2.setThemeVo(themeVo2);
					newsDetail2.setHtmlContentVo(htmlContentVo2);
					details.add(newsDetail2);
				}
			}
		}
		newsVo.setMinorList(details);
		request.setAttribute("newsVo", newsVo);
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/news/newsEdit";
	}

	/**
	 * 跳转公告查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toAnnounceList")
	public String toAnnounceList(HttpServletRequest request) {
		return "/information/announce/announceList";
	}

	/**
	 * 跳转公告修改与新增
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toAnnounceEdit")
	public String toAnnounceEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		HtmlContentVo htmlContentVo = null;
		if (themeVo != null) {
			themeVo.setCheckFlag(checkFlag);
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
		}
		request.setAttribute("themeVo", themeVo);
		request.setAttribute("htmlContentVo", htmlContentVo);
		return "/information/announce/announceEdit";
	}

	/**
	 * 跳转活动查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toActivityList")
	public String toActivityList(HttpServletRequest request) {
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/activity/activityList";
	}

	/**
	 * 跳转活动修改与新增页面
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toActivityEdit")
	public String toActivityEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		HtmlContentVo htmlContentVo = null;
		if (themeVo != null) {
			themeVo.setCheckFlag(checkFlag);
			List<ThemeCooperation> themeCooperationList = informationService
					.selectThemeCooperationByThemeCode(themeVo.getTheme_code());
			request.setAttribute("themeCooperationList", themeCooperationList);
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
		}
		request.setAttribute("themeVo", themeVo);
		request.setAttribute("htmlContentVo", htmlContentVo);
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/activity/activityEdit";
	}

	/**
	 * 跳转广告查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toCommercialsList")
	public String toCommercialsList(HttpServletRequest request) {
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/commercials/commercialsList";
	}

	/**
	 * 跳转广告修改与新增页面
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toCommercialsEdit")
	public String toCommercialsEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		HtmlContentVo htmlContentVo = null;
		if (themeVo != null) {
			themeVo.setCheckFlag(checkFlag);
			List<ThemeCooperation> themeCooperationList = informationService
					.selectThemeCooperationByThemeCode(themeVo.getTheme_code());
			request.setAttribute("themeCooperationList", themeCooperationList);
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
		}
		request.setAttribute("themeVo", themeVo);
		request.setAttribute("htmlContentVo", htmlContentVo);
		List<Cooperation4Info> cooperation4InfoList = informationService
				.selectCooperation4InfoList("00");
		request.setAttribute("cooperation4InfoList", cooperation4InfoList);
		return "/information/commercials/commercialsEdit";
	}

	/**
	 * 跳转预测与推荐查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toPredictList")
	public String toPredictList(HttpServletRequest request) {
		List<LotteryControl> lotteryControlList = lotteryControlService
				.getLoteryControlList();
		request.setAttribute("lotteryControlList", lotteryControlList);
		return "/information/predict/predictList";
	}

	/**
	 * 跳转预测修改与新增页面
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toPredictEdit")
	public String toPredictEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		HtmlContentVo htmlContentVo = null;
		if (themeVo != null) {
			themeVo.setCheckFlag(checkFlag);
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
		}
		request.setAttribute("themeVo", themeVo);
		request.setAttribute("htmlContentVo", htmlContentVo);
		List<LotteryControl> lotteryControlList = lotteryControlService
				.getLoteryControlList();
		request.setAttribute("lotteryControlList", lotteryControlList);
		return "/information/predict/predictEdit";
	}

	/**
	 * 跳转推荐修改与新增页面
	 * 
	 * @param request
	 * @param themeCode
	 * @return
	 */
	@RequestMapping(params = "toRecommendEdit")
	public String toRecommendEdit(HttpServletRequest request, String themeCode,int checkFlag) {
		ThemeVo themeVo = informationService.selectThemeByThemeCode(themeCode);
		HtmlContentVo htmlContentVo = null;
		if (themeVo != null) {
			themeVo.setCheckFlag(checkFlag);
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(themeVo.getHtmlCode());
		}
		request.setAttribute("themeVo", themeVo);
		request.setAttribute("htmlContentVo", htmlContentVo);
		List<LotteryControl> lotteryControlList = lotteryControlService
				.getLoteryControlList();
		request.setAttribute("lotteryControlList", lotteryControlList);
		return "/information/predict/recommendEdit";
	}

	/**
	 * 跳转资讯查询页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toInfoList")
	public String toInfoList(HttpServletRequest request) {
		return "/information/info/infoList";
	}

	/**
	 * 跳转资讯修改与新增页面
	 * 
	 * @param request
	 * @param htmlCode
	 * @return
	 */
	@RequestMapping(params = "toInfoEdit")
	public String toInfoEdit(HttpServletRequest request, String htmlCode,int checkFlag) {
		HtmlContentVo htmlContentVo = null;
		{
			htmlContentVo = informationService
					.selectHtmlContentByHtmlCode(htmlCode);
			if(htmlContentVo!=null){
				htmlContentVo.setCheckFlag(checkFlag);
			}
		}
		request.setAttribute("htmlContentVo", htmlContentVo);
		return "/information/info/infoEdit";
	}

	/**
	 * 跳转url 管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toUrManage")
	public String toUrlList(HttpServletRequest request) {
		return "/information/urlManage";
	}

	/**
	 * 查询url列表
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "urlList")
	@ResponseBody
	public List<Url> urlList(String type) {
		return informationService.selectUrlByType(type);
	}

	/**
	 * 添加url
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "addUrl")
	@ResponseBody
	public Json addUrl(HttpServletRequest request, Url url) {
		Json j = new Json();
		try {
			url = informationService.addUrl(url);
			j.setObj(url);
			StringBuilder desc = new StringBuilder("增加");
			desc.append("url:");
			desc.append(url.getCode());
			saveLog(request, "/informationController.do?addUrl",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除url
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "delUrl")
	@ResponseBody
	public Json delUrl(HttpServletRequest request, Url url) {
		Json j = new Json();
		try {
			informationService.delUrl(url);
			StringBuilder desc = new StringBuilder("删除");
			desc.append("url:");
			desc.append(url.getCode());
			saveLog(request, "/informationController.do?delUrl",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑url
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "editUrl")
	@ResponseBody
	public Json editUrl(HttpServletRequest request, Url url) {
		Json j = new Json();
		try {
			informationService.editUrl(url);
			StringBuilder desc = new StringBuilder("编辑");
			desc.append("url:");
			desc.append(url.getCode());
			desc.append("名称:");
			desc.append(url.getName());
			desc.append("类型:");
			desc.append(url.getType());
			desc.append("地址:");
			desc.append(url.getName());
			desc.append("code值:");
			desc.append(url.getUrlCode());
			saveLog(request, "/informationController.do?editUrl",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

	/**
	 * 查询主题列表
	 * 
	 * @param dg
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "themeList")
	@ResponseBody
	public EasyuiDataGridJson themeList(EasyuiDataGrid dg, ThemeVo themeVo) {
		EasyuiDataGridJson edgj = null;
		try {
			if (themeVo.getProgramType() == 0) {
				return null;
			}
			edgj = informationService.selectThemeList(dg, themeVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}

	/**
	 * 查询资讯列表
	 * 
	 * @param dg
	 * @param htmlContentVo
	 * @return
	 */
	@RequestMapping(params = "htmlContentList")
	@ResponseBody
	public EasyuiDataGridJson htmlContentList(EasyuiDataGrid dg,
			HtmlContentVo htmlContentVo) {
		EasyuiDataGridJson edgj = null;
		try {
			edgj = informationService.selectHtmlContentList(dg, htmlContentVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}

	/**
	 * 得到主题全部，已上线，待上线，已下线 条数
	 * 
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "getNum")
	@ResponseBody
	public Json getNum(ThemeVo themeVo) {
		Json j = new Json();
		try {
			j.setObj(informationService.getNum(themeVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 得到资讯已使用 未使用条数
	 * 
	 * @param htmlContentVo
	 * @return
	 */
	@RequestMapping(params = "getNumHtml")
	@ResponseBody
	public Json getNumHtml(HtmlContentVo htmlContentVo) {
		Json j = new Json();
		try {
			j.setObj(informationService.getNumHtml(htmlContentVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		return j;
	}

	/***
	 * 新增或修改主题和资讯信息
	 * 
	 * @param request
	 * @param themeVo
	 * @param contentVo
	 * @param flag
	 * @return
	 */
	@RequestMapping(params = "editTheme")
	@ResponseBody
	public Json editTheme(HttpServletRequest request, ThemeVo themeVo,
			HtmlContentVo contentVo, int flag) {
		Json j = new Json();
		try {
			String themeCode = informationService.editTheme(request, themeVo,
					contentVo, flag);
			j.setMsg("编辑成功!");
			j.setObj(themeCode);
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder();
			if (flag == 1) {
				if (themeVo.getTheme_code() == null) {

					desc.append("增加");
				} else {
					desc.append("修改");
				}
				String programTypeStr = null;
				switch (themeVo.getProgramType()) {
				case 1:
					programTypeStr = "新闻: ";
					break;
				case 2:
					programTypeStr = "活动: ";
					break;
				case 3:
					programTypeStr = "广告: ";
					break;
				case 4:
					programTypeStr = "预测推荐: ";
					break;
				case 5:
					programTypeStr = "公告: ";
					break;
				default:
					break;
				}

				desc.append(programTypeStr);
				desc.append(themeCode);
				desc.append("标题: ");
				desc.append(themeVo.getThemeTitle());
			} else if (flag == 2) {
				if (contentVo.getHtmlCode() == null) {
					desc.append("增加");
					desc.append("资讯 ");
				} else {
					desc.append("修改");
					desc.append("资讯: ");
					desc.append(contentVo.getHtmlCode());
				}

				desc.append("标题: ");
				desc.append(contentVo.getHtmlTitle());
			}
			saveLog(request, "/informationController.do?editTheme",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("编辑失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 通过资讯类型 得到资讯前10条信息
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "shwoTenInfo")
	@ResponseBody
	public Json shwoTenInfo(int type) {
		Json j = new Json();
		try {
			List<HtmlContentVo> tenInfo = informationService
					.selectHtmlContent10ByType(type);
			j.setMsg("查询成功!");
			j.setSuccess(true);
			j.setObj(tenInfo);
		} catch (Exception e) {
			j.setMsg("查询失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 修改主题上下线状态
	 * 
	 * @param request
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "updateState")
	@ResponseBody
	public Json updateState(HttpServletRequest request, ThemeVo themeVo) {
		Json j = new Json();
		try {
			informationService.updateState(themeVo);
			j.setMsg("修改成功!");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("修改");
			desc.append("主题:");
			desc.append(themeVo.getTheme_code());
			desc.append("状态为:");
			if (themeVo.getState() == 1) {
				desc.append("上线");
			} else {
				desc.append("下线");
			}
			saveLog(request, "/informationController.do?updateState",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("保存失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 修改主题权重
	 * 
	 * @param request
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "updateWeight")
	@ResponseBody
	public Json updateWeight(HttpServletRequest request, ThemeVo themeVo) {
		Json j = new Json();
		try {
			informationService.updateWeight(themeVo);
			j.setMsg("修改成功!");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("修改");
			desc.append("主题:");
			desc.append(themeVo.getTheme_code());
			desc.append("权重为:");
			desc.append(themeVo.getWeight());
			saveLog(request, "/informationController.do?updateWeight",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("保存失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 修改活动 开始结束状态
	 * 
	 * @param request
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "updateActivityState")
	@ResponseBody
	public Json updateActivityState(HttpServletRequest request, ThemeVo themeVo) {
		Json j = new Json();
		try {
			informationService.updateActivityState(themeVo);
			j.setMsg("修改成功!");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("修改");
			desc.append("活动:");
			desc.append(themeVo.getTheme_code());
			desc.append("活动状态为:");
			if (themeVo.getActivityState() == 1) {
				desc.append("开始");
			} else {
				desc.append("结束");
			}
			saveLog(request, "/informationController.do?updateActivityState",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("保存失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 模拟删除还原
	 * 
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "hiddenTheme")
	@ResponseBody
	public Json hiddenTheme(ThemeVo themeVo) {
		Json j = new Json();
		try {
			informationService.updatehidden(themeVo);
			j.setMsg("修改成功!");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("保存失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 获得二维码url 字符串
	 * 
	 * @param request
	 * @param htmlCode
	 * @return
	 */
	@RequestMapping(params = "getTowPic")
	@ResponseBody
	public Json getTowPic(HttpServletRequest request, String htmlCode) {
		Json j = new Json();
		try {
			String url = informationService.getTowPic(htmlCode);
			j.setMsg("生成二维码成功!");
			j.setSuccess(true);
			j.setObj(url);
		} catch (Exception e) {
			j.setMsg("生成二维码失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 删除主题
	 * 
	 * @param request
	 * @param themeVo
	 * @return
	 */
	@RequestMapping(params = "deleteTheme")
	@ResponseBody
	public Json deleteTheme(HttpServletRequest request, ThemeVo themeVo) {
		Json j = new Json();
		try {
			informationService.deleteTheme(themeVo);
			j.setMsg("删除成功!");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("删除");
			desc.append("主题:");
			desc.append(themeVo.getTheme_code());
			saveLog(request, "/informationController.do?deleteTheme",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("删除失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 删除资讯
	 * 
	 * @param request
	 * @param htmlContentVo
	 * @return
	 */
	@RequestMapping(params = "deleteHtmlContent")
	@ResponseBody
	public Json deleteHtmlContent(HttpServletRequest request,
			HtmlContentVo htmlContentVo) {
		Json j = new Json();
		try {
			informationService.deleteHtmlContent(htmlContentVo);
			j.setMsg("修改成功!");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("删除");
			desc.append("资讯:");
			desc.append(htmlContentVo.getHtmlCode());
			saveLog(request, "/informationController.do?deleteTheme",
					CommonConstants.LOG_FOR_INFO, desc.toString());
		} catch (Exception e) {
			j.setMsg("保存失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
	

	@RequestMapping(params = "cooperation4recommendList")
	@ResponseBody
	public Json cooperation4recommendList(String platform) {
		Json j = new Json();
		try {
			List<Cooperation4Info> cooperation4InfoList = informationService
					.selectCooperation4InfoList(platform);
			j.setMsg("查询成功!");
			j.setSuccess(true);
			j.setObj(cooperation4InfoList);
		} catch (Exception e) {
			j.setMsg("查询失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(params = "sv4recommendList")
	@ResponseBody
	public Json sv4recommendList(String platform) {
		Json j = new Json();
		try {
			List<SoftwareVersion> SoftwareVersionList = informationService
					.selectSV4recommendList(platform);
			j.setMsg("查询成功!");
			j.setSuccess(true);
			j.setObj(SoftwareVersionList);
		} catch (Exception e) {
			j.setMsg("查询失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(params = "selectAppParaByCode")
	@ResponseBody
	public Json selectAppParaByCode(String appCode) {
		Json j = new Json();
		try {
			List<AppParaVo> appParaVoList = informationService
					.selectAppParaByCode(appCode);
			j.setMsg("查询成功!");
			j.setSuccess(true);
			j.setObj(appParaVoList);
		} catch (Exception e) {
			j.setMsg("查询失败!");
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
}
