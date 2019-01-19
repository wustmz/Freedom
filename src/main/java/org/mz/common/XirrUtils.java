package org.mz.common;

import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;

import java.util.Collection;

public class XirrUtils {

    public static double getXirr(Collection<Transaction> txs) {
        return new Xirr(txs).xirr();
    }
}
