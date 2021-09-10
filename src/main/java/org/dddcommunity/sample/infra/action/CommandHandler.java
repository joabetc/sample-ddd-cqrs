package org.dddcommunity.sample.infra.action;

public interface CommandHandler<C extends Command, R> extends ActionHandler<C, R> { }
