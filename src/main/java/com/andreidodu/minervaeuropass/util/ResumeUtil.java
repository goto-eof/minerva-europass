package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;

public class ResumeUtil {
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

    public static List<String> calculateTopXFrontEndPersonalProjectsTechnologies(ResumeDTO resumeDTO) {
        List<List<String>> allFrontEndTechnologies = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .map((ExperienceItemDTO::getFrontEndTechnologyList))
                .toList();
        Map<String, Integer> countFrontEndTechnologies = calculateMap(allFrontEndTechnologies);
        return calculateTopXTechnologies(countFrontEndTechnologies, ResumeConst.TOP_X_TECHNOLOGIES);
    }

    public static List<String> calculateTopXBackEndPersonalProjectsTechnologies(ResumeDTO resumeDTO) {
        List<List<String>> allBackEndTechnologies = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .map((ExperienceItemDTO::getBackEndTechnologyList))
                .toList();
        Map<String, Integer> countBackEndTechnologies = calculateMap(allBackEndTechnologies);
        return calculateTopXTechnologies(countBackEndTechnologies, ResumeConst.TOP_X_TECHNOLOGIES);
    }


    public static List<String> calculateTopXFrontEndExperienceTechnologies(ResumeDTO resumeDTO) {
        List<List<String>> allFrontEndTechnologies = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .map((ExperienceItemDTO::getFrontEndTechnologyList))
                .toList();
        Map<String, Integer> countFrontEndTechnologies = calculateMap(allFrontEndTechnologies);
        return calculateTopXTechnologies(countFrontEndTechnologies, ResumeConst.TOP_X_TECHNOLOGIES);
    }

    public static List<String> calculateTopXBackEndExperienceTechnologies(ResumeDTO resumeDTO) {
        List<List<String>> allBackEndTechnologies = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .map((ExperienceItemDTO::getBackEndTechnologyList))
                .toList();
        Map<String, Integer> countBackEndTechnologies = calculateMap(allBackEndTechnologies);
        return calculateTopXTechnologies(countBackEndTechnologies, ResumeConst.TOP_X_TECHNOLOGIES);
    }

    private static List<String> calculateTopXTechnologies(Map<String, Integer> countBackEndTechnologies, int limit) {
        Map<String, Integer> sortedMap = sortByValue(countBackEndTechnologies);
        int i = 0;
        List<String> result = new ArrayList<>();
        for (String s : sortedMap.keySet()) {
            result.add(s + " (" + sortedMap.get(s) + ")");
            i++;
            if (i >= limit) {
                return result;
            }
        }
        return result;
    }


    private static Map<String, Integer> calculateMap(List<List<String>> items) {
        Map<String, Integer> result = new HashMap<>();

        items.forEach(list -> {
            list.forEach(item -> {
                result.merge(item.toLowerCase(), 1, Integer::sum);
            });
        });


        return result;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
