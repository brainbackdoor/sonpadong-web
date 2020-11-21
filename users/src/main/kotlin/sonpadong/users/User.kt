package sonpadong.users

import javax.persistence.Entity

@Entity
class User(
    var name: String,
    val email: String
) : RandomIdEntity<User>() {
}
