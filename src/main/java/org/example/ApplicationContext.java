package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhamilya on 2/25/24
 */
public class ApplicationContext {
    @Setter
    private ObjectFactory objectFactory;
    private Map<Class, Object> cashe = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        if (cashe.containsKey(type)) {
            return (T) cashe.get(type);
        }

        Class<? extends T> implClass = type;
        if (implClass.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = objectFactory.createObject(implClass);
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cashe.put(type, t);
        }
        return t;
    }
}
