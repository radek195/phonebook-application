package org.example.infrastructure.initializers;

import org.example.domain.contact.ContactService;
import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.states.InitialState;
import org.example.domain.user.UserService;
import org.example.infrastructure.utils.ScannerUtil;

import java.util.Scanner;

public class ApplicationInitializer {

    public static void run() {
        ScannerUtil scannerUtil = new ScannerUtil(new Scanner(System.in));
        ConversationContext context = new ConversationContext(
                scannerUtil,
                new UserService.Impl(RepositoryProvider.getUserRepository()),
                new ContactService.Impl(RepositoryProvider.getContactRepository())
        );
        context.setState(new InitialState(context));
        context.conversationLoop();
    }

}
