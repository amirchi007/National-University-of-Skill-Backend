package com.nus.National_University_of_Skill_Backend.feature.Post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByTeacherId(teacherId: Long): List<Post>
    fun findByLessonId(lessonId: Long): List<Post>
    fun findByLessonField(field: String): List<Post>

    fun findByTeacherIdAndLessonId(teacherId: Long, lessonId: Long): List<Post>
    fun findByTeacherIdAndLessonField(teacherId: Long, field: String): List<Post>
    fun findByLessonIdAndLessonField(lessonId: Long, field: String): List<Post>
    fun findByTeacherIdAndLessonIdAndLessonField(teacherId: Long, lessonId: Long, field: String): List<Post>

    fun findByStatus(status: PostStatus): List<Post>
    fun findByTeacherIdAndStatus(teacherId: Long, status: PostStatus): List<Post>
}
