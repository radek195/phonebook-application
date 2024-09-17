package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.ConversationState;

public class ExitState implements ConversationState {

    @Override
    public void handle(ConversationContext context) {
        context.setFinished(true);
    }
}
