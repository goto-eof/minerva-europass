package com.andreidodu.minervaeuropass.constants;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class ImageConfiguration {

    @Value("${com.andreidodu.minerva-europass.image-path}")
    private String imagePath;

    @PostConstruct
    private void postConstruct() {
        log.info("\n\nimagePath: " + imagePath + "\n");
    }

}
