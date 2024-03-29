package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.LocaleConst;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DateUtil {

    private final TranslationService translationService;
    public final static String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
    public final static String PATTERN_MMM_YYYY = "MMM yyyy";


    public static String formatLocalDate(LocalDate date, String stringPattern) {
        if (date == null){
            return null;
        }
        Locale locale = calculateLocale(ThreadContext.getRequestContext().getLocale());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(stringPattern, locale);
        return date.format(pattern);
    }

    private static Locale calculateLocale(String locale) {
        if (LocaleConst.LOCALE_IT_IT.equalsIgnoreCase(locale)) {
            return Locale.ITALY;
        }
        return Locale.US;
    }

    public static Integer calculateYearsOld(LocalDate from, LocalDate to) {
        if (from == null){
            return null;
        }
        return from.until(to)
                .getYears();
    }

    public String calculateDateTo(LocalDate dateTo) {
        if (dateTo == null) {
            return translationService.retrieveTranslation(TranslationConst.KEY_TODAY, ThreadContext.getRequestContext().getLocale());
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
