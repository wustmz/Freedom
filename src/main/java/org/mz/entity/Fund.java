package org.mz.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity(name = "fund")
public class Fund {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "rate")
    private BigDecimal rate;
}
