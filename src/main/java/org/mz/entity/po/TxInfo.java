package org.mz.entity.po;

import lombok.Data;
import lombok.ToString;
import org.mz.entity.FundTx;

@Data
@ToString
public class TxInfo extends FundTx {
    private String name;
    private String code;
}
