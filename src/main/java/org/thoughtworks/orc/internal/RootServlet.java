package org.thoughtworks.orc.internal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //TODO init url-mapping
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO dispatch to certain action
        //TODO render with template
        super.service(req, resp);
    }
}
