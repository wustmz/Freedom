package org.mz;

import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.DateTimeUtils;
import org.mz.common.GsonUtil;
import org.mz.common.MathUtil;
import org.mz.common.XirrUtils;
import org.mz.entity.Finance;
import org.mz.entity.FundTx;
import org.mz.service.FinanceService;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private FundTxService fundTxService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testFinanceSave() {

        BigDecimal qieman = new BigDecimal(23322.97);
        BigDecimal alipay = new BigDecimal(1292.62);
        BigDecimal wechat = new BigDecimal(0.57);
        BigDecimal bank = new BigDecimal(15524.42 + 7107.50 - 16000);
        BigDecimal stock = new BigDecimal(22348);
        BigDecimal dept = new BigDecimal(35000);

        BigDecimal loan = new BigDecimal(18000);
        BigDecimal huabei = new BigDecimal(15000 - 14290.89);
        BigDecimal baitiao = new BigDecimal(11965 - 9173);
        BigDecimal zhaoshang = new BigDecimal(20000 - 15165.93);
        BigDecimal zhongxin = new BigDecimal(27500 - 6366.61);

        Finance finance = new Finance();
        finance.setQieman(qieman);
        finance.setAlipay(alipay);
        finance.setWechat(wechat);
        finance.setBank(bank);
        finance.setStock(stock);
        finance.setDept(dept);

        finance.setLoan(loan);
        finance.setHuabei(huabei);
        finance.setBaitiao(baitiao);
        finance.setZhaoshang(zhaoshang);
        finance.setZhongxin(zhongxin);

        BigDecimal total = qieman
                .add(alipay).add(wechat).add(bank).add(stock).add(dept)
                .subtract(loan).subtract(huabei).subtract(baitiao).subtract(zhaoshang).subtract(zhongxin);
        finance.setTotal(total);

        financeService.save(finance);
    }

    @Test
    public void testFinanceUpdate() {

        BigDecimal qieman = new BigDecimal(23322.97);
        BigDecimal alipay = new BigDecimal(1292.62);
        BigDecimal wechat = new BigDecimal(0.57);
        BigDecimal bank = new BigDecimal(15524.42 + 7107.50 - 16000);
        BigDecimal stock = new BigDecimal(22348);
        BigDecimal dept = new BigDecimal(35000);

        BigDecimal loan = new BigDecimal(18000);
        BigDecimal huabei = new BigDecimal(15000 - 14290.89);
        BigDecimal baitiao = new BigDecimal(11965 - 9173);
        BigDecimal zhaoshang = new BigDecimal(20000 - 15165.93);
        BigDecimal zhongxin = new BigDecimal(27500 - 6366.61);

        Finance finance = financeService.findById(1);
        finance.setQieman(qieman);
        finance.setAlipay(alipay);
        finance.setWechat(wechat);
        finance.setBank(bank);
        finance.setStock(stock);
        finance.setDept(dept);

        finance.setLoan(loan);
        finance.setHuabei(huabei);
        finance.setBaitiao(baitiao);
        finance.setZhaoshang(zhaoshang);
        finance.setZhongxin(zhongxin);

        BigDecimal t1 = qieman.add(alipay).add(wechat).add(bank).add(stock).add(dept);
        System.out.println("t1: " + t1);
        BigDecimal t2 = loan.add(huabei).add(baitiao).add(zhaoshang).add(zhongxin);
        System.out.println("t2: " + t2);
        BigDecimal total = t1.subtract(t2);
        finance.setTotal(total);

        financeService.save(finance);
        System.out.println("total: " + financeService.findById(1).getTotal());
    }

    @Test
    public void testFinanceFind() {
//        List<Finance> finances = financeService.findAll();
        Finance finance = financeService.findById(1);
        BigDecimal total = finance.getTotal();
        System.out.println(total.toString());
    }

    @Test
    public void testStock() {
        Map<String, String> vars = new HashMap<>();
        vars.put("gid", "sh510500");
        vars.put("key", "9303093729a121adf70f5562dc5cedc1");

        String url = "http://web.juhe.cn:8080/finance/stock/hs?gid={gid}&key={key}";
        String object = restTemplate.getForObject(url, String.class, vars);
        System.out.println(object);
    }

    @Test
    public void test() {
        List<Transaction> txs = Arrays.asList(
                new Transaction(-300, "2018-01-15"),
                new Transaction(600, "2018-02-23"));
        double xirr = XirrUtils.getXirr(txs);
        System.out.println(xirr);
    }

    @Test
    public void testTime() {
        String simpleTime = DateTimeUtils.getSimpleTime(System.currentTimeMillis());
        System.out.println(simpleTime);
    }

    /**
     * Fund总览
     */
    @Test
    public void testFindAll() {
        List<FundTx> all = fundTxService.findFundTxByCode("000614");
        //份额*最新净值=当前价值；所有基金当前价值相加等于总价值
//        double total = 0;
//        for (FundTx tx : all) {
//            double netValue = FundUtil.getCurrentNetValue(tx.getCode());
//            double share = tx.getShare();
//            total += netValue * share;
//        }
//        System.out.println(GsonUtil.toJson(total));
        System.out.println(GsonUtil.toJson(all));
    }


    /**
     * 总览
     */
    @Test
    public void testRate() {
        List<FundTx> all = fundTxService.findAll();
        Map<String, Object> xirrInfo = XirrUtils.getXirrInfo(all);
        System.out.println("money===" + xirrInfo.get("invest"));
        System.out.println("total===" + xirrInfo.get("total"));
        System.out.println("xirr===" + xirrInfo.get("xirr"));
    }

    /**
     * 总览记录
     */
    @Test
    public void test1() {
        List<FundTx> all = fundTxService.findAll();
        Map<String, Object> xirrInfo = XirrUtils.getXirrInfo(all);
        double amount = (double) xirrInfo.get("total");
        System.out.println("money===" + xirrInfo.get("invest"));
        System.out.println("total===" + MathUtil.getDouble2(amount));
        System.out.println("xirr===" + xirrInfo.get("xirr"));

        Map<String, Object> map = new HashMap<>();
        for (FundTx fundTx : all) {
            map.put(fundTx.getCode(), fundTx.getName());
        }

        //基金信息汇总
        List<Map<String, Object>> pages = new ArrayList<>();
        Map<String, Object> page;
        for (String s : map.keySet()) {
            page = new HashMap<>();
            List<FundTx> txList = fundTxService.findFundTxByCode(s);
            Map<String, Object> info = XirrUtils.getXirrInfo(txList);
            page.put("code", s);
            page.put("name", map.get(s));
            double total = (double) info.get("total");
            page.put("total", MathUtil.getDouble2(total));
            page.put("ratio", MathUtil.getRate(total / amount));
            page.put("xirr", info.get("xirr"));
            pages.add(page);
        }
        System.out.println(GsonUtil.toJson(pages));
    }

    /**
     * 具体交易记录
     */
    @Test
    public void testFundList() {
        List<FundTx> txList = fundTxService.findFundTxByCode("000614");
        System.out.println(GsonUtil.toJson(XirrUtils.getXirrInfo(txList)));
    }

    @Test
    public void testArray() {

    }

}

