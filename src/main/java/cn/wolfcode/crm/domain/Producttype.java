package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Producttype {
    private Long id;

    private String name;

    private Long parentId;

    private List<Product> list;
}