package org.mz.controller;

import org.mz.common.APIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;

@Controller
@RequestMapping("/stock")
public class StockControllet {


    @GetMapping("find")
    @ResponseBody
    private String get() {
        String s = APIUtil.API("bank");
        double now = Double.parseDouble(s.split(",")[3]);
        double buy = 10.46;
        double rate = (now - buy) / buy;
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(rate);
        return "now=======>>" + now + "; rate=======>>" + r;
    }
}
