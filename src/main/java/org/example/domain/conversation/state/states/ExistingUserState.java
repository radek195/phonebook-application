package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class ExistingUserState extends State {
    public ExistingUserState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Username taken.");
        System.out.println("T - Try again, H - Home, E - Exit");
        context.setState(evaluateNewState(context.getClientInput()));
    }

    @Override
    protected State evaluateNewState(String answer) {
        return switch (answer) {
            case "T" -> new RegisterState(context);
            case "H" -> new InitialState(context);
            case "E" -> new ExitState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new InitialState(context);
            }
        };
    }
}
