package com.beebank.monitor.monitorenum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 申请类型枚举
 */
public enum InterfaceCodeEnum {
    DATA_QUERY("S05800101BMS1001", "REQ1001","交易数据检索接口"),
    MESSAGE_QUERY("S05800101BMS1002", "REQ1002","报文检索接口"),
    AUTHORIZE_QUERY("S05800101BMS1003", "REQ1003","授权查询接口"),
    AUTHORIZE_RESULT("S05800101BMS1007", "REQ1007","授权结果接口");
    // 对应ESB系统接口编码
    private String ESBcode;
    //黑名单系统接口编码
    private String blackCode;
    private String value;
    // 构造方法
    InterfaceCodeEnum(String ESBcode,String blackCode, String value) {
        this.ESBcode = ESBcode;
        this.blackCode = blackCode;
        this.value = value;
    }
    public String getESBcode() {
        return ESBcode;
    }

    public String getValue() {
        return value;
    }
    public String getBlackCode() {
        return blackCode;
    }

    public static List<Map<String, String>> getList(){
        ArrayList<Map<String, String>> lists = new ArrayList<>();
        for (InterfaceCodeEnum e : InterfaceCodeEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("ESBcode",e.ESBcode);
            map.put("blackCode",e.blackCode);
            map.put("value",e.value);
            lists.add(map);
        }
        return lists;
    }
}
