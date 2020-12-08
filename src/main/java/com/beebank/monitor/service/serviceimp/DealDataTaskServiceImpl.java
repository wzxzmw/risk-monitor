package com.beebank.monitor.service.serviceimp;

import com.beebank.monitor.entity.TaskRecord;
import com.beebank.monitor.monitorconst.StaticConstant;
import com.beebank.monitor.monitorenum.TaskStatusEnum;
import com.beebank.monitor.service.DealDataTaskService;
import com.beebank.monitor.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * DealDataTaskServiceImpl
 *
 * @author : guo
 * @date : 2020-09-21 16:35
 */
@Service
public class DealDataTaskServiceImpl implements DealDataTaskService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //默认休眠等待时间（秒）
    public static int DEFAULT_SLEEP_SECONDS = 30;
    //默认最大等待时间（秒）
    public static int  DEFAULT_MAX_SECONDS = 1800;
    @Autowired
    private CompanyPeopleInfoServiceImpl infoService;
    @Autowired
    private TaskRecordServiceImpl taskRecordService;

    @Autowired
    private SaveDataService saveDataService;

    public static int DEFAULT_COUNT = 10000;
    //每次加载的数据量
    @Value("${company.person.count}")
    private int count;
    //定时任务单次休眠时间
    @Value("${schedule.task.sleepTime}")
    private String sleepTime;
    //定时任务最长休眠时间
    @Value("${schedule.task.maxTime}")
    private String maxTime;
    //执行定时任务的ip后缀
    @Value("${schedule.task.ip_suffix}")
    private String ipSuffix;
    //执行定时任务时间(暂时不用)
    @Value("${schedule.task.cron}")
    private String cron;

    private Date nowDate;

    /**
     * 更新总表数据
     */
    @Override
    @Scheduled(cron = "0 30 3 * * ?")
    public void updateData() {
        nowDate = new Date();
        if(isRigntIp()) {
            try {
                if(coreTaskSuccess()) {
                    long start = System.currentTimeMillis();
                    //新增任务执行记录
                    taskRecordService.insertTodayInfo(nowDate);
                    try {
                        count = count == 0 ? DEFAULT_COUNT : count;
                        //清除总表数据
                        logger.info("开始清除总表数据，{}",nowDate);
                        infoService.truncateTable();
//                        infoService.deleteAll();
                        saveDataService.importData(count);
                        //更新定时任务状态
                        taskRecordService.updateTaskStatus(TaskStatusEnum.SUCCEED.getcode(),TaskStatusEnum.SUCCEED.getValue(),"更新总表数据成功",nowDate);
                        logger.info("总表数据更新完成，定时执行成功！");
                    }catch (Exception e) {
                        taskRecordService.updateTaskStatus(TaskStatusEnum.ERROR.getcode(),TaskStatusEnum.ERROR.getValue(),"更新总表数据失败",nowDate);
                        logger.error("导入总表数据发生异常，定时任务失败！",e);
//                        throw e;
                    }
                    logger.info("本次定时任务执行共计： {} 秒",(System.currentTimeMillis()-start)/1000);

                }
            } catch (InterruptedException e) {
                logger.error("发生异常： ",e);

            }
        }else {
            logger.info("当前主机ip不符合要求，不执行定时任务！");
        }

    }

    /**
     * 判断是否是指定的以 ipSuffix 的地址，是则允许执行定时
     * @Description 由于设计仅有一台服务器执行此定时，根据 ip 是否以 ipSuffix 判断程序是否需要执行此任务
     * @return
     */
    public boolean isRigntIp() {
        return IpUtil.getLocalIp().endsWith(ipSuffix);
    }

    /**
     * 判断定时读取核心文件的任务是否完成，已完成方可继续执行更新主表数据的定时任务;如果正在执行
     * ，则休眠 DEFAULT_SLEEP_SECONDS 秒，最长等待 DEFAULT_MAX_SECONDS 秒，如仍未完成，则
     * 不执行更新总表数据的任务；如果读取核心文件定时执行失败，也不再执行更细总表数据的逻辑
     * @return 读取更新核心文件任务是否成功
     * @throws InterruptedException
     */
    public boolean coreTaskSuccess() throws InterruptedException {
        int tryTimes = 0;
        int realSleepTime = StringUtils.isEmpty(sleepTime) ? DEFAULT_SLEEP_SECONDS : Integer.parseInt(sleepTime);
        int realMaxTime = StringUtils.isEmpty(maxTime) ? DEFAULT_MAX_SECONDS : Integer.parseInt(maxTime);
        //组装查询当天读取核心文件的定时任务执行记录
        TaskRecord record = new TaskRecord();
        record.setTaskName(StaticConstant.TASK_NAME_CORE);
        record.setTaskDate(nowDate);
        long start = System.currentTimeMillis();
        while (realMaxTime >= (System.currentTimeMillis()-start)/1000) {
            List<TaskRecord> taskRecords = taskRecordService.listByObj(record);
            //如果核心定时未执行，则不执行更新总表数据的方法
            if(taskRecords == null || taskRecords.size() == 0) {
                logger.error("未发现读取核心文件定时任务执行记录，当前日期为：{},放弃执行更新总表数据的定时！",nowDate);
                return false;
            }

            //去第一条查询到的记录（TaskName和TaskDate组成唯一索引，理论上不会存在多条记录）
            TaskRecord recordCore = taskRecords.get(0);
            if(recordCore.getStatus().equals(TaskStatusEnum.ERROR.getcode())) {
                logger.error("读取核心文件定时任务执行失败，当前日期为：{},放弃执行更新总表数据的定时！",nowDate);
                return false;
            }else if(recordCore.getStatus().equals(TaskStatusEnum.SUCCEED.getcode())) {
                logger.info("读取核心文件定时任务执行成功，当前日期为：{},开始执行更新总表数据的定时！",nowDate);
                return true;
            }else if(recordCore.getStatus().equals(TaskStatusEnum.DO_WORKING.getcode())) {
                logger.info("读取核心文件定时任务正在执行，当前日期为：{},第 {} 次休眠 {} 秒,等待任务完成！",nowDate,tryTimes,realSleepTime);
                TimeUnit.SECONDS.sleep(realSleepTime);
            }
        }
        long end = System.currentTimeMillis();
        logger.error("当前日期为：{}，累计已等待 读取核心文件 定时任务 {} 秒，不再等待，本次更新总表数据定时不执行，请检查！",nowDate,(end-start)/1000);
        return false;
    }
}
