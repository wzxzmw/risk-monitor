package com.beebank.monitor.entity;

import com.alibaba.fastjson.JSONObject;

public class SoapXML {

    // requestHeader 中的请求头
    private JSONObject requestHeader;
    // <soapenv:Body> 中的请求体
    private JSONObject requestBody;
    // <soapenv:Header/>
    private JSONObject soapHeader;

    public JSONObject getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(JSONObject requestHeader) {
        this.requestHeader = requestHeader;
    }

    public JSONObject getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(JSONObject requestBody) {
        this.requestBody = requestBody;
    }

    public JSONObject getSoapHeader() {
        return soapHeader;
    }

    public void setSoapHeader(JSONObject soapHeader) {
        this.soapHeader = soapHeader;
    }

    public SoapXML(JSONObject requestHeader, JSONObject requestBody, JSONObject soapHeader) {
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.soapHeader = soapHeader;
    }

    public SoapXML() {
    }


}
