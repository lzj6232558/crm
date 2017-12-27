package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CheckProduct;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CheckProductMapper {
    /**
     * 查询需要盘点的商品条目
     * @return
     */
    List<CheckProduct> checkproduct(QueryObject qo);

    int queryCount(QueryObject qo);



    CheckProduct selectByPrimaryKey(Long id);

    /**
     * 查对应商品在所有仓库的总数量
     * 经过需求考察,没必要这个功能
     * @param id
     * @return
     */
    //CheckProduct selectDepotTotalNumber(Long id);

    void update(CheckProduct checkProduct);

    /**
     * 商品数量调整
     * @param newNumber
     */
    void adjust(@Param("newNumber") BigDecimal newNumber, @Param("id") Long id);
}
