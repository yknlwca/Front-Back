import React, { useState } from 'react';
import './JoinRoomComponent.css'

interface JoinRoomComponentProps {
    onJoin: (participantName: string) => void;
    roomName: string;
}

const JoinRoomComponent: React.FC<JoinRoomComponentProps> = ({ onJoin, roomName }) => {
    const [participantName, setParticipantName] = useState("" + Math.floor(Math.random() * 100));

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onJoin(participantName);
    };

    return (
        <div id="join">
            <div id="join-dialog">
                <h2>대기실</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label htmlFor="participant-name">안녕하세요</label>
                        <input
                            id="participant-name"
                            className="form-control"
                            type="text"
                            value={participantName}
                            onChange={(e) => setParticipantName(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label htmlFor="room-name">치료실입니다</label>
                    </div>
                    <button className="btn btn-lg btn-success" type="submit" disabled={!roomName || !participantName}>
                        입장
                    </button>
                </form>
            </div>
        </div>
    );
};

export default JoinRoomComponent;
