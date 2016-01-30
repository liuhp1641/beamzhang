package com.cm.manage.dao.operate;

import java.util.Map;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskVO;

public interface IUserTaskDao {
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg,UserTaskVO userTaskVO);
	public Map countByFunddingType(UserTaskVO userTaskVO);
}
