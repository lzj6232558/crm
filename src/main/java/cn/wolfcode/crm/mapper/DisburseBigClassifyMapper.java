package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DisburseBigClassify;
import java.util.List;

public interface DisburseBigClassifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DisburseBigClassify record);

    DisburseBigClassify selectByPrimaryKey(Long id);

    List<DisburseBigClassify> selectAll();

    int updateByPrimaryKey(DisburseBigClassify record);

    /**
     * 在删除父分类前删除和子分类的关系
     * @param bigId
     */
    void deleteRelationByBigId(Long bigId);
}