package com.nus.National_University_of_Skill_Backend.feature.Post.Service

import com.nus.National_University_of_Skill_Backend.feature.Post.Post
import com.nus.National_University_of_Skill_Backend.feature.Post.PostRepository
import com.nus.National_University_of_Skill_Backend.feature.Post.PostStatus
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository
) {
    fun save(post: Post) = repository.save(post)

    fun getById(id: Long): Post =
        repository.findById(id).orElseThrow { RuntimeException("Post not found") }

    fun getAll() = repository.findAll()

    fun deleteById(id: Long) = repository.deleteById(id)

    fun search(teacherId: Long?, lessonId: Long?, field: String?): List<Post> {
        return when {
            teacherId != null && lessonId != null && field != null ->
                repository.findByTeacherIdAndLessonIdAndLessonField(teacherId, lessonId, field)
            teacherId != null && lessonId != null ->
                repository.findByTeacherIdAndLessonId(teacherId, lessonId)
            teacherId != null && field != null ->
                repository.findByTeacherIdAndLessonField(teacherId, field)
            lessonId != null && field != null ->
                repository.findByLessonIdAndLessonField(lessonId, field)
            teacherId != null ->
                repository.findByTeacherId(teacherId)
            lessonId != null ->
                repository.findByLessonId(lessonId)
            field != null ->
                repository.findByLessonField(field)
            else -> repository.findAll()
        }
    }

    fun approvePost(postId: Long) {
        val post = getById(postId)
        post.status = PostStatus.APPROVED
        repository.save(post)
    }

    fun rejectPost(postId: Long) {
        val post = getById(postId)
        post.status = PostStatus.REJECTED
        repository.save(post)
    }

    fun getApprovedPostsForTeacher(teacherId: Long): List<Post> =
        repository.findByTeacherIdAndStatus(teacherId, PostStatus.APPROVED)

    fun getPendingPosts(): List<Post> =
        repository.findByStatus(PostStatus.PENDING)
}
