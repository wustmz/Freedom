package org.mz.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static com.baomidou.mybatisplus.enums.FieldFill.INSERT;
import static com.baomidou.mybatisplus.enums.FieldFill.INSERT_UPDATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("finance")
public class Finance implements Serializable {

    private int id;

    private BigDecimal qieman;


    private BigDecimal alipay;


    private BigDecimal wechat;


    private BigDecimal bank;


    private BigDecimal dept;


    private BigDecimal stock;


    private BigDecimal loan;


    private BigDecimal huabei;


    private BigDecimal baitiao;


    private BigDecimal zhaoshang;


    private BigDecimal zhongxin;


    private BigDecimal total;

    /**
     * 结余
     */
    private BigDecimal surplus;

    @TableField(fill = INSERT)
    private Date createTime;

    @TableField(fill = INSERT_UPDATE)
    private Date updateTime;

}
