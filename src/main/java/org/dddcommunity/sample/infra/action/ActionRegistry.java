package org.dddcommunity.sample.infra.action;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
abstract class ActionRegistry<A extends Action, H extends ActionHandler<A, ?>, P extends ActionHandlerProvider<H>> {
    private final Map<Class<? extends A>, P> handlerProviderMap = new HashMap<>();

    protected ActionRegistry(ApplicationContext applicationContext) {
        var names = getBeanNames(applicationContext);
        for (String name : names) {
            register(applicationContext, name);
        }
    }

    private void register(ApplicationContext applicationContext, String name) {
        var handlerClass = getHandlerClass(applicationContext, name);
        var generics = getGenerics(handlerClass);
        assert generics != null;
        var actionType = (Class<? extends A>) generics[0];
        handlerProviderMap.put(actionType, getHandlerProvider(applicationContext, handlerClass));
    }

    H get(Class<? extends A> commandClass) {
        return handlerProviderMap.get(commandClass).get();
    }

    protected abstract String[] getBeanNames(ApplicationContext applicationContext);

    protected abstract P getHandlerProvider(ApplicationContext applicationContext, Class<H> handlerClass);

    protected abstract Class<?>[] getGenerics(Class<H> handlerClass);

    protected abstract Class<H> getHandlerClass(ApplicationContext applicationContext, String name);
}
