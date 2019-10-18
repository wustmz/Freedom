package org.mz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mz.entity.FundTx;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FundTxMapper extends BaseMapper<FundTx> {

    List<FundTx> findFundTxByCode(@Param("code") String code);
}
