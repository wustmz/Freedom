package org.mz.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.mz.entity.DapanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DapanUtil {
    @Autowired
    private RestTemplate restTemplate;

    private DapanData parse(String object) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.parseObject(JSONObject.parseArray(JSONObject.parseObject(object).getString("result")).get(0).toString()).getString("dapandata"));
        DapanData dapanData = DapanData.builder().build();
        dapanData.setRate(jsonObject.getString("rate"));
        dapanData.setDot(jsonObject.getString("dot"));
        dapanData.setName(jsonObject.getString("name"));
        dapanData.setNowPic(jsonObject.getString("nowPic"));
        return dapanData;
    }

    public DapanData get(String code) {
        Map<String, String> vars = new HashMap<>();
        vars.put("gid", code);
        vars.put("key", "9303093729a121adf70f5562dc5cedc1");

        String url = "http://web.juhe.cn:8080/finance/stock/hs?gid={gid}&key={key}";
        String object = restTemplate.getForObject(url, String.class, vars);
        return parse(object);
    }


    public Map<String, String> getAllFundCode() {
        Map<String, String> map = new HashMap<>();
        String s1 = "sh512580";//环保ETF
        String s2 = "sz159952";//创业ETF
        String s3 = "sz159920";//恒生ETF
        String s4 = "sh512010";//医药ETF
        String s5 = "sh510500";//500ETF
        String s6 = "sh510880";//红利ETF
        String s7 = "sh512000";//券商ETF
        String s8 = "sh510300";//300ETF
        String s9 = "sz159928";//消费ETF
        String s10 = "sh513050";//中概互联
        String s11 = "sz162411";//华宝油气
        String s12 = "sh518880";//黄金ETF
        String s13 = "sh512980";//传媒ETF
        String s14 = "sh513030";//德国30
        map.put(s1, "环保ETF");
        map.put(s2, "创业ETF");
        map.put(s3, "恒生ETF");
        map.put(s4, "医药ETF");
        map.put(s5, "500ETF");
        map.put(s6, "红利ETF");
        map.put(s7, "券商ETF");
        map.put(s8, "300ETF");
        map.put(s9, "消费ETF");
        map.put(s10, "中概互联");
        map.put(s11, "华宝油气");
//        map.put(s12, "黄金ETF");
        map.put(s13, "传媒ETF");
        map.put(s14, "德国30");
        return map;
    }
}
