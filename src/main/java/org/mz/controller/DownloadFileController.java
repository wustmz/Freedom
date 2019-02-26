package org.mz.controller;

import org.mz.common.ExportUtil;
import org.mz.entity.FundTx;
import org.mz.entity.Tx;
import org.mz.service.FundTxService;
import org.mz.service.TxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class DownloadFileController {
    private static final Logger logger = LoggerFactory.getLogger(DownloadFileController.class);
    @Autowired
    private TxService txService;

    @GetMapping("/file")
    public String download(HttpServletResponse response) {
        List<Map<String, Object>> dataList;

        List<Tx> list = txService.findAll();
        String fName = "_log";
        String sTitle = "id,余额,创建时间";
        String mapKey = "id,username,createtDate";
        dataList = new ArrayList<>();
        Map<String, Object> map;
        for (Tx tx : list) {
            map = new HashMap<>(16);
            map.put("id", tx.getId());
            map.put("name", tx.getAmount());
            map.put("createtDate", tx.getCreatedTime());

            dataList.add(map);
        }

        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);
            return null;
        } catch (Exception e) {
            logger.error("生成csv文件失败", e);
        }
        return "failure";
    }
}
