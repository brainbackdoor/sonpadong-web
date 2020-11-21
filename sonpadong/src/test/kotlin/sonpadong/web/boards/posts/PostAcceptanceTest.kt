package sonpadong.web.boards.posts

import io.restassured.mapper.TypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.http.HttpStatus
import sonpadong.web.boards.categories.createCategory
import sonpadong.web.boards.createBoards
import sonpadong.web.support.AcceptanceTest
import sonpadong.web.support.given


class PostAcceptanceTest : AcceptanceTest() {

    @BeforeEach
    fun setUp() {
        createCategory("나눔의 광장")
        createBoards(1L, listOf("공지사항", "송파동 주보", "자유의 광장"))
        createPosts(1L, 9)
    }

    @ParameterizedTest
    @CsvSource("2020년 사목지침,안녕하세요")
    fun `게시글을 생성한다`(title: String, contents: String) {
        createPost(1L, title, contents)

        val actual = given().with()
                .get(POST_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<PostView>>() {})
        assertThat(actual.last().title).isEqualTo(title)
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
        val post = given().with()
                .get("$POST_BASE_URL/$id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(PostView::class.java)

        assertThat(post.title).isEqualTo("202${id}년 사목지침")
        return post
    }

    @ParameterizedTest
    @ValueSource(ints = [1])
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


}

fun createPosts(boardId: Long, count: Int) {
    for (i in 1..count) {
        createPost(boardId, "202${i}년 사목지침", "형제자매 여러분")
    }
}

fun createPost(boardId: Long, title: String, contents: String) {
    given().with()
            .body(PostCreateView(boardId, title, contents))
            .post(POST_BASE_URL)
            .then()
            .statusCode(HttpStatus.CREATED.value())
}
