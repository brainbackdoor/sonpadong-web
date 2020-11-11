package com.brainbackdoor.sonpadong.notice

import com.brainbackdoor.sonpadong.BaseEntity
import javax.persistence.Entity

@Entity
class Notice(
        val title: String,
        val content: String
) : BaseEntity<Notice>()
