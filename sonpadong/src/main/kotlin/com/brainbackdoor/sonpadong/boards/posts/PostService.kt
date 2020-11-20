package com.brainbackdoor.sonpadong.boards.posts

import com.brainbackdoor.sonpadong.ResourceNotFoundException
import com.brainbackdoor.sonpadong.boards.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostService @Autowired constructor(
        private val postRepository: PostRepository,
        private val boardService: BoardService
) {
    fun find(): List<PostView> = toPostViews(postRepository.findAll())
    fun findLatest(): List<PostView> = toPostViews(postRepository.findTop5ByOrderByCreatedDateDesc())
    fun findById(id: Long): Post = postRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("$id 에 해당하는 게시글이 없습니다") }

    fun findViewById(id: Long) = toPostView(findById(id))

    fun create(view: PostCreateView): PostView {
        val board = boardService.findById(view.boardId)
        val notice = view.toPost(board)
        return toPostView(postRepository.save(notice))
    }

    fun update(id: Long, view: PostUpdateView): PostView {
        val board = boardService.findById(view.boardId)
        val notice = findById(id)
        notice.update(view.toPost(board))
        return toPostView(notice)
    }
}
