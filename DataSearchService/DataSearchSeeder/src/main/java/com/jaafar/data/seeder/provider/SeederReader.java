package com.jaafar.data.seeder.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.data.seeder.model.LegalEntityModel;
import com.jaafar.data.seeder.queue.SingletonSeederQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author jaafaree
 * @create 2019/6/18 22:52
 */
@Slf4j
@Component
public class SeederReader {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private SingletonSeederQueue singletonSeederQueue;

    public Future<Long> readFile(String path) throws Exception {
        long count = 0L;
        try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(path))) {
            String fileLineContent;
            while ((fileLineContent = fileBufferReader.readLine()) != null) {
                try {
                    LegalEntityModel legalEntityModel = OBJECT_MAPPER.readValue(fileLineContent, new TypeReference<LegalEntityModel>(){});
                    singletonSeederQueue.offerQueue(legalEntityModel);
                    count ++;
                } catch (IOException e) {
                    log.warn("Deserialize line error", e);
                }
            }
            log.info("File reader processing completed, path: " + path);
        }
        return new AsyncResult<>(count);
    }
}
