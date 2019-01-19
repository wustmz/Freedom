package org.mz;

import com.google.gson.Gson;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mz.common.DateTimeUtils;
import org.mz.common.XirrUtils;
import org.mz.entity.FundTx;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    @Test
    public void testAdd (){
        FundTx tx = new FundTx();
        tx.setCreatedTime(System.currentTimeMillis());
        FundTx add = fundTxService.save(tx);
        System.out.println(new Gson().toJson(add));
    }

    @Test
    public void testFindAll (){
        List<FundTx> all = fundTxService.findAll();
        System.out.println(new Gson().toJson(all));
    }

}

