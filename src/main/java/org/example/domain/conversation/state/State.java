package org.example.domain.conversation.state;

import lombok.RequiredArgsConstructor;
import org.example.domain.conversation.ConversationContext;

@RequiredArgsConstructor
public abstract class State {

    protected final ConversationContext context;

    public abstract void handle();

    protected abstract State evaluateNewState(String answer);
}
