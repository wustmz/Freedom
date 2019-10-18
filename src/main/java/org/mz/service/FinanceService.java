package org.mz.service;

import com.baomidou.mybatisplus.service.IService;
import org.mz.entity.Finance;

import java.util.List;

public interface FinanceService extends IService<Finance> {
    List<Finance> list();
}
