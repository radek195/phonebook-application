package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.State;
import org.example.domain.user.UserDto;

public class RegisterState extends State {

    public RegisterState(ConversationContext context) {
        super(context);
    }

    @Override
    public void handle() {
        System.out.println("Please provide the following information:");
        System.out.println("Name");
        String name = context.getClientInput();
        System.out.println("Surname");
        String surname = context.getClientInput();
        System.out.println("Email");
        String email = context.getClientInput();
        System.out.println("Username");
        String username = context.getClientInput();
        System.out.println("Password");
        String password = context.getClientInput();

        if (username.isBlank() || password.isBlank()) {
            System.out.println("Username and password cannot be blank");
            context.setState(new BlankCredentialState(context));
            return;
        }

        UserDto user = UserDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .username(username)
                .password(password)
                .build();

        boolean userRegistered = context.registerNewUser(user);

        if (userRegistered) {
            System.out.println("Successfully registered!");
            context.setState(new LogInState(context));
            return;
        }

        context.setState(new ExistingUserState(context));

    }

    @Override
    protected State evaluateNewState(String answer) {
        return null;
    }
}
