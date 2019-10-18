package org.mz.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import static com.baomidou.mybatisplus.enums.FieldFill.INSERT;
import static com.baomidou.mybatisplus.enums.FieldFill.INSERT_UPDATE;

@Data
@TableName("fund_tx")
public class FundTx {

    private int id;

    private double amount;

    @TableField(fill = INSERT)
    private String createdTime;

    private double netValue;

    private double share;

    @TableField(fill = INSERT_UPDATE)
    private String updateTime;

    private String name;

    private String code;
}
