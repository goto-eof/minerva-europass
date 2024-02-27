package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillsMatrixDTO {
    private String title;
    private String description;
    private List<SkillMatrixItemDTO> skillsMatrixList;
}
