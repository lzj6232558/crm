package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.domain.PointExchange;
import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IGiftService;
import cn.wolfcode.crm.service.IPointExchangeService;
import cn.wolfcode.crm.service.IPointItemService;
import cn.wolfcode.crm.service.IVipService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charles on 2017/12/21.
 */
@Controller
@RequestMapping("giftChange")

public class GiftChangeController {
    @Autowired
    private IPointItemService pointItemService;
    @Autowired
    private IVipService vipService;
    @Autowired
    private IPointExchangeService pointExchangeService;
    @Autowired
    private IGiftService giftService;

    @RequestMapping("view")
    @RequiresPermissions("giftChange:view")
    @RequiredName("礼品视图")
    public String view() {
        return "giftchange";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qo) {
        return pointItemService.query(qo);
    }

    /**
     * 兑换礼品之后,更新数据库中会员当前积分
     * 通过会员id更新数据库积分
     *
     * @param vipId
     * @param totalcount
     * @return
     */
    @RequestMapping("renew")
    @ResponseBody
    public JsonResult renew(Long vipId, Long totalcount, String rows) {
        try {

            System.out.println(rows);
        JSONArray json = JSONArray.parseArray(rows);
        Map<String,Object> map=null;
        JSONObject jsonOne;
        for(int i=0;i<json.size();i++){
            map = new HashMap<String,Object>();

            jsonOne = json.getJSONObject(i);
            System.out.println(jsonOne.get("sn"));
            String sn = jsonOne.getString("sn");
            Long id = jsonOne.getLong("id");
            Long currentNum = jsonOne.getLong("currentNum");
            String imagepath = jsonOne.getString("imagepath");
            String name = jsonOne.getString("name");
            Long number = jsonOne.getLong("number");
            Long point = jsonOne.getLong("point");
            Long totalNum = jsonOne.getLong("totalNum");

            map.put("id",id);
            map.put("sn", sn);
            map.put("currentNum", currentNum);
            map.put("imagepath",imagepath );
            map.put("name",name);
            map.put("number", number);
            map.put("point", point);
            map.put("totalNum",totalNum);
        }
            //1.获取会员的当前积分
            Vip vip = vipService.selectByPrimaryKey(vipId);
            //2.判断该会员的积分是否足够兑换礼品,足够,兑换,更新数据库;不足,提示
            if (vip.getCurrentpoint() >= totalcount) {
                //修改数据库中该会员的积分
                vip.setCurrentpoint(vip.getCurrentpoint() - totalcount);
                vip.setTotalpoint(vip.getTotalpoint() - totalcount);
                vipService.renew(vip);
                //3.更新兑换的礼品到数据库(pointExchange表/gift表)
                PointExchange p = new PointExchange();
                p.setVip(vip);//更新会员
                p.setInputTime(new Date());//更新操作日期
                Subject subject = SecurityUtils.getSubject();
                p.setInputUser((Employee)subject.getPrincipal());
                Long number = (Long)(map.get("number"));
                p.setNumber(number);//更新兑换数量
                String name = (String) map.get("name");
                Long id = (Long)map.get("id");
                Gift gift = giftService.selectByPrimaryKey(id);
                gift.setName(name);
                p.setGift(gift);
                //4.更新礼品信息到数据库

                gift.setCurrentNum(gift.getCurrentNum() - number);
                gift.setTotalNum(gift.getTotalNum() - number);
                giftService.updateByPrimaryKey(gift);
                pointExchangeService.insert(p);
                return new JsonResult("兑换成功!");
            }
        } catch (Exception e) {
        }
        return new JsonResult(false, "积分不足,请充值积分");
    }
}
