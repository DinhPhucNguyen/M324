Projekt 
Ein Chat in der ein Gruppe die Zugrif ff haben sich gegenseitig anschreiben kann (Gruppenchat).


Projektstruktur

Backend:
Technologie: Spring Boot
Funktionalität: Verwaltet WebSocket-Verbindungen und speichert Nachrichten in einer MySQL-Datenbank.
Wichtige Komponenten:
ChatHandler: Verarbeitet WebSocket-Nachrichten.
Message: Entität für gespeicherte Nachrichten.

Frontend:
Technologie: JavaScript mit WebSockets
Funktionalität: Bietet eine Benutzeroberfläche für den Chat.
Wichtige Dateien:
app.js: Handhabt WebSocket-Verbindungen und UI-Interaktionen.
index.html: Grundlegendes HTML-Layout.
styles.css: Styling der Benutzeroberfläche.

Datenbank:
Technologie: MySQL
Struktur: Eine Tabelle messages zur Speicherung von Chat-Nachrichten.

Monitoring und Logging
Grafana: Visualisierung von Metriken und Logs.
Prometheus: Sammlung und Speicherung von Metriken.
Loki: Sammlung und Speicherung von Logs.

Docker-Setup
docker-compose.yml: Definiert die Container für Backend, Frontend, Datenbank, Grafana, Prometheus und Loki.
Netzwerk: Alle Dienste sind über ein gemeinsames Docker-Netzwerk verbunden.

Diese Struktur ermöglicht eine skalierbare und gut überwachte Chat-Anwendung mit Echtzeit-Kommunikation.nstallations- und Deployment-Anleitung


Voraussetzungen

-Docker: Stelle sicher, dass Docker auf deinem Computer installiert ist. Es ermöglicht das Ausführen von Anwendungen in Containern.
-Docker Compose: Ein Tool, um mehrere Docker-Container gleichzeitig zu starten.


Schritt-für-Schritt-Anleitung
1. Projekt herunterladen:
Lade das Projekt von der Quelle (z.B. GitHub) auf deinen Computer herunter.
2.Docker starten:
Öffne Docker auf deinem Computer. Stelle sicher, dass es läuft.
3.Terminal öffnen:
Öffne ein Terminal oder eine Eingabeaufforderung auf deinem Computer.
4.Zum Projektverzeichnis navigieren:
Wechsle in das Verzeichnis, in dem sich die docker-compose.yml-Datei befindet. Verwende dazu den Befehl:
  "cd /pfad/zum/projekt"
5.Anwendung starten:
Gib im Terminal den folgenden Befehl ein, um alle Dienste zu starten:
  "docker-compose up -d"
Dieser Befehl startet die Anwendung im Hintergrund.
6.Zugriff auf die Anwendung:
-Öffne einen Webbrowser und gehe zu http://localhost, um die Chat-Anwendung zu sehen.(Frontend)
-Grafana ist unter http://localhost:3000 erreichbar, um Metriken und Logs zu überwachen. Zum Anmeldendaten sind : 
username : admin
Passwort: admin
7.Überprüfung:
-Stelle sicher, dass alle Dienste laufen, indem du im Terminal den Befehl eingibst:
  "docker-compose ps"
-Dies zeigt dir den Status aller Container an.

Diese Anleitung hilft dir, die Anwendung einfach zu installieren und zu starten, ohne tiefere technische Kenntnisse zu benötigen.


Auflistung der verwendeten Tools

1.Jira
Verwendung: Projektmanagement und Aufgabenverfolgung.
Integration: Über GitHub Actions für automatisierte Workflows.

2.Grafana
Verwendung: Visualisierung von Metriken und Logs.
Funktion: Überwacht die Anwendung und zeigt Dashboards an.
OAuth2: Integration mit GitHub für Authentifizierung.

3.Loki
Verwendung: Sammlung und Speicherung von Logs.
Funktion: Analyse von Log-Daten in Grafana.

4.Prometheus
Verwendung: Sammlung und Speicherung von Metriken.
Funktion: Überwacht die Leistung der Anwendung.

