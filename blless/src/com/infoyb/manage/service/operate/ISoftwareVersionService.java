package com.cm.manage.service.operate;

import java.util.List;

import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.service.base.IBaseService;

public interface ISoftwareVersionService extends IBaseService{

	List<SoftwareVersion> selectSoftwareVersionByType(String svType);

	SoftwareVersion addSoftwareVersion(SoftwareVersion softwareVersion);

	void delSoftwareVersion(SoftwareVersion softwareVersion);

	void editSoftwareVersion(SoftwareVersion softwareVersion);

}
