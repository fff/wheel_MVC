package org.thoughtworks.orc;

import com.google.inject.Singleton;
import org.thoughtworks.orc.internal.util.Model;

@Singleton
public interface ViewRender {
    String render(String template, Model model);
}
