package com.brainbackdoor.sonpadong.board.post

import com.brainbackdoor.sonpadong.board.BOARD_BASE_URL
import com.brainbackdoor.sonpadong.board.BoardView
import com.brainbackdoor.sonpadong.support.AcceptanceTest
import com.brainbackdoor.sonpadong.support.given
import io.restassured.mapper.TypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.http.HttpStatus


class PostAcceptanceTest : AcceptanceTest() {

    @BeforeEach
    fun setUp() {
        createBoard("공지사항")
        createPosts(9)
    }

    @ParameterizedTest
    @CsvSource("2020년 사목지침,안녕하세요")
    fun `게시글을 생성한다`(title: String, contents: String) {
        val view = PostCreateView(1L, title, contents)

        given().with()
                .body(view)
                .post(POST_BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())

        val actual = given().with()
                .get(POST_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<PostView>>() {})
        assertThat(actual.last().title).isEqualTo(view.title)
    }

    @Test
    fun `홈페이지에서 게시글을 조회할 경우 최신글 5개만 노출한다`() {
        val actual = given().with()
                .get(POST_LATEST_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<PostView>>() {})

        assertThat(actual.size).isEqualTo(5)
        assertThat(actual[0].title).isEqualTo("2029년 사목지침")
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 8])
    fun `특정 게시글을 조회한다`(id: Int): PostView {
        val notice = given().with()
                .get("$POST_BASE_URL/$id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(PostView::class.java)

        assertThat(notice.title).isEqualTo("202${id}년 사목지침")
        return notice
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 8])
    fun `게시글을 수정한다`(id: Int) {
        val view = `특정 게시글을 조회한다`(id)
        val updateView = PostUpdateView(1L, "수정후 타이틀", "수정후 컨텐츠")

        val actual = given().with()
                .body(updateView)
                .put("$POST_BASE_URL/${view.id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(PostView::class.java)
        assertThat(actual.title).isEqualTo(updateView.title)
        assertThat(actual.content).isEqualTo(updateView.content)
    }

    fun createPosts(count: Int) {
        for (i in 1..count) {
            `게시글을 생성한다`("202${i}년 사목지침", "형제자매 여러분")
        }
    }

    fun createBoard(title: String) {
        given().with()
                .body(BoardView(title))
                .post(BOARD_BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
    }
}
