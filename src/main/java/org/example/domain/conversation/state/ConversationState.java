package org.example.domain.conversation.state;

import org.example.domain.conversation.ConversationContext;

public interface ConversationState {

    void handle(ConversationContext context);

}
