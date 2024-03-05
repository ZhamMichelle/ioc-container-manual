package org.example;

/**
 * @author zhamilya on 3/1/24
 */
public interface ProxyConfigurator {
    Object replaceWithObjectIfNeeded(Object t, Class implClass);
}
