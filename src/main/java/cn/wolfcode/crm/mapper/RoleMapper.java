package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    void insertRoleAndPermissionRelation(@Param("role_id")Long role_id,@Param("permission_id")Long permission_id);

    void  deleteRoleAndPermissionRelation(Long id);

    List<String> selectRoleNameByEmpId(Long id);
}