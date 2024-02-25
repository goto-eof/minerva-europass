package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.ExperienceDTO;
import com.andreidodu.minervaeuropass.dto.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import org.apache.tomcat.util.buf.StringUtils;

import java.time.LocalDate;
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
                .filter(ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper)
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
                .filter(ExperienceItemDTO::getIsWorkedAsBackEndDeveloper)
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

    public static String toBooleanString(Boolean bool) {
        if (bool == null) {
            return ResumeConst.VALUE_FALSE;
        }
        return bool.toString();
    }

    public static String calculateTimeAgoString(int yearsBetween, int monthsBetween) {
        StringBuilder sb = new StringBuilder();
        if (yearsBetween > 0) {
            if (yearsBetween == 1) {
                sb.append(yearsBetween).append(" " + ResumeConst.VALUE_YEAR + " ");
            } else {
                sb.append(yearsBetween).append(" " + ResumeConst.VALUE_YEARS + " ");
            }
        }
        if (monthsBetween > 0) {
            if (monthsBetween == 1) {
                sb.append(monthsBetween).append(" " + ResumeConst.VALUE_MONTH + " ");
            } else {
                sb.append(monthsBetween).append(" " + ResumeConst.VALUE_MONTHS + " ");
            }
        }
        return sb.toString();
    }


    public static String calculateYearsExperience(ExperienceDTO experienceDTO) {
        Map<String, Boolean> map = new HashMap<>();
        experienceDTO.getExperienceList().forEach(experienceItemDTO -> {
            fillMap(experienceItemDTO.getDateFrom(), experienceItemDTO.getDateTo(), map);
        });
        int size = map.keySet().size();
        int years = size / 12;
        int months = size % 12;
        return getStringOfYearsOfExperience(years, months);
    }

    private static String getStringOfYearsOfExperience(int years, int months) {
        String result = "";
        if (years > 0) {
            String yearsString = " " + years + " " + ResumeConst.VALUE_YEARS;
            if (years == 1) {
                yearsString = years + " " + ResumeConst.VALUE_YEAR;
            }
            result += yearsString;
        }
        if (months > 0) {
            String monthsString = " " + months + " " + ResumeConst.VALUE_MONTHS;
            if (months == 1) {
                monthsString = months + " " + ResumeConst.VALUE_MONTH;
            }
            result += monthsString;
        }
        return result;
    }

    private static void fillMap(LocalDate dateFrom, LocalDate dateTo, Map<String, Boolean> map) {
        LocalDate start = dateFrom;
        if (dateTo == null) {
            dateTo = LocalDate.now();
        }
        while (start.isBefore(dateTo) || isYearAndMonthEquals(dateTo, start)) {
            map.put(start.getYear() + "" + start.getMonth(), true);
            start = start.plusMonths(1);
        }
    }

    private static boolean isYearAndMonthEquals(LocalDate dateTo, LocalDate start) {
        return start.getYear() == dateTo.getYear() && start.getMonth() == dateTo.getMonth();
    }
}
