package org.thoughtworks.orc.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.thoughtworks.orc.OrcController;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;

public class ActionModule extends AbstractModule {
    private final String pkgName;
    private final Map<String, Class<? extends OrcController>> routes = newHashMap();

    public ActionModule(String pkgName) {
        this.pkgName = pkgName;
        final String pkgDirFullName = pkgFullName(pkgDirPath(pkgName));
        asList(new File(pkgDirFullName).listFiles()).stream()
                .forEach(this::initialRoute);
    }

    protected static String pkgDirPath(String name) {
        return name.replaceAll("\\.", "/");
    }

    protected static String pkgFullName(String pkgName) {
        return Thread.currentThread().getContextClassLoader().getResource(pkgName).getFile();
    }

    @Override
    protected void configure() {
        routes.values().stream().forEach(clazz -> bind(clazz));
        bind(String.class).annotatedWith(Names.named("test")).toInstance("test");
        bind(Action.class);
    }

    @Provides
    @Named(Constants.ROUTE_CLAZZ_LIST)
    Map<String, Class<? extends OrcController>> provideRoutesMap() {
        return this.routes;
    }

    protected void initialRoute(File file) {
        final String className = file.getName().split("\\.")[0];
        final String routeName = className.toLowerCase();
        final Class<? extends OrcController> routeClazz = (Class<? extends OrcController>) loadClass(className);
        routes.put(routeName, routeClazz);
    }

    protected Class<?> loadClass(String className) {
        try {
            return this.getClass().getClassLoader().loadClass(this.pkgName + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to load class:" + this.pkgName + "." + className, e);
        }
    }


}
