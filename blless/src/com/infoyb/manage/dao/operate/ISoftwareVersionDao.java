package com.cm.manage.dao.operate;

import java.util.List;

import com.cm.manage.model.operate.SoftwareVersion;

public interface ISoftwareVersionDao {

	List<SoftwareVersion> selectSoftwareVersionByType(String svType);

	SoftwareVersion addSoftwareVersion(SoftwareVersion softwareVersion);

	void delSoftwareVersion(SoftwareVersion softwareVersion);

	void editSoftwareVersion(SoftwareVersion softwareVersion);

}
