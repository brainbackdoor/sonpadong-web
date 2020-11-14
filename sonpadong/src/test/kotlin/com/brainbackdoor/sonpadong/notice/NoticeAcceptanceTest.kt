package com.brainbackdoor.sonpadong.notice

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.mapper.TypeRef
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeAcceptanceTest {

    @LocalServerPort
    private var serverPort: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = serverPort
    }


    @ParameterizedTest
    @CsvSource("2020년 사목지침,안녕하세요")
    fun `공지사항을 생성한다`(title: String, contents: String) {
        val noticeView = NoticeCreateView(title, contents)

        given().with()
                .body(noticeView)
                .post(NOTICE_BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())

        val actual = given().with()
                .get(NOTICE_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<NoticeView>>() {})
        assertThat(actual.last().title).isEqualTo(noticeView.title)
    }

    @Test
    fun `홈페이지에서 공지사항을 조회할 경우 최신글 5개만 노출한다`() {
        createNotice(9)

        val actual = given().with()
                .get(NOTICE_HOME_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<NoticeView>>() {})

        assertThat(actual.size).isEqualTo(5)
        assertThat(actual[0].title).isEqualTo("2029년 사목지침")
    }

    fun createNotice(count: Int) {
        for (i in 0..count) {
            `공지사항을 생성한다`("202${i}년 사목지침", "형제자매 여러분")
        }
    }


    private fun given(): RequestSpecification =
            RestAssured.given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .log()
                    .all()

}
