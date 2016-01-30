package com.cm.manage.dao.quartz.impl;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.quartz.IDistributionLockDao;
import com.cm.manage.model.quartz.DistributionLock;
import com.cm.manage.util.base.CommonUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class DistributionLockDaoImpl implements IDistributionLockDao {

    @Autowired
    private IBaseDao<DistributionLock> distributionLockDao;

    @Override
    public List<DistributionLock> getDistributionLockList(DistributionLock distributionLock) {
        StringBuffer sql = new StringBuffer("From DistributionLock where 1=1");
        List<Object> paras = new ArrayList<Object>();
        if (CommonUtil.isNotEmpty(distributionLock)) {
            if (CommonUtil.isNotEmpty(distributionLock.getName())) {
                sql.append(" and name=?");
                paras.add(distributionLock.getName());
            }
            if (CommonUtil.isNotEmpty(distributionLock.getStatus())) {
                sql.append(" and status=?");
                paras.add(distributionLock.getStatus());
            }
            if (CommonUtil.isNotEmpty(distributionLock.getUserName())) {
                sql.append(" and user=?");
                paras.add(distributionLock.getUserName());
            }
            if (CommonUtil.isNotEmpty(distributionLock.getCode())) {
                sql.append(" and code=?");
                paras.add(distributionLock.getCode());
            }
            if (CommonUtil.isNotEmpty(distributionLock.getCreateTime())) {
                sql.append(" and code>");
                paras.add(distributionLock.getCreateTime());
            }
        }
        return distributionLockDao.find(sql.toString(), paras);
    }

    public int updateStatus(String name, Integer status) {
        String sql = "update sys_distribution_lock t set t.status=? where t.name = ? ";
        return distributionLockDao.executeSql(sql, new Object[]{status, name});
    }
}
