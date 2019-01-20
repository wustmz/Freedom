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

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 交易列表
     *
     * @return
     */
    @RequestMapping("/tx/list")
    public String txList(Model model) {
        List<FundTx> list = fundTxService.findAll();
        model.addAttribute("list", list);
        return "txList";
    }


    /**
     * 查看详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tx/info/{id}")
    public String fundInfo(@PathVariable int id, Model model) {
        FundTx tx = fundTxService.findById(id);
        model.addAttribute("tx", tx);
        return "txInfo";
    }

    /**
     * 保存
     *
     * @param tx
     * @return
     */
    @RequestMapping("/tx/save")
    public String saveFund(FundTx tx) {
        fundTxService.save(tx);
        return "redirect:/tx/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/tx/delById/{id}")
    public String delById(@PathVariable int id) {
        fundTxService.deleteById(id);
        return "txList";
    }

    @RequestMapping("/tx/edit/{id}")
    public String editById(@PathVariable int id, Model model) {
        FundTx tx = fundTxService.findById(id);
        model.addAttribute("tx", tx);
        return "txEdit";
    }

    @RequestMapping("/tx/toAdd")
    public String add() {
        return "txAdd";
    }
}
