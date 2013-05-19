package org.thoughtworks.orc.internal;

import org.thoughtworks.orc.internal.util.Model;
import org.thoughtworks.orc.internal.util.NamePair;

public interface ViewRender {
    String render(NamePair namePair, Model model);
}
