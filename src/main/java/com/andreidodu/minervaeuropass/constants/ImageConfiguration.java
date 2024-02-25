package com.andreidodu.minervaeuropass.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ImageConfiguration {

    @Value("${com.andreidodu.minerva-europass.image-path}")
    private String imagePath;

}
