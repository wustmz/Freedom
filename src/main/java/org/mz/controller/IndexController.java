package org.mz.controller;

import com.google.gson.Gson;
import org.mz.entity.Fund;
import org.mz.entity.FundTx;
import org.mz.service.FundService;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private FundTxService fundTxService;
    @Autowired
    private FundService fundService;

    /**
     * 交易列表
     *
     * @return
     */
    @GetMapping("/tx/list")
    public String txList() {
        List<FundTx> all = fundTxService.findAll();
        return "txList";
    }

    /**
     * 基金列表
     *
     * @return
     */
    @GetMapping("/fund/list")
    public String fundList(Model model) {
        List<Fund> list = fundService.findAll();
        model.addAttribute("list", list);
        return "fundList";
    }

    @GetMapping("/fund/info/{id}")
    public String fundInfo(@PathVariable int id, Model model) {
        Fund fund = fundService.findById(id);
        model.addAttribute("fund", fund);
        return "fundList";
    }

    @PostMapping("/fund/save")
    public String saveFund(@RequestBody Fund fund) {
        fundService.save(fund);
        return "fundList";
    }

    @PostMapping("/fund/delById/{id}")
    public String delById(@PathVariable int id) {
        fundService.deleteById(id);
        return "fundList";
    }
}
