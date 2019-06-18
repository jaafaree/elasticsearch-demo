package com.jaafar.data.seeder.queue;

import com.jaafar.data.seeder.model.LegalEntityModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jaafaree
 * @create 2019/6/18 22:31
 */
@Slf4j
@Component
public class SingletonSeederQueue {

    private final static int QUEUE_CAPACITY = 1024;

    private BlockingDeque<LegalEntityModel> seederQueue;

    private SingletonSeederQueue() {
        this.seederQueue = new LinkedBlockingDeque<>(QUEUE_CAPACITY);
    }

    public void offerQueque(LegalEntityModel legalEntityModel) {
        try {
            this.seederQueue.offer(legalEntityModel);
        } catch (Exception e) {
            log.error("offer queue failed, legal entity is: {}", legalEntityModel,  e);
        }
    }

    public LegalEntityModel pop() {
        try {
            return this.seederQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
