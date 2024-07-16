import { APPLICATION_SERVER_URL } from "../../config/config";

export const getToken = async (roomName: string, participantName: string): Promise<string> => {
  const response = await fetch(APPLICATION_SERVER_URL + 'token', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      roomName: roomName,
      participantName: participantName,
    }),
  });

  if (!response.ok) {
    const error = await response.json();
    throw new Error(`Failed to get token: ${error.errorMessage}`);
  }

  const data = await response.json();
  return data.token;
};
