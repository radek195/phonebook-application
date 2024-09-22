package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

import java.util.List;

public class ViewContactsState extends State {

    public ViewContactsState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
//        List<ContactDto> contactList = context.getContactRepository().getAll(); TODO implement getAll method
        System.out.println(List.of("contact1", "contact2", "contact3")); //TODO print list here

        System.out.println("What would you like to do?");
        System.out.println("A - Add contact, E - Exit");
        System.out.println("Or type id of the contact you want to edit.");
        String answer = context.getClientInput();
        if (existingContactIdTyped(answer)) {
            context.setState(new ModifyContactState(context));
        }
        context.setState(evaluateNewState(answer));
    }

    @Override
    public State evaluateNewState(String answer) {
        return switch (answer) {
            case "A" -> new AddContactState(context);
            case "E" -> new ExitState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new LoggedInState(context);
            }
        };
    }

    public boolean existingContactIdTyped(String answer) {
        try {
            Long id = Long.parseLong(answer);
//            TODO get contact and set it in context
//            ContactDto contact = context.getContactRepository().get(id);
//            context.setContact(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
