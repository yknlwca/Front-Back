import {
    LocalVideoTrack,
    RemoteParticipant,
    RemoteTrack,
    RemoteTrackPublication,
    Room,
    RoomEvent
} from "livekit-client";
import "./App.css";
import { useEffect, useState } from "react";
import VideoComponent from "./components/VideoComponent";
import AudioComponent from "./components/AudioComponent";

type TrackInfo = {
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

function App() {
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
    // 소리 감지 상태 추가
    const [speakingParticipant, setSpeakingParticipant] = useState<string | null>(null);

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

        // 소리 관리
        room.on(RoomEvent.ParticipantSpeaking, (participant: RemoteParticipant) => {
            setSpeakingParticipant(participant.identity);
        });

        room.on(RoomEvent.ParticipantStoppedSpeaking, (participant: RemoteParticipant) => {
            setSpeakingParticipant((prev) => (prev === participant.identity ? null : prev));
        });

        try {
            const token = await getToken(roomName, participantName);
            await room.connect(LIVEKIT_URL, token);
            await room.localParticipant.enableCameraAndMicrophone();
            setLocalTrack(room.localParticipant.videoTrackPublications.values().next().value.videoTrack);

            room.localParticipant.on('speaking', () => setSpeakingParticipant(room.localParticipant.identity));
            room.localParticipant.on('stoppedSpeaking', () => setSpeakingParticipant(null));

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

    useEffect(() => {
        if (remoteTracks.length > 2) {
            alert(`현재 진료 중 입니다.`);
            leaveRoom();
        }
    }, [remoteTracks]);

    return (
        <>
            {!room ? (
                <div id="join">
                    <div id="join-dialog">
                        <h2>대기실</h2>
                        <form
                            onSubmit={(e) => {
                                joinRoom();
                                e.preventDefault();
                            }}
                        >
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
                            <button
                                className="btn btn-lg btn-success"
                                type="submit"
                                disabled={!roomName || !participantName}
                            >
                                입장
                            </button>
                        </form>
                    </div>
                </div>
            ) :  (
                <div id="room">
                    <div id="room-header">
                        <h2 id="room-title">{roomName} 치료실입니다</h2>
                        <button className="btn btn-danger" id="leave-room-button" onClick={leaveRoom}>
                            방 나가기
                        </button>
                    </div>
                    <div id="layout-container" style={{ display: 'flex', height: '100%' }}>
                        <div style={{ flex: 1, position: 'relative' }}>
                            {remoteTracks.length > 0 && (
                                <div id="remote-tracks" style={{ width: '100%', height: '100%' }}>
                                    {remoteTracks.map((remoteTrack) =>
                                        remoteTrack.trackPublication.kind === "video" ? (
                                            <div
                                            key={remoteTrack.trackPublication.trackSid}
                                                style={{
                                                    border:speakingParticipant == remoteTrack.participantIdentity ? '5px solid blue' : 'none',
                                                    width : '100%',
                                                    height: '100%'
                                                }}
                                            >
                                            <VideoComponent
                                                key={remoteTrack.trackPublication.trackSid}
                                                track={remoteTrack.trackPublication.videoTrack!}
                                                participantIdentity={remoteTrack.participantIdentity}
                                                style={{ width: '100%', height: '100%' }}
                                            />
                                                </div>
                                        ) : (
                                            <AudioComponent
                                                key={remoteTrack.trackPublication.trackSid}
                                                track={remoteTrack.trackPublication.audioTrack!}
                                            />
                                        )
                                    )}
                                </div>
                            )}
                            {localTrack && (
                                <div id="local-track"
                                    style={{
                                        position: isLocalTrackLarge ? 'relative' : 'absolute',
                                        bottom: isLocalTrackLarge ? '0' : '10px',
                                        right: isLocalTrackLarge ? '0' : '10px',
                                        width: isLocalTrackLarge ? '100%' : '20%',
                                        height: isLocalTrackLarge ? '100%' : '20%'
                                    }}
                                >
                                    <VideoComponent track={localTrack} participantIdentity={`${participantName}`} local={true} />
                                </div>
                            )}
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', marginLeft: '10px' }}>
    <button
        onClick={() => setIsLocalTrackLarge(prevState => !prevState)}
        className={`btn btn-lg ${isLocalTrackLarge ? 'btn-secondary' : 'btn-primary'}`}
    >
        {isLocalTrackLarge ? '작게' : '크게'}
    </button>
</div>
                    </div>
                </div>
            )}
        </>
    );
}

export default App;
