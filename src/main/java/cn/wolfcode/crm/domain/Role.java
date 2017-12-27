package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Role {
    private Long id;

    private String sn;

    private String name;

    private List<Permission> permissions;
}