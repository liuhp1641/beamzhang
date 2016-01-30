package com.cm.manage.controller.scoremall;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.scoremall.ScoreMallLogSum;
import com.cm.scoreMall.http.IScoreMall4BackHttpService;
import com.cm.scoreMall.http.bean.AwardProbability4Back;
import com.cm.scoreMall.http.bean.CCNVo4Back;
import com.cm.scoreMall.http.bean.CommodityAndPro4Back;
import com.cm.scoreMall.http.bean.CommodityLogMsg4Back;
import com.cm.scoreMall.http.bean.CommodityLogVo4Back;
import com.cm.scoreMall.http.bean.CommodityMsg4Back;
import com.cm.scoreMall.http.bean.CommodityVo4Back;
/**
 * 
 *=============================================
 *  @ClassName: ScoreMallController.java
 *	@desc:  积分商城控制器
 *  
 *  @author: zqiang  DateTime 2014-12-2 下午5:04:19    
 *  @version: 1.0
 *  @tags :
 *=============================================
 */
@Controller
@RequestMapping(value="/scoreMallController")
public class ScoreMallController extends BaseController {
	private static Logger logger = Logger.getLogger(ScoreMallController.class);
	@Resource(name="scoreMallInterface")
	private IScoreMall4BackHttpService scoreMallInterface;
	@Autowired
	private IMemberService memberService;
	/**
	 * 
	 *  Function: 跳转到积分商城页面
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:55:28
	 *  @return
	 */
	@RequestMapping(params="toScoreMallCenter")
	public String toScoreMall() {
		return "/scoremall/scoreMallCenter";
	}
	/**
	 * 
	 *  Function: 跳转至创建活动页面
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:55:20
	 *  @return
	 */
	@RequestMapping(params="toCreateActive")
	public String toCreateActive() {
		return "/scoremall/createScoreMall";
	}
	/**
	 * 
	 *  Function: 跳转至总账账户列表
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:55:10
	 *  @return
	 */
	@RequestMapping(params="toFindAccount")
	public String toFindAccount() {
		return "/scoremall/amountAccount";
	}
	/**
	 * 
	 *  Function: 跳转至抽奖日志页面
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:54:55
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="toDrawLog")
	public String toDrawLog(HttpServletRequest request) {
		List<CCNVo4Back> list = scoreMallInterface.queryCCNVo4BackList();
		request.setAttribute("activeName", list);
		return "/scoremall/drawLog";
	}
	/**
	 * 
	 *  Function: 查询商品列表(积分商城初始化页面)
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:54:44
	 *  @param request
	 *  @param dg
	 *  @param commodityVo4Back
	 *  @return
	 */
	@RequestMapping(params = "queryCommodityMsg4BackList")
	@ResponseBody
	public EasyuiDataGridJson queryCommodityMsg4BackList(HttpServletRequest request,EasyuiDataGrid dg,CommodityVo4Back commodityVo4Back) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		//根据总账code查询总账账户用户民
		@SuppressWarnings("rawtypes")
		List<Map> list = memberService.getMemberNameByCode("");
		try {
			CommodityMsg4Back commodityMsg4Back =scoreMallInterface.queryCommodityMsg4BackList(dg.getPage(), dg.getRows(),commodityVo4Back);
			List<CommodityVo4Back> comm = commodityMsg4Back.getCommodityVo4BacksList();
			for (int i=0;i<comm.size();i++) {
				for(int j=0;j<list.size();j++) {
					if((list.get(j).get("USER_CODE")).equals(comm.get(i).getAccountCode())) {
						comm.get(i).setAccountName(list.get(j).get("USER_NAME").toString());
					}
				}
			}
			edgj.setRows(commodityMsg4Back.getCommodityVo4BacksList());
			edgj.setTotal(commodityMsg4Back.getItemTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}
	/**
	 * 
	 *  Function: 查询日志列表（抽奖日志初始化页面）
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:47:15
	 *  @param request
	 *  @param dg
	 *  @param log
	 *  @return
	 */
	@RequestMapping(params = "queryCommodityDragLogList")
	@ResponseBody
	public EasyuiDataGridJson queryCommodityDragLogList(HttpServletRequest request, EasyuiDataGrid dg,CommodityLogVo4Back log) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		List<Map> list = memberService.getMemberNameByCode("");
		List<String> userCodeList = new ArrayList<String>();
		CommodityLogMsg4Back clb = null;
		int isVague = log.getIsVague();//是否模糊查询  0为否1为是
		try {
			if(0!=list.size()&&("".equals(log.getUserCode())||null==log.getUserCode())) {
				log.setUserCodeList(userCodeList);
				clb = scoreMallInterface.queryCommodityLogMsg4BackList(dg.getPage(), dg.getRows(), log);
				for(int i=0;i<list.size();i++) {
					for(CommodityLogVo4Back com : clb.getCommodityLogVo4BackList()) {
						if((list.get(i).get("USER_CODE")).equals(com.getUserCode())) {
							com.setUserCode(list.get(i).get("USER_NAME").toString());
							com.setUserName(list.get(i).get("USER_CODE").toString());
						}
					}
				}
			}else {
				List<Map> map = memberService.getMemberCodeByName(log.getUserCode(),isVague);
				if(0==map.size()) {
					userCodeList = null;
				}else {
					for(int j=0;j<map.size();j++) {
						userCodeList.add((String) map.get(j).get("USER_CODE"));
					}
				}
				log.setUserCodeList(userCodeList);
				clb = scoreMallInterface.queryCommodityLogMsg4BackList(dg.getPage(), dg.getRows(), log);
				for(int i=0;i<list.size();i++) {
					for(CommodityLogVo4Back com : clb.getCommodityLogVo4BackList()) {
						if((list.get(i).get("USER_CODE")).equals(com.getUserCode())) {
							com.setUserCode(list.get(i).get("USER_NAME").toString());
							com.setUserName(list.get(i).get("USER_CODE").toString());
						}
					}
				}
			}
			edgj.setRows(clb.getCommodityLogVo4BackList());
			edgj.setTotal(clb.getItemTotal());
			request.setAttribute("clb", clb);
			request.setAttribute("itemTotal", clb.getCommodityLogVo4BackList().size());
			request.setAttribute("allPayNum", clb.getAllPayNum());
			request.setAttribute("allAwardScoreNum", clb.getAllAwardScoreNum());
			request.setAttribute("allAwardRedBagNum", clb.getAllAwardRedBagNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edgj;
	}
	/**
	 * 
	 *  Function: 抽奖日志页面数据统计
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:46:47
	 *  @param request
	 *  @param dg
	 *  @param log
	 *  @return
	 */
	@RequestMapping(params = "queryTotal")
	@ResponseBody
	public ScoreMallLogSum queryTotal(HttpServletRequest request,EasyuiDataGrid dg,CommodityLogVo4Back log) {
		List<Map> list = memberService.getMemberNameByCode("");
		List<String> userCodeList = new ArrayList<String>();
		CommodityLogMsg4Back clb = null;
		try {
			if(0!=list.size()&&("".equals(log.getUserCode())||null==log.getUserCode())) {
				log.setUserCodeList(userCodeList);
				clb = scoreMallInterface.queryCommodityLogMsg4BackList(dg.getPage(), dg.getRows(), log);
				for(int i=0;i<list.size();i++) {
					for(CommodityLogVo4Back com : clb.getCommodityLogVo4BackList()) {
						if((list.get(i).get("USER_CODE")).equals(com.getUserCode())) {
							com.setUserCode(list.get(i).get("USER_NAME").toString());
						}
					}
				}
			}else {
				List<Map> map = memberService.getMemberCodeByName(log.getUserCode(),log.getIsVague());
				if(0==map.size()) {
					userCodeList = null;
				}else {
					for(int j=0;j<map.size();j++) {
						userCodeList.add((String) map.get(j).get("USER_CODE"));
					}
				}
				log.setUserCodeList(userCodeList);
				clb = scoreMallInterface.queryCommodityLogMsg4BackList(dg.getPage(), dg.getRows(), log);
				for(int i=0;i<list.size();i++) {
					for(CommodityLogVo4Back com : clb.getCommodityLogVo4BackList()) {
						if((list.get(i).get("USER_CODE")).equals(com.getUserCode())) {
							com.setUserCode(list.get(i).get("USER_NAME").toString());
						}
					}
				}
			}
			request.setAttribute("clb", clb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ScoreMallLogSum sms = new ScoreMallLogSum();
		sms.setItemTotal(clb == null?0:clb.getItemTotal());
		sms.setAllAwardRedBagNum(clb == null?0:clb.getAllAwardRedBagNum());
		sms.setAllAwardScoreNum(clb == null?0:clb.getAllAwardScoreNum());
		sms.setAllPayNum(clb == null?0:clb.getAllPayNum());
		return sms;
	}
	/**
	 * 
	 *  Function: 积分商城保存商品
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:47:57
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="saveCommodityAndPro")
	@ResponseBody
	public boolean saveCommodityAndPro(HttpServletRequest request) {
		//定义商品对象
		CommodityVo4Back com = new CommodityVo4Back();
		String[] rewardNum = request.getParameter("arrawardNum").split(",");
		String[] arawardCode = null;
		if(!"".equals(request.getParameter("arawardCode"))) {
			arawardCode = request.getParameter("arawardCode").split(",");
		}
		String[] hitRate = request.getParameter("arrawardProbability").split(",");
		AwardProbability4Back ap = new AwardProbability4Back();
		List<AwardProbability4Back> apList = new ArrayList<AwardProbability4Back>(); 
		for(int i=0;i<rewardNum.length;i++) {
			if(null != arawardCode) {
				ap.setApCode(arawardCode[i]);
			}
			ap.setAwardNum(Double.parseDouble(rewardNum[i]==null?"0":rewardNum[i]));
			ap.setAwardProbability(Integer.parseInt(hitRate[i]==null?"0":hitRate[i]));
			apList.add(ap);
			ap = new AwardProbability4Back();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//商品code
		String commodityCode = request.getParameter("commodityCode");
		if(null !=commodityCode&&!"".equals(commodityCode)) {
			com.setCommodityCode(commodityCode);
		}
		com.setCommodityName(request.getParameter("commodityName"));
		com.setTermFlag(Integer.parseInt(request.getParameter("termFlag")==null?"0":request.getParameter("termFlag")));
		com.setAwardType(Integer.parseInt(request.getParameter("awardType")==null?"0":request.getParameter("awardType")));
		com.setPayNum(Integer.parseInt(request.getParameter("payNum")==null?"0":request.getParameter("payNum")));
		com.setState(Integer.parseInt(request.getParameter("state")==null?"0":request.getParameter("state")));
		com.setAwardNumMax(Double.parseDouble(request.getParameter("awardNumMax")==null?"0":request.getParameter("awardNumMax")));
		com.setAwardNumMin(Double.parseDouble(request.getParameter("awardNumMin")==null?"0":request.getParameter("awardNumMin")));
		com.setPartakeDateType(Integer.parseInt(request.getParameter("partakeDateType")==""?"0":request.getParameter("partakeDateType")));
		com.setPartakeNum(Integer.parseInt(request.getParameter("partakeNum")==""?"0":request.getParameter("partakeNum")));
		com.setWeight(Integer.parseInt(request.getParameter("weight")==""?"0":request.getParameter("weight")));
		com.setAccountCode(request.getParameter("accountCode"));
		com.setStyle(Integer.parseInt(request.getParameter("colorId")==""?"0":request.getParameter("colorId")));
		if(!"".equals(request.getParameter("beginDate"))&&!"".equals(request.getParameter("endDate"))) {
			try {
				com.setBeginDate(sdf.parse(request.getParameter("beginDate")));
				com.setEndDate(sdf.parse(request.getParameter("endDate")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//商品概率对象
		CommodityAndPro4Back cap = new CommodityAndPro4Back();
		cap.setAwardProbability4BackList(apList);
		cap.setCommodityVo4Back(com);
		if(!"".equals(commodityCode)) {
			saveLog(request, "/scoreMallController.do?saveCommodityAndPro", CommonConstants.LOG_FOR_SCORE_MALL, "编辑商品("+request.getParameter("commodityName")+")");
			return scoreMallInterface.updateCommodityAndPro(cap);
		}else {
			saveLog(request, "/scoreMallController.do?saveCommodityAndPro", CommonConstants.LOG_FOR_SCORE_MALL, "新增商品("+request.getParameter("commodityName")+")");
			return scoreMallInterface.saveCommodityAndPro(cap);
		}
	}
	/**
	 * 
	 *  Function: 根据商品code查询商品
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:48:34
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="queryCommoditVo4Back")
	public String queryCommoditVo4Back(HttpServletRequest request) {
		CommodityAndPro4Back cap = new CommodityAndPro4Back();
		String code = request.getParameter("commodityCode");
		String flag = request.getParameter("type");
		cap = scoreMallInterface.queryCommoditVo4Back(code);
		String accountCode = cap.getCommodityVo4Back().getAccountCode();
		List<Map> list = memberService.getMemberNameByCode(accountCode);
		Map m=null;
		if(0!=list.size()){
			m =list.get(0);
			cap.getCommodityVo4Back().setAccountName((String)m.get("USER_NAME"));
		}else {
			cap.getCommodityVo4Back().setAccountName("");
		}
		request.setAttribute("cap", cap.getCommodityVo4Back());
		request.setAttribute("flag", flag);
		return "/scoremall/createScoreMall";
	}
	/**
	 * 
	 *  Function: //商品上下线操作
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:49:05
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="updateCommodityAndPro")
	@ResponseBody
	public String updateCommodityAndPro(HttpServletRequest request) {
		CommodityVo4Back com = new CommodityVo4Back();
		com.setCommodityCode(request.getParameter("commodityCode"));
		if("start".equals(request.getParameter("type"))) {
			com.setState(1);
		}else {
			if("online".equals(request.getParameter("type"))) {
				if("1".equals(request.getParameter("termFlag"))) {
					com.setState(3);
				}
				if("0".equals(request.getParameter("termFlag"))) {
					com.setState(1);
				}
			}
			if("offline".equals(request.getParameter("type"))) {
				com.setState(2);
			}
			if(request.getParameter("state") == "1") {
				saveLog(request, "/scoreMallController.do?updateCommodityAndPro", CommonConstants.LOG_FOR_SCORE_MALL, "商品("+request.getParameter("commodityName")+")上线操作");
			}else if(request.getParameter("state") == "2") {
				saveLog(request, "/scoreMallController.do?updateCommodityAndPro", CommonConstants.LOG_FOR_SCORE_MALL, "商品("+request.getParameter("commodityName")+")下线操作");
			}
		}
		return scoreMallInterface.updateCommodity(com);
	}
	/**
	 * 
	 *  Function: 通过商品code获取奖品设置
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:49:28
	 *  @param commodityCode
	 *  @return
	 */
	@RequestMapping(params="queryAwardByCommCode")
	@ResponseBody
	public List<AwardProbability4Back> queryAwardByCommCode(String commodityCode) {
		List<AwardProbability4Back> list = scoreMallInterface.queryCommoditVo4Back(commodityCode).getAwardProbability4BackList();
		return list;
	}
	/**
	 * 
	 *  Function: 活动名称同名校验
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:49:44
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="sameNameValidaty")
	@ResponseBody
	public boolean sameNameValidaty(HttpServletRequest request) {
		return scoreMallInterface.queryCommodityNameExist(request.getParameter("commodityName"));
	}
	/**
	 * 
	 *  Function: 查看商品（通过商品编号或者查看商品按钮）
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:49:59
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="scanCommoditVo4Back")
	public String scanCommodityVo4Back(HttpServletRequest request) {
		CommodityAndPro4Back cap = new CommodityAndPro4Back();
		String code = request.getParameter("commodityCode");
		String flag = request.getParameter("type");
		cap = scoreMallInterface.queryCommoditVo4Back(code);
		String accountCode = cap.getCommodityVo4Back().getAccountCode();
		List<Map> list = memberService.getMemberNameByCode(accountCode);
		Map m=null;
		if(0!=list.size()){
			m =list.get(0);
			cap.getCommodityVo4Back().setAccountName((String)m.get("USER_NAME"));
		}else {
			cap.getCommodityVo4Back().setAccountName("");
		}
		request.setAttribute("cap", cap.getCommodityVo4Back());
		request.setAttribute("flag", flag);
		return "/scoremall/scanScoreMall";
	}
	/**
	 * 
	 *  Function: 批量编辑权重 
	 *
	 *  @author zhangqiang  DateTime 2014-12-22 下午3:12:21
	 *  @param request
	 *  @return
	 */
	@RequestMapping(params="updateCommodityWeight")
	@ResponseBody
	public boolean updateCommodityWeight(HttpServletRequest request) {
		//获取数据
		String listCode = request.getParameter("listCode");
		String listWeight = request.getParameter("listWeight");
		String[] code = listCode.split(",");
		String[] weight = listWeight.split(",");
		List<CommodityVo4Back> commList =new ArrayList<CommodityVo4Back>();
		for(int i=0;i<code.length;i++) {
			CommodityVo4Back com = new CommodityVo4Back();
			com.setCommodityCode(code[i]);
			com.setWeight(Integer.parseInt(weight[i]));
			commList.add(com);
		}
		return scoreMallInterface.updateCommodityWeight(commList);
	}
	/**
	 * 
	 *  Function: 查询已上线商品数量
	 *
	 *  @author zhangqiang  DateTime 2014-12-24 上午10:53:47
	 *  @return
	 */
	@RequestMapping(params="queryUpLine")
	@ResponseBody
	public long queryOnLineCommodityNum() {
		return scoreMallInterface.queryOnLineCommodityNum();
	}
}
