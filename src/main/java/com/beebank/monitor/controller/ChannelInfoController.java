package com.beebank.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.beebank.monitor.entity.ChannelInfoInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("channel")
public class ChannelInfoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelInfoInit channelInfoInit;

    /**
     * 刷新渠道信息
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public JSONObject refresh() {
        JSONObject json = new JSONObject();
        try {
            channelInfoInit.refresh();
            json.put("result","success");
            json.put("message","刷新渠道信息成功");
        }catch (Exception e) {
            json.put("result","fail");
            json.put("message","刷新渠道信息失败");

        }
        logger.info("返回信息： {}",json);
        return json;

    }
}
