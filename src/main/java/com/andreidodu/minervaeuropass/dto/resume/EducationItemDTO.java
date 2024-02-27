package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EducationItemDTO {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String name;
    private String description;
}
