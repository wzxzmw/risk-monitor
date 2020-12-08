package com.beebank.monitor.dao;
import com.beebank.monitor.entity.TaskRecord;
import org.springframework.stereotype.Repository;

/**
 *  TaskRecordDao
 */
@Repository
public interface TaskRecordDao extends BaseDao<TaskRecord>{


    int updateByNameAndDate(TaskRecord taskRecord);
}
