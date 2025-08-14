package com.nus.National_University_of_Skill_Backend.service

import com.nus.National_University_of_Skill_Backend.model.entity.Teacher
import com.nus.National_University_of_Skill_Backend.repository.TeacherRepository
import org.springframework.stereotype.Service

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {
    fun getAllTeachers() = teacherRepository.findAll()
    fun getTeacherById(id: Long) = teacherRepository.findById(id).orElseThrow { RuntimeException("Teacher not found") }
    fun saveTeacher(teacher: Teacher) = teacherRepository.save(teacher)
    fun deleteTeacher(id: Long) = teacherRepository.deleteById(id)
}