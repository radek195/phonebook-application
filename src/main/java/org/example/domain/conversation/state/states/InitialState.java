package org.example.domain.conversation.state.states;

import org.example.domain.conversation.ConversationContext;
import org.example.domain.conversation.state.ConversationState;
import org.example.domain.conversation.state.State;

public class InitialState extends State implements ConversationState {

    public InitialState() {
        setOptions();
    }

    @Override
    public void handle(ConversationContext context) {
        displayQuestion();
        String answer = context.getScanner().getClientInput();
        context.setState(evaluateNewState(answer));
    }

    @Override
    public void displayQuestion() {
        System.out.println("What would you like to do?");
        System.out.println(super.options);
    }

    @Override
    public ConversationState evaluateNewState(String answer) {
        return switch (answer) {
            case "L" -> throw new RuntimeException("Not implemented yet"); //TODO implement login state
            case "E" -> new ExitState();
            default -> {
                System.out.println("Sorry I don't understand. Please try again");
                yield new InitialState();
            }
        };
    }

    @Override
    protected void setOptions() {
        super.options.put("L", "Log in");
        super.options.put("E", "Exit");
    }
}
