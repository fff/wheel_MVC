package org.thoughtworks.orc.internal;

import com.google.inject.Inject;
import org.thoughtworks.orc.internal.util.FormPicker;
import org.thoughtworks.orc.internal.util.HttpContext;
import org.thoughtworks.orc.internal.util.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static java.util.Optional.of;

public class ActualOrcController {
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final HttpSession session;
    protected final Model model = new Model();

    @Inject
    public ActualOrcController() {
        final HttpContext httpContext = HttpContext.current();
        if (httpContext == null) {
            throw new RuntimeException("http context isn't initialed yet.");
        }
        this.request = httpContext.request;
        this.response = httpContext.response;
        this.session = httpContext.session;
    }

    void writeResp(String pageContent) {
        final PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("response getWriter fail", e);
        }
        writer.print(pageContent);
        writer.flush();
        writer.close();
    }

    protected final <T> T form(Class<T> tClass) {
        return FormPicker.pickupObject(this.request, tClass);
    }

    protected final String form(String name) {
        return FormPicker.pickupSingle(this.request, name);
    }

    protected final <T extends Object> void session(String name, T value) {
        session.setAttribute(name, value);
    }

    protected final <T extends Object> Optional<T> session(String name) {
        return (Optional<T>) of(session.getAttribute(name));
    }

    protected final <T extends Object> void model(String name, T value) {
        model.put(name, value);
    }
}
