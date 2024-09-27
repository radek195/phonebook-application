package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class InitialState extends State {

    public InitialState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        context.loggedInUserId = null;
        System.out.println("What would you like to do?");
        System.out.println("R - Register, L - Log in, E - Exit");

        context.setState(evaluateNewState(context.getClientInput()));
    }

    @Override
    public State evaluateNewState(String answer) {
        return switch (answer) {
            case "R" -> throw new RuntimeException("Not Implemented yet"); //TODO
            case "L" -> new LogInState(context);
            case "E" -> new ExitState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new InitialState(context);
            }
        };
    }
}
