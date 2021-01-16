package com.financecrudbackend.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "fiscal_positions")
public class FiscalPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy")
    @Column(name = "year_of_balance", nullable = false)
    private Date yearOfBalance;
    @Column(name = "state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "item", nullable = false)
    private String item;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @Column(name = "percent_of_gdp", nullable = false, precision = 4, scale = 3)
    private BigDecimal percentOfGdp;
}
