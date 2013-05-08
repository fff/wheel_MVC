package org.thoughtworks.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

public class InterceptServlet extends HttpServlet {

    private HashMap<String, String[]> paramsMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillParamsMap(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillParamsMap(req);
    }

    private void fillParamsMap(HttpServletRequest req) {
        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String) parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);

            paramsMap.put(paramName, paramValues);
        }
    }

    public HashMap<String, String[]> getParamsMap() {
        return paramsMap;
    }
}
