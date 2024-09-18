package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class LogInState implements State {

    boolean correctCredentials;

    @Override
    public void handle(ConversationContext context) {
        System.out.println("Please enter your username or type E to exit");
        String username = context.getClientInput();

        System.out.println("Please enter your password:");
        String password = context.getClientInput();

        //TODO validate credentials and change user id
        context.setLoggedInUserId(1L);
        context.setState(evaluateNewState());
    }


    @Override
    public State evaluateNewState() {
        if(correctCredentials) {
            return new LoggedInState();
        }

        return new IncorrectCredentialsState();
    }

}
