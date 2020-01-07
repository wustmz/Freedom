package org.mz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.utils.DapanUtil;
import org.mz.entity.DapanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DapanTest {
    @Autowired
    private DapanUtil dapanUtil;

    @Test
    public void testStock() {
        DapanData dapanData = dapanUtil.get("sh510500");
        System.out.println(dapanData.getRate());
        System.out.println(dapanData.getDot());
        System.out.println(dapanData.getNowPic());
    }

    @Test
    public void testLow() {
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
        System.out.println("标的: " + map.get(code) + "; 代码: " + code + "; 涨跌幅: " + temp);
    }

    @Test
    public void testTime() {
        System.out.println(LocalDateTime.now().getHour());
    }
}
