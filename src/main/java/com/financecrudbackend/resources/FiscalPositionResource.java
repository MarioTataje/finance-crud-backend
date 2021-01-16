package com.financecrudbackend.resources;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import com.financecrudbackend.models.State;

@Data
public class FiscalPositionResource {
    private Long id;
    private Date yearOfBalance;
    private State state;
    private String category;
    private String item;
    private Integer amount;
    private BigDecimal percentOfGdp;
}


