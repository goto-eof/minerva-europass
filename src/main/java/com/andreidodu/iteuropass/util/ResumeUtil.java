package com.andreidodu.iteuropass.util;

import com.andreidodu.iteuropass.constants.ResumeConst;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeUtil {
    public static List<Map<String, Object>> listToList(List<String> list) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put(ResumeConst.FIELD_VALUE, item);
            result.add(map);
        }

        return result;
    }

    public static String listToString(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        return StringUtils.join(list, '•')
                .replace("•", " • ");
    }

    public static List<Map<String, Object>> listToListMap(Map<String, String> listOfMap) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : listOfMap.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put(ResumeConst.FIELD_NAME, key);
            map.put(ResumeConst.FIELD_VALUE, listOfMap.get(key));
            result.add(map);
        }

        return result;
    }
}
