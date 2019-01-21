package org.mz.entity;


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
    private double amount;
    @Column(name = "createdTime")
    private String createdTime;
    @Column(name = "netValue")
    private double netValue;
    @Column(name = "share")
    private double share;
    @Column(name = "updateTime")
    private String updateTime;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
}
