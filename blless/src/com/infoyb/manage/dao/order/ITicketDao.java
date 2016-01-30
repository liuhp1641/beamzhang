package com.cm.manage.dao.order;

import java.util.List;
import java.util.Map;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.TicketCenterVO;
import com.cm.manage.vo.order.TicketVO;
import com.cm.order.http.bean.TicketCenterBean;

public interface ITicketDao {
	
	/**
	 * 出票查询
	 * @param dg
	 * @param ticket
	 * @return
	 */
	public EasyuiDataGridJson ticketList(EasyuiDataGrid dg, TicketVO ticket);
	/**
	 * 传票查询
	 * @param dg
	 * @param ticket
	 * @return
	 */
	public EasyuiDataGridJson unticketList(EasyuiDataGrid dg, TicketVO ticket);
	/**
	 * 出票账户
	 * @param ticketBean
	 * @return
	 */
	public List<TicketCenterVO> ticketCenterList(List<TicketCenterBean> ticketBean);
	
	/**
	 * 余额警告阀值设置
	 * @param vo
	 * @return
	 */
	public void ticketCenterWar(TicketCenterVO vo);
	
	/**
	 * 票号详情
	 * @param ticketId
	 * @return
	 */
	public Map ticketInfo(String ticketId);

    public List<Map> getTicketCenterWarning();
}
