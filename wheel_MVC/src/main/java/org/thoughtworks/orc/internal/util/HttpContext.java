package org.thoughtworks.orc.internal.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpContext {

    public static ThreadLocal<HttpContext> current = new ThreadLocal<HttpContext>();

    public static HttpContext current() {
        HttpContext c = current.get();
        if (c == null) {
            throw new RuntimeException("There is no HTTP Context available from here.");
        }
        return c;
    }

    public final HttpServletRequest request;
    public final HttpServletResponse response;
    public final HttpSession session;

    public HttpContext(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
    }
}
