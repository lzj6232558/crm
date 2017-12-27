package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.service.IMenuService;
import cn.wolfcode.crm.util.MenuUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService service;


    @RequestMapping("getRootMenu")
    @ResponseBody
    public Object getRootMenu() {
        //先从session中取出来该用户的菜单集合
        Session session = SecurityUtils.getSubject().getSession();
        Object menuData = session.getAttribute("MENU_DATA");
        //如果session中没有,就需要查询数据库
        if(menuData==null) {
            menuData = service.getRootMenu();
            //对菜单做过滤操作
            MenuUtil.checkPermssion((List<Menu>)menuData);
            //放入session中
            session.setAttribute("MENU_DATA",menuData);
        }
        return menuData;
    }
    @ResponseBody
    @RequestMapping("menu")
    public void getMnnu(Long prentid, Model model){
        List<Menu>list=service.selectByPrentIdKey(prentid) ;
        model.addAttribute("list",list);

    }

}
