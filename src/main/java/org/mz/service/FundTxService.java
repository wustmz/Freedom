package org.mz.service;

import org.mz.entity.FundTx;

import java.util.List;


public interface FundTxService {

    void deleteById(int id);

    FundTx save(FundTx tx);

    FundTx findById(int id);

    List<FundTx> findAll();

}
