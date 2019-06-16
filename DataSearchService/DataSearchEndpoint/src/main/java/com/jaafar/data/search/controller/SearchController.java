package com.jaafar.data.search.controller;

import com.jaafar.data.common.VO.BaseResponse;
import com.jaafar.data.common.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Searching related endpoints
 *
 * @author jaafaree
 * @create 2019/6/16 23:07
 */
@RestController
public class SearchController {

    @GetMapping("/test/{type}")
    public BaseResponse testExceptionHandler(@PathVariable String type) {
        switch (type) {
            case "base":
                throw new BaseBusinessException("test ex");
            case "status":
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "test status ex");
            default:
                throw new RuntimeException("no type error");
        }
    }
}
