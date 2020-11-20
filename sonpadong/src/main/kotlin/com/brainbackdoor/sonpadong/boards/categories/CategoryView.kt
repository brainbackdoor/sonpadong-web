package com.brainbackdoor.sonpadong.boards.categories

const val CATEGORY_BASE_URL = "/api/categories"

class CategoryCreateView(
        val title: String
) {
    fun toCategory(): Category = Category(
            title
    )
}

class CategoryView(
        val id: Long,
        val title: String
)
