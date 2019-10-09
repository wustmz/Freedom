package org.mz.dao;

import org.mz.entity.Finance;
import org.mz.entity.Tx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Integer> {
}
