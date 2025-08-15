package com.nus.National_University_of_Skill_Backend.feature.Post.Service

import com.nus.National_University_of_Skill_Backend.feature.Post.Post
import com.nus.National_University_of_Skill_Backend.feature.Post.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository
) {
    fun save(detail: Post) = repository.save(detail)

    fun getById(id: Long): Post =
        repository.findById(id).orElseThrow { RuntimeException("TeacherLessonDetail not found") }

    fun getAll() = repository.findAll()

    fun deleteById(id: Long) = repository.deleteById(id)

    fun search(teacherId: Long?, lessonId: Long?, field: String?): List<Post> {
        return when {
            teacherId != null && lessonId != null && field != null -> repository.findByTeacherIdAndLessonIdAndLessonField(
                teacherId,
                lessonId,
                field
            )

            teacherId != null && lessonId != null -> repository.findByTeacherIdAndLessonId(teacherId, lessonId)
            teacherId != null && field != null -> repository.findByTeacherIdAndLessonField(teacherId, field)
            lessonId != null && field != null -> repository.findByLessonIdAndLessonField(lessonId, field)
            teacherId != null -> repository.findByTeacherId(teacherId)
            lessonId != null -> repository.findByLessonId(lessonId)
            field != null -> repository.findByLessonField(field)
            else -> repository.findAll()
        }
    }

    fun publishPost(postId: Long) {
        val post = repository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }
        post.isPublished = true
        repository.save(post)
    }


}