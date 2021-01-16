package com.financecrudbackend.resources;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SaveFiscalPositionResource {
    @DateTimeFormat(pattern = "YYYY")
    @NotNull
    private Date yearOfBalance;
    @NotNull
    @Size(max = 10)
    private String state;
    @NotNull
    @Size(max = 10)
    private String category;
    @NotNull
    @Size(max = 10)
    private String item;
    @NotNull
    private Integer amount;
    @NotNull
    private BigDecimal percentOfGdp;
}