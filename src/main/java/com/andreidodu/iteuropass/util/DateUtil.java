package com.andreidodu.iteuropass.util;

import javax.xml.crypto.dsig.spec.XPathType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public final static String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
    public static String formatLocalDate(LocalDate date, String stringPattern) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(stringPattern);
        return date.format(pattern);
    }
}
