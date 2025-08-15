package com.nus.National_University_of_Skill_Backend.feature.teacher.service

import com.nus.National_University_of_Skill_Backend.feature.teacher.Teacher
import com.nus.National_University_of_Skill_Backend.feature.teacher.TeacherRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {
    fun getAllTeachers() = teacherRepository.findAll()
    fun getTeacherById(id: Long) = teacherRepository.findById(id).orElseThrow { RuntimeException("Teacher not found") }
    fun saveTeacher(teacher: Teacher) = teacherRepository.save(teacher)
    fun deleteTeacher(id: Long) = teacherRepository.deleteById(id)
    fun searchTeachersByName(name: String, page: Int, size: Int) =
        teacherRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size) ).content

    fun searchTeachersByField(field: String, page: Int, size: Int) =
        teacherRepository.findByFieldContainingIgnoreCase(field, PageRequest.of(page, size)).content

}
