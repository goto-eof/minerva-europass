package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EducationItemDTO {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String name;
    private String description;
}
