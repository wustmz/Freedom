package org.mz.dao;

import org.mz.entity.FundTx;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundTxRepository extends JpaRepository<FundTx, Integer> {
    List<FundTx> findFundTxByCode(String code);
}
