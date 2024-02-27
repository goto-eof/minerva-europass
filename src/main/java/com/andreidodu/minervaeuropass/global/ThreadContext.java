package com.andreidodu.minervaeuropass.global;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.dto.RequestContextDTO;

public class ThreadContext {

    private static final ThreadLocal<RequestContextDTO> CONTEXT = (new ThreadLocal<>())
            .withInitial(() -> new RequestContextDTO(ApplicationConst.DEFAULT_LOCALE));

    public static void setRequestContext(RequestContextDTO requestContextDTO) {
        CONTEXT.set(requestContextDTO);
    }

    public static RequestContextDTO getRequestContext() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
