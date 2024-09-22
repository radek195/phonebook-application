package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class AddContactState extends State {

    public AddContactState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Please provide");
        System.out.println("Name");
        String name = context.getClientInput();
        System.out.println("Surname");
        String surname = context.getClientInput();
        System.out.println("Email (optional)");
        String email = context.getClientInput();
        System.out.println("Phone number");
        String phoneNumber = context.getClientInput();
        System.out.println("Description (optional)");
        String description = context.getClientInput();

        //TODO
        //save contact here
        //set loggedInState if successful

        context.setState(evaluateNewState(""));
    }

    @Override
    public State evaluateNewState(String answer) {
        return new FailedToAddContactState(context);
    }
}
