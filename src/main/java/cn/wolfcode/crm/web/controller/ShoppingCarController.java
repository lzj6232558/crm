package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Shoppingcar;
import cn.wolfcode.crm.mapper.ShoppingcarMapper;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("shoppingCar")
public class ShoppingCarController {

    @Autowired
    private ShoppingcarMapper mapper;

    @RequestMapping("add")
    @ResponseBody
    @RequiresPermissions("shoppingCar:add")
    @RequiredName("添加到购物车")
    public void add(Shoppingcar shopping) {
        //购物车现有
        List<Shoppingcar> shoppingcars = mapper.selletAll();

        //购物现有商品id集合
        List<Long> longs = mapper.selletAllId();

        if (shopping.getVipId()==1) {
            if (shoppingcars.size() == 0) {
                shopping.setTocalPrice(shopping.getPrice());
                mapper.insertBlus(shopping);
                return;
            }
            if (longs.contains(shopping.getPId())){
                //表中总价格
                BigDecimal bigDecimal = mapper.selectTocalPrice(shopping.getPId());
                BigDecimal add = bigDecimal.add(shopping.getPrice());
                //表中库存
                Long shoppingnumber = mapper.selectNumber(shopping.getPId());

                shopping.setShoppingnumber(shoppingnumber+1);
                shopping.setTocalPrice(add);
                mapper.update(shopping);
            }else {
                shopping.setTocalPrice(shopping.getPrice());
                mapper.insertBlus(shopping);
            }
        }else {
            if (shoppingcars.size() == 0) {
                shopping.setTocalPrice(shopping.getPrice());
                mapper.insert(shopping);
                return;
            }
            if (longs.contains(shopping.getPId())){
                //表中总价格
                BigDecimal bigDecimal = mapper.selectTocalPrice(shopping.getPId());
                BigDecimal add = bigDecimal.add(shopping.getPrice());
                //表中库存
                Long shoppingnumber = mapper.selectNumber(shopping.getPId());

                shopping.setShoppingnumber(shoppingnumber+1);
                shopping.setTocalPrice(add);
                mapper.update(shopping);
            }else {
                shopping.setTocalPrice(shopping.getPrice());
                mapper.insert(shopping);
            }
        }


    }

    //返回所有商品
    @RequiresPermissions("shoppingCar:allProduct")
    @RequestMapping("allProduct")
    @ResponseBody
    public List allProduct() {
        List<Shoppingcar> shoppings = mapper.productAll();
        return shoppings;
    }

    //返回选择的商品
    @RequiresPermissions("shoppingCar:selectProduct")
    @RequestMapping("selectProduct")
    @ResponseBody
    public List<Shoppingcar> selectProduct() {
        List<Shoppingcar> list = mapper.selletAll();
        return list;
    }
    //返回分类的商品
    @RequiresPermissions("shoppingCar:groupProduct")
    @RequestMapping("groupProduct")
    @ResponseBody
    public List<Shoppingcar> groupProduct(Long id) {
        List<Shoppingcar> list = mapper.productGroubByName(id);
        return list;
    }


    @RequiresPermissions("shoppingCar:delete")
    @RequiredName("购物车删除")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(String name) {
        try {
            mapper.deleteById(name);
            return new JsonResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "删除失败");
    }


    @RequiresPermissions("shoppingCar:selectmenoy")
    @RequiredName("挂单")
    @RequestMapping("selectmenoy")
    @ResponseBody
    public JsonResult selectmenoy(Long id) {
        try {
            mapper.updataSta();
            //改变状态为可以结账。
            mapper.updateshoppingstatus();
            return new JsonResult("挂单成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "挂单失败");
    }

    @RequiresPermissions("shoppingCar:getselectmenoy")
    @RequiredName("取单")
    @RequestMapping("getselectmenoy")
    @ResponseBody
    public JsonResult getselectmenoy() {
        try {
            mapper.getselectmenoy();
            return new JsonResult("取单成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "挂单失败");
    }


    //存入会员vipNumber
    @RequestMapping("vipNumber")
    @ResponseBody
    public void vipNumber(Long vipNumber) {
         mapper.insertVipNumber(vipNumber);
    }



    //查询有没有选中会员
    @RequestMapping("selectVipNumber")
    @ResponseBody
    public JsonResult selectVipNumber(Long vipNumber) {
        List<Shoppingcar> shoppingcars = mapper.selectVipNumber();
        if (shoppingcars.size()==0) {
            return new JsonResult("");
        }else {
            return new JsonResult(false,"请确定会员");
        }

    }

    //查询有没有商品。并且商品不是挂单状态
    @RequestMapping("selectAllStatis")
    @ResponseBody
    public JsonResult selectAllStatis() {
        List<Shoppingcar> shoppingcars = mapper.selectAllStatis();
        if (shoppingcars.size()>0) {
            return new JsonResult("");
        }else {
            return new JsonResult(false,"老哥，你应该先取单");
        }

    }


    @RequestMapping("selectVipProduct")
    @ResponseBody
    public JsonResult selectVipProduct(Long vipNumber) {

        List<Shoppingcar> shoppingcars = mapper.selectVipProduct(vipNumber);
        if (shoppingcars.size()==0) {
            return new JsonResult("有单");
        }else {
            return new JsonResult(false,"没单");
        }
    }

    @RequestMapping("deleteselect")
    @ResponseBody
    public void deleteselect(Long vipNumber) {
        mapper.deleteselect(vipNumber);
    }

}
