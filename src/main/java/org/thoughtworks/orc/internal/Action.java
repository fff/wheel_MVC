package org.thoughtworks.orc.internal;


import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.thoughtworks.orc.OrcController;
import org.thoughtworks.orc.internal.util.NamePair;

import java.util.Map;

public class Action {
    private View view;
    private Map<String, Class<? extends OrcController>> routeClazzList;
    private Injector injector;

    @Inject
    public Action(Injector injector, View view,
                  @Named(Constants.ROUTE_CLAZZ_LIST) Map<String, Class<? extends OrcController>> routeClazzList, @Named("test") String test) {
        this.view = view;
        this.routeClazzList = routeClazzList;
        this.injector = injector;
    }

    public void execute(NamePair names) {
        try {
            ActualOrcController controller = injector.getInstance(routeClazzList.get(names.route));
            controller.getClass().getDeclaredMethod(names.action).invoke(controller);
            controller.writeResp(this.view.render(controller.model, names));
        } catch (Exception e) {
            throw new RuntimeException("action execute fail", e);
        }
    }

}
