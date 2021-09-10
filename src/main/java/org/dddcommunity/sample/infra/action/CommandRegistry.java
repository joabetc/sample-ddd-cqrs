package org.dddcommunity.sample.infra.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component
final class CommandRegistry extends ActionRegistry<Command, CommandHandler<Command, ?>, CommandHandlerProvider> {

    @Autowired
    public CommandRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected String[] getBeanNames(ApplicationContext applicationContext) {
        return applicationContext.getBeanNamesForType(CommandHandler.class);
    }

    @Override
    protected CommandHandlerProvider getHandlerProvider(ApplicationContext applicationContext, Class<CommandHandler<Command, ?>> handlerClass) {
        return new CommandHandlerProvider(applicationContext, handlerClass);
    }

    @Override
    protected Class<?>[] getGenerics(Class<CommandHandler<Command, ?>> commandHandlerClass) {
        return GenericTypeResolver.resolveTypeArguments(commandHandlerClass, CommandHandler.class);
    }

    @Override
    protected Class<CommandHandler<Command, ?>> getHandlerClass(ApplicationContext applicationContext, String name) {
        return (Class<CommandHandler<Command, ?>>) applicationContext.getBean(name).getClass();
    }
}
