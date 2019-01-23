package org.mz;

import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.*;
import org.mz.entity.FundTx;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private FundTxService fundTxService;

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
        double total = 0;
        for (FundTx tx : all) {
            double netValue = FundUtil.getCurrentNetValue(tx.getCode());
            double share = tx.getShare();
            total += netValue * share;
        }
        System.out.println(GsonUtil.toJson(total));
    }


    /**
     * 总览
     */
    @Test
    public void testRate() {
        List<Transaction> xirrList = new ArrayList<>();
        Transaction transaction;
        List<FundTx> list = fundTxService.findAll();
        double total = 0;
        double money = 0;
        for (FundTx tx : list) {
            transaction = new Transaction(MathUtil.negate(tx.getAmount()), tx.getCreatedTime());
            xirrList.add(transaction);
            double netValue = FundUtil.getCurrentNetValue(tx.getCode());
            double share = tx.getShare();
            total += netValue * share;
            money += tx.getAmount();
        }
        xirrList.add(new Transaction(total, DateTimeUtils.getSimpleTime(System.currentTimeMillis())));
        double xirr = XirrUtils.getXirr(xirrList);
        System.out.println("money===" + money);
        System.out.println("total===" + total);
        System.out.println("xirr===" + MathUtil.getRate(xirr));
    }

    @Test
    public void test1() {
        List<FundTx> all = fundTxService.findAll();
        Map<String, Object> map = new HashMap<>();
        double amount = 0;
        for (FundTx tx : all) {
            map.put(tx.getCode(), tx.getName());
            double netValue = FundUtil.getCurrentNetValue(tx.getCode());
            double share = tx.getShare();
            amount += netValue * share;
        }


        for (String s : map.keySet()) {
            List<FundTx> txList = fundTxService.findFundTxByCode(s);
            List<Transaction> xirrList = new ArrayList<>();
            Transaction tx;
            double total = 0;
            for (FundTx fundTx : txList) {
                tx = new Transaction(MathUtil.negate(fundTx.getAmount()), fundTx.getCreatedTime());
                xirrList.add(tx);
                double netValue = FundUtil.getCurrentNetValue(fundTx.getCode());
                double share = fundTx.getShare();
                total += netValue * share;
            }
            xirrList.add(new Transaction(total, DateTimeUtils.getSimpleTime(System.currentTimeMillis())));
            double xirr = XirrUtils.getXirr(xirrList);
            System.out.println(
                    "name===" + map.get(s) + ";"
                            + "code===" + s + ";"
                            + "total===" + String.format("%.2f", total) + ";"
                            + "ratio===" + MathUtil.getRate(total / amount) + ";"
                            + "xirr===" + MathUtil.getRate(xirr));
        }
    }

}

