package com.beebank.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.beebank.monitor.service.serviceimp.DealDataTaskServiceImpl;
import com.beebank.monitor.service.serviceimp.QueryServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class QueryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QueryServiceImp queryService;

    @Autowired
    private DealDataTaskServiceImpl dealDataTaskService;

    /**
     * 查询涉恐黑名单--json
     * @param requestJson
     * @return
     */
//    @PostMapping(value = "checkRisk")
    @PostMapping(value = "MCR1001")
    @ResponseBody
    public String checkRiskJson(@RequestBody JSONObject requestJson) {
        String returnMsg = queryService.queryBlackJson(requestJson).toJSONString();
        logger.info("返回报文为： {}",returnMsg);
       return returnMsg;
    }



    /**
     * 查询涉恐黑名单--soap
     * @param soapXml
     * @return
     */
    @PostMapping(value = "MCR1002",produces ="text/plain;charset=GB18030" )
    @ResponseBody
    public String checkRiskSoap(@RequestBody String soapXml) {
        String returnMsg =queryService.queryBlackSoap(soapXml);
        logger.info("返回报文为： {}",returnMsg);
        return returnMsg;
    }

    @PostMapping("testReturn")
    @ResponseBody
    public String testReturn(@RequestBody JSONObject requestJson) {
        String requestJsonStr = requestJson.toJSONString();
        logger.error("收到请求：{} ", requestJsonStr);
        return requestJsonStr;
    }

    @RequestMapping("executeTask")
    @ResponseBody
    public String executeTask() {
        dealDataTaskService.updateData();
        return "定时任务已触发";

    }
}
