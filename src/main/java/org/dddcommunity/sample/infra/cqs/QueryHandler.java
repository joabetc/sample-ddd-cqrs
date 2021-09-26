package org.dddcommunity.sample.infra.cqs;

public interface QueryHandler<Q extends Query, R> extends ActionHandler<Q, R> { }
