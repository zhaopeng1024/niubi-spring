package io.spring.bean;

import org.springframework.beans.factory.FactoryBean;

public class ProductFactoryBean implements FactoryBean {
    public Object getObject() throws Exception {
        return new Product();
    }

    public Class<?> getObjectType() {
        return Product.class;
    }
}
