package com.beebank.monitor.dao;

import com.beebank.monitor.entity.CompanyPeopleInfo;
import org.springframework.stereotype.Repository;

/**
 *  CompanyPeopleInfoDao
 */
@Repository
public interface CompanyPeopleInfoDao extends BaseDao<CompanyPeopleInfo>{

    void truncateTable();

    void deleteAll();
}
