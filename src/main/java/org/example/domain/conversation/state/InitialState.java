package org.example.domain.conversation.state;

import org.example.domain.conversation.ConversationContext;

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
            case "E" -> throw new RuntimeException("Not implemented yet");  //TODO implement exit state
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
