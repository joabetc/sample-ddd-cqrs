package org.dddcommunity.sample.infra.action;

interface Bus<A extends Action> {
    <R> R execute(A action);
}
