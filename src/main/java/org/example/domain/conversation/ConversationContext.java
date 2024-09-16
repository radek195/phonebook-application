package org.example.domain.conversation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.domain.conversation.state.ConversationState;
import org.example.domain.conversation.state.InitialState;
import org.example.infrastructure.utils.ScannerUtil;

@RequiredArgsConstructor
public class ConversationContext {

    @Getter
    public final ScannerUtil scanner;


    @Setter private boolean isFinished;
    @Setter private ConversationState state;

    public void conversationLoop() {
        this.state = new InitialState();

        while (!isFinished) {
            handleConversation();
        }
    }

    public void handleConversation() {
        this.state.handle(this);
    }
}

