package org.thoughtworks.orc.internal.util;

import com.google.inject.AbstractModule;

public class UtilModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FormPicker.class);
        bind(ViewFinder.class);
    }
}
