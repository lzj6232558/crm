package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DisburseLittleClassify;

import java.util.List;

public interface IDisburseLittleClassifyService {
    void deleteByPrimaryKey(Long id);

    void insert(DisburseLittleClassify record);

    DisburseLittleClassify   selectByPrimaryKey(Long id);

    List<DisburseLittleClassify  > selectAll();

    void updateByPrimaryKey(DisburseLittleClassify record);

    /**
     * 根据大分类的名字查询小分类
     * @param bigName
     * @return
     */
    List<DisburseLittleClassify> getLittleByBigName(String bigName);
}
