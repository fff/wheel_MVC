package org.thoughtworks.orc.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private Map<String, Object> vars = new HashMap<>();

    public Object put(String key, Object value) {
        return vars.put(key, value);
    }

    public Object get(Object key) {
        return vars.get(key);
    }

    public boolean containsKey(Object key) {
        return vars.containsKey(key);
    }

    public Object remove(Object key) {
        return vars.remove(key);
    }

    public void putAll(Map<? extends String, ?> m) {
        vars.putAll(m);
    }

    public void clear() {
        vars.clear();
    }
}