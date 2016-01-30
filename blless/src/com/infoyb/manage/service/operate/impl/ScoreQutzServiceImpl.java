package com.cm.manage.service.operate.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IScoreQutzDao;
import com.cm.manage.model.score.ScoreQutzOption;
import com.cm.manage.model.score.ScoreQutzType;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IScoreQutzService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.score.ScoreQutzUserVO;
import com.cm.manage.vo.score.ScoreQutzVO;

@Service("scoreQutzService")
public class ScoreQutzServiceImpl extends BaseServiceImpl implements
		IScoreQutzService {
	
	@Autowired
	private IScoreQutzDao  scoreQutzDao;
	
	/**
	 * 奖池列表
	 * @return
	 */
	public List<Map> scoreQutzType(){
		return scoreQutzDao.scoreQutzType();
	}
	
	/**
	 * 最大奖池编号
	 * @return
	 */
	public Integer maxQutzTypeId(){
		return scoreQutzDao.maxQutzTypeId();
	}
	
	/**
	 * 奖池保存
	 * @param vo
	 * @return
	 */
	public void saveQutzType(ScoreQutzType vo){
		scoreQutzDao.saveQutzType(vo);
	}

	/**
	 * 奖池删除
	 * @param qutzTypeId
	 * @return
	 */
	public boolean delQutzType(String qutzTypeId){
		return  scoreQutzDao.delQutzType(qutzTypeId);
	}
	
	/**
	 * 
	 * 根据奖池Id获取奖池信息
	 * @param qutzTypeId
	 * @return
	 */
	public Map qutzTypeInfo(String qutzTypeId){
		return scoreQutzDao.qutzTypeInfo(qutzTypeId);
	}
	/**
	 * 积分竞猜查询列表
	 * @param dg
	 * @param score
	 * @return
	 */
	@Override
	public EasyuiDataGridJson scoreQutzList(EasyuiDataGrid dg, ScoreQutzVO score) {
		
		return scoreQutzDao.scoreQutzList(dg, score);
	}

	/**
	  * 根据竞猜类型上期活动信息
	  * @param qutzTypeId
	  * @return
	  */
	 public Map lastScoreQutz(String qutzTypeId,Integer bonusType){
		 return scoreQutzDao.lastScoreQutz(qutzTypeId, bonusType);
	 }
	 /**
	  * 竞猜答案
	  * @param qutzId
	  * @return
	  */
	 public List<ScoreQutzOption> scoreQutzOption(String qutzId){
		 return  scoreQutzDao.scoreQutzOption(qutzId);
	 }
	 /**
	  * 竞猜活动保存
	  * @param scoreQutz
	  * @param options
	  */
	 public void saveQutzActivity(ScoreQutzVO scoreQutz,String options)throws BusiException{
		 String qutzId = scoreQutz.getQutzId();
	     if (!CommonUtil.isNotEmpty(qutzId)) {
	    	 ScoreQutzVO vo=qutzActivityInfo(null,scoreQutz.getQutzIssue());
	    	 if(vo!=null){
	    		 throw new BusiException("1001", "该期次已被创建");
	    	 }
	     }
		 scoreQutzDao.saveQutzActivity(scoreQutz, options);
	 }
	 
	 /**
	  * 竞猜活动状态修改
	  * @param qutzId
	  * @param status
	  */
	 public boolean updateQutzActivityStatus(String qutzId,Integer status,Integer bonusStatus){
		 return scoreQutzDao.updateQutzActivityStatus(qutzId, status,bonusStatus);
	 }
	 
	 /**
	  * 竞猜活动详情
	  * @param qutzId
	  * @return
	  */
	 public ScoreQutzVO qutzActivityInfo(String qutzId,String qutzIssue){
		 return scoreQutzDao.qutzActivityInfo(qutzId,qutzIssue);
	 }
	 /**
	  * 竞猜答案保存
	  * @param scoreQutz
	  * @return
	  */
	 public boolean saveQutzAnswer(ScoreQutzVO scoreQutz){
		 return scoreQutzDao.saveQutzAnswer(scoreQutz);
	 }
	 /**
	  * 竞猜活动删除
	  * @param qutzId
	  * @return
	  */
	 public boolean delQutzActivity(String qutzId){
		 return scoreQutzDao.delQutzActivity(qutzId);
	 }
	 /**
	  * 竞猜活动隐藏
	  * @param qutzId
	  * @return
	  */
	 public boolean qutzActivityHide(String qutzId,Integer status){
		 return scoreQutzDao.qutzActivityHide(qutzId, status);
	 }
	 
	 /**
	  * 竞猜活动分享设置
	  * @param qutzId
	  * @param status
	  * @return
	  */
	 public boolean qutzActivityShare(String qutzId,Integer status){
		 return scoreQutzDao.qutzActivityShare(qutzId, status);
	 }
	 /**
	  * 竞猜活动推荐
	  * @param qutzId
	  * @return
	  */
	 public boolean activityRecommend(String qutzId,Integer isRecommend){
		 return scoreQutzDao.activityRecommend(qutzId,isRecommend);
	 }
	 /**
	  * 竞猜答案删除
	  * @param optionId
	  * @return
	  */
	 public boolean delScoreQutzAnswer(String optionId){
		 return scoreQutzDao.delScoreQutzAnswer(optionId);
	 }
	 
	 /**
	  * 积分竞猜活动日志
	  * @param dg
	  * @param vo
	  * @return
	  */
	 public EasyuiDataGridJson scoreQutzLog(EasyuiDataGrid dg, ScoreQutzUserVO vo) {
		 return scoreQutzDao.scoreQutzLog(dg, vo);
	 }
	 
	 /**
	  * 积分竞猜时间更新
	  * @param qutzId
	  * @param endTime
	  * @return
	  */
	 public void updateActivityTime(String qutzId,String endTime){
		 scoreQutzDao.updateActivityTime(qutzId, endTime);
	 }
	 /**
	  * 积分竞猜中奖用户
	  * @param qutzId
	  * @return
	  */
	 public EasyuiDataGridJson qutzBonusUser(EasyuiDataGrid dg,String qutzId){
		 return scoreQutzDao.qutzBonusUser(dg,qutzId);
	 }
}
