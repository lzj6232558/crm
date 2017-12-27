package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    IDepartmentService service;

    @RequestMapping("myView")
    @RequiresPermissions("department:myView")
    @RequiredName("部门视图")
    public String view() {
        return "department";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("department:query")
    @RequiredName("部门查询")
    public List<Department> query() {
        return service.selectAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("department:saveOrUpdate")
    @RequiredName("部门信息保存/编辑")
    public JsonResult saveOrUpdate(Department department) {
        try {
            if (department.getId() == null) {
                department.setState(true);
                service.insert(department);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(department);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("department:changeState")
    @RequiredName("部门状态修改")
    public JsonResult changeState(Long id) {
        try {
            service.changeState(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
