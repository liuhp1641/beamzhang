package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IResourcesDao;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.model.system.SyroleSyresources;
import com.cm.manage.util.system.ResourcesComparator;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Resources;


@Repository("resourcesDao")
public class ResourcesDaoImpl  implements IResourcesDao {

	@Autowired
	private IBaseDao<Syresources> resourcesDao;
	@Autowired
	private IBaseDao<SyroleSyresources> syroleSyresourcesDao;

	public List<EasyuiTreeNode> tree(String id) {
		String hql = "from Syresources t where t.syresources is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Syresources t where t.syresources.id ='" + id + "' order by t.seq";
		}
		List<Syresources> resourceses = resourcesDao.find(hql);
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		for (Syresources syresources : resourceses) {
			tree.add(tree(syresources, false));
		}
		return tree;
	}

	private EasyuiTreeNode tree(Syresources syresources, boolean recursive) {
		EasyuiTreeNode node = new EasyuiTreeNode();
		node.setId(syresources.getId());
		node.setText(syresources.getText());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("src", syresources.getSrc());
		node.setAttributes(attributes);
		if (syresources.getSyresourceses() != null && syresources.getSyresourceses().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Syresources> syresourcesList = new ArrayList<Syresources>(syresources.getSyresourceses());
				Collections.sort(syresourcesList, new ResourcesComparator());// 排序
				List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
				for (Syresources r : syresourcesList) {
					EasyuiTreeNode t = tree(r, true);
					children.add(t);
				}
				node.setChildren(children);
			}
		}
		return node;
	}
	public List<Resources> treegrid(String id) {
		List<Resources> treegrid = new ArrayList<Resources>();
		String hql = "from Syresources t where t.syresources is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Syresources t where t.syresources.id = '" + id + "' order by t.seq";
		}
		List<Syresources> syresourceses = resourcesDao.find(hql);
		for (Syresources syresources : syresourceses) {
			Resources r = new Resources();
			BeanUtils.copyProperties(syresources, r);
			if (syresources.getSyresources() != null) {
				r.setParentId(syresources.getSyresources().getId());
				r.setParentText(syresources.getSyresources().getText());
			}
			if (syresources.getSyresourceses() != null && syresources.getSyresourceses().size() > 0) {
				r.setState("closed");
			}
			treegrid.add(r);
		}
		return treegrid;
	}

	public Resources add(Resources resources) {
		Syresources syresources = new Syresources();
		BeanUtils.copyProperties(resources, syresources);
		if (resources.getParentId() != null && !resources.getParentId().toString().trim().equals("")) {
			syresources.setSyresources(resourcesDao.load(Syresources.class, resources.getParentId()));
		}
		resourcesDao.save(syresources);
		return resources;
	}

	public void del(Resources resources) {
		Syresources syresources = resourcesDao.get(Syresources.class, resources.getId());
		if (resources != null) {
			recursiveDelete(syresources);
		}
	}

	private void recursiveDelete(Syresources syresources) {
		if (syresources.getSyresourceses() != null && syresources.getSyresourceses().size() > 0) {
			Set<Syresources> syresourceses = syresources.getSyresourceses();
			for (Syresources t : syresourceses) {
				recursiveDelete(t);
			}
		}

		List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syresources=?", syresources);
		if (syroleSyresourcesList != null) {// 删除资源前,需要现将角色资源关系表中的相关记录删除
			for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
				syroleSyresourcesDao.delete(syroleSyresources);
			}
		}

		resourcesDao.delete(syresources);
	}

	public Resources edit(Resources resources) {
		Syresources syresources = resourcesDao.get(Syresources.class, resources.getId());
		if (syresources != null) {
			BeanUtils.copyProperties(resources, syresources);
			if (!syresources.getId().equals("0")) {// 跟节点不可以修改上级节点
				syresources.setSyresources(resourcesDao.get(Syresources.class, resources.getParentId()));
			}
		}
		return resources;
	}

}
