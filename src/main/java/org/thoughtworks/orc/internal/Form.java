package org.thoughtworks.orc.internal;


import javax.servlet.http.HttpServletRequest;

public class Form {

    public <T> T pickupObject(HttpServletRequest request, Class<T> tClass) {
        //TODO
        return null;
    }

    public String pickupSingle(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    public String[] pickupSet(HttpServletRequest request, String name) {
        return request.getParameterValues(name);
    }
}
