package org.mz.controller;

import com.google.gson.Gson;
import org.mz.entity.FundTx;
import org.mz.service.FundTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private FundTxService fundTxService;

    @GetMapping("/t")
    @ResponseBody
    public String tt() {
        List<FundTx> all = fundTxService.findAll();
        return new Gson().toJson(all);
    }
}
