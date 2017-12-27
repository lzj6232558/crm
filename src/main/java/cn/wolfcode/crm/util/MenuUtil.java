package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Iterator;
import java.util.List;

public class MenuUtil {
    private MenuUtil(){}


    public static void checkPermssion(List<Menu> menuData) {
        Subject subject = SecurityUtils.getSubject();
        Iterator<Menu> iterator = menuData.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            Permission permission = menu.getPermission();

            if(permission != null){
                boolean permitted = subject.isPermitted(permission.getResource());
                if(!permitted){
                    iterator.remove();
                    continue;
                }
            }
            
            if(menu.getChildren().size()>0){
                checkPermssion(menu.getChildren());
            }
        }

    }
}
