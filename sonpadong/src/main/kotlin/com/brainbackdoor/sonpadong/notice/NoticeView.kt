package com.brainbackdoor.sonpadong.notice

const val NOTICE_BASE_URL = "/api/notices"
const val NOTICE_HOME_URL = "/api/notices-latest"

class NoticeCreateView (
        val title: String,
        val contents: String
) {
    fun toNotice(): Notice = Notice(
            title,
            contents
    )
}

class NoticeView (
        val id : Long,
        val title: String,
        val contents: String,
        val createdDate: String
)
