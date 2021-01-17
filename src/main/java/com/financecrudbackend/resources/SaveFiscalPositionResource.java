package com.financecrudbackend.resources;

import com.financecrudbackend.models.State;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Year;

@Data
public class SaveFiscalPositionResource {
    @NotNull
    private Year yearOfBalance;
    @NotNull
    @Size(max = 10)
    private String category;
    private State state;
    @NotNull
    @Size(max = 10)
    private String item;
    @NotNull
    private Integer amount;
    @Digits(integer = 1, fraction = 3)
    @NotNull
    private BigDecimal percentOfGdp;
}