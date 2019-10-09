package org.mz.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Finance {
    @Id
    private int id;
    @Column(name = "qieman")
    private BigDecimal qieman;

    @Column(name = "alipay")
    private BigDecimal alipay;

    @Column(name = "wechat")
    private BigDecimal wechat;

    @Column(name = "bank")
    private BigDecimal bank;

    @Column(name = "dept")
    private BigDecimal dept;

    @Column(name = "stock")
    private BigDecimal stock;

    @Column(name = "loan")
    private BigDecimal loan;

    @Column(name = "huabei")
    private BigDecimal huabei;

    @Column(name = "baitiao")
    private BigDecimal baitiao;

    @Column(name = "zhaoshang")
    private BigDecimal zhaoshang;

    @Column(name = "zhongxin")
    private BigDecimal zhongxin;

    @Column(name = "total")
    private BigDecimal total;

    @CreatedDate
    @Column(name = "createtime")
    private Date createtime;

    @LastModifiedDate
    @Column(name = "updatetime")
    private Date updatetime;

}
