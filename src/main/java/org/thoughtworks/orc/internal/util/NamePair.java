package org.thoughtworks.orc.internal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamePair {
    public final String route;
    public final String action;
    private static Pattern split = Pattern.compile("/(\\w+)/(\\w+)\\b");

    public NamePair(String route, String action) {
        this.route = route;
        this.action = action;
    }

    public static NamePair parseUrl(String url) {
        final Matcher matcher = split.matcher(url);
        if (!matcher.find()) {
            throw new RuntimeException("url is invalid:" + url);
        }
        return new NamePair(matcher.group(1), matcher.group(2));
    }

    @Override
    public String toString() {
        return "[" + route + ", " + action + "]";
    }
}
