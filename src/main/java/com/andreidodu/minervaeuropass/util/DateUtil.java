package com.andreidodu.minervaeuropass.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public final static String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
    public final static String PATTERN_MMM_YYYY = "MMM yyyy";

    public static String formatLocalDate(LocalDate date, String stringPattern) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(stringPattern);
        return date.format(pattern);
    }

    public static int calculateYearsOld(LocalDate from, LocalDate to) {
        return from.until(to)
                .getYears();
    }

    public static String calculateDateTo(LocalDate dateTo) {
        if (dateTo == null) {
            return "oggi";
        }
        return DateUtil.formatLocalDate(dateTo, DateUtil.PATTERN_MMM_YYYY);
    }

    public static int calculateMonthsBetween(LocalDate dateFrom, LocalDate dateTo) {
        if (dateTo == null) {
            dateTo = LocalDate.now();
        }
        return dateFrom.until(dateTo).getMonths();
    }

    public static int calculateYearsBetween(LocalDate dateFrom, LocalDate dateTo) {
        if (dateTo == null) {
            dateTo = LocalDate.now();
        }
        return dateFrom.until(dateTo).getYears();
    }
}
