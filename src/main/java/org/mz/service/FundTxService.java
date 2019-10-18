package org.mz.service;

import com.baomidou.mybatisplus.service.IService;
import org.mz.entity.FundTx;

import java.util.List;

public interface FundTxService extends IService<FundTx> {
    List<FundTx> findFundTxByCode(String code);

    List<FundTx> list();
}
