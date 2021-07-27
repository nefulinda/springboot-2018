package com.nefu.myspringboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(5);
        //设置最大线程数
        executor.setMaxPoolSize(20);
       //设置线程工厂
        // executor.setThreadFactory();
        //配置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //设置线程活跃时间
        executor.setKeepAliveSeconds(60);
        //设置默认线程名
        executor.setThreadNamePrefix("zw");
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //线程初始化
        executor.initialize();
        return executor;
    }

    public  Executor asyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(5);
        //设置最大线程数
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //设置线程存活时间(秒)
        executor.setKeepAliveSeconds(60);
        //所有线程任务完成后关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //线程初始化
        executor.initialize();
        //返回线程对象
        return executor;

    }
}
