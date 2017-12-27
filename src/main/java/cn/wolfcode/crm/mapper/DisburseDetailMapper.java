package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DisburseDetail;
import cn.wolfcode.crm.query.DisburseQueryObject;
import java.util.List;
import java.util.Map;

public interface DisburseDetailMapper {

    int insert(DisburseDetail record);

    List<DisburseDetail> selectAll();

    int updateByPrimaryKey(DisburseDetail record);

    void deleteByPrimaryKey(Long id);

    /**
     * 支出分析
     * @param qo
     * @return
     */
    List<Map<String,Object>>  selectGroupBy(DisburseQueryObject qo);
}