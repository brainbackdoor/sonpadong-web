package sonpadong.web.support

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

abstract class BaseTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class ControllerTest protected constructor()

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AcceptanceTest protected constructor() {
    @LocalServerPort
    private var serverPort: Int = 0

    @BeforeEach
    fun port() {
        RestAssured.port = serverPort
    }
}

fun given(): RequestSpecification =
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .log()
                .all()
