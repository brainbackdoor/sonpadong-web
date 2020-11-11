package com.brainbackdoor.sonpadong.notice

import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long> {
}
