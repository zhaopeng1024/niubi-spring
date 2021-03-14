package io.spring.demo.test;

import io.spring.boot.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoSpringBootTestApplicationTests {

    @Autowired
    DemoService demoService;

    @Test
    void contextLoads() {
        System.out.println(demoService.warp("demo"));
    }

}
