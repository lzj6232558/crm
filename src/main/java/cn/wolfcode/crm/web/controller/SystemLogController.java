package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.service.ISystemLogService;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemLog")
public class SystemLogController {

    @Autowired
    ISystemLogService service;

    @RequestMapping("myView")
    @RequiresPermissions("systemLog:myView")
    @RequiredName("日志视图")
    public String view() {
        return "systemLog";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("systemLog:query")
    @RequiredName("日志查询")
    public List<SystemLog> query() {
        return service.selectAll();
    }
}
