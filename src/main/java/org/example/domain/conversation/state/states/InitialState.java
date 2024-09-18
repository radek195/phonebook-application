package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class InitialState implements State {

    String answer;

    @Override
    public void handle(ConversationContext context) {
        System.out.println("What would you like to do?");
        System.out.println("R - Register, L - Log in, E - Exit");
        this.answer = context.getClientInput();
        context.setState(evaluateNewState());
    }

    @Override
    public State evaluateNewState() {
        return switch (answer) {
            case "R" -> throw new RuntimeException("Not Implemented yet"); //TODO
            case "L" -> new LogInState();
            case "E" -> new ExitState();
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new InitialState();
            }
        };
    }
}
