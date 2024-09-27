package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class IncorrectCredentialsState extends State {

    public IncorrectCredentialsState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Username or password is incorrect.");
        System.out.println("T - Try again, H - Home, E - Exit");
        context.setState(evaluateNewState(context.getClientInput()));
    }

    @Override
    public State evaluateNewState(String answer) {
        return switch (answer) {
            case "T" -> new LogInState(context);
            case "H" -> new InitialState(context);
            case "E" -> new ExitState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new InitialState(context);
            }
        };
    }
}
