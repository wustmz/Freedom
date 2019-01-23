package org.mz;

import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.*;
import org.mz.entity.FundTx;
import org.mz.entity.Tx;
import org.mz.service.FundTxService;
import org.mz.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<FundTx> all = fundTxService.findFundTxByCode("000614");
        System.out.println(GsonUtil.toJson(all));
    }

}

