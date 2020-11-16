package com.brainbackdoor.sonpadong.notice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping(NOTICE_BASE_URL)
class ApiNoticeController {
    @Autowired
    private lateinit var noticeService: NoticeService

    @GetMapping
    fun find(): ResponseEntity<List<NoticeView>> =
            ResponseEntity.ok(noticeService.find())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<NoticeView> =
            ResponseEntity.ok(noticeService.findViewById(id))

    @PostMapping
    fun create(@RequestBody noticeView: NoticeCreateView): ResponseEntity<NoticeView> {
        val notice = noticeService.create(noticeView)

        return ResponseEntity.created(URI("$NOTICE_BASE_URL/${notice.id}")).build()
    }

    @PutMapping("/{id}")
    fun update(@RequestBody noticeView: NoticeUpdateView, @PathVariable id: Long): ResponseEntity<NoticeView> =
            ResponseEntity.ok(noticeService.update(id, noticeView))


}
