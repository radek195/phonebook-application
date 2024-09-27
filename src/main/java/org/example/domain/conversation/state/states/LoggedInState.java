package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class LoggedInState extends State {
    public LoggedInState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("What would you like to do?");
        System.out.println("V - View contacts, A - Add contact, L - Log out, E - Exit");

        context.setState(evaluateNewState(context.getClientInput()));
    }

    @Override
    public State evaluateNewState(String answer) {
        return switch (answer) {
            case "V" -> new ViewContactsState(context);
            case "A" -> new AddContactState(context);
            case "E" -> new ExitState(context);
            case "L" -> new InitialState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new LoggedInState(context);
            }
        };
    }
}
