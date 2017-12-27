package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Producttype;
import cn.wolfcode.crm.mapper.ShoppingcarMapper;
import cn.wolfcode.crm.service.IConsumptionRecordService;
import cn.wolfcode.crm.service.IProducttypeService;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("collectMoney")
public class CollectMoneyController {

    @Autowired
    private ISystemDictionaryItemService itemService;

    @Autowired
    private IProducttypeService producttypeService;

    @Autowired
    private ShoppingcarMapper mapper;

    @Autowired
    private IConsumptionRecordService consumptionRecordService;

    @RequestMapping("myView")
    @RequiresPermissions("collectMoney/myView")
    @RequiredName("收银视图")
    public String view(Model model) {

        List<Producttype> producttypes = producttypeService.selectAllTwoType();
        model.addAttribute("items", producttypes);
        mapper.delete();
        return "collectMoney/money";
    }

    @RequestMapping("getMoney")
    @RequiresPermissions("collectMoney/getMoney")
    @ResponseBody
    public JsonResult getMoney(Long vipNumber) {
        JsonResult insert = consumptionRecordService.insert(vipNumber);
        if (insert.isSuccess()) {
            mapper.updateshopingByVipNumber(vipNumber);
        }
        return insert;
    }


}
