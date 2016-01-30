package com.cm.manage.dao.operate.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.ISoftwareVersionDao;
import com.cm.manage.model.operate.SoftwareVersion;

@Repository
public class SoftwareVersionDaoImpl implements ISoftwareVersionDao{
	@Autowired
	private IBaseDao<SoftwareVersion> softwareVersionDao;
	@Override
	public List<SoftwareVersion> selectSoftwareVersionByType(String svType) {
		List<SoftwareVersion> softwareVersions = null;
		if (svType != null && !svType.equals("")) {
			softwareVersions = softwareVersionDao.find("from  SoftwareVersion where svType =?", svType);
		} else {
			softwareVersions = softwareVersionDao.find("from SoftwareVersion");
		}
		return softwareVersions;
	}

	@Override
	public SoftwareVersion addSoftwareVersion(SoftwareVersion softwareVersion) {
		SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
		String ymdhms = dff.format(new Date());
		int max = 2000;
		int min = 1000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		String svCode = ymdhms + s;
		softwareVersion.setSvCode(svCode);
		long id = (Long) softwareVersionDao.save(softwareVersion);
		softwareVersion.setId(id);
		return softwareVersion;
	}

	@Override
	public void delSoftwareVersion(SoftwareVersion softwareVersion) {
		softwareVersionDao.delete(softwareVersion);
		
	}

	@Override
	public void editSoftwareVersion(SoftwareVersion softwareVersion) {
		softwareVersionDao.update(softwareVersion);		
	}

}
