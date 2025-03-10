package com.example.websocket_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Chat-Anwendung
 * - Startet die Spring Boot Anwendung
 * - Aktiviert automatische Konfiguration
 * - Aktiviert Component-Scanning für alle Spring-Komponenten
 * 
 * @SpringBootApplication kombiniert:
 * - @Configuration: Erlaubt Bean-Definitionen
 * - @EnableAutoConfiguration: Automatische Spring-Konfiguration
 * - @ComponentScan: Sucht nach Spring-Komponenten im Projekt
 */
@SpringBootApplication
public class WebsocketDemoApplication {

    /**
     * Einstiegspunkt der Anwendung
     * Startet den Spring Boot ApplicationContext
     */
    public static void main(String[] args) {
        SpringApplication.run(WebsocketDemoApplication.class, args);
    }
} 