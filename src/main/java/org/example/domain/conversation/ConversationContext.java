package org.example.domain.conversation;

import lombok.Setter;
import org.example.domain.conversation.state.State;
import org.example.infrastructure.utils.ScannerUtil;


public class ConversationContext {

    @Setter
    private State state;

    public final ScannerUtil scanner;

    private boolean isFinished;
    @Setter private long loggedInUserId;

    public ConversationContext(State state, ScannerUtil scanner) {
        this.state = state;
        this.scanner = scanner;
    }

    public void conversationLoop() {
        while (!isFinished) {
            this.state.handle(this);
        }
    }

    public String getClientInput() {
        return this.scanner.getClientInput();
    }

    public void finishConversation() {
        this.isFinished = true;
    }

}

