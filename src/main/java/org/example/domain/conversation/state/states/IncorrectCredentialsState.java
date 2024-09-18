package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class IncorrectCredentialsState implements State {

    String answer;

    @Override
    public void handle(ConversationContext context) {
        System.out.println("Username or password is incorrect.");
        System.out.println("T - Try again, E - Exit");
        this.answer = context.getClientInput();
    }

    @Override
    public State evaluateNewState() {
        if(answer.equals("T")) {
            return new LogInState();
        }

        return new ExitState();
    }
}
