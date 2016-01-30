package com.cm.game.http;

import java.util.List;

import com.cm.game.http.bean.GameInfoForBack;
import com.cm.game.http.bean.GameInfoListForBack;
import com.cm.game.http.bean.GameLogsStatisticsForBack;
import com.cm.game.http.bean.GameRoomAndChipsForBack;
import com.cm.game.http.bean.GameRoomForBack;
import com.cm.game.http.bean.GameRoomListForBack;

/**
 * 
 *  Class Name: IBlackjackGameForBackHttpService.java
 *  Function: 21点游戏房间service   
 *  
 *  @author zhangqiang  DateTime 2015-1-11 上午10:53:31    
 *  @version 1.0
 */
public interface IBlackjackGameForBackHttpService {
	/**
	 * 保存房间信息和筹码设置信息
	 */
	public boolean saveGameRoomAndChips(GameRoomAndChipsForBack gameRoomAndChipsForBack);
	/**
	 * 编辑房间信息和筹码设置
	 */
	public boolean updateGameRoomAndChips(GameRoomAndChipsForBack gameRoomAndChipsForBack);
	/**
	 * 查询房间信息和筹码设置
	 */
	public GameRoomListForBack queryGameRoomListForBack(int pageId,int pageSize,GameRoomForBack gameRoomForBack);
	/**
	 * 修改游戏房间权重
	 */
	public boolean updateGameRoomWeight(List<String> roomCodeList,List<Integer> roomWeightList);
	/**
	 * 根据roomCode查询游戏房间和筹码设置
	 */
	public GameRoomAndChipsForBack queryGameRoomAndChipsByRoomCode(String code);
	/**
	 * 删除游戏房间
	 */
	public int deleteGameRoomAndChips(List<String> roomCodeList);
	/**
	 * 游戏房间开始结束和隐藏取消隐藏操作
	 */
	public String editGameRoomState(String roomCode,int state);
	/**
	 * 查询所有的房间列表
	 */
	public List<String> queryAllGameRoom();
	/**
	 * 删除游戏房间
	 */
	public String deleteGameRoom(String roomCode);
	/**
	 * 查询所有的游戏牌局
	 */
	public GameInfoListForBack queryGameInfoListForBack(int pageId,int pageSize,GameInfoForBack gameInfoForBack);
	/**
	 * 根据游戏code获取游戏日志详情
	 */
	public List<GameInfoForBack> queryGameInfoByGameCode(String gameCode);
	/**
	 * 获取游戏日志统计
	 */
	public GameLogsStatisticsForBack queryGameInfoTotal(GameInfoForBack gameInfoForBack);
}
