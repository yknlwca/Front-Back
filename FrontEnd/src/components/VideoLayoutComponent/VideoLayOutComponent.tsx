import React from 'react';
import { TrackInfo } from '../../hooks/useWebRTC'; 
import VideoComponent from '../VideoComponent/VideoComponent';
import AudioComponent from '../AudioComponent/AudioComponent';
import { LocalVideoTrack } from 'livekit-client';
import './VideoLayoutComponent.css';

interface VideoLayoutComponentProps {
    localTrack: LocalVideoTrack | undefined;
    remoteTracks: TrackInfo[];
    participantName: string;
    isLocalTrackLarge: boolean;
    toggleLocalTrackSize: () => void;
}
// 최초 시작 isLocalTrackLarge는 false로 시작
const VideoLayoutComponent: React.FC<VideoLayoutComponentProps> = ({ localTrack, remoteTracks, participantName, isLocalTrackLarge, toggleLocalTrackSize }) => (
    <div id="layout-container">
            {remoteTracks.length > 0 && (
                <div id="remote-tracks" className={isLocalTrackLarge ? 'half-size' : 'full-size'} onDoubleClick={toggleLocalTrackSize}>
                    {remoteTracks.map((remoteTrack) =>
                        remoteTrack.trackPublication.kind === "video" ? (
                            <VideoComponent
                                key={remoteTrack.trackPublication.trackSid}
                                track={remoteTrack.trackPublication.videoTrack!}
                                participantIdentity={remoteTrack.participantIdentity}
                            />
                        ) : (
                            <AudioComponent key={remoteTrack.trackPublication.trackSid} track={remoteTrack.trackPublication.audioTrack!} />
                        )
                    )}
                </div>
            )}
            {localTrack && (
                <div
                    id="local-track"
                className={isLocalTrackLarge ? 'half-size' : 'small-size'}
                onDoubleClick={toggleLocalTrackSize}
                >
                    <VideoComponent track={localTrack} participantIdentity={ `${participantName} (본인)`} local={true} />
                </div>
            )}
    </div>
);

export default VideoLayoutComponent;
