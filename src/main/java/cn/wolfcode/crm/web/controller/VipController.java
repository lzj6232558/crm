package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.VipQueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.service.IVipService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("vip")
public class VipController {

    @Autowired
    IVipService service;
    @Autowired
    ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("myView")
    @RequiresPermissions("vip:myView")
    @RequiredName("会员卡视图")
    public String view() {
        return "vip/vip";
    }

    @RequestMapping("query")
    @RequiresPermissions("vip:query")
    @RequiredName("会员信息列表")
    @ResponseBody
    public PageResult query(VipQueryObject qo){

        return service.query(qo);
    }
    @RequestMapping("selectVipByNameOrPhone")
    @ResponseBody
    public List<Vip> selectVipByNameOrPhone(String nameOrPhone){
        return service.selectVipByNameOrPhone(nameOrPhone);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("consumptionRecord:saveOrUpdate")
    @RequiredName("会员新增/编辑")
    public JsonResult saveOrUpdate(Vip vip) {
        try {
            if (vip.getId() == null) {
                service.insert(vip);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(vip);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("source")
    @RequiresPermissions("vip:source")
    @RequiredName("会员来源")
    @ResponseBody
    public List<SystemDictionaryItem> source(Long id){
        return systemDictionaryItemService.selectItemByParentId(id);
    }

    @RequestMapping("changeState")
    @RequiresPermissions("vip:changeState")
    @RequiredName("会员卡状态")
    @ResponseBody
    public JsonResult changeState(Long id){
        try{
           service.changeState(id);
           return new JsonResult("已成功挂失:");
        }catch(RuntimeException e){
            return new JsonResult(false,e.getMessage());
        }
    }
    @RequestMapping("checkVipPhone")
    @RequiresPermissions("vip/checkVipPhone")
    @RequiredName("检测会员电话")
    @ResponseBody
    public JsonResult checkVipPhone(String phone){
        try{
            service.checkVipPhone(phone);
            return new JsonResult("该手机号尚未注册");
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    @RequestMapping("editVipPassword")
    @RequiresPermissions("vip/editVipPassword")
    @RequiredName("会员更改密码")
    @ResponseBody
    public JsonResult editVipPassword(Long vId,String oldPassword,String newPassword){
        System.out.println(vId);
        try{
            service.editVipPassword(oldPassword,newPassword,vId);
            return new JsonResult("修改成功");
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    @RequestMapping("initPassword")
    @RequiresPermissions("vip/initPassword")
    @RequiredName("会员初始化密码")
    @ResponseBody
    public JsonResult initPassword(Long id){
        try{
            service.initPassword(id);
            return new JsonResult("重置成功");
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

    @RequestMapping("checkPassword")
    @RequiresPermissions("vip/checkPassword")
    @RequiredName("消费时检测会员密码")
    @ResponseBody
    public JsonResult checkPassword(Long vId,String vipPassword){
        try{
            service.checkVipPassword(vipPassword,vId);
            return new JsonResult("输入正确");
        }catch(Exception e){
            return new JsonResult(false,"密码输入错误");
        }
    }

}
