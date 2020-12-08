package com.beebank.monitor.entity;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class RiskMonitorThreadPool {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${riskMonitorThread.core-pool-size}")
    private int corePoolSize;
    @Value("${riskMonitorThread.max-pool-size}")
    private int maxPoolSize;
    @Value("${riskMonitorThread.thread-name-prefix}")
    private String threadNamePrefix;
    @Value("${riskMonitorThread.keep-alive-seconds}")
    private int keepAliveSeconds;
    @Value("${riskMonitorThread.quence-capacity}")
    private int quenceCapacity;
    @Value("${riskMonitorThread.allow-core-thread-timeout}")
    private boolean allowCoreThreadTimeout;

    @Bean(name = "threadPool")
    public ThreadPoolTaskExecutor asyncDefaultThread() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        // 设置为-1时，默认使用当前系统cpu核心数*2 作为核心线程数
        if(corePoolSize == -1) {
            int i = Runtime.getRuntime().availableProcessors();
            logger.info("当前系统cpu核心数为：{}",i);
            //核心线程数
            threadPool.setCorePoolSize(2*i);
        }else {
            threadPool.setCorePoolSize(corePoolSize);
        }

        //最大线程数
        threadPool.setMaxPoolSize(maxPoolSize);
        //线程池队列
        threadPool.setQueueCapacity(quenceCapacity);
        //线程名称前缀
        threadPool.setThreadNamePrefix(threadNamePrefix);
        //是否允许核心线程过期（默认不允许核心线程过期）
        threadPool.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);

        //非核心线程存活时间
        threadPool.setKeepAliveSeconds(keepAliveSeconds);
        //拒绝策略
        /*ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
        ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
        ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
        ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务*/
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 初始化线程池
        threadPool.initialize();

        // 只有当logger级别为info时，打印此日志
        if(logger.isInfoEnabled()) {
            logger.info("自定义线程池： {}", JSON.toJSONString(threadPool));
        }


        return threadPool;

    }
}
