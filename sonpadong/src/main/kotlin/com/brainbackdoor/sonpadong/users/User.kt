package com.brainbackdoor.sonpadong.users

import com.brainbackdoor.sonpadong.RandomIdEntity
import javax.persistence.Entity

@Entity
class User(
    var name: String,
    val email: String
) : RandomIdEntity<User>() {
}
