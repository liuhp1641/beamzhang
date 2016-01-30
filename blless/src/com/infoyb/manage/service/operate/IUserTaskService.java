package com.cm.manage.service.operate;

import java.util.Map;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskVO;

public interface IUserTaskService extends IBaseService {
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg,UserTaskVO userTaskVO);
	public Map countByFunddingType(UserTaskVO userTaskVO);

}
