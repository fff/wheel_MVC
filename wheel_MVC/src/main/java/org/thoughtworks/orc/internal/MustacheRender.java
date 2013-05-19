package org.thoughtworks.orc.internal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.thoughtworks.orc.internal.util.Model;
import org.thoughtworks.orc.internal.util.NamePair;
import org.thoughtworks.orc.internal.util.ViewFinder;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

@Singleton
public class MustacheRender implements ViewRender {
    private final ViewFinder viewFinder;
    private final Map<NamePair, Template> templateCache = newHashMap();

    @Inject
    public MustacheRender(ViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    @Override
    public String render(NamePair namePair, Model model) {
        if (!templateCache.containsKey(namePair)) {
            templateCache.put(namePair, Mustache.compiler().compile(viewFinder.readTemplate(namePair)));
        }
        return templateCache.get(namePair).execute(model.asMap());
    }
}
