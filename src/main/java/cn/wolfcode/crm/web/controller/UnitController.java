package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Unit;
import cn.wolfcode.crm.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("unit")
public class UnitController {

    @Autowired
    IUnitService service;

    @RequestMapping("query")
    @ResponseBody
    public List<Unit> query() {
        return service.selectAll();
    }

}
