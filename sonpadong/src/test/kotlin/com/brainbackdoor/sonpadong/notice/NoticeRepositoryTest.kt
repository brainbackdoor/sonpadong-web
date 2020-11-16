package com.brainbackdoor.sonpadong.notice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime.now
import javax.transaction.Transactional

@DataJpaTest
@Transactional
class NoticeRepositoryTest {
    @Autowired
    private lateinit var repository: NoticeRepository

    @Test
    fun `글을 작성하면 작성시간이 저장된다`() {
        val now = now()
        val notice = Notice("사목지침", "친애하는 형제자매 여러분")
        repository.save(notice)

        assertThat(notice.createdDate).isAfterOrEqualTo(now)
    }
}
