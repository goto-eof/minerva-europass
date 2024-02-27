package com.andreidodu.minervaeuropass;

public class ThreadContext {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setLocale(String locale) {
        CONTEXT.set(locale);
    }

    public static String getLocale() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
