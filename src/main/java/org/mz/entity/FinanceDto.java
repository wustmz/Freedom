package org.mz.entity;

import lombok.Data;

@Data
public class FinanceDto {
    private String createTime;
    private String total;

    public FinanceDto(String createTime, String total) {
        super();
        this.createTime = createTime;
        this.total = total;
    }
}
