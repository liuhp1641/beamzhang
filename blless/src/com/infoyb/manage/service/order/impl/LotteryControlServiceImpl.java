package com.cm.manage.service.order.impl;

import com.cm.manage.dao.order.ILotteryControlDao;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.order.ILotteryControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LotteryControlServiceImpl extends BaseServiceImpl implements ILotteryControlService {

    @Autowired
    private ILotteryControlDao lotteryControlDao;

    @Override
    public LotteryControl getLotteryControl(String lotteryCode) {
        return lotteryControlDao.getLotteryControl(lotteryCode);
    }

    @Override
    public List<LotteryControl> getLoteryControlList() {
        return lotteryControlDao.getLoteryControlList();
    }

    @Override
    public int updateStatus(String lotteryCode, Integer status, String memo) {
        return lotteryControlDao.updateStatus(lotteryCode, status, memo);
    }

    @Override
    public int updateStatus(String lotteryCode, Integer status) {
        return lotteryControlDao.updateStatus(lotteryCode, status);
    }

    @Override
    public int updateSendStatus(String lotteryCode, Integer status) {
        return lotteryControlDao.updateSendStatus(lotteryCode, status);
    }

    @Override
    public int updateLotteryControl(String lotteryCode, String mark, String tips, Integer delayTime) {
        return lotteryControlDao.updateLotteryControl(lotteryCode, mark, tips, delayTime);
    }
}
