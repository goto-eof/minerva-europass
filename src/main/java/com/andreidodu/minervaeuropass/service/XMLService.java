package com.andreidodu.minervaeuropass.service;

import java.io.InputStream;
import java.util.Map;

public interface XMLService {
    InputStream createXMLFromMap(Map<String, Object> map);
}
