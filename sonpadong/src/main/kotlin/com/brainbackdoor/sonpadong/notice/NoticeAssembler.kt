package com.brainbackdoor.sonpadong.notice

fun toNoticeViews(notices: List<Notice>): List<NoticeView> {
    return notices.map { toNoticeView(it) }
}

private fun toNoticeView(notice: Notice): NoticeView {
    return NoticeView(
            notice.title,
            notice.content,
            notice.createdDate.toString()
    )
}
