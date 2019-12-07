package org.mz.controller;

import org.mz.common.utils.APIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {


    @GetMapping("find")
    public String get(Model model) {
        List<Object> list = getArr("fzzq", 7.939);
        Object now = list.get(0);
        Object rate = list.get(1);
        model.addAttribute("now", now);
        model.addAttribute("rate", rate);
        List<Object> list1 = getArr("qdg", 9.675);
        Object now1 = list1.get(0);
        Object rate1 = list1.get(1);
        model.addAttribute("now1", now1);
        model.addAttribute("rate1", rate1);
        return "stock";
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
