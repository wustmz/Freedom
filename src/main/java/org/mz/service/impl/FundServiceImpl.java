package org.mz.service.impl;

import org.mz.dao.FundRepository;
import org.mz.dao.FundRepository;
import org.mz.entity.Fund;
import org.mz.service.FundService;
import org.mz.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundRepository fundRepository;

    @Override
    public void deleteById(int id) {
        fundRepository.deleteById(id);
    }

    @Override
    public void save(Fund tx) {
        fundRepository.saveAndFlush(tx);
    }

    @Override
    public Fund findById(int id) {
        return fundRepository.findById(id).get();
    }

    @Override
    public List<Fund> findAll() {
        Iterable<Fund> all = fundRepository.findAll();
        List<Fund> list = new ArrayList<>();
        for (Fund fund : all) {
            list.add(fund);
        }
        return list;
    }

}
