import React, { useState } from 'react';
import './ChatComponent.css';
import '@livekit/components-styles';

interface ChatComponentProps {
    localParticipant: RemoteParticipant;
    messages: { user: string; text: string }[];
    onSendMessage: (message: { user: string; text: string }) => void;
}

const ChatComponent: React.FC<ChatComponentProps> = ({ localParticipant, messages, onSendMessage }) => {
    const [input, setInput] = useState<string>('');

    const handleSend = () => {
        if (input.trim()) {
            const message = { user: localParticipant.identity, text: input };
            onSendMessage(message);
            setInput('');
        }
    };

    return (
        <div className="chat-container">
            <div className="chat-messages">
                {messages.map((msg, index) => (
                    <div key={index} className="chat-message">
                        <strong>{msg.user}:</strong> {msg.text}
                    </div>
                ))}
            </div>
            <div className="chat-input">
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    onKeyPress={(e) => e.key === 'Enter' && handleSend()}
                />
                <button onClick={handleSend}>Send</button>
            </div>
        </div>
    );
};

export default ChatComponent;
