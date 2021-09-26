package org.dddcommunity.sample.infra.cqs;

import org.springframework.context.ApplicationContext;

final class CommandHandlerProvider extends ActionHandlerProvider<CommandHandler<Command, ?>> {

    public CommandHandlerProvider(ApplicationContext applicationContext, Class<CommandHandler<Command, ?>> type) {
        super(applicationContext, type);
    }
}
