package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService service;

    @RequestMapping("myView")
    public String view(){
        return "role";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<Role> query(){
        return service.selectAll();
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Role role) {
        try {
            if (role.getId() == null) {
                service.insert(role);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(role);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
