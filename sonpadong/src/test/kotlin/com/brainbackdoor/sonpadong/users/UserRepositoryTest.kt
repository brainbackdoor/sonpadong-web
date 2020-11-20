package com.brainbackdoor.sonpadong.users

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `8자의 랜덤 ID가 생성된다`() {
        val user = User("씨유", "cu@woowahan.com")
        val persistUser = userRepository.save(user)

        assertThat(persistUser.id.length).isEqualTo(8)
    }
}
