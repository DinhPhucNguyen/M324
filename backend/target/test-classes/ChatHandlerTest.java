package com.example.websocket_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatHandlerTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private WebSocketSession session;

    private ChatHandler chatHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        chatHandler = new ChatHandler(messageRepository, objectMapper);
    }

    @Test
    public void testAfterConnectionEstablished() throws Exception {
        // Given
        when(session.getId()).thenReturn("session-id");

        // When
        chatHandler.afterConnectionEstablished(session);

        // Then
        verify(session, times(1)).getId();
    }

    @Test
    public void testAfterConnectionClosed() throws Exception {
        // Given
        when(session.getId()).thenReturn("session-id");

        // When
        chatHandler.afterConnectionClosed(session, null);

        // Then
        verify(session, times(1)).getId();
    }

    @Test
    public void testHandleTextMessage() throws Exception {
        // Given
        String payload = "{\"type\":\"CHAT\",\"content\":\"Hello\",\"username\":\"testuser\"}";
        TextMessage textMessage = new TextMessage(payload);
        when(session.getId()).thenReturn("session-id");

        Message message = new Message();
        message.setContent("Hello");
        message.setUsername("testuser");
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        List<Message> messages = new ArrayList<>();
        messages.add(message);
        when(messageRepository.findAll()).thenReturn(messages);

        // When
        chatHandler.handleTextMessage(session, textMessage);

        // Then
        verify(messageRepository, times(1)).save(any(Message.class));
    }
}
