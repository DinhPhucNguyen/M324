package com.example.websocket_demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ChatHandler verwaltet die WebSocket-Verbindungen und Nachrichtenverarbeitung
 * - Verarbeitet eingehende Nachrichten
 * - Speichert Nachrichten in der Datenbank
 * - Verteilt Nachrichten an alle verbundenen Clients
 */
@Component
public class ChatHandler extends TextWebSocketHandler {
    
    // Logger für Debugging und Fehleranalyse
    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);
    
    // Zugriff auf die Datenbank für Nachrichten
    private final MessageRepository messageRepository;
    
    // Konvertiert zwischen Java-Objekten und JSON
    private final ObjectMapper objectMapper;
    
    // Speichert alle aktiven WebSocket-Verbindungen (Thread-safe)
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // Map für Username zu Session-Zuordnung
    private final Map<String, String> sessionUsernames = new ConcurrentHashMap<>();

    /**
     * Konstruktor für Dependency Injection
     */
    public ChatHandler(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Wird aufgerufen, wenn ein neuer Client eine WebSocket-Verbindung aufbaut
     * - Speichert die neue Session
     * - Sendet initial die Anzahl der Nachrichten
     * - Sendet den kompletten Chat-Verlauf
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Neue WebSocket Verbindung: {}", session.getId());
        sessions.put(session.getId(), session);
        sendMessageHistory(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("WebSocket Verbindung geschlossen: {}", session.getId());
        sessionUsernames.remove(session.getId());
        sessions.remove(session.getId());
        broadcastUserList(); // Aktualisierte Nutzerliste senden
    }

    /**
     * Hauptmethode zur Verarbeitung eingehender Nachrichten
     * - Konvertiert JSON zu Message-Objekt
     * - Speichert in Datenbank
     * - Verteilt an alle verbundenen Clients
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            Message msg = objectMapper.readValue(message.getPayload(), Message.class);
            
            // Username für Session speichern
            sessionUsernames.put(session.getId(), msg.getUsername());
            
            // Aktualisiere die Benutzerliste, wenn ein neuer Benutzername gesetzt wird
            broadcastUserList();
            
            Message savedMsg = messageRepository.save(msg);
            String messageJson = objectMapper.writeValueAsString(Map.of("type", "MESSAGE", "message", savedMsg));
            
            for (WebSocketSession s : sessions.values()) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(messageJson));
                }
            }
        } catch (Exception e) {
            logger.error("Fehler beim Verarbeiten der Nachricht", e);
            throw e;
        }
    }

    /**
     * Sendet die Liste aller aktiven Benutzer an alle Clients
     */
    private void broadcastUserList() throws Exception {
        Set<String> activeUsers = sessionUsernames.values().stream()
            .collect(Collectors.toSet());
        
        String userListJson = objectMapper.writeValueAsString(
            Map.of("type", "USERS", "users", activeUsers)
        );
        
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(userListJson));
            }
        }
    }

    /**
     * Sendet den kompletten Chat-Verlauf an einen Client
     */
    private void sendMessageHistory(WebSocketSession session) throws Exception {
        if (session.isOpen()) {
            List<Message> messages = messageRepository.findAll();
            session.sendMessage(new TextMessage(
                objectMapper.writeValueAsString(Map.of("type", "HISTORY", "messages", messages))
            ));
            logger.info("Nachrichtenverlauf mit {} Nachrichten gesendet an Session {}", 
                messages.size(), session.getId());
        }
    }
} 