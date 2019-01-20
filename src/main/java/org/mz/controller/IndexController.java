package org.mz.controller;

import org.mz.entity.FundTx;
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

    /**
     * 交易列表
     *
     * @return
     */
    @GetMapping("/tx/list")
    public String txList(Model model) {
        List<FundTx> list = fundTxService.findAll();
        model.addAttribute("list", list);
        return "txList";
    }


    @GetMapping("/tx/info/{id}")
    public String fundInfo(@PathVariable int id, Model model) {
        FundTx tx = fundTxService.findById(id);
        model.addAttribute("tx", tx);
        return "txList";
    }

    @PostMapping("/tx/save")
    public String saveFund(@RequestBody FundTx tx) {
        fundTxService.save(tx);
        return "txList";
    }

    @PostMapping("/tx/delById/{id}")
    public String delById(@PathVariable int id) {
        fundTxService.deleteById(id);
        return "txList";
    }
}
