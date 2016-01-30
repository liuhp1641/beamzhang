package com.cm.manage.service.order;


import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.base.IBaseService;

import java.util.List;

public interface ILotteryControlService extends IBaseService {

    public LotteryControl getLotteryControl(String lotteryCode);

    public List<LotteryControl> getLoteryControlList();

    public int updateStatus(String lotteryCode, Integer status, String memo);

    public int updateStatus(String lotteryCode, Integer status);

    public int updateSendStatus(String lotteryCode, Integer status);

    public int updateLotteryControl(String lotteryCode, String mark, String tips,Integer delayTime);

}