5.GitHub
Verwendung: Versionskontrolle und Quellcode-Hosting.
Funktion: Verwaltung des Projektcodes und Zusammenarbeit.

6.GitHub Actions
Verwendung: Automatisierung von CI/CD-Pipelines.
Funktion: Automatisiert Tests, Builds und Deployments.

7.Node.js
Verwendung: Laufzeitumgebung für JavaScript im Frontend.

8.npm
Verwendung: Paketmanager für JavaScript-Abhängigkeiten.

9.JDK 17
Verwendung: Java Development Kit für das Backend.

10.Maven
Verwendung: Build-Tool für das Java-Backend.

11.Linting Tools
ESLint: Für JavaScript-Code-Qualität.
Prettier: Für Code-Formatierung.
Stylelint: Für CSS-Formatierung.
Checkstyle: Für Java-Code-Qualität.

12.Unit Test Tools
Jest: Für JavaScript-Tests im Frontend.
JUnit: Für Java-Tests im Backend.

13.Integrations-Tools
Mockito: Für das Mocking in Java-Tests.

14.Docker
Verwendung: Containerisierung der Anwendung und ihrer Dienste.

Diese Liste umfasst die wichtigsten Tools, die in deinem Projekt verwendet werden, einschließlich der OAuth2-Integration mit Grafana und GitHub.Erklärung der Zusatzleistungen

1.CI/CD Deployment
Beschreibung: Automatisierte Workflows mit GitHub Actions für kontinuierliche Integration und Bereitstellung.
Vorteil: Erhöht die Effizienz und Zuverlässigkeit des Entwicklungsprozesses.

                      linting 
install --> build --> intergrationtest --> deploy
                      Unittest
Jira intergration 


Containerisierung mit Docker
Beschreibung: Verwendung von Docker zur Containerisierung der Anwendung.
Vorteil: Erleichtert die Bereitstellung und Skalierung der Anwendung.

compose name m324 
db-1         kein port
loki-1        3100
backend1      8080
promethues-1   9090
frontend-1     80
grafana-1     3000


Pipeline Stages

1. Install
Was passiert? Alle nötigen Abhängigkeiten und Tools werden installiert.
Warum hier? Ohne die Installation können wir den Build-Prozess und Tests nicht durchführen.
2. Build
Was passiert? Der Code wird kompiliert und Artefakte erstellt.
Warum hier? Wir stellen sicher, dass der Code korrekt gebaut wird und keine grundlegenden Fehler auftreten.
3. Linting
Was passiert? Der Code wird auf Stil- und Syntaxfehler überprüft.
Warum nach dem Build? Wir prüfen nur den funktionierenden Code, der erfolgreich kompiliert wurde, um Zeit zu sparen.
4. Unit-Tests und Integrationstests (gleichzeitig)
Was passiert? Unit-Tests prüfen einzelne Code-Einheiten, während Integrationstests das Zusammenspiel testen.
Warum gleichzeitig? Beide Tests decken unterschiedliche Aspekte ab, daher führen wir sie parallel aus, um Zeit zu sparen.
5. Deploy
Was passiert? Der getestete Code wird in die Produktionsumgebung überführt.
Warum hier? Erst nach erfolgreichen Tests wird der Code in die Live-Umgebung deployt, um Fehler zu vermeiden.
Warum diese Reihenfolge?

Wir haben diese Reihenfolge gewählt, um Fehler früh zu erkennen, den Prozess zu beschleunigen und nur getesteten Code in die Produktionsumgebung zu bringen. Die parallele Ausführung von Unit- und Integrationstests spart Zeit, und das Deployment erfolgt nur, wenn der Code stabil ist.

Jira und Tracking
Mit Jira können wir jede Phase nachverfolgen und sicherstellen, dass wir bei Problemen schnell reagieren können. So bleibt der Prozess transparent und effizient.


                      linting 
install --> build --> intergrationtest --> deploy
                      Unittest

Jira intergration 



Unit Tests
Frontend: Jest 

"connect should initialize WebSocket connection:"

