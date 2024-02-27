package com.andreidodu.minervaeuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CertificateItemDTO implements ExperienceCommon {
    private String name;
    private String description;
    private LocalDate date;
    private String link;
    private List<String> backEndTechnologyList;
    private List<String> frontEndTechnologyList;
}
