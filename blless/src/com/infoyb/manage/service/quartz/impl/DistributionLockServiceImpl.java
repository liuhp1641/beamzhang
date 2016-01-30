package com.cm.manage.service.quartz.impl;

import com.cm.manage.dao.quartz.IDistributionLockDao;
import com.cm.manage.model.quartz.DistributionLock;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.quartz.IDistributionLockService;
import com.cm.manage.vo.quartz.DistributionLockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DistributionLockServiceImpl extends BaseServiceImpl implements IDistributionLockService {
    @Autowired
    private IDistributionLockDao distributionLockDao;

    @Override
    public List<DistributionLockVO> getDistributionLockList() {
        List<DistributionLock> distributionLockList = distributionLockDao.getDistributionLockList(null);
        List<DistributionLockVO> distributionLockVOList = new ArrayList<DistributionLockVO>();
        for(DistributionLock distributionLock : distributionLockList){
            distributionLockVOList.add(new DistributionLockVO(distributionLock));
        }
        return distributionLockVOList;
    }

    public int updateStatus(String name, Integer status) {
        return distributionLockDao.updateStatus(name, status);
    }
}
