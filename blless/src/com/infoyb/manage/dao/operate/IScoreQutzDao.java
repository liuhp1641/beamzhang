package com.cm.manage.dao.operate;

import java.util.List;
import java.util.Map;

import com.cm.manage.model.score.ScoreQutz;
import com.cm.manage.model.score.ScoreQutzOption;
import com.cm.manage.model.score.ScoreQutzType;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.score.ScoreQutzUserVO;
import com.cm.manage.vo.score.ScoreQutzVO;

public interface IScoreQutzDao {
	
	/**
	 * 奖池列表
	 * @return
	 */
	public List<Map> scoreQutzType();
	
	/**
	 * 最大奖池编号
	 * @return
	 */
	public Integer maxQutzTypeId();
	
	/**
	 * 奖池保存
	 * @param vo
	 * @return
	 */
	public void saveQutzType(ScoreQutzType vo);
	
	/**
	 * 奖池删除
	 * @param qutzTypeId
	 * @return
	 */
	public boolean delQutzType(String qutzTypeId);
	
	/**
	 * 
	 * 根据奖池Id获取奖池信息
	 * @param qutzTypeId
	 * @return
	 */
	public Map qutzTypeInfo(String qutzTypeId);
	
	/**
	 * 积分竞猜查询列表
	 * @param dg
	 * @param score
	 * @return
	 */
	 public EasyuiDataGridJson scoreQutzList(EasyuiDataGrid dg, ScoreQutzVO score) ;
	 
	 /**
	  * 根据竞猜类型上期活动信息
	  * @param qutzTypeId
	  * @return
	  */
	 public Map lastScoreQutz(String qutzTypeId,Integer bonusType);
	 
	 /**
	  * 竞猜答案
	  * @param qutzId
	  * @return
	  */
	 public List<ScoreQutzOption> scoreQutzOption(String qutzId);
	 
	 /**
	  * 竞猜活动保存
	  * @param scoreQutz
	  * @param options
	  */
	 public void saveQutzActivity(ScoreQutzVO scoreQutz,String options);
	 
	 /**
	  * 竞猜活动状态修改
	  * @param qutzId
	  * @param status
	  */
	 public boolean updateQutzActivityStatus(String qutzId,Integer status,Integer bonusStatus);
	 /**
	  * 竞猜活动详情
	  * @param qutzId
	  * @return
	  */
	 public ScoreQutzVO qutzActivityInfo(String qutzId,String qutzIssue);
	 
	 /**
	  * 竞猜答案保存
	  * @param scoreQutz
	  * @return
	  */
	 public boolean saveQutzAnswer(ScoreQutzVO scoreQutz);
	 /**
	  * 竞猜活动删除
	  * @param qutzId
	  * @return
	  */
	 public boolean delQutzActivity(String qutzId);
	 /**
	  * 竞猜活动隐藏
	  * @param qutzId
	  * @return
	  */
	 public boolean qutzActivityHide(String qutzId,Integer status);
	 /**
	  * 竞猜活动分享设置
	  * @param qutzId
	  * @param status
	  * @return
	  */
	 public boolean qutzActivityShare(String qutzId,Integer status);
	 
	 /**
	  * 竞猜活动推荐
	  * @param qutzId
	  * @return
	  */
	 public boolean activityRecommend(String qutzId,Integer isRecommend);
	 
	 /**
	  * 竞猜答案删除
	  * @param optionId
	  * @return
	  */
	 public boolean delScoreQutzAnswer(String optionId);
	 
	 /**
	  * 积分竞猜活动日志
	  * @param dg
	  * @param vo
	  * @return
	  */
	 public EasyuiDataGridJson scoreQutzLog(EasyuiDataGrid dg, ScoreQutzUserVO vo) ;
	 
	 /**
	  * 积分竞猜时间更新
	  * @param qutzId
	  * @param endTime
	  * @return
	  */
	 public void updateActivityTime(String qutzId,String endTime);
	 /**
	  * 积分竞猜中奖用户
	  * @param qutzId
	  * @return
	  */
	 public EasyuiDataGridJson qutzBonusUser(EasyuiDataGrid dg,String qutzId);

	 public List<ScoreQutz> selectAllScoreQutz();
	 
}
