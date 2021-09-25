package org.dddcommunity.sample.infra.action;

import org.springframework.context.ApplicationContext;

final class QueryHandlerProvider extends ActionHandlerProvider<QueryHandler<Query, ?>> {

    public QueryHandlerProvider(ApplicationContext applicationContext, Class<QueryHandler<Query, ?>> type) {
        super(applicationContext, type);
    }
}
