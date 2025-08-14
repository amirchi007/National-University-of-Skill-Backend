package com.nus.National_University_of_Skill_Backend.service

import com.nus.National_University_of_Skill_Backend.model.entity.TeacherLessonDetail
import com.nus.National_University_of_Skill_Backend.repository.TeacherLessonDetailRepository
import org.springframework.stereotype.Service

@Service
class TeacherLessonDetailService(
    private val repository: TeacherLessonDetailRepository
) {
    fun save(detail: TeacherLessonDetail) = repository.save(detail)
    fun findById(id: Long) = repository.findById(id)
    fun findAll() = repository.findAll()
    fun delete(id: Long) = repository.deleteById(id)
}
