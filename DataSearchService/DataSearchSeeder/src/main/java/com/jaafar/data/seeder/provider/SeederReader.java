package com.jaafar.data.seeder.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaafar.data.seeder.model.LegalEntityModel;
import com.jaafar.data.seeder.queue.SingletonSeederQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jaafaree
 * @create 2019/6/18 22:52
 */
@Component
public class SeederReader {

    @Autowired
    private SingletonSeederQueue singletonSeederQueue;

    public void readFile(String path) throws Exception {
        try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(path))) {
            String fileLineContent;
            while ((fileLineContent = fileBufferReader.readLine()) != null) {
                try {
                    LegalEntityModel legalEntityModel = new ObjectMapper().readValue(fileLineContent, new TypeReference<LegalEntityModel>(){});
                    singletonSeederQueue.offerQueque(legalEntityModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
