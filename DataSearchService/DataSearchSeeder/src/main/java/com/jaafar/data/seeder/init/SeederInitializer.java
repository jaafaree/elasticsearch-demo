package com.jaafar.data.seeder.init;

import com.jaafar.data.seeder.provider.SeederReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 9/12/2019 3:51 PM
 */
@Slf4j
@Component
public class SeederInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${seeder.dir}")
    private String dir;

    @Autowired
    private SeederReader seederReader;

    @Autowired
    @Qualifier("serviceExecutor")
    private ThreadPoolTaskExecutor threadPool;

    private Map<String, Future> taskMap = new HashMap<>();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Seeder application starting process");
        if (dir == null) {
            log.info("No work dir error, exiting...");
            this.shutdown(applicationReadyEvent);
        }
        startReader(new File(dir));

        try {
            int activeCount = threadPool.getActiveCount();

            while (activeCount > 0) {
                activeCount = threadPool.getActiveCount();
                Thread.sleep(500);
            }

            if (!taskMap.isEmpty()) {
                for (String key : taskMap.keySet()) {
                    if (taskMap.get(key).isDone()) {
                        log.info(key + ":" + taskMap.get(key).get().toString());
                    } else {
                        Thread.sleep(500);
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error("Thread interrupted: " + e);
        } catch (ExecutionException e) {
            log.error("Future task execution error: " + e);
        }

    }

    private void shutdown(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Seeder application shutdown");
        applicationReadyEvent.getApplicationContext().close();
    }

    private void startReader(File file) {
        if (file.isFile()) {
            try {
                Future<Long> task = this.seederReader.readFile(file.getPath());
                taskMap.put(file.getPath(), task);
            } catch (Exception e) {
                log.error("Start reader error, path: " + file.getPath());
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(this::startReader);
            }
        }

    }
}
