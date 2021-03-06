package io.spring.bean;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanWiredTest {

    /**
     * 测试bean标签装配bean
     */
    @Test
    public void testTagWired() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }

    /**
     * 测试@Bean注解装配bean
     */
    @Test
    public void testBeanAnnotationWired() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationConfig.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }

    /**
     * 测试ComponentScan扫描装配bean
     */
    @Test
    public void testComponentScan() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Order order = applicationContext.getBean("order", Order.class);
        System.out.println(order);
    }

    /**
     * BeanDefinition 编程式装配Bean
     */
    @Test
    public void testBeanDefinition() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(Product.class);
        // 默认是单例，可以设置成多例bean
        // beanDefinition.setScope("prototype");
        applicationContext.registerBeanDefinition("product", beanDefinition);
        Product product = applicationContext.getBean("product", Product.class);
        System.out.println(product);
        System.out.println(applicationContext.getBean("product", Product.class));
    }

    /**
     * FactoryBean装配Bean
     */
    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        // 工厂Bean装配到IOC容器，会产生两个bean，一个是工厂bean自身，另一个getObject()方法返回的bean
        beanDefinition.setBeanClass(ProductFactoryBean.class);
        applicationContext.registerBeanDefinition("product", beanDefinition);
        Product product = applicationContext.getBean("product", Product.class);
        FactoryBean factoryBean = applicationContext.getBean("&product", ProductFactoryBean.class);
        System.out.println(product);
        System.out.println(factoryBean);
    }

    /**
     * 使用Supplier接口
     */
    @Test
    public void testSupplier() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
        applicationContext.registerBean(Product.class, () -> {
            Product product = new Product();
            product.setName("华为P40");
            return product;
        });
        Product product = applicationContext.getBean("product", Product.class);
        System.out.println(product);
        System.out.println(product.getName());
    }

    /**
     * Import注解导入bean
     */
    @Test
    public void testImport() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
        Level level = applicationContext.getBean("io.spring.bean.Level", Level.class);
        System.out.println(level);
    }

    /**
     * 测试手动注入
     */
    @Test
    public void testManualDI() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user01 = applicationContext.getBean("user01", User.class);
        System.out.println(user01);
        System.out.println(user01.getUsername());
        System.out.println(user01.getPassword());

        User user02 = applicationContext.getBean("user02", User.class);
        System.out.println(user02);
        System.out.println(user02.getUsername());
        System.out.println(user02.getPassword());

    }

}
