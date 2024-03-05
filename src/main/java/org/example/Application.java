package org.example;

import java.util.Map;

/**
 * @author zhamilya on 2/28/24
 */
public class Application {
    public static ApplicationContext run(String packageScan, Map<Class,Class> ifc2ImplClass){
        JavaConfig config = new JavaConfig(packageScan,ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        //TODO: homework - init all singletons which are not lazy
        context.setObjectFactory(objectFactory);
        return context;
    }
}
