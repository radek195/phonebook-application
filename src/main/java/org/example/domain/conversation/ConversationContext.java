package org.example.domain.conversation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.domain.contact.ContactDto;
import org.example.domain.contact.ContactService;
import org.example.domain.conversation.state.State;
import org.example.domain.user.UserService;
import org.example.infrastructure.utils.ScannerUtil;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ConversationContext {

    public final ScannerUtil scanner;
    public final UserService userService;
    public final ContactService contactService;

    @Setter
    @Getter
    private State state;

    public boolean isFinished;
    public Long loggedInUserId;
    public ContactDto existingContact;

    public void conversationLoop() {
        while (!isFinished) {
            this.state.handle();
        }
    }

    public String getClientInput() {
        return this.scanner.getClientInput();
    }

    public void finishConversation() {
        this.isFinished = true;
    }

    public boolean validateUserCredentials(String username, String password) {
        Optional<Long> userId = userService.validateUserCredentials(username, password);
        if (userId.isPresent()) {
            loggedInUserId = userId.get();
            return true;
        }
        return false;
    }

    public List<ContactDto> getContactList() {
        return contactService.getAllContactsForUser(loggedInUserId);
    }

    public Long updateContact(ContactDto contact) {
        return contactService.updateContact(contact, existingContact.id());
    }

    public Long saveContact(ContactDto contact) {
        return contactService.saveContact(contact);
    }
}

