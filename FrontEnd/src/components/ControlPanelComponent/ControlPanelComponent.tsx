import React from 'react';
import { LocalVideoTrack } from 'livekit-client';
import './ControlPanelComponent.css'
import { ChatToggle } from '@livekit/components-react';

interface ControlPanelComponentProps {
    cameras: MediaDeviceInfo[];
    localTrack: LocalVideoTrack | undefined;
    muted: boolean;
    cameraOff: boolean;
    handleMuteClick: () => void;
    handleCameraClick: () => void;
    handleCameraChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    leaveRoom: () => void;
}

const ControlPanelComponent: React.FC<ControlPanelComponentProps> = ({
    cameras, localTrack, muted, cameraOff, handleMuteClick, handleCameraClick, handleCameraChange, leaveRoom
}) => (
    <div id="camera-nav">
        <select id="select-cameras" onChange={handleCameraChange} value={localTrack?.mediaStreamTrack.getSettings().deviceId}>
            {cameras.map((camera) => (
                <option key={camera.deviceId} value={camera.deviceId}>
                    {camera.label}
                </option>
            ))}
        </select>
        <button className="btn btn-primary" id="mute" onClick={handleMuteClick}>
            {muted ? "음소거 해제" : "음소거 켜기"}
        </button>
        <button className="btn btn-secondary" id="camera-onoff" onClick={handleCameraClick}>
            {cameraOff ? "카메라 켜기" : "카메라 끄기"}
        </button>
        <button className="btn btn-danger" id="leave-room-button" onClick={leaveRoom}>
            방 나가기
        </button>
        <ChatToggle></ChatToggle>
    </div>
);

export default ControlPanelComponent;
