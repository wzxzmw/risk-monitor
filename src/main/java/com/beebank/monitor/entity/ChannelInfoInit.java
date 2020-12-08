package com.beebank.monitor.entity;

import com.beebank.monitor.service.serviceimp.ChannelInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChannelInfoInit {
    public static List<ChannelInfo> channelInfoList = new ArrayList<>();
    private final ChannelInfo channelInfo = new ChannelInfo();

    @Autowired
    private ChannelInfoServiceImpl channelInfoService;


    /**
     * 初始化加载渠道数据信息
     */
    @PostConstruct
    private void initChannelList() {
        if(!channelInfoList.isEmpty())
            channelInfoList.clear();
        channelInfoList = channelInfoService.listByObj(channelInfo);
    }

    /**
     * 清空渠道信息集合
     */
    @PreDestroy
    public void destroy() {
        if(!channelInfoList.isEmpty() )
            channelInfoList.clear();
    }

    /**
     * 根据own获取渠道信息
     * @param own
     * @return
     */
    public ChannelInfo getChannelInfoByOwn(String own) {
        if(!StringUtils.isEmpty(own)){
            for (ChannelInfo info:channelInfoList) {
                if(info.getOwn().equalsIgnoreCase(own))
                    return info;
            }
        }
        return null;
    }

    /**
     * 刷新渠道信息数据
     */
    public void refresh() {
        channelInfoList.clear();
        channelInfoList = channelInfoService.listByObj(channelInfo);
    }

}
