package com.financecrudbackend.models;

import com.financecrudbackend.util.YearPropertyConverter;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Year;

@Data
@Entity
@Table(name = "fiscal_positions")
public class FiscalPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year_of_balance",columnDefinition = "smallint", nullable = false)
    @Convert(converter = YearPropertyConverter.class)
    private Year yearOfBalance;
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
