package org.thoughtworks.orc;

import com.google.inject.Guice;
import com.google.inject.Injector;
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

public class RootServlet extends HttpServlet {

    Injector injector;

    @Override
    public void init(ServletConfig config) throws ServletException {
        injector = Guice.createInjector(new Application(config.getInitParameter("viewDirPath"), "mst"), new ActionModule(config.getInitParameter("controllerPackageName")), new ViewModule());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpContext.current.set(new HttpContext(req, resp, req.getSession(true)));
        injector.getInstance(org.thoughtworks.orc.internal.Action.class).execute(NamePair.parseUrl(req.getRequestURL().toString()));
    }


}
