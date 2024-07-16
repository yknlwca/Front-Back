import React from 'react';
import { tokenize, createDefaultGrammar } from '@livekit/components-core';

export function formatChatMessageLinks(message: string): React.ReactNode {
  return tokenize(message, createDefaultGrammar()).map((tok, i) => {
    if (typeof tok === 'string') {
      return tok;
    } else {
      const content = tok.content.toString();
      const href =
        tok.type === 'url'
          ? /^http(s?):\/\//.test(content)
            ? content
            : `https://${content}`
          : `mailto:${content}`;
      return (
        <a className="lk-chat-link" key={i} href={href} target="_blank" rel="noreferrer">
          {content}
        </a>
      );
    }
  });
}
