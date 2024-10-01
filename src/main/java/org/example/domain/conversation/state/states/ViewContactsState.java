package org.example.domain.conversation.state.states;

import org.example.domain.contact.ContactDto;
import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;

import java.util.List;

public class ViewContactsState extends State {

    public ViewContactsState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        List<ContactDto> contactList = context.getContactList();
        contactList.forEach(ContactDto::displayContact);

        System.out.println("What would you like to do?");
        System.out.println("H - Home, A - Add contact, E - Exit");
        System.out.println("Or type id of the contact you want to edit.");
        String answer = context.getClientInput();
        if (existingContactIdTyped(answer, contactList)) {
            context.setState(new ModifyContactState(context));
            return;
        }
        context.setState(evaluateNewState(answer));
    }

    @Override
    public State evaluateNewState(String answer) {
        return switch (answer) {
            case "A" -> new AddContactState(context);
            case "H" -> new LoggedInState(context);
            case "E" -> new ExitState(context);
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new LoggedInState(context);
            }
        };
    }

    public boolean existingContactIdTyped(String answer, List<ContactDto> contactList) {
        try {
            long id = Long.parseLong(answer);
            List<ContactDto> foundContact = contactList
                    .stream()
                    .filter(contact -> contact.id() == id)
                    .toList();

            if (foundContact.size() == 1) {
                context.existingContact = foundContact.get(0);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
