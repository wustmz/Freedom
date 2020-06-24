package org.mz.schedule;

import lombok.extern.slf4j.Slf4j;
import org.mz.common.utils.DapanUtil;
import org.mz.entity.DapanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class Jobs {

    @Autowired
    private DapanUtil dapanUtil;


    /**
     * 每天下午两点半运行一次
     * 找到跌幅最大的一只股票, 每周定投两千, 每天定投四百
     */
    @Scheduled(cron = "0 30 14 ? * *")
//    @Scheduled(cron = "0 0/2 * * * ?")
    public void cronJob() {
        //找到跌幅最大的一只股票, 每周定投两千, 每天定投四百
        Map<String, String> map = dapanUtil.getAllFundCode();

        Double temp = 0.0;
        String code = "";
        for (String fundCode : map.keySet()) {
            DapanData dapanData = dapanUtil.get(fundCode);
            Double rate = Double.parseDouble(dapanData.getRate());

            if (rate < temp) {
                temp = rate;
                code = fundCode;
            }
        }
        log.info("今日最大跌幅标的: {}; 代码: {}; 涨跌幅: {}", map.get(code), code, temp);
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void query() {
        int hour = LocalDateTime.now().getHour();

        if ((hour >= 9 && hour <= 11) || (hour >= 13 && hour <= 15)) {
            log.info("开始查询~~~");
            Map<String, String> map = dapanUtil.getAllFundCode();
            for (String fundCode : map.keySet()) {
                DapanData dapanData = dapanUtil.get(fundCode);
                Double rate = Double.parseDouble(dapanData.getRate());
                log.info("标的: {}; 代码: {}; 涨跌幅: {}", map.get(fundCode), fundCode, rate);
            }
        }
    }

}