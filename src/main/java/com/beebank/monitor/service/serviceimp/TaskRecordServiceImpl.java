package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.TaskRecordDao;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.monitorconst.StaticConstant;
import com.beebank.monitor.monitorenum.TaskStatusEnum;
import com.beebank.monitor.service.TaskRecordService;
import com.beebank.monitor.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.beebank.monitor.entity.TaskRecord;

import java.sql.Timestamp;
import java.util.Date;

/**
 *  TaskRecordServiceImpl
 */
@Service("taskRecordService")
public class TaskRecordServiceImpl extends BaseServiceImpl<TaskRecord> implements TaskRecordService {


    @Autowired
    private TaskRecordDao taskRecordDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.taskRecordDao;
    }

    @Override
    public void insertTodayInfo(Date nowDate) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskName(StaticConstant.TASK_NAME_INFO);
        taskRecord.setTaskDate(nowDate);
        taskRecord.setStatus(TaskStatusEnum.DO_WORKING.getcode());
        taskRecord.setStatusName(TaskStatusEnum.DO_WORKING.getValue());
        taskRecord.setRemark("开始执行更新总表数据的任务");
        //尝试更新任务记录
        int count = taskRecordDao.updateByNameAndDate(taskRecord);
        //没有可以更新的数则插入
        if (count == 0) taskRecordDao.insert(taskRecord);

    }

    @Override
    public void updateTaskStatus( String code, String value,String remark,Date nowDate) {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setStatus(code);
        taskRecord.setStatusName(value);
        taskRecord.setUpdateTime(Timestamp.valueOf(DateUtil.getNowTime()));
        taskRecord.setRemark(remark);
        taskRecord.setTaskName(StaticConstant.TASK_NAME_INFO);
        taskRecord.setTaskDate(nowDate);
        taskRecordDao.updateByNameAndDate(taskRecord);
    }
}
