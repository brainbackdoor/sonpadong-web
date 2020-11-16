package com.brainbackdoor.sonpadong

import com.brainbackdoor.sonpadong.notice.NOTICE_HOME_URL
import com.brainbackdoor.sonpadong.notice.NoticeService
import com.brainbackdoor.sonpadong.notice.NoticeView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiHomeController {
    @Autowired
    private lateinit var noticeService: NoticeService

    @GetMapping(NOTICE_HOME_URL)
    fun findLatest(): ResponseEntity<List<NoticeView>> = ResponseEntity.ok(noticeService.findLatest())
}
