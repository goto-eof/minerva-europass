package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.*;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ResumeUtil {

    final private TranslationService translationService;
    public final int MONTHS_IN_YEAR = 12;

    public String listToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return StringUtils.join(list, ResumeConst.LIST_SEPARATOR.charAt(0))
                .replace(ResumeConst.LIST_SEPARATOR, " " + ResumeConst.LIST_SEPARATOR + " ");
    }

    public List<Map<String, Object>> listToListMap(Map<String, String> listOfMap) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : listOfMap.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put(ResumeConst.FIELD_NAME, key);
            map.put(ResumeConst.FIELD_VALUE, listOfMap.get(key));
            result.add(map);
        }

        return result;
    }

    public Map<String, String> calculateTopRolesByExperience(ResumeDTO resumeDTO) {
        long frontEndRoleCount = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .filter(ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper)
                .count();
        long backEndRoleCount = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .filter(ExperienceItemDTO::getIsWorkedAsBackEndDeveloper)
                .count();

        Map<String, Long> countFrontEndTechnologies = new HashMap<>();
        countFrontEndTechnologies.put(translationService.retrieveTranslation(TranslationConst.KEY_FRONT_END_ROLE, resumeDTO.getLocaleName())
                , frontEndRoleCount);
        countFrontEndTechnologies.put(translationService.retrieveTranslation(TranslationConst.KEY_BACK_END_ROLE, resumeDTO.getLocaleName())
                , backEndRoleCount);
        final Map<String, Long> sortedCountFrontEndTechnologies = sortByValue(countFrontEndTechnologies);
        return sortedCountFrontEndTechnologies
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> this.calculateRoleValue(entry, resumeDTO.getLocaleName())));
    }

    public Map<String, String> calculateTopRolesByPersonalProjects(ResumeDTO resumeDTO) {
        long frontEndRoleCount = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsWorkedAsFrontEndDeveloper()))
                .count();

        long backEndRoleCount = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsWorkedAsBackEndDeveloper()))
                .count();

        Map<String, Long> countRolesMap = new HashMap<>();
        countRolesMap.put(translationService.retrieveTranslation(TranslationConst.KEY_FRONT_END_ROLE, resumeDTO.getLocaleName())
                , frontEndRoleCount);
        countRolesMap.put(translationService.retrieveTranslation(TranslationConst.KEY_BACK_END_ROLE, resumeDTO.getLocaleName())
                , backEndRoleCount);
        final Map<String, Long> sortedCountFrontEndTechnologies = sortByValue(countRolesMap);
        return sortedCountFrontEndTechnologies
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> this.calculateRoleValue(entry, resumeDTO.getLocaleName())));
    }

    private String calculateRoleValue(Map.Entry<String, Long> entry, String locale) {
        long value = entry.getValue();
        if (value == 0 || value == 1) {
            return value + " " + translationService.retrieveTranslation(TranslationConst.KEY_MATCH, locale);
        }
        return value + " " + translationService.retrieveTranslation(TranslationConst.KEY_MATCHES, locale);

    }


    public Map<String, String> calculateYearsExperienceByPersonalProjects(ResumeDTO resumeDTO) {
        String frontEndExperience = calculateYearsExperienceFrontEnd(resumeDTO.getPersonalProjects().getExperienceList(), resumeDTO.getLocaleName());
        String backEndExperience = calculateYearsExperienceBackEnd(resumeDTO.getPersonalProjects().getExperienceList(), resumeDTO.getLocaleName());
        Map<String, String> experienceMap = new HashMap<>();
        experienceMap.put(translationService.retrieveTranslation(TranslationConst.KEY_FRONT_END_EXPERIENCE, resumeDTO.getLocaleName()), frontEndExperience);
        experienceMap.put(translationService.retrieveTranslation(TranslationConst.KEY_BACK_END_EXPERIENCE, resumeDTO.getLocaleName()), backEndExperience);
        return experienceMap;
    }

    public Map<String, String> calculateYearsExperienceByExperience(ResumeDTO resumeDTO) {
        String frontEndExperience = calculateYearsExperienceFrontEnd(resumeDTO.getExperience().getExperienceList(), resumeDTO.getLocaleName());
        String backEndExperience = calculateYearsExperienceBackEnd(resumeDTO.getExperience().getExperienceList(), resumeDTO.getLocaleName());
        Map<String, String> experienceMap = new HashMap<>();
        experienceMap.put(translationService.retrieveTranslation(TranslationConst.KEY_FRONT_END_EXPERIENCE, resumeDTO.getLocaleName()), frontEndExperience);
        experienceMap.put(translationService.retrieveTranslation(TranslationConst.KEY_BACK_END_EXPERIENCE, resumeDTO.getLocaleName()), backEndExperience);
        return experienceMap;
    }

    public List<String> calculateTopXFrequencyFrontEndPersonalProjectsTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> allFrontEndTechnologies = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .filter(ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper)
                .map((ExperienceItemDTO::getFrontEndTechnologyList))
                .toList();
        Map<String, Integer> countFrontEndTechnologies = calculateMap(allFrontEndTechnologies);
        return calculateTopXTechnologies(countFrontEndTechnologies, limit);
    }


    public List<String> calculateTopXFrequencyBackEndPersonalProjectsTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> techList = resumeDTO
                .getPersonalProjects()
                .getExperienceList()
                .stream()
                .map(item -> (ExperienceCommon) item)
                .filter(item -> ((ExperienceItemDTO) item).getIsWorkedAsBackEndDeveloper())
                .map((ExperienceCommon::getBackEndTechnologyList))
                .collect(Collectors.toList());
        return calculateFrequency(techList, limit);
    }

    public List<String> calculateTopXFrequencyBackEndCertificatesTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> techList = resumeDTO
                .getCertificates()
                .getCertificateList()
                .stream()
                .map((ExperienceCommon::getBackEndTechnologyList))
                .collect(Collectors.toList());
        return calculateFrequency(techList, limit);
    }

    public List<String> calculateTopXFrequencyFrontEndCertificatesTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> techList = resumeDTO
                .getCertificates()
                .getCertificateList()
                .stream()
                .map((ExperienceCommon::getFrontEndTechnologyList))
                .collect(Collectors.toList());
        return calculateFrequency(techList, limit);
    }


    public List<String> calculateFrequency(List<List<String>> techList, int limit) {
        Map<String, Integer> countTechnologies = calculateMap(techList);
        return calculateTopXTechnologies(countTechnologies, limit);
    }

    public List<String> calculateTopXFrontEndExperienceTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> allFrontEndTechnologies = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .filter(ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper)
                .map((ExperienceItemDTO::getFrontEndTechnologyList))
                .toList();
        return calculateFrequency(allFrontEndTechnologies, limit);
    }

    public List<String> calculateTopXBackEndExperienceTechnologies(ResumeDTO resumeDTO, int limit) {
        List<List<String>> allBackEndTechnologies = resumeDTO
                .getExperience()
                .getExperienceList()
                .stream()
                .filter(ExperienceItemDTO::getIsWorkedAsBackEndDeveloper)
                .map((ExperienceItemDTO::getBackEndTechnologyList))
                .toList();
        Map<String, Integer> countBackEndTechnologies = calculateMap(allBackEndTechnologies);
        return calculateTopXTechnologies(countBackEndTechnologies, limit);
    }

    private List<String> calculateTopXTechnologies(Map<String, Integer> countBackEndTechnologies, int limit) {
        Map<String, Integer> sortedMap = sortByValue(countBackEndTechnologies);
        return sortedMap.keySet().stream().map(s -> s + " (" + sortedMap.get(s) + ")")
                .limit(limit)
                .collect(Collectors.toList());
    }


    private Map<String, Integer> calculateMap(List<List<String>> items) {
        Map<String, Integer> result = new HashMap<>();

        items.forEach(list -> {

            Optional.ofNullable(list).ifPresent(innerList -> innerList.forEach(item -> {
                result.merge(item.toLowerCase(), 1, Integer::sum);
            }));

        });


        return result;
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public String toBooleanString(Boolean bool) {
        if (bool == null) {
            return ResumeConst.VALUE_FALSE;
        }
        return bool.toString();
    }

    public String calculateTimeAgoString(int yearsBetween, int monthsBetween, String locale) {
        StringBuilder sb = new StringBuilder();
        if (yearsBetween > 0) {
            if (yearsBetween == 1) {
                sb.append(yearsBetween).append(" " + translationService.retrieveTranslation(TranslationConst.KEY_YEAR, locale) + " ");
            } else {
                sb.append(yearsBetween).append(" " + translationService.retrieveTranslation(TranslationConst.KEY_YEARS, locale) + " ");
            }
        }
        if (monthsBetween > 0) {
            if (monthsBetween == 1) {
                sb.append(monthsBetween).append(!sb.isEmpty() ? " " : "").append(translationService.retrieveTranslation(TranslationConst.KEY_MONTH, locale)).append(" ");
            } else {
                sb.append(monthsBetween).append(!sb.isEmpty() ? " " : "").append(translationService.retrieveTranslation(TranslationConst.KEY_MONTHS, locale)).append(" ");
            }
        }
        return sb.toString();
    }


    public String calculateYearsExperienceFrontEndAndBackEnd(ExperienceDTO experienceDTO, String locale) {
        Map<String, Boolean> map = new HashMap<>();
        experienceDTO.getExperienceList().forEach(experienceItemDTO -> {
            fillMap(experienceItemDTO.getDateFrom(), experienceItemDTO.getDateTo(), map);
        });
        return calculateTimeExperience(map, locale);
    }

    public String calculateYearsExperienceFrontEnd(List<ExperienceItemDTO> list, String locale) {
        Map<String, Boolean> map = new HashMap<>();
        list.stream().filter(ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper).forEach(experienceItemDTO -> {
            fillMap(experienceItemDTO.getDateFrom(), experienceItemDTO.getDateTo(), map);
        });
        return calculateTimeExperience(map, locale);
    }

    public String calculateYearsExperienceBackEnd(List<ExperienceItemDTO> list, String locale) {
        Map<String, Boolean> map = new HashMap<>();
        list.stream().filter(ExperienceItemDTO::getIsWorkedAsBackEndDeveloper).forEach(experienceItemDTO -> {
            fillMap(experienceItemDTO.getDateFrom(), experienceItemDTO.getDateTo(), map);
        });
        return calculateTimeExperience(map, locale);
    }

    private String calculateTimeExperience(Map<String, Boolean> map, String locale) {
        int size = map.keySet().size();
        int years = size / MONTHS_IN_YEAR;
        int months = size % MONTHS_IN_YEAR;
        return getStringOfYearsOfExperience(years, months, locale);
    }

    public String getStringOfYearsOfExperience(int years, int months, String locale) {
        String result = "";
        if (years > 0) {
            String yearsString = years + " " + translationService.retrieveTranslation(TranslationConst.KEY_YEARS, locale);
            if (years == 1) {
                yearsString = years + " " + translationService.retrieveTranslation(TranslationConst.KEY_YEAR, locale);
            }
            result += yearsString;
        }
        if (months > 0) {
            String monthsString = (!result.isEmpty() ? " " : "") + months + " " + translationService.retrieveTranslation(TranslationConst.KEY_MONTHS, locale);
            if (months == 1) {
                monthsString = (!result.isEmpty() ? " " : "") + months + " " + translationService.retrieveTranslation(TranslationConst.KEY_MONTH, locale);
            }
            result += monthsString;
        }
        return result;
    }

    private void fillMap(LocalDate dateFrom, LocalDate dateTo, Map<String, Boolean> map) {
        LocalDate start = dateFrom;
        if (dateTo == null) {
            dateTo = LocalDate.now();
        }
        while (start.isBefore(dateTo) || isYearAndMonthEquals(dateTo, start)) {
            map.put(start.getYear() + "" + start.getMonth(), true);
            start = start.plusMonths(1);
        }
    }

    private boolean isYearAndMonthEquals(LocalDate dateTo, LocalDate start) {
        return start.getYear() == dateTo.getYear() && start.getMonth() == dateTo.getMonth();
    }


    public List<String> technologiesToYearsOfExperience(List<ExperienceItemDTO> experienceItemList, ExperienceType type, int limit, String locale) {
        return calculateYearsOfExperiencePerTechnology(experienceItemList, type, limit)
                .map(item -> item.getTechnology() + " (" + this.getStringOfYearsOfExperience(item.getYears(), item.getMonths(), locale) + ")")
                .collect(Collectors.toList());
    }

    public List<String> technologiesToYearsOfExperienceLight(List<ExperienceItemDTO> experienceItemList, ExperienceType type, int limit) {
        return calculateYearsOfExperiencePerTechnology(experienceItemList, type, limit)
                .map(TechExperienceDTO::getTechnology)
                .collect(Collectors.toList());
    }

    private Stream<TechExperienceDTO> calculateYearsOfExperiencePerTechnology(List<ExperienceItemDTO> experienceItemList, ExperienceType type, int limit) {
        return experienceItemList
                .stream()
                .filter(item -> isWorkedAsType(type, item))
                .map(item -> chooseListByDevType(type, item))
                .flatMap(List::stream)
                .collect(Collectors.toSet())
                .stream()
                .map(item -> calculateYearsOfExperienceByTechnology(item, experienceItemList, type))
                .sorted(Comparator.comparing(TechExperienceDTO::getTotalMonths).reversed())
                .limit(limit);
    }

    private List<String> chooseListByDevType(ExperienceType type, ExperienceItemDTO item) {
        return ExperienceType.BACK_END.equals(type) ? item.getBackEndTechnologyList() : item.getFrontEndTechnologyList();
    }

    private TechExperienceDTO calculateYearsOfExperienceByTechnology(String technology, List<ExperienceItemDTO> experienceItemList, ExperienceType type) {
        Map<String, Boolean> map = new HashMap<>();
        experienceItemList.stream()
                .filter(item -> isWorkedAsType(type, item))
                .filter(item -> chooseListByDevType(technology, type, item))
                .forEach(experienceItemDTO -> {
                    fillMap(experienceItemDTO.getDateFrom(), experienceItemDTO.getDateTo(), map);
                });
        int size = map.keySet().size();
        int years = size / MONTHS_IN_YEAR;
        int months = size % MONTHS_IN_YEAR;
        return new TechExperienceDTO(technology, years, months, size);
    }

    private boolean chooseListByDevType(String technology, ExperienceType type, ExperienceItemDTO item) {
        return ExperienceType.BACK_END.equals(type) ? item.getBackEndTechnologyList().contains(technology) : item.getFrontEndTechnologyList().contains(technology);
    }

    private Boolean isWorkedAsType(ExperienceType type, ExperienceItemDTO item) {
        return ExperienceType.BACK_END.equals(type) ? item.getIsWorkedAsBackEndDeveloper() : item.getIsWorkedAsFrontEndDeveloper();
    }
}
