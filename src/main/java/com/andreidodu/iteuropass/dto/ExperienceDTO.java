package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExperienceDTO {
    private String title;
    private List<ExperienceItemDTO> experienceList;
}
