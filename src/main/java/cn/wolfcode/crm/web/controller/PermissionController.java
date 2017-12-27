package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    IPermissionService service;

    @RequiresPermissions("permission:myView")
    @RequiredName("权限页面")
    @RequestMapping("myView")
    public String view() {
        return "permission";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<Permission> query() {
        return service.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try{
            service.deleteByPrimaryKey(id);
            return new JsonResult("删除完成");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new JsonResult(false,"删除失败");
    }


    @RequestMapping("reload")
    @ResponseBody
    public JsonResult reload() {
        try{
            service.reload();
            return new JsonResult("加载完成");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new JsonResult(false,"加载失败");
    }


    @RequestMapping("getPermissionbyRoleId")
    @ResponseBody
    public List<Permission> getPermissionbyRoleId(Long id) {
        return service.getPermissionbyRoleId(id);
    }

}
