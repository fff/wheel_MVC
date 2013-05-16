package org.thoughtworks.orc.internal.util;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.thoughtworks.orc.internal.Constants;

import java.io.*;
import java.util.Map;

@Singleton
public class ViewFinder {

    private final String templateDirPath;
    private final String templateFileSuffix;
    private final Map<NamePair, String> templateFileCache = Maps.newHashMap();

    @Inject
    public ViewFinder(@Named(Constants.VIEW_DIR_PATH) String templateDirPath,
                      @Named(Constants.VIEW_FILE_SUFFIX) String templateFileSuffix) {
        this.templateDirPath = templateDirPath;
        this.templateFileSuffix = templateFileSuffix;
    }

    public String readTemplate(NamePair names) {
        if (!templateFileCache.containsKey(names)) {
            this.templateFileCache.put(names, read2String(names));
        }
        return templateFileCache.get(names);
    }

    private String read2String(NamePair names) {
        final String filename = templateDirPath + names.route + "/" + templateFileName(names);
        try {
            final FileInputStream fi = new FileInputStream(new File(filename));
            final InputStreamReader in = new InputStreamReader(fi);
            final BufferedReader reader = new BufferedReader(in);
            String line = reader.readLine();
            StringBuilder content = new StringBuilder();
            while (line != null) {
                content.append(line);
                line = reader.readLine();
            }
            fi.close();
            in.close();
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to read:" + filename);
        }
    }

    private String templateFileName(NamePair names) {
        return names.action + "." + templateFileSuffix;
    }

}
