package org.mz;

import lombok.extern.slf4j.Slf4j;
import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.utils.DateTimeUtils;
import org.mz.common.utils.GsonUtil;
import org.mz.common.utils.MathUtil;
import org.mz.common.utils.XirrUtils;
import org.mz.entity.Finance;
import org.mz.entity.FundTx;
import org.mz.mapper.FundTxMapper;
import org.mz.service.FinanceService;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private FundTxService fundTxService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private FundTxMapper fundTxMapper;


    private BigDecimal getSurplus() {
        BigDecimal cash = BigDecimal.valueOf(20209.38 + 2000 + 4462.38);

        BigDecimal huabei = BigDecimal.valueOf(15000 - 14446.17);
        BigDecimal baitiao = BigDecimal.valueOf(2022.02);
        BigDecimal zhaoshang = BigDecimal.valueOf(3270 + 5551.68);
        BigDecimal zhongxin = BigDecimal.valueOf(6809.94);

        //结余
        return cash.subtract(huabei).subtract(baitiao).subtract(zhaoshang).subtract(zhongxin);
    }


    @Test
    public void testFinanceSave() {
        //资产
        BigDecimal qieman = new BigDecimal("51834.35");
        BigDecimal alipay = new BigDecimal("4462.38");
        BigDecimal wechat = new BigDecimal("89.66");
        BigDecimal bank = new BigDecimal("21009.38");
        BigDecimal stock = new BigDecimal("2040.23");
        BigDecimal dept = new BigDecimal(20000 + 2000);//别人欠我的
        //信用贷
        BigDecimal loan = new BigDecimal(3270 * 3);
        //消费贷
        BigDecimal huabei = BigDecimal.valueOf(15000 - 14446.17);
        BigDecimal baitiao = new BigDecimal("15126.95");
        BigDecimal zhaoshang = BigDecimal.valueOf(32000 - 13403.8);
        BigDecimal zhongxin = BigDecimal.valueOf(27500 - 16408.41);

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
        finance.setSurplus(this.getSurplus());

        BigDecimal total = qieman
                .add(alipay).add(wechat).add(bank).add(stock).add(dept)
                .subtract(loan).subtract(huabei).subtract(baitiao).subtract(zhaoshang).subtract(zhongxin);
        finance.setTotal(total);

        financeService.insertOrUpdate(finance);
        log.info("总计金额: {}", total.toString());
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
        List<FundTx> all = fundTxMapper.findFundTxByCode("000614");
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
        List<FundTx> all = fundTxService.list();
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
        List<FundTx> all = fundTxService.list();
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
            List<FundTx> txList = fundTxMapper.findFundTxByCode(s);
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
        BigDecimal b1 = new BigDecimal(5);
        BigDecimal b2 = new BigDecimal(3);
        System.out.println(b1.subtract(b2));
    }

}

