package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionaryItem {
    private Long id;
    private SystemDictionary  systemDictionary;
    private String name;

    private String intro;

}