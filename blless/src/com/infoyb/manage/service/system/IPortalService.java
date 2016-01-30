package com.cm.manage.service.system;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.Portal;

/**
 * portal Service
 * 
 * @author 
 * 
 */
public interface IPortalService extends IBaseService {

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal);

	public void del(String ids);

	public void edit(Portal portal);

	public void add(Portal portal);

}
