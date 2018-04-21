package org.dark.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.dark.concurrency.example.threadLocal.RequestHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * @author xiaozefeng
 * @date 2018/4/20 下午5:15
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test(){
        log.info("test");
        Long id = RequestHolder.getId();
        log.info("id:{}",id);
        return "test";
    }

}