Testziel: Überprüfen, ob die WebSocket-Verbindung korrekt initialisiert wird.
Warum?: Dies stellt sicher, dass die App in der Lage ist, eine WebSocket-Verbindung aufzubauen, was für die Kommunikation im Chat erforderlich ist.

"setUsername should set the username and enable chat:"

Testziel: Überprüfen, ob der Benutzername korrekt gesetzt wird und der Chat aktiviert wird.
Warum?: Dieser Test stellt sicher, dass der Benutzername korrekt gespeichert wird und der Chat korrekt funktioniert, nachdem der Benutzername gesetzt wurde.

"sendMessage should send message via WebSocket:"

Testziel: Überprüfen, ob eine Nachricht über WebSocket gesendet wird.
Warum?: Dies stellt sicher, dass Nachrichten ordnungsgemäß über die WebSocket-Verbindung an den Server gesendet werden.

Backend: 

Junit:
Hauptsächlich für das Testen von Java-Komponenten.
Mockito:
Zum Mocken von Abhängigkeiten und Testen von Interaktionen.

"ChatHandlerTest:"
Ziel: Testet die Methoden des ChatHandler, um sicherzustellen, dass WebSocket-Verbindungen korrekt verarbeitet werden.
Beispiele:
afterConnectionEstablished: Überprüft, ob eine neue Verbindung korrekt registriert wird.

"handleTextMessage:"
Testet, ob eingehende Nachrichten korrekt verarbeitet und gespeichert werden.
MessageTest:
Ziel: Überprüft die Getter und Setter der Message-Klasse.
Warum: Sicherstellen, dass die Datenintegrität gewahrt bleibt und die Entität korrekt funktioniert.

ingesamt (10 unittest)

Integrationstests

Verwendete Technologien

JUnit:
Für das Ausführen von Integrationstests im Java-Backend.

Spring Boot Test:
Zum Testen der gesamten Anwendungskonfiguration und der Interaktion zwischen Komponenten.

Durchführung der Integrationstests
Ziel: Überprüfen, ob verschiedene Komponenten des Systems nahtlos zusammenarbeiten.
Beispiele:
WebSocket-Interaktionen: Testen, ob Nachrichten korrekt über WebSockets gesendet und empfangen werden.
Datenbank-Interaktionen: Sicherstellen, dass Daten korrekt in der Datenbank gespeichert und abgerufen werden.
Testumgebung:
Verwendung einer In-Memory-Datenbank (H2) für Tests, um die Datenbanklogik zu überprüfen, ohne eine echte Datenbank zu benötigen.
Warum diese Tests?
Gesamtfunktionalität: Sicherstellen, dass alle Teile der Anwendung zusammenarbeiten.
Fehlererkennung: Identifizieren von Problemen, die bei der Interaktion zwischen Komponenten auftreten können.
Vertrauen in die Anwendung: Gewährleisten, dass die Anwendung in einer realistischen Umgebung korrekt funktioniert.
Diese Tests sind entscheidend, um die Stabilität und Zuverlässigkeit der gesamten Anwendung sicherzustellen.

Task-Tracking Integration
Integration von Jira für Aufgabenverfolgung und Projektmanagement. (scrum)
Vorteil: Verbessert die Organisation und Nachverfolgbarkeit von Aufgaben.

Monitoring mit Grafana und Loki
Beschreibung: Überwachung der Anwendung mit Grafana Dashboards und Log-Analyse mit Loki.
Vorteil: Bietet Einblicke in die Leistung und hilft bei der Fehlerbehebung.

Feature Branching
Beschreibung: Verwendung von Feature-Branches für die Entwicklung neuer Funktionen.
Vorteil: Erleichtert die Zusammenarbeit und reduziert Konflikte im Code.

OAuth2-Integration mit GitHub in Grafana
Beschreibung: Einrichtung von OAuth2 für die Authentifizierung in Grafana über GitHub.
Vorteil: Erhöht die Sicherheit und vereinfacht die Benutzerverwaltung.
Diese Zusatzleistungen verbessern die Qualität, Effizienz und Wartbarkeit des Projekts erheblich.
