Gruppenchat-Projekt

📚 Projektbeschreibung

Ein Echtzeit-Gruppenchat, in dem eine Gruppe von Nutzern miteinander kommunizieren kann. Die Anwendung basiert auf Spring Boot im Backend und JavaScript mit WebSockets im Frontend. Nachrichten werden in einer MySQL-Datenbank gespeichert.

🛠️ Projektstruktur

💻 Backend

Technologie: Spring Boot

Funktionalität: Verwaltung der WebSocket-Verbindungen & Speicherung der Nachrichten

Wichtige Komponenten:

ChatHandler: Verarbeitung der WebSocket-Nachrichten

Message: Entität zur Speicherung von Nachrichten

🌐 Frontend

Technologie: JavaScript mit WebSockets

Funktionalität: Bereitstellung der Benutzeroberfläche

Wichtige Dateien:

app.js: WebSocket-Verbindungen & UI-Interaktionen

index.html: Grundlegendes HTML-Layout

styles.css: Styling der Benutzeroberfläche

🏛️ Datenbank

Technologie: MySQL

Struktur: Eine Tabelle messages zur Speicherung der Chat-Nachrichten

📊 Monitoring & Logging

Grafana: Visualisierung von Metriken & Logs

Prometheus: Sammlung & Speicherung von Metriken

Loki: Sammlung & Speicherung von Logs

🛠️ Installation & Deployment

✅ Voraussetzungen

Docker: Installiert und betriebsbereit

Docker Compose: Zum gleichzeitigen Starten mehrerer Container

✈ Schritt-für-Schritt-Anleitung

Projekt herunterladen: Klone das Repository mit git clone <repository-url>

Docker starten: Stelle sicher, dass Docker aktiv ist

Terminal öffnen und ins Projektverzeichnis wechseln: cd /pfad/zum/projekt

Anwendung starten:

docker-compose up -d

Dadurch startet die Anwendung im Hintergrund.

Zugriff auf die Anwendung:

Frontend: http://localhost

Grafana Dashboard: http://localhost:3000

Anmeldedaten: admin / admin

Status überprüfen:

docker-compose ps

Zeigt den Status aller Container an.

📝 Verwendete Tools

🏰 Entwicklungs- & Projektmanagement-Tools

Jira - Projektmanagement & Aufgabenverfolgung

GitHub - Versionskontrolle & Quellcode-Hosting

GitHub Actions - CI/CD-Pipelines für Automatisierung

Feature Branching - Entwicklung neuer Features auf separaten Branches

🔄 Monitoring & Logging

Grafana - Visualisierung von Metriken & Logs

Loki - Sammlung & Speicherung von Logs

Prometheus - Sammlung & Speicherung von Metriken

OAuth2-Integration mit GitHub für Grafana-Authentifizierung

🛠️ Entwicklungs- & Test-Tools

Node.js - Laufzeitumgebung für das Frontend

npm - Paketmanager für JavaScript-Abhängigkeiten

JDK 17 - Java Development Kit für das Backend

Maven - Build-Tool für das Java-Backend

Linting Tools

ESLint (JS-Code-Qualität)

Prettier (JS-Code-Formatierung)

Stylelint (CSS-Formatierung)

Checkstyle (Java-Code-Qualität)

Testing-Frameworks

Jest (Frontend-Tests)

JUnit (Backend-Tests)

Mockito (Mocking für Integrationstests)

🛢 Docker-Setup

Container & Ports

Name

Container

Port

m324

DB-1

Kein Port

loki-1

Loki

3100

backend-1

Backend

8080

prometheus-1

Prometheus

9090

frontend-1

Frontend

80

grafana-1

Grafana

3000

⚙ CI/CD Pipeline

Ablauf: install → build → linting, tests (parallel) → deploy

Install - Installation aller Abhängigkeiten

Build - Code kompilieren & Artefakte erstellen

Linting - Code-Qualität prüfen

Unit- & Integrationstests (parallel)

Deploy - Bereitstellung in der Produktionsumgebung

🔧 Testing

Frontend: Jest

✅ connect should initialize WebSocket connection

✅ setUsername should set the username and enable chat

✅ sendMessage should send message via WebSocket

Backend: JUnit & Mockito

✅ ChatHandlerTest - Testet WebSocket-Interaktionen

✅ MessageTest - Sicherstellt, dass Nachrichten korrekt gespeichert werden

Integrationstests

Technologien: JUnit, Spring Boot Test, H2-Datenbank

Tests:

WebSocket-Nachrichtenverarbeitung

Datenbank-Interaktionen

End-to-End Funktionalität

📈 Monitoring mit Grafana & Loki

Echtzeitüberwachung der Anwendung

Analyse von Logs zur Fehlerbehebung

📘 Jira-Integration & Scrum

Aufgabenverfolgung & Statusüberwachung über Jira

Transparente Verwaltung von Entwicklungsschritten

🚀 Fazit

Diese strukturierte und skalierbare Chat-Anwendung bietet eine zuverlässige Echtzeitkommunikation, Monitoring & Logging, sowie eine CI/CD-Pipeline für eine nahtlose Entwicklung und Bereitstellung.
