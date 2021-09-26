package org.dddcommunity.sample.infra.cqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.openMocks;

@SuppressWarnings("unchecked")
class QueryBusImplTest {

    @InjectMocks
    private QueryBusImpl queryBus;

    @Mock
    private QueryRegistry queryRegistry;

    @Mock
    private Query query;

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
    void shouldGetQueryHandler() {
        mockQueryHandler();

        queryBus.execute(query);

        verify(queryRegistry, times(1)).get(any());
    }

    @Test
    void shouldHandleQuery() {
        final var queryHandler = mockQueryHandler();

        queryBus.execute(query);

        verify(queryHandler, times(1)).handle(any(Query.class));
    }

    @Test
    void shouldReturnResource() {
        final var queryHandler = mockQueryHandler();

        final var object = new Object();
        when(queryHandler.handle(any())).thenReturn(object);

        var result = queryBus.execute(query);

        assertThat(result).isEqualTo(object);
    }

    @SuppressWarnings("rawtypes")
    private QueryHandler mockQueryHandler() {
        final var queryHandler = mock(QueryHandler.class);
        when(queryRegistry.get(any())).thenReturn(queryHandler);

        return queryHandler;
    }
}