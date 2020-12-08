package com.beebank.monitor.service.serviceimp;


import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.dao.InterfaceRecordDao;
import com.beebank.monitor.entity.InterfaceRecord;
import com.beebank.monitor.service.InterfaceRecordService;
import com.beebank.monitor.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 *  InterfaceRecordServiceImpl
 */
@Service
public class InterfaceRecordServiceImpl extends BaseServiceImpl<InterfaceRecord> implements InterfaceRecordService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InterfaceRecordDao interfaceRecordDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.interfaceRecordDao;
    }


    @Async("threadPool")
    @Override
    public void saveRecord(String ownref,String own, String branchId, String requestMessage, String returnMessage, String status, String rlevel, int useTime) {
        logger.info("开始组装插入数据库，流水号为： {}",ownref);
        String requestTime = DateUtil.getNowTime();
        InterfaceRecord interfaceRecord = new InterfaceRecord(branchId,ownref,own,requestMessage,returnMessage,requestTime,status,rlevel,useTime);
        interfaceRecordDao.insert(interfaceRecord);
        logger.info("保存调用记录完成！申请流水号为： {}",ownref);
    }

}
