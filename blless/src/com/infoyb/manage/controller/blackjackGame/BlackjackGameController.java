package com.cm.manage.controller.blackjackGame;

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

import com.cm.game.http.IBlackjackGameForBackHttpService;
import com.cm.game.http.bean.GameChipsForBack;
import com.cm.game.http.bean.GameInfoForBack;
import com.cm.game.http.bean.GameInfoListForBack;
import com.cm.game.http.bean.GameLogsStatisticsForBack;
import com.cm.game.http.bean.GameRoomAndChipsForBack;
import com.cm.game.http.bean.GameRoomForBack;
import com.cm.game.http.bean.GameRoomListForBack;
import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
/**
 * 
 *  Class Name: 21点游戏控制器
 *  Function:
 *  
 *  Modifications:   
 *  
 *  @author zhangqiang  DateTime 2015-1-9 下午2:19:55    
 *  @version 1.0
 */
@Controller
@RequestMapping(value="/blackjackController")
public class BlackjackGameController extends BaseController {
	private static Logger logger = Logger.getLogger(BlackjackGameController.class);
	@Resource(name="blackjackGameForBackInterface")
	private IBlackjackGameForBackHttpService blackjackGameForBackHttpService;
	@Autowired
	private IMemberService memberService;
	/*
	 * 跳转至21点游戏初始化页面
	 */
	@RequestMapping(params="toBlackjackGame")
	public String toBalckjackGame() {
		return "/blackjackGame/blackjackGame";
	}
	/*
	 * 跳转至新增或者编辑21点游戏初始化页面
	 */
	@RequestMapping(params="toEditGameRoom")
	public String toEditGameRoom() {
		return "/blackjackGame/editGameRoom";
	}
	/*
	 * 跳转至游戏日志页面
	 */
	@RequestMapping(params="toGameLog")
	public String toGameLog() {
		return "/blackjackGame/gameLog";
	}
	/*
	 * 游戏房间查询，游戏初始化界面
	 */
	@RequestMapping(params="queryGameRoomList")
	@ResponseBody
	public EasyuiDataGridJson queryGameRoomList(HttpServletRequest request,EasyuiDataGrid dg,GameRoomForBack gameRoomForBack) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		GameRoomListForBack gameRoomListForBack = blackjackGameForBackHttpService.queryGameRoomListForBack(dg.getPage(), dg.getRows(), gameRoomForBack);
		edgj.setRows(gameRoomListForBack.getGameRoomForBackList());
		edgj.setTotal(gameRoomListForBack.getItemTotal());
		return edgj;
	}
	/*
	 * 游戏房间创建
	 */
	@RequestMapping(params="saveGameRoomAndChips")
	@ResponseBody
	public boolean saveGameRoomAndChips(HttpServletRequest request,GameRoomForBack gameRoomForBack) {
		//筹码设置字符串
		String chipsStr = gameRoomForBack.getChipsStr();
		String[] list = chipsStr.split(",");
		String[] leveList = gameRoomForBack.getChipsLeveStr().split(",");
		GameRoomAndChipsForBack gameRoomAndChipsForBack = new GameRoomAndChipsForBack();
		gameRoomAndChipsForBack.setGameRoomForBack(gameRoomForBack);
		List<GameChipsForBack> gameChipsForBackList = new ArrayList<GameChipsForBack>();
		for(int i=0;i<list.length;i++) {
			GameChipsForBack gameChipsBack = new GameChipsForBack();
			gameChipsBack.setChipsCredits(Integer.parseInt(list[i]));
			gameChipsBack.setLeve(Integer.parseInt(leveList[i]));
			gameChipsForBackList.add(gameChipsBack);
		}
		gameRoomAndChipsForBack.setGameChipsForBackList(gameChipsForBackList);
		if(null == gameRoomForBack.getRoomCode()&&"".equals(gameRoomForBack.getRoomCode())) {
			saveLog(request, "/blackjackController?saveGameRoomAndChips", CommonConstants.LOG_FOR_GAMEROOM, "新增游戏房间("+gameRoomForBack.getRoomName()+")");
		}
		return blackjackGameForBackHttpService.saveGameRoomAndChips(gameRoomAndChipsForBack);
	}
	/*
	 * 编辑游戏房间和筹码
	 */
	@RequestMapping(params="updateGameRoomAndChips")
	@ResponseBody
	public boolean updateGameRoomAndChips(HttpServletRequest request,GameRoomForBack gameRoomForBack) {
		GameRoomAndChipsForBack grcf = new GameRoomAndChipsForBack();
		grcf.setGameRoomForBack(gameRoomForBack);
		//筹码设置字符串
		String chipsStr = gameRoomForBack.getChipsStr();
		String[] list = chipsStr.split(",");
		String[] leveList = gameRoomForBack.getChipsLeveStr().split(",");
		List<GameChipsForBack> gameChipsForBackList = new ArrayList<GameChipsForBack>();
		for(int i=0;i<list.length;i++) {
			GameChipsForBack gameChipsBack = new GameChipsForBack();
			gameChipsBack.setChipsCredits(Integer.parseInt(list[i]));
			gameChipsBack.setLeve(Integer.parseInt(leveList[i]));
			gameChipsForBackList.add(gameChipsBack);
		}
		grcf.setGameChipsForBackList(gameChipsForBackList);
		saveLog(request, "/blackjackController?updateGameRoomAndChips", CommonConstants.LOG_FOR_GAMEROOM, "编辑游戏房间("+gameRoomForBack.getRoomName()+")");
		return blackjackGameForBackHttpService.updateGameRoomAndChips(grcf);
	}
	/*
	 * 由游戏房间code查询游戏房间
	 */
	@RequestMapping(params="queryGameRoomAndChipsForBack")
	public String queryGameRoomAndChipsForBack(HttpServletRequest request) {
		String roomCode = request.getParameter("roomCode");
		logger.info("=================roomCode="+roomCode);
		//查询获取房间和筹码设置信息
		 GameRoomAndChipsForBack grcb = blackjackGameForBackHttpService.queryGameRoomAndChipsByRoomCode(roomCode);
		 //根据账户编号关联账户名称
		 List<Map> list = memberService.getMemberNameByCode(grcb.getGameRoomForBack().getAccountNo());
		 Map m = null;
		 if(0!=list.size()){
			m =list.get(0);
			grcb.getGameRoomForBack().setAccountName((String)m.get("USER_NAME"));
		 }else {
			grcb.getGameRoomForBack().setAccountName("");
		 }
		request.setAttribute("grcb", grcb.getGameRoomForBack());
		return "/blackjackGame/editGameRoom";
	}
	/*
	 * 由游戏房间code查询游戏房间
	 */
	@RequestMapping(params="toScanBalckjackGame")
	public String toScanBalckjackGame(HttpServletRequest request) {
		String roomCode = request.getParameter("roomCode");
		logger.info("=================roomCode="+roomCode);
		//查询获取房间和筹码设置信息
		 GameRoomAndChipsForBack grcb = blackjackGameForBackHttpService.queryGameRoomAndChipsByRoomCode(roomCode);
		 //根据账户编号关联账户名称
		 List<Map> list = memberService.getMemberNameByCode(grcb.getGameRoomForBack().getAccountNo());
		 Map m = null;
		 if(0!=list.size()){
			m =list.get(0);
			grcb.getGameRoomForBack().setAccountName((String)m.get("USER_NAME"));
		 }else {
			grcb.getGameRoomForBack().setAccountName("");
		 }
		request.setAttribute("grcb", grcb.getGameRoomForBack());
		return "/blackjackGame/scanBlackjackGame";
	}
	/*
	 * 根据房间编号查询筹码设置
	 */
	@RequestMapping(params="queryBargainingChipByRoomCode")
	@ResponseBody
	public List<GameChipsForBack> queryBargainingChipByRoomCode(HttpServletRequest request) {
		String roomCode = request.getParameter("roomCode");
		logger.info("=================roomCode="+roomCode);
		List<GameChipsForBack> list = new ArrayList<GameChipsForBack>();
		//查询获取房间和筹码设置信息
		 GameRoomAndChipsForBack grcb = blackjackGameForBackHttpService.queryGameRoomAndChipsByRoomCode(roomCode);
		 if(null != grcb) {
			 list = grcb.getGameChipsForBackList();
		 }
		 return list;
	}
	/*
	 * 游戏房间开始结束隐藏取消隐藏操作
	 */
	@RequestMapping(params="editGameRoomState")
	@ResponseBody
	public String editGameRoomState(HttpServletRequest request) {
		logger.info("=====================编辑状态：roomCode="+request.getParameter("roomCode"));
		logger.info("=====================编辑状态：state="+request.getParameter("state"));
		String msg = blackjackGameForBackHttpService.editGameRoomState(request.getParameter("roomCode"), Integer.parseInt(request.getParameter("state")));
		saveLog(request, "/blackjackController?editGameRoomState", CommonConstants.LOG_FOR_GAMEROOM, "编辑游戏房间状态");
		return msg; 
	}
	/*
	 * 批量修改权重
	 */
	@RequestMapping(params="updateGameRoomWeight")
	@ResponseBody
	public String updateGameRoomWeight(HttpServletRequest request) {
		String msg = "";
		String[] roomCode = request.getParameter("roomCodeStr").split(",");
		String[] weight = request.getParameter("weightStr").split(",");
		List<String> roomCodeList = new ArrayList<String>();
		List<Integer> weightList = new ArrayList<Integer>();
		for(int i=0;i<roomCode.length;i++) {
			roomCodeList.add(roomCode[i]);
			weightList.add(Integer.parseInt(weight[i]));
		}
		logger.info("批量编辑权重输入项，房间编号："+roomCodeList);
		logger.info("批量编辑权重输入项，新的权重值："+weightList);
		boolean flag = blackjackGameForBackHttpService.updateGameRoomWeight(roomCodeList, weightList);
		if(flag) {
			msg = "批量编辑权重成功！";
			saveLog(request, "/blackjackController?updateGameRoomWeight", CommonConstants.LOG_FOR_GAMEROOM, "编辑游戏房间权重");
		}else {
			msg = "批量编辑权重失败！";
		}
		return msg;
	}
	/*
	 * 游戏房间同名校验
	 */
	@RequestMapping(params="sameNameVali")
	@ResponseBody
	public boolean sameNameVali(HttpServletRequest request) {
		String roomName =request.getParameter("roomName");
		List<String> list = blackjackGameForBackHttpService.queryAllGameRoom();
		boolean flag = false;
		for(int i=0;i<list.size();i++) {
			if(roomName.equals(list.get(i))) {
				flag = true;
			}
		}
		return flag;
	}
	/*
	 *删除游戏房间 
	 */
	@RequestMapping(params="deleteGameRoom")
	@ResponseBody
	public String deleteGameRoom(HttpServletRequest request) {
		String roomCode =request.getParameter("roomCode");
		return blackjackGameForBackHttpService.deleteGameRoom(roomCode);
	}
	/*
	 * 获取游戏日志
	 */
	@RequestMapping(params="queryGameInfoList")
	@ResponseBody
	public EasyuiDataGridJson queryGameInfoList(HttpServletRequest request,EasyuiDataGrid dg,GameInfoForBack gameInfoForBack) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		String isVague = gameInfoForBack.getIsVague();//是否模糊查询  0为否1为是
		List<Map> list = memberService.getMemberNameByCode("");
		if(!"".equals(gameInfoForBack.getUserName())&&null!=gameInfoForBack.getUserName()) {
			List<Map> map = new ArrayList<Map>();
			if("1".equals(isVague)||"1" == isVague) {
				 map = memberService.getMemberCodeByName(gameInfoForBack.getUserName(),0);
			}else if("0".equals(isVague)||"0" == isVague) {
				map = memberService.getMemberCodeByName(gameInfoForBack.getUserName(),1);
			}
			List<String> userCodeList = new ArrayList<String>();
			if(0==map.size()) {
				userCodeList = null;
			}else {
				for(int j=0;j<map.size();j++) {
					userCodeList.add((String) map.get(j).get("USER_CODE"));
				}
			}
			gameInfoForBack.setUserCodeList(userCodeList);
		}
		GameInfoListForBack gameInfoListForBack = blackjackGameForBackHttpService.queryGameInfoListForBack(dg.getPage(), dg.getRows(), gameInfoForBack);
		if(null != gameInfoListForBack&&null!=gameInfoListForBack.getGameInfoListForBack()&&0!=gameInfoListForBack.getGameInfoListForBack().size()) {
			for (int i=0;i<gameInfoListForBack.getGameInfoListForBack().size();i++) {
				for(int j=0;j<list.size();j++) {
					if((list.get(j).get("USER_CODE")).equals(gameInfoListForBack.getGameInfoListForBack().get(i).getUserCode())) {
						gameInfoListForBack.getGameInfoListForBack().get(i).setUserName(list.get(j).get("USER_NAME").toString());
					}
				}
			}
			edgj.setRows(gameInfoListForBack.getGameInfoListForBack());
			edgj.setTotal(gameInfoListForBack.getItemTotal());
			logger.info("=============日志查询统计结果==========共计条数："+gameInfoListForBack.getTotalNums());
			logger.info("=============日志查询统计结果==========累计下注："+gameInfoListForBack.getBetTotals());
			logger.info("=============日志查询统计结果==========累计赢金："+gameInfoListForBack.getWinGoalTotal());
			request.setAttribute("totalNums", gameInfoListForBack.getTotalNums());
			request.setAttribute("betTotals", gameInfoListForBack.getBetTotals());
			request.setAttribute("winGoalTotal", gameInfoListForBack.getWinGoalTotal());
		}else {
			edgj.setRows(new ArrayList<GameInfoForBack>());
			edgj.setTotal(Long.parseLong("0"));
		}
		return edgj;
	}
	/*
	 * 游戏日志统计
	 */
	@RequestMapping(params="queryGameInfoTotal")
	@ResponseBody
	public GameLogsStatisticsForBack queryGameInfoTotal(HttpServletRequest request,GameInfoForBack gameInfoForBack) {
		String isVague = gameInfoForBack.getIsVague();//是否模糊查询  0为否1为是
		List<Map> list = memberService.getMemberNameByCode("");
		if(!"".equals(gameInfoForBack.getUserName())||null!=gameInfoForBack.getUserName()) {
			List<Map> map = new ArrayList<Map>();
			if("1".equals(isVague)||"1" == isVague) {
				 map = memberService.getMemberCodeByName(gameInfoForBack.getUserName(),0);
			}else if("0".equals(isVague)||"0" == isVague) {
				map = memberService.getMemberCodeByName(gameInfoForBack.getUserName(),1);
			}
			List<String> userCodeList = new ArrayList<String>();
			if(0==map.size()) {
				userCodeList = null;
			}else {
				for(int j=0;j<map.size();j++) {
					userCodeList.add((String) map.get(j).get("USER_CODE"));
				}
			}
			gameInfoForBack.setUserCodeList(userCodeList);
		}
		GameLogsStatisticsForBack gb = blackjackGameForBackHttpService.queryGameInfoTotal(gameInfoForBack);
		return gb;
	}
	/*
	 * 跳转至游戏详情页面
	 */
	@RequestMapping(params="toGameLogDetail")
	public String toGameLogDetail(HttpServletRequest request) {
		String gameCode = request.getParameter("gameCode");
		logger.info("=================gameCode"+gameCode);
		//查询游戏日志详情
		List<GameInfoForBack> gb = blackjackGameForBackHttpService.queryGameInfoByGameCode(gameCode);
		if(null != gb&&0!=gb.size()) {
			if(null != gb.get(0).getAccountName()) {
				//根据账户编号关联账户名称
				List<Map> list = memberService.getMemberNameByCode(gb.get(0).getAccountName());
				gb.get(0).setAccountName(list.get(0).get("USER_NAME").toString());
				logger.info("gb:" + gb);
				request.setAttribute("log", gb.get(0));
				logger.info("out of toGameLogDetail");
			}
		}
		return "/blackjackGame/scanGameLogDetail";
	}
	/*
	 * 查询筹码情况
	 */
	@RequestMapping(params="queryGameChipsForLog")
	@ResponseBody
	public EasyuiDataGridJson queryGameChipsForLog(HttpServletRequest request,GameInfoForBack gameInfoForBack) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		String gameCode = request.getParameter("gameCode");
		logger.info("=================筹码详情 in:gameCode="+gameCode);
		//查询游戏日志详情
		List<GameInfoForBack> list = blackjackGameForBackHttpService.queryGameInfoByGameCode(gameCode);
		List<GameInfoForBack> listForBack = new ArrayList<GameInfoForBack>();
		listForBack.add(list.get(0));
		edgj.setRows(listForBack);
		edgj.setTotal(Long.parseLong("1"));
		return edgj;
	}
	/*
	 * 查询牌局情况
	 */
	@RequestMapping(params="queryGamblingForLog")
	@ResponseBody
	public EasyuiDataGridJson queryGamblingForLog(HttpServletRequest request,GameInfoForBack gameInfoForBack) {
		EasyuiDataGridJson edgj = new EasyuiDataGridJson();
		String gameCode = request.getParameter("gameCode");
		logger.info("=================牌局情况 out:gameCode="+gameCode);
		//查询游戏日志详情
		List<GameInfoForBack> list = blackjackGameForBackHttpService.queryGameInfoByGameCode(gameCode);
		edgj.setRows(list);
		logger.info("==================牌局详情数据条数："+list.size());
		edgj.setTotal(Long.parseLong(list.size()+""));
		return edgj;
	}
}
