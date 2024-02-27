package com.andreidodu.minervaeuropass.dto;

import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class TechExperienceDTO {

    private String technology;
    private int years;
    private int months;
    private int totalMonths;

}
