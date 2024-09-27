package integration.conversation

import org.example.domain.contact.ContactService
import org.example.domain.conversation.ConversationContext
import org.example.domain.conversation.state.states.ExitState
import org.example.domain.conversation.state.states.InitialState
import org.example.domain.conversation.state.states.LogInState
import org.example.domain.conversation.state.states.LoggedInState
import org.example.domain.user.UserService
import org.example.infrastructure.utils.ScannerUtil
import spock.lang.Specification
import spock.lang.Subject

class ConversationContextIT extends Specification {

    def scannerUtil = Mock(ScannerUtil)
    def userService = Mock(UserService)
    def contactService = Mock(ContactService)

    @Subject
    def conversationContext = new ConversationContext(scannerUtil, userService, contactService)

    def 'should change state from Initial to LogIn'() {
        given:
            conversationContext.setState(new InitialState(conversationContext))
            scannerUtil.getClientInput() >> "L"

        when:
            conversationContext.state.handle()

        then:
            conversationContext.state instanceof LogInState
    }

    def 'should change state from Initial to Exit'() {
        given:
            conversationContext.setState(new InitialState(conversationContext))
            scannerUtil.getClientInput() >> "E"

        when:
            conversationContext.state.handle()

        then:
            conversationContext.state instanceof ExitState
    }

    def 'should change state from LogIn to LoggedIn'() {
        given:
            conversationContext.setState(new LogInState(conversationContext))
            1 * scannerUtil.getClientInput() >> "John"
            1 * scannerUtil.getClientInput() >> "Nolan"
            userService.validateUserCredentials("John", "Nolan") >> Optional.of(17l)

        when:
            conversationContext.state.handle()

        then:
            conversationContext.state instanceof LoggedInState

        and:
            conversationContext.loggedInUserId == 17
    }

}
