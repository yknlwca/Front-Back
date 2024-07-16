import React, { useEffect, useState } from 'react';
import { Room, RoomEvent, RemoteParticipant, LocalParticipant } from '@livekit/protocol';
import ChatEntry from './ChatEntry';
import { formatChatMessageLinks } from './formatChatMessageLinks';

const LiveKitRoom = () => {
  const [room, setRoom] = useState<Room | null>(null);
  const [messages, setMessages] = useState<ReceivedChatMessage[]>([]);
  const [inputMessage, setInputMessage] = useState<string>('');

  useEffect(() => {
    const newRoom = new Room();
    setRoom(newRoom);

    newRoom.on(RoomEvent.DataReceived, (payload, participant, kind, topic) => {
      const message: ReceivedChatMessage = {
        message: new TextDecoder().decode(payload),
        from: participant,
        timestamp: Date.now(),
      };
      setMessages((prevMessages) => [...prevMessages, message]);
    });

    return () => {
      newRoom.disconnect();
    };
  }, []);

  const handleSendMessage = () => {
    if (room && inputMessage.trim()) {
      const payload = new TextEncoder().encode(inputMessage);
      room.localParticipant.publishData(payload, 'reliable', 'chat');
      setInputMessage('');
    }
  };

  return (
    <div className="livekit-room">
      <ul className="chat-messages">
        {messages.map((msg, index) => (
          <ChatEntry
            key={index}
            entry={msg}
            messageFormatter={formatChatMessageLinks}
          />
        ))}
      </ul>
      <div className="chat-input">
        <input
          type="text"
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)}
        />
        <button onClick={handleSendMessage}>Send</button>
      </div>
    </div>
  );
};

export default LiveKitRoom;
