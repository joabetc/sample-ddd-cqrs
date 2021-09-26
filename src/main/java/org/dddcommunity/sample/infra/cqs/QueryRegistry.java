package org.dddcommunity.sample.infra.cqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component
final class QueryRegistry extends ActionRegistry<Query, QueryHandler<Query, ?>, QueryHandlerProvider> {

    @Autowired
    public QueryRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected String[] getBeanNames(ApplicationContext applicationContext) {
        return applicationContext.getBeanNamesForType(QueryHandler.class);
    }

    @Override
    protected QueryHandlerProvider getHandlerProvider(ApplicationContext applicationContext, Class<QueryHandler<Query, ?>> queryHandlerClass) {
        return new QueryHandlerProvider(applicationContext, queryHandlerClass);
    }

    protected Class<?>[] getGenerics(Class<QueryHandler<Query, ?>> queryHandlerClass) {
        return GenericTypeResolver.resolveTypeArguments(queryHandlerClass, QueryHandler.class);
    }

    @Override
    protected Class<QueryHandler<Query, ?>> getHandlerClass(ApplicationContext applicationContext, String name) {
        return (Class<QueryHandler<Query, ?>>) applicationContext.getBean(name);
    }
}
