package com.beebank.monitor.service.serviceimp;

import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.dao.CompanyPeopleInfoDao;
import com.beebank.monitor.entity.CompanyPeopleInfo;
import com.beebank.monitor.service.CompanyPeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  CompanyPeopleInfoServiceImpl
 */
@Service("companyPeopleInfoService")
public class CompanyPeopleInfoServiceImpl extends BaseServiceImpl<CompanyPeopleInfo> implements CompanyPeopleInfoService {


    @Autowired
    private CompanyPeopleInfoDao companyPeopleInfoDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.companyPeopleInfoDao;
    }



    @Override
    public void truncateTable() {
        companyPeopleInfoDao.truncateTable();
    }

    @Override
    public void deleteAll() {
        companyPeopleInfoDao.deleteAll();
    }
}
