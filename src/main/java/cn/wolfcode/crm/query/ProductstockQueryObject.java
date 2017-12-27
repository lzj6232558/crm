package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductstockQueryObject extends QueryObject {
    //库存阈值
    private Integer maxNumber;

    //仓库id
    private Long depotId;

}
