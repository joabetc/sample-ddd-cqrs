package org.dddcommunity.sample.infra.cqs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
abstract class ActionHandlerProvider<H extends ActionHandler<?, ?>> {
    private final ApplicationContext applicationContext;
    private final Class<H> type;

    public H get() {
        return applicationContext.getBean(type);
    }
}
