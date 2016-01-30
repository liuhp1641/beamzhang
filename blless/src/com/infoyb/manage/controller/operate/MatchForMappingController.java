package com.cm.manage.controller.operate;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.operate.IMatchForMappingService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;

@Controller
@RequestMapping("/matchController")
public class MatchForMappingController extends BaseController {

    private static final Logger logger = Logger.getLogger(MatchForMappingController.class);
    
    @Autowired
    private IMatchForMappingService matchService;
    /**
     * 跳转到赛事管理
     *
     * @return
     */
    @RequestMapping(params = "toMatchForMapping")
    public String toMatchForMapping() {
        return "/operate/matchForMapping";
    }

    /**
     * 赛事管理查询
     *
     * @return
     */
    @RequestMapping(params = "matchForMappingList")
    @ResponseBody
    public EasyuiDataGridJson matchForMappingList(EasyuiDataGrid dg) {
        return matchService.matchForMappingList(dg);
    }
    

    /**
     * 更改 赛事
     *
     * @return
     */
    @RequestMapping(params = "updateMatchShortName")
    @ResponseBody
    public Json updateMatchShortName(HttpServletRequest request, String matchName, String matchShortName) {
        Json j = new Json();
        try {
        	matchService.updateMatchShortName(matchName, matchShortName);
            j.setMsg("修改操作项成功");
            StringBuilder desc = new StringBuilder("修改");
            desc.append("赛事: ");
            desc.append(matchName);
            desc.append("为: ");
            desc.append(matchShortName);
            saveLog(request, "/matchController.do?updateMatchShortName",
                    CommonConstants.LOG_FOR_MATCH_FOR_MAPPING, desc.toString());
        } catch (Exception e) {
            j.setMsg("修改操作项失败");
            e.printStackTrace();
        }
        j.setSuccess(true);
        return j;
    }
    
}
