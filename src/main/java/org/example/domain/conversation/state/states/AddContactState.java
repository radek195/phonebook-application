package org.example.domain.conversation.state.states;

import org.example.domain.contact.ContactDto;
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

        Long id = context.saveContact(ContactDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNumber(phoneNumber)
                .description(description)
                .build());

        if (id == null) {
            System.out.println("Could not add contact");
            context.setState(new FailedToAddContactState(context));
        }

        context.setState(new LoggedInState(context));
    }

    @Override
    public State evaluateNewState(String answer) {
        return null;
    }
}
