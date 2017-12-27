package cn.wolfcode.crm.page;

import lombok.*;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private int total;
    private List<?> rows;
}
