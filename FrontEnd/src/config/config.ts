export const APPLICATION_SERVER_URL = import.meta.env.APPLICATION_SERVER_URL || (window.location.hostname === "localhost" ? "http://localhost:6080/" : "https://" + window.location.hostname + ":6443/");
export const LIVEKIT_URL = import.meta.env.VITE_LIVEKIT_API_URL || (window.location.hostname === "localhost" ? "ws://localhost:7880/" : "wss://" + window.location.hostname + ":7443/");
export const LIVEKIT_TOKEN = import.meta.env.VITE_TOKEN;
