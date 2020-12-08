package com.beebank.monitor.service;
import com.beebank.monitor.entity.TaskRecord;

import java.util.Date;

/**
 *  TaskRecordService
 */
public interface TaskRecordService extends BaseService<TaskRecord>{

    void insertTodayInfo(Date nowDate);

    void updateTaskStatus( String code, String value,String remark,Date nowDate);
}
