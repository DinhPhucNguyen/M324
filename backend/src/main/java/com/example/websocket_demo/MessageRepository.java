package com.example.websocket_demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository-Interface für Datenbankoperationen mit Message-Objekten
 * Erbt von JpaRepository, welches Standard-CRUD-Operationen bereitstellt:
 * - save(Message): Speichert eine neue Nachricht
 * - findAll(): Holt alle Nachrichten
 * - count(): Zählt alle Nachrichten
 * - delete(Message): Löscht eine Nachricht
 * usw.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Durch die Vererbung von JpaRepository<Message, Long> bekommen wir
    // automatisch alle grundlegenden Datenbankoperationen
    
    // Hier könnten bei Bedarf weitere spezielle Abfragen definiert werden, z.B.:
    // List<Message> findByUsername(String username);
    // List<Message> findByCreatedAtAfter(LocalDateTime date);
} 