package org.mz.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class FundTx {
    @Id
    private int id;
    @Column(name = "fundId")
    private int fundId;
    @Column(name = "netValue")
    private BigDecimal netValue;
    @Column(name = "createdTime")
    private long createdTime;
    @Column(name = "updateTime")
    private long updateTime;
}
