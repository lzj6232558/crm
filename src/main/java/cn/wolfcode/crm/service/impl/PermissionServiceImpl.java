package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper mapper;
    @Autowired
    ApplicationContext ctx;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return mapper.insert(record);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Permission> getPermissionbyRoleId(Long id) {
        return mapper.getPermissionbyRoleId(id);
    }

    @Override
    public void reload() {
        List<String> resources = mapper.selectResources();
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beans.values();
        for (Object value : values) {
            Method[] methods = value.getClass().getSuperclass().getDeclaredMethods();

            for (Method method : methods) {
                RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);

                if (annotation != null) {
                    String[] value1 = annotation.value();
                    if (!resources.contains(value1[0])) {
                        RequiredName name = method.getAnnotation(RequiredName.class);
                        Permission permission = new Permission();
                        permission.setResource(value1[0]);
                        permission.setName(name.value());
                        mapper.insert(permission);
                    }
                }
            }
        }
    }

    @Override
    public List<String> selectResourceByEmpId(Long id) {
        return mapper.selectResourceByEmpId(id);
    }
}
