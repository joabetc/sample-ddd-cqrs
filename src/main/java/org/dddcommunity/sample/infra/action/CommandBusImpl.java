package org.dddcommunity.sample.infra.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Component
public final class CommandBusImpl implements CommandBus {

    private final CommandRegistry commandRegistry;

    @Override
    public <R> R execute(Command action) {
        var commandHandler = commandRegistry.get(action.getClass());
        return (R) commandHandler.handle(action);
    }
}
