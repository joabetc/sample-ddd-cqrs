package org.dddcommunity.sample.infra.cqs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Component
public final class QueryBusImpl implements QueryBus {

    private final QueryRegistry queryRegistry;

    @Override
    public <R> R execute(Query action) {
        var queryHandler = queryRegistry.get(action.getClass());
        return (R) queryHandler.handle(action);
    }
}
