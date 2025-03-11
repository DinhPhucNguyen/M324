// __tests__/app.test.js

// Mock für localStorage
global.localStorage = {
  setItem: jest.fn()
};

// Mock für DOM-Elemente
document.getElementById = jest.fn().mockImplementation((id) => {
  if (id === 'username') return { value: 'testUser' };
  if (id === 'message') return { value: 'Test message', focus: jest.fn() };
  if (id === 'chat') return { appendChild: jest.fn(), scrollTop: 0, scrollHeight: 100 };
  if (id === 'userList') return { innerHTML: '' };
  if (id === 'messageInput') return { 
    value: 'Test message', 
    addEventListener: jest.fn(),
    focus: jest.fn()
  };
  return { style: {}, innerHTML: '', value: '', addEventListener: jest.fn() };
});

// Mock für WebSocket
global.WebSocket = jest.fn().mockImplementation(() => ({
  send: jest.fn(),
  close: jest.fn(),
  addEventListener: jest.fn(),
  readyState: 1,
  OPEN: 1
}));

// Mock für WebSocket-Instanz
global.stompClient = {
  send: jest.fn()
};

// Importiere die zu testenden Funktionen
// Hinweis: Du musst möglicherweise deine app.js anpassen, um die Funktionen zu exportieren
const { connect, setUsername, addMessage, sendMessage } = require('./app');

describe('Chat App Functions', () => {
  beforeEach(() => {
    // Reset mocks before each test
    jest.clearAllMocks();
  });

  test('connect should initialize WebSocket connection', () => {
    connect();
    expect(global.WebSocket).toHaveBeenCalled();
  });

  test('setUsername should set the username and enable chat', () => {
    // Mock für localStorage
    global.localStorage = {
      setItem: jest.fn()
    };
    
    // Mock für DOM-Manipulation
    document.querySelector = jest.fn().mockImplementation(() => ({
      style: {},
      classList: {
        add: jest.fn(),
        remove: jest.fn()
      }
    }));
    
    setUsername();
    
    expect(global.localStorage.setItem).toHaveBeenCalledWith('username', 'testUser');
    // Weitere Erwartungen basierend auf deiner Implementierung
  });

  test('addMessage should append message to chat', () => {
    const message = {
      content: 'Hello',
      username: 'user1',
      createdAt: new Date().toISOString()
    };
    
    addMessage(message);
    
    expect(document.getElementById('chat').appendChild).toHaveBeenCalled();
  });

  test('sendMessage should send message via WebSocket', () => {
    // Mock für WebSocket-Instanz
    global.stompClient = {
      send: jest.fn()
    };
    
    sendMessage();
    
    expect(global.stompClient.send).toHaveBeenCalled();
    expect(document.getElementById('message').value).toBe('');
  });
});
