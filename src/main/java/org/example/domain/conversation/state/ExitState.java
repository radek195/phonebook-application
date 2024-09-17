package org.example.domain.conversation.state;

import org.example.domain.conversation.ConversationContext;

public class ExitState implements ConversationState {

    @Override
    public void handle(ConversationContext context) {
        context.setFinished(true);
    }
}
