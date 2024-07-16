import React, { useState } from 'react';
import { useChat, ChatMessage } from '../../hooks/useChat';
import { ChatEntry } from '@livekit/components-react';
import './ChatComponent.css';

interface ChatComponentProps {
  room: Room | undefined;
}

const ChatComponent: React.FC<ChatComponentProps> = ({ room }) => {
  const { messages, sendMessage, isDataChannelReady } = useChat(room);
  const [message, setMessage] = useState("");

  const handleSendMessage = async (event: React.FormEvent) => {
    event.preventDefault();
    if (message.trim() !== "" && isDataChannelReady) {
      await sendMessage(message);
      setMessage("");
    }
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMessage(event.target.value);
  };

  const handleKeyPress = async (event: React.KeyboardEvent) => {
    if (event.key === 'Enter') {
      event.preventDefault();
      await handleSendMessage(event);
    }
  };

  return (
    <div className="chat-container">
      <ul className="chat-messages">
        {messages.map((msg: ChatMessage, idx: number) => (
          <ChatEntry
            key={idx}
            entry={{
              id: msg.id,
              message: msg.message,
              from: { identity: msg.from, name: msg.from },
              timestamp: msg.timestamp,
            }}
          />
        ))}
      </ul>
      <form className="chat-input" onSubmit={handleSendMessage}>
        <input
          type="text"
          value={message}
          onChange={handleInputChange}
          onKeyPress={handleKeyPress}
          placeholder="Enter your message"
          disabled={!isDataChannelReady}
        />
        <button type="submit" disabled={!isDataChannelReady}>Send</button>
      </form>
    </div>
  );
};

export default ChatComponent;
