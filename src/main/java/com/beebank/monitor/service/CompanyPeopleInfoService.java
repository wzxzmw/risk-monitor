package com.beebank.monitor.service;
import com.beebank.monitor.entity.CompanyPeopleInfo;
/**
 *  CompanyPeopleInfoService
 */
public interface CompanyPeopleInfoService extends BaseService<CompanyPeopleInfo>{

    void truncateTable();
    void deleteAll();
}
