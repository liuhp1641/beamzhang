package com.cm.manage.service.quartz;


import com.cm.manage.model.quartz.DistributionLock;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.quartz.DistributionLockVO;

import java.util.List;


public interface IDistributionLockService extends IBaseService {

    public List<DistributionLockVO> getDistributionLockList();

    public int updateStatus(String name, Integer status);
}

