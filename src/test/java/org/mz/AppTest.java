package org.mz;

import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.DateTimeUtils;
import org.mz.common.FundUtil;
import org.mz.common.GsonUtil;
import org.mz.common.XirrUtils;
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

    @Autowired
    private TxService txService;

    @Test
    public void testOp() {
        List<Tx> list = txService.findAll();
        List<Transaction> xirrList = new ArrayList<>();
        Transaction transaction;
        for (Tx tx : list) {
            transaction = new Transaction(tx.getAmount().doubleValue() * (-1), tx.getCreatedTime());
            xirrList.add(transaction);
        }
        String now = DateTimeUtils.getSimpleTime(System.currentTimeMillis());
        xirrList.add(new Transaction(18900, now));
        double xirr = XirrUtils.getXirr(xirrList);
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        System.out.println(nt.format(xirr));
    }

    @Test
    public void testTime() {
        String simpleTime = DateTimeUtils.getSimpleTime(System.currentTimeMillis());
        System.out.println(simpleTime);
    }

    @Test
    public void testAdd() {
        FundTx tx = new FundTx();
        tx.setCreatedTime("");
    }

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


    @Test
    public void testRate() {
        List<Transaction> xirrList = new ArrayList<>();
        Transaction transaction;
        List<FundTx> list = fundTxService.findAll();
        double total = 0;
        double money = 0;
        for (FundTx tx : list) {
            transaction = new Transaction(negate(tx.getAmount()), tx.getCreatedTime());
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
        System.out.println("xirr===" + getRate(xirr));
    }

    private String getRate(double xirr) {
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        return nt.format(xirr);
    }

    private double negate(double amount) {
        return -amount;
    }

    @Test
    public void test1() {
        double v = 1.0089;
        double v1 = 1.8675;
        System.out.println(v * v1);
    }

}

