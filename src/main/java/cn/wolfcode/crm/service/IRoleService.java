package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Role;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<String> selectRoleNameByEmpId(Long id);
}
