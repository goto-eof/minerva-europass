package com.andreidodu.minervaeuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OtherDTO {
    private String title;
    private String description;
    private List<OtherItemDTO> otherList;
}
