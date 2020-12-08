package com.beebank.monitor.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beebank.monitor.monitorenum.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ValidateDataUtil {

    public static final String[] VALIDATE_KEY = {"OWN","BRANCHID","OWNREF","OWNTASKDESC","AUTHFLAG","AUTHORG","MSGS"};
    public static final String[] MSG_KEY = {"MSGID","DESC","TYPE"};
    public static final String[] DATA_KEY = {"IDS"};

    private static final Logger logger = LoggerFactory.getLogger(ValidateDataUtil.class);

    /**
     * 校验json中字段是否存在，有错误直接返回错误信息，校验通过返回null
     * @param validateJson
     * @return
     */
    public static String validateCheckRisk(JSONObject validateJson) {
        String result = null;
        try {
            //校验外层必传参数
            for(String field : VALIDATE_KEY) {
                if(!validateJson.containsKey(field) || StringUtils.isEmpty(validateJson.get(field))) {
                    result =  ResultCodeEnum.VALITDATE_ERROR.getValue()+", ["+field+"] 参数不能为空！";
                    break;
                }
            }
            JSONArray msgArray = validateJson.getJSONObject("MSGS").getJSONArray("MSG");
            for (int i = 0; i < msgArray.size(); i++) {
                JSONObject msgJson = msgArray.getJSONObject(i);
                for (String msgField : MSG_KEY) {
                    if(!msgJson.containsKey(msgField) || StringUtils.isEmpty(msgJson.get(msgField))) {
                        result = ResultCodeEnum.VALITDATE_ERROR.getValue()+", ["+msgField+"] 参数不能为空！";
                        break;
                    }
                }
                //如果类型不是 Keys : 关键字，则DATA节点必须为格式良好的json，且其中ids不能为空
                if(!msgJson.getString("TYPE").equalsIgnoreCase("Keys")) {
                    JSONObject dataJSON = msgJson.getJSONObject("DATA");
                    for (String dataField : DATA_KEY) {
                        if(!dataJSON.containsKey(dataField) || StringUtils.isEmpty(dataJSON.get(dataField))) {
                            result = ResultCodeEnum.VALITDATE_ERROR.getValue()+", ["+dataField+"] 参数不能为空！";
                            break;
                        }
                    }
                }
            }


        }catch (Exception e){
            result = ResultCodeEnum.VALITDATE_ERROR.getValue();
        }
        logger.info("参数校验结果为： {}",result);
        return result;
    }
}
