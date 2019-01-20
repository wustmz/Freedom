package org.mz.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FundTx {
    @Id
    private int id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "netValue")
    private BigDecimal netValue;
    @Column(name = "createdTime")
    private String createdTime;
    @Column(name = "updateTime")
    private String updateTime;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
}
