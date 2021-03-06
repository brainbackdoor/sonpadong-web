package sonpadong.web.boards.categories

import io.restassured.mapper.TypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.http.HttpStatus
import sonpadong.web.support.AcceptanceTest
import sonpadong.web.support.given

class CategoryAcceptanceTest : AcceptanceTest() {

    @ParameterizedTest
    @CsvSource("나눔의 광장")
    fun `카테고리를 생성한다`(title: String) {
        createCategories(listOf(title, "사목분과 및 총구역"))
        val categories = given().with()
                .get(CATEGORY_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<CategoryView>>() {})

        assertThat(categories.first().title).isEqualTo(title)
    }
}

fun createCategories(titles: List<String>) {
    titles.forEach { createCategory(it) }
}

fun createCategory(title: String) {
    given().with()
            .body(CategoryCreateView(title))
            .post(CATEGORY_BASE_URL)
            .then()
            .statusCode(HttpStatus.CREATED.value())
}
