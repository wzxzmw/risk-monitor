package com.beebank.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = {"com.beebank.monitor.dao"})
@EnableScheduling
@SpringBootApplication
public class RiskMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiskMonitorApplication.class, args);
    }

}
