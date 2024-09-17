package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class ExitState implements State {

    @Override
    public void handle(ConversationContext context) {
        context.setFinished(true);
    }
}
