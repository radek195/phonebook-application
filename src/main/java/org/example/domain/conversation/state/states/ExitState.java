package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class ExitState extends State {

    public ExitState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        context.finishConversation();
    }

    @Override
    public State evaluateNewState(String answer) {
        return null;
    }
}
