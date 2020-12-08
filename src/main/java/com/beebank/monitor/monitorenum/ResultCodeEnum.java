package com.beebank.monitor.monitorenum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举
 */
public enum ResultCodeEnum {
    SUCCESS("0000", "调用成功"),
    SWITCH_OFF_ALL("0001", "总开关关闭"),
    SWITCH_OFF("0002", "渠道开关关闭"),
    VALITDATE_ERROR("1000", "参数非法"),
    NOT_ASSERT("2000", "无法判断"),
    SYSTEM_ERROR("3000", "本系统发生异常");
    // 成员变量
    private String key;
    private String value;
    // 构造方法
    ResultCodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static List<Map<String, String>> getList(){
        ArrayList<Map<String, String>> lists = new ArrayList<>();
        for (ResultCodeEnum e : ResultCodeEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("key",e.key);
            map.put("value",e.value);
            lists.add(map);
        }
        return lists;
    }
}
