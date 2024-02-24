package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EducationDTO {
    private String title;
    private String description;
    private List<EducationItemDTO> educationList;
}
