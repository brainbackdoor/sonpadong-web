package com.brainbackdoor.sonpadong.notice

import com.brainbackdoor.sonpadong.BaseEntity
import javax.persistence.Entity

@Entity
class Notice(
        var title: String,
        var content: String
) : BaseEntity<Notice>() {
    fun update(update: Notice) {
        this.title = update.title
        this.content = update.content
    }
}
