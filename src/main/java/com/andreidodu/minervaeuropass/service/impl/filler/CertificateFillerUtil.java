package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.resume.CertificateItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CertificateFillerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ResumeUtil resumeUtil;
    private final TranslationService translationService;


    public void fillUppCertificate(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getCertificates() != null) {
            result.put(ResumeConst.FIELD_CERTIFICATES_TITLE, resumeDTO.getCertificates().getTitle());
            result.put(ResumeConst.FIELD_CERTIFICATES_DESCRIPTION, resumeDTO.getCertificates().getDescription());
            result.put(ResumeConst.FIELD_CERTIFICATES_LIST, certificatesListToListOfMaps(resumeDTO.getCertificates().getCertificateList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromPersonalProjects(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_CERTIFICATES, res);

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
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, resumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, resumeUtil.listToString(item.getFrontEndTechnologyList()));

            return result;
        }).toList();
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromPersonalProjects(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = resumeUtil.calculateTopXFrequencyBackEndCertificatesTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY,
                this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_BACK_END_TECHNOLOGY, ThreadContext.getRequestContext().getLocale())
        );
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = resumeUtil.calculateTopXFrequencyFrontEndCertificatesTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_FRONT_END_TECHNOLOGY, ThreadContext.getRequestContext().getLocale()));
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }
}
