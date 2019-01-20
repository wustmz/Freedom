package org.mz.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author 86501
 * @Description：
 * @date 2019/1/20
 * @time 12:47
 */
@Entity(name = "tx")
@Data
public class Tx {
    @Id
    private int id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "createdTime")
    private String createdTime;
}
