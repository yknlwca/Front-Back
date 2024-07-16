import React, { useMemo } from 'react';
import type { ReceivedChatMessage } from '@livekit/components-core';

export type MessageFormatter = (message: string) => React.ReactNode;

export interface ChatEntryProps extends React.HTMLAttributes<HTMLLIElement> {
  entry: ReceivedChatMessage;
  hideName?: boolean;
  hideTimestamp?: boolean;
  messageFormatter?: MessageFormatter;
}

const ChatEntry = React.forwardRef<HTMLLIElement, ChatEntryProps>(
  ({ entry, hideName = false, hideTimestamp = false, messageFormatter, ...props }, ref) => {
    const formattedMessage = useMemo(() => {
      return messageFormatter ? messageFormatter(entry.message) : entry.message;
    }, [entry.message, messageFormatter]);
    const hasBeenEdited = !!entry.editTimestamp;
    const time = new Date(entry.timestamp);
    const locale = navigator ? navigator.language : 'en-US';

    return (
      <li
        ref={ref}
        className="lk-chat-entry"
        title={time.toLocaleTimeString(locale, { timeStyle: 'full' })}
        data-lk-message-origin={entry.from?.isLocal ? 'local' : 'remote'}
        {...props}
      >
        {(!hideTimestamp || !hideName || hasBeenEdited) && (
          <span className="lk-meta-data">
            {!hideName && (
              <strong className="lk-participant-name">
                {entry.from?.name ?? entry.from?.identity}
              </strong>
            )}
            {(!hideTimestamp || hasBeenEdited) && (
              <span className="lk-timestamp">
                {hasBeenEdited && 'edited '}
                {time.toLocaleTimeString(locale, { timeStyle: 'short' })}
              </span>
            )}
          </span>
        )}
        <span className="lk-message-body">{formattedMessage}</span>
      </li>
    );
  },
);

export default ChatEntry;
