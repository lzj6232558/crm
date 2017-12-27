package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisburseQueryObject extends QueryObject {
    private String groupBy = "user.username";
}
