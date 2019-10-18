package org.mz.controller;

import org.mz.entity.Finance;
import org.mz.entity.FundTx;
import org.mz.entity.Tx;
import org.mz.service.FinanceService;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 86501
 * @Description：
 * @date 2019/1/20
 * @time 13:04
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;


    @RequestMapping("/list")
    public String list(Model model) {
        List<Finance> list = financeService.list();
        model.addAttribute("list", list);
        return "financeList";
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
        Finance finance = financeService.selectById(id);
        model.addAttribute("finance", finance);
        return "financeInfo";
    }

    /**
     * 保存
     *
     * @param finance
     * @return
     */
    @RequestMapping("/save")
    public String save(Finance finance) {
        BigDecimal qieman = finance.getQieman();
        BigDecimal alipay = finance.getAlipay();
        BigDecimal wechat = finance.getWechat();
        BigDecimal bank = finance.getBank();
        BigDecimal stock = finance.getStock();
        BigDecimal dept = finance.getDept();

        BigDecimal loan = finance.getLoan();
        BigDecimal huabei = finance.getHuabei();
        BigDecimal baitiao = finance.getBaitiao();
        BigDecimal zhaoshang = finance.getZhaoshang();
        BigDecimal zhongxin = finance.getZhongxin();

        BigDecimal t1 = qieman.add(alipay).add(wechat).add(bank).add(stock).add(dept);
        System.out.println("t1: " + t1);
        BigDecimal t2 = loan.add(huabei).add(baitiao).add(zhaoshang).add(zhongxin);
        System.out.println("t2: " + t2);
        BigDecimal total = t1.subtract(t2);
        finance.setTotal(total);

        financeService.insertOrUpdate(finance);
        return "redirect:/finance/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delById/{id}")
    public String delById(@PathVariable int id) {
        financeService.deleteById(id);
        return "financeList";
    }

    @RequestMapping("/edit/{id}")
    public String editById(@PathVariable int id, Model model) {
        Finance finance = financeService.selectById(id);
        model.addAttribute("finance", finance);
        return "financeEdit";
    }

    @RequestMapping("/toAdd")
    public String add() {
        return "financeAdd";
    }
}
