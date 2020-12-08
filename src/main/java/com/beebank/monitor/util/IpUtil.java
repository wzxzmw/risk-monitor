package com.beebank.monitor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IpUtil
 *
 * @author : guo
 * @date : 2020-09-24 13:23
 */
public class IpUtil {
    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    public static String getLocalIp() {
        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();
            logger.info("获取到的ip地址为： {}",ip);
            return ip;

        } catch (UnknownHostException e) {
            logger.error("获取ip地址失败： ",e);
            return null;
        }
    }
}
