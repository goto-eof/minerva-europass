package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExperienceItemDTO {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String jobTitle;
    private String description;
    private String mainActivities;
    private String customer;
    private String sector;
    private List<String> backEndTechnologyList;
    private List<String> frontEndTechnologyList;
    private List<String> toolList;
}
