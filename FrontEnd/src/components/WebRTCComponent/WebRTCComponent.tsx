import React, { useState } from 'react';
import { useWebRTC } from '../../hooks/useWebRTC';
import JoinRoomComponent from '../JoinRoomComponent/JoinRoomComponent';
import ControlPanelComponent from '../ControlPanelComponent/ControlPanelComponent';
import RoomHeaderComponent from '../RoomHeaderComponent/RoomHeaderComponent';
import VideoLayoutComponent from '../VideoLayoutComponent/VideoLayoutComponent';
import { LayoutContextProvider, LiveKitRoom } from '@livekit/components-react';
import ChatComponent from '../ChatComponent/ChatComponent';
import { LIVEKIT_URL } from '../../config/config';

const WebRTCComponent = () => {
  const {
    room,
    localTrack,
    remoteTracks,
    roomName,
    isLocalTrackLarge,
    muted,
    cameraOff,
    cameras,
    joinRoom,
    leaveRoom,
    handleMuteClick,
    handleCameraClick,
    handleCameraChange,
    toggleLocalTrackSize,
  } = useWebRTC();

  const [participantName, setParticipantName] = useState<string | undefined>(undefined);

  const handleJoinRoom = async (name: string) => {
    setParticipantName(name);
    await joinRoom(name);
  };

  return (
    <div>
      {!room ? (
        <JoinRoomComponent onJoin={handleJoinRoom} roomName={roomName} />
      ) : (
        <LiveKitRoom serverUrl={LIVEKIT_URL} token={room.localParticipant.identity}>
          <LayoutContextProvider>
            <div className="webrtc-container">
              <RoomHeaderComponent roomName={roomName} isLocalTrackLarger={isLocalTrackLarge} />
              <div className="main-container">
                <VideoLayoutComponent
                  localTrack={localTrack}
                  remoteTracks={remoteTracks}
                  participantName={participantName}
                  isLocalTrackLarge={isLocalTrackLarge}
                  toggleLocalTrackSize={toggleLocalTrackSize}
                />
                <ChatComponent room={room} />
              </div>
              <ControlPanelComponent
                cameras={cameras}
                localTrack={localTrack}
                muted={muted}
                cameraOff={cameraOff}
                handleMuteClick={handleMuteClick}
                handleCameraClick={handleCameraClick}
                handleCameraChange={handleCameraChange}
                leaveRoom={leaveRoom}
              />
            </div>
          </LayoutContextProvider>
        </LiveKitRoom>
      )}
    </div>
  );
};

export default WebRTCComponent;
