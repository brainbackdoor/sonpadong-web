package com.brainbackdoor.sonpadong.notice

const val NOTICE_BASE_URL = "/api/notices"
const val NOTICE_HOME_URL = "/api/notices-latest"

class NoticeCreateView(
        val title: String,
        val content: String
) {
    fun toNotice(): Notice = Notice(
            title,
            content
    )
}

class NoticeUpdateView(
        val title: String,
        val content: String
) {
    fun toNotice(): Notice = Notice(
            title,
            content
    )
}

class NoticeView(
        val id: Long,
        val title: String,
        val content: String,
        val createdDate: String
)
