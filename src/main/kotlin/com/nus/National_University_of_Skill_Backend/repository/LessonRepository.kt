package com.nus.National_University_of_Skill_Backend.repository

import com.nus.National_University_of_Skill_Backend.model.entity.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : JpaRepository<Lesson, Long>