package com.beebank.monitor.monitorenum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BossIndEnum
 *
 * @author : guo
 * @date : 2020-09-23 13:28
 */
public enum BossIndEnum {


    LEGAL_PERSON("1","法人"),
    HEAD_PERSON("2","负责人");
    // 成员变量
    private String code;
    private String value;
    // 构造方法
    BossIndEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public String getcode() {
        return code;
    }

    public String getValue() {
        return value;
    }
    public static String getValueByCode(String code) {
        for (BossIndEnum e : BossIndEnum.values()) {
            if(e.code.equals(code)) {
                return e.value;
            }
        }
        return null;
    }

    public static List<Map<String, String>> getList(){
        ArrayList<Map<String, String>> lists = new ArrayList<>();
        for (BossIndEnum e : BossIndEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("code",e.code);
            map.put("value",e.value);
            lists.add(map);
        }
        return lists;
    }
}
