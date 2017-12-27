package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PointExchange;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import jxl.write.WriteException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
public interface IPointExchangeService {
    int deleteByPrimaryKey(Long id);

    int insert(PointExchange record);

    PointExchange selectByPrimaryKey(Long id);

    List<PointExchange> selectAll();

    int updateByPrimaryKey(PointExchange record);

    PageResult query(QueryObject qo);

    void exportXls(HttpServletResponse resp) throws IOException, WriteException;
}
