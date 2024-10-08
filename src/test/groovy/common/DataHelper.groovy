package common

import org.example.domain.contact.ContactDto
import org.example.domain.user.UserDto

trait DataHelper {

    UserDto getUserOne() {
        UserDto.builder()
                .id(1)
                .name("Mathias")
                .surname("Perry")
                .email("m.perry@yahoo.com")
                .username("perrym")
                .password("M4th1a5")
                .build()
    }

    UserDto getUserTwo() {
        UserDto.builder()
                .id(2)
                .name("Thomas")
                .surname("Knight")
                .email("t.knight@gmail.com")
                .username("teenight")
                .password("kn1ght!")
                .build()
    }

    ContactDto getContactOneFor(long userId) {
        ContactDto.builder()
                .id(1)
                .users_id(userId)
                .name("Anthony")
                .surname("Smith")
                .email("a.smith@gmail.com")
                .phoneNumber("+55787626154")
                .description("friend")
                .build()
    }

    ContactDto getContactTwoFor(long userId) {
        ContactDto.builder()
                .id(2)
                .users_id(userId)
                .name("Alicia")
                .surname("Brown")
                .email("a.brown@gmail.com")
                .phoneNumber("+53989606412")
                .description("mom")
                .build()
    }
}