package com.cm.manage.dao.system;

import java.util.List;

import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Resources;

public interface IResourcesDao {

	public List<EasyuiTreeNode> tree(String id);

	public List<Resources> treegrid(String id);

	public Resources add(Resources resources);

	public void del(Resources resources);

	public Resources edit(Resources resources);

}
