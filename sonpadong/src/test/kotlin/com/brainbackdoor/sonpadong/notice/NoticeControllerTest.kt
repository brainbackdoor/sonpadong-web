package com.brainbackdoor.sonpadong.notice

import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class NoticeControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockBean
    private lateinit var noticeService: NoticeService

    @Test
    fun `공지사항을 조회한다`() {
        val noticeViews = createNoticeViews()
        whenever(noticeService.find()).thenReturn(noticeViews)

        mockMvc.perform(get(NOTICE_BASE_URL))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize<Any>(6)))
    }

    @Test
    fun `홈페이지에서 공지사항을 조회할 경우 최신글 5개만 노출한다`() {
        val noticeViews = createLatestNoticeViews()
        whenever(noticeService.findLatest()).thenReturn(noticeViews)

        mockMvc.perform(get(NOTICE_HOME_URL))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize<Any>(5)))
    }

    private fun createNoticeViews(): List<NoticeView>? {
        return listOf(
                NoticeView(1L, "2019년 사목지침", "친애하는 형제자매 여러분", "2019-03-11 오후 9:21:07"),
                NoticeView(2L, "2018년 사목지침", "가서 너희도 그렇게 하여라", "2018-02-21 오전 11:25:35"),
                NoticeView(3L, "2015 본당 사목지침", "주님 안에 사랑하는 형제자매 여러분!", "2015-01-11 오후 2:51:29")
        )
    }

    private fun createLatestNoticeViews(): List<NoticeView>? {
        return listOf(
                NoticeView(1L, "2019년 사목지침", "친애하는 형제자매 여러분", "2019-03-11 오후 9:21:07"),
                NoticeView(2L, "2018년 사목지침", "가서 너희도 그렇게 하여라", "2018-02-21 오전 11:25:35"),
                NoticeView(3L, "2015 본당 사목지침", "주님 안에 사랑하는 형제자매 여러분!", "2015-01-11 오후 2:51:29"),
                NoticeView(4L, "2014년 송파동성당 한 해 돌아보기", "2014년 송파동성당 한 해 돌아보기", "2015-01-01 오후 6:39:21"),
                NoticeView(5L, "국군장병 위문품보내기 주소접수", "국군의날 군인주일을 맞이하여 송파동성당", "2014-09-14 오후 7:06:17")
        )
    }
}
