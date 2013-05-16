package org.thoughtworks.orc.internal;

import com.google.inject.Inject;
import org.thoughtworks.orc.OrcController;
import org.thoughtworks.orc.internal.util.FormPicker;
import org.thoughtworks.orc.internal.util.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static java.util.Optional.of;

public class OrcControllerImpl implements OrcController {
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final FormPicker formPicker;
    protected final HttpSession session;
    protected final Model model = new Model();

    @Inject
    public OrcControllerImpl(FormPicker formPicker, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.formPicker = formPicker;
        session = request.getSession(true);
    }

    @Override
    public <T> T form(Class<T> tClass) {
        return formPicker.pickupObject(this.request, tClass);
    }

    @Override
    public String form(String name) {
        return formPicker.pickupSingle(this.request, name);
    }

    @Override
    public <T extends Object> void session(String name, T value) {
        session.setAttribute(name, value);
    }

    @Override
    public <T extends Object> Optional<T> session(String name) {
        return (Optional<T>) of(session.getAttribute(name));
    }

    @Override
    public <T extends Object> void model(String name, T value) {
        model.put(name, value);
    }

    protected void writeResp(String pageContent) {
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
}
