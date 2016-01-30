package com.cm.manage.dao.operate;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;

public interface IMatchForMappingDao {

	/**
	 * 赛事列表
	 * @return
	 */
	public EasyuiDataGridJson matchForMappingList(EasyuiDataGrid dg);
	
	/**
	 * 更改赛事
	 * @param matchName
	 * @param matchShortName
	 */
	public void updateMatchShortName(String matchName, String matchShortName);
}
