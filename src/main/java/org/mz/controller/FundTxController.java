package org.mz.controller;

import org.mz.entity.FundTx;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 86501
 * @Description：
 * @date 2019/1/20
 * @time 13:04
 */
@Controller
@RequestMapping("/tx")
public class FundTxController {
    @Autowired
    private FundTxService fundTxService;

    /**
     * 交易列表
     *
     * @return
     */
    @RequestMapping("/list")
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
    @RequestMapping("/info/{id}")
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
    @RequestMapping("/save")
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
    @RequestMapping("/delById/{id}")
    public String delById(@PathVariable int id) {
        fundTxService.deleteById(id);
        return "txList";
    }

    @RequestMapping("/edit/{id}")
    public String editById(@PathVariable int id, Model model) {
        FundTx tx = fundTxService.findById(id);
        model.addAttribute("tx", tx);
        return "txEdit";
    }

    @RequestMapping("/toAdd")
    public String add() {
        return "txAdd";
    }
}
