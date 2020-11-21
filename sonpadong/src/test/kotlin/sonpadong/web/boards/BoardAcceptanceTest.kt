package sonpadong.web.boards

import io.restassured.mapper.TypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.http.HttpStatus
import sonpadong.web.boards.categories.createCategory
import sonpadong.web.support.AcceptanceTest
import sonpadong.web.support.given

class BoardAcceptanceTest : AcceptanceTest() {

    @ParameterizedTest
    @CsvSource("공지사항")
    fun `게시판을 생성한다`(title: String) {
        createCategory("나눔의 광장")
        createBoards(1L, listOf(title, "송파동 주보", "자유의 광장"))

        val boards = given().with()
                .get(BOARD_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<BoardView>>() {})

        assertThat(boards.first().title).isEqualTo(title)
    }
}

fun createBoards(categoryId: Long, titles: List<String>) {
    titles.forEach { createBoard(categoryId, it) }
}

fun createBoard(categoryId: Long, title: String) {
    given().with()
            .body(BoardCreateView(categoryId, title))
            .post(BOARD_BASE_URL)
            .then()
            .statusCode(HttpStatus.CREATED.value())
}
