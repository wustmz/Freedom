package org.mz.service;

import com.baomidou.mybatisplus.service.IService;
import org.mz.entity.Tx;

import java.util.List;

public interface TxService extends IService<Tx> {
    List<Tx> list();
}
