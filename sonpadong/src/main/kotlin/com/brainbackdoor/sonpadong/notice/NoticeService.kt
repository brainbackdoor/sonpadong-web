package com.brainbackdoor.sonpadong.notice

import com.brainbackdoor.sonpadong.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoticeService @Autowired constructor(
        private val noticeRepository: NoticeRepository
) {
    fun find(): List<NoticeView> = toNoticeViews(noticeRepository.findAll())
    fun findLatest(): List<NoticeView> = toNoticeViews(noticeRepository.findTop5ByOrderByCreatedDateDesc())
    fun findById(id: Long): Notice = noticeRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("$id 에 해당하는 공지사항이 없습니다") }

    fun findViewById(id: Long) = toNoticeView(findById(id))

    fun create(noticeView: NoticeCreateView): NoticeView {
        val notice = noticeView.toNotice()
        return toNoticeView(noticeRepository.save(notice))
    }

    fun update(id: Long, view: NoticeUpdateView): NoticeView {
        val notice = findById(id)
        notice.update(view.toNotice())
        return toNoticeView(notice)
    }
}
