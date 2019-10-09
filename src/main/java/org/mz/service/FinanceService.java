package org.mz.service;

import org.mz.dao.FinanceRepository;
import org.mz.entity.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository financeRepository;

    public void deleteById(int id) {
        financeRepository.deleteById(id);
    }


    public void save(Finance finance) {
        financeRepository.save(finance);
    }


    public Finance findById(int id) {
        return financeRepository.findById(id).get();
    }


    public List<Finance> findAll() {
        return financeRepository.findAll();
    }
}
