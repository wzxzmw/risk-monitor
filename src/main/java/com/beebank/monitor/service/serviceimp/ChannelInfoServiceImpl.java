package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.ChannelInfoDao;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.entity.ChannelInfo;
import com.beebank.monitor.service.ChannelInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  ChannelInfoServiceImpl
 */
@Service("channelInfoService")
public class ChannelInfoServiceImpl extends BaseServiceImpl<ChannelInfo> implements ChannelInfoService {


    @Autowired
    private ChannelInfoDao channelInfoDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.channelInfoDao;
    }

}
