package org.thoughtworks.orc.internal.util;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.thoughtworks.orc.internal.Constants;

import java.io.File;
import java.util.Map;

@Singleton
public class ViewFinder {

    private final String templateDirPath;
    private final String templateFileSuffix;
    private final Map<String, String> templateFileCache = Maps.newHashMap();

    @Inject
    public ViewFinder(@Named(Constants.VIEW_DIR_PATH) String templateDirPath,
                      @Named(Constants.VIEW_FILE_SUFFIX) String templateFileSuffix) {
        this.templateDirPath = templateDirPath;
        this.templateFileSuffix = templateFileSuffix;
    }

    public String readTemplate(NamePair names) {
        if (!templateFileCache.containsKey(names)) {
            final File templateFile = findTemplateFile(names);
            //TODO read template file to cache
        }
        return templateFileCache.get(names);
    }

    private File findTemplateFile(NamePair names) {
        final File routeDir = new File(templateDirPath + names.route);
        if (!(routeDir.exists() && routeDir.isDirectory())) {
            throw new RuntimeException("no existed for route:" + names);
        }
        final String templateFileName = templateFileName(names);
        final File templateFile = new File(routeDir, templateFileName);
        if (!templateFile.exists()) {
            throw new RuntimeException("no template file exist:/" + names.route + "/" + templateFileName);
        }
        return templateFile;
    }

    private String templateFileName(NamePair names) {
        return names.action + "." + templateFileSuffix;
    }


}
