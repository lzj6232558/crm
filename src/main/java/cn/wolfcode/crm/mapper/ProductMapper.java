package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    void changeState(Long id);

    /**
     * 根据商品的名字查询商品信息
     * @param proName
     * @return
     */
    List<Product> selectByProName(String proName);
}