package org.mz.service;

import org.mz.dao.TxRepository;
import org.mz.entity.Tx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxService {

    @Autowired
    private TxRepository txRepository;

    public void deleteById(int id) {
        txRepository.deleteById(id);
    }


    public void save(Tx tx) {
        txRepository.save(tx);
    }


    public Tx findById(int id) {
        return txRepository.findById(id).get();
    }


    public List<Tx> findAll() {
        return txRepository.findAll();
    }

}
