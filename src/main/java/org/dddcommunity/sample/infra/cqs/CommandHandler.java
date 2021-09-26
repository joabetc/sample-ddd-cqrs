package org.dddcommunity.sample.infra.cqs;

public interface CommandHandler<C extends Command, R> extends ActionHandler<C, R> { }
