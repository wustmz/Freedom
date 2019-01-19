package org.mz.service;

import org.mz.entity.Fund;

import java.util.List;

public interface FundService {
    void deleteById(int id);

    void save(Fund tx);

    Fund findById(int id);

    List<Fund> findAll();
}
