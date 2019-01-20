package org.mz.dao;

import org.mz.entity.Tx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxRepository extends JpaRepository<Tx, Integer> {
}
