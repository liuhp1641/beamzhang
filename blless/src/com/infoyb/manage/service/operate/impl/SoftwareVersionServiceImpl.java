package com.cm.manage.service.operate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.ISoftwareVersionDao;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.ISoftwareVersionService;

@Service
public class SoftwareVersionServiceImpl extends BaseServiceImpl implements ISoftwareVersionService{
	@Autowired
	private ISoftwareVersionDao softwareVersionDao;
	@Override
	public List<SoftwareVersion> selectSoftwareVersionByType(String svType) {
		return softwareVersionDao.selectSoftwareVersionByType( svType);
	}

	@Override
	public SoftwareVersion addSoftwareVersion(SoftwareVersion softwareVersion) {
		return softwareVersionDao.addSoftwareVersion( softwareVersion);
	}

	@Override
	public void delSoftwareVersion(SoftwareVersion softwareVersion) {
		softwareVersionDao.delSoftwareVersion(softwareVersion);
		
	}

	@Override
	public void editSoftwareVersion(SoftwareVersion softwareVersion) {
		softwareVersionDao.editSoftwareVersion(softwareVersion);
		
	}

}
