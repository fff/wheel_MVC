package org.thoughtworks.orc.internal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RootServlet extends HttpServlet {

    private HashMap<String, String[]> paramsMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillParamsMap(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillParamsMap(req);
    }

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

    private void fillParamsMap(HttpServletRequest req) {
        while (req.getParameterNames().hasMoreElements()) {
            paramsMap.put((String) req.getParameterNames().nextElement(),
                    req.getParameterValues((String) req.getParameterNames().nextElement()));
        }
    }

    public HashMap<String, String[]> getParamsMap() {
        return paramsMap;
    }
}
