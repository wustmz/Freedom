package org.mz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DapanData {
    private String rate;
    private String dot;
    private String name;
    private String nowPic;
}
