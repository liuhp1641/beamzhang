package com.cm.manage.dao.operate;

import java.util.Map;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.IssueForjczqVO;

public interface IIssueForjczqDao {

	/**
	 * 竞彩足球查询页面
	 * @param dg
	 * @param issue
	 * @return
	 */
	 public EasyuiDataGridJson issueForjczqList(EasyuiDataGrid dg, IssueForjczqVO issue);
	 
	 /**
	  * 竞彩足球详情
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public Map issueForjczqInfo(String issue,String sn);
	 
	 /**
	  * 设置开赛时间
	  * @param issue
	  * @param sn
	  * @param endTime
	  * @return
	  */
	 public boolean editEndTime(String issue,String sn,String endTime);
	 
	 /**
	  * 设置自动更新
	  * @param issue
	  * @param sn
	  * @param backup1
	  * @return
	  */
	 public boolean editBackup1(String issue,String sn,String backup1);
	 
	 /**
	  * 是否取消比赛
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean editCancel(String issue,String sn);
	 
	 /**
	  * 设置隐藏对阵
	  * @param issue
	  * @param sn
	  * @param backup2
	  * @return
	  */
	 public boolean editBackup2(String issue,String sn,String backup2);
	 /**
	  * 对阵推荐
	  * @param issue
	  * @param sn
	  * @return
	  */
	 public boolean issueRecommend(String issue,String sn,Integer backup3);
	 
	 /**
	  * 设置对阵标签
	  * @param issue
	  * @param sn
	  * @param label
	  * @return
	  */
	 public boolean editLabel(String issue,String sn,String playCode,String pollCode, String label);
}
