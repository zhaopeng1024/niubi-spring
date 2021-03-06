package io.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class Order {

    private Product product;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
