package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Producttype;
import java.util.List;

public interface ProducttypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Producttype record);

    Producttype selectByPrimaryKey(Long id);

    List<Producttype> selectAll();

    int updateByPrimaryKey(Producttype record);

    List<Producttype> selectParentClassify();

    List<Producttype> selectChildClassify(Long id);

    Producttype selectPrductByTypeNumber(Long id);

    List<Producttype> selectAllTwoType();
}