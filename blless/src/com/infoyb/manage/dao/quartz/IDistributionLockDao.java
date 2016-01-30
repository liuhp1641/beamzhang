package com.cm.manage.dao.quartz;


import com.cm.manage.model.quartz.DistributionLock;

import java.util.List;


public interface IDistributionLockDao {

    public List<DistributionLock> getDistributionLockList(DistributionLock distributionLock);

    public int updateStatus(String name,Integer status);
}
