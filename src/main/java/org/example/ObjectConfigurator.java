package org.example;

//Like BeanPostProcessor
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
