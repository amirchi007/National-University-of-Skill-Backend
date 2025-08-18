package com.nus.National_University_of_Skill_Backend.feature.teacher

import com.nus.National_University_of_Skill_Backend.feature.Post.PostStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : JpaRepository<Teacher, Long> {
    fun findByStatus(status: PostStatus): List<Teacher>

    fun findByNameContainingIgnoreCaseAndStatus(
        name: String,
        status: PostStatus,
        pageable: Pageable
    ): Page<Teacher>

    fun findByFieldContainingIgnoreCaseAndStatus(
        field: String,
        status: PostStatus,
        pageable: Pageable
    ): Page<Teacher>
}

