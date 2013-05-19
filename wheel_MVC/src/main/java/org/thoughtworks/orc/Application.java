package org.thoughtworks.orc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.thoughtworks.orc.internal.Constants;

public class Application extends AbstractModule {
    private String viewDirPath = "views/";
    private String viewFileSuffix = "mst";

    public Application(String viewDirPath, String viewFileSuffix) {
        this.viewDirPath = viewDirPath;
        this.viewFileSuffix = viewFileSuffix;
    }

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named(Constants.VIEW_DIR_PATH)).toInstance(this.viewDirPath);
        bind(String.class).annotatedWith(Names.named(Constants.VIEW_FILE_SUFFIX)).toInstance(this.viewFileSuffix);
    }
}
