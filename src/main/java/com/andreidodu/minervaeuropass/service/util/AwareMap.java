package com.andreidodu.minervaeuropass.service.util;


import com.andreidodu.minervaeuropass.exception.ApplicationException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AwareMap<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            throw new ApplicationException("key already exists! (k, v) = [" + key + ", " + value + "]");
        }
        if (value == null) {
            throw new ApplicationException("value is null! (k, v) = [" + key + ",  null]");
        }
        map.put(key, value);
    }

    public void putAll(Map<K, V> newMap) {
        newMap.keySet().forEach(key -> {
            this.put(key, newMap.get(key));
        });
    }

    public void putAll(AwareMap<K, V> newMap) {
        newMap.getMap().keySet().forEach(key -> {
            this.put(key, newMap.getMap().get(key));
        });
    }

}
