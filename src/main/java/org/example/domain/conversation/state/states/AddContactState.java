package org.example.domain.conversation.state.states;

import org.example.domain.contact.ContactDto;
import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class AddContactState implements State {

    @Override
    public void handle(ConversationContext context) {
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

        ContactDto dto = ContactDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNumber(phoneNumber)
                .description(description)
                .build();

        //TODO save contact


    }

    @Override
    public State evaluateNewState() {
        return null;
    }
}
