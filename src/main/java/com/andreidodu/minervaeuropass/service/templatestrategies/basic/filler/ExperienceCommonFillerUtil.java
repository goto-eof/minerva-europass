package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.KeyValueDTO;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExperienceCommonFillerUtil {
    private final ResumeUtil resumeUtil;
    private final DateUtil dateUtil;


    public List<Map<String, Object>> experiencesToListMap(List<ExperienceItemDTO> experienceList) {
        return experienceList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, dateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_JOB_TITLE, item.getJobTitle());
            result.put(ResumeConst.FIELD_WORKING_METHODOLOGY, item.getWorkingMethodology());
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_URL, item.getUrl());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            result.put(ResumeConst.FIELD_MAIN_ACTIVITIES, item.getMainActivities());
            result.put(ResumeConst.FIELD_COMPANY, item.getCustomer());
            result.put(ResumeConst.FIELD_SECTOR, item.getSector());
            result.put(ResumeConst.FIELD_IS_WORKED_AS_FRONT_END_DEVELOPER, resumeUtil.toBooleanString(item.getIsWorkedAsFrontEndDeveloper()));
            result.put(ResumeConst.FIELD_IS_WORKED_AS_BACK_END_DEVELOPER, resumeUtil.toBooleanString(item.getIsWorkedAsBackEndDeveloper()));
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, resumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, resumeUtil.listToString(item.getFrontEndTechnologyList()));
            result.put(ResumeConst.FIELD_TOOL_LIST, resumeUtil.listToString(item.getToolList()));
            if (item.getUrlList() != null && !item.getUrlList().isEmpty()) {
                result.put(ResumeConst.FIELD_URL_LIST, urlListToListMap(item.getUrlList()));
            }

            int monthsBetween = DateUtil.calculateMonthsBetween(item.getDateFrom(), item.getDateTo());
            int yearsBetween = DateUtil.calculateYearsBetween(item.getDateFrom(), item.getDateTo());

            result.put(ResumeConst.FIELD_JOB_DURATION, resumeUtil.calculateTimeAgoString(yearsBetween, monthsBetween + 1));

            return result;
        }).toList();
    }

    private static List<Map<String, String>> urlListToListMap(List<KeyValueDTO> urlList) {
        return urlList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put(ResumeConst.FIELD_URL, item.getValue());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getKey());
            return result;
        }).toList();
    }
}
