package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class MyRealm extends AuthorizingRealm{
    @Autowired
    IRoleService roleService;
    @Autowired
    IPermissionService permissionService;

    @Autowired
    IEmployeeService service;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();//获取当前登录的用户名
        Employee employee = service.selectEmployeeByUsername(username);//根据用户名查出当前对象

        //当前对象为空返回null
        if(employee == null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes(employee.getUsername()), this.getName());

        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Employee employee = (Employee) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(employee.isAdmin()){
            info.addRoles(Arrays.asList("admin"));
            info.addStringPermission("*:*");
        }else {
            List<String> roles = roleService.selectRoleNameByEmpId(employee.getId());
            List<String> permissions =  permissionService.selectResourceByEmpId(employee.getId());
            info.addRoles(roles);
            info.addStringPermissions(permissions);
        }
            return info;
    }

}
