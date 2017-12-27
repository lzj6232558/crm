package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Producttype;

import java.util.List;

public interface IProducttypeService {
    int deleteByPrimaryKey(Long id);

    int insert(Producttype record);

    Producttype selectByPrimaryKey(Long id);

    List<Producttype> selectAll();

    int updateByPrimaryKey(Producttype record);

    List<Producttype> selectParentClassify();

    List<Producttype> selectChildClassify(Long id);

    List<Producttype> selectAllTwoType();
}
