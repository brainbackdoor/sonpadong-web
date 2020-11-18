package com.brainbackdoor.sonpadong.board

import com.brainbackdoor.sonpadong.board.category.CATEGORY_BASE_URL
import com.brainbackdoor.sonpadong.board.category.CategoryCreateView
import com.brainbackdoor.sonpadong.support.AcceptanceTest
import com.brainbackdoor.sonpadong.support.given
import io.restassured.mapper.TypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.http.HttpStatus

class BoardAcceptanceTest : AcceptanceTest() {

    @ParameterizedTest
    @CsvSource("공지사항")
    fun `게시판을 생성한다`(title: String) {
        createCategory("나눔의 광장")
        val view = BoardCreateView(1L, title)
        given().with()
                .body(view)
                .post(BOARD_BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())

        val boards = given().with()
                .get(BOARD_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<BoardView>>() {})

        assertThat(boards.last().title).isEqualTo(view.title)
    }

    fun createCategory(title: String) {
        given().with()
                .body(CategoryCreateView(title))
                .post(CATEGORY_BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
    }
}
