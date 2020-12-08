package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.dao.InterfaceRecordRecentDao;
import com.beebank.monitor.entity.InterfaceRecordRecent;
import com.beebank.monitor.service.InterfaceRecordRecentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  InterfaceRecordRecentServiceImpl
 *
 * @version : 1.0
 * @author	: gqk
 * @date	: 2020年3月1日
 */
@Service
public class InterfaceRecordRecentServiceImpl extends BaseServiceImpl<InterfaceRecordRecent> implements InterfaceRecordRecentService {
    @Autowired
    private InterfaceRecordRecentDao interfaceRecordRecentDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.interfaceRecordRecentDao;
    }
}
