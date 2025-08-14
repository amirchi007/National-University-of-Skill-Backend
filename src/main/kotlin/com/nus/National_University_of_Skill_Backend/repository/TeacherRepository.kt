package com.nus.National_University_of_Skill_Backend.repository

import com.nus.National_University_of_Skill_Backend.model.entity.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : JpaRepository<Teacher, Long>