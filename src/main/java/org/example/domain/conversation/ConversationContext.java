package org.example.domain.conversation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.domain.conversation.state.State;
import org.example.domain.conversation.state.states.InitialState;
import org.example.infrastructure.utils.ScannerUtil;

@RequiredArgsConstructor
public class ConversationContext {

    @Getter
    public final ScannerUtil scanner;


    @Setter
    private boolean isFinished;
    @Setter
    private State state;

    public void conversationLoop() {
        this.state = new InitialState();

        while (!isFinished) {
            this.state.handle(this);
        }
    }
}

