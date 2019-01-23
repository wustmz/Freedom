package org.mz.common;

import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.mz.entity.FundTx;

import java.util.*;

public class XirrUtils {

    public static double getXirr(Collection<Transaction> txs) {
        return new Xirr(txs).xirr();
    }

    public static Map<String, Object> getXirrInfo(List<FundTx> list) {
        String now = DateTimeUtils.getSimpleTime(System.currentTimeMillis());
        List<Transaction> xirrList = new ArrayList<>();
        Transaction tx;
        double total = 0;
        double invest = 0;
        for (FundTx fundTx : list) {
            tx = new Transaction(MathUtil.negate(fundTx.getAmount()), fundTx.getCreatedTime());
            xirrList.add(tx);
            double netValue = FundUtil.getCurrentNetValue(fundTx.getCode());
            double share = fundTx.getShare();
            total += netValue * share;
            invest += fundTx.getAmount();
        }
        xirrList.add(new Transaction(total, now));
        double xirr = XirrUtils.getXirr(xirrList);
        Map<String, Object> map = new HashMap<>();
        map.put("invest", invest);
        map.put("total", total);
        map.put("xirr", MathUtil.getRate(xirr));
        return map;
    }
}
