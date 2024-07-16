import {
    LocalVideoTrack,
    RemoteParticipant,
    RemoteTrack,
    RemoteTrackPublication,
    Room,
    RoomEvent,

} from "livekit-client";

import "./WebRTCComponent.css";
import { useEffect, useState } from "react";
import JoinRoomComponent from "../JoinRoomComponent/JoinRoomComponent";
import ControlPanelComponent from "../ControlPanelComponent/ControlPanelComponent";
import RoomHeaderCompoent from "../RoomHeaderComponent/RoomHeaderComponent";
import VideoLayoutComponent from "../VideoLayoutComponent/VideoLayOutComponent";


export type TrackInfo = {
    trackPublication: RemoteTrackPublication;
    participantIdentity: string;
};

// APPLICATION_SERVER_URL : 애플리케이션 서버의 URL. 토큰을 얻기 위해 서버에 요청하는 URL
// LIVEKIT_URL : LiveKit 서버의 URL, LiveKit API 이용해 가져온다
let APPLICATION_SERVER_URL = "";
let LIVEKIT_URL = import.meta.env.VITE_LIVEKIT_API_URL || "";
configureUrls();

function configureUrls() {
    if (!APPLICATION_SERVER_URL) {
        if (window.location.hostname === "localhost") {
            APPLICATION_SERVER_URL = "http://localhost:6080/";
        } else {
            APPLICATION_SERVER_URL = "https://" + window.location.hostname + ":6443/";
        }
    }

    if (!LIVEKIT_URL) {
        if (window.location.hostname === "localhost") {
            LIVEKIT_URL = "ws://localhost:7880/";
        } else {
            LIVEKIT_URL = "wss://" + window.location.hostname + ":7443/";
        }
    }
}

function WebRTCComponent() {
    // room : 화상회의 방 객체
    // localTrack : 본인 비디오
    // remoteTrack : 참가자 객체
    // participantName : 참가자 이름 -> 환자
    // roomName : 방 이름 -> 치료사 이름 따와야함
    const [room, setRoom] = useState<Room | undefined>(undefined);
    const [localTrack, setLocalTrack] = useState<LocalVideoTrack | undefined>(undefined);
    const [remoteTracks, setRemoteTracks] = useState<TrackInfo[]>([]);
    const [participantName, setParticipantName] = useState("" + Math.floor(Math.random() * 100));
    const [roomName, setRoomName] = useState("{{치료사이름}}");

    // 카메라 화면 크기 관리
    const [isLocalTrackLarge, setIsLocalTrackLarge] = useState(true);
    // 카메라, 마이크 상태 관리
    const [muted, setMuted] = useState(false);
    const [cameraOff, setCameraOff] = useState(false);
    const [cameras, setCameras] = useState<MediaDeviceInfo[]>([]);

    async function joinRoom() {
        const room = new Room();
        setRoom(room);

        room.on(
            RoomEvent.TrackSubscribed,
            (_track: RemoteTrack, publication: RemoteTrackPublication, participant: RemoteParticipant) => {
                setRemoteTracks((prev) => [
                    ...prev,
                    { trackPublication: publication, participantIdentity: participant.identity }
                ]);
            }
        );

        room.on(RoomEvent.TrackUnsubscribed, (_track: RemoteTrack, publication: RemoteTrackPublication) => {
            setRemoteTracks((prev) => prev.filter((track) => track.trackPublication.trackSid !== publication.trackSid ));
        });


        try {
            const token = await getToken(roomName, participantName);
            await room.connect(LIVEKIT_URL, token);
            await room.localParticipant.enableCameraAndMicrophone();
            setLocalTrack(room.localParticipant.videoTrackPublications.values().next().value.videoTrack);
            await getCameras(); // Get available cameras

        } catch (error) {
            console.log("There was an error connecting to the room:", (error as Error).message);
            await leaveRoom();
        }
    }

    async function leaveRoom() {
        await room?.disconnect();
        setRoom(undefined);
        setLocalTrack(undefined);
        setRemoteTracks([]);
    }

    async function getToken(roomName: string, participantName: string) {
        const response = await fetch(APPLICATION_SERVER_URL + "token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                roomName: roomName,
                participantName: participantName
            })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(`Failed to get token: ${error.errorMessage}`);
        }

        const data = await response.json();
        return data.token;
    }

    async function getCameras() {
        try {
            const devices = await navigator.mediaDevices.enumerateDevices();
            const videoDevices = devices.filter(device => device.kind === 'videoinput');
            setCameras(videoDevices);
        } catch (error) {
            console.error("Error getting cameras:", error);
        }
    }

    const handleMuteClick = () => {
        if (localTrack) {
            const audioTracks = room?.localParticipant.audioTrackPublications;
            if (audioTracks ) {
                audioTracks.forEach(publication => {
                    if (muted) {
                        publication.track?.unmute();
                    } else {
                        publication.track?.mute();
                    }
                });
            }
            setMuted(!muted);
        }
    };

    const handleCameraClick = () => {
        setCameraOff(!cameraOff);
    }


    const handleCameraChange = async (event: React.ChangeEvent<HTMLSelectElement>) => {
        const deviceId = event.target.value;
        const videoTrack = await LocalVideoTrack.create({
            deviceId,
        });
        room?.localParticipant.publishTrack(videoTrack);
        localTrack?.stop();
        setLocalTrack(videoTrack);
    };

    // const onSendMessage = (message: { uesr: string; text: string }) => {
        
    // }

    useEffect(() => {
        if (remoteTracks.length > 2) {
            alert(`현재 진료 중 입니다.`);
            leaveRoom();
        }
    }, [remoteTracks]);

    return (
        <>
            {!room ? (
                <JoinRoomComponent onJoin={joinRoom} roomName={roomName} />
            ) :  (
                <div className="webrtc-container">
                        <RoomHeaderCompoent roomName={roomName} isLocalTrackLarger={isLocalTrackLarge} />
                        <div className="main-container">
                        <VideoLayoutComponent
                            localTrack={localTrack}
                            remoteTracks={remoteTracks}
                            participantName={participantName}
                            isLocalTrackLarge={isLocalTrackLarge}
                            toggleLocalTrackSize={() => setIsLocalTrackLarge(!isLocalTrackLarge)}
                        >
                        </VideoLayoutComponent>
                            
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
            )}
        </>

    );
}

export default WebRTCComponent;
