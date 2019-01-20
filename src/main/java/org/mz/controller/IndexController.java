package org.mz.controller;

import org.decampo.xirr.Transaction;
import org.mz.common.XirrUtils;
import org.mz.entity.Tx;
import org.mz.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TxService txService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Tx> list = txService.findAll();
        List<Transaction> xirrList = new ArrayList<>();
        Transaction transaction;
        for (Tx tx : list) {
            transaction = new Transaction(tx.getAmount().doubleValue(), tx.getCreatedTime());
            xirrList.add(transaction);
        }
        double xirr = XirrUtils.getXirr(xirrList);
        model.addAttribute("xirr", xirr);
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Tx> list = txService.findAll();
        model.addAttribute("list", list);
        return "list";
    }

    /**
     * 查看详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        Tx tx = txService.findById(id);
        model.addAttribute("tx", tx);
        return "info";
    }

    /**
     * 保存
     *
     * @param tx
     * @return
     */
    @RequestMapping("/save")
    public String saveFund(Tx tx) {
        txService.save(tx);
        return "redirect:/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delById/{id}")
    public String delById(@PathVariable int id) {
        txService.deleteById(id);
        return "list";
    }

    @RequestMapping("/edit/{id}")
    public String editById(@PathVariable int id, Model model) {
        Tx tx = txService.findById(id);
        model.addAttribute("tx", tx);
        return "edit";
    }

    @RequestMapping("/toAdd")
    public String add() {
        return "add";
    }
}
