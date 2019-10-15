package org.mz.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.mz.common.FundUtil;
import org.mz.entity.FinanceDto;
import org.mz.entity.Finance;
import org.mz.entity.FundTx;
import org.mz.entity.Tx;
import org.mz.service.FinanceService;
import org.mz.service.FundTxService;
import org.mz.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private TxService txService;
    @Autowired
    private FundTxService fundTxService;
    @Autowired
    private FinanceService financeService;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/finance/list";
    }


    @GetMapping(value = "/Echars.do")
    public String echarts4(Model model) {
        System.err.println("========开始");
        return "Echars";
    }

    @RequestMapping("/finances")
    @ResponseBody
    public List<FinanceDto> finances() {
        List<Finance> finances = financeService.findAll();
        List<FinanceDto> dtos = new ArrayList<>();
        FinanceDto dto;
        for (Finance finance : finances) {
            String format = DateFormatUtils.format(finance.getUpdatetime(), "yyyy-MM-dd");
            dto = new FinanceDto(format, finance.getTotal().toString());
            dtos.add(dto);
        }
        return dtos;
    }

//    @RequestMapping("/")
//    public String index(Model model) {
//        List<FundTx> all = fundTxService.findAll();
//        Map<String, Object> xirrInfo = XirrUtils.getXirrInfo(all);
//        double amount = (double) xirrInfo.get("total");
//
//        //总价值
//        model.addAttribute("amount", MathUtil.getDouble2(amount));
//        //总投入
//        model.addAttribute("invest", xirrInfo.get("invest"));
//        //总投入年收益率
//        model.addAttribute("totalXirr", xirrInfo.get("xirr"));
//
//        Map<String, Object> map = new HashMap<>();
//        for (FundTx fundTx : all) {
//            map.put(fundTx.getCode(), fundTx.getName());
//        }
//
//        //基金信息汇总
//        List<Map<String, Object>> pages = new ArrayList<>();
//        Map<String, Object> page;
//        for (String s : map.keySet()) {
//            page = new HashMap<>();
//            List<FundTx> txList = fundTxService.findFundTxByCode(s);
//            Map<String, Object> info = XirrUtils.getXirrInfo(txList);
//            page.put("code", s);
//            page.put("name", map.get(s));
//            double total = (double) info.get("total");
//            page.put("total", MathUtil.getDouble2(total));
//            page.put("ratio", MathUtil.getRate(total / amount));
//            page.put("xirr", info.get("xirr"));
//            String xirr = (String) info.get("xirr");
//            if (!xirr.contains("-")) {
//                page.put("color", "red");
//            } else {
//                page.put("color", "green");
//            }
//            pages.add(page);
//        }
//        model.addAttribute("pages", pages);
//        return "index";
//    }

    @RequestMapping("/fundList/{code}")
    public String getFundList(@PathVariable String code, Model model) {
        List<FundTx> txList = fundTxService.findFundTxByCode(code);
        model.addAttribute("txList", txList);
        List<String> list = FundUtil.getCurrentNetValueAndTime(code);
        model.addAttribute("list", list);
        return "txList";
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
