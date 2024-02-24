package com.andreidodu.minervaeuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExperienceDTO {
    private String title;
    private String description;
    private List<ExperienceItemDTO> experienceList;
}
