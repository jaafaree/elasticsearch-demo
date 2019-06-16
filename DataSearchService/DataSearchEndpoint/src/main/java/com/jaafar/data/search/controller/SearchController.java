package com.jaafar.data.search.controller;

import com.jaafar.data.common.Exception.BaseBusinessException;
import com.jaafar.data.common.VO.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Searching related endpoints
 *
 * @author jaafaree
 * @create 2019/6/16 23:07
 */
@RestController
public class SearchController {

    @GetMapping("/test")
    public BaseResponse test() {
        throw new BaseBusinessException("test ex");
    }
}
