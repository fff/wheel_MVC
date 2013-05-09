package org.thoughtworks.orc.internal.util;


import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;

@Singleton
public class FormPicker {

    public <T> T pickupObject(HttpServletRequest request, Class<T> tClass) {
        try {
            final T obj = tClass.newInstance();
            //TODO fill obj
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("pickObject fail", e);
        }
    }

    public String pickupSingle(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    public String[] pickupSet(HttpServletRequest request, String name) {
        return request.getParameterValues(name);
    }
}
