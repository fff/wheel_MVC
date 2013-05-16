package org.thoughtworks.orc.internal;

import com.google.inject.AbstractModule;
import org.thoughtworks.orc.internal.util.ViewFinder;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ViewFinder.class);
        bind(ViewRender.class).to(SimpleViewRender.class);
        bind(View.class);
    }
}
