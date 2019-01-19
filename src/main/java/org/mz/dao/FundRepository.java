package org.mz.dao;

import org.mz.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Integer> {
}
