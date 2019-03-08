package org.mz.controller;

import org.mz.common.APIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockControllet {


    @GetMapping("find")
    @ResponseBody
    private String get() {
        List<Object> list = getArr("fzzq", 8.131);
        return "now=======>>" + list.get(0) + "; rate=======>>" + list.get(1);
    }


    private List<Object> getArr(String name, double buy) {
        String s = APIUtil.API(name);
        double now = Double.parseDouble(s.split(",")[3]);
        double rate = (now - buy) / buy;
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(rate);
        List<Object> list = new ArrayList<>();
        list.add(now);
        list.add(r);
        return list;
    }
}
