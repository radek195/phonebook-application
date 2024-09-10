package common

import org.example.domain.user.UserDto

trait DataHelper {

    UserDto getUserOne() {
        new UserDto(
                1,
                "Mathias",
                "Perry",
                "m.perry@yahoo.com",
                "perrym",
                "M4th1a5"
        )
    }

    UserDto getUserTwo() {
        new UserDto(
                2,
                "Thomas",
                "Knight",
                "t.knight@gmail.com",
                "teenight",
                "kn1ght!"
        )
    }
}