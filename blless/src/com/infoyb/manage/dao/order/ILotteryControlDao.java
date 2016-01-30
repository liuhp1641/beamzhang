package com.cm.manage.dao.order;

import com.cm.manage.model.order.LotteryControl;

import java.util.List;


public interface ILotteryControlDao {

    public LotteryControl getLotteryControl(String lotteryCode);

    public List<LotteryControl> getLoteryControlList();

    public List<LotteryControl> getLoteryControlListForJob();

    public int updateStatus(String lotteryCode, Integer status, String memo);

    public int updateStatus(String lotteryCode, Integer status);

    public int updateSendStatus(String lotteryCode, Integer status);

    public int updateLotteryControl(String lotteryCode, String mark, String tips,Integer delayTime);
}
