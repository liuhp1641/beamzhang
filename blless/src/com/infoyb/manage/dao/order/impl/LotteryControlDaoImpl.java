package com.cm.manage.dao.order.impl;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.order.ILotteryControlDao;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.util.base.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LotteryControlDaoImpl implements ILotteryControlDao {

    @Autowired
    private IBaseDao<LotteryControl> lotteryControlDao;

    @Override
    public LotteryControl getLotteryControl(String lotteryCode) {
        String sql = "from LotteryControl where lotteryCode = ? ";
        List<LotteryControl> lotteryControlList = lotteryControlDao.find(sql, new Object[]{lotteryCode});
        if (CommonUtil.isNotEmpty(lotteryControlList)) {
            return lotteryControlList.get(0);
        }
        return null;
    }

    @Override
    public List<LotteryControl> getLoteryControlList() {
        String sql = "select new LotteryControl(id,lotteryCode,lotteryName,sort,status,sendStatus,memo,mark,tips,delayTime) from LotteryControl order by sort asc ";
        return lotteryControlDao.find(sql, new Object[]{});
    }

    @Override
    public List<LotteryControl> getLoteryControlListForJob() {
        String sql = "from LotteryControl where delayTime <> 0 ";
        return lotteryControlDao.find(sql, new Object[]{});
    }

    @Override
    public int updateStatus(String lotteryCode, Integer status, String memo) {
        String sql = "update tms_lottery_control set status = ?,memo = ? where lottery_code = ? ";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(status);
        paramList.add(memo);
        paramList.add(lotteryCode);
        return lotteryControlDao.executeSql(sql, paramList);
    }

    @Override
    public int updateStatus(String lotteryCode, Integer status) {
        String sql = "update tms_lottery_control set status = ? where lottery_code = ? ";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(status);
        paramList.add(lotteryCode);
        return lotteryControlDao.executeSql(sql, paramList);
    }

    @Override
    public int updateSendStatus(String lotteryCode, Integer status) {
        String sql = "update tms_lottery_control set send_status = ? where lottery_code = ? ";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(status);
        paramList.add(lotteryCode);
        return lotteryControlDao.executeSql(sql, paramList);
    }

    @Override
    public int updateLotteryControl(String lotteryCode, String mark, String tips, Integer delayTime) {
        String sql = "update tms_lottery_control set mark = ?,tips = ?,delay_time = ?  where lottery_code = ? ";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(mark);
        paramList.add(tips);
        paramList.add(delayTime);
        paramList.add(lotteryCode);
        return lotteryControlDao.executeSql(sql, paramList);
    }
}
