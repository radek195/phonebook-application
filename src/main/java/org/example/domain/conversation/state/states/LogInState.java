package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class LogInState extends State {

    public LogInState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Please enter your username:");
        String username = context.getClientInput();

        System.out.println("Please enter your password:");
        String password = context.getClientInput();

        boolean correctCredentials = context.validateUserCredentials(username, password);
        if (correctCredentials) {
            context.setState(new LoggedInState(context));
            return;
        }

        context.setState(evaluateNewState(""));
    }

    @Override
    public State evaluateNewState(String answer) {
        return new IncorrectCredentialsState(context);
    }

}
