package org.mz;

import com.google.gson.Gson;

import org.decampo.xirr.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.DateTimeUtils;
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
        System.out.println(GsonUtil.toJson(all));
    }

    @Test
    public void test1() {
        System.out.println(new Gson().toJson(new FundTx()));
    }

}

