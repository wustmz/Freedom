package org.mz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.mz.entity.Tx;
import org.mz.mapper.TxMapper;
import org.mz.service.TxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxServiceImpl extends ServiceImpl<TxMapper, Tx> implements TxService {
    @Override
    public List<Tx> list() {
        return this.list();
    }
}
