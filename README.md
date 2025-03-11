Gruppenchat-Projekt

📚 Projektbeschreibung

Ein Echtzeit-Gruppenchat, in dem eine Gruppe von Nutzern miteinander kommunizieren kann. Die Anwendung basiert auf Spring Boot im Backend und JavaScript mit WebSockets im Frontend. Nachrichten werden in einer MySQL-Datenbank gespeichert.

---------------------------------------------------------------------------

🛠️ Projektstruktur

💻 Backend
- Technologie: Spring Boot
- Funktionalität: Verwaltung der WebSocket-Verbindungen & Speicherung der Nachrichten
- Wichtige Komponenten:
    - ChatHandler: Verarbeitung der WebSocket-Nachrichten
    - Message: Entität zur Speicherung von Nachrichten

🌐 Frontend
- Technologie: JavaScript mit WebSockets
- Funktionalität: Bereitstellung der Benutzeroberfläche
- Wichtige Dateien:
    - app.js: WebSocket-Verbindungen & UI-Interaktionen
    - index.html: Grundlegendes HTML-Layout
    - styles.css: Styling der Benutzeroberfläche

🏛️ Datenbank

- Technologie: MySQL
- Struktur: Eine Tabelle messages zur Speicherung der Chat-Nachrichten

📊 Monitoring & Logging

- Grafana: Visualisierung von Metriken & Logs
- Prometheus: Sammlung & Speicherung von Metriken
- Loki: Sammlung & Speicherung von Logs

---------------------------------------------------------------------------

🛠️ Installation & Deployment

✅ Voraussetzungen
- Docker: Installiert und betriebsbereit
- Docker Compose: Zum gleichzeitigen Starten mehrerer Container

✈ Schritt-für-Schritt-Anleitung

1. Projekt herunterladen: Klone das Repository mit git clone <repository-url>

2. Docker starten: Stelle sicher, dass Docker aktiv ist

3. Terminal öffnen und ins Projektverzeichnis wechseln: cd /pfad/zum/projekt

4. Anwendung starten:
docker-compose up -d
(Dadurch startet die Anwendung im Hintergrund.)

5. Zugriff auf die Anwendung:
    - Frontend: http://localhost
    - Grafana Dashboard: http://localhost:3000
      - Anmeldedaten: admin / admin

6. Status überprüfen:
docker-compose ps
(Zeigt den Status aller Container an.)

---------------------------------------------------------------------------

📝 Verwendete Tools

🏰 Entwicklungs- & Projektmanagement-Tools

1. Jira - Projektmanagement & Aufgabenverfolgung

2. GitHub - Versionskontrolle & Quellcode-Hosting

3. GitHub Actions - CI/CD-Pipelines für Automatisierung

4. Feature Branching - Entwicklung neuer Features auf separaten Branches

🔄 Monitoring & Logging

5. Grafana - Visualisierung von Metriken & Logs

6. Loki - Sammlung & Speicherung von Logs

7. Prometheus - Sammlung & Speicherung von Metriken

8. OAuth2-Integration mit GitHub für Grafana-Authentifizierung

🛠️ Entwicklungs- & Test-Tools

9. Node.js - Laufzeitumgebung für das Frontend

10. npm - Paketmanager für JavaScript-Abhängigkeiten

11. JDK 17 - Java Development Kit für das Backend

12. Maven - Build-Tool für das Java-Backend

13. Linting Tools

   - ESLint (JS-Code-Qualität)
   - Prettier (JS-Code-Formatierung)
   - Stylelint (CSS-Formatierung)
   - Checkstyle (Java-Code-Qualität)

14. Testing-Frameworks

   - Test (Frontend-Tests)
   - JUnit (Backend-Tests)
   - Mockito (Mocking für Integrationstests)

🛢 Docker-Setup

 Container & Ports


- Name:          - Container:          - Port:

- m324         - DB-1              - Kein Port

- loki-1       - Loki              - 3100

- backend-1    - Backend           - 8080

- prometheus-1 - Prometheus        - 9090

- frontend-1   - Frontend          - 80

- grafana-1    - Grafana           - 3000



---------------------------------------------------------------------------

⚙ CI/CD Pipeline

Ablauf: install → build → linting, tests (parallel) → deploy

1. Install
-Was passiert? Alle nötigen Abhängigkeiten und Tools werden installiert.
-Warum hier? Ohne die Installation können wir den Build-Prozess und Tests nicht durchführen.
2. Build
-Was passiert? Der Code wird kompiliert und Artefakte erstellt.
-Warum hier? Wir stellen sicher, dass der Code korrekt gebaut wird und keine grundlegenden Fehler auftreten.
3. Linting
-Was passiert? Der Code wird auf Stil- und Syntaxfehler überprüft.
-Warum nach dem Build? Wir prüfen nur den funktionierenden Code, der erfolgreich kompiliert wurde, um Zeit zu sparen.
4. Unit-Tests und Integrationstests (gleichzeitig)
-Was passiert? Unit-Tests prüfen einzelne Code-Einheiten, während Integrationstests das Zusammenspiel testen.
-Warum gleichzeitig? Beide Tests decken unterschiedliche Aspekte ab, daher führen wir sie parallel aus, um Zeit zu sparen.
5. Deploy
-Was passiert? Der getestete Code wird in die Produktionsumgebung überführt.
-Warum hier? Erst nach erfolgreichen Tests wird der Code in die Live-Umgebung deployt, um Fehler zu vermeiden.
-Warum diese Reihenfolge?

