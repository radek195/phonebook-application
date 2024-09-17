package org.example.domain.conversation.state;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ConversationState {

    protected Map<String, String> options = new LinkedHashMap<>();

    protected abstract void setOptions();

    protected abstract void displayQuestion();

    protected abstract State evaluateNewState(String answer);
}
