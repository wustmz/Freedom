package org.mz.service;

import org.mz.entity.FundTx;

import java.util.List;


public interface FundTxService {

    void deleteById(int id);

    void save(FundTx tx);

    FundTx findById(int id);

    List<FundTx> findAll();

    List<FundTx> findFundTxByCode(String code);

}
