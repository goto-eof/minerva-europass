package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract
class SectionCommonDTO {
    private String title;
    private String description;
    private Boolean enabled = true;
}
