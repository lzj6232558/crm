package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPointExchangeService;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Charles on 2017/12/17.
 */
@Controller
@RequestMapping("pointexchange")
public class PointExchangeController {
    @Autowired
    private IPointExchangeService pointexchangeService;

    @RequestMapping("view")
    @RequiresPermissions("pointexchange:view")
    @RequiredName("积分记录视图")
    public String view(QueryObject qo) {
        PageResult rows = pointexchangeService.query(qo);
        return "pointexchange";
    }


    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("pointexchange:query")
    @RequiredName("积分记录视图")
    public PageResult query(QueryObject qo) {
        PageResult rows = pointexchangeService.query(qo);
        return rows;
    }

    @RequestMapping("export")
    public void exportXls(HttpServletResponse resp) throws Exception {
        pointexchangeService.exportXls(resp);
    }
}
