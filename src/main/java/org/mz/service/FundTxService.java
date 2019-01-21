package org.mz.service;

import org.mz.dao.FundTxRepository;
import org.mz.entity.FundTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundTxService {

    @Autowired
    private FundTxRepository fundTxRepository;

    public void deleteById(int id) {
        fundTxRepository.deleteById(id);
    }

    public void save(FundTx tx) {
        fundTxRepository.save(tx);
    }

    public FundTx findById(int id) {
        return fundTxRepository.findById(id).get();
    }

    public List<FundTx> findAll() {
        Iterable<FundTx> all = fundTxRepository.findAll();
        List<FundTx> list = new ArrayList<>();
        for (FundTx fundTx : all) {
            list.add(fundTx);
        }
        return list;
    }

    public List<FundTx> findFundTxByCode(String code) {
        return fundTxRepository.findFundTxByCode(code);
    }

}
