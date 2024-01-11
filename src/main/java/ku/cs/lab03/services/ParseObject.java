package ku.cs.lab03.services;

import java.util.HashMap;
import java.util.Map;

public class ParseObject {
    private Map<String, Object> items;
    public ParseObject() {
        items = new HashMap<>();
    }

    public void addItem(String key, Object obj) {
        items.put(key, obj);
    }

    public Object getItem(String key) {
        return items.get(key);
    }

}


