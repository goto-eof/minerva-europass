package com.andreidodu.minervaeuropass.repository;

import com.andreidodu.minervaeuropass.model.Translation;

import java.util.Optional;

public interface TranslationRepository extends TransactionalRepository<Translation, Long> {
    Optional<Translation> findByLanguage_LocalNameIgnoreCaseAndTranslationCode_Code(String language, String code);
}