Wir haben diese Reihenfolge gewählt, um Fehler früh zu erkennen, den Prozess zu beschleunigen und nur getesteten Code in die Produktionsumgebung zu bringen. Die parallele Ausführung von Unit- und Integrationstests spart Zeit, und das Deployment erfolgt nur, wenn der Code stabil ist.



---------------------------------------------------------------------------


🔧 Testing

Unit Tests

🌐Frontend: Jest 

    "connect should initialize WebSocket connection:"

-Testziel: Überprüfen, ob die WebSocket-Verbindung korrekt initialisiert wird.
-Warum?: Dies stellt sicher, dass die App in der Lage ist, eine WebSocket-Verbindung aufzubauen, was für die Kommunikation im Chat erforderlich ist.

    "setUsername should set the username and enable chat:"

-Testziel: Überprüfen, ob der Benutzername korrekt gesetzt wird und der Chat aktiviert wird.
-Warum?: Dieser Test stellt sicher, dass der Benutzername korrekt gespeichert wird und der Chat korrekt funktioniert, nachdem der Benutzername gesetzt wurde.

    "sendMessage should send message via WebSocket:"

Testziel: Überprüfen, ob eine Nachricht über WebSocket gesendet wird.
Warum?: Dies stellt sicher, dass Nachrichten ordnungsgemäß über die WebSocket-Verbindung an den Server gesendet werden.


💻 Backend: 

Junit:
Hauptsächlich für das Testen von Java-Komponenten.
Mockito:
Zum Mocken von Abhängigkeiten und Testen von Interaktionen.

    "ChatHandlerTest:"
-Ziel: Testet die Methoden des ChatHandler, um sicherzustellen, dass WebSocket-Verbindungen korrekt verarbeitet werden.
-Beispiele: afterConnectionEstablished: Überprüft, ob eine neue Verbindung korrekt registriert wird.

    "handleTextMessage:"
-Testet, ob eingehende Nachrichten korrekt verarbeitet und gespeichert werden.

    "MessageTest:"
-Ziel: Überprüft die Getter und Setter der Message-Klasse.
-Warum: Sicherstellen, dass die Datenintegrität gewahrt bleibt und die Entität korrekt funktioniert.

ingesamt (10 unittest)


Integrationstests

- Technologien: JUnit, Spring Boot Test, H2-Datenbank

- Tests:

    - WebSocket-Nachrichtenverarbeitung

    - Datenbank-Interaktionen

    - End-to-End Funktionalität
      
Warum diese Tests?
Gesamtfunktionalität: Sicherstellen, dass alle Teile der Anwendung zusammenarbeiten.
Fehlererkennung: Identifizieren von Problemen, die bei der Interaktion zwischen Komponenten auftreten können.
Vertrauen in die Anwendung: Gewährleisten, dass die Anwendung in einer realistischen Umgebung korrekt funktioniert.
Diese Tests sind entscheidend, um die Stabilität und Zuverlässigkeit der gesamten Anwendung sicherzustellen.

---------------------------------------------------------------------------

📈 Monitoring mit Grafana & Loki

- Echtzeitüberwachung der Anwendung

- Analyse von Logs zur Fehlerbehebung

---------------------------------------------------------------------------

📘 Jira-Integration & Scrum

- Aufgabenverfolgung & Statusüberwachung über Jira

- Transparente Verwaltung von Entwicklungsschritten

---------------------------------------------------------------------------

🛠️ Feature Branching
Beschreibung: Verwendung von Feature-Branches für die Entwicklung neuer Funktionen.
Vorteil: Erleichtert die Zusammenarbeit und reduziert Konflikte im Code.

---------------------------------------------------------------------------

💻 OAuth2-Integration mit GitHub in Grafana
Beschreibung: Einrichtung von OAuth2 für die Authentifizierung in Grafana über GitHub.
Vorteil: Erhöht die Sicherheit und vereinfacht die Benutzerverwaltung.
Diese Zusatzleistungen verbessern die Qualität, Effizienz und Wartbarkeit des Projekts erheblich.

---------------------------------------------------------------------------

🚀 Fazit

Diese strukturierte und skalierbare Chat-Anwendung bietet eine zuverlässige Echtzeitkommunikation, Monitoring & Logging, sowie eine CI/CD-Pipeline für eine nahtlose Entwicklung und Bereitstellung.
