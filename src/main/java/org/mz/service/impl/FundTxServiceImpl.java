package org.mz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.mz.entity.FundTx;
import org.mz.mapper.FundTxMapper;
import org.mz.service.FundTxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundTxServiceImpl extends ServiceImpl<FundTxMapper, FundTx> implements FundTxService {
    @Override
    public List<FundTx> findFundTxByCode(String code) {
        EntityWrapper<FundTx> ew = new EntityWrapper<>();
        ew.eq("code", code);
        return this.selectList(ew);
    }

    @Override
    public List<FundTx> list() {
        return this.list();
    }
}
