package org.dddcommunity.sample.infra.cqs;

interface Bus<A extends Action> {
    <R> R execute(A action);
}
