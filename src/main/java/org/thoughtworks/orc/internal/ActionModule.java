package org.thoughtworks.orc.internal;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;

public class ActionModule extends AbstractModule {
    private final String pkgName;
    private final Map<String, Class<? extends OrcControllerImpl>> routes = newHashMap();

    public ActionModule(String pkgName) {
        this.pkgName = pkgName;
        final String pkgDirFullName = this.getClass().getClassLoader().getResource(pkgDirPath(pkgName)).getFile();
        asList(new File(pkgDirFullName).listFiles()).stream()
                .forEach(this::initialRoute);
    }

    @Override
    protected void configure() {
        routes.values().stream().forEach(clazz -> bind(clazz));
        bind(Map.class).annotatedWith(Names.named(Constants.ROUTE_CLAZZ_LIST)).toInstance(ImmutableMap.copyOf(routes));
        bind(View.class);
        bind(Action.class);
    }

    private void initialRoute(File file) {
        final String className = file.getName().split("\\.")[0];
        final String routeName = className.toLowerCase();
        final Class<? extends OrcControllerImpl> routeClazz = (Class<? extends OrcControllerImpl>) loadClass(className);
        routes.put(routeName, routeClazz);
    }

    private Class<?> loadClass(String className) {
        try {
            return this.getClass().getClassLoader().loadClass(this.pkgName + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to load class:" + this.pkgName + "." + className, e);
        }
    }

    private String pkgDirPath(String name) {
        return name.replaceAll("\\.", "/");
    }


}
