package com.jaafar.data.seeder.threadpool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableConfigurationProperties({SeederThreadPoolProperties.class})
@EnableAsync
public class SeederThreadPoolConfig {

    @Autowired
    SeederThreadPoolProperties poolConfig;

    @Bean("serviceExecutor")
    public ThreadPoolTaskExecutor getServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolConfig.getCorePoolSize());
        executor.setMaxPoolSize(poolConfig.getMaxPoolSize());
        executor.setQueueCapacity(poolConfig.getQueueCapacity());
        executor.setKeepAliveSeconds(poolConfig.getKeepAliveTime());
        executor.setThreadNamePrefix(poolConfig.getThreadNamePrefix());

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

}
