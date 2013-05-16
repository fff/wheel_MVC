package org.thoughtworks.orc.internal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.thoughtworks.orc.ViewRender;
import org.thoughtworks.orc.internal.util.Model;
import org.thoughtworks.orc.internal.util.NamePair;
import org.thoughtworks.orc.internal.util.ViewFinder;

@Singleton
public class View {

    private ViewRender render;
    private ViewFinder viewFinder;

    @Inject
    public View(ViewRender render, ViewFinder viewFinder) {
        this.render = render;
        this.viewFinder = viewFinder;
    }

    public String render(Model model, NamePair names) {
        return render.render(viewFinder.readTemplate(names), model);
    }
}
