package org.mz.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

import static com.baomidou.mybatisplus.enums.FieldFill.INSERT;


/**
 * @author 86501
 * @Description：
 * @date 2019/1/20
 * @time 12:47
 */
@TableName("tx")
@Data
public class Tx {

    private int id;

    private BigDecimal amount;

    @TableField(fill = INSERT)
    private String createdTime;
}
