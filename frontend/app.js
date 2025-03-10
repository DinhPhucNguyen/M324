// WebSocket-Verbindungs-URL basierend auf dem aktuellen Host
const wsUrl = 'ws://' + window.location.host + '/chat';
let socket;  // WebSocket-Verbindung
let username = '';  // Benutzername für die aktuelle Session

/**
 * Stellt die WebSocket-Verbindung her und richtet Event-Handler ein
 * Wird beim Start und bei Verbindungsabbruch aufgerufen
 */
function connect() {
    socket = new WebSocket(wsUrl);
    
    // Handler für eingehende Nachrichten vom Server
    socket.onmessage = function(event) {
        const response = JSON.parse(event.data);
        
        // Verschiedene Nachrichtentypen verarbeiten
        switch(response.type) {
            case 'USERS':
                // Nutzerliste aktualisieren
                const userList = document.querySelector('#userList ul');
                userList.innerHTML = '';
                response.users.forEach(user => {
                    const li = document.createElement('li');
                    li.textContent = user;
                    userList.appendChild(li);
                });
                break;
            case 'MESSAGE':
                // Zeigt eine neue Nachricht an
                addMessage(response.message);
                break;
            case 'HISTORY':
                // Lädt den kompletten Chat-Verlauf
                const messagesDiv = document.getElementById('messages');
                messagesDiv.innerHTML = '';
                response.messages.forEach(addMessage);
                break;
        }
    };

    // Automatische Wiederverbindung bei Verbindungsabbruch
    socket.onclose = function() {
        console.log('WebSocket Disconnected. Reconnecting...');
        setTimeout(connect, 1000);
    };

    // Fehlerbehandlung
    socket.onerror = function(error) {
        console.error('WebSocket Error:', error);
    };

    // Erfolgreiche Verbindung
    socket.onopen = function() {
        console.log('WebSocket Connected');
    };
}

/**
 * Setzt den Benutzernamen und aktiviert den Chat
 */
function setUsername() {
    const usernameInput = document.getElementById('usernameInput');
    username = usernameInput.value.trim();
    
    if (username) {
        // Chat-Interface aktivieren
        document.getElementById('messageInput').disabled = false;
        document.getElementById('sendButton').disabled = false;
        // Username-Eingabe deaktivieren
        usernameInput.disabled = true;
        document.getElementById('username-area').querySelector('button').disabled = true;
        
        // Sende den Benutzernamen an den Server, um die Benutzerliste zu aktualisieren
        if (socket.readyState === WebSocket.OPEN) {
            socket.send(JSON.stringify({
                content: '',  // Leere Nachricht, nur um den Benutzernamen zu setzen
                username: username,
                createdAt: new Date().toISOString()
            }));
        }
    }
}

/**
 * Fügt eine neue Nachricht zur Chat-UI hinzu
 * Unterscheidet zwischen eigenen und fremden Nachrichten
 */
function addMessage(message) {
    const messagesDiv = document.getElementById('messages');
    const messageDiv = document.createElement('div');
    
    // Styling basierend auf Nachrichtenabsender
    const isOwnMessage = message.username === username;
    messageDiv.className = `message ${isOwnMessage ? 'own-message' : 'other-message'}`;
    
    // Zeitstempel formatieren
    const time = new Date(message.createdAt).toLocaleTimeString();
    
    // Nachricht mit Username und Zeitstempel anzeigen
    messageDiv.innerHTML = `
        <span class="username">${message.username}</span>
        <span class="time">${time}</span>
        <div class="content">${message.content}</div>
    `;
    
    // Nachricht anhängen und zum Ende scrollen
    messagesDiv.appendChild(messageDiv);
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}

/**
 * Sendet eine neue Nachricht an den Server
 */
function sendMessage() {
    const input = document.getElementById('messageInput');
    const message = input.value.trim();
    
    if (message && socket.readyState === WebSocket.OPEN) {
        // Nachricht als JSON an Server senden
        socket.send(JSON.stringify({
            content: message,
            username: username,
            createdAt: new Date().toISOString()
        }));
        input.value = '';  // Eingabefeld leeren
    }
}

// Event-Listener für Enter-Taste bei Nachrichteneingabe
document.getElementById('messageInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

// Event-Listener für Enter-Taste bei Username-Eingabe
document.getElementById('usernameInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        setUsername();
    }
});

// Initial WebSocket-Verbindung herstellen
connect(); 