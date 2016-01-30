package com.cm.manage.service.operate.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IIssueForjczqDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IIssueForjczqService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.IssueForjczqVO;

@Service("issueForjczqService")
public class IssueForjczqServiceImpl extends BaseServiceImpl implements
		IIssueForjczqService {

	@Autowired
	private IIssueForjczqDao issueDao;
	
	@Override
	public EasyuiDataGridJson issueForjczqList(EasyuiDataGrid dg,
			IssueForjczqVO issue) {
		return issueDao.issueForjczqList(dg, issue);
	}
	
	/**
	  * 竞彩足球详情
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public Map issueForjczqInfo(String issue,String sn){
		 return issueDao.issueForjczqInfo(issue,sn);
	 }

	 
	 /**
	  * 设置开赛时间
	  * @param issue
	  * @param sn
	  * @param endTime
	  * @return
	  */
	 public boolean editEndTime(String issue,String sn,String endTime){
		 return issueDao.editEndTime(issue,sn, endTime);
	 }
	 
	 /**
	  * 设置自动更新
	  * @param issue
	  * @param sn
	  * @param backup1
	  * @return
	  */
	 public boolean editBackup1(String issue,String sn,String backup1){
		 return issueDao.editBackup1(issue,sn, backup1);
	 }
	 
	 /**
	  * 是否取消比赛
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean editCancel(String issue,String sn){
		 return issueDao.editCancel(issue,sn);
	 }
	 
	 /**
	  * 设置隐藏对阵
	  * @param issue
	  * @param sn
	  * @param backup2
	  * @return
	  */
	 public boolean editBackup2(String issue,String sn,String backup2){
		 return issueDao.editBackup2(issue,sn, backup2);
	 }
	 /**
	  * 对阵推荐
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean issueRecommend(String issue,String sn,Integer backup3){
		 return issueDao.issueRecommend(issue, sn,backup3);
	 }
	 
	 /**
	  * 设置对阵标签
	  * @param issue
	  * @param sn
	  * @param label
	  * @return
	  */
	 public boolean editLabel(String issue,String sn,String playCode,String pollCode, String label){
		 return issueDao.editLabel(issue,sn,playCode,pollCode,label);
	 }
}
