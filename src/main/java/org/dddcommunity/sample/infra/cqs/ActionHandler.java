package org.dddcommunity.sample.infra.cqs;

interface ActionHandler<A extends Action, R> {
    R handle(A action);
}
