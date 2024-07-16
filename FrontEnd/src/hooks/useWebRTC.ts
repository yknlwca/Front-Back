import { useState, useEffect } from 'react';
import {
  LocalVideoTrack,
  RemoteTrackPublication,
  Room,
  RoomEvent,
  RemoteTrack,
  RemoteParticipant,
} from 'livekit-client';
import { getToken } from '../apis/WebRTC/WebRTCApi'; 
import { LIVEKIT_URL } from '../config/config';

export type TrackInfo = {
  trackPublication: RemoteTrackPublication;
  participantIdentity: string;
};

export const useWebRTC = () => {
  const [room, setRoom] = useState<Room | undefined>(undefined);
  const [localTrack, setLocalTrack] = useState<LocalVideoTrack | undefined>(undefined);
  const [remoteTracks, setRemoteTracks] = useState<TrackInfo[]>([]);
  const [participantName, setParticipantName] = useState<string | undefined>(undefined);
  const [roomName, setRoomName] = useState('{{치료사이름}}');
  const [isLocalTrackLarge, setIsLocalTrackLarge] = useState(true);
  const [muted, setMuted] = useState(false);
  const [cameraOff, setCameraOff] = useState(false);
  const [cameras, setCameras] = useState<MediaDeviceInfo[]>([]);
  const [isDataChannelReady, setIsDataChannelReady] = useState(false);

  const joinRoom = async (name: string) => {
    const newRoom = new Room();
    setRoom(newRoom);
    setParticipantName(name);

    newRoom.on(
      RoomEvent.TrackSubscribed,
      (_track: RemoteTrack, publication: RemoteTrackPublication, participant: RemoteParticipant) => {
        setRemoteTracks((prev) => [
          ...prev,
          { trackPublication: publication, participantIdentity: participant.identity },
        ]);
      }
    );

    newRoom.on(RoomEvent.TrackUnsubscribed, (_track: RemoteTrack, publication: RemoteTrackPublication) => {
      setRemoteTracks((prev) =>
        prev.filter((track) => track.trackPublication.trackSid !== publication.trackSid)
      );
    });

    newRoom.on(RoomEvent.DataReceived, () => {
      setIsDataChannelReady(true);
    });

    try {
      const token = await getToken(roomName, name);
      await newRoom.connect(LIVEKIT_URL, token);

      if (newRoom.numParticipants > 2) {
        alert('현재 진료 중 입니다.');
        await leaveRoom();
        return;
      }

      await newRoom.localParticipant.enableCameraAndMicrophone();
      setLocalTrack(newRoom.localParticipant.videoTrackPublications.values().next().value.videoTrack);
      await getCameras();
    } catch (error) {
      console.log('There was an error connecting to the room:', (error as Error).message);
      await leaveRoom();
    }
  };

  const leaveRoom = async () => {
    await room?.disconnect();
    setRoom(undefined);
    setLocalTrack(undefined);
    setRemoteTracks([]);
    setIsDataChannelReady(false);
  };

  const getCameras = async () => {
    try {
      const devices = await navigator.mediaDevices.enumerateDevices();
      const videoDevices = devices.filter((device) => device.kind === 'videoinput');
      if (videoDevices.length === 0) {
        alert('카메라가 없습니다. 카메라를 연결한 후 다시 시도해 주세요.');
      }
      setCameras(videoDevices);
    } catch (error) {
      console.error('Error getting cameras:', error);
    }
  };

  const handleMuteClick = () => {
    if (localTrack) {
      const audioTracks = room?.localParticipant.audioTrackPublications;
      if (audioTracks) {
        audioTracks.forEach((publication) => {
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

  const handleCameraClick = async () => {
    if (cameraOff) {
      await localTrack?.unmute();
    } else {
      await localTrack?.mute();
    }
    setCameraOff(!cameraOff);
  };

  const handleCameraChange = async (event: React.ChangeEvent<HTMLSelectElement>) => {
    const deviceId = event.target.value;
    try {
      const mediaStream = await navigator.mediaDevices.getUserMedia({
        video: { deviceId: { exact: deviceId } },
      });

      const videoTrack = mediaStream.getVideoTracks()[0];
      if (videoTrack) {
        const localVideoTrack = new LocalVideoTrack(videoTrack);
        await room?.localParticipant.publishTrack(localVideoTrack);
        localTrack?.stop();
        setLocalTrack(localVideoTrack);
      }
    } catch (error) {
      console.error('카메라 바꾸기 에러 : ', error);
    }
  };

  const toggleLocalTrackSize = () => {
    setIsLocalTrackLarge(!isLocalTrackLarge);
  };

  return {
    room,
    localTrack,
    remoteTracks,
    participantName,
    roomName,
    isLocalTrackLarge,
    muted,
    cameraOff,
    cameras,
    isDataChannelReady,
    joinRoom,
    leaveRoom,
    handleMuteClick,
    handleCameraClick,
    handleCameraChange,
    toggleLocalTrackSize,
  };
};
