package com.nus.National_University_of_Skill_Backend.repository

import com.nus.National_University_of_Skill_Backend.model.entity.TeacherLessonDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherLessonDetailRepository : JpaRepository<TeacherLessonDetail, Long>
