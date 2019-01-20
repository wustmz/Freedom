package org.mz.service.impl;

import org.mz.dao.TxRepository;
import org.mz.entity.Tx;
import org.mz.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("txService")
public class TxServiceImpl implements TxService {
    @Autowired
    private TxRepository txRepository;

    @Override
    public void deleteById(int id) {
        txRepository.deleteById(id);
    }

    @Override
    public void save(Tx tx) {
        txRepository.save(tx);
    }

    @Override
    public Tx findById(int id) {
        return txRepository.findById(id).get();
    }

    @Override
    public List<Tx> findAll() {
        Iterable<Tx> all = txRepository.findAll();
        List<Tx> list = new ArrayList<>();
        for (Tx tx : all) {
            list.add(tx);
        }
        return list;
    }

}
