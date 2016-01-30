package com.cm.manage.service.system;

import java.util.List;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Resources;


/**
 * 资源Service
 * 
 * @author 
 * 
 */
public interface IResourcesService extends IBaseService {

	public List<EasyuiTreeNode> tree(String id);

	public List<Resources> treegrid(String id);

	public Resources add(Resources resources);

	public void del(Resources resources);

	public Resources edit(Resources resources);

}
