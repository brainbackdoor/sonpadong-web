package com.brainbackdoor.sonpadong.notice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
class NoticeController {
    @Autowired
    private lateinit var noticeService: NoticeService

    @GetMapping(NOTICE_BASE_URL)
    fun find(): ResponseEntity<List<NoticeView>> = ResponseEntity.ok(noticeService.find())

    @PostMapping(NOTICE_BASE_URL)
    fun create(@RequestBody noticeView: NoticeCreateView): ResponseEntity<NoticeView> {
        val notice = noticeService.create(noticeView)

        return ResponseEntity.created(URI(NOTICE_BASE_URL + "/" + notice.id)).build()
    }

    @GetMapping(NOTICE_HOME_URL)
    fun findLatest(): ResponseEntity<List<NoticeView>> = ResponseEntity.ok(noticeService.findLatest())
}
