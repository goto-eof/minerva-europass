package com.andreidodu.minervaeuropass.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Table(name = "me_translation")
@EntityListeners(AuditingEntityListener.class)
public class Translation extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "translation")
    private String translation;

    @Column(name = "locale_name")
    private String localeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "translation_code_id", nullable = false)
    private TranslationCode translationCode;
}
