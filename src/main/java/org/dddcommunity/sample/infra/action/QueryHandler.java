package org.dddcommunity.sample.infra.action;

public interface QueryHandler<Q extends Query, R> extends ActionHandler<Q, R> { }
