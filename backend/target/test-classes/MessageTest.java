package com.example.websocket_demo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageCreation() {
        // Given
        Message message = new Message();
        Long id = 1L;
        String content = "Test message";
        String username = "testuser";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        message.setId(id);
        message.setContent(content);
        message.setUsername(username);
        message.setCreatedAt(createdAt);

        // Then
        assertEquals(id, message.getId());
        assertEquals(content, message.getContent());
        assertEquals(username, message.getUsername());
        assertEquals(createdAt, message.getCreatedAt());
    }
}
