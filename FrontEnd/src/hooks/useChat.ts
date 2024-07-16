import { useState, useEffect } from 'react';
import { RoomEvent, Room, DataPacket_Kind } from 'livekit-client';

export interface ChatMessage {
  id: string;
  from: string;
  message: string;
  timestamp: number;
}

export const useChat = (room: Room | undefined) => {
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const [isDataChannelReady, setIsDataChannelReady] = useState(false);

  useEffect(() => {
    if (!room) return;

    const handleDataReceived = (payload: Uint8Array, participant: string) => {
      const message = new TextDecoder().decode(payload);
      const chatMessage: ChatMessage = {
        id: Math.random().toString(36).substring(7),
        from: participant,
        message,
        timestamp: Date.now(),
      };
      setMessages((prev) => [...prev, chatMessage]);
    };

    room.on(RoomEvent.DataReceived, handleDataReceived);

    const interval = setInterval(() => {
      if (room.localParticipant.isDataReady) {
        setIsDataChannelReady(true);
        clearInterval(interval);
      }
    }, 1000);

    return () => {
      room.off(RoomEvent.DataReceived, handleDataReceived);
      clearInterval(interval);
    };
  }, [room]);

  const sendMessage = async (message: string) => {
    if (room && isDataChannelReady) {
      const payload = new TextEncoder().encode(message);
      await room.localParticipant.publishData(payload, DataPacket_Kind.RELIABLE);
      const chatMessage: ChatMessage = {
        id: Math.random().toString(36).substring(7),
        from: 'Me',
        message,
        timestamp: Date.now(),
      };
      setMessages((prev) => [...prev, chatMessage]);
    }
  };

  return { messages, sendMessage, isDataChannelReady };
};
