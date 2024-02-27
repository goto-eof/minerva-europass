package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.repository.TranslationRepository;
import com.andreidodu.minervaeuropass.service.TranslationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    final private TranslationRepository translationRepository;

    @Cacheable("translations")
    @Override
    public String retrieveTranslation(final String code, final String language) {
        return this.translationRepository
                .findByLocaleNameAndTranslationCode_Code(language, code)
                .orElseThrow(() -> new ApplicationException("translation not found"))
                .getTranslation();
    }
}
