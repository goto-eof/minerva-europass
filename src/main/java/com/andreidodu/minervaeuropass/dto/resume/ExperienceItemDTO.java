package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class ExperienceItemDTO implements ExperienceCommon {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String jobTitle;
    private String name;
    private String description;
    private String url;
    private List<KeyValueDTO> urlList;
    private String mainActivities;
    private String customer;
    private String sector;
    private List<String> backEndTechnologyList;
    private List<String> frontEndTechnologyList;
    private List<String> toolList;
    private Boolean isWorkedAsBackEndDeveloper;
    private Boolean isWorkedAsFrontEndDeveloper;
    private String workingMethodology;
}
