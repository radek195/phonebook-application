package org.example.domain.conversation.state.states;

import org.example.domain.contact.ContactDto;
import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

public class ModifyContactState extends State {

    public ModifyContactState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        ContactDto existing = context.getExistingContact();
        System.out.println("Please provide values to amend (or skip)");
        System.out.println("name:");
        String name = context.getClientInput();
        System.out.println("surname:");
        String surname = context.getClientInput();
        System.out.println("email:");
        String email = context.getClientInput();
        System.out.println("phoneNumber:");
        String phoneNumber = context.getClientInput();
        System.out.println("description:");
        String description = context.getClientInput();
        ContactDto dto = ContactDto.builder()
                .name(determineValue(name, existing.name()))
                .surname(determineValue(surname, existing.surname()))
                .email(determineValue(email, existing.email()))
                .phoneNumber(determineValue(phoneNumber, existing.phoneNumber()))
                .description(determineValue(description, existing.description()))
                .build();

//        TODO update contact
    }

    @Override
    public State evaluateNewState(String answer) {
        return null;
    }

    private String determineValue(String updated, String existing) {
        return updated.isBlank() ? existing : updated;
    }
}
