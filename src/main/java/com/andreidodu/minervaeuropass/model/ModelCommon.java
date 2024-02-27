package com.andreidodu.minervaeuropass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class ModelCommon {

    @JsonIgnore
    @CreatedDate
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected LocalDateTime createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "update_date", updatable = true, insertable = false)
    protected LocalDateTime lastModifiedDate;

    @Version
    private int version;


    @Override
    public String toString() {
        return "ModelCommon{" +
                "createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                '}';
    }
}