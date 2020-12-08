package com.beebank.monitor.service;

import com.alibaba.fastjson.JSONObject;


public interface QueryService {
    JSONObject queryBlackJson(JSONObject requestJson);

    String queryBlackSoap(String soapXml);
}
