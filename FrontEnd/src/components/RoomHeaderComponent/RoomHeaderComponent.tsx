import React from 'react';
import './RoomHeaderComponent.css'

interface RoomHeaderComponentProps {
    roomName: string;
    isLocalTrackLarger: boolean;
}

const RoomHeaderCompoent: React.FC<RoomHeaderComponentProps> = ({ roomName, isLocalTrackLarger }) => (
    <div id="room">
        <div id="room-header">
           {isLocalTrackLarger && <h2 id="room-title">{roomName} 치료실입니다</h2>}
        </div>
    </div>
);

export default RoomHeaderCompoent;
