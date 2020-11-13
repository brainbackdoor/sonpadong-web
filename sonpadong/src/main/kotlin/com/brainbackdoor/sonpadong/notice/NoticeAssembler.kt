package com.brainbackdoor.sonpadong.notice

fun toNoticeViews(notices: List<Notice>): List<NoticeView> {
    return notices.map { toNoticeView(it) }
}

fun toNoticeView(notice: Notice): NoticeView {
    return NoticeView(
            notice.id,
            notice.title,
            notice.content,
            notice.createdDate.toString()
    )
}
