package org.thoughtworks.orc.internal;

import com.google.common.base.Joiner;
import com.google.inject.Singleton;
import org.thoughtworks.orc.internal.util.Model;

import java.util.stream.Stream;

@Singleton
public class SimpleViewRender implements ViewRender {
    @Override
    public String render(String template, Model model) {
        final Stream<String> vars = model.keys().stream().map(k -> (k + "," + model.get(k)));
        return template + ";" + Joiner.on(";").join(vars.iterator());
    }
}
