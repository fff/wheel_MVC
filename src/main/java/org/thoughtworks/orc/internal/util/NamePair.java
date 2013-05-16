package org.thoughtworks.orc.internal.util;

public class NamePair {
    public final String route;
    public final String action;

    public NamePair(String route, String action) {
        this.route = route;
        this.action = action;
    }

    @Override
    public String toString() {
        return "[" + route + ", " + action + "]";
    }
}
