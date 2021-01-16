package com.financecrudbackend.resources;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FiscalPositionResource {
    private Long id;
    private Date yearOfBalance;
    private String state;
    private String category;
    private String item;
    private Integer amount;
    private BigDecimal percentOfGdp;
}


