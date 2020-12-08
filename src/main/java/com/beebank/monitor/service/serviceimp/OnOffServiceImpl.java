package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.dao.OnOffDao;
import com.beebank.monitor.entity.OnOff;
import com.beebank.monitor.service.OnOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  OnOffServiceImpl
 *
 * @version : 1.0
 * @author	: gqk
 * @date	: 2020年3月1日
 */
@Service
public class OnOffServiceImpl extends BaseServiceImpl<OnOff> implements OnOffService {
    @Autowired
    private OnOffDao onOffDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.onOffDao;
    }
}
