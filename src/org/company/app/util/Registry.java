package org.company.app.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Registry {
    private final Map<String, Object> values = new HashMap<String, Object>();
    private static Registry instance;

    private Registry() {
    }

    public static Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }

        return instance;
    }

    public Object get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }

        return null;
    }

    public void set(String key, Object value) {
        values.put(key, value);
    }
}
