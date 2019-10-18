package org.mz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.mz.entity.Finance;
import org.mz.mapper.FinanceMapper;
import org.mz.service.FinanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {

    @Override
    public List<Finance> list() {
        return this.list();
    }
}
