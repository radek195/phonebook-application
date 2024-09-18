package org.example.domain.conversation.state;

import org.example.domain.conversation.ConversationContext;

public interface State {

    void handle(ConversationContext context);

    State evaluateNewState();
}
