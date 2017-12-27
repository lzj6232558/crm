package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DisburseLittleClassify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisburseLittleClassifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DisburseLittleClassify record);

    DisburseLittleClassify selectByPrimaryKey(Long id);

    List<DisburseLittleClassify> selectAll();

    int updateByPrimaryKey(DisburseLittleClassify record);

    /**
     * 根据父级分类删除子级分类
     * @param bigId
     */
    void deleteByBigId(Long bigId);

    /**
     * 在删除子分类前先删除父子关系
     * @param littleId
     */
    void deleteRelationLittleBigId(Long littleId);

    /**
     * 在保存子分类后维护父子关系
     * @param bigId
     * @param littleId
     */
    void insertBigAndLittleRelation(@Param("bigId") Long bigId,@Param("littleId") Long littleId);

    /**
     * 根据大分类的名字查询小分类
     * @param bigName
     * @return
     */
    List<DisburseLittleClassify> getLittleByBigName(String bigName);
}