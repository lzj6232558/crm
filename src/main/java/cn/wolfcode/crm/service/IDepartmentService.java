package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Department;

import java.util.List;

public interface IDepartmentService {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
    //改变部门状态
    void changeState(Long id);
}
