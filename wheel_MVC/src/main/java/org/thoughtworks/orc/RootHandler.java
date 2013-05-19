package org.thoughtworks.orc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.thoughtworks.orc.internal.ActionModule;
import org.thoughtworks.orc.internal.ViewModule;
import org.thoughtworks.orc.internal.util.HttpContext;
import org.thoughtworks.orc.internal.util.NamePair;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootHandler extends HttpServlet {

    Injector injector;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            injector = Guice.createInjector(new Application(config.getInitParameter("viewDirFullPath"), "mst"), new ActionModule(config.getInitParameter("controllerPackageName")), new ViewModule());
            final String userModuleClass = config.getInitParameter("userModuleClassName");
            if (userModuleClass != null) {
                injector = injector.createChildInjector((Module) Class.forName(userModuleClass).newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("initial failed.", e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpContext.current.set(new HttpContext(req, resp, req.getSession(true)));
        injector.getInstance(org.thoughtworks.orc.internal.Action.class).execute(NamePair.parseUrl(req.getRequestURI().toString()));
    }


}
