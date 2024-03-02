package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OtherSkillsDTO extends SectionCommonDTO{
    private List<String> socialList;
    private List<String> organizationalList;
    private List<String> otherList;
}
