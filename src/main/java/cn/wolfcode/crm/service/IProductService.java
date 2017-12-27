package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    void implorXsl(MultipartFile file) throws Exception;

    /**
     * 根据商品的名字进行查询商品信息
     * @param proName
     * @return
     */
    List<Product> selectByProName(String proName);
}
