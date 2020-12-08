package com.beebank.monitor.service;


import com.beebank.monitor.entity.InterfaceRecord;

public interface InterfaceRecordService extends BaseService<InterfaceRecord>{
    void saveRecord(String ownref,String own,String branchId,String requestMessage, String returnMessage,  String status, String rlevel, int useTime);
}
