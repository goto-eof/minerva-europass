package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.List;

public class StringUtil {
    public static String addSpaceIfNecessary(StringBuilder sb) {
        return !sb.isEmpty() ? " " : "";
    }

    public static String addSpaceIfNecessary(String result) {
        return !result.isEmpty() ? " " : "";
    }

    public static String joinList(List<String> list) {
        return StringUtils.join(list, ResumeConst.LIST_SEPARATOR.charAt(0))
                .replace(ResumeConst.LIST_SEPARATOR, " " + ResumeConst.LIST_SEPARATOR + " ");
    }
}
