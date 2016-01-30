package com.cm.manage.controller.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.service.order.ILotteryControlService;
import com.cm.manage.service.order.ITicketService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.order.TicketCenterVO;
import com.cm.manage.vo.order.TicketVO;
import com.cm.order.http.IOrderManageHttpService;
import com.cm.order.http.bean.TicketCenterBean;

@Controller
@RequestMapping("/ticketController")
public class TicketController extends BaseController {
    private static final Logger logger = Logger.getLogger(TicketController.class);

    @Autowired
    private ILotteryControlService lotteryControlService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IOrderManageHttpService orderInterface;

    /**
     * 跳转到出票查询
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "toTicketList")
    public String toTicketList(HttpServletRequest request) {
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        return "/ticket/ticketList";
    }

    /**
     * 出票查询
     *
     * @param dg
     * @param ticket
     * @return
     */
    @RequestMapping(params = "ticketList")
    @ResponseBody
    public EasyuiDataGridJson ticketList(EasyuiDataGrid dg, TicketVO ticket) {
        return ticketService.ticketList(dg, ticket);
    }

    /**
     * 跳转到传票查询
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "toUnTicketList")
    public String toUnTicketList(HttpServletRequest request) {
        //彩种
        List<LotteryControl> lottery = lotteryControlService.getLoteryControlList();
        request.setAttribute("lottery", lottery);
        return "/ticket/unticketList";
    }

    /**
     * 传票查询
     *
     * @param dg
     * @param ticket
     * @return
     */
    @RequestMapping(params = "unticketList")
    @ResponseBody
    public EasyuiDataGridJson unticketList(EasyuiDataGrid dg, TicketVO ticket) {
        return ticketService.unticketList(dg, ticket);
    }

    /**
     * 重新传票
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "sendTicket")
    @ResponseBody
    public Json sendTicket(HttpServletRequest request, String ids) {
        Json j = new Json();
        List<String> ticketList = new ArrayList<String>();
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            ticketList.add(idStr[i]);
        }
        int n = orderInterface.sendTicketByAdm(ticketList);
        if (n != 1) {
            j.setSuccess(false);
        } else {
            saveLog(request, "/ticketController.do?sendTicket", CommonConstants.LOG_FOR_SEND_TICKET, "票(" + ids + ")重发");
            j.setSuccess(true);
        }
        return j;
    }

    /**
     * 跳转到出票账户
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "toTicketCenter")
    public String toTicketCenter() {
        return "/ticket/ticketCenterList";
    }

    /**
     * 出票账户
     *
     * @param dg
     * @param ticket
     * @return
     */
    @RequestMapping(params = "ticketCenterList")
    @ResponseBody
    public List<TicketCenterVO> ticketCenterList() {
        List<TicketCenterBean> ticketBean = orderInterface.ticketCenterBalance();
        return ticketService.ticketCenterList(ticketBean);
    }

    /**
     * 余额警告阀值设置页面
     *
     * @return
     */
    @RequestMapping(params = "toTicketCenterWar")
    public String toTicketCenterWar() {
        return "/ticket/ticketCenterWar";
    }

    /**
     * 余额警告阀值设置
     *
     * @param ticketCenters
     * @return
     */
    @RequestMapping(params = "ticketWar")
    @ResponseBody
    public Json ticketWar(HttpServletRequest request, String ticketCenters) {
        Json j = new Json();
        JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(ticketCenters);
        int success = 0;
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            TicketCenterVO ticketCenter = (TicketCenterVO) JSONObject.toBean(jsonObj, TicketCenterVO.class);
            ticketService.ticketCenterWar(ticketCenter);
            String postName = ticketCenter.getPostName();
            saveLog(request, "/ticketController.do?ticketWar", CommonConstants.LOG_FOR_SEND_TICKET, "设置出票口(" + postName + ")余额警告阀值为：" + ticketCenter.getMaxBalance());
        }
        j.setSuccess(true);

        return j;
    }

    /**
     * 票号详情
     *
     * @param request
     * @param ticketId
     * @return
     */
    @RequestMapping(params = "toTicketDetail")
    public String toAutoDetail(HttpServletRequest request, String ticketId) {
        Map ticket = ticketService.ticketInfo(ticketId);
        request.setAttribute("ticket", ticket);
        return "/ticket/ticketDetail";
    }
}
