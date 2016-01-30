package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IPortalDao;
import com.cm.manage.model.system.Syportal;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.Portal;


@Repository("portalDao")
public class PortalDaoImpl  implements IPortalDao {

	@Autowired
	private IBaseDao<Syportal> portalDao;
	
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		String hql = " from Syportal t where 1=1 ";
		String totalHql = " select count(*) " + hql;
		j.setTotal(portalDao.count(totalHql));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<Syportal> syportals = portalDao.find(hql);// 查询
		List<Portal> portals = new ArrayList<Portal>();
		if (syportals != null && syportals.size() > 0) {
			for (Syportal syportal : syportals) {
				Portal p = new Portal();
				BeanUtils.copyProperties(syportal, p);
				portals.add(p);
			}
		}
		j.setRows(portals);// 设置返回的行
		return j;
	}

	public void del(String ids) {
		for (String id : ids.split(",")) {
			Syportal syportal = portalDao.get(Syportal.class, id);
			if (syportal != null) {
				portalDao.delete(syportal);
			}
		}
	}

	public void edit(Portal portal) {
		Syportal syportal = portalDao.get(Syportal.class, portal.getId());
		if (syportal != null) {
			BeanUtils.copyProperties(portal, syportal);
		}
	}

	public void add(Portal portal) {
		Syportal syportal = new Syportal();
		BeanUtils.copyProperties(portal, syportal);
		portalDao.save(syportal);
	}

}
