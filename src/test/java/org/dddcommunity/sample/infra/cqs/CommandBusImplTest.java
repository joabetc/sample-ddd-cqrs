package org.dddcommunity.sample.infra.cqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SuppressWarnings("unchecked")
class CommandBusImplTest {

    @InjectMocks
    private CommandBusImpl commandBus;

    @Mock
    private CommandRegistry commandRegistry;

    @Mock
    private Command command;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldGetCommandHandler() {
        mockCommandHandler();

        commandBus.execute(command);

        verify(commandRegistry, times(1)).get(any());
    }

    @Test
    void shouldHandleCommand() {
        final var commandHandler = mockCommandHandler();

        commandBus.execute(command);

        verify(commandHandler, times(1)).handle(any(Command.class));
    }

    @Test
    void shouldReturnResource() {
        final var commandHandler = mockCommandHandler();

        final var object = new Object();
        when(commandHandler.handle(any())).thenReturn(object);

        var result = commandBus.execute(command);

        assertThat(result).isEqualTo(object);
    }

    @SuppressWarnings("rawtypes")
    private CommandHandler mockCommandHandler() {
        final var commandHandler = mock(CommandHandler.class);
        when(commandRegistry.get(any())).thenReturn(commandHandler);

        return commandHandler;
    }
}