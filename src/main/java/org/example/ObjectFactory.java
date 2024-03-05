package org.example;

import lombok.SneakyThrows;

import javax.annotation.Detainted;
import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
    //    private static ObjectFactory ourInstance = new ObjectFactory();
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

//    public static ObjectFactory getInstance() {
//        return ourInstance;
//    }

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        //working off BeanPostProcessor-s
        T t = implClass.getDeclaredConstructor().newInstance();
        for(ObjectConfigurator configurator : configurators){
            configurator.configure(t, context);
        }

        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }

        for (ProxyConfigurator configurator : proxyConfigurators) {
            t = (T) configurator.replaceWithObjectIfNeeded(t, implClass);
        }

        return t;
    }
}
