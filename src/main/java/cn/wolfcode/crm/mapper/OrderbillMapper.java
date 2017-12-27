package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderbillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderbill record);

    Orderbill selectByPrimaryKey(Long id);

    List<Orderbill> selectAll();

    int updateByPrimaryKey(Orderbill record);

    int queryCount(QueryObject qo);

    List<Orderbill> queryList(QueryObject qo);

    int queryReturnbillCount(QueryObject qo);

    List<Orderbill> queryReturnbillList(QueryObject qo);

    //维护订单和产品的关系
    void insertRelation(@Param("billId") Long billId, @Param("productId") Long productId);

    //删除关系
    void deleteRelation(Long billId);

    //订单审核
    void audit(Orderbill old);
}