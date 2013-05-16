package org.thoughtworks.orc.internal;

import org.thoughtworks.orc.internal.util.Model;

public interface ViewRender {
    String render(String template, Model model);
}
