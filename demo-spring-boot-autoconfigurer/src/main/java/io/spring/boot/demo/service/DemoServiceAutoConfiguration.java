package io.spring.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DemoService.class)
// @EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解的类生效
@EnableConfigurationProperties(DemoProperties.class)
public class DemoServiceAutoConfiguration {

    private final DemoProperties demoProperties;

    @Autowired
    public DemoServiceAutoConfiguration(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Bean
    @ConditionalOnMissingBean // 当前Bean不在类路径相匹配
//     当配置文件中demo.service.enabled=true时匹配
    @ConditionalOnProperty(prefix = "demo.service", value = "enabled", havingValue = "true")
    public DemoService demoService() {
        DemoService demoService = new DemoService();
        demoService.setDemoProperties(demoProperties);
        return demoService;
    }


}
