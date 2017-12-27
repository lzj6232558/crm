package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DisburseDetail;
import cn.wolfcode.crm.query.DisburseQueryObject;

import java.util.List;
import java.util.Map;

public interface IDisburseDetailService {

    void insert(DisburseDetail record);

    List<DisburseDetail> selectAll();

    void updateByPrimaryKey(DisburseDetail record);

    void deleteByPrimaryKey(Long id);

    /**
     * 支出分析
     * @param qo
     * @return
     */
    List<Map<String,Object>> selectGroupBy(DisburseQueryObject qo);
}
