package org.mz.dao;

import org.mz.entity.FundTx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundTxRepository extends JpaRepository<FundTx, Integer> {
}
