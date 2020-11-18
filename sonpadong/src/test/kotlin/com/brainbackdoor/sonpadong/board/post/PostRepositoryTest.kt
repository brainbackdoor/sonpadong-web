package com.brainbackdoor.sonpadong.board.post

import com.brainbackdoor.sonpadong.board.Board
import com.brainbackdoor.sonpadong.board.category.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime.now
import javax.transaction.Transactional

@DataJpaTest
@Transactional
class PostRepositoryTest {
    @Autowired
    private lateinit var repository: PostRepository

    @Test
    fun `글을 작성하면 작성시간이 저장된다`() {
        val now = now()
        val board = Board("공지사항", Category("나눔의 광장"))
        val notice = Post("사목지침", "친애하는 형제자매 여러분", board)
        repository.save(notice)

        assertThat(notice.createdDate).isAfterOrEqualTo(now)
    }
}
