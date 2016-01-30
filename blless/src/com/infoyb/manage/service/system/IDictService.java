package com.cm.manage.service.system;

import java.util.List;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.DictDetail;
import com.cm.manage.vo.system.DictType;

public interface IDictService extends IBaseService{
	/**
	 * 字典类型
	 * @return
	 */
	public List<EasyuiTreeNode> getDictType(String model);
	
	/**
	 * 
	 * @param dg
	 * @param type_id
	 * @return
	 */
	public EasyuiDataGridJson getDictDetail(EasyuiDataGrid dg,String model, Long typeId);
	
	/**
	 * 类型添加
	 * @param dict
	 * @return
	 */
	public DictType typeAdd(DictType dict);
	
	/**
	 * 字典类型删除
	 * @param id
	 */
	public void typeDel(String model,Long id);

	/**
	 * 字典类型信息
	 * @param model
	 * @param id
	 * @return
	 */
	public DictType typeInfo(String model,Long id);
	
	
	
	/**
	 * 字典详细添加
	 * @param dict
	 * @return
	 */
	
	public DictDetail detailAdd(DictDetail dict);
	/**
	 * 字典详细删除
	 * @param ids
	 */
	public void detailDel(String model,String ids);

    /**
     * @param typeId
     */
    public void initMemcached(Long typeId, String model);

}
