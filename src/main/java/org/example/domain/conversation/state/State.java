package org.example.domain.conversation.state;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class State {

    protected Map<String, String> options = new LinkedHashMap<>();

    protected abstract void setOptions();

    protected abstract void displayQuestion();

    protected abstract ConversationState evaluateNewState(String answer);
}
