package io.spring.boot.demo.service;

/**
 * 提供一个Service，包含一个能够将字符串加上前后缀的方法String wrap(String word)
 */
public class DemoService {

    DemoProperties demoProperties;

    public DemoProperties getDemoProperties() {
        return demoProperties;
    }

    public void setDemoProperties(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    public String warp(String word) {
        return demoProperties.getPrefix()  + word + demoProperties.getSuffix();
    }
}
