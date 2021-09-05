package org.dddcommunity.sample.infra.action;

interface ActionHandler<A extends Action, R> {
    R handle(A action);
}
