package org.thoughtworks.orc.internal.util;


import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;

@Singleton
public class FormPicker {

    public static <T> T pickupObject(HttpServletRequest request, Class<T> tClass) {
        try {
            final T obj = tClass.newInstance();
            //TODO fill obj
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("pickObject fail", e);
        }
    }

    public static String pickupSingle(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    public static String[] pickupSet(HttpServletRequest request, String name) {
        return request.getParameterValues(name);
    }
}
