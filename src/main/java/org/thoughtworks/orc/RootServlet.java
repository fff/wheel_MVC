package org.thoughtworks.orc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.thoughtworks.orc.internal.ActionModule;
import org.thoughtworks.orc.internal.util.UtilModule;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootServlet extends HttpServlet {

    Injector injector;

    public static String[] splitURL(HttpServletRequest request) {
        return request.getRequestURI().split("/");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //TODO configuration
        injector = Guice.createInjector(new Application("1", "2"), new ActionModule("3"), new UtilModule());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
