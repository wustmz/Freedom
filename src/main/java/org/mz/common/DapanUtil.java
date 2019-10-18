package org.mz.common;

import com.alibaba.fastjson.JSONObject;
import org.mz.entity.DapanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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
}
