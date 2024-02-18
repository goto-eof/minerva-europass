package com.andreidodu.iteuropass.service.impl;

import com.andreidodu.iteuropass.dto.ResumeDTO;
import com.andreidodu.iteuropass.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    @Override
    public Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) {
        Map<String, Object> result = new HashMap<>();
        result.put("firstname", resumeDTO.getFirstName());
        result.put("lastname", resumeDTO.getLastName());
        result.put("city", resumeDTO.getCity());
        result.put("county", resumeDTO.getCounty());
        result.put("country", resumeDTO.getCountry());
        result.put("citizenship", StringUtils.join(resumeDTO.getCitizenshipList(), ',').replace(",", ", "));
        result.put("emailList", listToListMap(resumeDTO.getEmailMap()));
        result.put("urlList", listToListMap(resumeDTO.getUrlMap()));
        result.put("phoneNumberList", listToListMap(resumeDTO.getPhoneNumberMap()));
        result.put("birthDate", resumeDTO.getBirthDate().toString());
        return result;
    }

    private List<Map<String, Object>> listToListMap(Map<String, String> listOfMap) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : listOfMap.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key);
            map.put("value", listOfMap.get(key));
            result.add(map);
        }

        return result;
    }
}
