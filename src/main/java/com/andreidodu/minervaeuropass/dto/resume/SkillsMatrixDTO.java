package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillsMatrixDTO extends SectionCommonDTO{
    private List<SkillMatrixItemDTO> skillsMatrixList;
}
