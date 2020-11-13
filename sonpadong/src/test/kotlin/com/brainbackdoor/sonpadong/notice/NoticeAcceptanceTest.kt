package com.brainbackdoor.sonpadong.notice

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.mapper.TypeRef
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeAcceptanceTest {

    @Autowired
    private lateinit var noticeService: NoticeService

    @LocalServerPort
    private var serverPort: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = serverPort
    }


    @Test
    fun `공지사항을 생성한다`() {
        val noticeView = NoticeCreateView("2020년 사목지침", "안녕하세요")

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
        assertThat(actual[0].title).isEqualTo(noticeView.title)
    }

    private fun given(): RequestSpecification = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).log().all()


    @Test
    fun `홈페이지에서 공지사항을 조회할 경우 최신글 5개만 노출한다`() {

    }

}
