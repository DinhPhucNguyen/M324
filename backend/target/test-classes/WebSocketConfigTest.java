package com.example.websocket_demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WebSocketConfigTest {

    @Mock
    private ChatHandler chatHandler;

    @Mock
    private WebSocketHandlerRegistry registry;

    @InjectMocks
    private WebSocketConfig webSocketConfig;

    @Test
    public void testRegisterWebSocketHandlers() {
        // When
        webSocketConfig.registerWebSocketHandlers(registry);

        // Then
        verify(registry).addHandler(chatHandler, "/ws");
        verify(registry).setAllowedOrigins("*");
    }
}
