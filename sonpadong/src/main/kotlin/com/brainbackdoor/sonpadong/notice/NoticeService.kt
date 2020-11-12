package com.brainbackdoor.sonpadong.notice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoticeService @Autowired constructor(
    private val noticeRepository: NoticeRepository
) {
    fun find() : List<NoticeView> = toNoticeViews(noticeRepository.findAll())
}
