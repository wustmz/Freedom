package org.mz.service;

import org.mz.entity.Tx;

import java.util.List;


public interface TxService {

    void deleteById(int id);

    void save(Tx tx);

    Tx findById(int id);

    List<Tx> findAll();

}
