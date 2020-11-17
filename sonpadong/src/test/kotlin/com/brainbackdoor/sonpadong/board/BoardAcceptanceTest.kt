package com.brainbackdoor.sonpadong.board

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
        val view = BoardCreateView(title)
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
}
