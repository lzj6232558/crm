package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Productstock {
    private Long id;

    private BigDecimal price;

    private BigDecimal storenumber;

    private BigDecimal amount;

    private Product product;

    private Depot depot;

}