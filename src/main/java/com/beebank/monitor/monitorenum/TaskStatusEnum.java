package com.beebank.monitor.monitorenum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TaskStatusEnum
 *
 * @author : guo
 * @date : 2020-09-22 11:29
 */
public enum TaskStatusEnum {
    NOT_START("00", "尚未开始"),
    DO_WORKING("10", "正在执行"),
    SUCCEED("20", "执行任务成功"),
    ERROR("30", "执行任务失败");
    // 成员变量
    private String code;
    private String value;
    // 构造方法
    TaskStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public String getcode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static List<Map<String, String>> getList(){
        ArrayList<Map<String, String>> lists = new ArrayList<>();
        for (TaskStatusEnum e : TaskStatusEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("code",e.code);
            map.put("value",e.value);
            lists.add(map);
        }
        return lists;
    }
}
