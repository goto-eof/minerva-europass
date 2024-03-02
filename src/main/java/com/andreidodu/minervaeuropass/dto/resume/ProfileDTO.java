package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String image;
    private String city;
    private String county;
    private String country;
    private String jobTitle;
    private List<String> citizenshipList;
    private List<KeyValueDTO> emailMap;
    private List<KeyValueDTO> phoneNumberMap;
    private LocalDate birthDate;
    private List<KeyValueDTO> urlMap;
    private List<String> mainSkillList;
    private List<String> languageList;
}
