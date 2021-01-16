package com.financecrudbackend.resources;

import com.financecrudbackend.models.State;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
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