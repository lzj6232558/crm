package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    RoleMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        int insert = mapper.insert(record);
        List<Permission> permissions = record.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                mapper.insertRoleAndPermissionRelation(record.getId(),permission.getId());
            }
        }
        return insert;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        mapper.deleteRoleAndPermissionRelation(record.getId());
        List<Permission> permissions = record.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                mapper.insertRoleAndPermissionRelation(record.getId(),permission.getId());
            }
        }
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<String> selectRoleNameByEmpId(Long id) {
        return mapper.selectRoleNameByEmpId(id);
    }
}
