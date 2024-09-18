package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class LoggedInState implements State {

    String answer;

    @Override
    public void handle(ConversationContext context) {
        System.out.println("What would you like to do?");
        System.out.println("V - View contacts, A - Add contact, E - Exit");
        this.answer = context.getClientInput();
    }

    @Override
    public State evaluateNewState() {
        return switch (answer) {
//            case "V" -> new ViewContactsState(); TODO
            case "A" -> new AddContactState();
            case "E" -> new ExitState();
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new LoggedInState();
            }
        };
    }
}
