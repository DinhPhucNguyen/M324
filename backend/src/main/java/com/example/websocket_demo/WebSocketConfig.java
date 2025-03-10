package com.example.websocket_demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Konfigurationsklasse für WebSocket-Verbindungen
 * - Aktiviert WebSocket-Unterstützung (@EnableWebSocket)
 * - Konfiguriert WebSocket-Endpunkte
 * - Definiert Zugriffsregeln
 */
@Configuration  // Markiert diese Klasse als Spring-Konfiguration
@EnableWebSocket  // Aktiviert WebSocket-Funktionalität
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;

    // ChatHandler wird per Dependency Injection injiziert
    public WebSocketConfig(ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }

    /**
     * Registriert den WebSocket-Endpunkt
     * - /chat ist der Pfad, unter dem der WebSocket erreichbar ist
     * - setAllowedOrigins("*") erlaubt Zugriff von allen Domains (CORS)
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/chat")  // Endpunkt: ws://server/chat
               .setAllowedOrigins("*");           // Erlaubt alle Origins (CORS)
    }
} 