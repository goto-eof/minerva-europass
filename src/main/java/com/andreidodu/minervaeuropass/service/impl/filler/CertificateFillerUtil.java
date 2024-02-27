package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.CertificateItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CertificateFillerUtil {

    public void fillUppCertificate(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getCertificates() != null) {
            result.put(ResumeConst.FIELD_CERTIFICATES_TITLE, resumeDTO.getCertificates().getTitle());
            result.put(ResumeConst.FIELD_CERTIFICATES_DESCRIPTION, resumeDTO.getCertificates().getDescription());
            result.put(ResumeConst.FIELD_CERTIFICATES_LIST, certificatesListToListOfMaps(resumeDTO.getCertificates().getCertificateList()));
        }
    }

    private List<Map<String, Object>> certificatesListToListOfMaps(List<CertificateItemDTO> certificateList) {
        certificateList.sort(Comparator.comparing(CertificateItemDTO::getDate).reversed());
        return certificateList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            result.put(ResumeConst.FIELD_DATE, DateUtil.formatLocalDate(item.getDate(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_LINK, item.getLink());
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getFrontEndTechnologyList()));

            return result;
        }).toList();


    }
}
