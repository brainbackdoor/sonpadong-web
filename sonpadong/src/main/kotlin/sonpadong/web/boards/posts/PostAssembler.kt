package sonpadong.web.boards.posts

fun toPostViews(posts: List<Post>): List<PostView> = posts.map { toPostView(it) }

fun toPostView(post: Post): PostView {
    return PostView(
            post.id,
            post.title,
            post.content,
            post.createdDate.toString()
    )
}
