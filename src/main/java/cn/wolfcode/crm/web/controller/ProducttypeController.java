package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Producttype;
import cn.wolfcode.crm.service.IProducttypeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("producttype")
public class ProducttypeController {

    @Autowired
    IProducttypeService service;

    @RequestMapping("view")
    @RequiresPermissions("producttype:view")
    @RequiredName("商品分类视图")
    public String view(){
        return "product/producttype";
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Producttype> selectAll(){
        return service.selectAll();
    }

    //查询一级分类
    @RequestMapping("selectParentClassify")
    @ResponseBody
    public List<Producttype> selectParentClassify(){
        return service.selectParentClassify();
    }

    //查询二级分类
    @RequestMapping("selectChildClassify")
    @ResponseBody
    public List<Producttype> selectChildClassify( Long id){
        return service.selectChildClassify(id);
    }

    //保存一级分类
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiredName("商品分类保存/修改")
    @RequiresPermissions("producttype:saveOrUpdate")
    public JsonResult saveOrUpdate(Producttype producttype) {
        try {
            if (producttype.getId() == null) {
                service.insert(producttype);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(producttype);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    //删除一级分类
    @RequestMapping("delete")
    @ResponseBody
    @RequiredName("商品分类删除")
    @RequiresPermissions("producttype:delete")
    public JsonResult delete(Long id){
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult("保存成功");
        }catch (Exception e){

        }
        return new JsonResult("操作失败");
    }
}
