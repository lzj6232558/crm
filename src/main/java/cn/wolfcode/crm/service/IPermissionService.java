package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    List<Permission> getPermissionbyRoleId(Long id);

    void reload();

    List<String> selectResourceByEmpId(Long id);
}
