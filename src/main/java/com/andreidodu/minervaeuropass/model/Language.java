package com.andreidodu.minervaeuropass.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "me_language")
@EntityListeners(AuditingEntityListener.class)
public class Language extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String language;

    @Column(name = "locale_name")
    private String localName;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Translation> translationList;
}
