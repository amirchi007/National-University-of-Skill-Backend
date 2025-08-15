package com.nus.National_University_of_Skill_Backend.feature.teacher

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : JpaRepository<Teacher, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Teacher>
    fun findByFieldContainingIgnoreCase(field: String, pageable: Pageable): Page<Teacher>
}

