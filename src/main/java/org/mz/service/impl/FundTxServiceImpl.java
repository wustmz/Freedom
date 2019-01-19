package org.mz.service.impl;

import org.mz.dao.FundTxRepository;
import org.mz.entity.FundTx;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fundTxService")
public class FundTxServiceImpl implements FundTxService {
    @Autowired
    private FundTxRepository fundTxRepository;

    @Override
    public void deleteById(int id) {
        fundTxRepository.deleteById(id);
    }

    @Override
    public FundTx save(FundTx tx) {
        return fundTxRepository.save(tx);
    }

    @Override
    public FundTx findById(int id) {
        return fundTxRepository.findById(id).get();
    }

    @Override
    public List<FundTx> findAll() {
        Iterable<FundTx> all = fundTxRepository.findAll();
        List<FundTx> list = new ArrayList<>();
        for (FundTx fundTx : all) {
            list.add(fundTx);
        }
        return list;
    }

}
