package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    IEmployeeService service;

    @RequestMapping("myView")
    @RequiresPermissions("employee:myView")
    @RequiredName("员工视图")
    public String view() {
        return "employee";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiredName("员工查询")
    @RequiresPermissions("employee:query")
    public PageResult query(QueryObject qo) {
        PageResult result = service.query(qo);
        return result;
    }

    //在支出时选择支出人
    @RequestMapping("selectDisburseAll")
    @ResponseBody
    public List<Employee> selectDisburseAll() {
        List<Employee> employees = service.selectAll();
        return employees;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiredName("员工信息保存/修改")
    @RequiresPermissions("employee:saveOrUpdate")
    public JsonResult saveOrUpdate(Employee employee) {
        try {
            if (employee.getId() == null) {
                employee.setState(true);
                service.insert(employee);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(employee);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiredName("员工状态修改")
    @RequiresPermissions("employee:changeState")
    public JsonResult changeState(Long id) {
        try {
            service.changeState(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @ResponseBody
    @RequestMapping("getRoleIdByEmpId")
    public List<Long> getRoleIdByEmpId(Long id) {
        return service.getRoleIdByEmpId(id);
    }

    @ResponseBody
    @RequestMapping("export")
    public JsonResult export(HttpServletResponse response) {
        try {
            service.export(response);
            return new JsonResult("下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "下载失败");
        }
    }

    @RequestMapping("imlport")
    @ResponseBody
    public JsonResult implortXsl(MultipartFile file) {
        try {
            service.implorXsl(file);
            return new JsonResult("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "上传失败");
        }

    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Employee> selectAll() {
        return service.selectAll();
    }
}
