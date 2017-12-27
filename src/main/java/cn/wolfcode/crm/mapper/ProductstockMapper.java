package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Productstock;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductstockMapper {


    /**
     * 我的东西不要乱动
     * @param id
     * @return
     */

    /**
     * 通过商品id和仓库id查库存
     * @param productId
     * @return
     */
    Productstock selectByProductIdAndDepotId(@Param("productId") Long productId, @Param("depotId") Long depotId);

    int queryCount(QueryObject qo);

    List<Productstock> queryList(QueryObject qo);











    /**
     * 华丽的分割线_______________________________________________________________________________________
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(Productstock record);

    Productstock selectByPrimaryKey(Long id);

    List<Productstock> selectAll();

    int updateByPrimaryKey(Productstock record);

    /**
     * 在销售中通过商品id和要销售的数量在对应仓库中进行查询 是否有该商品 数量是否足够
     * @param ProductId 商品id
     * @param storeNumber 商品数量
     * @return
     */
    Productstock selectProductByProductIdAndNumber(@Param("ProductId") Long ProductId, @Param("storeNumber") BigDecimal storeNumber, @Param("depotId") Long depotId);

    /**
     * 在销售后更改库存量和库存总价
     * @param ps
     */
    void updateSellLaterStorenumberById(Productstock ps);

    //根据货品id和仓库id查商品
    Productstock selectStockByPidAndDid(@Param("depotID")Long id,@Param("pId")Long id1);

}