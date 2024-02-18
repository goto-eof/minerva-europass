package com.andreidodu.iteuropass.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResumeDTO {
    private String firstName;
    private String lastName;
    private String city;
    private String county;
    private String country;
    private List<String> citizenshipList;
    private Map<String, String> emailMap;
    private Map<String, String> phoneNumberMap;
    private LocalDate birthDate;
    private Map<String, String> urlMap;
}
