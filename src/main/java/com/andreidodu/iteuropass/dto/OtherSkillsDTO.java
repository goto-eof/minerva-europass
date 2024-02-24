package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OtherSkillsDTO {
    private String title;
    private String description;
    private List<String> socialList;
    private List<String> organizationalList;
    private List<String> otherList;
}
