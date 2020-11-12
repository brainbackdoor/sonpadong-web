package com.brainbackdoor.sonpadong.notice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(NOTICE_BASE_URL)
class NoticeController {
    @Autowired
    private lateinit var noticeService: NoticeService

    @GetMapping
    fun find() : ResponseEntity<List<NoticeView>> = ResponseEntity.ok(noticeService.find())

}
