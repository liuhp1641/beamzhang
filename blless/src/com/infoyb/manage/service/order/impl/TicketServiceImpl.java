package com.cm.manage.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cm.manage.util.base.CommonUtil;
import com.cm.order.http.IOrderManageHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.order.ITicketDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.order.ITicketService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.TicketCenterVO;
import com.cm.manage.vo.order.TicketVO;
import com.cm.order.http.bean.TicketCenterBean;

import javax.annotation.Resource;

@Service("ticketService")
public class TicketServiceImpl extends BaseServiceImpl implements
        ITicketService {

    @Autowired
    private ITicketDao ticketDao;
    @Resource(name = "orderInterface")
    private IOrderManageHttpService orderInterface;

    @Override
    public EasyuiDataGridJson ticketList(EasyuiDataGrid dg, TicketVO ticket) {

        return ticketDao.ticketList(dg, ticket);
    }

    /**
     * 传票查询
     *
     * @param dg
     * @param ticket
     * @return
     */
    public EasyuiDataGridJson unticketList(EasyuiDataGrid dg, TicketVO ticket) {
        return ticketDao.unticketList(dg, ticket);
    }

    /**
     * 出票账户
     *
     * @param ticketBean
     * @return
     */
    public List<TicketCenterVO> ticketCenterList(List<TicketCenterBean> ticketBean) {
        return ticketDao.ticketCenterList(ticketBean);
    }

    /**
     * 余额警告阀值设置
     *
     * @param vo
     * @return
     */
    public void ticketCenterWar(TicketCenterVO vo) {
        ticketDao.ticketCenterWar(vo);
    }

    /**
     * 票号详情
     *
     * @param ticketId
     * @return
     */
    public Map ticketInfo(String ticketId) {
        return ticketDao.ticketInfo(ticketId);
    }

    @Override
    public Map<String, Object> postAccountWarning() {
        List<TicketCenterBean> ticketCenterBeanList = orderInterface.ticketCenterBalance();
        List<Map> warningList = ticketDao.getTicketCenterWarning();
        Map<String, Double> postMap = new HashMap<String, Double>();
        for (Map map : warningList) {
            postMap.put(map.get("POST_CODE") + "", CommonUtil.formatDouble(map.get("BALANCE")));
        }
        Map<String, Object> warningMap = new HashMap<String, Object>();
        for (TicketCenterBean ticketCenterBean : ticketCenterBeanList) {
            double postBalance = ticketCenterBean.getBalance();
            if (postBalance <= 0) {
                continue;
            }
            double warnAmount = postMap.get(ticketCenterBean.getPostCode());
            if (postBalance > warnAmount) {
                continue;
            }
            warningMap.put(ticketCenterBean.getPostName(), postBalance);
        }
        return warningMap;
    }
}
