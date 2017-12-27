package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject{
    private Long parentId;
    private Long childId;
}
