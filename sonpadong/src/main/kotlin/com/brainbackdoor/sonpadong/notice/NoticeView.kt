package com.brainbackdoor.sonpadong.notice

const val NOTICE_BASE_URL = "/api/notices"

class NoticeView (
    val title: String,
    val contents: String,
    val createdDate: String
)
